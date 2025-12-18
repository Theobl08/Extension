package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Block.class)
public abstract class BlockMixin {
    @ModifyReturnValue(method = "isExceptionForConnection", at = @At("RETURN"))
    private static boolean isExceptionForConnection(boolean original, @Local(argsOnly = true) BlockState state) {
        return original || state.getBlock() instanceof CarvedPumpkinBlock;
    }
}
