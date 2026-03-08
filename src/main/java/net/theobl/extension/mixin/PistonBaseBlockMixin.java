package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.piston.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.PistonType;
import net.minecraft.world.level.redstone.ExperimentalRedstoneUtils;
import net.minecraft.world.level.redstone.Orientation;
import net.theobl.extension.Config;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.minecraft.world.level.block.Block.*;
import static net.minecraft.world.level.block.DirectionalBlock.FACING;

@Mixin(PistonBaseBlock.class)
public class PistonBaseBlockMixin {
    @Final
    @Shadow
    private boolean isSticky;

    @Definition(id = "state", local = @Local(type = BlockState.class, argsOnly = true))
    @Definition(id = "hasBlockEntity", method = "Lnet/minecraft/world/level/block/state/BlockState;hasBlockEntity()Z")
    @Expression("state.hasBlockEntity() == false")
    @ModifyExpressionValue(method = "isPushable", at = @At("MIXINEXTRAS:EXPRESSION"))
    private static boolean moveBlockEntities(boolean original, @Local(argsOnly = true) BlockState state) {
        return original || Config.pistonsCanPushBlockEntities;
    }

    @WrapMethod(method = "moveBlocks")
    private boolean extension$moveBlockEntities(Level level, BlockPos pistonPos, Direction direction, boolean extending, Operation<Boolean> original) {
        if(Config.pistonsCanPushBlockEntities) {
            BlockPos armPos = pistonPos.relative(direction);
            if (!extending && level.getBlockState(armPos).is(Blocks.PISTON_HEAD)) {
                level.setBlock(armPos, Blocks.AIR.defaultBlockState(), UPDATE_INVISIBLE | UPDATE_KNOWN_SHAPE | UPDATE_SKIP_BLOCK_ENTITY_SIDEEFFECTS);
            }

            PistonStructureResolver resolver = new PistonStructureResolver(level, pistonPos, direction, extending);
            if (!resolver.resolve()) {
                return false;
            } else {
                Map<BlockPos, BlockState> deleteAfterMove = new HashMap<>();
                List<BlockPos> toPush = resolver.getToPush();
                List<BlockState> toPushShapes = new ArrayList<>();

                for (BlockPos pos : toPush) {
                    BlockState state = level.getBlockState(pos);
                    toPushShapes.add(state);
                    deleteAfterMove.put(pos, state);
                }

                List<BlockPos> toDestroy = resolver.getToDestroy();
                BlockState[] toUpdate = new BlockState[toPush.size() + toDestroy.size()];
                Direction pushDirection = extending ? direction : direction.getOpposite();
                int updateIndex = 0;

                for (int i = toDestroy.size() - 1; i >= 0; i--) {
                    BlockPos pos = toDestroy.get(i);
                    BlockState state = level.getBlockState(pos);
                    BlockEntity blockEntity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
                    dropResources(state, level, pos, blockEntity);
                    if (!state.is(BlockTags.FIRE) && level.isClientSide()) {
                        level.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, pos, getId(state));
                    }

                    state.onDestroyedByPushReaction(level, pos, pushDirection, level.getFluidState(pos));
                    toUpdate[updateIndex++] = state;
                }

                //23w13a_or_b specific code, slightly tweaked
                Map<Integer, BlockEntity> blockEntities = new HashMap<>();
                int l = 0;

                for(int i = toPush.size(); l < i; l++) {
                    BlockPos pos = toPush.get(l);
                    BlockEntity blockEntity = toPushShapes.get(l).hasBlockEntity() ? level.getBlockEntity(pos) : null;
                    if(blockEntity != null) {
                        blockEntities.put(l, blockEntity);
                        level.removeBlockEntity(pos);
                        blockEntity.setChanged();
                    }
                }
                //End of specific code

                for (int i = toPush.size() - 1; i >= 0; i--) {
                    BlockPos pos = toPush.get(i);
                    BlockState blockState = level.getBlockState(pos);
                    pos = pos.relative(pushDirection);
                    deleteAfterMove.remove(pos);
                    BlockState actualState = Blocks.MOVING_PISTON.defaultBlockState().setValue(FACING, direction);
                    level.setBlock(pos, actualState, UPDATE_INVISIBLE | UPDATE_MOVE_BY_PISTON | UPDATE_SKIP_BLOCK_ENTITY_SIDEEFFECTS);
                    //level.setBlockEntity(MovingPistonBlock.newMovingBlockEntity(pos, actualState, toPushShapes.get(i), direction, extending, false));
                    PistonMovingBlockEntity blockEntity = (PistonMovingBlockEntity) MovingPistonBlock.newMovingBlockEntity(
                            pos, actualState, toPushShapes.get(i), direction, extending, false
                    );
                    //23w13a_or_b specific code, slightly tweaked
                    if(!blockEntities.isEmpty() && blockEntities.containsKey(i)) {
                        blockEntity.setCarriedBlockEntity(blockEntities.get(i), level);
                    }
                    //End of specific code
                    level.setBlockEntity(blockEntity);
                    toUpdate[updateIndex++] = blockState;
                }

                if (extending) {
                    PistonType type = this.isSticky ? PistonType.STICKY : PistonType.DEFAULT;
                    BlockState state = Blocks.PISTON_HEAD
                            .defaultBlockState()
                            .setValue(FACING, direction)
                            .setValue(PistonHeadBlock.TYPE, type);
                    BlockState blockState = Blocks.MOVING_PISTON
                            .defaultBlockState()
                            .setValue(MovingPistonBlock.FACING, direction)
                            .setValue(MovingPistonBlock.TYPE, this.isSticky ? PistonType.STICKY : PistonType.DEFAULT);
                    deleteAfterMove.remove(armPos);
                    level.setBlock(armPos, blockState, UPDATE_INVISIBLE | UPDATE_MOVE_BY_PISTON | UPDATE_SKIP_BLOCK_ENTITY_SIDEEFFECTS);
                    level.setBlockEntity(MovingPistonBlock.newMovingBlockEntity(armPos, blockState, state, direction, true, true));
                }

                BlockState air = Blocks.AIR.defaultBlockState();

                for (BlockPos pos : deleteAfterMove.keySet()) {
                    level.setBlock(pos, air, UPDATE_CLIENTS | UPDATE_KNOWN_SHAPE | UPDATE_MOVE_BY_PISTON);
                }

                for (Map.Entry<BlockPos, BlockState> entry : deleteAfterMove.entrySet()) {
                    BlockPos pos = entry.getKey();
                    BlockState oldState = entry.getValue();
                    oldState.updateIndirectNeighbourShapes(level, pos, UPDATE_CLIENTS);
                    air.updateNeighbourShapes(level, pos, UPDATE_CLIENTS);
                    air.updateIndirectNeighbourShapes(level, pos, UPDATE_CLIENTS);
                }

                Orientation orientation = ExperimentalRedstoneUtils.initialOrientation(level, resolver.getPushDirection(), null);
                updateIndex = 0;

                for (int i = toDestroy.size() - 1; i >= 0; i--) {
                    BlockState state = toUpdate[updateIndex++];
                    BlockPos pos = toDestroy.get(i);
                    if (level instanceof ServerLevel serverlevel) {
                        state.affectNeighborsAfterRemoval(serverlevel, pos, false);
                    }

                    state.updateIndirectNeighbourShapes(level, pos, UPDATE_CLIENTS);
                    level.updateNeighborsAt(pos, state.getBlock(), orientation);
                }

                for (int i = toPush.size() - 1; i >= 0; i--) {
                    level.updateNeighborsAt(toPush.get(i), toUpdate[updateIndex++].getBlock(), orientation);
                }

                if (extending) {
                    level.updateNeighborsAt(armPos, Blocks.PISTON_HEAD, orientation);
                }

                return true;
            }
        } else {
            return original.call(level, pistonPos, direction, extending);
        }
    }
}
