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
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.datagen.recipe.FletchingRecipeBuilder;
import net.theobl.extension.item.ModItems;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static net.neoforged.neoforge.common.conditions.NeoForgeConditions.*;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    @Override
    protected void buildRecipes() {
        generateForEnabledBlockFamilies(FeatureFlagSet.of(FeatureFlags.VANILLA));

        shapeless(RecipeCategory.TRANSPORTATION, ModItems.SPAWNER_MINECART)
                .requires(Blocks.SPAWNER)
                .requires(Items.MINECART)
                .unlockedBy("has_minecart", has(Items.MINECART))
                .save(output);

        oneToOneConversionRecipe(Items.NETHER_WART, Items.NETHER_WART_BLOCK, "nether_wart", 9);
        oneToOneConversionRecipe(ModItems.BLUE_NETHER_WART, Items.WARPED_WART_BLOCK, null, 9);
        threeByThreePacker(RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_WART_BLOCK, ModItems.BLUE_NETHER_WART);
        shapeless(RecipeCategory.MISC, ModItems.RED_NETHER_BRICK, 2)
                .requires(Items.NETHER_BRICK).requires(Items.NETHER_WART)
                .unlockedBy(getHasName(Items.NETHER_BRICK), has(Items.NETHER_BRICK)).save(output);
        shapeless(RecipeCategory.MISC, ModItems.BLUE_NETHER_BRICK, 2)
                .requires(Items.NETHER_BRICK).requires(ModItems.BLUE_NETHER_WART)
                .unlockedBy(getHasName(Items.NETHER_BRICK), has(Items.NETHER_BRICK)).save(output);

        twoByTwoPacker(Blocks.RED_NETHER_BRICKS, ModItems.RED_NETHER_BRICK);
        twoByTwoPacker(ModBlocks.BLUE_NETHER_BRICKS, ModItems.BLUE_NETHER_BRICK);

        wallRecipes(ModBlocks.STONE_WALL.get(), Items.STONE);
        wallRecipes(ModBlocks.POLISHED_GRANITE_WALL.get(), Items.POLISHED_GRANITE);
        wallRecipes(ModBlocks.POLISHED_DIORITE_WALL.get(), Items.POLISHED_DIORITE);
        wallRecipes(ModBlocks.POLISHED_ANDESITE_WALL.get(), Items.POLISHED_ANDESITE);
        wallRecipes(ModBlocks.PRISMARINE_BRICK_WALL.get(), Items.PRISMARINE_BRICKS);
        wallRecipes(ModBlocks.DARK_PRISMARINE_WALL.get(), Items.DARK_PRISMARINE);
        wallRecipes(ModBlocks.PURPUR_WALL.get(), Items.PURPUR_BLOCK);
        wallRecipes(ModBlocks.QUARTZ_WALL.get(), Items.QUARTZ_BLOCK);

        chiseled(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_GRANITE, Items.GRANITE_SLAB);
        chiseled(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_DIORITE, Items.DIORITE_SLAB);
        chiseled(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_ANDESITE, Items.ANDESITE_SLAB);
        chiseled(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_BRICKS, Items.BRICK_SLAB);
        chiseled(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_MUD_BRICKS, Items.MUD_BRICK_SLAB);
        chiseled(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_PRISMARINE, Items.PRISMARINE_SLAB);
        chiseled(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_END_STONE_BRICKS, Items.END_STONE_BRICK_SLAB);

        stairBuilder(ModBlocks.SMOOTH_STONE_STAIRS, Ingredient.of(Items.SMOOTH_STONE)).unlockedBy(getHasName(Items.SMOOTH_STONE), has(Items.SMOOTH_STONE))
                .save(output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_STAIRS, Blocks.SMOOTH_STONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHERITE_STAIRS, Blocks.NETHERITE_BLOCK);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TINTED_GLASS_PANE, 2)
                .define('G', Blocks.GLASS_PANE)
                .define('S', Items.AMETHYST_SHARD)
                .pattern(" S ")
                .pattern("SGS")
                .pattern(" S ")
                .unlockedBy("has_amethyst_shard", has(Items.AMETHYST_SHARD))
                .save(output, Extension.MODID + ":" + getConversionRecipeName(ModBlocks.TINTED_GLASS_PANE, Blocks.GLASS_PANE));
        shaped(RecipeCategory.DECORATIONS, ModBlocks.TINTED_GLASS_PANE, 16)
                .define('#', Blocks.TINTED_GLASS)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_tinted_glass", has(Blocks.TINTED_GLASS))
                .save(output);

        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.STONE_BRICKS, ModBlocks.POLISHED_STONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_STONE, Blocks.STONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_STONE_STAIRS, Blocks.STONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_STONE_STAIRS, ModBlocks.POLISHED_STONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_STONE_SLAB, Blocks.STONE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_STONE_SLAB, ModBlocks.POLISHED_STONE, 2);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, ModBlocks.POLISHED_STONE_WALL, Blocks.STONE);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, ModBlocks.POLISHED_STONE_WALL, ModBlocks.POLISHED_STONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.STONE_BRICK_STAIRS, ModBlocks.POLISHED_STONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.STONE_BRICK_SLAB, ModBlocks.POLISHED_STONE, 2);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, Blocks.STONE_BRICK_WALL, ModBlocks.POLISHED_STONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.CHISELED_STONE_BRICKS, ModBlocks.POLISHED_STONE);

        mossyRecipes(ModBlocks.MOSSY_COBBLED_DEEPSLATE, Blocks.COBBLED_DEEPSLATE, "mossy_cobbled_deepslate");
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_COBBLED_DEEPSLATE_STAIRS, ModBlocks.MOSSY_COBBLED_DEEPSLATE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_COBBLED_DEEPSLATE_SLAB, ModBlocks.MOSSY_COBBLED_DEEPSLATE, 2);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, ModBlocks.MOSSY_COBBLED_DEEPSLATE_WALL, ModBlocks.MOSSY_COBBLED_DEEPSLATE);

        mossyRecipes(ModBlocks.MOSSY_DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICKS, "mossy_deepslate_bricks");
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DEEPSLATE_BRICK_STAIRS, ModBlocks.MOSSY_DEEPSLATE_BRICKS);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DEEPSLATE_BRICK_SLAB, ModBlocks.MOSSY_DEEPSLATE_BRICKS, 2);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, ModBlocks.MOSSY_DEEPSLATE_BRICK_WALL, ModBlocks.MOSSY_DEEPSLATE_BRICKS);

        shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.STONE_BRICKS, 4)
                .define('#', ModBlocks.POLISHED_STONE)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_polished_stone", has(ModBlocks.POLISHED_STONE))
                .save(output);
        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_STONE, 4)
                .define('#', Blocks.STONE)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_stone", has(Blocks.STONE))
                .save(output);

        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_BASALT_STAIRS, Blocks.SMOOTH_BASALT);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_BASALT_SLAB, Blocks.SMOOTH_BASALT,2);

        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BRICK_STAIRS, Blocks.QUARTZ_BRICKS);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BRICK_SLAB, Blocks.QUARTZ_BRICKS,2);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, ModBlocks.QUARTZ_BRICK_WALL, Blocks.QUARTZ_BRICKS);

        tilesRecipes(ModBlocks.NETHER_BRICK_TILES, Blocks.NETHER_BRICKS);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHER_BRICK_TILE_STAIRS, ModBlocks.NETHER_BRICK_TILES);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHER_BRICK_TILE_SLAB, ModBlocks.NETHER_BRICK_TILES, 2);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, ModBlocks.NETHER_BRICK_TILE_WALL, ModBlocks.NETHER_BRICK_TILES);

        smeltingResultFromBase(ModBlocks.CRACKED_RED_NETHER_BRICKS, Items.RED_NETHER_BRICKS);
        fence(ModBlocks.RED_NETHER_BRICK_FENCE.get(), Blocks.RED_NETHER_BRICKS, ModItems.RED_NETHER_BRICK.get());
        chiseled(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICK_SLAB);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS);
        tilesRecipes(ModBlocks.RED_NETHER_BRICK_TILES, Blocks.RED_NETHER_BRICKS);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_NETHER_BRICK_TILE_STAIRS, ModBlocks.RED_NETHER_BRICK_TILES);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_NETHER_BRICK_TILE_SLAB, ModBlocks.RED_NETHER_BRICK_TILES, 2);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, ModBlocks.RED_NETHER_BRICK_TILE_WALL, ModBlocks.RED_NETHER_BRICK_TILES);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_NETHER_BRICKS)
                .define('W', ModItems.BLUE_NETHER_WART)
                .define('N', Items.NETHER_BRICK)
                .pattern("NW")
                .pattern("WN")
                .unlockedBy("has_blue_nether_wart", has(ModItems.BLUE_NETHER_WART))
                .save(output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_NETHER_BRICK_SLAB, ModBlocks.BLUE_NETHER_BRICKS, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_NETHER_BRICK_STAIRS, ModBlocks.BLUE_NETHER_BRICKS);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, ModBlocks.BLUE_NETHER_BRICK_WALL, ModBlocks.BLUE_NETHER_BRICKS);
        fence(ModBlocks.BLUE_NETHER_BRICK_FENCE.get(), ModBlocks.BLUE_NETHER_BRICKS, ModItems.BLUE_NETHER_BRICK.get());
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_BLUE_NETHER_BRICKS, ModBlocks.BLUE_NETHER_BRICKS);
        tilesRecipes(ModBlocks.BLUE_NETHER_BRICK_TILES, ModBlocks.BLUE_NETHER_BRICKS);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_NETHER_BRICK_TILE_STAIRS, ModBlocks.BLUE_NETHER_BRICK_TILES);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_NETHER_BRICK_TILE_SLAB, ModBlocks.BLUE_NETHER_BRICK_TILES, 2);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, ModBlocks.BLUE_NETHER_BRICK_TILE_WALL, ModBlocks.BLUE_NETHER_BRICK_TILES);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_SANDSTONE, 1)
                .define('#', Items.SOUL_SAND)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(Items.SOUL_SAND), has(Items.SOUL_SAND))
                .save(output.withConditions(not(modLoaded("quark"))));
        stairBuilder(ModBlocks.SOUL_SANDSTONE_STAIRS, Ingredient.of(ModBlocks.SOUL_SANDSTONE, ModBlocks.CHISELED_SOUL_SANDSTONE, ModBlocks.CUT_SOUL_SANDSTONE))
                .unlockedBy("has_soul_sandstone", has(ModBlocks.SOUL_SANDSTONE))
                .unlockedBy("has_chiseled_soul_sandstone", has(ModBlocks.CHISELED_SOUL_SANDSTONE))
                .unlockedBy("has_cut_soul_sandstone", has(ModBlocks.CUT_SOUL_SANDSTONE))
                .save(output);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_SANDSTONE_SLAB, Ingredient.of(ModBlocks.SOUL_SANDSTONE, ModBlocks.CHISELED_SOUL_SANDSTONE))
                .unlockedBy("has_soul_sandstone", has(ModBlocks.SOUL_SANDSTONE))
                .unlockedBy("has_chiseled_soul_sandstone", has(ModBlocks.CHISELED_SOUL_SANDSTONE))
                .save(output);
        wallRecipes(ModBlocks.SOUL_SANDSTONE_WALL, ModBlocks.SOUL_SANDSTONE);
        chiseled(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SOUL_SANDSTONE, ModBlocks.SOUL_SANDSTONE_SLAB);
        smeltingResultFromBase(ModBlocks.SMOOTH_SOUL_SANDSTONE, ModBlocks.SOUL_SANDSTONE);
        cutRecipes(ModBlocks.CUT_SOUL_SANDSTONE, ModBlocks.SOUL_SANDSTONE);

        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_SANDSTONE_STAIRS, ModBlocks.SOUL_SANDSTONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_SANDSTONE_SLAB, ModBlocks.SOUL_SANDSTONE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SOUL_SANDSTONE, ModBlocks.SOUL_SANDSTONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS, ModBlocks.SMOOTH_SOUL_SANDSTONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB, ModBlocks.SMOOTH_SOUL_SANDSTONE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SOUL_SANDSTONE_SLAB, ModBlocks.SOUL_SANDSTONE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SOUL_SANDSTONE_SLAB, ModBlocks.CUT_SOUL_SANDSTONE, 2);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_O_LANTERN)
                .define('A', Blocks.CARVED_PUMPKIN)
                .define('B', Blocks.SOUL_TORCH)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_carved_pumpkin", has(Blocks.CARVED_PUMPKIN))
                .save(output);
        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COPPER_O_LANTERN)
                .define('A', Blocks.CARVED_PUMPKIN)
                .define('B', Blocks.COPPER_TORCH)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_carved_pumpkin", has(Blocks.CARVED_PUMPKIN))
                .save(output);
        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.REDSTONE_O_LANTERN)
                .define('A', Blocks.CARVED_PUMPKIN)
                .define('B', Blocks.REDSTONE_TORCH)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_carved_pumpkin", has(Blocks.CARVED_PUMPKIN))
                .save(output);

        shaped(RecipeCategory.DECORATIONS, ModBlocks.REDSTONE_LANTERN)
                .define('#', Items.REDSTONE_TORCH)
                .define('X', Items.IRON_NUGGET)
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(output);

        shaped(RecipeCategory.DECORATIONS, ModBlocks.REDSTONE_CAMPFIRE)
                .define('L', ItemTags.LOGS)
                .define('S', Items.STICK)
                .define('C', Tags.Items.DUSTS_REDSTONE)
                .pattern(" S ")
                .pattern("SCS")
                .pattern("LLL")
                .unlockedBy("has_stick", has(Items.STICK))
                .unlockedBy("has_redstone", has(Tags.Items.DUSTS_REDSTONE))
                .save(output);

        shaped(RecipeCategory.DECORATIONS, ModBlocks.COPPER_CAMPFIRE)
                .define('L', ItemTags.LOGS)
                .define('S', Items.STICK)
                .define('C', Items.COPPER_NUGGET)
                .pattern(" S ")
                .pattern("SCS")
                .pattern("LLL")
                .unlockedBy("has_stick", has(Items.STICK))
                .unlockedBy("has_redstone", has(Items.COPPER_NUGGET))
                .save(output);

        fletching(RecipeCategory.COMBAT, Items.ARROW, 8)
                .requires(Items.FLINT)
                .requires(Items.STICK)
                .requires(Tags.Items.FEATHERS)
                .unlockedBy("has_feather", this.has(Items.FEATHER))
                .unlockedBy("has_flint", this.has(Items.FLINT))
                .save(this.output, Extension.asResource("arrow").toString());
    }

    protected void generateForEnabledBlockFamilies(FeatureFlagSet featureFlagSet) {
        ModBlockFamilies.getAllFamilies()
                .filter(BlockFamily::shouldGenerateRecipe)
                .forEach(family -> generateRecipes(family, featureFlagSet));
    }

    protected void oneToOneConversionRecipe(ItemLike result, ItemLike ingredient, @Nullable String group, int resultCount) {
        shapeless(RecipeCategory.MISC, result, resultCount)
                .requires(ingredient)
                .group(group)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(output, Extension.MODID + ":" + getConversionRecipeName(result, ingredient));
    }

    protected void netheriteSmithing(Item ingredientItem, RecipeCategory category, Item resultItem) {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(ingredientItem), Ingredient.of(Items.NETHERITE_INGOT), category, resultItem)
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(output, Extension.MODID + ":" + getItemName(resultItem) + "_smithing");
    }

    protected void twoByTwoPacker(ItemLike packed, ItemLike unpacked) {
        shaped(RecipeCategory.BUILDING_BLOCKS, packed, 1)
                .define('#', unpacked)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(unpacked), has(unpacked))
                .save(output, Extension.MODID + ":" + getConversionRecipeName(packed, unpacked));
    }

    protected void stonecutterResultFromBase(RecipeCategory category, ItemLike result, ItemLike material) {
        stonecutterResultFromBase(category, result, material, 1);
    }

    protected void stonecutterResultFromBase(RecipeCategory category, ItemLike result, ItemLike material, int resultCount) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), category, result, resultCount)
                .unlockedBy(getHasName(material), has(material))
                .save(output, Extension.MODID + ":" + getConversionRecipeName(result, material) + "_stonecutting");
    }

    protected void fence(Block fence, ItemLike material, Item middleItem) {
        int i = !fence.defaultBlockState().is(BlockTags.WOODEN_FENCES) ? 6 : 3;
        shaped(RecipeCategory.DECORATIONS, fence, i)
                .define('W', material)
                .define('#', middleItem)
                .pattern("W#W")
                .pattern("W#W")
                .unlockedBy(getHasName(material), has(material))
                .save(output);
    }

    protected void wallRecipes(ItemLike wall, ItemLike material) {
        wall(RecipeCategory.DECORATIONS, wall, material);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, wall, material);
    }

    protected void cutRecipes(ItemLike cutResult, ItemLike material) {
        cut(RecipeCategory.BUILDING_BLOCKS, cutResult, material);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, cutResult, material);
    }

    protected void tilesRecipes(ItemLike tiles, ItemLike material) {
        shaped(RecipeCategory.BUILDING_BLOCKS, tiles, 4)
                .define('S', material)
                .pattern("SS")
                .pattern("SS")
                .unlockedBy(getHasName(material), has(material))
                .save(output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, tiles, material);
    }

    protected void mossyRecipes(ItemLike result, ItemLike material, String groupName) {
        shapeless(RecipeCategory.BUILDING_BLOCKS, result)
                .requires(material)
                .requires(Blocks.VINE)
                .group(groupName)
                .unlockedBy("has_vine", has(Blocks.VINE))
                .save(output, Extension.MODID + ":" + getConversionRecipeName(result, Blocks.VINE));
        shapeless(RecipeCategory.BUILDING_BLOCKS, result)
                .requires(material)
                .requires(Blocks.MOSS_BLOCK)
                .group(groupName)
                .unlockedBy("has_moss_block", has(Blocks.MOSS_BLOCK))
                .save(output, Extension.MODID + ":" + getConversionRecipeName(result, Blocks.MOSS_BLOCK));
    }

    protected FletchingRecipeBuilder fletching(RecipeCategory category, ItemLike result, int count) {
        return FletchingRecipeBuilder.fletching(this.items, category, result, count);
    }

    public static final class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider lookupProvider, RecipeOutput output) {
            return new ModRecipeProvider(lookupProvider, output);
        }

        @Override
        public String getName() {
            return "Extension recipes";
        }
    }
}
