package net.theobl.extension.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.theobl.extension.Config;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseBasedChunkGenerator.class)
public class NoiseBasedChunkGeneratorMixin {
    @Shadow
    @Final
    private Holder<NoiseGeneratorSettings> settings;

    @Inject(method = "createFluidPicker", at = @At(value = "RETURN"), cancellable = true)
    private static void extension$mc237017fix(NoiseGeneratorSettings pSettings, CallbackInfoReturnable<Aquifer.FluidPicker> cir) {
        if(Config.mc237017fix)
            cir.setReturnValue((x, y, z) -> new Aquifer.FluidStatus(pSettings.seaLevel(), pSettings.defaultFluid()));
    }
}
