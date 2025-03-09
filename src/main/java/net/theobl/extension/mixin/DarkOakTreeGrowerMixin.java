package net.theobl.extension.mixin;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.DarkOakTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.theobl.extension.Config;
import net.theobl.extension.worldgen.ModConfiguredFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DarkOakTreeGrower.class)
public class DarkOakTreeGrowerMixin {
    @Inject(at = @At("RETURN"),
            method = "getConfiguredFeature(Lnet/minecraft/util/RandomSource;Z)Lnet/minecraft/resources/ResourceKey;",
            cancellable = true)
    private void extension_GetConfiguredFeature(RandomSource p_222924_, boolean p_222925_, CallbackInfoReturnable<ResourceKey<ConfiguredFeature<?, ?>>> cir) {
        if(Config.darkOak) cir.setReturnValue(ModConfiguredFeatures.SMALL_DARK_OAK_KEY);
    }
}
