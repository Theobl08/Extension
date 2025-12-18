package net.theobl.extension.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LightTexture.class)
public abstract class LightTextureMixin {
    @ModifyArg(method = "getBrightness(FI)F", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;lerp(FFF)F"), index = 0)
    private static float modifyBrightness(float delta) {
        double gamma = Minecraft.getInstance().options.gamma().get();
        if (gamma < 0) {
            return (float) (1.0F + gamma);
        }
        return delta;
    }
}
