package net.theobl.extension.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Lightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Lightmap.class)
public abstract class LightTextureMixin {
    @ModifyArg(method = "getBrightness", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;lerp(FFF)F"), index = 0)
    private static float modifyBrightness(float delta) {
        double gamma = Minecraft.getInstance().options.gamma().get();
        if (gamma < 0) {
            return (float) (1.0F + gamma);
        }
        return delta;
    }
}
