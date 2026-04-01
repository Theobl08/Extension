package net.theobl.extension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;
import net.theobl.extension.item.ModItems;
import net.theobl.extension.particles.ModParticleTypes;
import net.theobl.extension.util.ModUtil;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.StreamSupport;

public class ModBlocks {
    // Create a Deferred Register to hold Blocks which will all be registered under the "extension" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Extension.MODID);

    private static final List<Block> PLANKS = BuiltInRegistries.BLOCK.stream().filter(block -> ModUtil.name(block).endsWith("planks")).toList();

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

    public static final DeferredBlock<Block> SMOOTH_STONE_STAIRS = registerStair("smooth_stone_stairs", Blocks.SMOOTH_STONE);
    public static final DeferredBlock<Block> NETHERITE_STAIRS = registerFireResistantBlock("netherite_stairs",
            properties -> new StairBlock(Blocks.NETHERITE_BLOCK.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK));

    public static final DeferredBlock<Block> POLISHED_STONE = registerBlock("polished_stone",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> POLISHED_STONE_STAIRS = registerStair("polished_stone_stairs", POLISHED_STONE);
    public static final DeferredBlock<Block> POLISHED_STONE_SLAB = registerBlock("polished_stone_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> POLISHED_STONE_WALL = registerBlock("polished_stone_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));

    public static final DeferredBlock<Block> CHISELED_GRANITE = registerBlock("chiseled_granite",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_BRICKS = registerBlock("granite_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_BRICK_STAIRS = registerStair("granite_brick_stairs", GRANITE_BRICKS);
    public static final DeferredBlock<Block> GRANITE_BRICK_SLAB = registerBlock("granite_brick_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE_SLAB));
    public static final DeferredBlock<Block> GRANITE_BRICK_WALL = registerBlock("granite_brick_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE_WALL));

    public static final DeferredBlock<Block> CHISELED_DIORITE = registerBlock("chiseled_diorite",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE));
    public static final DeferredBlock<Block> DIORITE_BRICKS = registerBlock("diorite_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE));
    public static final DeferredBlock<Block> DIORITE_BRICK_STAIRS = registerStair("diorite_brick_stairs", DIORITE_BRICKS);
    public static final DeferredBlock<Block> DIORITE_BRICK_SLAB = registerBlock("diorite_brick_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE_SLAB));
    public static final DeferredBlock<Block> DIORITE_BRICK_WALL = registerBlock("diorite_brick_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE_WALL));

    public static final DeferredBlock<Block> CHISELED_ANDESITE = registerBlock("chiseled_andesite",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE));
    public static final DeferredBlock<Block> ANDESITE_BRICKS = registerBlock("andesite_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE));
    public static final DeferredBlock<Block> ANDESITE_BRICK_STAIRS = registerStair("andesite_brick_stairs", ANDESITE_BRICKS);
    public static final DeferredBlock<Block> ANDESITE_BRICK_SLAB = registerBlock("andesite_brick_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE_SLAB));
    public static final DeferredBlock<Block> ANDESITE_BRICK_WALL = registerBlock("andesite_brick_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE_WALL));

    public static final DeferredBlock<Block> MOSSY_DEEPSLATE_BRICKS = registerBlock("mossy_deepslate_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS));
    public static final DeferredBlock<Block> MOSSY_DEEPSLATE_BRICK_STAIRS = registerStair("mossy_deepslate_brick_stairs", MOSSY_DEEPSLATE_BRICKS);
    public static final DeferredBlock<Block> MOSSY_DEEPSLATE_BRICK_SLAB = registerBlock("mossy_deepslate_brick_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_SLAB));
    public static final DeferredBlock<Block> MOSSY_DEEPSLATE_BRICK_WALL = registerBlock("mossy_deepslate_brick_wall",
            WallBlock::new,BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_WALL));

    public static final DeferredBlock<Block> MOSSY_COBBLED_DEEPSLATE = registerBlock("mossy_cobbled_deepslate",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> MOSSY_COBBLED_DEEPSLATE_STAIRS = registerStair("mossy_cobbled_deepslate_stairs", MOSSY_COBBLED_DEEPSLATE);
    public static final DeferredBlock<Block> MOSSY_COBBLED_DEEPSLATE_SLAB = registerBlock("mossy_cobbled_deepslate_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE_SLAB));
    public static final DeferredBlock<Block> MOSSY_COBBLED_DEEPSLATE_WALL = registerBlock("mossy_cobbled_deepslate_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE_WALL));

    public static final DeferredBlock<Block> SMOOTH_BASALT_STAIRS = registerStair("smooth_basalt_stairs", Blocks.SMOOTH_BASALT);
    public static final DeferredBlock<Block> SMOOTH_BASALT_SLAB = registerBlock("smooth_basalt_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_BASALT));

    public static final DeferredBlock<Block> QUARTZ_BRICK_STAIRS = registerStair("quartz_brick_stairs", Blocks.QUARTZ_BRICKS);
    public static final DeferredBlock<Block> QUARTZ_BRICK_SLAB = registerBlock("quartz_brick_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_SLAB));
    public static final DeferredBlock<Block> QUARTZ_BRICK_WALL = registerBlock("quartz_brick_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK));


    public static final DeferredBlock<Block> NETHER_BRICK_TILES = registerBlock("nether_brick_tiles",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS));
    public static final DeferredBlock<Block> NETHER_BRICK_TILE_STAIRS = registerStair("nether_brick_tile_stairs", NETHER_BRICK_TILES);
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
    public static final DeferredBlock<Block> RED_NETHER_BRICK_TILE_STAIRS = registerStair("red_nether_brick_tile_stairs", RED_NETHER_BRICK_TILES);
    public static final DeferredBlock<Block> RED_NETHER_BRICK_TILE_SLAB = registerBlock("red_nether_brick_tile_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICK_SLAB));
    public static final DeferredBlock<Block> RED_NETHER_BRICK_TILE_WALL = registerBlock("red_nether_brick_tile_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICK_WALL));

    public static final DeferredBlock<Block> BLUE_NETHER_WART = BLOCKS.registerBlock("blue_nether_wart",
            BlueNetherWartBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_WART).mapColor(MapColor.WARPED_WART_BLOCK));

    public static final DeferredBlock<Block> BLUE_NETHER_BRICKS = registerBlock("blue_nether_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS));
    public static final DeferredBlock<Block> CRACKED_BLUE_NETHER_BRICKS = registerBlock("cracked_blue_nether_bricks",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_NETHER_BRICKS));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_STAIRS = registerStair("blue_nether_brick_stairs", BLUE_NETHER_BRICKS);
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
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_TILE_STAIRS = registerStair("blue_nether_brick_tile_stairs", BLUE_NETHER_BRICK_TILES);
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_TILE_SLAB = registerBlock("blue_nether_brick_tile_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS));
    public static final DeferredBlock<Block> BLUE_NETHER_BRICK_TILE_WALL = registerBlock("blue_nether_brick_tile_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS));

    public static final DeferredBlock<Block> SOUL_SANDSTONE = registerBlock("soul_sandstone",
            Block::new, soulSandstoneProperties());
    public static final DeferredBlock<Block> SOUL_SANDSTONE_STAIRS = registerStair("soul_sandstone_stairs", SOUL_SANDSTONE);
    public static final DeferredBlock<Block> SOUL_SANDSTONE_SLAB = registerBlock("soul_sandstone_slab",
            SlabBlock::new, soulSandstoneProperties());
    public static final DeferredBlock<Block> SOUL_SANDSTONE_WALL = registerBlock("soul_sandstone_wall",
            WallBlock::new, soulSandstoneProperties());
    public static final DeferredBlock<Block> CHISELED_SOUL_SANDSTONE = registerBlock("chiseled_soul_sandstone",
            Block::new, soulSandstoneProperties());
    public static final DeferredBlock<Block> SMOOTH_SOUL_SANDSTONE = registerBlock("smooth_soul_sandstone",
            Block::new, soulSandstoneProperties());
    public static final DeferredBlock<Block> SMOOTH_SOUL_SANDSTONE_STAIRS = registerStair("smooth_soul_sandstone_stairs", SMOOTH_SOUL_SANDSTONE);
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
    public static final DeferredBlock<Block> ENDER_O_LANTERN = registerBlock("ender_o_lantern",
            CarvedPumpkinBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JACK_O_LANTERN).lightLevel(s -> 15));

    public static final DeferredBlock<Block> ENDER_TORCH = BLOCKS.registerBlock(
            "ender_torch",
            p -> new TorchBlock(ModParticleTypes.ENDER_FIRE_FLAME.get(), p),
            () -> BlockBehaviour.Properties.of().noCollision().instabreak().lightLevel(_ -> 14).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)
    );
    public static final DeferredBlock<Block> ENDER_WALL_TORCH = BLOCKS.registerBlock(
            "ender_wall_torch",
            p -> new WallTorchBlock(ModParticleTypes.ENDER_FIRE_FLAME.get(), p),
            () -> BlockBehaviour.Properties.of()
                    .overrideLootTable(ENDER_TORCH.get().getLootTable())
                    .overrideDescription(ENDER_TORCH.get().getDescriptionId())
                    .noCollision()
                    .instabreak()
                    .lightLevel(_ -> 14)
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY)
    );

    public static final DeferredBlock<Block> REDSTONE_LANTERN = registerBlock("redstone_lantern",
            RedstoneLanternBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(litBlockEmission(7)));
    public static final DeferredBlock<Block> ENDER_LANTERN = registerBlock("ender_lantern",
            LanternBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(state -> 15));


    public static final DeferredBlock<Block> REDSTONE_CAMPFIRE = registerBlock("redstone_campfire",
            properties -> new RedstoneCampfireBlock(false, 1, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE).lightLevel(litBlockEmission(7)));
    public static final DeferredBlock<Block> COPPER_CAMPFIRE = registerBlock("copper_campfire",
            properties -> new CampfireBlock(false, 1, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE).lightLevel(litBlockEmission(15)));
    public static final DeferredBlock<Block> ENDER_CAMPFIRE = registerBlock("ender_campfire",
            properties -> new CampfireBlock(false, 1, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE).lightLevel(litBlockEmission(15)));

    public static final DeferredBlock<Block> COPPER_FIRE = BLOCKS.registerBlock("copper_fire",
            CopperFireBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FIRE).mapColor(MapColor.COLOR_LIGHT_GREEN));
    public static final DeferredBlock<Block> REDSTONE_FIRE = BLOCKS.registerBlock("redstone_fire",
            RedstoneFireBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FIRE));
    public static final DeferredBlock<Block> ENDER_FIRE = BLOCKS.registerBlock("ender_fire",
            EnderFireBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FIRE));

    public static final DeferredBlock<Block> MILK_CAULDRON = BLOCKS.registerBlock("milk_cauldron",
            MilkCauldronBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
    public static final Map<Holder<Potion>, DeferredBlock<PotionCauldronBlock>> POTION_CAULDRON = registerPotionCauldrons();

//    public static final DeferredBlock<Block> SPRUCE_CRAFTING_TABLE = registerBlock("spruce_crafting_table", CraftingTableBlock::new,
//            BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
//    public static final DeferredBlock<Block> BIRCH_CRAFTING_TABLE = registerBlock("birch_crafting_table", CraftingTableBlock::new,
//            BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
//    public static final DeferredBlock<Block> JUNGLE_CRAFTING_TABLE = registerBlock("jungle_crafting_table", CraftingTableBlock::new,
//            BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
//    public static final DeferredBlock<Block> ACACIA_CRAFTING_TABLE = registerBlock("acacia_crafting_table", CraftingTableBlock::new,
//            BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
//    public static final DeferredBlock<Block> DARK_OAK_CRAFTING_TABLE = registerBlock("dark_oak_crafting_table", CraftingTableBlock::new,
//            BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
//    public static final DeferredBlock<Block> MANGROVE_CRAFTING_TABLE = registerBlock("mangrove_crafting_table", CraftingTableBlock::new,
//            BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
//    public static final DeferredBlock<Block> CHERRY_CRAFTING_TABLE = registerBlock("cherry_crafting_table", CraftingTableBlock::new,
//            BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE).sound(SoundType.CHERRY_WOOD));
//    public static final DeferredBlock<Block> PALE_OAK_CRAFTING_TABLE = registerBlock("pale_oak_crafting_table", CraftingTableBlock::new,
//            BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
//    public static final DeferredBlock<Block> BAMBOO_CRAFTING_TABLE = registerBlock("bamboo_crafting_table", CraftingTableBlock::new,
//            BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE).sound(SoundType.BAMBOO_WOOD));
//    public static final DeferredBlock<Block> CRIMSON_CRAFTING_TABLE = registerBlock("crimson_crafting_table", CraftingTableBlock::new,
//            BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE).sound(SoundType.NETHER_WOOD));
//    public static final DeferredBlock<Block> WARPED_CRAFTING_TABLE = registerBlock("warped_crafting_table", CraftingTableBlock::new,
//            BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE).sound(SoundType.NETHER_WOOD));
    public static final Map<Block, DeferredBlock<Block>> VARIANTS_CRAFTING_TABLE = registerCraftingTables();

    public static final DeferredBlock<Block> POTATO_FRUIT = registerBlock(
            "potato_fruit",
            Block::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .strength(1.0F)
                    .sound(SoundType.NETHER_WOOD)
                    .pushReaction(PushReaction.DESTROY)
                    .lightLevel(blockstate -> 15)
    );
    public static final DeferredBlock<Block> POTATO_PEDICULE = registerBlock(
            "potato_pedicule",
            ChainBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .strength(0.2F)
                    .sound(SoundType.NETHER_WOOD)
                    .pushReaction(PushReaction.DESTROY)
    );
    public static final DeferredBlock<Block> POTATO_SPROUTS = registerBlock(
            "potato_sprouts",
            p -> new SaplingBlock(ModTreeGrower.POTATO, p),
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .sound(SoundType.NETHER_SPROUTS)
                    .noOcclusion()
                    .randomTicks()
                    .instabreak()
                    .dynamicShape()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)
    );
    public static final DeferredBlock<Block> POTATO_LEAVES = registerBlock(
            "potato_leaves",
            p -> new TintedParticleLeavesBlock(0.01F, p),
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .strength(0.2F)
                    .randomTicks()
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .isValidSpawn(Blocks::ocelotOrParrot)
                    .isSuffocating(ModBlocks::never)
                    .isViewBlocking(ModBlocks::never)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(ModBlocks::never)
    );
    public static final DeferredBlock<Block> POTATO_STEM = registerBlock(
            "potato_stem",
            RotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.EMERALD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.STEM)
    );
    public static final DeferredBlock<Block> POTATO_HYPHAE = registerBlock(
            "potato_hyphae",
            RotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.EMERALD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.STEM)
    );
    public static final DeferredBlock<Block> POTATO_PLANKS = registerBlock(
            "potato_planks",
            Block::new,
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.EMERALD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.NETHER_WOOD)
    );
    public static final DeferredBlock<Block> POTATO_STAIRS = registerStair("potato_stairs", POTATO_PLANKS);
    public static final DeferredBlock<Block> POTATO_SLAB = registerBlock(
            "potato_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.of()
                    .mapColor(POTATO_PLANKS.get().defaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.NETHER_WOOD)
    );
    public static final DeferredBlock<Block> POTATO_FENCE = registerBlock(
            "potato_fence",
            FenceBlock::new,
            () -> BlockBehaviour.Properties.of()
                    .mapColor(POTATO_PLANKS.get().defaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.NETHER_WOOD)
    );
    public static final DeferredBlock<Block> POTATO_FENCE_GATE = registerBlock(
            "potato_fence_gate",
            p -> new FenceGateBlock(ModWoodType.POTATO, p),
            () -> BlockBehaviour.Properties.of()
                    .mapColor(POTATO_PLANKS.get().defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
    );
    public static final DeferredBlock<Block> POTATO_DOOR = registerBlock(
            "potato_door",
            p -> new DoorBlock(BlockSetType.CRIMSON, p),
            () -> BlockBehaviour.Properties.of()
                    .mapColor(POTATO_PLANKS.get().defaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
    );
    public static final DeferredBlock<Block> POTATO_TRAPDOOR = registerBlock(
            "potato_trapdoor",
            p -> new TrapDoorBlock(BlockSetType.CRIMSON, p),
            () -> BlockBehaviour.Properties.of()
                    .mapColor(POTATO_PLANKS.get().defaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F)
                    .noOcclusion()
                    .isValidSpawn(Blocks::never)
    );
    public static final DeferredBlock<Block> POTATO_PRESSURE_PLATE = registerBlock(
            "potato_pressure_plate",
            p -> new PressurePlateBlock(BlockSetType.CRIMSON, p),
            () -> BlockBehaviour.Properties.of()
                    .mapColor(POTATO_PLANKS.get().defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision()
                    .strength(0.5F)
                    .pushReaction(PushReaction.DESTROY)
    );
    public static final DeferredBlock<Block> POTATO_BUTTON = registerBlock(
            "potato_button",
            p -> new ButtonBlock(BlockSetType.CRIMSON, 30, p),
            () -> BlockBehaviour.Properties.of()
                    .noCollision()
                    .strength(0.5F)
                    .pushReaction(PushReaction.DESTROY)
    );
    public static final DeferredBlock<Block> POTATO_SIGN = BLOCKS.registerBlock(
            "potato_sign",
            p -> new StandingSignBlock(ModWoodType.POTATO, p),
            () -> BlockBehaviour.Properties.of()
                    .mapColor(POTATO_PLANKS.get().defaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .forceSolidOn()
                    .noCollision()
                    .strength(1.0F)
    );
    public static final DeferredBlock<Block> POTATO_WALL_SIGN = BLOCKS.registerBlock(
            "potato_wall_sign",
            p -> new WallSignBlock(ModWoodType.POTATO, p),
            () -> BlockBehaviour.Properties.of()
                    .overrideLootTable(POTATO_SIGN.get().getLootTable())
                    .overrideDescription(POTATO_SIGN.get().getDescriptionId())
                    .mapColor(POTATO_PLANKS.get().defaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .forceSolidOn()
                    .noCollision()
                    .strength(1.0F)
    );
    public static final DeferredBlock<Block> POTATO_HANGING_SIGN = BLOCKS.registerBlock(
            "potato_hanging_sign",
            p -> new CeilingHangingSignBlock(ModWoodType.POTATO, p),
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.EMERALD)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision()
                    .strength(1.0F)
    );
    public static final DeferredBlock<Block> POTATO_WALL_HANGING_SIGN = BLOCKS.registerBlock(
            "potato_wall_hanging_sign",
            p-> new WallHangingSignBlock(ModWoodType.POTATO, p),
            () -> BlockBehaviour.Properties.of()
                    .overrideLootTable(POTATO_HANGING_SIGN.get().getLootTable())
                    .overrideDescription(POTATO_HANGING_SIGN.get().getDescriptionId())
                    .mapColor(MapColor.WOOD)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision()
                    .strength(1.0F)
    );

    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return state -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    private static BlockBehaviour.Properties soulSandstoneProperties() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).mapColor(MapColor.COLOR_BROWN);
    }

    private static boolean never(BlockState state, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    private static Map<Holder<Potion>, DeferredBlock<PotionCauldronBlock>> registerPotionCauldrons() {
        Map<Holder<Potion>, DeferredBlock<PotionCauldronBlock>> potionCauldrons = new HashMap<>();
        for (Holder<Potion> potion : ModUtil.POTIONS) {
            String name = ModUtil.name(potion);
            DeferredBlock<PotionCauldronBlock> block = BLOCKS.registerBlock(name + "_cauldron",
                    p -> new PotionCauldronBlock(potion, ExtendedCauldronInteraction.POTIONS_INTERACTIONS.get(potion), p),
                    () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON));
            potionCauldrons.put(potion, block);
        }
        return potionCauldrons;
    }

    private static Map<Block, DeferredBlock<Block>> registerCraftingTables() {
        Map<Block, DeferredBlock<Block>> blocks = new HashMap<>();
        for (Block plank : PLANKS) {
            if(plank == Blocks.OAK_PLANKS) {
                continue; // Vanilla crafting table is based of oak planks
            }
            String name = ModUtil.name(plank).replace("planks", "crafting_table");
            BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE);
            if(name.contains("cherry")) {
                properties = properties.sound(SoundType.CHERRY_WOOD);
            } else if(name.contains("bamboo")) {
                properties = properties.sound(SoundType.BAMBOO_WOOD);
            } else if(name.contains("crimson") || name.contains("warped")) {
                properties = properties.sound(SoundType.NETHER_WOOD);
            }
            blocks.put(plank, registerBlock(name, CraftingTableBlock::new, properties));
        }
        return blocks;
    }

    public static <T extends Block> DeferredBlock<Block> registerStair(String name, DeferredBlock<T> baseBlock) {
        return registerBlock(name, p -> new StairBlock(baseBlock.get().defaultBlockState(), p), () -> BlockBehaviour.Properties.ofFullCopy(baseBlock.get()));
    }

    public static DeferredBlock<Block> registerStair(String name, Block baseBlock) {
        return registerBlock(name, p -> new StairBlock(baseBlock.defaultBlockState(), p), BlockBehaviour.Properties.ofFullCopy(baseBlock));
    }

    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends T> block, Supplier<BlockBehaviour.Properties> properties) {
        DeferredBlock<T> deferredBlock = BLOCKS.registerBlock(name, block, properties);
        ModItems.ITEMS.registerSimpleBlockItem(name, deferredBlock);
        return deferredBlock;
    }

    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends T> block, BlockBehaviour.Properties properties) {
        return registerBlock(name, block, () -> properties);
    }

    public static <T extends Block> DeferredBlock<T> registerFireResistantBlock(String name, Function<BlockBehaviour.Properties, ? extends T> block, BlockBehaviour.Properties properties){
        DeferredBlock<T> deferredBlock = BLOCKS.registerBlock(name, block, () -> properties);
        ModItems.ITEMS.registerSimpleBlockItem(name, deferredBlock, Item.Properties::fireResistant);
        return deferredBlock;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
