package net.theobl.extension.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NullMarked;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@NullMarked
public class FletchingRecipe implements Recipe<CraftingInput> {
    public static final MapCodec<FletchingRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(
            inst -> inst.group(
                    Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(FletchingRecipe::getResult),
                    Codec.lazyInitialized(() -> Ingredient.CODEC.listOf(3, 3)).fieldOf("ingredients").forGetter(FletchingRecipe::getIngredients)
            )
            .apply(inst, FletchingRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, FletchingRecipe> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            fletchingRecipe -> fletchingRecipe.group,
            ItemStackTemplate.STREAM_CODEC,
            fletchingRecipe -> fletchingRecipe.result,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
            fletchingRecipe -> fletchingRecipe.ingredients,
            FletchingRecipe::new
    );
    public static final RecipeSerializer<FletchingRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    final String group;
    protected final ItemStackTemplate result;
    protected final List<Ingredient> ingredients;
    @Nullable
    private PlacementInfo placementInfo;

    public FletchingRecipe(String group, ItemStackTemplate result, List<Ingredient> ingredients) {
        this.group = group;
        this.result = result;
        this.ingredients = ingredients;
    }

    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        return defaultCraftingReminder(input);
    }

    static NonNullList<ItemStack> defaultCraftingReminder(CraftingInput input) {
        NonNullList<ItemStack> result = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        for (int slot = 0; slot < result.size(); slot++) {
            var item = input.getItem(slot);
            ItemStackTemplate remainder = item.getCraftingRemainder();
            result.set(slot, remainder != null ? remainder.create() : ItemStack.EMPTY);
        }

        return result;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (input.ingredientCount() != this.ingredients.size()) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            if (!this.ingredients.get(i).test(input.getItem(i)))
                return false;
        }
//        return true;
            return input.size() == 1 && this.ingredients.size() == 1
                    ? this.ingredients.getFirst().test(input.getItem(0))
                    : input.stackedContents().canCraft(this, null);
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        return this.result.create();
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public RecipeSerializer<? extends Recipe<CraftingInput>> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public String group() {
        return this.group;
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

    public ItemStackTemplate getResult() {
        return result;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
