package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightmapRenderStateExtractor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LightmapRenderStateExtractor.class)
public class LightmapRenderStateExtractorMixin {
    @Shadow
    @Final
    private Minecraft minecraft;


    @Definition(id = "max", method = "Ljava/lang/Math;max(FF)F")
    @Expression("max(?, ?)")
    @ModifyArg(method = "extract", at = @At("MIXINEXTRAS:EXPRESSION"), index = 0)
    public float extract(float original) {
        float brightnessOption = this.minecraft.options.gamma().get().floatValue();
        if(brightnessOption < 0.0F) {
            return brightnessOption;
        }
        else {
            return original;
        }
    }
}
