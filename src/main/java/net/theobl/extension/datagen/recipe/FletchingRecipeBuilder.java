package net.theobl.extension.datagen.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.RecipeUnlockedTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.theobl.extension.item.crafting.FletchingRecipe;
import org.jspecify.annotations.Nullable;

import java.util.*;

public class FletchingRecipeBuilder implements RecipeBuilder {
    private final HolderGetter<Item> items;
    private final RecipeCategory category;
    private final ItemStackTemplate result;
    private final List<Ingredient> ingredients = new ArrayList<>();
    private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
    private @Nullable String group;

    private FletchingRecipeBuilder(HolderGetter<Item> items, RecipeCategory category, ItemStackTemplate result) {
        this.items = items;
        this.category = category;
        this.result = result;
    }

    public static FletchingRecipeBuilder fletching(HolderGetter<Item> items, RecipeCategory category, ItemLike result, int count) {
        return new FletchingRecipeBuilder(items, category, new ItemStackTemplate(result.asItem(), count));
    }

    public FletchingRecipeBuilder requires(TagKey<Item> tag) {
        return this.requires(Ingredient.of(this.items.getOrThrow(tag)));
    }

    public FletchingRecipeBuilder requires(ItemLike item) {
        return this.requires(Ingredient.of(item));
    }

    public FletchingRecipeBuilder requires(Ingredient ingredient) {
        if(this.ingredients.size() >= 3) {
            throw new IllegalStateException("Fletching recipes cannot exceeds 3 ingredients");
        }
        this.ingredients.add(ingredient);
        return this;
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String groupName) {
        this.group = groupName;
        return this;
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(this.result);
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> resourceKey) {
        // Create the recipe.
        FletchingRecipe fletchingRecipe = new FletchingRecipe(Objects.requireNonNullElse(this.group, ""), result, ingredients);
        // Pass the id, the recipe, and the recipe advancement into the RecipeOutput.
        output.accept(resourceKey, fletchingRecipe, advancementBuilder.build(output, resourceKey, this.category));
    }
}
