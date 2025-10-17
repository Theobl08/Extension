package net.theobl.extension.block;

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

import java.util.function.Function;
import java.util.function.ToIntFunction;

public class ModBlocks {
    // Create a Deferred Register to hold Blocks which will all be registered under the "extension" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Extension.MODID);

    // Creates a new Block with the id "extension:stone_wall", combining the namespace and path
    public static final DeferredBlock<Block> STONE_WALL = registerBlock("stone_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE_WALL).forceSolidOn());
    public static final DeferredBlock<Block> POLISHED_GRANITE_WALL = registerBlock("polished_granite_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE_WALL).forceSolidOn());
    public static final DeferredBlock<Block> POLISHED_DIORITE_WALL = registerBlock("polished_diorite_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE_WALL).forceSolidOn());
    public static final DeferredBlock<Block> POLISHED_ANDESITE_WALL = registerBlock("polished_andesite_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE_WALL).forceSolidOn());
    public static final DeferredBlock<Block> PRISMARINE_BRICK_WALL = registerBlock("prismarine_brick_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_WALL).forceSolidOn());
    public static final DeferredBlock<Block> DARK_PRISMARINE_WALL = registerBlock("dark_prismarine_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_WALL).forceSolidOn());
    public static final DeferredBlock<Block> PURPUR_WALL = registerBlock("purpur_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICK_WALL).forceSolidOn());
    public static final DeferredBlock<Block> QUARTZ_WALL = registerBlock("quartz_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICK_WALL).forceSolidOn());

    public static final DeferredBlock<Block> CHISELED_BRICKS = registerBlock("chiseled_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS));
    public static final DeferredBlock<Block> CHISELED_MUD_BRICKS = registerBlock("chiseled_mud_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICKS));
    public static final DeferredBlock<Block> CHISELED_PRISMARINE = registerBlock("chiseled_prismarine",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICKS));
    public static final DeferredBlock<Block> CHISELED_END_STONE_BRICKS = registerBlock("chiseled_end_stone_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICKS));

    public static final DeferredBlock<Block> TINTED_GLASS_PANE = registerBlock("tinted_glass_pane",
            IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_STAINED_GLASS_PANE));

    public static final DeferredBlock<Block> SMOOTH_STONE_STAIRS = registerBlock("smooth_stone_stairs",
            properties -> new StairBlock(Blocks.SMOOTH_STONE.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> NETHERITE_STAIRS = registerFireResistantBlock("netherite_stairs",
            properties -> new StairBlock(Blocks.NETHERITE_BLOCK.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK));

    public static final DeferredBlock<Block> POLISHED_STONE = registerBlock("polished_stone",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> POLISHED_STONE_STAIRS = registerBlock("polished_stone_stairs",
            properties -> new StairBlock(ModBlocks.POLISHED_STONE.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> POLISHED_STONE_SLAB = registerBlock("polished_stone_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> POLISHED_STONE_WALL = registerBlock("polished_stone_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));

    public static final DeferredBlock<Block> CHISELED_GRANITE = registerBlock("chiseled_granite",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_BRICKS = registerBlock("granite_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_BRICK_STAIRS = registerBlock("granite_brick_stairs",
            properties -> new StairBlock(ModBlocks.GRANITE_BRICKS.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE_STAIRS));
    public static final DeferredBlock<Block> GRANITE_BRICK_SLAB = registerBlock("granite_brick_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE_SLAB));
    public static final DeferredBlock<Block> GRANITE_BRICK_WALL = registerBlock("granite_brick_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE_WALL));

    public static final DeferredBlock<Block> CHISELED_DIORITE = registerBlock("chiseled_diorite",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE));
    public static final DeferredBlock<Block> DIORITE_BRICKS = registerBlock("diorite_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE));
    public static final DeferredBlock<Block> DIORITE_BRICK_STAIRS = registerBlock("diorite_brick_stairs",
            properties -> new StairBlock(ModBlocks.DIORITE_BRICKS.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE_STAIRS));
    public static final DeferredBlock<Block> DIORITE_BRICK_SLAB = registerBlock("diorite_brick_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE_SLAB));
    public static final DeferredBlock<Block> DIORITE_BRICK_WALL = registerBlock("diorite_brick_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE_WALL));

    public static final DeferredBlock<Block> CHISELED_ANDESITE = registerBlock("chiseled_andesite",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE));
    public static final DeferredBlock<Block> ANDESITE_BRICKS = registerBlock("andesite_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE));
    public static final DeferredBlock<Block> ANDESITE_BRICK_STAIRS = registerBlock("andesite_brick_stairs",
            properties -> new StairBlock(ModBlocks.ANDESITE_BRICKS.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE_STAIRS));
    public static final DeferredBlock<Block> ANDESITE_BRICK_SLAB = registerBlock("andesite_brick_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE_SLAB));
    public static final DeferredBlock<Block> ANDESITE_BRICK_WALL = registerBlock("andesite_brick_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE_WALL));

    public static final DeferredBlock<Block> MOSSY_DEEPSLATE_BRICKS = registerBlock("mossy_deepslate_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS));
    public static final DeferredBlock<Block> MOSSY_DEEPSLATE_BRICK_STAIRS = registerBlock("mossy_deepslate_brick_stairs",
            properties -> new StairBlock(ModBlocks.MOSSY_DEEPSLATE_BRICKS.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_STAIRS));
    public static final DeferredBlock<Block> MOSSY_DEEPSLATE_BRICK_SLAB = registerBlock("mossy_deepslate_brick_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_SLAB));
    public static final DeferredBlock<Block> MOSSY_DEEPSLATE_BRICK_WALL = registerBlock("mossy_deepslate_brick_wall",
            WallBlock::new,BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_WALL));

    public static final DeferredBlock<Block> MOSSY_COBBLED_DEEPSLATE = registerBlock("mossy_cobbled_deepslate",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> MOSSY_COBBLED_DEEPSLATE_STAIRS = registerBlock("mossy_cobbled_deepslate_stairs",
            properties -> new StairBlock(ModBlocks.MOSSY_COBBLED_DEEPSLATE.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE_STAIRS));
    public static final DeferredBlock<Block> MOSSY_COBBLED_DEEPSLATE_SLAB = registerBlock("mossy_cobbled_deepslate_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE_SLAB));
    public static final DeferredBlock<Block> MOSSY_COBBLED_DEEPSLATE_WALL = registerBlock("mossy_cobbled_deepslate_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE_WALL));

    public static final DeferredBlock<Block> SMOOTH_BASALT_STAIRS = registerBlock("smooth_basalt_stairs",
            properties -> new StairBlock(Blocks.SMOOTH_BASALT.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_BASALT));
    public static final DeferredBlock<Block> SMOOTH_BASALT_SLAB = registerBlock("smooth_basalt_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_BASALT));

    public static final DeferredBlock<Block> QUARTZ_BRICK_STAIRS = registerBlock("quartz_brick_stairs",
            properties -> new StairBlock(Blocks.SMOOTH_BASALT.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_STAIRS));
    public static final DeferredBlock<Block> QUARTZ_BRICK_SLAB = registerBlock("quartz_brick_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_SLAB));
    public static final DeferredBlock<Block> QUARTZ_BRICK_WALL = registerBlock("quartz_brick_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK));


    public static final DeferredBlock<Block> NETHER_BRICK_TILES = registerBlock("nether_brick_tiles",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS));
    public static final DeferredBlock<Block> NETHER_BRICK_TILE_STAIRS = registerBlock("nether_brick_tile_stairs",
            properties -> new StairBlock(NETHER_BRICK_TILES.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_STAIRS));
    public static final DeferredBlock<Block> NETHER_BRICK_TILE_SLAB = registerBlock("nether_brick_tile_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_SLAB));
    public static final DeferredBlock<Block> NETHER_BRICK_TILE_WALL = registerBlock("nether_brick_tile_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_WALL));

    public static final DeferredBlock<Block> CRACKED_RED_NETHER_BRICKS = registerBlock("cracked_red_nether_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS));
    public static final DeferredBlock<Block> RED_NETHER_BRICK_FENCE = registerBlock("red_nether_brick_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_FENCE));
    public static final DeferredBlock<Block> CHISELED_RED_NETHER_BRICKS = registerBlock("chiseled_red_nether_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS));
    public static final DeferredBlock<Block> RED_NETHER_BRICK_TILES = registerBlock("red_nether_brick_tiles",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS));
    public static final DeferredBlock<Block> RED_NETHER_BRICK_TILE_STAIRS = registerBlock("red_nether_brick_tile_stairs",
            properties -> new StairBlock(RED_NETHER_BRICK_TILES.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICK_STAIRS));
    public static final DeferredBlock<Block> RED_NETHER_BRICK_TILE_SLAB = registerBlock("red_nether_brick_tile_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICK_SLAB));
    public static final DeferredBlock<Block> RED_NETHER_BRICK_TILE_WALL = registerBlock("red_nether_brick_tile_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICK_WALL));

    public static final DeferredBlock<Block> BLUE_NETHER_WART = BLOCKS.registerBlock("blue_nether_wart",
            BlueNetherWartBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_WART).mapColor(MapColor.WARPED_WART_BLOCK));

    public static final DeferredBlock<Block> BLUE_NETHER_BRICKS = registerBlock("blue_nether_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS));
    public static final DeferredBlock<Block> CRACKED_BLUE_NETHER_BRICKS = registerBlock("cracked_blue_nether_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_NETHER_BRICKS));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_STAIRS = registerBlock("blue_nether_brick_stairs",
            properties -> new StairBlock(ModBlocks.BLUE_NETHER_BRICKS.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_STAIRS));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_SLAB = registerBlock("blue_nether_brick_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_SLAB));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_WALL = registerBlock("blue_nether_brick_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_WALL));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_FENCE = registerBlock("blue_nether_brick_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_FENCE));
    public static final DeferredBlock<Block> CHISELED_BLUE_NETHER_BRICKS = registerBlock("chiseled_blue_nether_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_NETHER_BRICKS));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_TILES = registerBlock("blue_nether_brick_tiles",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_TILE_STAIRS = registerBlock("blue_nether_brick_tile_stairs",
            properties -> new StairBlock(BLUE_NETHER_BRICK_TILES.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_TILE_SLAB = registerBlock("blue_nether_brick_tile_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_TILE_WALL = registerBlock("blue_nether_brick_tile_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS));

    public static final DeferredBlock<Block> SOUL_SANDSTONE = registerBlock("soul_sandstone",
            Block::new, soulSandstoneProperties());
    public static final DeferredBlock<Block> SOUL_SANDSTONE_STAIRS = registerBlock("soul_sandstone_stairs",
            properties -> new StairBlock(ModBlocks.SOUL_SANDSTONE.get().defaultBlockState(), properties), soulSandstoneProperties());
    public static final DeferredBlock<Block> SOUL_SANDSTONE_SLAB = registerBlock("soul_sandstone_slab",
            SlabBlock::new, soulSandstoneProperties());
    public static final DeferredBlock<Block> SOUL_SANDSTONE_WALL = registerBlock("soul_sandstone_wall",
            WallBlock::new, soulSandstoneProperties());
    public static final DeferredBlock<Block> CHISELED_SOUL_SANDSTONE = registerBlock("chiseled_soul_sandstone",
            Block::new, soulSandstoneProperties());
    public static final DeferredBlock<Block> SMOOTH_SOUL_SANDSTONE = registerBlock("smooth_soul_sandstone",
            Block::new, soulSandstoneProperties());
    public static final DeferredBlock<Block> SMOOTH_SOUL_SANDSTONE_STAIRS = registerBlock("smooth_soul_sandstone_stairs",
            properties -> new StairBlock(SMOOTH_SOUL_SANDSTONE.get().defaultBlockState(), properties), soulSandstoneProperties());
    public static final DeferredBlock<Block> SMOOTH_SOUL_SANDSTONE_SLAB = registerBlock("smooth_soul_sandstone_slab",
            SlabBlock::new, soulSandstoneProperties());
    public static final DeferredBlock<Block> CUT_SOUL_SANDSTONE = registerBlock("cut_soul_sandstone",
            Block::new, soulSandstoneProperties());
    public static final DeferredBlock<Block> CUT_SOUL_SANDSTONE_SLAB = registerBlock("cut_soul_sandstone_slab",
            SlabBlock::new, soulSandstoneProperties());

    public static final DeferredBlock<Block> SOUL_O_LANTERN = registerBlock("soul_o_lantern",
            CarvedPumpkinBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JACK_O_LANTERN).lightLevel(s -> 10));
    public static final DeferredBlock<Block> REDSTONE_O_LANTERN = registerBlock("redstone_o_lantern",
            RedstoneCarvedPumpkinBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JACK_O_LANTERN).lightLevel(s -> 7));
    public static final DeferredBlock<Block> COPPER_O_LANTERN = registerBlock("copper_o_lantern",
            CarvedPumpkinBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JACK_O_LANTERN).lightLevel(s -> 15));

    public static final DeferredBlock<Block> REDSTONE_LANTERN = registerBlock("redstone_lantern",
            RedstoneLanternBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(litBlockEmission(7)));
    public static final DeferredBlock<Block> REDSTONE_CAMPFIRE = registerBlock("redstone_campfire",
            properties -> new RedstoneCampfireBlock(false, 1, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE).lightLevel(litBlockEmission(7)));
    public static final DeferredBlock<Block> COPPER_CAMPFIRE = registerBlock("copper_campfire",
            properties -> new CampfireBlock(false, 1, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE).lightLevel(litBlockEmission(15)));

    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return state -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    private static BlockBehaviour.Properties soulSandstoneProperties() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).mapColor(MapColor.COLOR_BROWN);
    }

    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends T> block, BlockBehaviour.Properties properties) {
        DeferredBlock<T> deferredBlock = BLOCKS.registerBlock(name, block, properties);
        ModItems.ITEMS.registerSimpleBlockItem(name, deferredBlock);
        return deferredBlock;
    }

    public static <T extends Block> DeferredBlock<T> registerFireResistantBlock(String name, Function<BlockBehaviour.Properties, ? extends T> block, BlockBehaviour.Properties properties){
        DeferredBlock<T> deferredBlock = BLOCKS.registerBlock(name, block, properties);
        ModItems.ITEMS.registerSimpleBlockItem(name, deferredBlock, new Item.Properties().fireResistant());
        return deferredBlock;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
