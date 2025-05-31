package net.theobl.extension.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.block.entity.ModHangingSignBlockEntity;
import net.theobl.extension.block.entity.ModSignBlockEntity;

public class ModBlockEntityType {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Extension.MOD_ID);

    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register("mod_sign",
                    () -> BlockEntityType.Builder.of(ModSignBlockEntity::new,
                            //ModBlocks.WHITE_SIGN.get(), ModBlocks.WHITE_WALL_SIGN.get()).build(null));
                            ModBlocks.COLORED_SIGNS.get(0).get(), ModBlocks.COLORED_WALL_SIGNS.get(0).get(),
                            ModBlocks.COLORED_SIGNS.get(1).get(), ModBlocks.COLORED_WALL_SIGNS.get(1).get(),
                            ModBlocks.COLORED_SIGNS.get(2).get(), ModBlocks.COLORED_WALL_SIGNS.get(2).get(),
                            ModBlocks.COLORED_SIGNS.get(3).get(), ModBlocks.COLORED_WALL_SIGNS.get(3).get(),
                            ModBlocks.COLORED_SIGNS.get(4).get(), ModBlocks.COLORED_WALL_SIGNS.get(4).get(),
                            ModBlocks.COLORED_SIGNS.get(5).get(), ModBlocks.COLORED_WALL_SIGNS.get(5).get(),
                            ModBlocks.COLORED_SIGNS.get(6).get(), ModBlocks.COLORED_WALL_SIGNS.get(6).get(),
                            ModBlocks.COLORED_SIGNS.get(7).get(), ModBlocks.COLORED_WALL_SIGNS.get(7).get(),
                            ModBlocks.COLORED_SIGNS.get(8).get(), ModBlocks.COLORED_WALL_SIGNS.get(8).get(),
                            ModBlocks.COLORED_SIGNS.get(9).get(), ModBlocks.COLORED_WALL_SIGNS.get(9).get(),
                            ModBlocks.COLORED_SIGNS.get(10).get(), ModBlocks.COLORED_WALL_SIGNS.get(10).get(),
                            ModBlocks.COLORED_SIGNS.get(11).get(), ModBlocks.COLORED_WALL_SIGNS.get(11).get(),
                            ModBlocks.COLORED_SIGNS.get(12).get(), ModBlocks.COLORED_WALL_SIGNS.get(12).get(),
                            ModBlocks.COLORED_SIGNS.get(13).get(), ModBlocks.COLORED_WALL_SIGNS.get(13).get(),
                            ModBlocks.COLORED_SIGNS.get(14).get(), ModBlocks.COLORED_WALL_SIGNS.get(14).get(),
                            ModBlocks.COLORED_SIGNS.get(15).get(), ModBlocks.COLORED_WALL_SIGNS.get(15).get()).build(null));

    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register("mod_hanging_sign",
                    () -> BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
//                            ModBlocks.WHITE_HANGING_SIGN.get(), ModBlocks.WHITE_WALL_HANGING_SIGN.get()).build(null));
                            ModBlocks.COLORED_HANGING_SIGNS.get(0).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(0).get(),
                            ModBlocks.COLORED_HANGING_SIGNS.get(1).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(1).get(),
                            ModBlocks.COLORED_HANGING_SIGNS.get(2).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(2).get(),
                            ModBlocks.COLORED_HANGING_SIGNS.get(3).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(3).get(),
                            ModBlocks.COLORED_HANGING_SIGNS.get(4).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(4).get(),
                            ModBlocks.COLORED_HANGING_SIGNS.get(5).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(5).get(),
                            ModBlocks.COLORED_HANGING_SIGNS.get(6).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(6).get(),
                            ModBlocks.COLORED_HANGING_SIGNS.get(7).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(7).get(),
                            ModBlocks.COLORED_HANGING_SIGNS.get(8).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(8).get(),
                            ModBlocks.COLORED_HANGING_SIGNS.get(9).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(9).get(),
                            ModBlocks.COLORED_HANGING_SIGNS.get(10).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(10).get(),
                            ModBlocks.COLORED_HANGING_SIGNS.get(11).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(11).get(),
                            ModBlocks.COLORED_HANGING_SIGNS.get(12).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(12).get(),
                            ModBlocks.COLORED_HANGING_SIGNS.get(13).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(13).get(),
                            ModBlocks.COLORED_HANGING_SIGNS.get(14).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(14).get(),
                            ModBlocks.COLORED_HANGING_SIGNS.get(15).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(15).get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
