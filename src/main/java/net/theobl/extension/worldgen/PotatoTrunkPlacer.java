package net.theobl.extension.worldgen;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class PotatoTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<PotatoTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            i -> trunkPlacerParts(i)
                    .and(
                            i.group(
                                    IntProviders.POSITIVE_CODEC.fieldOf("extra_branch_steps").forGetter(p -> p.extraBranchSteps),
                                    Codec.floatRange(0.0F, 1.0F).fieldOf("place_branch_per_log_probability").forGetter(p -> p.placeBranchPerLogProbability),
                                    IntProviders.NON_NEGATIVE_CODEC.fieldOf("extra_branch_length").forGetter(c -> c.extraBranchLength),
                                    RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("can_grow_through").forGetter(t -> t.canGrowThrough),
                                    Codec.BOOL.fieldOf("megaBush").forGetter(p -> p.megaBush)
                            )
                    )
                    .apply(i, PotatoTrunkPlacer::new)
    );
    private final IntProvider extraBranchSteps;
    private final float placeBranchPerLogProbability;
    private final IntProvider extraBranchLength;
    private final HolderSet<Block> canGrowThrough;
    private final boolean megaBush;

    public PotatoTrunkPlacer(
            int baseHeight,
            int heightRandA,
            int heightRandB,
            IntProvider extraBranchSteps,
            float placeBranchPerLogProbability,
            IntProvider extraBranchLength,
            HolderSet<Block> canGrowThrough,
            boolean megaBush
    ) {
        super(baseHeight, heightRandA, heightRandB);
        this.extraBranchSteps = extraBranchSteps;
        this.placeBranchPerLogProbability = placeBranchPerLogProbability;
        this.extraBranchLength = extraBranchLength;
        this.canGrowThrough = canGrowThrough;
        this.megaBush = megaBush;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerType.POTATO_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level, BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, BlockPos origin, TreeConfiguration config) {

        List<FoliagePlacer.FoliageAttachment> attachments = Lists.<FoliagePlacer.FoliageAttachment>newArrayList();
        BlockPos.MutableBlockPos logPos = new BlockPos.MutableBlockPos();

        for(int heightPos = 0; heightPos < treeHeight; ++heightPos) {
            int currentHeight = origin.getY() + heightPos;
            if (this.placeLog(
                    level, trunkSetter, random, logPos.set(origin.getX(), currentHeight, origin.getZ()), config
            )
                    && heightPos < treeHeight - 1
                    && random.nextFloat() < this.placeBranchPerLogProbability) {
                Direction branchDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                int l = this.extraBranchLength.sample(random);
                int branchPos = Math.max(0, l - this.extraBranchLength.sample(random) - 1);
                int branchSteps = this.extraBranchSteps.sample(random);
                this.placeBranch(level, trunkSetter, random, treeHeight, config, attachments, logPos, currentHeight, branchDir, branchPos, branchSteps);
            }

            if (heightPos == treeHeight - 1) {
                attachments.add(new FoliagePlacer.FoliageAttachment(logPos.set(origin.getX(), currentHeight + 1, origin.getZ()), 0, false));
            }
        }

        return attachments;
    }

    private void placeBranch(
            WorldGenLevel level,
            BiConsumer<BlockPos, BlockState> trunkSetter,
            RandomSource random,
            int treeHeight,
            TreeConfiguration config,
            List<FoliagePlacer.FoliageAttachment> attachments,
            BlockPos.MutableBlockPos logPos,
            int currentHeight,
            Direction branchDir,
            int branchPos,
            int branchSteps
    ) {
        int heightAlongBranch = currentHeight + branchPos;
        int logX = logPos.getX();
        int logZ = logPos.getZ();

        for(int branchPlacementIndex = branchPos; branchPlacementIndex < treeHeight && branchSteps > 0; --branchSteps) {
            if (branchPlacementIndex >= 1) {
                int placementHeight = currentHeight + branchPlacementIndex;
                logX += branchDir.getStepX();
                logZ += branchDir.getStepZ();
                heightAlongBranch = placementHeight;
                if (this.placeLog(level, trunkSetter, random, logPos.set(logX, placementHeight, logZ), config)) {
                    heightAlongBranch = placementHeight + 1;
                }

                if (this.megaBush) {
                    attachments.add(new FoliagePlacer.FoliageAttachment(logPos.immutable(), 0, false));
                }
            }

            ++branchPlacementIndex;
        }

        if (heightAlongBranch - currentHeight > 1) {
            BlockPos blockpos = new BlockPos(logX, heightAlongBranch, logZ);
            attachments.add(new FoliagePlacer.FoliageAttachment(blockpos, 0, false));
            if (this.megaBush) {
                attachments.add(new FoliagePlacer.FoliageAttachment(blockpos.below(2), 0, false));
            }
        }
    }

    @Override
    protected boolean validTreePos(WorldGenLevel level, BlockPos pos) {
        return super.validTreePos(level, pos)
                || level.isStateAtPosition(pos, blockstate -> blockstate.is(this.canGrowThrough));
    }
}
