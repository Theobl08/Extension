package net.theobl.extension.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AttachedListToLeavesDecorator extends TreeDecorator {
    public static final MapCodec<AttachedListToLeavesDecorator> CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                            Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(p -> p.probability),
                            Codec.BOOL.optionalFieldOf("use_logs", false).forGetter(p -> p.useLogs),
                            Codec.intRange(0, 16).fieldOf("exclusion_radius_xz").forGetter(p -> p.exclusionRadiusXZ),
                            Codec.intRange(0, 16).fieldOf("exclusion_radius_y").forGetter(p -> p.exclusionRadiusY),
                            Codec.list(BlockStateProvider.CODEC).fieldOf("block_provider").forGetter(p -> p.blockProvider),
                            Codec.intRange(1, 16).fieldOf("required_empty_blocks").forGetter(p -> p.requiredEmptyBlocks),
                            ExtraCodecs.nonEmptyList(Direction.CODEC.listOf()).fieldOf("directions").forGetter(p -> p.directions)
                    )
                    .apply(i, AttachedListToLeavesDecorator::new)
    );
    protected final float probability;
    protected final int exclusionRadiusXZ;
    protected final int exclusionRadiusY;
    protected final List<BlockStateProvider> blockProvider;
    protected final int requiredEmptyBlocks;
    protected final List<Direction> directions;
    protected boolean useLogs;

    public AttachedListToLeavesDecorator(float probability, boolean useLogs, int exclusionRadiusXZ, int exclusionRadiusY, List<BlockStateProvider> blockProvider, int requiredEmptyBlocks, List<Direction> directions) {
        this.probability = probability;
        this.useLogs = useLogs;
        this.exclusionRadiusXZ = exclusionRadiusXZ;
        this.exclusionRadiusY = exclusionRadiusY;
        this.blockProvider = blockProvider;
        this.requiredEmptyBlocks = requiredEmptyBlocks;
        this.directions = directions;
    }

    @Override
    public void place(Context context) {
        Set<BlockPos> propaguleBlacklist = new HashSet();
        RandomSource random = context.random();

        for(BlockPos leafPos : this.useLogs
                ? Util.shuffledCopy(context.logs(), random)
                : Util.shuffledCopy(context.leaves(), random)) {
            Direction direction = Util.getRandom(this.directions, random);
            BlockPos placementPos = leafPos.relative(direction);
            if (!propaguleBlacklist.contains(placementPos) && random.nextFloat() < this.probability && this.hasRequiredEmptyBlocks(context, leafPos, direction)
                )
            {
                BlockPos corner1 = placementPos.offset(-this.exclusionRadiusXZ, -this.exclusionRadiusY, -this.exclusionRadiusXZ);
                BlockPos corner2 = placementPos.offset(this.exclusionRadiusXZ, this.exclusionRadiusY, this.exclusionRadiusXZ);

                for(BlockPos inPos : BlockPos.betweenClosed(corner1, corner2)) {
                    propaguleBlacklist.add(inPos.immutable());
                }

                for(BlockStateProvider stateProvider : this.blockProvider) {
                    context.setBlock(placementPos, stateProvider.getState(context.level(), random, placementPos));
                    placementPos = placementPos.relative(direction);
                }
            }
        }
    }

    private boolean hasRequiredEmptyBlocks(TreeDecorator.Context context, BlockPos leafPos, Direction direction) {
        for(int i = 1; i <= this.requiredEmptyBlocks; ++i) {
            BlockPos offsetPos = leafPos.relative(direction, i);
            if (!context.isAir(offsetPos)) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecoratorType.ATTACHED_LIST_TO_LEAVES.get();
    }
}
