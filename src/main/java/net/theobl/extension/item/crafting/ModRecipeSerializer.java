package net.theobl.extension.item.crafting;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;

public class ModRecipeSerializer {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Extension.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<FletchingRecipe>> FLETCHING_RECIPE =
            RECIPE_SERIALIZERS.register("fletching", FletchingRecipe.Serializer::new);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<TippedArrowFletchingRecipe>> TIPPED_ARROW =
            RECIPE_SERIALIZERS.register("fletching_special_tippedarrow", TippedArrowFletchingRecipe.Serializer::new);

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
