package net.theobl.extension.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.theobl.extension.tags.ModBlockTags;

public class EnderFireBlock extends BaseFireBlock {
    public static final MapCodec<EnderFireBlock> CODEC = simpleCodec(EnderFireBlock::new);

    @Override
    protected MapCodec<EnderFireBlock> codec() {
        return CODEC;
    }

    public EnderFireBlock(Properties properties) {
        super(properties, 2.0F);
    }

    @Override
    protected BlockState updateShape(
            BlockState state,
            LevelReader level,
            ScheduledTickAccess ticks,
            BlockPos pos,
            Direction directionToNeighbour,
            BlockPos neighbourPos,
            BlockState neighbourState,
            RandomSource random
    ) {
        return this.canSurvive(state, level, pos) ? this.defaultBlockState() : Blocks.AIR.defaultBlockState();
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canSurviveOnBlock(level.getBlockState(pos.below())) && level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP);
    }

    public static boolean canSurviveOnBlock(BlockState state) {
        return state.is(ModBlockTags.ENDER_FIRE_BASE_BLOCKS);
    }

    @Override
    protected boolean canBurn(BlockState state) {
        return true;
    }
}
