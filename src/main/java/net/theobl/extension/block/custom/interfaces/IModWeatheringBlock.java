package net.theobl.extension.block.custom.interfaces;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.theobl.extension.block.ModBlocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

public interface IModWeatheringBlock extends IModChangeOverTimeBlock {

    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(ModBlocks.CUT_COPPER_WALL.get(), ModBlocks.EXPOSED_CUT_COPPER_WALL.get())
            .put(ModBlocks.EXPOSED_CUT_COPPER_WALL.get(), ModBlocks.WEATHERED_CUT_COPPER_WALL.get())
            .put(ModBlocks.WEATHERED_CUT_COPPER_WALL.get(), ModBlocks.OXIDIZED_CUT_COPPER_WALL.get()).build());

    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());

    static Optional<Block> getPrevious(final Block block) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(block));
    }

    static Optional<BlockState> getPrevious(final BlockState blockState) {
        return getPrevious(blockState.getBlock()).map(block -> block.withPropertiesOf(IModChangeOverTimeBlock.getAdjustedBlockState(blockState)));
    }

    static Block getFirst(final Block block) {
        Block firstBlock = block;
        for(Block previousBlock = PREVIOUS_BY_BLOCK.get().get(block); previousBlock != null; previousBlock = PREVIOUS_BY_BLOCK.get().get(previousBlock)) {
            firstBlock = previousBlock;
        }
        return firstBlock;
    }

    static BlockState getFirst(final BlockState blockState) {
        return getFirst(blockState.getBlock()).withPropertiesOf(IModChangeOverTimeBlock.getAdjustedBlockState(blockState));
    }

    static Optional<Block> getNext(final Block block) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(block));
    }

    default @NotNull Optional<BlockState> getNext(final BlockState blockState) {
        return getNext(blockState.getBlock()).map(block -> block.withPropertiesOf(IModChangeOverTimeBlock.getAdjustedBlockState(blockState)));
    }

    static void randomTick(final ChangeOverTimeBlock<WeatheringCopper.WeatherState> block, final BlockState blockState, final ServerLevel level, final BlockPos blockPos, final RandomSource randomSource) {
        if(!isWaxed(blockState)) {
            block.onRandomTick(blockState, level, blockPos, randomSource);
        }
    }

    static boolean isRandomlyTicking(final BlockState blockState) {
        return !isWaxed(blockState) && getNext(blockState.getBlock()).isPresent();
    }

    static BlockState getToolModifiedState(final BlockState blockState, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        if(!isWaxed(blockState)) {
            return scrapeWeatherState(blockState, context, toolAction, isClient);
        }
        return IModWaxableBlock.getToolModifiedState(blockState, context, toolAction, isClient);
    }

    @Nullable
    static BlockState scrapeWeatherState(final BlockState blockState, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        final ItemStack itemStack = context.getItemInHand();
        if(itemStack.getItem() instanceof AxeItem && toolAction.equals(ToolActions.AXE_SCRAPE)) {
            final Optional<BlockState> previousBlockState = getPrevious(blockState);
            if(previousBlockState.isPresent()) {
                context.getLevel().levelEvent(context.getPlayer(), 3005, context.getClickedPos(), 0);
                return previousBlockState.get();
            }
        }
        return null;
    }

    private static boolean isWaxed(final BlockState blockState) {
        return IModWaxableBlock.isWaxed(blockState);
    }

    static void lightningStrike(final BlockState blockState, final Level level, final BlockPos blockPos) {
        if(!IModWaxableBlock.isWaxed(blockState)) {
            level.setBlockAndUpdate(blockPos, getFirst(blockState));
            final BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
            final int blocks = level.random.nextInt(3) + 3;

            for(int j = 0; j < blocks; ++j) {
                mutableBlockPos.set(blockPos);
                for(int i = 0; i < (level.random.nextInt(8) + 1); ++i) {
                    final Optional<BlockPos> optionalCopperPos = randomStepCleaningCopper(level, mutableBlockPos);
                    if (optionalCopperPos.isEmpty()) {
                        break;
                    }
                    mutableBlockPos.set(optionalCopperPos.get());
                }
            }
        }
    }

    private static Optional<BlockPos> randomStepCleaningCopper(final Level level, final BlockPos blockPos) {
        for(BlockPos blockpos : BlockPos.randomInCube(level.random, 10, blockPos, 1)) {
            final BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.getBlock() instanceof IModWeatheringBlock) {
                getPrevious(blockstate).ifPresent(blockState -> level.setBlockAndUpdate(blockpos, blockState));
                level.levelEvent(3002, blockpos, -1);
                return Optional.of(blockpos);
            }
        }
        return Optional.empty();
    }
}
