package net.theobl.extension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class RedstoneCarvedPumpkinBlock extends CarvedPumpkinBlock {
    public RedstoneCarvedPumpkinBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 15;
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }
}
