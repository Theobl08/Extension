package net.theobl.extension.block;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.NeoForgeMod;

import java.util.Map;

import static net.minecraft.core.cauldron.CauldronInteraction.*;

public interface ExtendedCauldronInteraction {
    CauldronInteraction.InteractionMap MILK = newInteractionMap("milk");
    CauldronInteraction FILL_MILK = (state, level, pos, player, hand, itemInHand) -> emptyBucket(
            level, pos, player, hand, itemInHand, ModBlocks.MILK_CAULDRON.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY);

    static void bootStrap() {
        addMilkInteractions(CauldronInteraction.EMPTY.map());
        addMilkInteractions(CauldronInteraction.WATER.map());
        addMilkInteractions(CauldronInteraction.LAVA.map());
        addMilkInteractions(CauldronInteraction.POWDER_SNOW.map());

        Map<Item, CauldronInteraction> milk = MILK.map();
        milk.put(
                Items.BUCKET,
                (state, level, pos, player, hand, itemInHand) -> fillBucket(
                        state,
                        level,
                        pos,
                        player,
                        hand,
                        itemInHand,
                        new ItemStack(Items.MILK_BUCKET),
                        s -> true,
                        NeoForgeMod.BUCKET_FILL_MILK.get()
                )
        );
        addDefaultInteractions(milk);
        addMilkInteractions(milk);
    }

    static void addMilkInteractions(Map<Item, CauldronInteraction> interactionsMap) {
        interactionsMap.put(Items.MILK_BUCKET, FILL_MILK);
    }
}