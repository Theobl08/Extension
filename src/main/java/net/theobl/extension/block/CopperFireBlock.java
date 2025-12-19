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

public class CopperFireBlock extends BaseFireBlock {
    public static final MapCodec<CopperFireBlock> CODEC = simpleCodec(CopperFireBlock::new);

    @Override
    protected MapCodec<? extends BaseFireBlock> codec() {
        return CODEC;
    }

    public CopperFireBlock(Properties properties) {
        super(properties, 1.0F);
    }

    @Override
    protected BlockState updateShape(
            BlockState state,
            LevelReader level,
            ScheduledTickAccess access,
            BlockPos pos,
            Direction direction,
            BlockPos neighborPos,
            BlockState neighborState,
            RandomSource randomSource
    ) {
        return this.canSurvive(state, level, pos) ? this.defaultBlockState() : Blocks.AIR.defaultBlockState();
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canSurviveOnBlock(level.getBlockState(pos.below())) && level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP);
    }

    public static boolean canSurviveOnBlock(BlockState state) {
        return state.is(ModBlockTags.COPPER_FIRE_BASE_BLOCKS);
    }

    @Override
    protected boolean canBurn(BlockState state) {
        return true;
    }
}
