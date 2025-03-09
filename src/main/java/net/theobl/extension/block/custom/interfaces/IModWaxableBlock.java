package net.theobl.extension.block.custom.interfaces;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.theobl.extension.block.ModBlocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

public interface IModWaxableBlock extends IModChangeOverTimeBlock {
    Supplier<BiMap<Block, Block>> UNWAXED_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(ModBlocks.WAXED_CUT_COPPER_WALL.get(), ModBlocks.CUT_COPPER_WALL.get())
            .put(ModBlocks.WAXED_EXPOSED_CUT_COPPER_WALL.get(), ModBlocks.EXPOSED_CUT_COPPER_WALL.get())
            .put(ModBlocks.WAXED_WEATHERED_CUT_COPPER_WALL.get(), ModBlocks.WEATHERED_CUT_COPPER_WALL.get())
            .put(ModBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL.get(), ModBlocks.OXIDIZED_CUT_COPPER_WALL.get()).build());

    Supplier<BiMap<Block, Block>> WAXED_BY_BLOCK = Suppliers.memoize(() -> UNWAXED_BY_BLOCK.get().inverse());

    static Optional<Block> getUnwaxed(final Block block) {
        return Optional.ofNullable(UNWAXED_BY_BLOCK.get().get(block));
    }

    static Optional<BlockState> getUnwaxed(final BlockState blockState) {
        final boolean hasPowerProperty = blockState.hasProperty(BlockStateProperties.POWER);
        return getUnwaxed(blockState.getBlock()).map(block -> block.withPropertiesOf(IModChangeOverTimeBlock.getAdjustedBlockState(blockState)));
    }

    static Optional<Block> getWaxed(final Block block) {
        return Optional.ofNullable(WAXED_BY_BLOCK.get().get(block));
    }

    static @NotNull Optional<BlockState> getWaxed(final BlockState blockState) {
        return getWaxed(blockState.getBlock()).map(block -> block.withPropertiesOf(IModChangeOverTimeBlock.getAdjustedBlockState(blockState)));
    }

    @Nullable
    static BlockState scrapeWax(final BlockState blockState, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        final Optional<BlockState> previousBlockState = getUnwaxed(blockState);
        if(previousBlockState.isPresent()) {
            if (context.getPlayer() instanceof ServerPlayer serverPlayer) {
                Advancement advWaxOff = serverPlayer.server.getAdvancements().getAdvancement(new ResourceLocation("minecraft:husbandry/wax_off"));
                AdvancementProgress advWaxOffProgress = serverPlayer.getAdvancements().getOrStartProgress(advWaxOff);
                if (!advWaxOffProgress.isDone()) {
                    for (String criteria : advWaxOffProgress.getRemainingCriteria()) {
                        serverPlayer.getAdvancements().award(advWaxOff, criteria);
                    }
                }
            }
            context.getLevel().levelEvent(context.getPlayer(), 3004, context.getClickedPos(), 0);
            return previousBlockState.get();
        }
        return blockState;
    }

    @Nullable
    static InteractionResult applyWax(final BlockState blockState, final ItemStack itemStack, final Player player, final BlockPos blockPos, final InteractionHand hand, final Level level) {
        final Optional<BlockState> waxedBlockState = getWaxed(blockState);
        if(waxedBlockState.isPresent()) {
            final BlockState waxedState = waxedBlockState.get();
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockPos, itemStack);
            }
            hurt(itemStack, player, 1, level, hand);
            level.setBlock(blockPos, waxedState, 11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, waxedState));
            if (player instanceof ServerPlayer serverPlayer) {
                Advancement advWaxOn = serverPlayer.server.getAdvancements().getAdvancement(new ResourceLocation("minecraft:husbandry/wax_on"));
                AdvancementProgress advWaxOnProgress = serverPlayer.getAdvancements().getOrStartProgress(advWaxOn);
                if (!advWaxOnProgress.isDone()) {
                    for (String criteria : advWaxOnProgress.getRemainingCriteria()) {
                        serverPlayer.getAdvancements().award(advWaxOn, criteria);
                    }
                }
            }
            level.levelEvent(player, 3003, blockPos, 0);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    static boolean isWaxed(final BlockState blockState) {
        return UNWAXED_BY_BLOCK.get().containsKey(blockState.getBlock());
    }

    static BlockState getToolModifiedState(final BlockState blockState, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        if(isWaxed(blockState) && context.getItemInHand().getItem() instanceof AxeItem && toolAction.equals(ToolActions.AXE_WAX_OFF)) {
            return scrapeWax(blockState, context, toolAction, isClient);
        }
        return null;
    }

    static void hurt(final ItemStack itemStack, final Player player, final int amount, final Level level, final InteractionHand hand) {
        if(player == null) {
            return;
        }
        if(level != null && level.isClientSide() && hand != null) {
            player.swing(hand);
        }
        if(player.isCreative()) {
            return;
        }
        if(itemStack.isDamageableItem()) {
            itemStack.hurtAndBreak(amount, player, p -> p.broadcastBreakEvent(p.getUsedItemHand()));
        } else if(!player.isCreative()) {
            itemStack.shrink(amount);
        }
    }
}
