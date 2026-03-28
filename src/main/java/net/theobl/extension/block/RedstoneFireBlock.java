package net.theobl.extension.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.theobl.extension.tags.ModBlockTags;

public class RedstoneFireBlock extends BaseFireBlock {
    public static final MapCodec<RedstoneFireBlock> CODEC = simpleCodec(RedstoneFireBlock::new);

    @Override
    protected MapCodec<? extends BaseFireBlock> codec() {
        return CODEC;
    }

    public RedstoneFireBlock(Properties properties) {
        super(properties, 1.0F);
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
        return state.is(ModBlockTags.REDSTONE_FIRE_BASE_BLOCKS);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);

        BlockPos below = pos.below();
        BlockState belowState = level.getBlockState(below);
        if (!this.canBurn(belowState) && !belowState.isFaceSturdy(level, below, Direction.UP)) {
            if (this.canBurn(level.getBlockState(pos.west()))) {
                for (int i = 0; i < 2; i++) {
                    double xx = pos.getX() + random.nextDouble() * 0.1F;
                    double yy = pos.getY() + random.nextDouble();
                    double zz = pos.getZ() + random.nextDouble();
                    level.addParticle(DustParticleOptions.REDSTONE, xx, yy, zz, 0.0, 0.0, 0.0);
                }
            }

            if (this.canBurn(level.getBlockState(pos.east()))) {
                for (int i = 0; i < 2; i++) {
                    double xx = pos.getX() + 1 - random.nextDouble() * 0.1F;
                    double yy = pos.getY() + random.nextDouble();
                    double zz = pos.getZ() + random.nextDouble();
                    level.addParticle(DustParticleOptions.REDSTONE, xx, yy, zz, 0.0, 0.0, 0.0);
                }
            }

            if (this.canBurn(level.getBlockState(pos.north()))) {
                for (int i = 0; i < 2; i++) {
                    double xx = pos.getX() + random.nextDouble();
                    double yy = pos.getY() + random.nextDouble();
                    double zz = pos.getZ() + random.nextDouble() * 0.1F;
                    level.addParticle(DustParticleOptions.REDSTONE, xx, yy, zz, 0.0, 0.0, 0.0);
                }
            }

            if (this.canBurn(level.getBlockState(pos.south()))) {
                for (int i = 0; i < 2; i++) {
                    double xx = pos.getX() + random.nextDouble();
                    double yy = pos.getY() + random.nextDouble();
                    double zz = pos.getZ() + 1 - random.nextDouble() * 0.1F;
                    level.addParticle(DustParticleOptions.REDSTONE, xx, yy, zz, 0.0, 0.0, 0.0);
                }
            }

            if (this.canBurn(level.getBlockState(pos.above()))) {
                for (int i = 0; i < 2; i++) {
                    double xx = pos.getX() + random.nextDouble();
                    double yy = pos.getY() + 1 - random.nextDouble() * 0.1F;
                    double zz = pos.getZ() + random.nextDouble();
                    level.addParticle(DustParticleOptions.REDSTONE, xx, yy, zz, 0.0, 0.0, 0.0);
                }
            }
        } else {
            for (int i = 0; i < 3; i++) {
                double xx = pos.getX() + random.nextDouble();
                double yy = pos.getY() + random.nextDouble() * 0.5 + 0.5;
                double zz = pos.getZ() + random.nextDouble();
                level.addParticle(DustParticleOptions.REDSTONE, xx, yy, zz, 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected boolean canBurn(BlockState state) {
        return true;
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 15;
    }
}
