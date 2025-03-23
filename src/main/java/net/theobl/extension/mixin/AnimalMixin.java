package net.theobl.extension.mixin;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Animal.class)
public class AnimalMixin {
    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    private void extension$addParticle(Level instance, ParticleOptions pParticleData, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
        if(!instance.isClientSide) {
            ((ServerLevel) instance).sendParticles(pParticleData, pX, pY, pZ, 1, pXSpeed, pYSpeed, pZSpeed, 0.0);
        }
        else {
            instance.addParticle(pParticleData, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
        }
    }
}
