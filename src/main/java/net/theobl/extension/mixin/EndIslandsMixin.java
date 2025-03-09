package net.theobl.extension.mixin;

import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(targets = "net.minecraft.world.level.levelgen.DensityFunctions$EndIslandDensityFunction")
public class EndIslandsMixin {
    @Redirect(method = "getHeightValue",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;sqrt(F)F"),
            slice = @Slice(to = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(FFF)F")))
    private static float getNoiseAt(float value, SimplexNoise simplexNoiseSampler, int i, int j) {
        // Explicitly cast i and j to longs.
        return Mth.sqrt((long) i * (long) i + (long) j * (long) j);
    }
}
