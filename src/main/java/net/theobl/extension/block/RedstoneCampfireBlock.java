package net.theobl.extension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneCampfireBlock extends CampfireBlock {
    public RedstoneCampfireBlock(boolean spawnParticles, int fireDamage, Properties properties) {
        super(spawnParticles, fireDamage, properties);
    }

    private static void makeRedstoneParticle(BlockState state, LevelAccessor level, BlockPos pos) {
        RandomSource random = level.getRandom();
        double d0 = (double)pos.getX() + 0.5 + (random.nextDouble() / 3) * (double)(random.nextBoolean() ? 1 : -1);
        double d1 = (double)pos.getY() + random.nextDouble() + random.nextDouble();
        double d2 = (double)pos.getZ() + 0.5 + (random.nextDouble() / 3) * (double)(random.nextBoolean() ? 1 : -1);
        level.addParticle(DustParticleOptions.REDSTONE, d0, d1, d2, 0.0, 0.0, 0.0);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (state.getValue(LIT)) {
            makeRedstoneParticle(state, level, pos);
        }
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return direction != Direction.UP && direction != Direction.DOWN && state.getValue(LIT) ? 15 : 0;
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }
}
