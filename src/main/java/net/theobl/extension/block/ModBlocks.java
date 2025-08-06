package net.theobl.extension.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;
import net.theobl.extension.item.ModItems;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {
    // Create a Deferred Register to hold Blocks which will all be registered under the "extension" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Extension.MODID);

    // Creates a new Block with the id "extension:stone_wall", combining the namespace and path
    public static final DeferredBlock<Block> STONE_WALL = registerBlock("stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE_WALL).forceSolidOn()));
    public static final DeferredBlock<Block> POLISHED_GRANITE_WALL = registerBlock("polished_granite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE_WALL).forceSolidOn()));
    public static final DeferredBlock<Block> POLISHED_DIORITE_WALL = registerBlock("polished_diorite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE_WALL).forceSolidOn()));
    public static final DeferredBlock<Block> POLISHED_ANDESITE_WALL = registerBlock("polished_andesite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE_WALL).forceSolidOn()));
    public static final DeferredBlock<Block> PRISMARINE_BRICK_WALL = registerBlock("prismarine_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_WALL).forceSolidOn()));
    public static final DeferredBlock<Block> DARK_PRISMARINE_WALL = registerBlock("dark_prismarine_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_WALL).forceSolidOn()));
    public static final DeferredBlock<Block> PURPUR_WALL = registerBlock("purpur_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICK_WALL).forceSolidOn()));
    public static final DeferredBlock<Block> QUARTZ_WALL = registerBlock("quartz_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICK_WALL).forceSolidOn()));

    public static final DeferredBlock<Block> TINTED_GLASS_PANE = registerBlock("tinted_glass_pane",
            () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_STAINED_GLASS_PANE)));

    public static final DeferredBlock<Block> SMOOTH_STONE_STAIRS = registerBlock("smooth_stone_stairs",
            () -> new StairBlock(Blocks.SMOOTH_STONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS)));
    public static final DeferredBlock<Block> NETHERITE_STAIRS = registerFireResistantBlock("netherite_stairs",
            () -> new StairBlock(Blocks.NETHERITE_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)));

    public static final DeferredBlock<Block> POLISHED_STONE = registerBlock("polished_stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> POLISHED_STONE_STAIRS = registerBlock("polished_stone_stairs",
            () -> new StairBlock(ModBlocks.POLISHED_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS)));
    public static final DeferredBlock<Block> POLISHED_STONE_SLAB = registerBlock("polished_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
    public static final DeferredBlock<Block> POLISHED_STONE_WALL = registerBlock("polished_stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredBlock<Block> MOSSY_DEEPSLATE_BRICKS = registerBlock("mossy_deepslate_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS)));
    public static final DeferredBlock<Block> MOSSY_DEEPSLATE_BRICK_STAIRS = registerBlock("mossy_deepslate_brick_stairs",
            () -> new StairBlock(ModBlocks.MOSSY_DEEPSLATE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_STAIRS)));
    public static final DeferredBlock<Block> MOSSY_DEEPSLATE_BRICK_SLAB = registerBlock("mossy_deepslate_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_SLAB)));
    public static final DeferredBlock<Block> MOSSY_DEEPSLATE_BRICK_WALL = registerBlock("mossy_deepslate_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_WALL)));

    public static final DeferredBlock<Block> MOSSY_COBBLED_DEEPSLATE = registerBlock("mossy_cobbled_deepslate",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE)));
    public static final DeferredBlock<Block> MOSSY_COBBLED_DEEPSLATE_STAIRS = registerBlock("mossy_cobbled_deepslate_stairs",
            () -> new StairBlock(ModBlocks.MOSSY_COBBLED_DEEPSLATE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE_STAIRS)));
    public static final DeferredBlock<Block> MOSSY_COBBLED_DEEPSLATE_SLAB = registerBlock("mossy_cobbled_deepslate_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE_SLAB)));
    public static final DeferredBlock<Block> MOSSY_COBBLED_DEEPSLATE_WALL = registerBlock("mossy_cobbled_deepslate_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE_WALL)));

    public static final DeferredBlock<Block> SMOOTH_BASALT_STAIRS = registerBlock("smooth_basalt_stairs",
            () -> new StairBlock(Blocks.SMOOTH_BASALT.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_BASALT)));
    public static final DeferredBlock<Block> SMOOTH_BASALT_SLAB = registerBlock("smooth_basalt_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_BASALT)));

    public static final DeferredBlock<Block> QUARTZ_BRICK_STAIRS = registerBlock("quartz_brick_stairs",
            () -> new StairBlock(Blocks.SMOOTH_BASALT.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_STAIRS)));
    public static final DeferredBlock<Block> QUARTZ_BRICK_SLAB = registerBlock("quartz_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_SLAB)));
    public static final DeferredBlock<Block> QUARTZ_BRICK_WALL = registerBlock("quartz_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));


    public static final DeferredBlock<Block> NETHER_BRICK_TILES = registerBlock("nether_brick_tiles",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)));
    public static final DeferredBlock<Block> NETHER_BRICK_TILE_STAIRS = registerBlock("nether_brick_tile_stairs",
            () -> new StairBlock(NETHER_BRICK_TILES.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_STAIRS)));
    public static final DeferredBlock<Block> NETHER_BRICK_TILE_SLAB = registerBlock("nether_brick_tile_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_SLAB)));
    public static final DeferredBlock<Block> NETHER_BRICK_TILE_WALL = registerBlock("nether_brick_tile_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_WALL)));

    public static final DeferredBlock<Block> CRACKED_RED_NETHER_BRICKS = registerBlock("cracked_red_nether_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS)));
    public static final DeferredBlock<Block> RED_NETHER_BRICK_FENCE = registerBlock("red_nether_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_FENCE)));
    public static final DeferredBlock<Block> CHISELED_RED_NETHER_BRICKS = registerBlock("chiseled_red_nether_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS)));
    public static final DeferredBlock<Block> RED_NETHER_BRICK_TILES = registerBlock("red_nether_brick_tiles",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS)));
    public static final DeferredBlock<Block> RED_NETHER_BRICK_TILE_STAIRS = registerBlock("red_nether_brick_tile_stairs",
            () -> new StairBlock(RED_NETHER_BRICK_TILES.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICK_STAIRS)));
    public static final DeferredBlock<Block> RED_NETHER_BRICK_TILE_SLAB = registerBlock("red_nether_brick_tile_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICK_SLAB)));
    public static final DeferredBlock<Block> RED_NETHER_BRICK_TILE_WALL = registerBlock("red_nether_brick_tile_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICK_WALL)));

    public static final DeferredBlock<Block> BLUE_NETHER_WART = BLOCKS.register("blue_nether_wart",
            () -> new BlueNetherWartBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_WART).mapColor(MapColor.WARPED_WART_BLOCK)));

    public static final DeferredBlock<Block> BLUE_NETHER_BRICKS = registerBlock("blue_nether_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)));
    public static final DeferredBlock<Block> CRACKED_BLUE_NETHER_BRICKS = registerBlock("cracked_blue_nether_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_NETHER_BRICKS)));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_STAIRS = registerBlock("blue_nether_brick_stairs",
            () -> new StairBlock(ModBlocks.BLUE_NETHER_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_STAIRS)));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_SLAB = registerBlock("blue_nether_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_SLAB)));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_WALL = registerBlock("blue_nether_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_WALL)));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_FENCE = registerBlock("blue_nether_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_FENCE)));
    public static final DeferredBlock<Block> CHISELED_BLUE_NETHER_BRICKS = registerBlock("chiseled_blue_nether_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_NETHER_BRICKS)));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_TILES = registerBlock("blue_nether_brick_tiles",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_TILE_STAIRS = registerBlock("blue_nether_brick_tile_stairs",
            () -> new StairBlock(BLUE_NETHER_BRICK_TILES.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_TILE_SLAB = registerBlock("blue_nether_brick_tile_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_TILE_WALL = registerBlock("blue_nether_brick_tile_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)));

    public static final DeferredBlock<Block> SOUL_SANDSTONE = registerBlock("soul_sandstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).mapColor(MapColor.COLOR_BROWN)));
    public static final DeferredBlock<Block> SOUL_SANDSTONE_STAIRS = registerBlock("soul_sandstone_stairs",
            () -> new StairBlock(ModBlocks.SOUL_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ModBlocks.SOUL_SANDSTONE.get())));
    public static final DeferredBlock<Block> SOUL_SANDSTONE_SLAB = registerBlock("soul_sandstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.SOUL_SANDSTONE.get())));
    public static final DeferredBlock<Block> SOUL_SANDSTONE_WALL = registerBlock("soul_sandstone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.SOUL_SANDSTONE.get())));
    public static final DeferredBlock<Block> CHISELED_SOUL_SANDSTONE = registerBlock("chiseled_soul_sandstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(ModBlocks.SOUL_SANDSTONE.get())));
    public static final DeferredBlock<Block> SMOOTH_SOUL_SANDSTONE = registerBlock("smooth_soul_sandstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(ModBlocks.SOUL_SANDSTONE.get())));
    public static final DeferredBlock<Block> SMOOTH_SOUL_SANDSTONE_STAIRS = registerBlock("smooth_soul_sandstone_stairs",
            () -> new StairBlock(ModBlocks.SMOOTH_SOUL_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ModBlocks.SOUL_SANDSTONE.get())));
    public static final DeferredBlock<Block> SMOOTH_SOUL_SANDSTONE_SLAB = registerBlock("smooth_soul_sandstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.SOUL_SANDSTONE.get())));
    public static final DeferredBlock<Block> CUT_SOUL_SANDSTONE = registerBlock("cut_soul_sandstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(ModBlocks.SOUL_SANDSTONE.get())));
    public static final DeferredBlock<Block> CUT_SOUL_SANDSTONE_SLAB = registerBlock("cut_soul_sandstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.SOUL_SANDSTONE.get())));

    public static final DeferredBlock<Block> SOUL_O_LANTERN = registerBlock("soul_o_lantern",
            () -> new CarvedPumpkinBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JACK_O_LANTERN).lightLevel(s -> 10)));
    public static final DeferredBlock<Block> REDSTONE_O_LANTERN = registerBlock("redstone_o_lantern",
            () -> new RedstoneCarvedPumpkinBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JACK_O_LANTERN).lightLevel(s -> 7)));

    public static final DeferredBlock<Block> REDSTONE_LANTERN = registerBlock("redstone_lantern",
            () -> new RedstoneLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(litBlockEmission(7))));
    public static final DeferredBlock<Block> REDSTONE_CAMPFIRE = registerBlock("redstone_campfire",
            () -> new RedstoneCampfireBlock(false, 1,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE).lightLevel(litBlockEmission(7))));

    public static final DeferredBlock<Block> INVERTED_REDSTONE_TORCH = BLOCKS.register("inverted_redstone_torch",
            () -> new InvertedRedstoneTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_TORCH)));
    public static final DeferredBlock<Block> INVERTED_REDSTONE_WALL_TORCH = BLOCKS.register("inverted_redstone_wall_torch",
            () -> new InvertedRedstoneWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_TORCH)));

    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return state -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> deferredBlock = BLOCKS.register(name, block);
        ModItems.ITEMS.registerSimpleBlockItem(name, deferredBlock);
        return deferredBlock;
    }

    public static <T extends Block> DeferredBlock<T> registerFireResistantBlock(String name, Supplier<T> block){
        DeferredBlock<T> deferredBlock = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(deferredBlock.get(), new Item.Properties().fireResistant()));
        return deferredBlock;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
