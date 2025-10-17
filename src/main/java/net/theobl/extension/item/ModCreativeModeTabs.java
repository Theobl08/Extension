package net.theobl.extension.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;

import static net.minecraft.world.item.CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;

public class ModCreativeModeTabs {
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "extension" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Extension.MODID);

    // Creates a creative tab with the id "extension:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXTENSION_TAB = CREATIVE_MODE_TABS.register("extension_tab",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.extension"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> ModItems.SPAWNER_MINECART.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        for (DeferredHolder<Item, ? extends Item> deferredItem : ModItems.ITEMS.getEntries()) {
                            if(deferredItem.get() instanceof BlockItem && !(deferredItem.get() == ModItems.BLUE_NETHER_WART.asItem()))
                                output.accept(deferredItem.get());
                        }
                        for (DeferredHolder<Item, ? extends Item> deferredItem : ModItems.ITEMS.getEntries()) {
                            if(!(deferredItem.get() instanceof BlockItem))
                                output.accept(deferredItem.get());
                        }
                        output.accept(ModItems.BLUE_NETHER_WART); // Add the example item to the tab. For your own tabs, this method is preferred over the event
                    }).build());

    // Add blocks to the vanilla tabs
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
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
            event.insertAfter(ModBlocks.COPPER_O_LANTERN.toStack(), ModBlocks.REDSTONE_O_LANTERN.toStack(), PARENT_AND_SEARCH_TABS);
        }
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.insertAfter(Items.COPPER_LANTERN.waxedOxidized().getDefaultInstance(), ModBlocks.REDSTONE_LANTERN.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.SOUL_CAMPFIRE.getDefaultInstance(), ModBlocks.COPPER_CAMPFIRE.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.COPPER_CAMPFIRE.toStack(), ModBlocks.REDSTONE_CAMPFIRE.toStack(), PARENT_AND_SEARCH_TABS);
        }
        if(event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.insertAfter(Items.REDSTONE_TORCH.getDefaultInstance(), ModBlocks.REDSTONE_LANTERN.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.REDSTONE_LANTERN.toStack(), ModBlocks.REDSTONE_CAMPFIRE.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.REDSTONE_CAMPFIRE.toStack(), ModBlocks.REDSTONE_O_LANTERN.toStack(), PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
            event.insertAfter(Items.TNT_MINECART.getDefaultInstance(), ModItems.SPAWNER_MINECART.toStack(), PARENT_AND_SEARCH_TABS);
        if (event.getTabKey() == CreativeModeTabs.COMBAT)
            event.insertAfter(Items.DIAMOND_HORSE_ARMOR.getDefaultInstance(), ModItems.NETHERITE_HORSE_ARMOR.toStack(), PARENT_AND_SEARCH_TABS);
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.insertAfter(Items.NETHER_BRICK.getDefaultInstance(), ModItems.RED_NETHER_BRICK.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.RED_NETHER_BRICK.toStack(), ModItems.BLUE_NETHER_BRICK.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.NETHER_WART.getDefaultInstance(), ModItems.BLUE_NETHER_WART.toStack(), PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS)
            event.insertAfter(new ItemStack(Items.HUSK_SPAWN_EGG), new ItemStack(ModItems.ILLUSIONER_SPAWN_EGG.asItem()), PARENT_AND_SEARCH_TABS);
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
