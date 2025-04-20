package net.theobl.extension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.theobl.extension.block.entity.ModBlockEntities;
import net.theobl.extension.block.entity.RedstoneCampfireBlockEntity;

import javax.annotation.Nullable;
import java.util.Optional;

public class RedstoneCampfireBlock extends CampfireBlock {
    public RedstoneCampfireBlock(boolean spawnParticles, int fireDamage, Properties properties) {
        super(spawnParticles, fireDamage, properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        if (level.getBlockEntity(pos) instanceof RedstoneCampfireBlockEntity campfireBlockEntity) {
            ItemStack itemstack = player.getItemInHand(hand);
            Optional<RecipeHolder<CampfireCookingRecipe>> optional = campfireBlockEntity.getCookableRecipe(itemstack);
            if (optional.isPresent()) {
                if (!level.isClientSide && campfireBlockEntity.placeFood(player, itemstack, optional.get().value().getCookingTime())) {
                    player.awardStat(Stats.INTERACT_WITH_CAMPFIRE);
                    return ItemInteractionResult.SUCCESS;
                }
                return ItemInteractionResult.CONSUME;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof RedstoneCampfireBlockEntity) {
                Containers.dropContents(level, pos, ((RedstoneCampfireBlockEntity)blockentity).getItems());
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    private static void makeRedstoneParticle(BlockState state, LevelAccessor level, BlockPos pos) {
        RandomSource random = level.getRandom();
        double d0 = (double)pos.getX() + 0.5 + (random.nextDouble() / 3) * (double)(random.nextBoolean() ? 1 : -1);
        double d1 = (double)pos.getY() + random.nextDouble() + random.nextDouble();
        double d2 = (double)pos.getZ() + 0.5 + (random.nextDouble() / 3) * (double)(random.nextBoolean() ? 1 : -1);
        level.addParticle(DustParticleOptions.REDSTONE, d0, d1, d2, 0.0, 0.0, 0.0);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (state.getValue(LIT)) {
            makeRedstoneParticle(state, level, pos);
        }
    }

    public static void dowse(@Nullable Entity entity, LevelAccessor level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) {
            for (int i = 0; i < 20; i++) {
                makeParticles((Level)level, pos, state.getValue(SIGNAL_FIRE), true);
            }
        }
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof RedstoneCampfireBlockEntity) {
            ((RedstoneCampfireBlockEntity)blockentity).dowse();
        }

        level.gameEvent(entity, GameEvent.BLOCK_CHANGE, pos);
    }

    @Override
    public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
        if (!state.getValue(BlockStateProperties.WATERLOGGED) && fluidState.getType() == Fluids.WATER) {
            boolean flag = state.getValue(LIT);
            if (flag) {
                if (!level.isClientSide()) {
                    level.playSound(null, pos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                dowse(null, level, pos, state);
            }
            level.setBlock(pos, state.setValue(WATERLOGGED, Boolean.valueOf(true)).setValue(LIT, Boolean.valueOf(false)), 3);
            level.scheduleTick(pos, fluidState.getType(), fluidState.getType().getTickDelay(level));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new RedstoneCampfireBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (level.isClientSide) {
            return state.getValue(LIT)
                    ? createTickerHelper(blockEntityType, ModBlockEntities.REDSTONE_CAMPFIRE.get(), RedstoneCampfireBlockEntity::particleTick)
                    : null;
        } else {
            return state.getValue(LIT)
                    ? createTickerHelper(blockEntityType, ModBlockEntities.REDSTONE_CAMPFIRE.get(), RedstoneCampfireBlockEntity::cookTick)
                    : createTickerHelper(blockEntityType, ModBlockEntities.REDSTONE_CAMPFIRE.get(), RedstoneCampfireBlockEntity::cooldownTick);
        }
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return direction != Direction.UP && direction != Direction.DOWN && state.getValue(LIT) ? 15 : 0;
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }
}
