package net.theobl.extension.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;

import java.util.List;

public class TippedArrowFletchingRecipe extends FletchingRecipe {
    private final CraftingBookCategory category;

    public TippedArrowFletchingRecipe(CraftingBookCategory category) {
        super("", new ItemStack(Items.TIPPED_ARROW), List.of(Ingredient.of(Items.LINGERING_POTION), Ingredient.of(Items.STICK), Ingredient.of(Items.FEATHER)));
        this.category = category;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack potion = input.getItem(0);
        if (!potion.is(Items.LINGERING_POTION)) {
            return ItemStack.EMPTY;
        } else {
            ItemStack result = new ItemStack(Items.TIPPED_ARROW, 8);
            result.set(DataComponents.POTION_CONTENTS, potion.get(DataComponents.POTION_CONTENTS));
            return result;
        }
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public CraftingBookCategory category() {
        return this.category;
    }

    @Override
    public RecipeSerializer<? extends Recipe<CraftingInput>> getSerializer() {
        return ModRecipeSerializer.TIPPED_ARROW.get();
    }

    public static class Serializer implements RecipeSerializer<TippedArrowFletchingRecipe> {
        public static final MapCodec<TippedArrowFletchingRecipe> CODEC = RecordCodecBuilder.mapCodec(
                inst -> inst.group(
                        CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(TippedArrowFletchingRecipe::category)
                ).apply(inst, TippedArrowFletchingRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, TippedArrowFletchingRecipe> STREAM_CODEC =
                StreamCodec.composite(CraftingBookCategory.STREAM_CODEC, TippedArrowFletchingRecipe::category, TippedArrowFletchingRecipe::new);

        @Override
        public MapCodec<TippedArrowFletchingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, TippedArrowFletchingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
