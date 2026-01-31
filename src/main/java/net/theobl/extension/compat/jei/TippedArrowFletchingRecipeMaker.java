package net.theobl.extension.compat.jei;

import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;
import net.theobl.extension.Extension;
import net.theobl.extension.item.crafting.FletchingRecipe;

import java.util.List;

public class TippedArrowFletchingRecipeMaker {

    public static List<RecipeHolder<FletchingRecipe>> createRecipes() {
        String group = "jei.tipped.arrow";
        Ingredient stickIngredient = Ingredient.of(Items.STICK);
        Ingredient featherIngredient = Ingredient.of(Items.FEATHER);

        return BuiltInRegistries.POTION.listElements().map(potion -> {
            ItemStack input = PotionContents.createItemStack(Items.LINGERING_POTION, potion);
            ItemStack output = PotionContents.createItemStack(Items.TIPPED_ARROW, potion);
            output.setCount(8);

//            Ingredient potionIngredient = new Ingredient(new DataComponentIngredient(HolderSet.direct(input.getItemHolder()), DataComponentExactPredicate.expect(DataComponents.POTION_CONTENTS, new PotionContents(potion.getDelegate())), false));
            Ingredient potionIngredient = DataComponentIngredient.of(false, DataComponentExactPredicate.expect(DataComponents.POTION_CONTENTS, new PotionContents(potion.getDelegate())), HolderSet.direct(input.getItemHolder()));
            Identifier potionId = potion.key().identifier();
            Identifier recipeId = Extension.asResource("jei.tipped.arrow." + potionId.getNamespace() + "." + potionId.getPath());
            ResourceKey<Recipe<?>> resourceKey = ResourceKey.create(Registries.RECIPE, recipeId);
            FletchingRecipe recipe = new FletchingRecipe(group, output, List.of(potionIngredient, stickIngredient, featherIngredient));

            return new RecipeHolder<>(resourceKey, recipe);
        }).toList();
    }
}
