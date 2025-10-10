package net.theobl.extension.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.theobl.extension.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    @Shadow private int remainingFireTicks;

    @Inject(method = "setRemainingFireTicks", at = @At("TAIL"))
    private void noPlayerOnFireWhenNotNeeded(int remainingFireTicks, CallbackInfo ci) {
        if(((Entity) (Object) this) instanceof Player player) {
            if (player.isCreative())
                this.remainingFireTicks = 0;
            if(player.level() instanceof ServerLevel serverLevel) {
                if (!serverLevel.getGameRules().getBoolean(GameRules.RULE_FIRE_DAMAGE) && Config.noFireOverlay)
                    this.remainingFireTicks = 0;
            }
        }
    }
}
