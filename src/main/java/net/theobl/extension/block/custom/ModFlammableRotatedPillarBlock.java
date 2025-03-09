package net.theobl.extension.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.theobl.extension.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {
    public ModFlammableRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

//    @Override
//    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
//        if(context.getItemInHand().getItem() instanceof AxeItem) {
//            if(state.is(ModBlocks.WHITE_LOG.get())){
//                return ModBlocks.STRIPPED_WHITE_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
//            }
//            if(state.is(ModBlocks.WHITE_WOOD.get())){
//                return ModBlocks.STRIPPED_WHITE_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
//            }
//        }
//        return super.getToolModifiedState(state, context, toolAction, simulate);
//    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        Map<Block, Block> standardToStripped = new HashMap<>();
//        standardToStripped.put(ModBlocks.WHITE_LOG.get(), ModBlocks.COLORED_STRIPPED_LOGS.get(0).get());
//        standardToStripped.put(ModBlocks.WHITE_WOOD.get(), ModBlocks.COLORED_STRIPPED_WOODS.get(0).get());
//        standardToStripped.put(ModBlocks.LIGHT_GRAY_LOG.get(), ModBlocks.COLORED_STRIPPED_LOGS.get(1).get());
//        standardToStripped.put(ModBlocks.LIGHT_GRAY_WOOD.get(), ModBlocks.COLORED_STRIPPED_WOODS.get(1).get());
//        standardToStripped.put(ModBlocks.GRAY_LOG.get(), ModBlocks.COLORED_STRIPPED_LOGS.get(2).get());
//        standardToStripped.put(ModBlocks.GRAY_WOOD.get(), ModBlocks.COLORED_STRIPPED_WOODS.get(2).get());
//        standardToStripped.put(ModBlocks.BLACK_LOG.get(), ModBlocks.COLORED_STRIPPED_LOGS.get(3).get());
//        standardToStripped.put(ModBlocks.BLACK_WOOD.get(), ModBlocks.COLORED_STRIPPED_WOODS.get(3).get());

        if(context.getItemInHand().getItem() instanceof AxeItem) {
            for (Map.Entry<Block, Block> entry : standardToStripped.entrySet()) {
                if(state.is(entry.getKey())){
                    return entry.getValue().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                }
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
