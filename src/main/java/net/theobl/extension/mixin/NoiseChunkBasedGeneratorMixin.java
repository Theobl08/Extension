package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(NoiseBasedChunkGenerator.class)
public class NoiseChunkBasedGeneratorMixin {
    @ModifyReturnValue(method = "createFluidPicker", at = @At("RETURN"))
    private static Aquifer.FluidPicker fixMC237017(Aquifer.FluidPicker original, @Local(argsOnly = true) NoiseGeneratorSettings settings){
        return (x,y,z) -> new Aquifer.FluidStatus(settings.seaLevel(), settings.defaultFluid());
    }
}
