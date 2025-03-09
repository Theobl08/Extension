package net.theobl.extension.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Extension.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

//        this.tag(ModTags.Blocks.WHITE_LOGS)
//                .add(ModBlocks.WHITE_LOG.get(),
//                        ModBlocks.WHITE_WOOD.get(),
//                        ModBlocks.COLORED_STRIPPED_LOGS.get(0).get(),
//                        ModBlocks.COLORED_STRIPPED_WOODS.get(0).get());
////                        ModBlocks.STRIPPED_WHITE_LOG.get(),
////                        ModBlocks.STRIPPED_WHITE_WOOD.get());
//
//        this.tag(ModTags.Blocks.LIGHT_GRAY_LOGS)
//                .add(ModBlocks.LIGHT_GRAY_LOG.get(),
//                        ModBlocks.LIGHT_GRAY_WOOD.get(),
//                        ModBlocks.COLORED_STRIPPED_LOGS.get(1).get(),
//                        ModBlocks.COLORED_STRIPPED_WOODS.get(1).get());
////                        ModBlocks.STRIPPED_LIGHT_GRAY_LOG.get(),
////                        ModBlocks.STRIPPED_LIGHT_GRAY_WOOD.get());
//
//        this.tag(ModTags.Blocks.GRAY_LOGS)
//                .add(ModBlocks.GRAY_LOG.get(),
//                        ModBlocks.GRAY_WOOD.get(),
//                        ModBlocks.COLORED_STRIPPED_LOGS.get(2).get(),
//                        ModBlocks.COLORED_STRIPPED_WOODS.get(2).get());
////                        ModBlocks.STRIPPED_GRAY_LOG.get(),
////                        ModBlocks.STRIPPED_GRAY_WOOD.get());
//
//        this.tag(ModTags.Blocks.BLACK_LOGS)
//                .add(ModBlocks.BLACK_LOG.get(),
//                        ModBlocks.BLACK_WOOD.get(),
//                        ModBlocks.COLORED_STRIPPED_LOGS.get(3).get(),
//                        ModBlocks.COLORED_STRIPPED_WOODS.get(3).get());
////                        ModBlocks.STRIPPED_BLACK_LOG.get(),
////                        ModBlocks.STRIPPED_BLACK_WOOD.get());
//
//        this.tag(BlockTags.LOGS_THAT_BURN)
//                .addTag(ModTags.Blocks.WHITE_LOGS)
//                .addTag(ModTags.Blocks.LIGHT_GRAY_LOGS)
//                .addTag(ModTags.Blocks.GRAY_LOGS)
//                .addTag(ModTags.Blocks.BLACK_LOGS);

        for (TagKey<Block> tagKey : ModTags.Blocks.COLORED_LOGS) {
            int index = ModTags.Blocks.COLORED_LOGS.indexOf(tagKey);
            this.tag(tagKey).add(ModBlocks.COLORED_LOGS.get(index).get(),
                    ModBlocks.COLORED_WOODS.get(index).get(),
                    ModBlocks.COLORED_STRIPPED_LOGS.get(index).get(),
                    ModBlocks.COLORED_STRIPPED_WOODS.get(index).get());
            this.tag(BlockTags.LOGS_THAT_BURN).addTag(tagKey);
        }

        for (RegistryObject<Block> block : ModBlocks.COLORED_PLANKS) {
            this.tag(BlockTags.PLANKS).add(block.get());
        }

//        this.tag(BlockTags.PLANKS)
//                .add(ModBlocks.WHITE_PLANKS.get())
//                .add(ModBlocks.LIGHT_GRAY_PLANKS.get())
//                .add(ModBlocks.GRAY_PLANKS.get())
//                .add(ModBlocks.BLACK_PLANKS.get());

//        this.tag(BlockTags.WOODEN_STAIRS)
//                .add(ModBlocks.WHITE_STAIRS.get())
//                .add(ModBlocks.LIGHT_GRAY_STAIRS.get())
//                .add(ModBlocks.GRAY_STAIRS.get())
//                .add(ModBlocks.BLACK_STAIRS.get());

        for (RegistryObject<Block> block : ModBlocks.COLORED_STAIRS) {
            this.tag(BlockTags.WOODEN_STAIRS).add(block.get());
        }

        for (RegistryObject<Block> block : ModBlocks.COLORED_SLABS) {
            this.tag(BlockTags.WOODEN_SLABS).add(block.get());
        }

//        this.tag(BlockTags.WOODEN_SLABS)
//                .add(ModBlocks.WHITE_SLAB.get())
//                .add(ModBlocks.LIGHT_GRAY_SLAB.get())
//                .add(ModBlocks.GRAY_SLAB.get())
//                .add(ModBlocks.BLACK_SLAB.get());

        for (RegistryObject<Block> block : ModBlocks.COLORED_FENCES) {
            this.tag(BlockTags.WOODEN_FENCES).add(block.get());
        }
//        this.tag(BlockTags.WOODEN_FENCES)
//                .add(ModBlocks.WHITE_FENCE.get())
//                .add(ModBlocks.LIGHT_GRAY_FENCE.get())
//                .add(ModBlocks.GRAY_FENCE.get())
//                .add(ModBlocks.BLACK_FENCE.get());

        for (RegistryObject<Block> block : ModBlocks.COLORED_FENCE_GATES) {
            this.tag(BlockTags.FENCE_GATES).add(block.get());
        }
//        this.tag(BlockTags.FENCE_GATES)
//                .add(ModBlocks.WHITE_FENCE_GATE.get())
//                .add(ModBlocks.LIGHT_GRAY_FENCE_GATE.get())
//                .add(ModBlocks.GRAY_FENCE_GATE.get())
//                .add(ModBlocks.BLACK_FENCE_GATE.get());

        for (RegistryObject<Block> block : ModBlocks.COLORED_DOORS) {
            this.tag(BlockTags.WOODEN_DOORS).add(block.get());
        }

//        this.tag(BlockTags.WOODEN_DOORS)
//                .add(ModBlocks.WHITE_DOOR.get())
//                .add(ModBlocks.LIGHT_GRAY_DOOR.get())
//                .add(ModBlocks.GRAY_DOOR.get())
//                .add(ModBlocks.BLACK_DOOR.get());

        for (RegistryObject<Block> block : ModBlocks.COLORED_TRAPDOORS) {
            this.tag(BlockTags.WOODEN_TRAPDOORS).add(block.get());
        }
//        this.tag(BlockTags.WOODEN_TRAPDOORS)
//                .add(ModBlocks.WHITE_TRAPDOOR.get())
//                .add(ModBlocks.LIGHT_GRAY_TRAPDOOR.get())
//                .add(ModBlocks.GRAY_TRAPDOOR.get())
//                .add(ModBlocks.BLACK_TRAPDOOR.get());

        for (RegistryObject<Block> block : ModBlocks.COLORED_PRESSURE_PLATES) {
            this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(block.get());
        }
//        this.tag(BlockTags.WOODEN_PRESSURE_PLATES)
//                .add(ModBlocks.WHITE_PRESSURE_PLATE.get())
//                .add(ModBlocks.LIGHT_GRAY_PRESSURE_PLATE.get())
//                .add(ModBlocks.GRAY_PRESSURE_PLATE.get())
//                .add(ModBlocks.BLACK_PRESSURE_PLATE.get());

        for (RegistryObject<Block> block : ModBlocks.COLORED_BUTTONS) {
            this.tag(BlockTags.WOODEN_BUTTONS).add(block.get());
        }
//        this.tag(BlockTags.WOODEN_BUTTONS)
//                .add(ModBlocks.WHITE_BUTTON.get())
//                .add(ModBlocks.LIGHT_GRAY_BUTTON.get())
//                .add(ModBlocks.GRAY_BUTTON.get())
//                .add(ModBlocks.BLACK_BUTTON.get());

        for (RegistryObject<Block> block : ModBlocks.COLORED_SIGNS) {
            this.tag(BlockTags.STANDING_SIGNS).add(block.get());
        }
        for (RegistryObject<Block> block : ModBlocks.COLORED_WALL_SIGNS) {
            this.tag(BlockTags.WALL_SIGNS).add(block.get());
        }
        for (RegistryObject<Block> block : ModBlocks.COLORED_HANGING_SIGNS) {
            this.tag(BlockTags.CEILING_HANGING_SIGNS).add(block.get());
        }
        for (RegistryObject<Block> block : ModBlocks.COLORED_WALL_HANGING_SIGNS) {
            this.tag(BlockTags.WALL_HANGING_SIGNS).add(block.get());
        }
        for (RegistryObject<Block> block : ModBlocks.COLORED_LEAVES) {
            this.tag(BlockTags.LEAVES).add(block.get());
        }

//        this.tag(BlockTags.LEAVES)
//                .add(ModBlocks.WHITE_LEAVES.get())
//                .add(ModBlocks.LIGHT_GRAY_LEAVES.get())
//                .add(ModBlocks.GRAY_LEAVES.get())
//                .add(ModBlocks.BLACK_LEAVES.get());

        this.tag(BlockTags.STAIRS)
                .add(ModBlocks.BLUE_NETHER_BRICK_STAIRS.get())
                .add(ModBlocks.SOUL_SANDSTONE_STAIRS.get())
                .add(ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get());

        this.tag(BlockTags.SLABS)
                .add(ModBlocks.BLUE_NETHER_BRICK_SLAB.get())
                .add(ModBlocks.SOUL_SANDSTONE_SLAB.get())
                .add(ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get())
                .add(ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get());

        this.tag(BlockTags.WALLS)
                .add(ModBlocks.STONE_WALL.get())
                .add(ModBlocks.POLISHED_GRANITE_WALL.get())
                .add(ModBlocks.POLISHED_DIORITE_WALL.get())
                .add(ModBlocks.POLISHED_ANDESITE_WALL.get())
                .add(ModBlocks.PRISMARINE_BRICK_WALL.get())
                .add(ModBlocks.DARK_PRISMARINE_BRICK_WALL.get())
                .add(ModBlocks.PURPUR_WALL.get())
                .add(ModBlocks.QUARTZ_WALL.get())
                .add(ModBlocks.SMOOTH_QUARTZ_WALL.get())
                .add(ModBlocks.BLUE_NETHER_BRICK_WALL.get())
                .add(ModBlocks.SOUL_SANDSTONE_WALL.get())
                .add(ModBlocks.CUT_COPPER_WALL.get())
                .add(ModBlocks.EXPOSED_CUT_COPPER_WALL.get())
                .add(ModBlocks.WEATHERED_CUT_COPPER_WALL.get())
                .add(ModBlocks.OXIDIZED_CUT_COPPER_WALL.get())
                .add(ModBlocks.WAXED_CUT_COPPER_WALL.get())
                .add(ModBlocks.WAXED_EXPOSED_CUT_COPPER_WALL.get())
                .add(ModBlocks.WAXED_WEATHERED_CUT_COPPER_WALL.get())
                .add(ModBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL.get());

        this.tag(BlockTags.FENCES)
                .add(ModBlocks.RED_NETHER_BRICK_FENCE.get())
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE.get());

        this.tag(Tags.Blocks.FENCE_GATES)
                .add(ModBlocks.NETHER_BRICK_FENCE_GATE.get())
                .add(ModBlocks.RED_NETHER_BRICK_FENCE_GATE.get())
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get());

        this.tag(BlockTags.MINEABLE_WITH_HOE)
                .addTag(BlockTags.LEAVES);

        this.tag(BlockTags.ENCHANTMENT_POWER_PROVIDER)
                .addTag(Tags.Blocks.BOOKSHELVES);

        this.tag(Tags.Blocks.GLASS_PANES).add(ModBlocks.TINTED_GLASS_PANE.get());

        this.tag(ModTags.Blocks.AMETHYST_ORES)
                .add(ModBlocks.AMETHYST_ORE.get(),
                        ModBlocks.DEEPSLATE_AMETHYST_ORE.get());

        this.tag(Tags.Blocks.ORES)
                .addTag(ModTags.Blocks.AMETHYST_ORES);

        this.tag(Tags.Blocks.ORES_IN_GROUND_STONE)
                .add(ModBlocks.AMETHYST_ORE.get());

        this.tag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE)
                .add(ModBlocks.DEEPSLATE_AMETHYST_ORE.get());

        for (RegistryObject<Block> quiltedConcrete : ModBlocks.QUILTED_CONCRETES) {
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(quiltedConcrete.get());
        }

        for (RegistryObject<Block> glazedConcrete : ModBlocks.GLAZED_CONCRETES) {
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(glazedConcrete.get());
        }

        for (RegistryObject<Block> antiblock : ModBlocks.ANTIBLOCKS) {
            this.tag(ModTags.Blocks.ANTIBLOCK).add(antiblock.get());
        }

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(ModTags.Blocks.AMETHYST_ORES)
                .add(ModBlocks.REDSTONE_LANTERN.get())
                .add(ModBlocks.NETHER_BRICK_FENCE_GATE.get(),
                        ModBlocks.CRACKED_RED_NETHER_BRICKS.get(),
                        ModBlocks.RED_NETHER_BRICK_FENCE.get(),
                        ModBlocks.RED_NETHER_BRICK_FENCE_GATE.get(),
                        ModBlocks.CHISELED_RED_NETHER_BRICKS.get(),
                        ModBlocks.BLUE_NETHER_BRICKS.get(),
                        ModBlocks.CRACKED_BLUE_NETHER_BRICKS.get(),
                        ModBlocks.BLUE_NETHER_BRICK_STAIRS.get(),
                        ModBlocks.BLUE_NETHER_BRICK_SLAB.get(),
                        ModBlocks.BLUE_NETHER_BRICK_WALL.get(),
                        ModBlocks.BLUE_NETHER_BRICK_FENCE.get(),
                        ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get(),
                        ModBlocks.CHISELED_BLUE_NETHER_BRICKS.get())
                .add(ModBlocks.SOUL_SANDSTONE.get(),
                        ModBlocks.SOUL_SANDSTONE_STAIRS.get(),
                        ModBlocks.SOUL_SANDSTONE_SLAB.get(),
                        ModBlocks.SOUL_SANDSTONE_WALL.get(),
                        ModBlocks.CHISELED_SOUL_SANDSTONE.get(),
                        ModBlocks.SMOOTH_SOUL_SANDSTONE.get(),
                        ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get(),
                        ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get(),
                        ModBlocks.CUT_SOUL_SANDSTONE.get(),
                        ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get());

        this.tag(Tags.Blocks.ORE_RATES_DENSE)
                .addTag(ModTags.Blocks.AMETHYST_ORES);

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(Tags.Blocks.BOOKSHELVES)
                .add(ModBlocks.SPRUCE_CRAFTING_TABLE.get(),
                        ModBlocks.BIRCH_CRAFTING_TABLE.get(),
                        ModBlocks.JUNGLE_CRAFTING_TABLE.get(),
                        ModBlocks.ACACIA_CRAFTING_TABLE.get(),
                        ModBlocks.DARK_OAK_CRAFTING_TABLE.get(),
                        ModBlocks.MANGROVE_CRAFTING_TABLE.get(),
                        ModBlocks.BAMBOO_CRAFTING_TABLE.get(),
                        ModBlocks.CHERRY_CRAFTING_TABLE.get(),
                        ModBlocks.CRIMSON_CRAFTING_TABLE.get(),
                        ModBlocks.WARPED_CRAFTING_TABLE.get(),
                        ModBlocks.OAK_MOSAIC.get(),
                        ModBlocks.SPRUCE_MOSAIC.get(),
                        ModBlocks.BIRCH_MOSAIC.get(),
                        ModBlocks.JUNGLE_MOSAIC.get(),
                        ModBlocks.ACACIA_MOSAIC.get(),
                        ModBlocks.DARK_OAK_MOSAIC.get(),
                        ModBlocks.MANGROVE_MOSAIC.get(),
                        ModBlocks.CHERRY_MOSAIC.get(),
                        ModBlocks.CRIMSON_MOSAIC.get(),
                        ModBlocks.WARPED_MOSAIC.get());

        this.tag(Tags.Blocks.BOOKSHELVES)
                .add(ModBlocks.SPRUCE_BOOKSHELF.get(),
                        ModBlocks.BIRCH_BOOKSHELF.get(),
                        ModBlocks.JUNGLE_BOOKSHELF.get(),
                        ModBlocks.ACACIA_BOOKSHELF.get(),
                        ModBlocks.DARK_OAK_BOOKSHELF.get(),
                        ModBlocks.MANGROVE_BOOKSHELF.get(),
                        ModBlocks.CHERRY_BOOKSHELF.get(),
                        ModBlocks.BAMBOO_BOOKSHELF.get(),
                        ModBlocks.CRIMSON_BOOKSHELF.get(),
                        ModBlocks.WARPED_BOOKSHELF.get());

        this.tag(BlockTags.WOOL)
                .add(ModBlocks.VIBRANT_RED_WOOL.get(),
                        ModBlocks.DULL_ORANGE_WOOL.get(),
                        ModBlocks.BRIGHT_YELLOW_WOOL.get(),
                        ModBlocks.CHARTREUSE_WOOL.get(),
                        ModBlocks.VIBRANT_GREEN_WOOL.get(),
                        ModBlocks.SPRING_GREEN_WOOL.get(),
                        ModBlocks.BRIGHT_CYAN_WOOL.get(),
                        ModBlocks.CAPRI_WOOL.get(),
                        ModBlocks.ULTRAMARINE_WOOL.get(),
                        ModBlocks.VIOLET_WOOL.get(),
                        ModBlocks.VIBRANT_PURPLE_WOOL.get(),
                        ModBlocks.BRIGHT_MAGENTA_WOOL.get(),
                        ModBlocks.ROSE_WOOL.get(),
                        ModBlocks.DARK_GRAY_WOOL.get(),
                        ModBlocks.SILVER_WOOL.get(),
                        ModBlocks.ALPHA_WHITE_WOOL.get());

        this.tag(BlockTags.WOOL_CARPETS)
                .add(ModBlocks.VIBRANT_RED_CARPET.get(),
                        ModBlocks.DULL_ORANGE_CARPET.get(),
                        ModBlocks.BRIGHT_YELLOW_CARPET.get(),
                        ModBlocks.CHARTREUSE_CARPET.get(),
                        ModBlocks.VIBRANT_GREEN_CARPET.get(),
                        ModBlocks.SPRING_GREEN_CARPET.get(),
                        ModBlocks.BRIGHT_CYAN_CARPET.get(),
                        ModBlocks.CAPRI_CARPET.get(),
                        ModBlocks.ULTRAMARINE_CARPET.get(),
                        ModBlocks.VIOLET_CARPET.get(),
                        ModBlocks.VIBRANT_PURPLE_CARPET.get(),
                        ModBlocks.BRIGHT_MAGENTA_CARPET.get(),
                        ModBlocks.ROSE_CARPET.get(),
                        ModBlocks.DARK_GRAY_CARPET.get(),
                        ModBlocks.SILVER_CARPET.get(),
                        ModBlocks.ALPHA_WHITE_CARPET.get());
    }
}
