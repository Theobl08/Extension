package net.theobl.extension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.cauldron.CauldronInteractions;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.theobl.extension.block.entity.PotionCauldronBlockEntity;
import net.theobl.extension.util.ModUtil;

import static net.minecraft.core.cauldron.CauldronInteractions.*;
import static net.theobl.extension.util.ModUtil.createFilledResult;

public class ExtendedCauldronInteraction {
    public static CauldronInteraction.Dispatcher MILK = newDispatcher("milk");
    public static CauldronInteraction.Dispatcher POTION = newDispatcher("potion");

    public static void bootStrap() {
        addMilkInteractions(CauldronInteractions.EMPTY);
        addMilkInteractions(CauldronInteractions.WATER);
        addMilkInteractions(CauldronInteractions.LAVA);
        addMilkInteractions(CauldronInteractions.POWDER_SNOW);

        MILK.put(
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
        addDefaultInteractions(MILK);
        addMilkInteractions(MILK);

            POTION.put(
                    Items.GLASS_BOTTLE,
                    (state, level, pos, player, hand, itemInHand) -> {
                        if(level.getBlockEntity(pos) instanceof PotionCauldronBlockEntity blockEntity && state.getValue(PotionCauldronBlock.LEVEL) != 1) {
                            if (!level.isClientSide()) {
                                Item usedItem = itemInHand.getItem();
                                player.setItemInHand(
                                        hand, ItemUtils.createFilledResult(itemInHand, player, PotionContents.createItemStack(Items.POTION, blockEntity.getPotion()))
                                );
                                player.awardStat(Stats.USE_CAULDRON);
                                player.awardStat(Stats.ITEM_USED.get(usedItem));
                                PotionCauldronBlock.lowerFillLevel(state, level, pos);
                                level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                                level.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
                                ModUtil.showPotionInteractParticles((ServerLevel) level, new PotionContents(blockEntity.getPotion()), pos, (double) (state.getValue(PotionCauldronBlock.LEVEL) - 1) / 4);
                            }

                            return InteractionResult.SUCCESS;
                        }
                        else  {
                            return InteractionResult.TRY_WITH_EMPTY_HAND;
                        }
                    }
            );
            POTION.put(Items.POTION, (state, level, pos, player, hand, itemInHand) -> {
                if (state.getValue(PotionCauldronBlock.LEVEL) == PotionCauldronBlock.MAX_FILL_LEVEL) {
                    return InteractionResult.TRY_WITH_EMPTY_HAND;
                } else {
                    PotionContents potionContents = itemInHand.get(DataComponents.POTION_CONTENTS);
                    PotionCauldronBlockEntity blockEntity = (PotionCauldronBlockEntity) level.getBlockEntity(pos);
                    if (potionContents != null && blockEntity != null && potionContents.is(blockEntity.getPotion())) {
                        if (!level.isClientSide()) {
                            player.setItemInHand(hand, ItemUtils.createFilledResult(itemInHand, player, new ItemStack(Items.GLASS_BOTTLE)));
                            player.awardStat(Stats.USE_CAULDRON);
                            player.awardStat(Stats.ITEM_USED.get(itemInHand.getItem()));
                            level.setBlockAndUpdate(pos, state.cycle(PotionCauldronBlock.LEVEL));
                            level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                            level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                            ModUtil.showPotionInteractParticles((ServerLevel) level, potionContents, pos, (double) state.getValue(PotionCauldronBlock.LEVEL) / 4);
                        }

                        return InteractionResult.SUCCESS;
                    } else {
                        return InteractionResult.TRY_WITH_EMPTY_HAND;
                    }
                }
            });
            POTION.put(Items.ARROW, ExtendedCauldronInteraction::arrowInteraction);
        }

    static void addMilkInteractions(CauldronInteraction.Dispatcher interactionsMap) {
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
        if(level.getBlockEntity(pos) instanceof PotionCauldronBlockEntity block) {
            if(!level.isClientSide()) {
                ItemStack tippedArrow = PotionContents.createItemStack(Items.TIPPED_ARROW, block.getPotion());
                int count = itemInHand.getCount();
                int currentLevel = state.getValue(PotionCauldronBlock.LEVEL);
                if((count >= 1 && count <= 16) || (count >= 17 && count <= 32 && currentLevel >= 2) || (count >= 33 && count <= 48 && currentLevel >= 3)
                        || (count >= 49 && count <= 64 && currentLevel >= 4)) {
                    craftTippedArrow(tippedArrow, state, level, pos, player, hand, itemInHand, count);
                } else if (currentLevel == 1) {
                    craftTippedArrow(tippedArrow, state, level, pos, player, hand, itemInHand, 16);
                } else if (currentLevel == 2) {
                    craftTippedArrow(tippedArrow, state, level, pos, player, hand, itemInHand, 32);
                } else if (currentLevel == 3) {
                    craftTippedArrow(tippedArrow, state, level, pos, player, hand, itemInHand, 48);
                }
                ModUtil.showPotionInteractParticles((ServerLevel) level, new PotionContents(block.getPotion()), pos, (double) (state.getValue(PotionCauldronBlock.LEVEL) - 1) / 4);
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

    private static void craftTippedArrow(
            ItemStack tippedArrow, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack itemInHand, int count) {
        tippedArrow.setCount(count);
        player.setItemInHand(hand, createFilledResult(itemInHand, player, tippedArrow, true, count));
        PotionCauldronBlock.lowerFillLevelArrow(state, level, pos, Mth.ceil((float) count / 16.0F));
    }
}
