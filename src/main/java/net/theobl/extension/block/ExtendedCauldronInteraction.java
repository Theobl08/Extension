package net.theobl.extension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.common.NeoForgeMod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

import static net.minecraft.core.cauldron.CauldronInteraction.*;

public interface ExtendedCauldronInteraction {
    CauldronInteraction.InteractionMap MILK = newInteractionMap("milk");
    Map<Holder<Potion>, CauldronInteraction.InteractionMap> POTIONS_INTERACTIONS = new HashMap<>();
    List<Holder<Potion>> POTIONS = StreamSupport.stream(BuiltInRegistries.POTION.asHolderIdMap().spliterator(), false)
            .filter(potionHolder -> !potionHolder.is(Potions.WATER)).toList();

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

        for(Holder<Potion> potion : POTIONS) {
            Map<Item, CauldronInteraction> map = POTIONS_INTERACTIONS.get(potion).map();
            map.put(
                    Items.GLASS_BOTTLE,
                    (state, level, pos, player, hand, itemInHand) -> {
                        if (!level.isClientSide()) {
                            Item usedItem = itemInHand.getItem();
                            player.setItemInHand(
                                    hand, ItemUtils.createFilledResult(itemInHand, player, PotionContents.createItemStack(Items.POTION, potion))
                            );
                            player.awardStat(Stats.USE_CAULDRON);
                            player.awardStat(Stats.ITEM_USED.get(usedItem));
                            PotionCauldronBlock.lowerFillLevel(state, level, pos);
                            level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                            level.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
                        }

                        return InteractionResult.SUCCESS;
                    }
            );
            map.put(Items.POTION, (state, level, pos, player, hand, itemInHand) -> {
                if (state.getValue(PotionCauldronBlock.LEVEL) == PotionCauldronBlock.MAX_FILL_LEVEL) {
                    return InteractionResult.TRY_WITH_EMPTY_HAND;
                } else {
                    PotionContents potionContents = itemInHand.get(DataComponents.POTION_CONTENTS);
                    if (potionContents != null && potionContents.is(potion)) {
                        if (!level.isClientSide()) {
                            player.setItemInHand(hand, ItemUtils.createFilledResult(itemInHand, player, new ItemStack(Items.GLASS_BOTTLE)));
                            player.awardStat(Stats.USE_CAULDRON);
                            player.awardStat(Stats.ITEM_USED.get(itemInHand.getItem()));
                            level.setBlockAndUpdate(pos, state.cycle(PotionCauldronBlock.LEVEL));
                            level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                            level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                        }

                        return InteractionResult.SUCCESS;
                    } else {
                        return InteractionResult.TRY_WITH_EMPTY_HAND;
                    }
                }
            });
            map.put(Items.ARROW, ExtendedCauldronInteraction::arrowInteraction);
        }
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

    private static InteractionResult arrowInteraction(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack itemInHand) {
        if(state.getBlock() instanceof PotionCauldronBlock block) {
            if(!level.isClientSide()) {
                ItemStack tippedArrow = PotionContents.createItemStack(Items.TIPPED_ARROW, block.getPotion());
                int count = itemInHand.getCount();
                if(count >= 1 && count <= 16) {
                    tippedArrow.setCount(count);
                    player.setItemInHand(hand, createFilledResult(itemInHand, player, tippedArrow, true, count));
                    PotionCauldronBlock.lowerFillLevelArrow(state, level, pos, 1);
                } else if (count >= 17 && count <= 32) {
                    if(state.getValue(PotionCauldronBlock.LEVEL) >= 2) {
                        tippedArrow.setCount(count);
                        player.setItemInHand(hand, createFilledResult(itemInHand, player, tippedArrow, true, count));
                        PotionCauldronBlock.lowerFillLevelArrow(state, level, pos, 2);
                    }
                    else {
                        tippedArrow.setCount(16);
                        player.setItemInHand(hand, createFilledResult(itemInHand, player, tippedArrow, true, 16));
                        PotionCauldronBlock.lowerFillLevelArrow(state, level, pos, 1);
                    }
                } else if (count >= 33 && count <= 48) {
                    if(state.getValue(PotionCauldronBlock.LEVEL) >= 3) {
                        tippedArrow.setCount(count);
                        player.setItemInHand(hand, createFilledResult(itemInHand, player, tippedArrow, true, count));
                        PotionCauldronBlock.lowerFillLevelArrow(state, level, pos, 3);
                    } else if (state.getValue(PotionCauldronBlock.LEVEL) == 2) {
                        tippedArrow.setCount(32);
                        player.setItemInHand(hand, createFilledResult(itemInHand, player, tippedArrow, true, 32));
                        PotionCauldronBlock.lowerFillLevelArrow(state, level, pos, 2);
                    } else {
                        tippedArrow.setCount(16);
                        player.setItemInHand(hand, createFilledResult(itemInHand, player, tippedArrow, true, 16));
                        PotionCauldronBlock.lowerFillLevelArrow(state, level, pos, 1);
                    }
                } else if (count >= 49 && count <= 64) {
                    if(state.getValue(PotionCauldronBlock.LEVEL) >= 4) {
                        tippedArrow.setCount(count);
                        player.setItemInHand(hand, createFilledResult(itemInHand, player, tippedArrow, true, count));
                        PotionCauldronBlock.lowerFillLevelArrow(state, level, pos, 4);
                    } else if(state.getValue(PotionCauldronBlock.LEVEL) == 3) {
                        tippedArrow.setCount(48);
                        player.setItemInHand(hand, createFilledResult(itemInHand, player, tippedArrow, true, 48));
                        PotionCauldronBlock.lowerFillLevelArrow(state, level, pos, 3);
                    } else if (state.getValue(PotionCauldronBlock.LEVEL) == 2) {
                        tippedArrow.setCount(32);
                        player.setItemInHand(hand, createFilledResult(itemInHand, player, tippedArrow, true, 32));
                        PotionCauldronBlock.lowerFillLevelArrow(state, level, pos, 2);
                    } else {
                        tippedArrow.setCount(16);
                        player.setItemInHand(hand, createFilledResult(itemInHand, player, tippedArrow, true, 16));
                        PotionCauldronBlock.lowerFillLevelArrow(state, level, pos, 1);
                    }
                }
            }
            return InteractionResult.SUCCESS;
        }
        else {
            return InteractionResult.TRY_WITH_EMPTY_HAND;
        }
    }

    private static boolean isUnderWater(Level level, BlockPos pos) {
        FluidState fluidstate = level.getFluidState(pos.above());
        return fluidstate.is(FluidTags.WATER);
    }

    static void init() {
        for(Holder<Potion> potionHolder : POTIONS) {
            CauldronInteraction.InteractionMap interactionMap = newInteractionMap(potionHolder.unwrapKey().get().identifier().getPath());
            POTIONS_INTERACTIONS.put(potionHolder, interactionMap);
        }
    }

    static ItemStack createFilledResult(
            ItemStack itemStack, Player player, ItemStack newItemStack, boolean limitCreativeStackSize, int consumedAmount) {
        boolean isCreative = player.hasInfiniteMaterials();
        if (limitCreativeStackSize && isCreative) {
            if (!player.getInventory().contains(newItemStack)) {
                player.getInventory().add(newItemStack);
            }

            return itemStack;
        } else {
            itemStack.consume(consumedAmount, player);
            if (itemStack.isEmpty()) {
                return newItemStack;
            } else {
                if (!player.getInventory().add(newItemStack)) {
                    player.drop(newItemStack, false);
                }

                return itemStack;
            }
        }
    }
}
