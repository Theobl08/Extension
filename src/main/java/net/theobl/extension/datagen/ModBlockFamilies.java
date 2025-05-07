package net.theobl.extension.datagen;

import com.google.common.collect.Maps;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.theobl.extension.block.ModBlocks;

import java.util.Map;
import java.util.stream.Stream;

public class ModBlockFamilies {
    private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();

    public static final BlockFamily POLISHED_STONE = familyBuilder(ModBlocks.POLISHED_STONE.get())
            .wall(ModBlocks.POLISHED_STONE_WALL.get())
            .stairs(ModBlocks.POLISHED_STONE_STAIRS.get())
            .slab(ModBlocks.POLISHED_STONE_SLAB.get())
            .getFamily();

    public static final BlockFamily MOSSY_STONE_BRICKS = familyBuilder(ModBlocks.MOSSY_DEEPSLATE_BRICKS.get())
            .wall(ModBlocks.MOSSY_DEEPSLATE_BRICK_WALL.get())
            .stairs(ModBlocks.MOSSY_DEEPSLATE_BRICK_STAIRS.get())
            .slab(ModBlocks.MOSSY_DEEPSLATE_BRICK_SLAB.get())
            .getFamily();

    public static final BlockFamily SMOOTH_BASALT = familyBuilder(Blocks.SMOOTH_BASALT)
            .stairs(ModBlocks.SMOOTH_BASALT_STAIRS.get())
            .slab(ModBlocks.SMOOTH_BASALT_SLAB.get())
            .getFamily();

    public static final BlockFamily QUARTZ_BRICKS = familyBuilder(Blocks.QUARTZ_BRICKS)
            .wall(ModBlocks.QUARTZ_BRICK_WALL.get())
            .stairs(ModBlocks.QUARTZ_BRICK_STAIRS.get())
            .slab(ModBlocks.QUARTZ_BRICK_SLAB.get())
            .getFamily();

    public static final BlockFamily NETHER_BRICK_TILES = familyBuilder(ModBlocks.NETHER_BRICK_TILES.get())
            .slab(ModBlocks.NETHER_BRICK_TILE_SLAB.get())
            .stairs(ModBlocks.NETHER_BRICK_TILE_STAIRS.get())
            .wall(ModBlocks.NETHER_BRICK_TILE_WALL.get())
            .getFamily();

    public static final BlockFamily RED_NETHER_BRICK_TILES = familyBuilder(ModBlocks.RED_NETHER_BRICK_TILES.get())
            .slab(ModBlocks.RED_NETHER_BRICK_TILE_SLAB.get())
            .stairs(ModBlocks.RED_NETHER_BRICK_TILE_STAIRS.get())
            .wall(ModBlocks.RED_NETHER_BRICK_TILE_WALL.get())
            .getFamily();

    public static final BlockFamily BLUE_NETHER_BRICKS = familyBuilder(ModBlocks.BLUE_NETHER_BRICKS.get())
            //.fence(ModBlocks.BLUE_NETHER_BRICK_FENCE.get())
            .wall(ModBlocks.BLUE_NETHER_BRICK_WALL.get())
            .stairs(ModBlocks.BLUE_NETHER_BRICK_STAIRS.get())
            .slab(ModBlocks.BLUE_NETHER_BRICK_SLAB.get())
            .chiseled(ModBlocks.CHISELED_BLUE_NETHER_BRICKS.get())
            .cracked(ModBlocks.CRACKED_BLUE_NETHER_BRICKS.get())
            .getFamily();

    public static final BlockFamily BLUE_NETHER_BRICK_TILES = familyBuilder(ModBlocks.BLUE_NETHER_BRICK_TILES.get())
            .slab(ModBlocks.BLUE_NETHER_BRICK_TILE_SLAB.get())
            .stairs(ModBlocks.BLUE_NETHER_BRICK_TILE_STAIRS.get())
            .wall(ModBlocks.BLUE_NETHER_BRICK_TILE_WALL.get())
            .getFamily();

    public static final BlockFamily SOUL_SANDSTONE = familyBuilder(ModBlocks.SOUL_SANDSTONE.get())
            .wall(ModBlocks.SOUL_SANDSTONE_WALL.get())
            .stairs(ModBlocks.SOUL_SANDSTONE_STAIRS.get())
            .slab(ModBlocks.SOUL_SANDSTONE_SLAB.get())
            .chiseled(ModBlocks.CHISELED_SOUL_SANDSTONE.get())
            .cut(ModBlocks.CUT_SOUL_SANDSTONE.get())
            .dontGenerateRecipe()
            .getFamily();
    public static final BlockFamily CUT_SOUL_SANDSTONE = familyBuilder(ModBlocks.CUT_SOUL_SANDSTONE.get())
            .slab(ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get())
            .getFamily();
    public static final BlockFamily SMOOTH_SOUL_SANDSTONE = familyBuilder(ModBlocks.SMOOTH_SOUL_SANDSTONE.get())
            .slab(ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get())
            .stairs(ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get())
            .getFamily();

    private static BlockFamily.Builder familyBuilder(Block baseBlock) {
        BlockFamily.Builder blockfamily$builder = new BlockFamily.Builder(baseBlock);
        BlockFamily blockfamily = MAP.put(baseBlock, blockfamily$builder.getFamily());
        if (blockfamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + BuiltInRegistries.BLOCK.getKey(baseBlock));
        } else {
            return blockfamily$builder;
        }
    }
    public static Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }
}
