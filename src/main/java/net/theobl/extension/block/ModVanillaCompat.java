package net.theobl.extension.block;

import com.google.common.collect.Maps;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.registries.RegistryObject;
import net.theobl.extension.item.ModItems;
import net.theobl.extension.mixin.FireBlockMixin;

public class ModVanillaCompat {
    public static void setup() {
        for (RegistryObject<Block> log : ModBlocks.COLORED_LOGS) {
            RegistryObject<Block> strippedLog = ModBlocks.COLORED_STRIPPED_LOGS.get(ModBlocks.COLORED_LOGS.indexOf(log));
            registerFlammable(log.get(), 5,5);
            registerFlammable(strippedLog.get(), 5,5);
            registerStrippable(log.get(), strippedLog.get());
        }

        for (RegistryObject<Block> wood : ModBlocks.COLORED_WOODS) {
            RegistryObject<Block> strippedWood = ModBlocks.COLORED_STRIPPED_WOODS.get(ModBlocks.COLORED_WOODS.indexOf(wood));
            registerFlammable(wood.get(), 5,5);
            registerFlammable(strippedWood.get(), 5,5);
            registerStrippable(wood.get(), strippedWood.get());
        }

        for (RegistryObject<Block> leaves : ModBlocks.COLORED_LEAVES) {
            registerFlammable(leaves.get(), 30,60);
        }

        for (RegistryObject<Block> plank : ModBlocks.COLORED_PLANKS) {
            registerFlammable(plank.get(), 5,20);
        }

        for (RegistryObject<Block> stair : ModBlocks.COLORED_STAIRS) {
            registerFlammable(stair.get(), 5,20);
        }

        for (RegistryObject<Block> slab : ModBlocks.COLORED_SLABS) {
            registerFlammable(slab.get(), 5,20);
        }

        for (RegistryObject<Block> fence : ModBlocks.COLORED_FENCES) {
            registerFlammable(fence.get(), 5,20);
        }

        for (RegistryObject<Block> gate : ModBlocks.COLORED_FENCE_GATES) {
            registerFlammable(gate.get(), 5,20);
        }

        registerFlammable(ModBlocks.PAINTING_PLANKS.get(), 5, 20);
        registerFlammable(ModBlocks.PAINTING_STAIRS.get(), 5, 20);
        registerFlammable(ModBlocks.PAINTING_SLAB.get(), 5, 20);
        registerFlammable(ModBlocks.PAINTING_FENCE.get(), 5, 20);
        registerFlammable(ModBlocks.PAINTING_FENCE_GATE.get(), 5, 20);

        registerFlammable(ModBlocks.OAK_MOSAIC.get(), 5,20);
        registerFlammable(ModBlocks.SPRUCE_MOSAIC.get(), 5,20);
        registerFlammable(ModBlocks.BIRCH_MOSAIC.get(), 5,20);
        registerFlammable(ModBlocks.JUNGLE_MOSAIC.get(), 5,20);
        registerFlammable(ModBlocks.ACACIA_MOSAIC.get(), 5,20);
        registerFlammable(ModBlocks.DARK_OAK_MOSAIC.get(), 5,20);
        registerFlammable(ModBlocks.MANGROVE_MOSAIC.get(), 5,20);
        registerFlammable(ModBlocks.CHERRY_MOSAIC.get(), 5,20);

        registerFlammable(ModBlocks.SPRUCE_BOOKSHELF.get(), 30,20);
        registerFlammable(ModBlocks.BIRCH_BOOKSHELF.get(), 30,20);
        registerFlammable(ModBlocks.JUNGLE_BOOKSHELF.get(), 30,20);
        registerFlammable(ModBlocks.ACACIA_BOOKSHELF.get(), 30,20);
        registerFlammable(ModBlocks.DARK_OAK_BOOKSHELF.get(), 30,20);
        registerFlammable(ModBlocks.MANGROVE_BOOKSHELF.get(), 30,20);
        registerFlammable(ModBlocks.BAMBOO_BOOKSHELF.get(), 30,20);
        registerFlammable(ModBlocks.CHERRY_BOOKSHELF.get(), 30,20);

        registerFlammable(ModBlocks.VIBRANT_RED_WOOL.get(), 30, 60);
        registerFlammable(ModBlocks.DULL_ORANGE_WOOL.get(), 30, 60);
        registerFlammable(ModBlocks.BRIGHT_YELLOW_WOOL.get(), 30, 60);
        registerFlammable(ModBlocks.CHARTREUSE_WOOL.get(), 30, 60);
        registerFlammable(ModBlocks.VIBRANT_GREEN_WOOL.get(), 30, 60);
        registerFlammable(ModBlocks.SPRING_GREEN_WOOL.get(), 30, 60);
        registerFlammable(ModBlocks.BRIGHT_CYAN_WOOL.get(), 30, 60);
        registerFlammable(ModBlocks.CAPRI_WOOL.get(), 30, 60);
        registerFlammable(ModBlocks.ULTRAMARINE_WOOL.get(), 30, 60);
        registerFlammable(ModBlocks.VIOLET_WOOL.get(), 30, 60);
        registerFlammable(ModBlocks.VIBRANT_PURPLE_WOOL.get(), 30, 60);
        registerFlammable(ModBlocks.BRIGHT_MAGENTA_WOOL.get(), 30, 60);
        registerFlammable(ModBlocks.ROSE_WOOL.get(), 30, 60);
        registerFlammable(ModBlocks.DARK_GRAY_WOOL.get(), 30, 60);
        registerFlammable(ModBlocks.SILVER_WOOL.get(), 30, 60);
        registerFlammable(ModBlocks.ALPHA_WHITE_WOOL.get(), 30, 60);

        registerFlammable(ModBlocks.VIBRANT_RED_CARPET.get(), 60, 20);
        registerFlammable(ModBlocks.DULL_ORANGE_CARPET.get(), 60, 20);
        registerFlammable(ModBlocks.BRIGHT_YELLOW_CARPET.get(), 60, 20);
        registerFlammable(ModBlocks.CHARTREUSE_CARPET.get(), 60, 20);
        registerFlammable(ModBlocks.VIBRANT_GREEN_CARPET.get(), 60, 20);
        registerFlammable(ModBlocks.SPRING_GREEN_CARPET.get(), 60, 20);
        registerFlammable(ModBlocks.BRIGHT_CYAN_CARPET.get(), 60, 20);
        registerFlammable(ModBlocks.CAPRI_CARPET.get(), 60, 20);
        registerFlammable(ModBlocks.ULTRAMARINE_CARPET.get(), 60, 20);
        registerFlammable(ModBlocks.VIOLET_CARPET.get(), 60, 20);
        registerFlammable(ModBlocks.VIBRANT_PURPLE_CARPET.get(), 60, 20);
        registerFlammable(ModBlocks.BRIGHT_MAGENTA_CARPET.get(), 60, 20);
        registerFlammable(ModBlocks.ROSE_CARPET.get(), 60, 20);
        registerFlammable(ModBlocks.DARK_GRAY_CARPET.get(), 60, 20);
        registerFlammable(ModBlocks.SILVER_CARPET.get(), 60, 20);
        registerFlammable(ModBlocks.ALPHA_WHITE_CARPET.get(), 60, 20);

    }

    public static void registerFlammable(Block block, int fireSpreadSpeed, int flammability) {
        FireBlock fireblock = (FireBlock) Blocks.FIRE;
        //((FireBlockMixin) fireblock).invokeSetFlammable(block, fireSpreadSpeed, flammability);
        fireblock.setFlammable(block, fireSpreadSpeed, flammability);
    }

    public static void registerStrippable(Block log, Block strippedLog) {
        AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
        AxeItem.STRIPPABLES.put(log, strippedLog);
    }

    public static void registerItemInVanillaTabs(BuildCreativeModeTabContentsEvent event) {
        MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            registerAfter(Items.BAMBOO_CHEST_RAFT, ModItems.CRIMSON_BOAT.get(), entries);
            registerAfter(ModItems.CRIMSON_BOAT.get(), ModItems.CRIMSON_CHEST_BOAT.get(), entries);
            registerAfter(ModItems.CRIMSON_CHEST_BOAT.get(), ModItems.WARPED_BOAT.get(), entries);
            registerAfter(ModItems.WARPED_BOAT.get(), ModItems.WARPED_CHEST_BOAT.get(), entries);

            registerAfter(Items.TNT_MINECART, ModItems.SPAWNER_MINECART.get(), entries);
        } else if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            registerAfter(Items.DIAMOND_HORSE_ARMOR, ModItems.NETHERITE_HORSE_ARMOR.get(), entries);
        } else if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            registerAfter(Items.STONE_SLAB, ModBlocks.STONE_WALL.get().asItem(), entries);
            registerAfter(Items.POLISHED_ANDESITE_SLAB, ModBlocks.POLISHED_ANDESITE_WALL.get().asItem(), entries);
            registerAfter(Items.POLISHED_DIORITE_SLAB, ModBlocks.POLISHED_DIORITE_WALL.get().asItem(), entries);
            registerAfter(Items.POLISHED_GRANITE_SLAB, ModBlocks.POLISHED_GRANITE_WALL.get().asItem(), entries);
            registerAfter(Items.PRISMARINE_BRICK_SLAB, ModBlocks.PRISMARINE_BRICK_WALL.get().asItem(), entries);
            registerAfter(Items.DARK_PRISMARINE_SLAB, ModBlocks.DARK_PRISMARINE_BRICK_WALL.get().asItem(), entries);
            registerAfter(Items.PURPUR_SLAB, ModBlocks.PURPUR_WALL.get().asItem(), entries);
            registerAfter(Items.QUARTZ_SLAB, ModBlocks.QUARTZ_WALL.get().asItem(), entries);
            registerAfter(Items.SMOOTH_QUARTZ_SLAB, ModBlocks.SMOOTH_QUARTZ_WALL.get().asItem(), entries);
            registerAfter(Items.CUT_COPPER_SLAB, ModBlocks.CUT_COPPER_WALL.get().asItem(), entries);
            registerAfter(Items.EXPOSED_CUT_COPPER_SLAB, ModBlocks.EXPOSED_CUT_COPPER_WALL.get().asItem(), entries);
            registerAfter(Items.WEATHERED_CUT_COPPER_SLAB, ModBlocks.WEATHERED_CUT_COPPER_WALL.get().asItem(), entries);
            registerAfter(Items.OXIDIZED_CUT_COPPER_SLAB, ModBlocks.OXIDIZED_CUT_COPPER_WALL.get().asItem(), entries);
            registerAfter(Items.WAXED_CUT_COPPER_SLAB, ModBlocks.WAXED_CUT_COPPER_WALL.get().asItem(), entries);
            registerAfter(Items.WAXED_EXPOSED_CUT_COPPER_SLAB, ModBlocks.WAXED_EXPOSED_CUT_COPPER_WALL.get().asItem(), entries);
            registerAfter(Items.WAXED_WEATHERED_CUT_COPPER_SLAB, ModBlocks.WAXED_WEATHERED_CUT_COPPER_WALL.get().asItem(), entries);
            registerAfter(Items.WAXED_OXIDIZED_CUT_COPPER_SLAB, ModBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL.get().asItem(), entries);

            registerAfter(Items.NETHER_BRICK_FENCE, ModBlocks.NETHER_BRICK_FENCE_GATE.get().asItem(), entries);

            registerAfter(Items.RED_NETHER_BRICKS, ModBlocks.CRACKED_RED_NETHER_BRICKS.get().asItem(), entries);
            registerAfter(Items.RED_NETHER_BRICK_WALL, ModBlocks.RED_NETHER_BRICK_FENCE.get().asItem(), entries);
            registerAfter(ModBlocks.RED_NETHER_BRICK_FENCE.get().asItem(), ModBlocks.RED_NETHER_BRICK_FENCE_GATE.get().asItem(), entries);
            registerAfter(ModBlocks.RED_NETHER_BRICK_FENCE_GATE.get().asItem(), ModBlocks.CHISELED_RED_NETHER_BRICKS.get().asItem(), entries);

            registerAfter(ModBlocks.CHISELED_RED_NETHER_BRICKS.get().asItem(), ModBlocks.BLUE_NETHER_BRICKS.get().asItem(), entries);
            registerAfter(ModBlocks.BLUE_NETHER_BRICKS.get().asItem(), ModBlocks.CRACKED_BLUE_NETHER_BRICKS.get().asItem(), entries);
            registerAfter(ModBlocks.CRACKED_BLUE_NETHER_BRICKS.get().asItem(), ModBlocks.BLUE_NETHER_BRICK_STAIRS.get().asItem(), entries);
            registerAfter(ModBlocks.BLUE_NETHER_BRICK_STAIRS.get().asItem(), ModBlocks.BLUE_NETHER_BRICK_SLAB.get().asItem(), entries);
            registerAfter(ModBlocks.BLUE_NETHER_BRICK_SLAB.get().asItem(), ModBlocks.BLUE_NETHER_BRICK_WALL.get().asItem(), entries);
            registerAfter(ModBlocks.BLUE_NETHER_BRICK_WALL.get().asItem(), ModBlocks.BLUE_NETHER_BRICK_FENCE.get().asItem(), entries);
            registerAfter(ModBlocks.BLUE_NETHER_BRICK_FENCE.get().asItem(), ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get().asItem(), entries);
            registerAfter(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get().asItem(), ModBlocks.CHISELED_BLUE_NETHER_BRICKS.get().asItem(), entries);

            Item previous = Items.CUT_RED_SANDSTONE_SLAB;
            for (RegistryObject<Block> entry : ModBlocks.BLOCKS.getEntries()) {
                if (entry.get().toString().contains("soul")) {
                    registerAfter(previous, entry.get().asItem(), entries);
                    previous = entry.get().asItem();
                }
            }
        }
        else if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            registerAfter(Items.SPAWNER, ModItems.SPAWNER_MINECART.get(), entries);
            registerAfter(Items.HUSK_SPAWN_EGG, ModItems.ILLUSIONER_SPAWN_EGG.get(), entries);
        }
        else if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            registerAfter(Items.NETHER_BRICK, ModItems.RED_NETHER_BRICK.get(), entries);
            registerAfter(ModItems.RED_NETHER_BRICK.get(), ModItems.BLUE_NETHER_BRICK.get(), entries);
        }
        else if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            registerAfter(Items.SOUL_SAND, ModBlocks.SOUL_SANDSTONE.get().asItem(), entries);
        }
        else if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            registerAfter(Items.REDSTONE_TORCH, ModBlocks.REDSTONE_LANTERN.get().asItem(), entries);
        }
        else if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            registerAfter(Items.SOUL_LANTERN, ModBlocks.REDSTONE_LANTERN.get().asItem(), entries);
        }
        else if (event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
            registerAfter(Items.GLASS_PANE, ModBlocks.TINTED_GLASS_PANE.get().asItem(), entries);

            Item previous = Items.PINK_TERRACOTTA;
            for (RegistryObject<Block> antiblock : ModBlocks.ANTIBLOCKS) {
                registerAfter(previous, antiblock.get().asItem(), entries);
                previous = antiblock.get().asItem();
            }

            previous = Items.PINK_CONCRETE;
            for (RegistryObject<Block> quiltedConcrete : ModBlocks.QUILTED_CONCRETES) {
                registerAfter(previous, quiltedConcrete.get().asItem(), entries);
                previous = quiltedConcrete.get().asItem();
            }

            previous = Items.PINK_GLAZED_TERRACOTTA;
            for (RegistryObject<Block> glazedConcrete : ModBlocks.GLAZED_CONCRETES) {
                registerAfter(previous, glazedConcrete.get().asItem(), entries);
                previous = glazedConcrete.get().asItem();
            }
            previous = Items.PINK_WOOL;
            for (RegistryObject<Block> planks : ModBlocks.COLORED_PLANKS) {
                registerAfter(previous, planks.get().asItem(), entries);
                previous = planks.get().asItem();
            }
        }
    }

    public static void registerAfter(Item after, Item item, MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries) {
        entries.putAfter(after.getDefaultInstance(), item.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
}
