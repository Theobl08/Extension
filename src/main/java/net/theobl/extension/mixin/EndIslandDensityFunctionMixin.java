package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(targets = "net.minecraft.world.level.levelgen.DensityFunctions$EndIslandDensityFunction")
public class EndIslandDensityFunctionMixin {
    @ModifyExpressionValue(method = "getHeightValue", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;sqrt(F)F"),
            slice = @Slice(to = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(FFF)F")))
    private static float fixMC159283(float original, @Local(argsOnly = true, ordinal = 0) int i, @Local(argsOnly = true, ordinal = 1) int j) {
        return Mth.sqrt((long) i * (long) i + (long) j * (long) j);
    }
}
