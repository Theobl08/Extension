package net.theobl.extension.item.crafting;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;

public class ModRecipeType {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, Extension.MODID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<FletchingRecipe>> FLETCHING =
            RECIPE_TYPES.register("fletching", RecipeType::simple);

    public static void register(IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
    }
}
