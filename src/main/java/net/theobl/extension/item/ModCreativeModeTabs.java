package net.theobl.extension.item;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Util;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.minecraft.world.item.CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;

public class ModCreativeModeTabs {
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "extension" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Extension.MODID);

    // Creates a creative tab with the id "extension:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXTENSION_TAB = CREATIVE_MODE_TABS.register(
            "extension",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.extension.main"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> ModItems.SPAWNER_MINECART.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        for (DeferredHolder<Block, ? extends Block> deferredItem : ModBlocks.BLOCKS.getEntries()) {
                            if(((DeferredBlock<?>) deferredItem).asItem() instanceof BlockItem item && item != Items.CAULDRON && deferredItem != ModBlocks.BLUE_NETHER_WART)
                                acceptWithoutThrowing(output, deferredItem.get());
                        }
                        output.accept(ModItems.POTATO_RAFT);
                        output.accept(ModItems.POTATO_CHEST_RAFT);
                        for (DeferredHolder<Item, ? extends Item> deferredItem : ModItems.ITEMS.getEntries()) {
                            if(!(deferredItem.get() instanceof BlockItem))
                                acceptWithoutThrowing(output, deferredItem.get());
                        }
                        output.accept(ModItems.BLUE_NETHER_WART); // Add the example item to the tab. For your own tabs, this method is preferred over the event
                    }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> VARIANT_VANILLA_BLOCKS = CREATIVE_MODE_TABS.register(
            "variant_vanilla_blocks",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.extension.variant_vanilla_blocks"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> ModBlocks.CRAFTING_TABLES.get(WoodType.WARPED).toStack())
                    .displayItems((parameters, output) -> {
                        List<WoodType> gameplayWoodTypeOrder = List.of(
                                        WoodType.OAK,
                                        WoodType.SPRUCE,
                                        WoodType.BIRCH,
                                        WoodType.JUNGLE,
                                        WoodType.ACACIA,
                                        WoodType.DARK_OAK,
                                        WoodType.MANGROVE,
                                        WoodType.CHERRY,
                                        WoodType.PALE_OAK,
                                        WoodType.POPLAR,
                                        WoodType.BAMBOO,
                                        WoodType.CRIMSON,
                                        WoodType.WARPED
                                );

                        Map<WoodType, Block> craftingTables = Util.copyAndPut(Util.mapValues(ModBlocks.CRAFTING_TABLES, Holder::value), WoodType.OAK, Blocks.CRAFTING_TABLE);
                        Map<WoodType, Block> cartogarphyTables = Util.copyAndPut(Util.mapValues(ModBlocks.CARTOGRAPHY_TABLES, Holder::value), WoodType.DARK_OAK, Blocks.CARTOGRAPHY_TABLE);
                        Map<WoodType, Block> fletchingTables = Util.copyAndPut(Util.mapValues(ModBlocks.FLETCHING_TABLES, Holder::value), WoodType.BIRCH, Blocks.FLETCHING_TABLE);
                        Map<WoodType, Block> beehives = Util.copyAndPut(Util.mapValues(ModBlocks.BEEHIVES, Holder::value), WoodType.OAK, Blocks.BEEHIVE);
                        Map<WoodType, Block> bookshelves = Util.copyAndPut(Util.mapValues(ModBlocks.BOOKSHELVES, Holder::value), WoodType.OAK, Blocks.BOOKSHELF);
                        Map<WoodType, Block> chisledBookshelves = Util.copyAndPut(Util.mapValues(ModBlocks.CHISELED_BOOKSHELVES, Holder::value), WoodType.OAK, Blocks.CHISELED_BOOKSHELF);
                        Map<WoodType, Block> lecterns = Util.copyAndPut(Util.mapValues(ModBlocks.LECTERNS, Holder::value), WoodType.OAK, Blocks.LECTERN);
                        Map<WoodType, Block> barrels = Util.copyAndPut(Util.mapValues(ModBlocks.BARRELS, Holder::value), WoodType.SPRUCE, Blocks.BARREL);
                        Map<WoodType, Block> crafters = Util.copyAndPut(Util.mapValues(ModBlocks.CRAFTERS, Holder::value), WoodType.OAK, Blocks.CRAFTER);

                        gameplayWoodTypeOrder.forEach(woodType -> {
                            output.accept(craftingTables.get(woodType));
                            output.accept(cartogarphyTables.get(woodType));
                            output.accept(fletchingTables.get(woodType));
                            output.accept(beehives.get(woodType));
                            output.accept(bookshelves.get(woodType));
                            output.accept(chisledBookshelves.get(woodType));
                            output.accept(lecterns.get(woodType));
                            output.accept(barrels.get(woodType));
                            output.accept(crafters.get(woodType));
                        });
                    })
                    .build()
    );

    // Add blocks to the vanilla tabs
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.insertAfter(Items.BAMBOO_BLOCK.getDefaultInstance(), ModBlocks.BAMBOO_WOOD.toStack(), PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.SMOOTH_STONE.getDefaultInstance(), ModBlocks.SMOOTH_STONE_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.NETHERITE_BLOCK.getDefaultInstance(), ModBlocks.NETHERITE_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.STONE_SLAB.getDefaultInstance(), ModBlocks.STONE_WALL.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.POLISHED_GRANITE_SLAB.getDefaultInstance(), ModBlocks.POLISHED_GRANITE_WALL.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.POLISHED_DIORITE_SLAB.getDefaultInstance(), ModBlocks.POLISHED_DIORITE_WALL.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.POLISHED_ANDESITE_SLAB.getDefaultInstance(), ModBlocks.POLISHED_ANDESITE_WALL.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.PRISMARINE_BRICK_SLAB.getDefaultInstance(), ModBlocks.PRISMARINE_BRICK_WALL.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.DARK_PRISMARINE_SLAB.getDefaultInstance(), ModBlocks.DARK_PRISMARINE_WALL.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.PURPUR_SLAB.getDefaultInstance(), ModBlocks.PURPUR_WALL.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.QUARTZ_SLAB.getDefaultInstance(), ModBlocks.QUARTZ_WALL.toStack(), PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.SMOOTH_STONE_SLAB.getDefaultInstance(), ModBlocks.POLISHED_STONE.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.POLISHED_STONE.toStack(), ModBlocks.POLISHED_STONE_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.POLISHED_STONE_STAIRS.toStack(), ModBlocks.POLISHED_STONE_SLAB.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.POLISHED_STONE_SLAB.toStack(), ModBlocks.POLISHED_STONE_WALL.toStack(), PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.GRANITE_WALL.getDefaultInstance(), ModBlocks.CHISELED_GRANITE.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.POLISHED_GRANITE_WALL.toStack(), ModBlocks.GRANITE_BRICKS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.GRANITE_BRICKS.toStack(), ModBlocks.GRANITE_BRICK_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.GRANITE_BRICK_STAIRS.toStack(), ModBlocks.GRANITE_BRICK_SLAB.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.GRANITE_BRICK_SLAB.toStack(), ModBlocks.GRANITE_BRICK_WALL.toStack(), PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.DIORITE_WALL.getDefaultInstance(), ModBlocks.CHISELED_DIORITE.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.POLISHED_DIORITE_WALL.toStack(), ModBlocks.DIORITE_BRICKS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.DIORITE_BRICKS.toStack(), ModBlocks.DIORITE_BRICK_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.DIORITE_BRICK_STAIRS.toStack(), ModBlocks.DIORITE_BRICK_SLAB.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.DIORITE_BRICK_SLAB.toStack(), ModBlocks.DIORITE_BRICK_WALL.toStack(), PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.ANDESITE_WALL.getDefaultInstance(), ModBlocks.CHISELED_ANDESITE.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.POLISHED_ANDESITE_WALL.toStack(), ModBlocks.ANDESITE_BRICKS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.ANDESITE_BRICKS.toStack(), ModBlocks.ANDESITE_BRICK_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.ANDESITE_BRICK_STAIRS.toStack(), ModBlocks.ANDESITE_BRICK_SLAB.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.ANDESITE_BRICK_SLAB.toStack(), ModBlocks.ANDESITE_BRICK_WALL.toStack(), PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.BRICK_WALL.getDefaultInstance(), ModBlocks.CHISELED_BRICKS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.PACKED_MUD.getDefaultInstance(), ModBlocks.CHISELED_MUD_BRICKS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.PRISMARINE_WALL.getDefaultInstance(), ModBlocks.CHISELED_PRISMARINE.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.END_STONE.getDefaultInstance(), ModBlocks.CHISELED_END_STONE_BRICKS.toStack(), PARENT_AND_SEARCH_TABS);

//            event.insertAfter(Items.COBBLED_DEEPSLATE_WALL.getDefaultInstance(), ModBlocks.MOSSY_COBBLED_DEEPSLATE.toStack(), PARENT_AND_SEARCH_TABS);
//            event.insertAfter(ModBlocks.MOSSY_COBBLED_DEEPSLATE.toStack(), ModBlocks.MOSSY_COBBLED_DEEPSLATE_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);
//            event.insertAfter(ModBlocks.MOSSY_COBBLED_DEEPSLATE_STAIRS.toStack(), ModBlocks.MOSSY_COBBLED_DEEPSLATE_SLAB.toStack(), PARENT_AND_SEARCH_TABS);
//            event.insertAfter(ModBlocks.MOSSY_COBBLED_DEEPSLATE_SLAB.toStack(), ModBlocks.MOSSY_COBBLED_DEEPSLATE_WALL.toStack(), PARENT_AND_SEARCH_TABS);
//
//            event.insertAfter(Items.DEEPSLATE_BRICK_WALL.getDefaultInstance(), ModBlocks.MOSSY_DEEPSLATE_BRICKS.toStack(), PARENT_AND_SEARCH_TABS);
//            event.insertAfter(ModBlocks.MOSSY_DEEPSLATE_BRICKS.toStack(), ModBlocks.MOSSY_DEEPSLATE_BRICK_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);
//            event.insertAfter(ModBlocks.MOSSY_DEEPSLATE_BRICK_STAIRS.toStack(), ModBlocks.MOSSY_DEEPSLATE_BRICK_SLAB.toStack(), PARENT_AND_SEARCH_TABS);
//            event.insertAfter(ModBlocks.MOSSY_DEEPSLATE_BRICK_SLAB.toStack(), ModBlocks.MOSSY_DEEPSLATE_BRICK_WALL.toStack(), PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.SMOOTH_BASALT.getDefaultInstance(), ModBlocks.SMOOTH_BASALT_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.SMOOTH_BASALT_STAIRS.toStack(), ModBlocks.SMOOTH_BASALT_SLAB.toStack(), PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.QUARTZ_BRICKS.getDefaultInstance(), ModBlocks.QUARTZ_BRICK_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.QUARTZ_BRICK_STAIRS.toStack(), ModBlocks.QUARTZ_BRICK_SLAB.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.QUARTZ_BRICK_SLAB.toStack(), ModBlocks.QUARTZ_BRICK_WALL.toStack(), PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.CHISELED_NETHER_BRICKS.getDefaultInstance(), ModBlocks.NETHER_BRICK_TILES.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.NETHER_BRICK_TILES.toStack(), ModBlocks.NETHER_BRICK_TILE_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.NETHER_BRICK_TILE_STAIRS.toStack(), ModBlocks.NETHER_BRICK_TILE_SLAB.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.NETHER_BRICK_TILE_SLAB.toStack(), ModBlocks.NETHER_BRICK_TILE_WALL.toStack(), PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.RED_NETHER_BRICKS.getDefaultInstance(), ModBlocks.CRACKED_RED_NETHER_BRICKS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.RED_NETHER_BRICK_WALL.getDefaultInstance(), ModBlocks.RED_NETHER_BRICK_FENCE.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.RED_NETHER_BRICK_FENCE.toStack(), ModBlocks.CHISELED_RED_NETHER_BRICKS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.CHISELED_RED_NETHER_BRICKS.toStack(), ModBlocks.RED_NETHER_BRICK_TILES.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.RED_NETHER_BRICK_TILES.toStack(), ModBlocks.RED_NETHER_BRICK_TILE_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.RED_NETHER_BRICK_TILE_STAIRS.toStack(), ModBlocks.RED_NETHER_BRICK_TILE_SLAB.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.RED_NETHER_BRICK_TILE_SLAB.toStack(), ModBlocks.RED_NETHER_BRICK_TILE_WALL.toStack(), PARENT_AND_SEARCH_TABS);

            event.insertAfter(ModBlocks.RED_NETHER_BRICK_TILE_WALL.toStack(), ModBlocks.BLUE_NETHER_BRICKS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.BLUE_NETHER_BRICKS.toStack(), ModBlocks.CRACKED_BLUE_NETHER_BRICKS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.CRACKED_BLUE_NETHER_BRICKS.toStack(), ModBlocks.BLUE_NETHER_BRICK_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.BLUE_NETHER_BRICK_STAIRS.toStack(), ModBlocks.BLUE_NETHER_BRICK_SLAB.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.BLUE_NETHER_BRICK_SLAB.toStack(), ModBlocks.BLUE_NETHER_BRICK_WALL.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.BLUE_NETHER_BRICK_WALL.toStack(), ModBlocks.BLUE_NETHER_BRICK_FENCE.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.BLUE_NETHER_BRICK_FENCE.toStack(), ModBlocks.CHISELED_BLUE_NETHER_BRICKS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.CHISELED_BLUE_NETHER_BRICKS.toStack(), ModBlocks.BLUE_NETHER_BRICK_TILES.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.BLUE_NETHER_BRICK_TILES.toStack(), ModBlocks.BLUE_NETHER_BRICK_TILE_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.BLUE_NETHER_BRICK_TILE_STAIRS.toStack(), ModBlocks.BLUE_NETHER_BRICK_TILE_SLAB.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.BLUE_NETHER_BRICK_TILE_SLAB.toStack(), ModBlocks.BLUE_NETHER_BRICK_TILE_WALL.toStack(), PARENT_AND_SEARCH_TABS);

            Item previous = Items.CUT_RED_SANDSTONE_SLAB;
            for (DeferredHolder<Block, ? extends Block> entry : ModBlocks.BLOCKS.getEntries()) {
                if (entry.get().toString().contains("soul_sandstone")) {
                    event.insertAfter(previous.getDefaultInstance(), entry.get().asItem().getDefaultInstance(), PARENT_AND_SEARCH_TABS);
                    previous = entry.get().asItem();
                }
            }
        }
        if (event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS)
            event.insertAfter(Items.GLASS_PANE.getDefaultInstance(), ModBlocks.TINTED_GLASS_PANE.toStack(), PARENT_AND_SEARCH_TABS);
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.insertAfter(Items.SOUL_SAND.getDefaultInstance(), ModBlocks.SOUL_SANDSTONE.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.JACK_O_LANTERN.getDefaultInstance(), ModBlocks.SOUL_O_LANTERN.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.SOUL_O_LANTERN.toStack(), ModBlocks.COPPER_O_LANTERN.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.COPPER_O_LANTERN.toStack(), ModBlocks.ENDER_O_LANTERN.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.ENDER_O_LANTERN.toStack(), ModBlocks.REDSTONE_O_LANTERN.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.RED_SHRUB.getDefaultInstance(), ModBlocks.ORANGE_SHRUB.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.ORANGE_SHRUB.toStack(), ModBlocks.YELLOW_SHRUB.toStack(), PARENT_AND_SEARCH_TABS);
        }
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.insertAfter(Items.COPPER_TORCH.getDefaultInstance(), ModItems.ENDER_TORCH.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.SOUL_LANTERN.getDefaultInstance(), ModBlocks.ENDER_LANTERN.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.COPPER_LANTERN.waxed().oxidized().getDefaultInstance(), ModBlocks.REDSTONE_LANTERN.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.SOUL_CAMPFIRE.getDefaultInstance(), ModBlocks.COPPER_CAMPFIRE.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.OCHRE_FROGLIGHT.getDefaultInstance(), ModBlocks.CERULEAN_FROGLIGHT.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.PEARLESCENT_FROGLIGHT.getDefaultInstance(), ModBlocks.CARMINE_FROGLIGHT.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.COPPER_CAMPFIRE.toStack(), ModBlocks.ENDER_CAMPFIRE.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.ENDER_CAMPFIRE.toStack(), ModBlocks.REDSTONE_CAMPFIRE.toStack(), PARENT_AND_SEARCH_TABS);
        }
        if(event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.insertAfter(Items.REDSTONE_TORCH.getDefaultInstance(), ModBlocks.REDSTONE_LANTERN.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.REDSTONE_LANTERN.toStack(), ModBlocks.REDSTONE_CAMPFIRE.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.REDSTONE_CAMPFIRE.toStack(), ModBlocks.REDSTONE_O_LANTERN.toStack(), PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
            event.insertAfter(Items.TNT_MINECART.getDefaultInstance(), ModItems.SPAWNER_MINECART.toStack(), PARENT_AND_SEARCH_TABS);
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.insertAfter(Items.NETHER_BRICK.getDefaultInstance(), ModItems.RED_NETHER_BRICK.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.RED_NETHER_BRICK.toStack(), ModItems.BLUE_NETHER_BRICK.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.NETHER_WART.getDefaultInstance(), ModItems.BLUE_NETHER_WART.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.EXPLORER_POTTERY_SHERD.getDefaultInstance(), ModItems.EMPTY_POTTERY_SHERD.toStack(), PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS)
            event.insertAfter(Items.EVOKER_SPAWN_EGG.getDefaultInstance(), ModItems.ILLUSIONER_SPAWN_EGG.toStack(), PARENT_AND_SEARCH_TABS);
    }

    private static void acceptWithoutThrowing(CreativeModeTab.Output output, ItemLike item) {
        try {
            output.accept(item);
        } catch (Exception _) {}
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
