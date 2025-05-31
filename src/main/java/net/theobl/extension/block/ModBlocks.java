package net.theobl.extension.block;

import com.google.common.base.Suppliers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.theobl.extension.block.custom.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.theobl.extension.Extension;
import net.theobl.extension.block.custom.interfaces.ModWallSignBlock;
import net.theobl.extension.item.ModItems;
import net.theobl.extension.util.ModWoodTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Extension.MOD_ID);

    public static final List<DyeColor> COLORS = new ArrayList<>(Arrays.asList(
            DyeColor.WHITE, DyeColor.LIGHT_GRAY, DyeColor.GRAY, DyeColor.BLACK,
            DyeColor.BROWN, DyeColor.RED, DyeColor.ORANGE, DyeColor.YELLOW,
            DyeColor.LIME, DyeColor.GREEN, DyeColor.CYAN, DyeColor.LIGHT_BLUE,
            DyeColor.BLUE, DyeColor.PURPLE, DyeColor.MAGENTA, DyeColor.PINK));

    public static final List<WoodType> COLORED_WOOD_TYPES = new ArrayList<>(Arrays.asList(
            ModWoodTypes.WHITE, ModWoodTypes.LIGHT_GRAY, ModWoodTypes.GRAY, ModWoodTypes.BLACK,
            ModWoodTypes.BROWN, ModWoodTypes.RED, ModWoodTypes.ORANGE, ModWoodTypes.YELLOW,
            ModWoodTypes.LIME, ModWoodTypes.GREEN, ModWoodTypes.CYAN, ModWoodTypes.LIGHT_BLUE,
            ModWoodTypes.BLUE, ModWoodTypes.PURPLE, ModWoodTypes.MAGENTA, ModWoodTypes.PINK));

    public static final RegistryObject<Block> AMETHYST_ORE = registerBlock("amethyst_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.COAL_ORE)));
    public static final RegistryObject<Block> DEEPSLATE_AMETHYST_ORE = registerBlock("deepslate_amethyst_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COAL_ORE)));

    public static final RegistryObject<Block> STONE_WALL = registerBlock("stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL).forceSolidOn()));
    public static final RegistryObject<Block> POLISHED_GRANITE_WALL = registerBlock("polished_granite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE_WALL).forceSolidOn()));
    public static final RegistryObject<Block> POLISHED_DIORITE_WALL = registerBlock("polished_diorite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE_WALL).forceSolidOn()));
    public static final RegistryObject<Block> POLISHED_ANDESITE_WALL = registerBlock("polished_andesite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL).forceSolidOn()));
    public static final RegistryObject<Block> PRISMARINE_BRICK_WALL = registerBlock("prismarine_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.PRISMARINE_WALL).forceSolidOn()));
    public static final RegistryObject<Block> DARK_PRISMARINE_BRICK_WALL = registerBlock("dark_prismarine_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.PRISMARINE_WALL).forceSolidOn()));
    public static final RegistryObject<Block> PURPUR_WALL = registerBlock("purpur_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICK_WALL).forceSolidOn()));
    public static final RegistryObject<Block> QUARTZ_WALL = registerBlock("quartz_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICK_WALL).forceSolidOn()));
    public static final RegistryObject<Block> SMOOTH_QUARTZ_WALL = registerBlock("smooth_quartz_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICK_WALL).forceSolidOn()));

    public static final RegistryObject<Block> CUT_COPPER_WALL = registerBlock("cut_copper_wall",
            Suppliers.memoize(() -> new ModWeatheringCopperWallBlock(WeatheringCopper.WeatherState.UNAFFECTED, Blocks.CUT_COPPER)));
    public static final RegistryObject<Block> EXPOSED_CUT_COPPER_WALL = registerBlock("exposed_cut_copper_wall",
            Suppliers.memoize(() -> new ModWeatheringCopperWallBlock(WeatheringCopper.WeatherState.EXPOSED, Blocks.EXPOSED_CUT_COPPER)));
    public static final RegistryObject<Block> WEATHERED_CUT_COPPER_WALL = registerBlock("weathered_cut_copper_wall",
            Suppliers.memoize(() -> new ModWeatheringCopperWallBlock(WeatheringCopper.WeatherState.WEATHERED, Blocks.WEATHERED_CUT_COPPER)));
    public static final RegistryObject<Block> OXIDIZED_CUT_COPPER_WALL = registerBlock("oxidized_cut_copper_wall",
            Suppliers.memoize(() -> new ModWeatheringCopperWallBlock(WeatheringCopper.WeatherState.OXIDIZED, Blocks.OXIDIZED_CUT_COPPER)));

    public static final RegistryObject<Block> WAXED_CUT_COPPER_WALL = registerBlock("waxed_cut_copper_wall",
            Suppliers.memoize(() -> new ModWeatheringCopperWallBlock(WeatheringCopper.WeatherState.UNAFFECTED, Blocks.WAXED_CUT_COPPER)));
    public static final RegistryObject<Block> WAXED_EXPOSED_CUT_COPPER_WALL = registerBlock("waxed_exposed_cut_copper_wall",
            Suppliers.memoize(() -> new ModWeatheringCopperWallBlock(WeatheringCopper.WeatherState.EXPOSED, Blocks.EXPOSED_CUT_COPPER)));
    public static final RegistryObject<Block> WAXED_WEATHERED_CUT_COPPER_WALL = registerBlock("waxed_weathered_cut_copper_wall",
            Suppliers.memoize(() -> new ModWeatheringCopperWallBlock(WeatheringCopper.WeatherState.WEATHERED, Blocks.WEATHERED_CUT_COPPER)));
    public static final RegistryObject<Block> WAXED_OXIDIZED_CUT_COPPER_WALL = registerBlock("waxed_oxidized_cut_copper_wall",
            Suppliers.memoize(() -> new ModWeatheringCopperWallBlock(WeatheringCopper.WeatherState.OXIDIZED, Blocks.OXIDIZED_CUT_COPPER)));

    public static final RegistryObject<Block> TINTED_GLASS_PANE = registerBlock("tinted_glass_pane",
            () -> new IronBarsBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_STAINED_GLASS_PANE)));

    public static final RegistryObject<Block> NETHER_BRICK_FENCE_GATE = registerBlock("nether_brick_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final RegistryObject<Block> RED_NETHER_BRICK_FENCE_GATE = registerBlock("red_nether_brick_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.RED_NETHER_BRICKS), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final RegistryObject<Block> CRACKED_RED_NETHER_BRICKS = registerBlock("cracked_red_nether_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RED_NETHER_BRICKS)));
    public static final RegistryObject<Block> RED_NETHER_BRICK_FENCE = registerBlock("red_nether_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICK_FENCE)));
    public static final RegistryObject<Block> CHISELED_RED_NETHER_BRICKS = registerBlock("chiseled_red_nether_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RED_NETHER_BRICKS)));

    public static final RegistryObject<Block> BLUE_NETHER_BRICKS = registerBlock("blue_nether_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS)));
    public static final RegistryObject<Block> CRACKED_BLUE_NETHER_BRICKS = registerBlock("cracked_blue_nether_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CRACKED_NETHER_BRICKS)));
    public static final RegistryObject<Block> BLUE_NETHER_BRICK_STAIRS = registerBlock("blue_nether_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.BLUE_NETHER_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.NETHER_BRICK_STAIRS)));
    public static final RegistryObject<Block> BLUE_NETHER_BRICK_SLAB = registerBlock("blue_nether_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICK_SLAB)));
    public static final RegistryObject<Block> BLUE_NETHER_BRICK_WALL = registerBlock("blue_nether_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICK_WALL)));
    public static final RegistryObject<Block> BLUE_NETHER_BRICK_FENCE = registerBlock("blue_nether_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICK_FENCE)));
    public static final RegistryObject<Block> BLUE_NETHER_BRICK_FENCE_GATE = registerBlock("blue_nether_brick_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final RegistryObject<Block> CHISELED_BLUE_NETHER_BRICKS = registerBlock("chiseled_blue_nether_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_NETHER_BRICKS)));

    public static final RegistryObject<Block> SOUL_SANDSTONE = registerBlock("soul_sandstone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE).mapColor(MapColor.COLOR_BROWN)));
    public static final RegistryObject<Block> SOUL_SANDSTONE_STAIRS = registerBlock("soul_sandstone_stairs",
            () -> new StairBlock(() -> ModBlocks.SOUL_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(ModBlocks.SOUL_SANDSTONE.get())));
    public static final RegistryObject<Block> SOUL_SANDSTONE_SLAB = registerBlock("soul_sandstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(ModBlocks.SOUL_SANDSTONE.get())));
    public static final RegistryObject<Block> SOUL_SANDSTONE_WALL = registerBlock("soul_sandstone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(ModBlocks.SOUL_SANDSTONE.get())));
    public static final RegistryObject<Block> CHISELED_SOUL_SANDSTONE = registerBlock("chiseled_soul_sandstone",
            () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.SOUL_SANDSTONE.get())));
    public static final RegistryObject<Block> SMOOTH_SOUL_SANDSTONE = registerBlock("smooth_soul_sandstone",
            () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.SOUL_SANDSTONE.get())));
    public static final RegistryObject<Block> SMOOTH_SOUL_SANDSTONE_STAIRS = registerBlock("smooth_soul_sandstone_stairs",
            () -> new StairBlock(() -> ModBlocks.SMOOTH_SOUL_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(ModBlocks.SOUL_SANDSTONE.get())));
    public static final RegistryObject<Block> SMOOTH_SOUL_SANDSTONE_SLAB = registerBlock("smooth_soul_sandstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(ModBlocks.SOUL_SANDSTONE.get())));
    public static final RegistryObject<Block> CUT_SOUL_SANDSTONE = registerBlock("cut_soul_sandstone",
            () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.SOUL_SANDSTONE.get())));
    public static final RegistryObject<Block> CUT_SOUL_SANDSTONE_SLAB = registerBlock("cut_soul_sandstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(ModBlocks.SOUL_SANDSTONE.get())));

    public static final RegistryObject<Block> REDSTONE_LANTERN = registerBlock("redstone_lantern",
            () -> new ModPoweredLantern(BlockBehaviour.Properties.copy(Blocks.LANTERN).lightLevel(s-> 7)));

    public static final List<RegistryObject<Block>> QUILTED_CONCRETES = registerQuiltedConcrete();
    private static List<RegistryObject<Block>> registerQuiltedConcrete() {
        List<RegistryObject<Block>> quiltedConcrete = new ArrayList<>();
        for (DyeColor color : COLORS) {
            RegistryObject<Block> block = registerBlock( "quilted_" + color.getName() + "_concrete",
                    () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE).mapColor(color.getMapColor())));
            quiltedConcrete.add(block);
        }
        return quiltedConcrete;
    }

    public static final List<RegistryObject<Block>> GLAZED_CONCRETES = registerGlazedConcrete();
    private static List<RegistryObject<Block>> registerGlazedConcrete() {
        List<RegistryObject<Block>> glazedConcrete = new ArrayList<>();
        for (DyeColor color : COLORS) {
            RegistryObject<Block> block = registerBlock( color.getName() + "_glazed_concrete",
            () -> new GlazedTerracottaBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_GLAZED_TERRACOTTA).mapColor(color.getMapColor())));
            glazedConcrete.add(block);
        }
        return glazedConcrete;
    }

    public static final List<RegistryObject<Block>> ANTIBLOCKS = registerAntiBlocks();
    private static List<RegistryObject<Block>> registerAntiBlocks() {
        List<RegistryObject<Block>> antiBlocks = new ArrayList<>();
        for (DyeColor color : COLORS) {
            RegistryObject<Block> block;
            if(color.equals(DyeColor.BLACK)) {
                block = registerBlock(color.getName() + "_antiblock",
                        () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1.8f).mapColor(color.getMapColor())));
            } else {
                block = registerBlock(color.getName() + "_antiblock",
                        () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1.8f).mapColor(color.getMapColor())
                                //.lightLevel(s -> 1).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)));
                                .lightLevel(s -> 1).hasPostProcess(ModBlocks::always).emissiveRendering(ModBlocks::always)));
            }
            antiBlocks.add(block);
        }
        return antiBlocks;
    }

    public static final RegistryObject<Block> VIBRANT_RED_WOOL = registerBlock("vibrant_red_wool",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> DULL_ORANGE_WOOL = registerBlock("dull_orange_wool",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> BRIGHT_YELLOW_WOOL = registerBlock("bright_yellow_wool",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> CHARTREUSE_WOOL = registerBlock("chartreuse_wool",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> VIBRANT_GREEN_WOOL = registerBlock("vibrant_green_wool",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> SPRING_GREEN_WOOL = registerBlock("spring_green_wool",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> BRIGHT_CYAN_WOOL = registerBlock("bright_cyan_wool",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> CAPRI_WOOL = registerBlock("capri_wool",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> ULTRAMARINE_WOOL = registerBlock("ultramarine_wool",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> VIOLET_WOOL = registerBlock("violet_wool",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> VIBRANT_PURPLE_WOOL = registerBlock("vibrant_purple_wool",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> BRIGHT_MAGENTA_WOOL = registerBlock("bright_magenta_wool",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> ROSE_WOOL = registerBlock("rose_wool",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> DARK_GRAY_WOOL = registerBlock("dark_gray_wool",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> SILVER_WOOL = registerBlock("silver_wool",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> ALPHA_WHITE_WOOL = registerBlock("alpha_white_wool",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));

    public static final RegistryObject<Block> VIBRANT_RED_CARPET = registerBlock("vibrant_red_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.RED_CARPET)));
    public static final RegistryObject<Block> DULL_ORANGE_CARPET = registerBlock("dull_orange_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CARPET)));
    public static final RegistryObject<Block> BRIGHT_YELLOW_CARPET = registerBlock("bright_yellow_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CARPET)));
    public static final RegistryObject<Block> CHARTREUSE_CARPET = registerBlock("chartreuse_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CARPET)));
    public static final RegistryObject<Block> VIBRANT_GREEN_CARPET = registerBlock("vibrant_green_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CARPET)));
    public static final RegistryObject<Block> SPRING_GREEN_CARPET = registerBlock("spring_green_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CARPET)));
    public static final RegistryObject<Block> BRIGHT_CYAN_CARPET = registerBlock("bright_cyan_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CARPET)));
    public static final RegistryObject<Block> CAPRI_CARPET = registerBlock("capri_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CARPET)));
    public static final RegistryObject<Block> ULTRAMARINE_CARPET = registerBlock("ultramarine_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CARPET)));
    public static final RegistryObject<Block> VIOLET_CARPET = registerBlock("violet_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CARPET)));
    public static final RegistryObject<Block> VIBRANT_PURPLE_CARPET = registerBlock("vibrant_purple_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CARPET)));
    public static final RegistryObject<Block> BRIGHT_MAGENTA_CARPET = registerBlock("bright_magenta_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CARPET)));
    public static final RegistryObject<Block> ROSE_CARPET = registerBlock("rose_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CARPET)));
    public static final RegistryObject<Block> DARK_GRAY_CARPET = registerBlock("dark_gray_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CARPET)));
    public static final RegistryObject<Block> SILVER_CARPET = registerBlock("silver_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CARPET)));
    public static final RegistryObject<Block> ALPHA_WHITE_CARPET = registerBlock("alpha_white_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CARPET)));

    public static final RegistryObject<Block> SPRUCE_BOOKSHELF = registerBlock("spruce_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SPRUCE_PLANKS)));
    public static final RegistryObject<Block> BIRCH_BOOKSHELF = registerBlock("birch_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BIRCH_PLANKS)));
    public static final RegistryObject<Block> JUNGLE_BOOKSHELF = registerBlock("jungle_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.JUNGLE_PLANKS)));
    public static final RegistryObject<Block> ACACIA_BOOKSHELF = registerBlock("acacia_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS)));
    public static final RegistryObject<Block> DARK_OAK_BOOKSHELF = registerBlock("dark_oak_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_PLANKS)));
    public static final RegistryObject<Block> MANGROVE_BOOKSHELF = registerBlock("mangrove_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MANGROVE_PLANKS)));
    public static final RegistryObject<Block> BAMBOO_BOOKSHELF = registerBlock("bamboo_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)));
    public static final RegistryObject<Block> CHERRY_BOOKSHELF = registerBlock("cherry_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CHERRY_PLANKS)));
    public static final RegistryObject<Block> CRIMSON_BOOKSHELF = registerBlock("crimson_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS)));
    public static final RegistryObject<Block> WARPED_BOOKSHELF = registerBlock("warped_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WARPED_PLANKS)));

    public static final RegistryObject<Block> OAK_MOSAIC = registerBlock("oak_mosaic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> SPRUCE_MOSAIC = registerBlock("spruce_mosaic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> BIRCH_MOSAIC = registerBlock("birch_mosaic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> JUNGLE_MOSAIC = registerBlock("jungle_mosaic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> ACACIA_MOSAIC = registerBlock("acacia_mosaic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> DARK_OAK_MOSAIC = registerBlock("dark_oak_mosaic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> MANGROVE_MOSAIC = registerBlock("mangrove_mosaic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> CHERRY_MOSAIC = registerBlock("cherry_mosaic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CHERRY_PLANKS)));
    public static final RegistryObject<Block> CRIMSON_MOSAIC = registerBlock("crimson_mosaic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS)));
    public static final RegistryObject<Block> WARPED_MOSAIC = registerBlock("warped_mosaic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WARPED_PLANKS)));

    public static final RegistryObject<Block> SPRUCE_CRAFTING_TABLE = registerBlock("spruce_crafting_table",
            () -> new ModCraftingTableBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));
    public static final RegistryObject<Block> BIRCH_CRAFTING_TABLE = registerBlock("birch_crafting_table",
            () -> new ModCraftingTableBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));
    public static final RegistryObject<Block> JUNGLE_CRAFTING_TABLE = registerBlock("jungle_crafting_table",
            () -> new ModCraftingTableBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));
    public static final RegistryObject<Block> ACACIA_CRAFTING_TABLE = registerBlock("acacia_crafting_table",
            () -> new ModCraftingTableBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));
    public static final RegistryObject<Block> DARK_OAK_CRAFTING_TABLE = registerBlock("dark_oak_crafting_table",
            () -> new ModCraftingTableBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));
    public static final RegistryObject<Block> MANGROVE_CRAFTING_TABLE = registerBlock("mangrove_crafting_table",
            () -> new ModCraftingTableBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));
    public static final RegistryObject<Block> BAMBOO_CRAFTING_TABLE = registerBlock("bamboo_crafting_table",
            () -> new ModCraftingTableBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)));
    public static final RegistryObject<Block> CHERRY_CRAFTING_TABLE = registerBlock("cherry_crafting_table",
            () -> new ModCraftingTableBlock(BlockBehaviour.Properties.copy(Blocks.CHERRY_PLANKS)));
    public static final RegistryObject<Block> CRIMSON_CRAFTING_TABLE = registerBlock("crimson_crafting_table",
            () -> new ModCraftingTableBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS)));
    public static final RegistryObject<Block> WARPED_CRAFTING_TABLE = registerBlock("warped_crafting_table",
            () -> new ModCraftingTableBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_PLANKS)));

    public static final RegistryObject<Block> PAINTING_PLANKS = registerBlock("painting_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> PAINTING_STAIRS = registerBlock("painting_stairs",
            () -> new StairBlock(() -> PAINTING_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));
    public static final RegistryObject<Block> PAINTING_SLAB = registerBlock("painting_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
    public static final RegistryObject<Block> PAINTING_FENCE = registerBlock("painting_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistryObject<Block> PAINTING_FENCE_GATE = registerBlock("painting_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final RegistryObject<Block> PAINTING_DOOR = registerBlock("painting_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR),BlockSetType.OAK));
    public static final RegistryObject<Block> PAINTING_TRAPDOOR = registerBlock("painting_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).isValidSpawn(ModBlocks::never),BlockSetType.OAK));
    public static final RegistryObject<Block> PAINTING_PRESSURE_PLATE = registerBlock("painting_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK));
    public static final RegistryObject<Block> PAINTING_BUTTON = registerBlock("painting_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), BlockSetType.OAK, 30, true));

    public static final List<RegistryObject<Block>> COLORED_LEAVES = registerColoredLeaves();
    private static List<RegistryObject<Block>> registerColoredLeaves(){
        List<RegistryObject<Block>> leaves = new ArrayList<>();
        for (DyeColor color : COLORS) {
            RegistryObject<Block> block = registerBlock(color.getName() + "_leaves",
                    () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).mapColor(color.getMapColor())
                            .isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).isRedstoneConductor(ModBlocks::never)));
            leaves.add(block);
        }
        return leaves;
    }

    public static final List<RegistryObject<Block>> COLORED_LOGS = registerColoredLogs(false);
    public static final List<RegistryObject<Block>> COLORED_WOODS = registerColoredWoods(false);
    public static final List<RegistryObject<Block>> COLORED_STRIPPED_LOGS = registerColoredLogs(true);
    private static List<RegistryObject<Block>> registerColoredLogs(boolean isStripped){
        List<RegistryObject<Block>> log = new ArrayList<>();
        for (DyeColor color : COLORS) {
            String name = isStripped ? "stripped_" + color.getName() + "_log" : color.getName() + "_log";
            RegistryObject<Block> block = registerBlock(name,
                    () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).mapColor(color.getMapColor())));
            log.add(block);
        }
        return log;
    }

    public static final List<RegistryObject<Block>> COLORED_STRIPPED_WOODS = registerColoredWoods(true);
    private static List<RegistryObject<Block>> registerColoredWoods(boolean isStripped){
        List<RegistryObject<Block>> log = new ArrayList<>();
        for (DyeColor color : COLORS) {
            String name = isStripped ? "stripped_" + color.getName() + "_wood" : color.getName() + "_wood";
            RegistryObject<Block> block = registerBlock(name,
                    () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).mapColor(color.getMapColor())));
            log.add(block);
        }
        return log;
    }

    public static final List<RegistryObject<Block>> COLORED_PLANKS = registerColoredPlanks();
    private static List<RegistryObject<Block>> registerColoredPlanks(){
        List<RegistryObject<Block>> planks = new ArrayList<>();
        for (DyeColor color : COLORS) {
            RegistryObject<Block> block = registerBlock( color.getName() + "_planks",
                    () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).mapColor(color.getMapColor())));
            planks.add(block);
        }
        return planks;
    }

    public static final List<RegistryObject<Block>> COLORED_STAIRS = registerColoredStairs();
    private static List<RegistryObject<Block>> registerColoredStairs(){
        List<RegistryObject<Block>> stairs = new ArrayList<>();
        for (DyeColor color : COLORS) {
            int index = COLORS.indexOf(color);
            RegistryObject<Block> block = registerBlock( color.getName() + "_stairs",
                    () -> new StairBlock(() -> COLORED_PLANKS.get(index).get().defaultBlockState(),
                            BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS).mapColor(color.getMapColor())));
            stairs.add(block);
        }
        return stairs;
    }

    public static final List<RegistryObject<Block>> COLORED_SLABS = registerColoredSlabs();
    private static List<RegistryObject<Block>> registerColoredSlabs(){
        List<RegistryObject<Block>> slabs = new ArrayList<>();
        for (DyeColor color : COLORS) {
            RegistryObject<Block> block = registerBlock( color.getName() + "_slab",
                    () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).mapColor(color.getMapColor())));
            slabs.add(block);
        }
        return slabs;
    }

    public static final List<RegistryObject<Block>> COLORED_FENCES = registerColoredFences();
    private static List<RegistryObject<Block>> registerColoredFences(){
        List<RegistryObject<Block>> fence = new ArrayList<>();
        for (DyeColor color : COLORS) {
            RegistryObject<Block> block = registerBlock( color.getName() + "_fence",
                    () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).mapColor(color.getMapColor())));
            fence.add(block);
        }
        return fence;
    }

    public static final List<RegistryObject<Block>> COLORED_FENCE_GATES = registerColoredFenceGates();
    private static List<RegistryObject<Block>> registerColoredFenceGates(){
        List<RegistryObject<Block>> fenceGate = new ArrayList<>();
        for (DyeColor color : COLORS) {
            RegistryObject<Block> block = registerBlock( color.getName() + "_fence_gate",
                    () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).mapColor(color.getMapColor()),
                            SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
            fenceGate.add(block);
        }
        return fenceGate;
    }

    public static final List<RegistryObject<Block>> COLORED_DOORS = registerColoredDoors();
    private static List<RegistryObject<Block>> registerColoredDoors(){
        List<RegistryObject<Block>> doors = new ArrayList<>();
        for (DyeColor color : COLORS) {
            RegistryObject<Block> block = registerBlock( color.getName() + "_door",
                    () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).mapColor(color.getMapColor()), BlockSetType.OAK));
            doors.add(block);
        }
        return doors;
    }

    public static final List<RegistryObject<Block>> COLORED_TRAPDOORS = registerColoredTrapdoors();
    private static List<RegistryObject<Block>> registerColoredTrapdoors(){
        List<RegistryObject<Block>> trapdoors = new ArrayList<>();
        for (DyeColor color : COLORS) {
            RegistryObject<Block> block = registerBlock( color.getName() + "_trapdoor",
                    () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).mapColor(color.getMapColor()).isValidSpawn(ModBlocks::never), BlockSetType.OAK));
            trapdoors.add(block);
        }
        return trapdoors;
    }

    public static final List<RegistryObject<Block>> COLORED_PRESSURE_PLATES = registerColoredPressurePlates();
    private static List<RegistryObject<Block>> registerColoredPressurePlates(){
        List<RegistryObject<Block>> pressurePlates = new ArrayList<>();
        for (DyeColor color : COLORS) {
            RegistryObject<Block> block = registerBlock( color.getName() + "_pressure_plate",
                    () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                            BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE).mapColor(color.getMapColor()), BlockSetType.OAK));
            pressurePlates.add(block);
        }
        return pressurePlates;
    }

    public static final List<RegistryObject<Block>> COLORED_BUTTONS = registerColoredButtons();
    private static List<RegistryObject<Block>> registerColoredButtons(){
        List<RegistryObject<Block>> button = new ArrayList<>();
        for (DyeColor color : COLORS) {
            RegistryObject<Block> block = registerBlock( color.getName() + "_button",
                    () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).mapColor(color.getMapColor()),
                            BlockSetType.OAK, 30, true));
            button.add(block);
        }
        return button;
    }

    public static final List<RegistryObject<Block>> COLORED_SIGNS = registerColoredSign();
    private static List<RegistryObject<Block>> registerColoredSign(){
        List<RegistryObject<Block>> hangingSign = new ArrayList<>();
        for (DyeColor color : COLORS) {
            int index = COLORS.indexOf(color);
            RegistryObject<Block> block = BLOCKS.register( color.getName() + "_sign",
                    () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), COLORED_WOOD_TYPES.get(index)));
            hangingSign.add(block);
        }
        return hangingSign;
    }

    public static final List<RegistryObject<Block>> COLORED_WALL_SIGNS = registerColoredWallSign();
    private static List<RegistryObject<Block>> registerColoredWallSign(){
        List<RegistryObject<Block>> hangingSign = new ArrayList<>();
        for (DyeColor color : COLORS) {
            int index = COLORS.indexOf(color);
            RegistryObject<Block> block = BLOCKS.register( color.getName() + "_wall_sign",
                    () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), COLORED_WOOD_TYPES.get(index)));
            hangingSign.add(block);
        }
        return hangingSign;
    }

    public static final List<RegistryObject<Block>> COLORED_HANGING_SIGNS = registerColoredHangingSign();
    private static List<RegistryObject<Block>> registerColoredHangingSign(){
        List<RegistryObject<Block>> hangingSign = new ArrayList<>();
        for (DyeColor color : COLORS) {
            int index = COLORS.indexOf(color);
            RegistryObject<Block> block = BLOCKS.register( color.getName() + "_hanging_sign",
                    () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), COLORED_WOOD_TYPES.get(index)));
            hangingSign.add(block);
        }
        return hangingSign;
    }

    public static final List<RegistryObject<Block>> COLORED_WALL_HANGING_SIGNS = registerColoredWallHangingSign();
    private static List<RegistryObject<Block>> registerColoredWallHangingSign(){
        List<RegistryObject<Block>> hangingSign = new ArrayList<>();
        for (DyeColor color : COLORS) {
            int index = COLORS.indexOf(color);
            RegistryObject<Block> block = BLOCKS.register( color.getName() + "_wall_hanging_sign",
                    () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), COLORED_WOOD_TYPES.get(index)));
            hangingSign.add(block);
        }
        return hangingSign;
    }

    private static boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    private static boolean never(BlockState pState, BlockGetter pGetter, BlockPos pPos) {
        return false;
    }

    private static Boolean never(BlockState p_50779_, BlockGetter p_50780_, BlockPos p_50781_, EntityType<?> p_50782_) {
        return (boolean) false;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
