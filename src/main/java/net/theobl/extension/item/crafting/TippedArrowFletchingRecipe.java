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
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;

import java.util.List;

public class TippedArrowFletchingRecipe extends FletchingRecipe {
    public static final MapCodec<TippedArrowFletchingRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                    Ingredient.CODEC.fieldOf("source").forGetter(o -> o.source),
                    Ingredient.CODEC.fieldOf("material").forGetter(o -> o.material),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(o -> o.result)
            ).apply(i, TippedArrowFletchingRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, TippedArrowFletchingRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC,
                    o -> o.source,
                    Ingredient.CONTENTS_STREAM_CODEC,
                    o -> o.material,
                    ItemStackTemplate.STREAM_CODEC,
                    o -> o.result,
                    TippedArrowFletchingRecipe::new
            );
    public static final RecipeSerializer<TippedArrowFletchingRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);
    private final Ingredient source;
    private final Ingredient material;
    private final ItemStackTemplate result;

    public TippedArrowFletchingRecipe(Ingredient source, Ingredient material, ItemStackTemplate result) {
        super("", result, List.of(source, material, Ingredient.of(Items.FEATHER)));
        this.source = source;
        this.material = material;
        this.result = result;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        ItemStack potion = input.getItem(0);
        if (!potion.is(Items.LINGERING_POTION)) {
            return ItemStack.EMPTY;
        } else {
            ItemStack result = this.result.create();
            result.set(DataComponents.POTION_CONTENTS, potion.get(DataComponents.POTION_CONTENTS));
            return result;
        }
    }

    @Override
    public RecipeSerializer<? extends Recipe<CraftingInput>> getSerializer() {
        return SERIALIZER;
    }
}
