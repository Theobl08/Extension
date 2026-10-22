package net.theobl.extension.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
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
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.gamerules.ModGameRules;
import net.theobl.extension.particles.ModParticleTypes;
import net.theobl.extension.util.ModUtil;

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
                    .addDripstoneDripping(PointedDripstoneBlock.LAVA_TRANSFER_PROBABILITY_PER_RANDOM_TICK, ModParticleTypes.DRIPPING_DRIPSTONE_BEETROOT_SOUP.get(), ModBlocks.BEETROOT_SOUP_CAULDRON.get(), SoundEvents.POINTED_DRIPSTONE_DRIP_LAVA_INTO_CAULDRON)
            ) {
                @Override
                public boolean move(LivingEntity entity, Vec3 movementVector, double gravity) {
                    return ModUtil.travelInWater(entity, movementVector, gravity);
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

    public static final DeferredHolder<FluidType, FluidType> MUSHROOM_STEW_TYPE = FLUID_TYPES.register(
            "mushroom_stew",
            () -> new FluidType(FluidType.Properties.create()
                    .descriptionId("item.minecraft.mushroom_stew")
                    .motionScale(Entity.WATER_FLOW_SCALE / 2.0D)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .viscosity(2000)
                    .addDripstoneDripping(PointedDripstoneBlock.LAVA_TRANSFER_PROBABILITY_PER_RANDOM_TICK, ModParticleTypes.DRIPPING_DRIPSTONE_MUSHROOM_STEW.get(), ModBlocks.MUSHROOM_STEW_CAULDRON.get(), SoundEvents.POINTED_DRIPSTONE_DRIP_LAVA_INTO_CAULDRON)
            ) {
                @Override
                public boolean move(LivingEntity entity, Vec3 movementVector, double gravity) {
                    return ModUtil.travelInWater(entity, movementVector, gravity);
                }

                @Override
                public boolean canConvertToSource(FluidState state, LevelReader reader, BlockPos pos) {
                    if (reader instanceof ServerLevel level) {
                        return level.getGameRules().get(ModGameRules.MUSHROOM_STEW_SOURCE_CONVERSION.get());
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
