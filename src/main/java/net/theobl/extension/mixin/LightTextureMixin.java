package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LightTexture.class)
public class LightTextureMixin {
    @WrapOperation(at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F", ordinal = 2), method = "updateLightTexture")
    public float updateLightTexture(float a, float b, Operation<Float> original) {
        double gamma = Minecraft.getInstance().options.gamma().get();
        if(gamma < 0)
            return (float) Math.max(gamma, b);
        else
            return original.call(a, b);
    }
}
