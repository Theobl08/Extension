package net.theobl.extension.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;

public class ModBlockEntityType {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Extension.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PotionCauldronBlockEntity>> POTION_CAULDRON =
            BLOCK_ENTITY_TYPES.register(
                    "potion_cauldron",
                    () -> new BlockEntityType<>(PotionCauldronBlockEntity::new,
                            ModBlocks.POTION_CAULDRON.get()));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
}
