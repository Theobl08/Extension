package net.theobl.extension.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.item.ModItems;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        generateForEnabledBlockFamilies(recipeOutput, FeatureFlagSet.of(FeatureFlags.VANILLA));

        netheriteSmithing(recipeOutput, Items.DIAMOND_HORSE_ARMOR, RecipeCategory.COMBAT, ModItems.NETHERITE_HORSE_ARMOR.asItem());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, ModItems.SPAWNER_MINECART)
                .requires(Blocks.SPAWNER)
                .requires(Items.MINECART)
                .unlockedBy("has_minecart", has(Items.MINECART))
                .save(recipeOutput);

        oneToOneConversionRecipe(recipeOutput, Items.NETHER_WART, Items.NETHER_WART_BLOCK, "nether_wart", 9);
        oneToOneConversionRecipe(recipeOutput, ModItems.BLUE_NETHER_WART, Items.WARPED_WART_BLOCK, null, 9);
        threeByThreePacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_WART_BLOCK, ModItems.BLUE_NETHER_WART);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RED_NETHER_BRICK, 2)
                .requires(Items.NETHER_BRICK).requires(Items.NETHER_WART)
                .unlockedBy(getHasName(Items.NETHER_BRICK), has(Items.NETHER_BRICK)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLUE_NETHER_BRICK, 2)
                .requires(Items.NETHER_BRICK).requires(ModItems.BLUE_NETHER_WART)
                .unlockedBy(getHasName(Items.NETHER_BRICK), has(Items.NETHER_BRICK)).save(recipeOutput);

        twoByTwoPacker(recipeOutput, Blocks.RED_NETHER_BRICKS, ModItems.RED_NETHER_BRICK);
        twoByTwoPacker(recipeOutput, ModBlocks.BLUE_NETHER_BRICKS, ModItems.BLUE_NETHER_BRICK);

        wallRecipes(recipeOutput, ModBlocks.STONE_WALL.get(), Items.STONE);
        wallRecipes(recipeOutput, ModBlocks.POLISHED_GRANITE_WALL.get(), Items.POLISHED_GRANITE);
        wallRecipes(recipeOutput, ModBlocks.POLISHED_DIORITE_WALL.get(), Items.POLISHED_DIORITE);
        wallRecipes(recipeOutput, ModBlocks.POLISHED_ANDESITE_WALL.get(), Items.POLISHED_ANDESITE);
        wallRecipes(recipeOutput, ModBlocks.PRISMARINE_BRICK_WALL.get(), Items.PRISMARINE_BRICKS);
        wallRecipes(recipeOutput, ModBlocks.DARK_PRISMARINE_WALL.get(), Items.DARK_PRISMARINE);
        wallRecipes(recipeOutput, ModBlocks.PURPUR_WALL.get(), Items.PURPUR_BLOCK);
        wallRecipes(recipeOutput, ModBlocks.QUARTZ_WALL.get(), Items.QUARTZ_BLOCK);

        stairBuilder(ModBlocks.SMOOTH_STONE_STAIRS, Ingredient.of(Items.SMOOTH_STONE)).unlockedBy(getHasName(Items.SMOOTH_STONE), has(Items.SMOOTH_STONE))
                .save(recipeOutput);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_STAIRS, Blocks.SMOOTH_STONE);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHERITE_STAIRS, Blocks.NETHERITE_BLOCK);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TINTED_GLASS_PANE, 2)
                .define('G', Blocks.GLASS_PANE)
                .define('S', Items.AMETHYST_SHARD)
                .pattern(" S ")
                .pattern("SGS")
                .pattern(" S ")
                .unlockedBy("has_amethyst_shard", has(Items.AMETHYST_SHARD))
                .save(recipeOutput, Extension.MODID + ":" + getConversionRecipeName(ModBlocks.TINTED_GLASS_PANE, Blocks.GLASS_PANE));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.TINTED_GLASS_PANE, 16)
                .define('#', Blocks.TINTED_GLASS)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_tinted_glass", has(Blocks.TINTED_GLASS))
                .save(recipeOutput);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_BASALT_STAIRS, Blocks.SMOOTH_BASALT);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_BASALT_SLAB, Blocks.SMOOTH_BASALT,2);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BRICK_STAIRS, Blocks.QUARTZ_BRICKS);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BRICK_SLAB, Blocks.QUARTZ_BRICKS,2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.QUARTZ_BRICK_WALL, Blocks.QUARTZ_BRICKS);

        tilesRecipes(recipeOutput, ModBlocks.NETHER_BRICK_TILES, Blocks.NETHER_BRICKS);

        smeltingResultFromBase(recipeOutput, ModBlocks.CRACKED_RED_NETHER_BRICKS, Items.RED_NETHER_BRICKS);
        fence(recipeOutput, ModBlocks.RED_NETHER_BRICK_FENCE.get(), Blocks.RED_NETHER_BRICKS, ModItems.RED_NETHER_BRICK.get());
        chiseled(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICK_SLAB);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS);
        tilesRecipes(recipeOutput, ModBlocks.RED_NETHER_BRICK_TILES, Blocks.RED_NETHER_BRICKS);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_NETHER_BRICKS)
                .define('W', ModItems.BLUE_NETHER_WART)
                .define('N', Items.NETHER_BRICK)
                .pattern("NW")
                .pattern("WN")
                .unlockedBy("has_blue_nether_wart", has(ModItems.BLUE_NETHER_WART))
                .save(recipeOutput);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_NETHER_BRICK_SLAB, ModBlocks.BLUE_NETHER_BRICKS, 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_NETHER_BRICK_STAIRS, ModBlocks.BLUE_NETHER_BRICKS);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.BLUE_NETHER_BRICK_WALL, ModBlocks.BLUE_NETHER_BRICKS);
        fence(recipeOutput, ModBlocks.BLUE_NETHER_BRICK_FENCE.get(), ModBlocks.BLUE_NETHER_BRICKS, ModItems.BLUE_NETHER_BRICK.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_BLUE_NETHER_BRICKS, ModBlocks.BLUE_NETHER_BRICKS);
        tilesRecipes(recipeOutput, ModBlocks.BLUE_NETHER_BRICK_TILES, ModBlocks.BLUE_NETHER_BRICKS);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_SANDSTONE, 1)
                .define('#', Items.SOUL_SAND)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(Items.SOUL_SAND), has(Items.SOUL_SAND))
                .save(recipeOutput.withConditions(not(modLoaded("quark"))));
        stairBuilder(ModBlocks.SOUL_SANDSTONE_STAIRS, Ingredient.of(ModBlocks.SOUL_SANDSTONE, ModBlocks.CHISELED_SOUL_SANDSTONE, ModBlocks.CUT_SOUL_SANDSTONE))
                .unlockedBy("has_soul_sandstone", has(ModBlocks.SOUL_SANDSTONE))
                .unlockedBy("has_chiseled_soul_sandstone", has(ModBlocks.CHISELED_SOUL_SANDSTONE))
                .unlockedBy("has_cut_soul_sandstone", has(ModBlocks.CUT_SOUL_SANDSTONE))
                .save(recipeOutput);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_SANDSTONE_SLAB, Ingredient.of(ModBlocks.SOUL_SANDSTONE, ModBlocks.CHISELED_SOUL_SANDSTONE))
                .unlockedBy("has_soul_sandstone", has(ModBlocks.SOUL_SANDSTONE))
                .unlockedBy("has_chiseled_soul_sandstone", has(ModBlocks.CHISELED_SOUL_SANDSTONE))
                .save(recipeOutput);
        wallRecipes(recipeOutput, ModBlocks.SOUL_SANDSTONE_WALL, ModBlocks.SOUL_SANDSTONE);
        chiseled(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SOUL_SANDSTONE, ModBlocks.SOUL_SANDSTONE_SLAB);
        smeltingResultFromBase(recipeOutput, ModBlocks.SMOOTH_SOUL_SANDSTONE, ModBlocks.SOUL_SANDSTONE);
        cutRecipes(recipeOutput, ModBlocks.CUT_SOUL_SANDSTONE, ModBlocks.SOUL_SANDSTONE);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_SANDSTONE_STAIRS, ModBlocks.SOUL_SANDSTONE);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_SANDSTONE_SLAB, ModBlocks.SOUL_SANDSTONE, 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SOUL_SANDSTONE, ModBlocks.SOUL_SANDSTONE);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS, ModBlocks.SMOOTH_SOUL_SANDSTONE);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB, ModBlocks.SMOOTH_SOUL_SANDSTONE, 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SOUL_SANDSTONE_SLAB, ModBlocks.SOUL_SANDSTONE, 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SOUL_SANDSTONE_SLAB, ModBlocks.CUT_SOUL_SANDSTONE, 2);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_O_LANTERN)
                .define('A', Blocks.CARVED_PUMPKIN)
                .define('B', Blocks.SOUL_TORCH)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_carved_pumpkin", has(Blocks.CARVED_PUMPKIN))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.REDSTONE_O_LANTERN)
                .define('A', Blocks.CARVED_PUMPKIN)
                .define('B', Blocks.REDSTONE_TORCH)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_carved_pumpkin", has(Blocks.CARVED_PUMPKIN))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.REDSTONE_LANTERN)
                .define('#', Items.REDSTONE_TORCH)
                .define('X', Items.IRON_NUGGET)
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.REDSTONE_CAMPFIRE)
                .define('L', ItemTags.LOGS)
                .define('S', Items.STICK)
                .define('C', Tags.Items.DUSTS_REDSTONE)
                .pattern(" S ")
                .pattern("SCS")
                .pattern("LLL")
                .unlockedBy("has_stick", has(Items.STICK))
                .unlockedBy("has_redstone", has(Tags.Items.DUSTS_REDSTONE))
                .save(recipeOutput);
    }

    protected void generateForEnabledBlockFamilies(RecipeOutput enabledFeatures, FeatureFlagSet featureFlagSet) {
        ModBlockFamilies.getAllFamilies()
                .filter(BlockFamily::shouldGenerateRecipe)
                .forEach(family -> generateRecipes(enabledFeatures, family, featureFlagSet));
    }

    protected static void oneToOneConversionRecipe(RecipeOutput recipeOutput, ItemLike result, ItemLike ingredient, @Nullable String group, int resultCount) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultCount)
                .requires(ingredient)
                .group(group)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput,Extension.MODID + ":" + getConversionRecipeName(result, ingredient));
    }

    protected static void netheriteSmithing(RecipeOutput recipeOutput, Item ingredientItem, RecipeCategory category, Item resultItem) {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(ingredientItem), Ingredient.of(Items.NETHERITE_INGOT), category, resultItem)
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(recipeOutput, Extension.MODID + ":" + getItemName(resultItem) + "_smithing");
    }

    protected static void twoByTwoPacker(RecipeOutput recipeOutput, ItemLike packed, ItemLike unpacked) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, packed, 1)
                .define('#', unpacked)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(unpacked), has(unpacked))
                .save(recipeOutput, Extension.MODID + ":" + getConversionRecipeName(packed, unpacked));
    }

    protected static void stonecutterResultFromBase(RecipeOutput recipeOutput, RecipeCategory category, ItemLike result, ItemLike material) {
        stonecutterResultFromBase(recipeOutput, category, result, material, 1);
    }

    protected static void stonecutterResultFromBase(RecipeOutput recipeOutput, RecipeCategory category, ItemLike result, ItemLike material, int resultCount) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), category, result, resultCount)
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput,Extension.MODID + ":" + getConversionRecipeName(result, material) + "_stonecutting");
    }

    protected static void fence(RecipeOutput recipeOutput, Block fence, ItemLike material, Item middleItem) {
        int i = !fence.defaultBlockState().is(BlockTags.WOODEN_FENCES) ? 6 : 3;
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, fence, i)
                .define('W', material)
                .define('#', middleItem)
                .pattern("W#W")
                .pattern("W#W")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    protected static void wallRecipes(RecipeOutput recipeOutput, ItemLike wall, ItemLike material) {
        wall(recipeOutput, RecipeCategory.DECORATIONS, wall, material);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, wall, material);
    }

    protected static void cutRecipes(RecipeOutput recipeOutput, ItemLike cutResult, ItemLike material) {
        cut(recipeOutput, RecipeCategory.BUILDING_BLOCKS, cutResult, material);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, cutResult, material);
    }

    protected static void tilesRecipes(RecipeOutput recipeOutput, ItemLike tiles, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, tiles, 4)
                .define('S', material)
                .pattern("SS")
                .pattern("SS")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, tiles, material);
    }
}
