package net.theobl.extension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.common.NeoForgeMod;

import java.util.Map;

import static net.minecraft.core.cauldron.CauldronInteraction.*;

public interface ExtendedCauldronInteraction {
    CauldronInteraction.InteractionMap MILK = newInteractionMap("milk");

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
        interactionsMap.put(Items.MILK_BUCKET, ExtendedCauldronInteraction::fillMilkInteraction);
    }

    private static InteractionResult fillMilkInteraction(
            BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack filledStack
    ) {
        return isUnderWater(level, pos)
                ? InteractionResult.CONSUME
                : emptyBucket(level, pos, player, hand, filledStack, ModBlocks.MILK_CAULDRON.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY_LAVA);
    }

    private static boolean isUnderWater(Level level, BlockPos pos) {
        FluidState fluidstate = level.getFluidState(pos.above());
        return fluidstate.is(FluidTags.WATER);
    }
}
