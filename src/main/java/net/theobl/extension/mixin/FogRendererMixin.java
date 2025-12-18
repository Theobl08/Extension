package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.renderer.fog.environment.LavaFogEnvironment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LavaFogEnvironment.class)
public abstract class FogRendererMixin {
    @WrapOperation(method = "setupFog", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isSpectator()Z"))
    private static boolean clearLavaAndPowderSnowViewInCreative(Entity instance, Operation<Boolean> original) {
        if(instance instanceof Player player) {
            return player.isCreative() || player.isSpectator();
        }
        return original.call(instance);
    }
}
