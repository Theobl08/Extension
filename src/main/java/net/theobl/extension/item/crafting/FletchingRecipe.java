package net.theobl.extension.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class FletchingRecipe implements Recipe<CraftingInput> {
    protected final ItemStack result;
    protected final List<Ingredient> ingredients;
    @Nullable
    private PlacementInfo placementInfo;

    public FletchingRecipe(ItemStack result, List<Ingredient> ingredients) {
        this.result = result;
        this.ingredients = ingredients;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
//        for (int i = 0; i < 3; i++) {
//            if (!this.ingredients.get(i).test(input.getItem(i)))
//                return false;
//        }
//        return true;
        if (input.ingredientCount() != this.ingredients.size()) {
            return false;
        } else {
            return input.size() == 1 && this.ingredients.size() == 1
                    ? this.ingredients.getFirst().test(input.getItem(0))
                    : input.stackedContents().canCraft(this, null);
        }
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        return this.result.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<CraftingInput>> getSerializer() {
        return ModRecipeSerializer.FLETCHING_RECIPE.get();
    }

    @Override
    public RecipeType<? extends Recipe<CraftingInput>> getType() {
        return ModRecipeType.FLETCHING.get();
    }

    @Override
    public PlacementInfo placementInfo() {
        if(this.placementInfo == null) {
            this.placementInfo = PlacementInfo.create(ingredients);
        }
        return this.placementInfo;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public ItemStack getResult() {
        return result;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public static class Serializer implements RecipeSerializer<FletchingRecipe> {
        public static final MapCodec<FletchingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(FletchingRecipe::getResult),
                Codec.lazyInitialized(() -> Ingredient.CODEC.listOf(3, 3)).fieldOf("ingredients").forGetter(FletchingRecipe::getIngredients)
        ).apply(inst, FletchingRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, FletchingRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        ItemStack.STREAM_CODEC,
                        fletchingRecipe -> fletchingRecipe.result,
                        Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
                        fletchingRecipe -> fletchingRecipe.ingredients,
                        FletchingRecipe::new);

        @Override
        public MapCodec<FletchingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, FletchingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
