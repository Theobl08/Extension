package net.theobl.extension.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.theobl.extension.Extension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixin {
    @Inject(method = "renderFire", at = @At("HEAD"), cancellable = true)
    private static void extension$noFireOverlayInCreative(Minecraft pMinecraft, PoseStack pPoseStack, CallbackInfo ci) {
        Player player = pMinecraft.player;
        if(player != null) {
            MobEffectInstance mobEffectInstance = player.getEffect(MobEffects.FIRE_RESISTANCE);
            if(player.isCreative()) {
                ci.cancel();
            } else if (player.hasEffect(MobEffects.FIRE_RESISTANCE) &&
                    !mobEffectInstance.endsWithin(400) &&
                    !player.isHolding(Items.MILK_BUCKET)) {
                ci.cancel();
            }
        }
    }
}
