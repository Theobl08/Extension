package net.theobl.extension.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "isExceptionForConnection", at = @At("RETURN"), cancellable = true)
    private static void isExceptionForConnection(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if(state.getBlock() instanceof CarvedPumpkinBlock) {
            cir.setReturnValue(true);
        }
    }
}
