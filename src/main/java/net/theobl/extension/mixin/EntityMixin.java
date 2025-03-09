package net.theobl.extension.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow private int remainingFireTicks;

    @Inject(method = "setRemainingFireTicks", at = @At("TAIL"))
    private void extension$noPlayerOnFireInCreative(int pTicks, CallbackInfo ci){
        if(((Entity) (Object) this) instanceof Player) {
            if(((Player) (Object) this).isCreative())
                this.remainingFireTicks = 0;
            else if (!((Player) (Object) this).level().getGameRules().getBoolean(GameRules.RULE_FIRE_DAMAGE)) {
                this.remainingFireTicks = 0;
            }
        }
    }
}
