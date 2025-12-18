package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.level.Level;
import net.theobl.extension.Config;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractBoat.class)
public abstract class AbstractBoatMixin extends EntityMixin {

    @Override
    protected float boatMaxUpStep(Operation<Float> original) {
        if(Config.boatStepUp)
            return 1.1F;
        else
            return super.boatMaxUpStep(original);
    }
}
