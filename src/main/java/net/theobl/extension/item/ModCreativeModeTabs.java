package net.theobl.extension.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Extension.MOD_ID);

    public static final RegistryObject<CreativeModeTab> EXTENSION_TAB = CREATIVE_MODE_TABS.register("extension_misc",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.AMETHYST_ORE.get()))
                    .title(Component.translatable("itemGroup.extension_misc"))
                    .displayItems((pParameters, pOutput) -> {
//                        pOutput.accept(ModItems.SPAWNER_MINECART.get());
//                        pOutput.accept(ModBlocks.AMETHYST_ORE.get());
//                        pOutput.accept(ModBlocks.DEEPSLATE_AMETHYST_ORE.get());
//                        ModBlocks.BLOCKS.getEntries().forEach(blockRegistryObject -> {
//                            pOutput.accept(blockRegistryObject.get());
//                        });
////                        pOutput.accept(ModItems.WHITE_BOAT.get());
////                        pOutput.accept(ModItems.WHITE_CHEST_BOAT.get());
//                        for(RegistryObject<Item> boat : ModItems.COLORED_BOATS) {
//                            pOutput.accept(boat.get());
//                        }
//                        for(RegistryObject<Item> boat : ModItems.COLORED_CHEST_BOATS) {
//                            pOutput.accept(boat.get());
//                        }
                        ModItems.ITEMS.getEntries().forEach(itemRegistryObject -> {
                            pOutput.accept(itemRegistryObject.get());
                        });
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> WOOD_TAB = CREATIVE_MODE_TABS.register("wood_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.COLORED_LOGS.get(0).get()))
                    .title(Component.translatable("itemGroup.wood_blocks"))
                    .displayItems((pParameters, pOutput) -> {
                        for (DyeColor color : ModBlocks.COLORS) {
                            int index = ModBlocks.COLORS.indexOf(color);
                            pOutput.accept(ModBlocks.COLORED_LEAVES.get(index).get());
                            pOutput.accept(ModBlocks.COLORED_LOGS.get(index).get());
                            pOutput.accept(ModBlocks.COLORED_WOODS.get(index).get());
                            pOutput.accept(ModBlocks.COLORED_STRIPPED_LOGS.get(index).get());
                            pOutput.accept(ModBlocks.COLORED_STRIPPED_WOODS.get(index).get());
                            pOutput.accept(ModBlocks.COLORED_PLANKS.get(index).get());
                            pOutput.accept(ModBlocks.COLORED_STAIRS.get(index).get());
                            pOutput.accept(ModBlocks.COLORED_SLABS.get(index).get());
                            pOutput.accept(ModBlocks.COLORED_FENCES.get(index).get());
                            pOutput.accept(ModBlocks.COLORED_FENCE_GATES.get(index).get());
                            pOutput.accept(ModBlocks.COLORED_DOORS.get(index).get());
                            pOutput.accept(ModBlocks.COLORED_TRAPDOORS.get(index).get());
                            pOutput.accept(ModBlocks.COLORED_PRESSURE_PLATES.get(index).get());
                            pOutput.accept(ModBlocks.COLORED_BUTTONS.get(index).get());
                            pOutput.accept(ModBlocks.COLORED_SIGNS.get(index).get());
                            pOutput.accept(ModBlocks.COLORED_HANGING_SIGNS.get(index).get());
                            pOutput.accept(ModItems.COLORED_BOATS.get(index).get());
                            pOutput.accept(ModItems.COLORED_CHEST_BOATS.get(index).get());
                        }
//                        pOutput.accept(ModBlocks.WHITE_LOG.get());
//                        pOutput.accept(ModBlocks.WHITE_WOOD.get());
//                        pOutput.accept(ModBlocks.WHITE_DOOR.get());
//                        pOutput.accept(ModBlocks.WHITE_TRAPDOOR.get());

//                        pOutput.accept(ModBlocks.LIGHT_GRAY_LOG.get());
//                        pOutput.accept(ModBlocks.LIGHT_GRAY_WOOD.get());
//                        pOutput.accept(ModBlocks.LIGHT_GRAY_DOOR.get());
//                        pOutput.accept(ModBlocks.LIGHT_GRAY_TRAPDOOR.get());

//                        pOutput.accept(ModBlocks.GRAY_LOG.get());
//                        pOutput.accept(ModBlocks.GRAY_WOOD.get());
//                        pOutput.accept(ModBlocks.GRAY_DOOR.get());
//                        pOutput.accept(ModBlocks.GRAY_TRAPDOOR.get());

//                        pOutput.accept(ModBlocks.BLACK_LOG.get());
//                        pOutput.accept(ModBlocks.BLACK_WOOD.get());
//                        pOutput.accept(ModBlocks.BLACK_DOOR.get());
//                        pOutput.accept(ModBlocks.BLACK_TRAPDOOR.get());


//                        ModBlocks.BLOCKS.getEntries().forEach(blockRegistryObject -> {
//                            if(blockRegistryObject.get().toString().contains("white") || blockRegistryObject.get().toString().contains("gray") || blockRegistryObject.get().toString().contains("black")){
//                                if(!(blockRegistryObject.get().toString().contains("alpha") ||
//                                        blockRegistryObject.get().toString().contains("quilted") ||
//                                        blockRegistryObject.get().toString().contains("dark") ||
//                                        blockRegistryObject.get().toString().contains("antiblock") ||
//                                        blockRegistryObject.get().toString().contains("glazed"))) {
//                                    pOutput.accept(blockRegistryObject.get());
//                                }
//                            }
//                        });
//
//                        pOutput.accept(Items.OAK_LOG);
//                        pOutput.accept(Items.OAK_WOOD);
//                        pOutput.accept(Items.STRIPPED_OAK_LOG);
//                        pOutput.accept(Items.STRIPPED_OAK_WOOD);
//                        pOutput.accept(Items.OAK_PLANKS);
//                        pOutput.accept(ModBlocks.OAK_MOSAIC.get());
//                        pOutput.accept(Items.BOOKSHELF);
//                        pOutput.accept(Items.CRAFTING_TABLE);
//                        pOutput.accept(Items.OAK_STAIRS);
//                        pOutput.accept(Items.OAK_SLAB);
//                        pOutput.accept(Items.PETRIFIED_OAK_SLAB);
//                        pOutput.accept(Items.OAK_FENCE);
//                        pOutput.accept(Items.OAK_FENCE_GATE);
//                        pOutput.accept(Items.OAK_DOOR);
//                        pOutput.accept(Items.OAK_TRAPDOOR);
//                        pOutput.accept(Items.OAK_PRESSURE_PLATE);
//                        pOutput.accept(Items.OAK_BUTTON);
//                        pOutput.accept(Items.LADDER);
//                        pOutput.accept(Items.OAK_SIGN);
//                        pOutput.accept(Items.OAK_HANGING_SIGN);
//                        pOutput.accept(Items.OAK_BOAT);
//                        pOutput.accept(Items.OAK_CHEST_BOAT);
//
//                        pOutput.accept(Items.SPRUCE_LOG);
//                        pOutput.accept(Items.SPRUCE_WOOD);
//                        pOutput.accept(Items.STRIPPED_SPRUCE_LOG);
//                        pOutput.accept(Items.STRIPPED_SPRUCE_WOOD);
//                        pOutput.accept(Items.SPRUCE_PLANKS);
//                        pOutput.accept(ModBlocks.SPRUCE_MOSAIC.get());
//                        pOutput.accept(ModBlocks.SPRUCE_BOOKSHELF.get());
//                        pOutput.accept(ModBlocks.SPRUCE_CRAFTING_TABLE.get());
//                        pOutput.accept(Items.SPRUCE_STAIRS);
//                        pOutput.accept(Items.SPRUCE_SLAB);
////                        pOutput.accept(Items.PETRIFIED_OAK_SLAB);
//                        pOutput.accept(Items.SPRUCE_FENCE);
//                        pOutput.accept(Items.SPRUCE_FENCE_GATE);
//                        pOutput.accept(Items.SPRUCE_DOOR);
//                        pOutput.accept(Items.SPRUCE_TRAPDOOR);
//                        pOutput.accept(Items.SPRUCE_PRESSURE_PLATE);
//                        pOutput.accept(Items.SPRUCE_BUTTON);
////                        pOutput.accept(Items.LADDER);
//                        pOutput.accept(Items.SPRUCE_SIGN);
//                        pOutput.accept(Items.SPRUCE_HANGING_SIGN);
//                        pOutput.accept(Items.SPRUCE_BOAT);
//                        pOutput.accept(Items.SPRUCE_CHEST_BOAT);
//
//                        pOutput.accept(Items.BIRCH_LOG);
//                        pOutput.accept(Items.BIRCH_WOOD);
//                        pOutput.accept(Items.STRIPPED_BIRCH_LOG);
//                        pOutput.accept(Items.STRIPPED_BIRCH_WOOD);
//                        pOutput.accept(Items.BIRCH_PLANKS);
//                        pOutput.accept(ModBlocks.BIRCH_MOSAIC.get());
//                        pOutput.accept(ModBlocks.BIRCH_BOOKSHELF.get());
//                        pOutput.accept(ModBlocks.BIRCH_CRAFTING_TABLE.get());
//                        pOutput.accept(Items.BIRCH_STAIRS);
//                        pOutput.accept(Items.BIRCH_SLAB);
////                        pOutput.accept(Items.PETRIFIED_OAK_SLAB);
//                        pOutput.accept(Items.BIRCH_FENCE);
//                        pOutput.accept(Items.BIRCH_FENCE_GATE);
//                        pOutput.accept(Items.BIRCH_DOOR);
//                        pOutput.accept(Items.BIRCH_TRAPDOOR);
//                        pOutput.accept(Items.BIRCH_PRESSURE_PLATE);
//                        pOutput.accept(Items.BIRCH_BUTTON);
////                        pOutput.accept(Items.LADDER);
//                        pOutput.accept(Items.BIRCH_SIGN);
//                        pOutput.accept(Items.BIRCH_HANGING_SIGN);
//                        pOutput.accept(Items.BIRCH_BOAT);
//                        pOutput.accept(Items.BIRCH_CHEST_BOAT);
//
//                        pOutput.accept(Items.JUNGLE_LOG);
//                        pOutput.accept(Items.JUNGLE_WOOD);
//                        pOutput.accept(Items.STRIPPED_JUNGLE_LOG);
//                        pOutput.accept(Items.STRIPPED_JUNGLE_WOOD);
//                        pOutput.accept(Items.JUNGLE_PLANKS);
//                        pOutput.accept(ModBlocks.JUNGLE_MOSAIC.get());
//                        pOutput.accept(ModBlocks.JUNGLE_BOOKSHELF.get());
//                        pOutput.accept(ModBlocks.JUNGLE_CRAFTING_TABLE.get());
//                        pOutput.accept(Items.JUNGLE_STAIRS);
//                        pOutput.accept(Items.JUNGLE_SLAB);
////                        pOutput.accept(Items.PETRIFIED_OAK_SLAB);
//                        pOutput.accept(Items.JUNGLE_FENCE);
//                        pOutput.accept(Items.JUNGLE_FENCE_GATE);
//                        pOutput.accept(Items.JUNGLE_DOOR);
//                        pOutput.accept(Items.JUNGLE_TRAPDOOR);
//                        pOutput.accept(Items.JUNGLE_PRESSURE_PLATE);
//                        pOutput.accept(Items.JUNGLE_BUTTON);
////                        pOutput.accept(Items.LADDER);
//                        pOutput.accept(Items.JUNGLE_SIGN);
//                        pOutput.accept(Items.JUNGLE_HANGING_SIGN);
//                        pOutput.accept(Items.JUNGLE_BOAT);
//                        pOutput.accept(Items.JUNGLE_CHEST_BOAT);
//
//                        pOutput.accept(Items.ACACIA_LOG);
//                        pOutput.accept(Items.ACACIA_WOOD);
//                        pOutput.accept(Items.STRIPPED_ACACIA_LOG);
//                        pOutput.accept(Items.STRIPPED_ACACIA_WOOD);
//                        pOutput.accept(Items.ACACIA_PLANKS);
//                        pOutput.accept(ModBlocks.ACACIA_MOSAIC.get());
//                        pOutput.accept(ModBlocks.ACACIA_BOOKSHELF.get());
//                        pOutput.accept(ModBlocks.ACACIA_CRAFTING_TABLE.get());
//                        pOutput.accept(Items.ACACIA_STAIRS);
//                        pOutput.accept(Items.ACACIA_SLAB);
////                        pOutput.accept(Items.PETRIFIED_OAK_SLAB);
//                        pOutput.accept(Items.ACACIA_FENCE);
//                        pOutput.accept(Items.ACACIA_FENCE_GATE);
//                        pOutput.accept(Items.ACACIA_DOOR);
//                        pOutput.accept(Items.ACACIA_TRAPDOOR);
//                        pOutput.accept(Items.ACACIA_PRESSURE_PLATE);
//                        pOutput.accept(Items.ACACIA_BUTTON);
////                        pOutput.accept(Items.LADDER);
//                        pOutput.accept(Items.ACACIA_SIGN);
//                        pOutput.accept(Items.ACACIA_HANGING_SIGN);
//                        pOutput.accept(Items.ACACIA_BOAT);
//                        pOutput.accept(Items.ACACIA_CHEST_BOAT);
//
//                        pOutput.accept(Items.DARK_OAK_LOG);
//                        pOutput.accept(Items.DARK_OAK_WOOD);
//                        pOutput.accept(Items.STRIPPED_DARK_OAK_LOG);
//                        pOutput.accept(Items.STRIPPED_DARK_OAK_WOOD);
//                        pOutput.accept(Items.DARK_OAK_PLANKS);
//                        pOutput.accept(ModBlocks.DARK_OAK_MOSAIC.get());
//                        pOutput.accept(ModBlocks.DARK_OAK_BOOKSHELF.get());
//                        pOutput.accept(ModBlocks.DARK_OAK_CRAFTING_TABLE.get());
//                        pOutput.accept(Items.DARK_OAK_STAIRS);
//                        pOutput.accept(Items.DARK_OAK_SLAB);
////                        pOutput.accept(Items.PETRIFIED_OAK_SLAB);
//                        pOutput.accept(Items.DARK_OAK_FENCE);
//                        pOutput.accept(Items.DARK_OAK_FENCE_GATE);
//                        pOutput.accept(Items.DARK_OAK_DOOR);
//                        pOutput.accept(Items.DARK_OAK_TRAPDOOR);
//                        pOutput.accept(Items.DARK_OAK_PRESSURE_PLATE);
//                        pOutput.accept(Items.DARK_OAK_BUTTON);
////                        pOutput.accept(Items.LADDER);
//                        pOutput.accept(Items.DARK_OAK_SIGN);
//                        pOutput.accept(Items.DARK_OAK_HANGING_SIGN);
//                        pOutput.accept(Items.DARK_OAK_BOAT);
//                        pOutput.accept(Items.DARK_OAK_CHEST_BOAT);
//
//                        pOutput.accept(Items.MANGROVE_LOG);
//                        pOutput.accept(Items.MANGROVE_WOOD);
//                        pOutput.accept(Items.STRIPPED_MANGROVE_LOG);
//                        pOutput.accept(Items.STRIPPED_MANGROVE_WOOD);
//                        pOutput.accept(Items.MANGROVE_PLANKS);
//                        pOutput.accept(ModBlocks.MANGROVE_MOSAIC.get());
//                        pOutput.accept(ModBlocks.MANGROVE_BOOKSHELF.get());
//                        pOutput.accept(ModBlocks.MANGROVE_CRAFTING_TABLE.get());
//                        pOutput.accept(Items.MANGROVE_STAIRS);
//                        pOutput.accept(Items.MANGROVE_SLAB);
////                        pOutput.accept(Items.PETRIFIED_OAK_SLAB);
//                        pOutput.accept(Items.MANGROVE_FENCE);
//                        pOutput.accept(Items.MANGROVE_FENCE_GATE);
//                        pOutput.accept(Items.MANGROVE_DOOR);
//                        pOutput.accept(Items.MANGROVE_TRAPDOOR);
//                        pOutput.accept(Items.MANGROVE_PRESSURE_PLATE);
//                        pOutput.accept(Items.MANGROVE_BUTTON);
////                        pOutput.accept(Items.LADDER);
//                        pOutput.accept(Items.MANGROVE_SIGN);
//                        pOutput.accept(Items.MANGROVE_HANGING_SIGN);
//                        pOutput.accept(Items.MANGROVE_BOAT);
//                        pOutput.accept(Items.MANGROVE_CHEST_BOAT);
//
//                        pOutput.accept(Items.CHERRY_LOG);
//                        pOutput.accept(Items.CHERRY_WOOD);
//                        pOutput.accept(Items.STRIPPED_CHERRY_LOG);
//                        pOutput.accept(Items.STRIPPED_CHERRY_WOOD);
//                        pOutput.accept(Items.CHERRY_PLANKS);
//                        pOutput.accept(ModBlocks.CHERRY_MOSAIC.get());
//                        pOutput.accept(ModBlocks.CHERRY_BOOKSHELF.get());
//                        pOutput.accept(ModBlocks.CHERRY_CRAFTING_TABLE.get());
//                        pOutput.accept(Items.CHERRY_STAIRS);
//                        pOutput.accept(Items.CHERRY_SLAB);
////                        pOutput.accept(Items.PETRIFIED_OAK_SLAB);
//                        pOutput.accept(Items.CHERRY_FENCE);
//                        pOutput.accept(Items.CHERRY_FENCE_GATE);
//                        pOutput.accept(Items.CHERRY_DOOR);
//                        pOutput.accept(Items.CHERRY_TRAPDOOR);
//                        pOutput.accept(Items.CHERRY_PRESSURE_PLATE);
//                        pOutput.accept(Items.CHERRY_BUTTON);
////                        pOutput.accept(Items.LADDER);
//                        pOutput.accept(Items.CHERRY_SIGN);
//                        pOutput.accept(Items.CHERRY_HANGING_SIGN);
//                        pOutput.accept(Items.CHERRY_BOAT);
//                        pOutput.accept(Items.CHERRY_CHEST_BOAT);
//
//                        pOutput.accept(Blocks.BAMBOO_BLOCK);
//                        pOutput.accept(Blocks.STRIPPED_BAMBOO_BLOCK);
//                        pOutput.accept(Blocks.BAMBOO_PLANKS);
//                        pOutput.accept(Blocks.BAMBOO_MOSAIC);
//                        pOutput.accept(ModBlocks.BAMBOO_BOOKSHELF.get());
//                        pOutput.accept(ModBlocks.BAMBOO_CRAFTING_TABLE.get());
//                        pOutput.accept(Items.BAMBOO_STAIRS);
//                        pOutput.accept(Items.BAMBOO_SLAB);
////                        pOutput.accept(Items.PETRIFIED_OAK_SLAB);
//                        pOutput.accept(Items.BAMBOO_FENCE);
//                        pOutput.accept(Items.BAMBOO_FENCE_GATE);
//                        pOutput.accept(Items.BAMBOO_DOOR);
//                        pOutput.accept(Items.BAMBOO_TRAPDOOR);
//                        pOutput.accept(Items.BAMBOO_PRESSURE_PLATE);
//                        pOutput.accept(Items.BAMBOO_BUTTON);
////                        pOutput.accept(Items.LADDER);
//                        pOutput.accept(Items.BAMBOO_SIGN);
//                        pOutput.accept(Items.BAMBOO_HANGING_SIGN);
//                        pOutput.accept(Items.BAMBOO_RAFT);
//                        pOutput.accept(Items.BAMBOO_CHEST_RAFT);
//
//                        pOutput.accept(Items.CRIMSON_STEM);
//                        pOutput.accept(Items.CRIMSON_HYPHAE);
//                        pOutput.accept(Items.STRIPPED_CRIMSON_STEM);
//                        pOutput.accept(Items.STRIPPED_CRIMSON_HYPHAE);
//                        pOutput.accept(Blocks.CRIMSON_PLANKS);
//                        pOutput.accept(ModBlocks.CRIMSON_MOSAIC.get());
//                        pOutput.accept(ModBlocks.CRIMSON_BOOKSHELF.get());
//                        pOutput.accept(ModBlocks.CRIMSON_CRAFTING_TABLE.get());
//                        pOutput.accept(Items.CRIMSON_STAIRS);
//                        pOutput.accept(Items.CRIMSON_SLAB);
////                        pOutput.accept(Items.PETRIFIED_OAK_SLAB);
//                        pOutput.accept(Items.CRIMSON_FENCE);
//                        pOutput.accept(Items.CRIMSON_FENCE_GATE);
//                        pOutput.accept(Items.CRIMSON_DOOR);
//                        pOutput.accept(Items.CRIMSON_TRAPDOOR);
//                        pOutput.accept(Items.CRIMSON_PRESSURE_PLATE);
//                        pOutput.accept(Items.CRIMSON_BUTTON);
////                        pOutput.accept(Items.LADDER);
//                        pOutput.accept(Items.CRIMSON_SIGN);
//                        pOutput.accept(Items.CRIMSON_HANGING_SIGN);
//                        pOutput.accept(ModItems.CRIMSON_BOAT.get());
//                        pOutput.accept(ModItems.CRIMSON_CHEST_BOAT.get());
//
//                        pOutput.accept(Items.WARPED_STEM);
//                        pOutput.accept(Items.WARPED_HYPHAE);
//                        pOutput.accept(Items.STRIPPED_WARPED_STEM);
//                        pOutput.accept(Items.STRIPPED_WARPED_HYPHAE);
//                        pOutput.accept(Items.WARPED_PLANKS);
//                        pOutput.accept(ModBlocks.WARPED_MOSAIC.get());
//                        pOutput.accept(ModBlocks.WARPED_BOOKSHELF.get());
//                        pOutput.accept(ModBlocks.WARPED_CRAFTING_TABLE.get());
//                        pOutput.accept(Items.WARPED_STAIRS);
//                        pOutput.accept(Items.WARPED_SLAB);
////                        pOutput.accept(Items.PETRIFIED_OAK_SLAB);
//                        pOutput.accept(Items.WARPED_FENCE);
//                        pOutput.accept(Items.WARPED_FENCE_GATE);
//                        pOutput.accept(Items.WARPED_DOOR);
//                        pOutput.accept(Items.WARPED_TRAPDOOR);
//                        pOutput.accept(Items.WARPED_PRESSURE_PLATE);
//                        pOutput.accept(Items.WARPED_BUTTON);
////                        pOutput.accept(Items.LADDER);
//                        pOutput.accept(Items.WARPED_SIGN);
//                        pOutput.accept(Items.WARPED_HANGING_SIGN);
//                        pOutput.accept(ModItems.WARPED_BOAT.get());
//                        pOutput.accept(ModItems.WARPED_CHEST_BOAT.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> ALPHA_COLORED_TAB = CREATIVE_MODE_TABS.register("alpha_colored_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.VIBRANT_RED_WOOL.get()))
                    .withTabsBefore(WOOD_TAB.getId())
                    .withTabsAfter(EXTENSION_TAB.getId())
                    .title(Component.translatable("itemGroup.alpha_colored_blocks"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.VIBRANT_RED_WOOL.get());
                        pOutput.accept(ModBlocks.DULL_ORANGE_WOOL.get());
                        pOutput.accept(ModBlocks.BRIGHT_YELLOW_WOOL.get());
                        pOutput.accept(ModBlocks.CHARTREUSE_WOOL.get());
                        pOutput.accept(ModBlocks.VIBRANT_GREEN_WOOL.get());
                        pOutput.accept(ModBlocks.SPRING_GREEN_WOOL.get());
                        pOutput.accept(ModBlocks.BRIGHT_CYAN_WOOL.get());
                        pOutput.accept(ModBlocks.CAPRI_WOOL.get());
                        pOutput.accept(ModBlocks.ULTRAMARINE_WOOL.get());
                        pOutput.accept(ModBlocks.VIOLET_WOOL.get());
                        pOutput.accept(ModBlocks.VIBRANT_PURPLE_WOOL.get());
                        pOutput.accept(ModBlocks.BRIGHT_MAGENTA_WOOL.get());
                        pOutput.accept(ModBlocks.ROSE_WOOL.get());
                        pOutput.accept(ModBlocks.DARK_GRAY_WOOL.get());
                        pOutput.accept(ModBlocks.SILVER_WOOL.get());
                        pOutput.accept(ModBlocks.ALPHA_WHITE_WOOL.get());

                        pOutput.accept(ModBlocks.VIBRANT_RED_CARPET.get());
                        pOutput.accept(ModBlocks.DULL_ORANGE_CARPET.get());
                        pOutput.accept(ModBlocks.BRIGHT_YELLOW_CARPET.get());
                        pOutput.accept(ModBlocks.CHARTREUSE_CARPET.get());
                        pOutput.accept(ModBlocks.VIBRANT_GREEN_CARPET.get());
                        pOutput.accept(ModBlocks.SPRING_GREEN_CARPET.get());
                        pOutput.accept(ModBlocks.BRIGHT_CYAN_CARPET.get());
                        pOutput.accept(ModBlocks.CAPRI_CARPET.get());
                        pOutput.accept(ModBlocks.ULTRAMARINE_CARPET.get());
                        pOutput.accept(ModBlocks.VIOLET_CARPET.get());
                        pOutput.accept(ModBlocks.VIBRANT_PURPLE_CARPET.get());
                        pOutput.accept(ModBlocks.BRIGHT_MAGENTA_CARPET.get());
                        pOutput.accept(ModBlocks.ROSE_CARPET.get());
                        pOutput.accept(ModBlocks.DARK_GRAY_CARPET.get());
                        pOutput.accept(ModBlocks.SILVER_CARPET.get());
                        pOutput.accept(ModBlocks.ALPHA_WHITE_CARPET.get());

                        pOutput.accept(ModItems.VIBRANT_RED_DYE.get());
                        pOutput.accept(ModItems.DULL_ORANGE_DYE.get());
                        pOutput.accept(ModItems.BRIGHT_YELLOW_DYE.get());
                        pOutput.accept(ModItems.CHARTREUSE_DYE.get());
                        pOutput.accept(ModItems.VIBRANT_GREEN_DYE.get());
                        pOutput.accept(ModItems.SPRING_GREEN_DYE.get());
                        pOutput.accept(ModItems.BRIGHT_CYAN_DYE.get());
                        pOutput.accept(ModItems.CAPRI_DYE.get());
                        pOutput.accept(ModItems.ULTRAMARINE_DYE.get());
                        pOutput.accept(ModItems.VIOLET_DYE.get());
                        pOutput.accept(ModItems.VIBRANT_PURPLE_DYE.get());
                        pOutput.accept(ModItems.BRIGHT_MAGENTA_DYE.get());
                        pOutput.accept(ModItems.ROSE_DYE.get());
                        pOutput.accept(ModItems.DARK_GRAY_DYE.get());
                        pOutput.accept(ModItems.SILVER_DYE.get());
                        pOutput.accept(ModItems.ALPHA_WHITE_DYE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
