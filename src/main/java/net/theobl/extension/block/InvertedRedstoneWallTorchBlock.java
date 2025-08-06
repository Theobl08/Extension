package net.theobl.extension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneWallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class InvertedRedstoneWallTorchBlock extends RedstoneWallTorchBlock {
    public InvertedRedstoneWallTorchBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.FALSE));
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        boolean flag = this.hasNeighborSignal(level, pos, state);
        List<Toggle> list = RECENT_TOGGLES.get(level);

        while (list != null && !list.isEmpty() && level.getGameTime() - list.get(0).when > 60L) {
            list.remove(0);
        }

        if (!state.getValue(LIT)) {
            if (flag) {
                level.setBlock(pos, state.setValue(LIT, Boolean.TRUE), 3);
                if (isToggledTooFrequently(level, pos, true)) {
                    level.levelEvent(1502, pos, 0);
                    level.scheduleTick(pos, level.getBlockState(pos).getBlock(), 160);
                }
            }
        } else if (!flag && !isToggledTooFrequently(level, pos, false)) {
            level.setBlock(pos, state.setValue(LIT, Boolean.FALSE), 3);
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (state.getValue(LIT) != this.hasNeighborSignal(level, pos, state) && !level.getBlockTicks().willTickThisTick(pos, this)) {
            level.scheduleTick(pos, this, 2);
        }
    }
}
