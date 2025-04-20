package net.theobl.extension.mixin;

import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseBasedChunkGenerator.class)
public class NoiseChunkBasedGeneratorMixin {
    @Inject(method = "createFluidPicker", at = @At("RETURN"), cancellable = true)
    private static void fixMC237017(NoiseGeneratorSettings settings, CallbackInfoReturnable<Aquifer.FluidPicker> cir){
        cir.setReturnValue((x,y,z)-> new Aquifer.FluidStatus(settings.seaLevel(), settings.defaultFluid()));
    }
}
