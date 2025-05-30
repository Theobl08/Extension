package net.theobl.extension.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.AbstractBoat;
import net.minecraft.world.level.Level;
import net.theobl.extension.Config;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractBoat.class)
public abstract class AbstractBoatMixin extends Entity {
    public AbstractBoatMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public float maxUpStep() {
        if(Config.boatStepUp)
            return 1.1F;
        else
            return 0.0F;
    }
}
