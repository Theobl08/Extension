package net.theobl.extension.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.theobl.extension.Config;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixin {
    @Final
    @Shadow
    private Minecraft minecraft;

    @Inject(method = "renderScreenEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ScreenEffectRenderer;renderFire(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;)V"), cancellable = true)
    private void noFireOverlayWhenNotNeeded(boolean sleeping, float partialTick, CallbackInfo ci) {
        Player player = minecraft.player;
        if(player != null) {
            MobEffectInstance mobEffectInstance = player.getEffect(MobEffects.FIRE_RESISTANCE);
            if(player.isCreative()) {
                ci.cancel();
            } else if (Config.noFireOverlay &&
                    player.hasEffect(MobEffects.FIRE_RESISTANCE) &&
                    mobEffectInstance != null && !mobEffectInstance.endsWithin(400) &&
                    !player.isHolding(Items.MILK_BUCKET)) {
                ci.cancel();
            }
        }
    }
}
