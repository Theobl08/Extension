package net.theobl.extension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.redstone.Orientation;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static net.minecraft.world.level.block.RedstoneTorchBlock.LIT;

@ParametersAreNonnullByDefault
public class RedstoneLanternBlock extends LanternBlock {
    public RedstoneLanternBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if(!level.isClientSide()) tick(state, ((ServerLevel) level), pos, level.random);
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return direction != Direction.UP && direction != Direction.DOWN && state.getValue(LIT) ? 15 : 0;
    }

    protected boolean hasNeighborSignal(Level level, BlockPos pos, BlockState state) {
        if(state.getValue(HANGING))
            return level.hasSignal(pos.above(), Direction.UP);
        else
            return level.hasSignal(pos.below(), Direction.DOWN);

    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        boolean flag = this.hasNeighborSignal(level, pos, state);
        List<RedstoneTorchBlock.Toggle> list = RedstoneTorchBlock.RECENT_TOGGLES.get(level);

        while (list != null && !list.isEmpty() && level.getGameTime() - list.getFirst().when > 60L) {
            list.removeFirst();
        }

        if (state.getValue(LIT)) {
            if (flag) {
                level.setBlock(pos, state.setValue(LIT, Boolean.FALSE), 3);
                if (RedstoneTorchBlock.isToggledTooFrequently(level, pos, true)) {
                    level.levelEvent(1502, pos, 0);
                    level.scheduleTick(pos, level.getBlockState(pos).getBlock(), 160);
                }
            }
        } else if (!flag && !RedstoneTorchBlock.isToggledTooFrequently(level, pos, false)) {
            level.setBlock(pos, state.setValue(LIT, Boolean.TRUE), 3);
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        if (state.getValue(LIT) == this.hasNeighborSignal(level, pos, state)) {
            level.scheduleTick(pos, this, 2);
        }
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(LIT);
    }
}
