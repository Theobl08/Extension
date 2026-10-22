package net.theobl.extension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.cauldron.CauldronInteractions;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
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
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.RegisterCauldronInteractionEvent;
import net.theobl.extension.Extension;
import net.theobl.extension.block.entity.PotionCauldronBlockEntity;
import net.theobl.extension.item.ModItems;
import net.theobl.extension.util.ModUtil;

import static net.minecraft.core.cauldron.CauldronInteractions.*;
import static net.theobl.extension.util.ModUtil.createFilledResult;

@EventBusSubscriber(modid = Extension.MODID)
public class ExtendedCauldronInteraction {
    public static final CauldronInteraction.Dispatcher MILK = new CauldronInteraction.Dispatcher();
    public static final CauldronInteraction.Dispatcher POTION = new CauldronInteraction.Dispatcher();
    public static final CauldronInteraction.Dispatcher BEETROOT_SOUP = new CauldronInteraction.Dispatcher();
    public static final CauldronInteraction.Dispatcher MUSHROOM_STEW = new CauldronInteraction.Dispatcher();
    public static final Identifier MILK_ID = Extension.asResource("milk");
    public static final Identifier POTION_ID = Extension.asResource("potion");
    public static final Identifier BEETROOT_SOUP_ID = Extension.asResource("beetroot_soup");
    public static final Identifier MUSHROOM_STEW_ID = Extension.asResource("mushroom_stew");

    @SubscribeEvent
    private static void registerCauldronInteractionDispatchers(RegisterCauldronInteractionEvent.Dispatcher event) {
        event.register(MILK_ID, MILK);
        event.register(POTION_ID, POTION);
        event.register(BEETROOT_SOUP_ID, BEETROOT_SOUP);
        event.register(MUSHROOM_STEW_ID, MUSHROOM_STEW);
    }

    @SubscribeEvent
    private static void registerCauldronInteractions(RegisterCauldronInteractionEvent.Interaction event) {
        final Identifier EMPTY = Identifier.withDefaultNamespace("empty");
        event.registerToAll(Items.MILK_BUCKET, ExtendedCauldronInteraction::fillMilkInteraction);
        event.registerToAll(ModItems.BEETROOT_SOUP_BUCKET.get(), ExtendedCauldronInteraction::fillBeetrootSoupInteraction);
        event.registerToAll(ModItems.MUSHROOM_STEW_BUCKET.get(), ExtendedCauldronInteraction::fillMushroomStewInteraction);

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

        addDefaultInteractions(BEETROOT_SOUP);
        event.register(
                BEETROOT_SOUP_ID,
                Items.BUCKET,
                (state, level, pos, player, hand, itemInHand) -> fillBucket(
                        state,
                        level,
                        pos,
                        player,
                        hand,
                        itemInHand,
                        ModItems.BEETROOT_SOUP_BUCKET.toStack(),
                        s -> true,
                        SoundEvents.BUCKET_FILL
                )
        );
        event.register(BEETROOT_SOUP_ID, Items.BOWL, (state, level, pos, player, hand, itemInHand) -> {
            if (!level.isClientSide()) {
                Item usedItem = itemInHand.getItem();
                player.setItemInHand(hand, ItemUtils.createFilledResult(itemInHand, player, Items.BEETROOT_SOUP.getDefaultInstance()));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(usedItem));
                FourLayeredCauldronBlock.lowerFillLevel(state, level, pos);
                level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
            }

            return InteractionResult.SUCCESS;
        });
        event.register(BEETROOT_SOUP_ID, Items.BEETROOT_SOUP, (state, level, pos, player, hand, itemInHand) -> {
            if (state.getValue(FourLayeredCauldronBlock.LEVEL) == FourLayeredCauldronBlock.MAX_FILL_LEVEL) {
                return InteractionResult.TRY_WITH_EMPTY_HAND;
            }

            if (!level.isClientSide()) {
                Item usedItem = itemInHand.getItem();
                player.setItemInHand(hand, ItemUtils.createFilledResult(itemInHand, player, Items.BOWL.getDefaultInstance()));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(usedItem));
                level.setBlockAndUpdate(pos, state.cycle(FourLayeredCauldronBlock.LEVEL));
                level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });
        event.register(EMPTY, Items.BEETROOT_SOUP, (state, level, pos, player, hand, itemInHand) -> {
            if (!level.isClientSide()) {
                Item usedItem = itemInHand.getItem();
                player.setItemInHand(hand, ItemUtils.createFilledResult(itemInHand, player, Items.BOWL.getDefaultInstance()));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(usedItem));
                level.setBlockAndUpdate(pos, ModBlocks.BEETROOT_SOUP_CAULDRON.get().defaultBlockState());
                level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

        addDefaultInteractions(MUSHROOM_STEW);
        event.register(
                MUSHROOM_STEW_ID,
                Items.BUCKET,
                (state, level, pos, player, hand, itemInHand) -> fillBucket(
                        state,
                        level,
                        pos,
                        player,
                        hand,
                        itemInHand,
                        ModItems.MUSHROOM_STEW_BUCKET.toStack(),
                        s -> true,
                        SoundEvents.BUCKET_FILL
                )
        );
        event.register(MUSHROOM_STEW_ID, Items.BOWL, (state, level, pos, player, hand, itemInHand) -> {
            if (!level.isClientSide()) {
                Item usedItem = itemInHand.getItem();
                player.setItemInHand(hand, ItemUtils.createFilledResult(itemInHand, player, Items.MUSHROOM_STEW.getDefaultInstance()));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(usedItem));
                FourLayeredCauldronBlock.lowerFillLevel(state, level, pos);
                level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
            }

            return InteractionResult.SUCCESS;
        });
        event.register(MUSHROOM_STEW_ID, Items.MUSHROOM_STEW, (state, level, pos, player, hand, itemInHand) -> {
            if (state.getValue(FourLayeredCauldronBlock.LEVEL) == FourLayeredCauldronBlock.MAX_FILL_LEVEL) {
                return InteractionResult.TRY_WITH_EMPTY_HAND;
            }

            if (!level.isClientSide()) {
                Item usedItem = itemInHand.getItem();
                player.setItemInHand(hand, ItemUtils.createFilledResult(itemInHand, player, Items.BOWL.getDefaultInstance()));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(usedItem));
                level.setBlockAndUpdate(pos, state.cycle(FourLayeredCauldronBlock.LEVEL));
                level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });
        event.register(EMPTY, Items.MUSHROOM_STEW, (state, level, pos, player, hand, itemInHand) -> {
            if (!level.isClientSide()) {
                Item usedItem = itemInHand.getItem();
                player.setItemInHand(hand, ItemUtils.createFilledResult(itemInHand, player, Items.BOWL.getDefaultInstance()));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(usedItem));
                level.setBlockAndUpdate(pos, ModBlocks.MUSHROOM_STEW_CAULDRON.get().defaultBlockState());
                level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

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
            event.register(POTION_ID, Items.ARROW, ExtendedCauldronInteraction::arrowInteraction);
    }

    private static InteractionResult fillMilkInteraction(
            BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack filledStack
    ) {
        return isUnderWater(level, pos)
                ? InteractionResult.CONSUME
                : emptyBucket(level, pos, player, hand, filledStack, ModBlocks.MILK_CAULDRON.get().defaultBlockState(), NeoForgeMod.BUCKET_EMPTY_MILK.get());
    }

    private static InteractionResult fillBeetrootSoupInteraction(
            BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack filledStack
    ) {
        return isUnderWater(level, pos)
                ? InteractionResult.CONSUME
                : emptyBucket(level, pos, player, hand, filledStack, ModBlocks.BEETROOT_SOUP_CAULDRON.get().defaultBlockState().setValue(FourLayeredCauldronBlock.LEVEL, FourLayeredCauldronBlock.MAX_FILL_LEVEL), SoundEvents.BUCKET_EMPTY);
    }

    private static InteractionResult fillMushroomStewInteraction(
            BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack filledStack
    ) {
        return isUnderWater(level, pos)
                ? InteractionResult.CONSUME
                : emptyBucket(level, pos, player, hand, filledStack, ModBlocks.MUSHROOM_STEW_CAULDRON.get().defaultBlockState().setValue(FourLayeredCauldronBlock.LEVEL, FourLayeredCauldronBlock.MAX_FILL_LEVEL), SoundEvents.BUCKET_EMPTY);
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
