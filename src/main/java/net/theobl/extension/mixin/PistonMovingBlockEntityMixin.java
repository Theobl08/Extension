package net.theobl.extension.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueInput;
import net.theobl.extension.extensions.PistonMovingBlockEntityExtension;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PistonMovingBlockEntity.class)
public abstract class PistonMovingBlockEntityMixin extends BlockEntity implements PistonMovingBlockEntityExtension {
    @Unique
    @Nullable
    private BlockEntity extension$renderBlockEntity;

    public PistonMovingBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    public @Nullable BlockEntity getRenderBlockEntity() {
        return this.extension$renderBlockEntity;
    }

    @Override
    public void setCarriedBlockEntity(@Nullable BlockEntity blockentity, Level level) {
        this.extension$renderBlockEntity = blockentity;
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z", shift = At.Shift.AFTER))
    private static void tickHandleBlockEntityData(Level level, BlockPos pos, BlockState state, PistonMovingBlockEntity blockEntity, CallbackInfo ci) {
        BlockEntity entity = level.getBlockEntity(pos);
        extension$handleBlockEntitiesData(blockEntity.getRenderBlockEntity(), entity, level);
    }

    @Inject(method = "finalTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z", shift = At.Shift.AFTER))
    private void finalTickHandleBlockEntityData(CallbackInfo ci) {
        BlockEntity blockEntity = this.level.getBlockEntity(this.worldPosition);
        extension$handleBlockEntitiesData(this.getRenderBlockEntity(), blockEntity, this.level);
    }

    @Unique
    private static void extension$handleBlockEntitiesData(BlockEntity input, BlockEntity output, Level level) {
        if (input != null && output != null && level != null) {
            output.loadCustomOnly(
                    TagValueInput.create(ProblemReporter.DISCARDING.forChild(input.problemPath()), level.registryAccess(), input.saveCustomOnly(level.registryAccess()))
            );
            output.setComponents(input.components());
            output.setChanged();
        }
    }
}
