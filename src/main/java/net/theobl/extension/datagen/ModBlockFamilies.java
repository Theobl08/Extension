package net.theobl.extension.datagen;

import com.google.common.collect.Maps;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.theobl.extension.block.ModBlocks;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import java.util.stream.Stream;

public class ModBlockFamilies {
    private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();
    public static final BlockFamily POTATO_PLANKS = familyBuilder(ModBlocks.POTATO_PLANKS)
            .log(ModBlocks.POTATO_STEM)
            .strippedLog(ModBlocks.POTATO_STEM)
            .button(ModBlocks.POTATO_BUTTON)
            .fence(ModBlocks.POTATO_FENCE)
            .fenceGate(ModBlocks.POTATO_FENCE_GATE)
            .hangingSign(ModBlocks.POTATO_HANGING_SIGN, ModBlocks.POTATO_WALL_HANGING_SIGN)
            .pressurePlate(ModBlocks.POTATO_PRESSURE_PLATE)
            .sign(ModBlocks.POTATO_SIGN, ModBlocks.POTATO_WALL_SIGN)
            .slab(ModBlocks.POTATO_SLAB)
            .stairs(ModBlocks.POTATO_STAIRS)
            .door(ModBlocks.POTATO_DOOR)
            .trapdoor(ModBlocks.POTATO_TRAPDOOR)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

    public static final BlockFamily POLISHED_PACKED_MUD = familyBuilder(ModBlocks.POLISHED_PACKED_MUD)
            .wall(ModBlocks.POLISHED_PACKED_MUD_WALL)
            .stairs(ModBlocks.POLISHED_PACKED_MUD_STAIRS)
            .slab(ModBlocks.POLISHED_PACKED_MUD_SLAB)
            .generateStonecutterRecipe()
            .getFamily();

    public static final BlockFamily POLISHED_STONE = familyBuilder(ModBlocks.POLISHED_STONE)
            .wall(ModBlocks.POLISHED_STONE_WALL)
            .stairs(ModBlocks.POLISHED_STONE_STAIRS)
            .slab(ModBlocks.POLISHED_STONE_SLAB)
            .generateStonecutterRecipe()
            .getFamily();

    public static final BlockFamily GRANITE_BRICKS = familyBuilder(ModBlocks.GRANITE_BRICKS)
            .wall(ModBlocks.GRANITE_BRICK_WALL)
            .stairs(ModBlocks.GRANITE_BRICK_STAIRS)
            .slab(ModBlocks.GRANITE_BRICK_SLAB)
            .generateStonecutterRecipe()
            .getFamily();

    public static final BlockFamily DIORITE_BRICKS = familyBuilder(ModBlocks.DIORITE_BRICKS)
            .wall(ModBlocks.DIORITE_BRICK_WALL)
            .stairs(ModBlocks.DIORITE_BRICK_STAIRS)
            .slab(ModBlocks.DIORITE_BRICK_SLAB)
            .generateStonecutterRecipe()
            .getFamily();

    public static final BlockFamily ANDESITE_BRICKS = familyBuilder(ModBlocks.ANDESITE_BRICKS)
            .wall(ModBlocks.ANDESITE_BRICK_WALL)
            .stairs(ModBlocks.ANDESITE_BRICK_STAIRS)
            .slab(ModBlocks.ANDESITE_BRICK_SLAB)
            .generateStonecutterRecipe()
            .getFamily();

    public static final BlockFamily POLISHED_END_STONE = familyBuilder(ModBlocks.POLISHED_END_STONE)
            .wall(ModBlocks.POLISHED_END_STONE_WALL)
            .stairs(ModBlocks.POLISHED_END_STONE_STAIRS)
            .slab(ModBlocks.POLISHED_END_STONE_SLAB)
            .generateStonecutterRecipe()
            .getFamily();

    public static final BlockFamily MOSSY_COBBLED_DEEPSLATE = familyBuilder(ModBlocks.MOSSY_COBBLED_DEEPSLATE)
            .wall(ModBlocks.MOSSY_COBBLED_DEEPSLATE_WALL)
            .stairs(ModBlocks.MOSSY_COBBLED_DEEPSLATE_STAIRS)
            .slab(ModBlocks.MOSSY_COBBLED_DEEPSLATE_SLAB)
            .generateStonecutterRecipe()
            .getFamily();

    public static final BlockFamily MOSSY_DEEPSLATE_BRICKS = familyBuilder(ModBlocks.MOSSY_DEEPSLATE_BRICKS)
            .wall(ModBlocks.MOSSY_DEEPSLATE_BRICK_WALL)
            .stairs(ModBlocks.MOSSY_DEEPSLATE_BRICK_STAIRS)
            .slab(ModBlocks.MOSSY_DEEPSLATE_BRICK_SLAB)
            .generateStonecutterRecipe()
            .getFamily();

    public static final BlockFamily SMOOTH_BASALT = familyBuilder(Blocks.SMOOTH_BASALT)
            .stairs(ModBlocks.SMOOTH_BASALT_STAIRS)
            .slab(ModBlocks.SMOOTH_BASALT_SLAB)
            .generateStonecutterRecipe()
            .getFamily();

    public static final BlockFamily QUARTZ_BRICKS = familyBuilder(Blocks.QUARTZ_BRICKS)
            .wall(ModBlocks.QUARTZ_BRICK_WALL)
            .stairs(ModBlocks.QUARTZ_BRICK_STAIRS)
            .slab(ModBlocks.QUARTZ_BRICK_SLAB)
            .generateStonecutterRecipe()
            .getFamily();

    public static final BlockFamily NETHER_BRICKS = familyBuilder(Blocks.NETHER_BRICKS)
            .tiles(ModBlocks.NETHER_BRICK_TILES)
            .pillar(ModBlocks.NETHER_BRICK_PILLAR)
            .generateStonecutterRecipe()
            .getFamily();

    public static final BlockFamily NETHER_BRICK_TILES = familyBuilder(ModBlocks.NETHER_BRICK_TILES)
            .slab(ModBlocks.NETHER_BRICK_TILE_SLAB)
            .stairs(ModBlocks.NETHER_BRICK_TILE_STAIRS)
            .wall(ModBlocks.NETHER_BRICK_TILE_WALL)
            .generateStonecutterRecipe()
            .getFamily();

    public static final BlockFamily RED_NETHER_BRICKS = familyBuilder(Blocks.RED_NETHER_BRICKS)
            .tiles(ModBlocks.RED_NETHER_BRICK_TILES)
            .pillar(ModBlocks.RED_NETHER_BRICK_PILLAR)
            .generateStonecutterRecipe()
            .getFamily();

    public static final BlockFamily RED_NETHER_BRICK_TILES = familyBuilder(ModBlocks.RED_NETHER_BRICK_TILES)
            .slab(ModBlocks.RED_NETHER_BRICK_TILE_SLAB)
            .stairs(ModBlocks.RED_NETHER_BRICK_TILE_STAIRS)
            .wall(ModBlocks.RED_NETHER_BRICK_TILE_WALL)
            .generateStonecutterRecipe()
            .getFamily();

    public static final BlockFamily BLUE_NETHER_BRICKS = familyBuilder(ModBlocks.BLUE_NETHER_BRICKS)
            //.fence(ModBlocks.BLUE_NETHER_BRICK_FENCE)
            .wall(ModBlocks.BLUE_NETHER_BRICK_WALL)
            .stairs(ModBlocks.BLUE_NETHER_BRICK_STAIRS)
            .slab(ModBlocks.BLUE_NETHER_BRICK_SLAB)
            .chiseled(ModBlocks.CHISELED_BLUE_NETHER_BRICKS)
            .cracked(ModBlocks.CRACKED_BLUE_NETHER_BRICKS)
            .tiles(ModBlocks.BLUE_NETHER_BRICK_TILES)
            .pillar(ModBlocks.BLUE_NETHER_BRICK_PILLAR)
            .generateStonecutterRecipe()
            .getFamily();

    public static final BlockFamily BLUE_NETHER_BRICK_TILES = familyBuilder(ModBlocks.BLUE_NETHER_BRICK_TILES)
            .slab(ModBlocks.BLUE_NETHER_BRICK_TILE_SLAB)
            .stairs(ModBlocks.BLUE_NETHER_BRICK_TILE_STAIRS)
            .wall(ModBlocks.BLUE_NETHER_BRICK_TILE_WALL)
            .generateStonecutterRecipe()
            .getFamily();

    public static final BlockFamily POLISHED_PRISMARINE = familyBuilder(ModBlocks.POLISHED_PRISMARINE)
            .wall(ModBlocks.POLISHED_PRISMARINE_WALL)
            .stairs(ModBlocks.POLISHED_PRISMARINE_STAIRS)
            .slab(ModBlocks.POLISHED_PRISMARINE_SLAB)
            .generateStonecutterRecipe()
            .getFamily();

    public static final BlockFamily SOUL_SANDSTONE = familyBuilder(ModBlocks.SOUL_SANDSTONE)
            .wall(ModBlocks.SOUL_SANDSTONE_WALL)
            .stairs(ModBlocks.SOUL_SANDSTONE_STAIRS)
            .slab(ModBlocks.SOUL_SANDSTONE_SLAB)
            .chiseled(ModBlocks.CHISELED_SOUL_SANDSTONE)
            .cut(ModBlocks.CUT_SOUL_SANDSTONE)
            .dontGenerateCraftingRecipe()
            .generateStonecutterRecipe()
            .getFamily();
    public static final BlockFamily CUT_SOUL_SANDSTONE = familyBuilder(ModBlocks.CUT_SOUL_SANDSTONE)
            .slab(ModBlocks.CUT_SOUL_SANDSTONE_SLAB)
            .generateStonecutterRecipe()
            .getFamily();
    public static final BlockFamily SMOOTH_SOUL_SANDSTONE = familyBuilder(ModBlocks.SMOOTH_SOUL_SANDSTONE)
            .slab(ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB)
            .stairs(ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS)
            .generateStonecutterRecipe()
            .getFamily();

    private static Builder familyBuilder(Block baseBlock) {
        Builder blockfamily$builder = new Builder(baseBlock);
        BlockFamily blockfamily = MAP.put(baseBlock, blockfamily$builder.getFamily());
        if (blockfamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + BuiltInRegistries.BLOCK.getKey(baseBlock));
        } else {
            return blockfamily$builder;
        }
    }

    private static Builder familyBuilder(DeferredBlock<Block> baseBlock) {
        return familyBuilder(baseBlock.get());
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }

    public static @Nullable BlockFamily getFamily(Block base) {
        return MAP.get(base);
    }

    @NullMarked
    public static class Builder extends BlockFamily.Builder {
        public Builder(Block baseBlock) {
            super(baseBlock);
        }

        public Builder button(DeferredBlock<Block> button) {
            super.button(button.get());
            return this;
        }

        public Builder chiseled(DeferredBlock<Block> chiseled) {
            super.chiseled(chiseled.get());
            return this;
        }

        public Builder cracked(DeferredBlock<Block> cracked) {
            super.cracked(cracked.get());
            return this;
        }

        public Builder tiles(DeferredBlock<Block> tiles) {
            super.tiles(tiles.get());
            return this;
        }

        public Builder pillar(DeferredBlock<Block> pillar) {
            super.pillar(pillar.get());
            return this;
        }

        public Builder cut(DeferredBlock<Block> cut) {
            super.cut(cut.get());
            return this;
        }

        public Builder door(DeferredBlock<Block> door) {
            super.door(door.get());
            return this;
        }

        public Builder fence(DeferredBlock<Block> fence) {
            super.fence(fence.get());
            return this;
        }

        public Builder fenceGate(DeferredBlock<Block> fenceGate) {
            super.fenceGate(fenceGate.get());
            return this;
        }

        public Builder sign(DeferredBlock<Block> sign, DeferredBlock<Block> wallSign) {
            super.sign(sign.get(), wallSign.get());
            return this;
        }

        public Builder hangingSign(DeferredBlock<Block> sign, DeferredBlock<Block> wallSign) {
            super.hangingSign(sign.get(), wallSign.get());
            return this;
        }

        public Builder log(DeferredBlock<Block> log) {
            super.log(log.get());
            return this;
        }

        public Builder strippedLog(DeferredBlock<Block> strippedLog) {
            super.strippedLog(strippedLog.get());
            return this;
        }

        public Builder slab(DeferredBlock<Block> slab) {
            super.slab(slab.get());
            return this;
        }

        public Builder stairs(DeferredBlock<Block> stairs) {
            super.stairs(stairs.get());
            return this;
        }

        public Builder pressurePlate(DeferredBlock<Block> pressurePlate) {
            super.pressurePlate(pressurePlate.get());
            return this;
        }

        public Builder trapdoor(DeferredBlock<Block> trapdoor) {
            super.trapdoor(trapdoor.get());
            return this;
        }

        public Builder wall(DeferredBlock<Block> wall) {
            super.wall(wall.get());
            return this;
        }

        @Override
        public Builder dontGenerateCraftingRecipe() {
            super.dontGenerateCraftingRecipe();
            return this;
        }

        @Override
        public Builder generateStonecutterRecipe() {
            super.generateStonecutterRecipe();
            return this;
        }

        @Override
        public Builder recipeGroupPrefix(String recipeGroupPrefix) {
            super.recipeGroupPrefix(recipeGroupPrefix);
            return this;
        }

        @Override
        public Builder recipeUnlockedBy(String recipeUnlockedBy) {
            super.recipeUnlockedBy(recipeUnlockedBy);
            return this;
        }
    }
}
