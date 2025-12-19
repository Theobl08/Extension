package net.theobl.extension.compat.jei;

import com.mojang.serialization.Codec;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.ICodecHelper;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.IRecipeManager;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeHolderType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Blocks;
import net.theobl.extension.item.crafting.FletchingRecipe;
import net.theobl.extension.item.crafting.ModRecipeType;

public class FletchingRecipeCategory extends AbstractRecipeCategory<RecipeHolder<FletchingRecipe>> {
    public static final IRecipeHolderType<FletchingRecipe> FLETCHING = IRecipeHolderType.create(ModRecipeType.FLETCHING.getId());

    public FletchingRecipeCategory(IGuiHelper guiHelper) {
        super(FLETCHING, Component.translatable("container.fletching"), guiHelper.createDrawableItemLike(Blocks.FLETCHING_TABLE), 90, 54);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<FletchingRecipe> recipe, IFocusGroup focuses) {
        builder.addInputSlot(1,1).setStandardSlotBackground().add(recipe.value().getIngredients().get(0));
        builder.addInputSlot(1,19).setStandardSlotBackground().add(recipe.value().getIngredients().get(1));
        builder.addInputSlot(1,37).setStandardSlotBackground().add(recipe.value().getIngredients().get(2));
        builder.addOutputSlot(69,19).setOutputSlotBackground().add(recipe.value().getResult());
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, RecipeHolder<FletchingRecipe> recipe, IFocusGroup focuses) {
        builder.addRecipeArrow().setPosition(30,19);
    }

    @Override
    public Codec<RecipeHolder<FletchingRecipe>> getCodec(ICodecHelper codecHelper, IRecipeManager recipeManager) {
        return codecHelper.getRecipeHolderCodec();
    }
}
