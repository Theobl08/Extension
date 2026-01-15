package net.theobl.extension.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
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

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class ExtensionJeiPlugin implements IModPlugin {
    private static final List<RecipeHolder<FletchingRecipe>> fletchingRecipes = new ArrayList<>();

    @Override
    public Identifier getPluginUid() {
        return Identifier.fromNamespaceAndPath(Extension.MODID, "extension_jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new FletchingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(FletchingRecipeCategory.FLETCHING, fletchingRecipes);
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

    public static void recipesReceived(RecipesReceivedEvent event) {
        fletchingRecipes.clear();
        fletchingRecipes.addAll(event.getRecipeMap().byType(ModRecipeType.FLETCHING.get()).stream().toList());
    }

    @SuppressWarnings("unused")
    public static void clientLogOut(ClientPlayerNetworkEvent.LoggingOut event) {
        fletchingRecipes.clear();
    }
}
