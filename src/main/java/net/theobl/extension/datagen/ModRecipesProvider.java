package net.theobl.extension.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.item.ModItems;
import net.theobl.extension.util.ModTags;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipesProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> AMETHYST_SMELTABLE =
            List.of(ModBlocks.AMETHYST_ORE.get(), ModBlocks.DEEPSLATE_AMETHYST_ORE.get());

    private static final List<Item> CONCRETES =
            List.of(Items.WHITE_CONCRETE, Items.LIGHT_GRAY_CONCRETE, Items.GRAY_CONCRETE, Items.BLACK_CONCRETE,
                    Items.BROWN_CONCRETE, Items.RED_CONCRETE, Items.ORANGE_CONCRETE, Items.YELLOW_CONCRETE,
                    Items.LIME_CONCRETE, Items.GREEN_CONCRETE, Items.CYAN_CONCRETE, Items.LIGHT_BLUE_CONCRETE,
                    Items.BLUE_CONCRETE, Items.PURPLE_CONCRETE, Items.MAGENTA_CONCRETE, Items.PINK_CONCRETE);

    private static final List<Item> DYES =
            List.of(Items.WHITE_DYE, Items.LIGHT_GRAY_DYE, Items.GRAY_DYE, Items.BLACK_DYE,
                    Items.BROWN_DYE, Items.RED_DYE, Items.ORANGE_DYE, Items.YELLOW_DYE,
                    Items.LIME_DYE, Items.GREEN_DYE, Items.CYAN_DYE, Items.LIGHT_BLUE_DYE,
                    Items.BLUE_DYE, Items.PURPLE_DYE, Items.MAGENTA_DYE, Items.PINK_DYE);

    public ModRecipesProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        for (RegistryObject<Block> quiltedConcrete : ModBlocks.QUILTED_CONCRETES) {
            int index = ModBlocks.QUILTED_CONCRETES.indexOf(quiltedConcrete);
            stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, quiltedConcrete.get(), CONCRETES.get(index));
        }
        for (RegistryObject<Block> glazedConcrete : ModBlocks.GLAZED_CONCRETES) {
            int index = ModBlocks.GLAZED_CONCRETES.indexOf(glazedConcrete);
            smeltingResultFromBase(pWriter, glazedConcrete.get(), CONCRETES.get(index));
        }

        for (RegistryObject<Block> antiblock : ModBlocks.ANTIBLOCKS) {
            int index = ModBlocks.ANTIBLOCKS.indexOf(antiblock);
            Item dye = DYES.get(index);
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, antiblock.get())
                    .requires(ModTags.Items.ANTIBLOCK).requires(dye).unlockedBy("has_needed_dye", has(dye)).save(pWriter);
        }
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ANTIBLOCKS.get(0).get(), 8)
                .pattern("SSS")
                .pattern("SGS")
                .pattern("SSS")
                .define('S', Tags.Items.STONE)
                .define('G', Items.GLOWSTONE_DUST)
                .unlockedBy(getHasName(Items.GLOWSTONE_DUST), has(Items.GLOWSTONE_DUST))
                .save(pWriter, Extension.MOD_ID + ":" + getItemName(ModBlocks.ANTIBLOCKS.get(0).get()) + "_from_glowstone");

        // Soul Sandstone
        ConditionalRecipe.builder().addCondition(not(modLoaded("quark")))
                .addRecipe(this::soulSandstone)
                .build(pWriter, Extension.MOD_ID, "soul_sandstone");
        stairBuilder(ModBlocks.SOUL_SANDSTONE_STAIRS.get(), Ingredient.of(ModBlocks.SOUL_SANDSTONE.get(), ModBlocks.CHISELED_SOUL_SANDSTONE.get(), ModBlocks.CUT_SOUL_SANDSTONE.get()))
                .unlockedBy(getHasName(ModBlocks.SOUL_SANDSTONE.get()), has(ModBlocks.SOUL_SANDSTONE.get()))
                .unlockedBy(getHasName(ModBlocks.CHISELED_SOUL_SANDSTONE.get()), has(ModBlocks.CHISELED_SOUL_SANDSTONE.get()))
                .unlockedBy(getHasName(ModBlocks.CUT_SOUL_SANDSTONE.get()), has(ModBlocks.CUT_SOUL_SANDSTONE.get())).save(pWriter);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_SANDSTONE_SLAB.get(), Ingredient.of(ModBlocks.SOUL_SANDSTONE.get(), ModBlocks.CHISELED_SOUL_SANDSTONE.get()))
                .unlockedBy(getHasName(ModBlocks.SOUL_SANDSTONE.get()), has(ModBlocks.SOUL_SANDSTONE.get()))
                .unlockedBy(getHasName(ModBlocks.CHISELED_SOUL_SANDSTONE.get()), has(ModBlocks.CHISELED_SOUL_SANDSTONE.get())).save(pWriter);
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_SANDSTONE_WALL.get(), ModBlocks.SOUL_SANDSTONE.get());
        chiseled(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SOUL_SANDSTONE.get(), ModBlocks.SOUL_SANDSTONE_SLAB.get());
        smeltingResultFromBase(pWriter, ModBlocks.SMOOTH_SOUL_SANDSTONE.get(), ModBlocks.SOUL_SANDSTONE.get());
        stairBuilder(ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get(), Ingredient.of(ModBlocks.SMOOTH_SOUL_SANDSTONE.get()))
                .unlockedBy(getHasName(ModBlocks.SMOOTH_SOUL_SANDSTONE.get()), has(ModBlocks.SMOOTH_SOUL_SANDSTONE.get())).save(pWriter);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get(), Ingredient.of(ModBlocks.SMOOTH_SOUL_SANDSTONE.get()))
                .unlockedBy(getHasName(ModBlocks.SMOOTH_SOUL_SANDSTONE.get()), has(ModBlocks.SMOOTH_SOUL_SANDSTONE.get())).save(pWriter);
        cut(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SOUL_SANDSTONE.get(), ModBlocks.SOUL_SANDSTONE.get());
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get(), Ingredient.of(ModBlocks.CUT_SOUL_SANDSTONE.get()))
                .unlockedBy(getHasName(ModBlocks.CUT_SOUL_SANDSTONE.get()), has(ModBlocks.CUT_SOUL_SANDSTONE.get())).save(pWriter);
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SOUL_SANDSTONE.get(), ModBlocks.SOUL_SANDSTONE.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_SANDSTONE_STAIRS.get(), ModBlocks.SOUL_SANDSTONE.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_SANDSTONE_WALL.get(), ModBlocks.SOUL_SANDSTONE.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_SANDSTONE_SLAB.get(), ModBlocks.SOUL_SANDSTONE.get(), 2);
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SOUL_SANDSTONE.get(), ModBlocks.SOUL_SANDSTONE.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get(), ModBlocks.SOUL_SANDSTONE.get(), 2);
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get(), ModBlocks.CUT_SOUL_SANDSTONE.get(), 2);
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get(), ModBlocks.SMOOTH_SOUL_SANDSTONE.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get(), ModBlocks.SMOOTH_SOUL_SANDSTONE.get(), 2);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.REDSTONE_LANTERN.get())
                .pattern("SSS")
                .pattern("STS")
                .pattern("SSS")
                .define('S', Items.IRON_NUGGET)
                .define('T', Items.REDSTONE_TORCH)
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, ModItems.SPAWNER_MINECART.get())
                .requires(Items.MINECART).requires(Items.SPAWNER).unlockedBy(getHasName(Items.MINECART), has(Items.MINECART)).save(pWriter);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(Items.DIAMOND_HORSE_ARMOR),
                Ingredient.of(Items.NETHERITE_INGOT), RecipeCategory.COMBAT, ModItems.NETHERITE_HORSE_ARMOR.get())
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(pWriter, new ResourceLocation(Extension.MOD_ID, getItemName(ModItems.NETHERITE_HORSE_ARMOR.get()) + "_smithing"));

        colorWoolWithDye(ModItems.VIBRANT_RED_DYE.get(), ModBlocks.VIBRANT_RED_WOOL.get(), pWriter);
        colorWoolWithDye(ModItems.DULL_ORANGE_DYE.get(), ModBlocks.DULL_ORANGE_WOOL.get(), pWriter);
        colorWoolWithDye(ModItems.BRIGHT_YELLOW_DYE.get(), ModBlocks.BRIGHT_YELLOW_WOOL.get(), pWriter);
        colorWoolWithDye(ModItems.CHARTREUSE_DYE.get(), ModBlocks.CHARTREUSE_WOOL.get(), pWriter);
        colorWoolWithDye(ModItems.VIBRANT_GREEN_DYE.get(), ModBlocks.VIBRANT_GREEN_WOOL.get(), pWriter);
        colorWoolWithDye(ModItems.SPRING_GREEN_DYE.get(), ModBlocks.SPRING_GREEN_WOOL.get(), pWriter);
        colorWoolWithDye(ModItems.BRIGHT_CYAN_DYE.get(), ModBlocks.BRIGHT_CYAN_WOOL.get(), pWriter);
        colorWoolWithDye(ModItems.CAPRI_DYE.get(), ModBlocks.CAPRI_WOOL.get(), pWriter);
        colorWoolWithDye(ModItems.ULTRAMARINE_DYE.get(), ModBlocks.ULTRAMARINE_WOOL.get(), pWriter);
        colorWoolWithDye(ModItems.VIOLET_DYE.get(), ModBlocks.VIOLET_WOOL.get(), pWriter);
        colorWoolWithDye(ModItems.VIBRANT_PURPLE_DYE.get(), ModBlocks.VIBRANT_PURPLE_WOOL.get(), pWriter);
        colorWoolWithDye(ModItems.BRIGHT_MAGENTA_DYE.get(), ModBlocks.BRIGHT_MAGENTA_WOOL.get(), pWriter);
        colorWoolWithDye(ModItems.ROSE_DYE.get(), ModBlocks.ROSE_WOOL.get(), pWriter);
        colorWoolWithDye(ModItems.DARK_GRAY_DYE.get(), ModBlocks.DARK_GRAY_WOOL.get(), pWriter);
        colorWoolWithDye(ModItems.SILVER_DYE.get(), ModBlocks.SILVER_WOOL.get(), pWriter);
        colorWoolWithDye(ModItems.ALPHA_WHITE_DYE.get(), ModBlocks.ALPHA_WHITE_WOOL.get(), pWriter);

        colorCarpetWithDye(ModBlocks.VIBRANT_RED_CARPET.get(),ModItems.VIBRANT_RED_DYE.get(), pWriter);
        colorCarpetWithDye(ModBlocks.DULL_ORANGE_CARPET.get(),ModItems.DULL_ORANGE_DYE.get(), pWriter);
        colorCarpetWithDye(ModBlocks.BRIGHT_YELLOW_CARPET.get(),ModItems.BRIGHT_YELLOW_DYE.get(), pWriter);
        colorCarpetWithDye(ModBlocks.CHARTREUSE_CARPET.get(),ModItems.CHARTREUSE_DYE.get(), pWriter);
        colorCarpetWithDye(ModBlocks.VIBRANT_GREEN_CARPET.get(),ModItems.VIBRANT_GREEN_DYE.get(), pWriter);
        colorCarpetWithDye(ModBlocks.SPRING_GREEN_CARPET.get(),ModItems.SPRING_GREEN_DYE.get(), pWriter);
        colorCarpetWithDye(ModBlocks.BRIGHT_CYAN_CARPET.get(),ModItems.BRIGHT_CYAN_DYE.get(), pWriter);
        colorCarpetWithDye(ModBlocks.CAPRI_CARPET.get(),ModItems.CAPRI_DYE.get(), pWriter);
        colorCarpetWithDye(ModBlocks.ULTRAMARINE_CARPET.get(),ModItems.ULTRAMARINE_DYE.get(), pWriter);
        colorCarpetWithDye(ModBlocks.VIOLET_CARPET.get(),ModItems.VIOLET_DYE.get(), pWriter);
        colorCarpetWithDye(ModBlocks.VIBRANT_PURPLE_CARPET.get(),ModItems.VIBRANT_PURPLE_DYE.get(), pWriter);
        colorCarpetWithDye(ModBlocks.BRIGHT_MAGENTA_CARPET.get(),ModItems.BRIGHT_MAGENTA_DYE.get(), pWriter);
        colorCarpetWithDye(ModBlocks.ROSE_CARPET.get(),ModItems.ROSE_DYE.get(), pWriter);
        colorCarpetWithDye(ModBlocks.DARK_GRAY_CARPET.get(),ModItems.DARK_GRAY_DYE.get(), pWriter);
        colorCarpetWithDye(ModBlocks.SILVER_CARPET.get(),ModItems.SILVER_DYE.get(), pWriter);
        colorCarpetWithDye(ModBlocks.ALPHA_WHITE_CARPET.get(),ModItems.ALPHA_WHITE_DYE.get(), pWriter);

        carpet(pWriter ,ModBlocks.VIBRANT_RED_CARPET.get(),ModBlocks.VIBRANT_RED_WOOL.get());
        carpet(pWriter, ModBlocks.DULL_ORANGE_CARPET.get(),ModBlocks.DULL_ORANGE_WOOL.get());
        carpet(pWriter, ModBlocks.BRIGHT_YELLOW_CARPET.get(),ModBlocks.BRIGHT_YELLOW_WOOL.get());
        carpet(pWriter, ModBlocks.CHARTREUSE_CARPET.get(),ModBlocks.CHARTREUSE_WOOL.get());
        carpet(pWriter, ModBlocks.VIBRANT_GREEN_CARPET.get(),ModBlocks.VIBRANT_GREEN_WOOL.get());
        carpet(pWriter, ModBlocks.SPRING_GREEN_CARPET.get(),ModBlocks.SPRING_GREEN_WOOL.get());
        carpet(pWriter, ModBlocks.BRIGHT_CYAN_CARPET.get(),ModBlocks.BRIGHT_CYAN_WOOL.get());
        carpet(pWriter, ModBlocks.CAPRI_CARPET.get(),ModBlocks.CAPRI_WOOL.get());
        carpet(pWriter, ModBlocks.ULTRAMARINE_CARPET.get(),ModBlocks.ULTRAMARINE_WOOL.get());
        carpet(pWriter, ModBlocks.VIOLET_CARPET.get(),ModBlocks.VIOLET_WOOL.get());
        carpet(pWriter, ModBlocks.VIBRANT_PURPLE_CARPET.get(),ModBlocks.VIBRANT_PURPLE_WOOL.get());
        carpet(pWriter, ModBlocks.BRIGHT_MAGENTA_CARPET.get(),ModBlocks.BRIGHT_MAGENTA_WOOL.get());
        carpet(pWriter, ModBlocks.ROSE_CARPET.get(),ModBlocks.ROSE_WOOL.get());
        carpet(pWriter, ModBlocks.DARK_GRAY_CARPET.get(),ModBlocks.DARK_GRAY_WOOL.get());
        carpet(pWriter, ModBlocks.SILVER_CARPET.get(),ModBlocks.SILVER_WOOL.get());
        carpet(pWriter, ModBlocks.ALPHA_WHITE_CARPET.get(),ModBlocks.ALPHA_WHITE_WOOL.get());


        dyeCrafting(pWriter, ModItems.ALPHA_WHITE_DYE.get(), Items.LIGHT_GRAY_DYE, Items.WHITE_DYE);
        dyeCrafting(pWriter, ModItems.SILVER_DYE.get(), Items.LIGHT_GRAY_DYE, Items.GRAY_DYE);
        dyeCrafting(pWriter, ModItems.DARK_GRAY_DYE.get(), Items.BLACK_DYE, Items.GRAY_DYE);
        dyeCrafting(pWriter, ModItems.ROSE_DYE.get(), Items.MAGENTA_DYE, Items.PINK_DYE);
        dyeCrafting(pWriter, ModItems.BRIGHT_MAGENTA_DYE.get(), Items.MAGENTA_DYE, Items.WHITE_DYE);
        dyeCrafting(pWriter, ModItems.VIBRANT_PURPLE_DYE.get(), Items.PURPLE_DYE, Items.WHITE_DYE);
        dyeCrafting(pWriter, ModItems.VIOLET_DYE.get(), Items.PURPLE_DYE, Items.MAGENTA_DYE);
        dyeCrafting(pWriter, ModItems.ULTRAMARINE_DYE.get(), Items.CYAN_DYE, Items.BLUE_DYE);
        dyeCrafting(pWriter, ModItems.CAPRI_DYE.get(), Items.CYAN_DYE, Items.LIGHT_BLUE_DYE);
        dyeCrafting(pWriter, ModItems.BRIGHT_CYAN_DYE.get(), Items.CYAN_DYE, Items.WHITE_DYE);
        dyeCrafting(pWriter, ModItems.SPRING_GREEN_DYE.get(), Items.LIME_DYE, Items.LIGHT_BLUE_DYE);
        dyeCrafting(pWriter, ModItems.VIBRANT_GREEN_DYE.get(), Items.LIME_DYE, Items.WHITE_DYE);
        dyeCrafting(pWriter, ModItems.CHARTREUSE_DYE.get(), Items.LIME_DYE, Items.YELLOW_DYE);
        dyeCrafting(pWriter, ModItems.BRIGHT_YELLOW_DYE.get(), Items.YELLOW_DYE, Items.WHITE_DYE);
        dyeCrafting(pWriter, ModItems.DULL_ORANGE_DYE.get(), Items.ORANGE_DYE, Items.WHITE_DYE);
        dyeCrafting(pWriter, ModItems.VIBRANT_RED_DYE.get(), Items.RED_DYE, Items.LIGHT_GRAY_DYE);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RED_NETHER_BRICK.get(),2)
                .requires(Items.NETHER_WART).requires(Items.NETHER_BRICK).unlockedBy(getHasName(Items.NETHER_BRICK), has(Items.NETHER_BRICK)).save(pWriter);
//        twoByTwoPacker(pWriter, RecipeCategory.BUILDING_BLOCKS, Items.RED_NETHER_BRICKS, ModItems.RED_NETHER_BRICK.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.NETHER_WART,9)
                .requires(Items.NETHER_WART_BLOCK).unlockedBy(getHasName(Items.NETHER_WART_BLOCK), has(Items.NETHER_WART_BLOCK)).save(pWriter);

        bookshelf(ModBlocks.SPRUCE_BOOKSHELF, Items.SPRUCE_PLANKS, pWriter);
        bookshelf(ModBlocks.BIRCH_BOOKSHELF, Items.BIRCH_PLANKS, pWriter);
        bookshelf(ModBlocks.JUNGLE_BOOKSHELF, Items.JUNGLE_PLANKS, pWriter);
        bookshelf(ModBlocks.ACACIA_BOOKSHELF, Items.ACACIA_PLANKS, pWriter);
        bookshelf(ModBlocks.DARK_OAK_BOOKSHELF, Items.DARK_OAK_PLANKS, pWriter);
        bookshelf(ModBlocks.MANGROVE_BOOKSHELF, Items.MANGROVE_PLANKS, pWriter);
        bookshelf(ModBlocks.BAMBOO_BOOKSHELF, Items.BAMBOO_PLANKS, pWriter);
        bookshelf(ModBlocks.CHERRY_BOOKSHELF, Items.CHERRY_PLANKS, pWriter);
        bookshelf(ModBlocks.CRIMSON_BOOKSHELF, Items.CRIMSON_PLANKS, pWriter);
        bookshelf(ModBlocks.WARPED_BOOKSHELF, Items.WARPED_PLANKS, pWriter);

        chiseled(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OAK_MOSAIC.get(), Items.OAK_SLAB);
        chiseled(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPRUCE_MOSAIC.get(), Items.SPRUCE_SLAB);
        chiseled(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BIRCH_MOSAIC.get(), Items.BIRCH_SLAB);
        chiseled(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.JUNGLE_MOSAIC.get(), Items.JUNGLE_SLAB);
        chiseled(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ACACIA_MOSAIC.get(), Items.ACACIA_SLAB);
        chiseled(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_OAK_MOSAIC.get(), Items.DARK_OAK_SLAB);
        chiseled(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MANGROVE_MOSAIC.get(), Items.MANGROVE_SLAB);
        chiseled(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHERRY_MOSAIC.get(), Items.CHERRY_SLAB);
        chiseled(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRIMSON_MOSAIC.get(), Items.CRIMSON_SLAB);
        chiseled(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WARPED_MOSAIC.get(), Items.WARPED_SLAB);

        twoByTwoPacker(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPRUCE_CRAFTING_TABLE.get(), Items.SPRUCE_PLANKS);
        twoByTwoPacker(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BIRCH_CRAFTING_TABLE.get(), Items.BIRCH_PLANKS);
        twoByTwoPacker(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.JUNGLE_CRAFTING_TABLE.get(), Items.JUNGLE_PLANKS);
        twoByTwoPacker(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ACACIA_CRAFTING_TABLE.get(), Items.ACACIA_PLANKS);
        twoByTwoPacker(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_OAK_CRAFTING_TABLE.get(), Items.DARK_OAK_PLANKS);
        twoByTwoPacker(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MANGROVE_CRAFTING_TABLE.get(), Items.MANGROVE_PLANKS);
        twoByTwoPacker(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BAMBOO_CRAFTING_TABLE.get(), Items.BAMBOO_PLANKS);
        twoByTwoPacker(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHERRY_CRAFTING_TABLE.get(), Items.CHERRY_PLANKS);
        twoByTwoPacker(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRIMSON_CRAFTING_TABLE.get(), Items.CRIMSON_PLANKS);
        twoByTwoPacker(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WARPED_CRAFTING_TABLE.get(), Items.WARPED_PLANKS);

        woodenBoat(pWriter, ModItems.CRIMSON_BOAT.get(), Items.CRIMSON_PLANKS);
        woodenBoat(pWriter, ModItems.WARPED_BOAT.get(), Items.WARPED_PLANKS);
        chestBoat(pWriter, ModItems.CRIMSON_CHEST_BOAT.get(),ModItems.CRIMSON_BOAT.get());
        chestBoat(pWriter, ModItems.WARPED_CHEST_BOAT.get(), ModItems.WARPED_BOAT.get());

        oreSmelting(pWriter, AMETHYST_SMELTABLE, RecipeCategory.MISC, Items.AMETHYST_SHARD, 0.25f, 200, "amethyst");
        oreBlasting(pWriter, AMETHYST_SMELTABLE, RecipeCategory.MISC, Items.AMETHYST_SHARD, 0.25f, 100, "amethyst");

        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.STONE_WALL.get(), Items.STONE);
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GRANITE_WALL.get(), Items.POLISHED_GRANITE);
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_DIORITE_WALL.get(), Items.POLISHED_DIORITE);
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_ANDESITE_WALL.get(), Items.POLISHED_ANDESITE);
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PRISMARINE_BRICK_WALL.get(), Items.PRISMARINE_BRICKS);
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_PRISMARINE_BRICK_WALL.get(), Items.DARK_PRISMARINE);
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PURPUR_WALL.get(), Items.PURPUR_BLOCK);
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_WALL.get(), Items.QUARTZ_BLOCK);
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_QUARTZ_WALL.get(), Items.SMOOTH_QUARTZ);

        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_COPPER_WALL.get(), Items.CUT_COPPER);
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.EXPOSED_CUT_COPPER_WALL.get(), Items.EXPOSED_CUT_COPPER);
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WEATHERED_CUT_COPPER_WALL.get(), Items.WEATHERED_CUT_COPPER);
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OXIDIZED_CUT_COPPER_WALL.get(), Items.OXIDIZED_CUT_COPPER);
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WAXED_CUT_COPPER_WALL.get(), Items.WAXED_CUT_COPPER);
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WAXED_EXPOSED_CUT_COPPER_WALL.get(), Items.WAXED_EXPOSED_CUT_COPPER);
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WAXED_WEATHERED_CUT_COPPER_WALL.get(), Items.WAXED_WEATHERED_CUT_COPPER);
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL.get(), Items.WAXED_OXIDIZED_CUT_COPPER);

        stainedGlassPaneFromStainedGlass(pWriter, ModBlocks.TINTED_GLASS_PANE.get(), Items.TINTED_GLASS);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TINTED_GLASS_PANE.get(), 2)
                .pattern(" S ")
                .pattern("SGS")
                .pattern(" S ")
                .define('S', Items.AMETHYST_SHARD)
                .define('G', Items.GLASS_PANE)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                .save(pWriter, Extension.MOD_ID + ":" + getItemName(ModBlocks.TINTED_GLASS_PANE.get()) + "_from_glass_pane");

        fenceGate(pWriter, ModBlocks.NETHER_BRICK_FENCE_GATE.get(), Items.NETHER_BRICKS, Items.NETHER_BRICK);
        fenceGate(pWriter, ModBlocks.RED_NETHER_BRICK_FENCE_GATE.get(), Items.RED_NETHER_BRICKS, ModItems.RED_NETHER_BRICK.get());
        smeltingResultFromBase(pWriter, ModBlocks.CRACKED_RED_NETHER_BRICKS.get(), Items.RED_NETHER_BRICKS);
        fence(pWriter, ModBlocks.RED_NETHER_BRICK_FENCE.get(), Items.RED_NETHER_BRICKS, ModItems.RED_NETHER_BRICK.get());
        chiseled(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_RED_NETHER_BRICKS.get(), Items.RED_NETHER_BRICK_SLAB);
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_RED_NETHER_BRICKS.get(), Items.RED_NETHER_BRICKS, 1);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_NETHER_BRICKS.get()).requires(Items.WARPED_WART_BLOCK)
                .requires(Items.NETHER_BRICKS).unlockedBy(getHasName(Items.NETHER_BRICKS), has(Items.NETHER_BRICKS)).save(pWriter);
        fenceGate(pWriter, ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get(), ModBlocks.BLUE_NETHER_BRICKS.get(), ModItems.BLUE_NETHER_BRICK.get());
        wall(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_NETHER_BRICK_WALL.get(), ModBlocks.BLUE_NETHER_BRICKS.get());
        smeltingResultFromBase(pWriter, ModBlocks.CRACKED_BLUE_NETHER_BRICKS.get(), ModBlocks.BLUE_NETHER_BRICKS.get());
        fence(pWriter, ModBlocks.BLUE_NETHER_BRICK_FENCE.get(), ModBlocks.BLUE_NETHER_BRICKS.get(), ModItems.BLUE_NETHER_BRICK.get());
        chiseled(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_BLUE_NETHER_BRICKS.get(), ModBlocks.BLUE_NETHER_BRICK_SLAB.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_BLUE_NETHER_BRICKS.get(), ModBlocks.BLUE_NETHER_BRICKS.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_NETHER_BRICK_STAIRS.get(), ModBlocks.BLUE_NETHER_BRICKS.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_NETHER_BRICK_SLAB.get(), ModBlocks.BLUE_NETHER_BRICKS.get(), 2);
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_NETHER_BRICK_WALL.get(), ModBlocks.BLUE_NETHER_BRICKS.get());
        stairBuilder(ModBlocks.BLUE_NETHER_BRICK_STAIRS.get(), Ingredient.of(ModBlocks.BLUE_NETHER_BRICKS.get()))
                .unlockedBy(getHasName(ModBlocks.BLUE_NETHER_BRICKS.get()), has(ModBlocks.BLUE_NETHER_BRICKS.get())).save(pWriter);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_NETHER_BRICK_SLAB.get(), Ingredient.of(ModBlocks.BLUE_NETHER_BRICKS.get()))
                .unlockedBy(getHasName(ModBlocks.BLUE_NETHER_BRICKS.get()), has(ModBlocks.BLUE_NETHER_BRICKS.get())).save(pWriter);

        twoByTwoPacker(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PAINTING_PLANKS.get(), Items.STICK);
        woodenStairs(pWriter, ModBlocks.PAINTING_STAIRS.get(), ModBlocks.PAINTING_PLANKS.get());
        woodenSlab(pWriter, ModBlocks.PAINTING_SLAB.get(), ModBlocks.PAINTING_PLANKS.get());
        woodenFence(pWriter, ModBlocks.PAINTING_FENCE.get(), ModBlocks.PAINTING_PLANKS.get());
        woodenFenceGate(pWriter, ModBlocks.PAINTING_FENCE_GATE.get(), ModBlocks.PAINTING_PLANKS.get());
        woodenDoor(pWriter, ModBlocks.PAINTING_DOOR.get(), ModBlocks.PAINTING_PLANKS.get());
        woodenTrapdoor(pWriter, ModBlocks.PAINTING_TRAPDOOR.get(), ModBlocks.PAINTING_PLANKS.get());
        woodenButton(pWriter, ModBlocks.PAINTING_BUTTON.get(), ModBlocks.PAINTING_PLANKS.get());
        woodenPressurePlate(pWriter, ModBlocks.PAINTING_PRESSURE_PLATE.get(), ModBlocks.PAINTING_PLANKS.get());

        for (RegistryObject<Block> planks : ModBlocks.COLORED_PLANKS){
            int index = ModBlocks.COLORED_PLANKS.indexOf(planks);
            woodFromLogs(pWriter, ModBlocks.COLORED_STRIPPED_WOODS.get(index).get(), ModBlocks.COLORED_STRIPPED_LOGS.get(index).get());
            woodFromLogs(pWriter, ModBlocks.COLORED_WOODS.get(index).get(), ModBlocks.COLORED_LOGS.get(index).get());
            planksFromLog(pWriter, planks.get(), ModTags.Items.COLORED_LOGS.get(index), 4);
            woodenStairs(pWriter, ModBlocks.COLORED_STAIRS.get(index).get(), planks.get());
            woodenSlab(pWriter, ModBlocks.COLORED_SLABS.get(index).get(), planks.get());
            woodenFence(pWriter, ModBlocks.COLORED_FENCES.get(index).get(), planks.get());
            woodenFenceGate(pWriter, ModBlocks.COLORED_FENCE_GATES.get(index).get(), planks.get());
            woodenDoor(pWriter, ModBlocks.COLORED_DOORS.get(index).get(), planks.get());
            woodenTrapdoor(pWriter, ModBlocks.COLORED_TRAPDOORS.get(index).get(), planks.get());
            woodenPressurePlate(pWriter, ModBlocks.COLORED_PRESSURE_PLATES.get(index).get(), planks.get());
            woodenButton(pWriter, ModBlocks.COLORED_BUTTONS.get(index).get(), planks.get());
            signBuilder(ModItems.COLORED_SIGNS.get(index).get(), Ingredient.of(planks.get())).unlockedBy("has_planks", has(planks.get())).save(pWriter);
            hangingSign(pWriter, ModItems.COLORED_HANGING_SIGNS.get(index).get(), ModBlocks.COLORED_STRIPPED_LOGS.get(index).get());
            woodenBoat(pWriter, ModItems.COLORED_BOATS.get(index).get(), planks.get());
            chestBoat(pWriter, ModItems.COLORED_CHEST_BOATS.get(index).get(), ModItems.COLORED_BOATS.get(index).get());
        }

        colorBlockWithDye(pWriter, DYES, ModBlocks.COLORED_LEAVES, ItemTags.LEAVES, "colored_leaves");
        colorBlockWithDye(pWriter, DYES, ModBlocks.COLORED_LOGS, ModTags.Items.DYEABLE_LOGS, "colored_logs");
        colorBlockWithDye(pWriter, DYES, ModBlocks.COLORED_WOODS, ModTags.Items.DYEABLE_WOODS, "colored_woods");
        colorBlockWithDye(pWriter, DYES, ModBlocks.COLORED_STRIPPED_LOGS, ModTags.Items.DYEABLE_STRIPPED_LOGS, "colored_stripped_logs");
        colorBlockWithDye(pWriter, DYES, ModBlocks.COLORED_STRIPPED_WOODS, ModTags.Items.DYEABLE_STRIPPED_WOODS, "colored_stripped_woods");
        colorBlockWithDye(pWriter, DYES, ModBlocks.COLORED_PLANKS, ItemTags.PLANKS, "colored_planks");
        colorBlockWithDye(pWriter, DYES, ModBlocks.COLORED_STAIRS, ItemTags.WOODEN_STAIRS, "colored_stairs");
        colorBlockWithDye(pWriter, DYES, ModBlocks.COLORED_SLABS, ItemTags.WOODEN_SLABS, "colored_slabs");
        colorBlockWithDye(pWriter, DYES, ModBlocks.COLORED_FENCES, ItemTags.WOODEN_FENCES, "colored_slabs");
        colorBlockWithDye(pWriter, DYES, ModBlocks.COLORED_FENCE_GATES, ItemTags.FENCE_GATES, "colored_fence_gates");
        colorBlockWithDye(pWriter, DYES, ModBlocks.COLORED_DOORS, ItemTags.WOODEN_DOORS, "colored_doors");
        colorBlockWithDye(pWriter, DYES, ModBlocks.COLORED_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS, "colored_trapdoors");
        colorBlockWithDye(pWriter, DYES, ModBlocks.COLORED_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES, "colored_pressure_plates");
        colorBlockWithDye(pWriter, DYES, ModBlocks.COLORED_BUTTONS, ItemTags.WOODEN_BUTTONS, "colored_buttons");
        colorBlockWithDye(pWriter, DYES, ModBlocks.COLORED_SIGNS, ItemTags.SIGNS, "colored_signs");
        colorBlockWithDye(pWriter, DYES, ModBlocks.COLORED_HANGING_SIGNS, ItemTags.HANGING_SIGNS, "colored_hanging_signs");
        colorItemWithDye(pWriter, DYES, ModItems.COLORED_BOATS, ModTags.Items.DYEABLE_BOATS, "colored_boats");
        colorItemWithDye(pWriter, DYES, ModItems.COLORED_CHEST_BOATS, ItemTags.CHEST_BOATS, "colored_chest_boats");

//        planksFromLog(pWriter, ModBlocks.WHITE_PLANKS.get().asItem(), ModTags.Items.WHITE_LOGS, 4);
//        woodFromLogs(pWriter, ModBlocks.WHITE_WOOD.get().asItem(), ModBlocks.WHITE_LOG.get().asItem());
//        woodFromLogs(pWriter, ModBlocks.STRIPPED_WHITE_WOOD.get().asItem(), ModBlocks.STRIPPED_WHITE_LOG.get().asItem());
//        woodenStairs(pWriter, ModBlocks.WHITE_STAIRS.get(), ModBlocks.WHITE_PLANKS.get());
//        woodenSlab(pWriter, ModBlocks.WHITE_SLAB.get(), ModBlocks.WHITE_PLANKS.get());
//        woodenFence(pWriter, ModBlocks.WHITE_FENCE.get(), ModBlocks.WHITE_PLANKS.get());
//        woodenFenceGate(pWriter, ModBlocks.WHITE_FENCE_GATE.get(), ModBlocks.WHITE_PLANKS.get());
//        woodenDoor(pWriter, ModBlocks.WHITE_DOOR.get(), ModBlocks.WHITE_PLANKS.get());
//        woodenTrapdoor(pWriter, ModBlocks.WHITE_TRAPDOOR.get(), ModBlocks.WHITE_PLANKS.get());
//        woodenPressurePlate(pWriter, ModBlocks.WHITE_PRESSURE_PLATE.get(), ModBlocks.WHITE_PLANKS.get());
//        woodenButton(pWriter, ModBlocks.WHITE_BUTTON.get(), ModBlocks.WHITE_PLANKS.get());
    }


    protected static void woodenStairs(Consumer<FinishedRecipe> pWriter, ItemLike pStairs, ItemLike pMaterial) {
        stairBuilder(pStairs, Ingredient.of(pMaterial))
                .group("wooden_stairs")
                .unlockedBy("has_planks", has(pMaterial))
                .save(pWriter);
    }

    protected static void woodenSlab(Consumer<FinishedRecipe> pWriter, ItemLike pSlab, ItemLike pMaterial) {
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, pSlab, Ingredient.of(pMaterial))
                .group("wooden_slab")
                .unlockedBy("has_planks", has(pMaterial))
                .save(pWriter);
    }

    protected static void woodenFence(Consumer<FinishedRecipe> pWriter, ItemLike pFence, ItemLike pMaterial) {
        fenceBuilder(pFence, Ingredient.of(pMaterial))
                .group("wooden_fence")
                .unlockedBy("has_planks", has(pMaterial))
                .save(pWriter);
    }

    protected static void woodenFenceGate(Consumer<FinishedRecipe> pWriter, ItemLike pFenceGate, ItemLike pMaterial) {
        fenceGateBuilder(pFenceGate, Ingredient.of(pMaterial))
                .group("wooden_fence_gate")
                .unlockedBy("has_planks", has(pMaterial))
                .save(pWriter);
    }

    protected static void woodenDoor(Consumer<FinishedRecipe> pWriter, ItemLike pDoor, ItemLike pMaterial) {
        doorBuilder(pDoor, Ingredient.of(pMaterial))
                .group("wooden_door")
                .unlockedBy("has_planks", has(pMaterial))
                .save(pWriter);
    }

    protected static void woodenTrapdoor(Consumer<FinishedRecipe> pWriter, ItemLike pTrapdoor, ItemLike pMaterial) {
        trapdoorBuilder(pTrapdoor, Ingredient.of(pMaterial))
                .group("wooden_trapdoor")
                .unlockedBy("has_planks", has(pMaterial))
                .save(pWriter);
    }

    protected static void woodenPressurePlate(Consumer<FinishedRecipe> pWriter, ItemLike pPressurePlate, ItemLike pMaterial) {
        pressurePlateBuilder(RecipeCategory.REDSTONE, pPressurePlate, Ingredient.of(pMaterial))
                .group("wooden_pressure_plate")
                .unlockedBy("has_planks", has(pMaterial))
                .save(pWriter);
    }

    protected static void woodenButton(Consumer<FinishedRecipe> pWriter, ItemLike pButton, ItemLike pMaterial){
        buttonBuilder(pButton, Ingredient.of(pMaterial))
                .group("wooden_button")
                .unlockedBy("has_planks", has(pMaterial))
                .save(pWriter);
    }

    protected static void fence(Consumer<FinishedRecipe> pWriter, ItemLike pFence, ItemLike pMaterial, ItemLike pItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, pFence)
                .define('W', pMaterial)
                .define('#', pItem)
                .pattern("W#W")
                .pattern("W#W")
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pWriter);
    }

    protected static void fenceGate(Consumer<FinishedRecipe> pWriter, ItemLike pFenceGate, ItemLike pMaterial, ItemLike pItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, pFenceGate)
                .define('#', pItem)
                .define('W', pMaterial)
                .pattern("#W#")
                .pattern("#W#")
                .group("fence_gate")
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pWriter);
    }

    protected static void bookshelf(RegistryObject<Block> blockRegistryObject, Item pItem, Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockRegistryObject.get(), 1)
                .group("bookshelves")
                .pattern("PPP")
                .pattern("BBB")
                .pattern("PPP")
                .define('P', pItem)
                .define('B', Items.BOOK)
                .unlockedBy(getHasName(Items.BOOK), has(Items.BOOK))
                .save(pWriter);
    }

//    protected static void chestBoatCrafting(Item pIn, Item pOut, Consumer<FinishedRecipe> pWriter) {
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, pOut,1)
//                .group("chest_boat")
//                .requires(Tags.Items.CHESTS)
//                .requires(pIn)
//                .unlockedBy("has_boat", has(ItemTags.BOATS))
//                .save(pWriter);
//    }

    protected static void colorWoolWithDye(Item pDye, Block pResult, Consumer<FinishedRecipe> pWriter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, pResult, 1)
                .group("wool")
                .requires(pDye)
                .requires(ItemTags.WOOL)
                .unlockedBy("has_needed_dye", has(pDye))
                .save(pWriter);
    }
//
//    protected static void carpetFromWool(Block pCarpet, Block pWool, Consumer<FinishedRecipe> pWriter) {
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pCarpet, 3)
//                .group("carpet")
//                .pattern("##")
//                .define('#', pWool)
//                .unlockedBy(getHasName(pWool), has(pWool))
//                .save(pWriter);
//    }
    protected static void colorCarpetWithDye(Block pResult, Item pDye, Consumer<FinishedRecipe> pWriter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, pResult, 1)
                .group("carpet")
                .requires(pDye)
                .requires(ItemTags.WOOL_CARPETS)
                .unlockedBy("has_needed_dye", has(pDye))
                .save(pWriter, new ResourceLocation(Extension.MOD_ID, "dye_" + getItemName(pResult)));
    }
    protected static void colorBlockWithDye(Consumer<FinishedRecipe> pWriter, List<Item> pDye, List<RegistryObject<Block>> pDyeableItems, TagKey<Item> pTag, String pGroup) {
        for(int i = 0; i < pDye.size(); i++) {
            Item item = pDye.get(i);
            Block result = pDyeableItems.get(i).get();
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, result)
                    .requires(item)
                    .requires(pTag)
                    .group(pGroup)
                    .unlockedBy("has_needed_dye", has(item))
                    .save(pWriter, new ResourceLocation(Extension.MOD_ID, "dye_" + getItemName(result)));
        }
    }

    protected static void colorItemWithDye(Consumer<FinishedRecipe> pWriter, List<Item> pDye, List<RegistryObject<Item>> pDyeableItems, TagKey<Item> pTag, String pGroup) {
        for(int i = 0; i < pDye.size(); i++) {
            Item dye = pDye.get(i);
            Item result = pDyeableItems.get(i).get();
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result)
                    .requires(dye)
                    .requires(pTag)
                    .group(pGroup)
                    .unlockedBy("has_needed_dye", has(dye))
                    .save(pWriter, new ResourceLocation(Extension.MOD_ID, "dye_" + getItemName(result)));
        }
    }

    protected static void dyeCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, Item pResult, Item pDye1, Item pDye2) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, pResult, 2)
                .requires(pDye1).requires(pDye2)
                .unlockedBy(getHasName(pDye1), has(pDye2)).unlockedBy(getHasName(pDye2), has(pDye2))
                .save(pFinishedRecipeConsumer);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, Extension.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }

    protected static void stonecutterResultFromBase(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pCategory, ItemLike pResult, ItemLike pMaterial) {
        stonecutterResultFromBase(pFinishedRecipeConsumer, pCategory, pResult, pMaterial, 1);
    }
    protected static void stonecutterResultFromBase(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pCategory, ItemLike pResult, ItemLike pMaterial, int pResultCount) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(pMaterial), pCategory, pResult, pResultCount)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pFinishedRecipeConsumer, Extension.MOD_ID + ":" + getConversionRecipeName(pResult, pMaterial) + "_stonecutting");
    }

    private void soulSandstone(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_SANDSTONE.get())
                .pattern("SS")
                .pattern("SS")
                .define('S', Items.SOUL_SAND)
                .unlockedBy(getHasName(Items.SOUL_SAND), has(Items.SOUL_SAND))
                .save(pFinishedRecipeConsumer);
    }
}
