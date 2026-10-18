package net.theobl.extension.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.theobl.extension.Extension;
import net.theobl.extension.gamerules.ModGameRules;

public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, Extension.MODID);

    public static final DeferredHolder<FluidType, FluidType> BEETROOT_SOUP_TYPE = FLUID_TYPES.register(
            "beetroot_soup",
            () -> new FluidType(FluidType.Properties.create()
                    .descriptionId("item.minecraft.beetroot_soup")
                    .motionScale(0.007)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .viscosity(2000)
            ) {
                @Override
                public boolean move(LivingEntity entity, Vec3 movementVector, double gravity) {
                    boolean isFalling = entity.getDeltaMovement().y <= 0.0;
                    double oldY = entity.getY();

                    float slowDown = entity.isSprinting() ? 0.9F : /*entity.getWaterSlowDown()*/ 0.8F;
                    float speed = 0.02F;
                    float waterWalker = (float)entity.getAttributeValue(Attributes.WATER_MOVEMENT_EFFICIENCY);
                    if (!entity.onGround()) {
                        waterWalker *= 0.5F;
                    }

                    if (waterWalker > 0.0F) {
                        slowDown += (0.54600006F - slowDown) * waterWalker;
                        speed += (entity.getSpeed() - speed) * waterWalker;
                    }

                    if (entity.hasEffect(MobEffects.DOLPHINS_GRACE)) {
                        slowDown = 0.96F;
                    }

                    speed *= (float)entity.getAttributeValue(net.neoforged.neoforge.common.NeoForgeMod.SWIM_SPEED);
                    entity.moveRelative(speed, movementVector);
                    entity.move(MoverType.SELF, entity.getDeltaMovement());
                    Vec3 movement = entity.getDeltaMovement();
                    if (entity.horizontalCollision && entity.onClimbable()) {
                        movement = new Vec3(movement.x, 0.2, movement.z);
                    }

                    movement = movement.multiply(slowDown, 0.8F, slowDown);
                    entity.setDeltaMovement(entity.getFluidFallingAdjustedMovement(gravity, isFalling, movement));

//                    entity.jumpOutOfFluid(oldY);
                    movement = entity.getDeltaMovement();
                    if (entity.horizontalCollision && entity.isFree(movement.x, movement.y + 0.6F - entity.getY() + oldY, movement.z)) {
                        entity.setDeltaMovement(movement.x, 0.3F, movement.z);
                    }
                    return true;
                }

                @Override
                public boolean canConvertToSource(FluidState state, LevelReader reader, BlockPos pos) {
                    if (reader instanceof ServerLevel level) {
                        return level.getGameRules().get(ModGameRules.BEETROOT_SOUP_SOURCE_CONVERSION.get());
                    }
                    //Best guess fallback to default (false)
                    return super.canConvertToSource(state, reader, pos);
                }
            }
    );

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
