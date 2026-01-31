package net.theobl.extension.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.theobl.extension.Extension;
import net.theobl.extension.inventory.FletchingMenu;
import net.theobl.extension.inventory.FletchingScreen;
import net.theobl.extension.inventory.ModMenuType;
import net.theobl.extension.item.crafting.FletchingRecipe;
import net.theobl.extension.item.crafting.ModRecipeType;
import net.theobl.extension.item.crafting.TippedArrowFletchingRecipe;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

@JeiPlugin
public class ExtensionJeiPlugin implements IModPlugin {
    private static final List<RecipeHolder<FletchingRecipe>> fletchingRecipes = new ArrayList<>();

    @Override
    public Identifier getPluginUid() {
        return Extension.asResource("extension_jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new FletchingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(FletchingRecipeCategory.FLETCHING, fletchingRecipes.stream().filter(recipe -> !recipe.id().identifier().getPath().contains("tipped")).toList());
        var specialFletchingRecipes = replaceSpecialFletchingRecipes(fletchingRecipes, registration.getJeiHelpers());
        registration.addRecipes(FletchingRecipeCategory.FLETCHING, specialFletchingRecipes);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(FletchingMenu.class, ModMenuType.FLETCHING.get(), FletchingRecipeCategory.FLETCHING, 1,3,4,36);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(FletchingScreen.class, 79, 32, 28, 23, FletchingRecipeCategory.FLETCHING);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(FletchingRecipeCategory.FLETCHING, Blocks.FLETCHING_TABLE);
    }

    /**
     * By default, JEI can't handle special recipes.
     * This method expands some special unhandled recipes into a list of normal recipes that JEI can understand.
     * <p>
     * If a special recipe we know how to replace is not present (because it has been removed),
     * we do not replace it.
     */
    private static List<RecipeHolder<FletchingRecipe>> replaceSpecialFletchingRecipes(List<RecipeHolder<FletchingRecipe>> unhandledFletchingRecipes, IJeiHelpers jeiHelpers) {
        Map<Class<? extends FletchingRecipe>, Supplier<List<RecipeHolder<FletchingRecipe>>>> replacers = new IdentityHashMap<>();
        replacers.put(TippedArrowFletchingRecipe.class, TippedArrowFletchingRecipeMaker::createRecipes);

        return unhandledFletchingRecipes.stream()
                .map(RecipeHolder::value)
                .map(FletchingRecipe::getClass)
                .filter(replacers::containsKey)
                .distinct()
                // distinct + this limit will ensure we stop iterating early if we find all the recipes we're looking for.
                .limit(replacers.size())
                .flatMap(recipeClass -> {
                    var supplier = replacers.get(recipeClass);
                    try {
                        return supplier.get()
                                .stream();
                    } catch (RuntimeException e) {
                        Extension.LOGGER.error("Failed to create JEI recipes for {}", recipeClass, e);
                        return Stream.of();
                    }
                })
                .toList();
    }

    public static void recipesReceived(RecipesReceivedEvent event) {
        fletchingRecipes.clear();
        fletchingRecipes.addAll(event.getRecipeMap().byType(ModRecipeType.FLETCHING.get()).stream().toList());
    }

    @SuppressWarnings("unused")
    public static void clientLogOut(ClientPlayerNetworkEvent.LoggingOut event) {
        fletchingRecipes.clear();
    }
}
