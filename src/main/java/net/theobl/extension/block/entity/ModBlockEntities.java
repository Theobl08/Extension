package net.theobl.extension.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Extension.MODID);

    public static final Supplier<BlockEntityType<RedstoneCampfireBlockEntity>> REDSTONE_CAMPFIRE =
            BLOCK_ENTITIES.register("redstone_campfire",
                    () -> BlockEntityType.Builder.of(RedstoneCampfireBlockEntity::new, ModBlocks.REDSTONE_CAMPFIRE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
