package net.theobl.extension.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.theobl.extension.Config;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(TrapDoorBlock.class)
public abstract class TrapdoorBlockMixin {
    @Final
    @Shadow
    private BlockSetType type;

    @Shadow
    protected abstract void playSound(@Nullable Player pPlayer, Level pLevel, BlockPos pPos, boolean pIsOpening);

    @Inject(at = @At(value = "RETURN", ordinal = 0), method = "use", cancellable = true)
    private void extension$UseTrapdoor(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, CallbackInfoReturnable<InteractionResult> cir) {
        if (Config.ironDoor) {
            pState = pState.cycle(BlockStateProperties.OPEN);
            pLevel.setBlock(pPos, pState, 2);
            if (pState.getValue(BlockStateProperties.WATERLOGGED)) {
                pLevel.scheduleTick(pPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
            }
            this.playSound(pPlayer, pLevel, pPos, pState.getValue(BlockStateProperties.OPEN));
            cir.setReturnValue(InteractionResult.sidedSuccess(pLevel.isClientSide));
        }
    }
}
