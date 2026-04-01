package net.theobl.extension.worldgen;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.UpwardsBranchingTrunkPlacer;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;

import java.util.List;

public class ModTreeFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> POTATO_TREE_TALL = createKey("potato_tree_tall");
    public static final ResourceKey<ConfiguredFeature<?, ?>> POTATO_TREE = createKey("potato_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOTHER_POTATO_TREE = createKey("mother_potato_tree");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        BeehiveDecorator beehive001 = new BeehiveDecorator(0.01F);
        BeehiveDecorator beehive05 = new BeehiveDecorator(0.5F);
        BeehiveDecorator beehive = new BeehiveDecorator(1.0F);
        FeatureUtils.register(
                context,
                MOTHER_POTATO_TREE,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.POTATO_STEM.get()),
                        new PotatoTrunkPlacer(32, 1, 20, UniformInt.of(1, 10), 0.4F, UniformInt.of(0, 1), blocks.getOrThrow(BlockTags.LOGS), false),
                        BlockStateProvider.simple(ModBlocks.POTATO_LEAVES.get()),
                        new AcaciaFoliagePlacer(UniformInt.of(3, 4), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(3, 0, 2)
                )
                        .decorators(
                                List.of(
                                        new AttachedListToLeavesDecorator(
                                                0.1F,
                                                false,
                                                1,
                                                0,
                                                List.of(
                                                        BlockStateProvider.simple(ModBlocks.POTATO_PEDICULE.get().defaultBlockState()),
                                                        BlockStateProvider.simple(ModBlocks.POTATO_FRUIT.get().defaultBlockState())
                                                ),
                                                3,
                                                List.of(Direction.DOWN)
                                        ),
                                        new AttachedListToLeavesDecorator(
                                                0.5F,
                                                true,
                                                1,
                                                0,
                                                List.of(
                                                        BlockStateProvider.simple(ModBlocks.POTATO_PEDICULE.get().defaultBlockState()),
                                                        BlockStateProvider.simple(ModBlocks.POTATO_FRUIT.get().defaultBlockState())
                                                ),
                                                3,
                                                List.of(Direction.DOWN)
                                        ),
                                        beehive,
                                        beehive05,
                                        beehive05,
                                        beehive05,
                                        beehive05,
                                        beehive05,
                                        beehive05
                                )
                        )
                        .ignoreVines()
                        .build()
        );
        FeatureUtils.register(
                context,
                POTATO_TREE_TALL,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.POTATO_STEM.get()),
                        new PotatoTrunkPlacer(4, 20, 20, UniformInt.of(1, 8), 0.4F, UniformInt.of(0, 1), blocks.getOrThrow(BlockTags.LOGS), false),
                        BlockStateProvider.simple(ModBlocks.POTATO_LEAVES.get()),
                        new AcaciaFoliagePlacer(UniformInt.of(2, 4), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(3, 0, 2)
                )
                        .decorators(
                                List.of(
                                        new AttachedListToLeavesDecorator(
                                                0.02F,
                                                false,
                                                1,
                                                1,
                                                List.of(
                                                        BlockStateProvider.simple(ModBlocks.POTATO_PEDICULE.get().defaultBlockState()),
                                                        BlockStateProvider.simple(ModBlocks.POTATO_FRUIT.get().defaultBlockState())
                                                ),
                                                3,
                                                List.of(Direction.DOWN)
                                        ),
                                        new AttachedListToLeavesDecorator(
                                                0.3F,
                                                true,
                                                1,
                                                0,
                                                List.of(
                                                        BlockStateProvider.simple(ModBlocks.POTATO_PEDICULE.get().defaultBlockState()),
                                                        BlockStateProvider.simple(ModBlocks.POTATO_FRUIT.get().defaultBlockState())
                                                ),
                                                3,
                                                List.of(Direction.DOWN)
                                        ),
                                        beehive05
                                )
                        )
                        .ignoreVines()
                        .build()
        );
        FeatureUtils.register(
                context,
                POTATO_TREE,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.POTATO_STEM.get()),
                        new PotatoTrunkPlacer(2, 1, 12, UniformInt.of(1, 6), 0.5F, UniformInt.of(0, 1), blocks.getOrThrow(BlockTags.LOGS), false),
                        BlockStateProvider.simple(ModBlocks.POTATO_LEAVES.get()),
                        new AcaciaFoliagePlacer(UniformInt.of(2, 3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(3, 0, 2)
                )
                        .decorators(
                                List.of(
                                        new AttachedListToLeavesDecorator(
                                                0.005F,
                                                false,
                                                1,
                                                0,
                                                List.of(
                                                        BlockStateProvider.simple(ModBlocks.POTATO_PEDICULE.get().defaultBlockState()),
                                                        BlockStateProvider.simple(ModBlocks.POTATO_FRUIT.get().defaultBlockState())
                                                ),
                                                3,
                                                List.of(Direction.DOWN)
                                        ),
                                        new AttachedListToLeavesDecorator(
                                                0.05F,
                                                true,
                                                1,
                                                0,
                                                List.of(
                                                        BlockStateProvider.simple(ModBlocks.POTATO_PEDICULE.get().defaultBlockState()),
                                                        BlockStateProvider.simple(ModBlocks.POTATO_FRUIT.get().defaultBlockState())
                                                ),
                                                3,
                                                List.of(Direction.DOWN)
                                        ),
                                        beehive001
                                )
                        )
                        .ignoreVines()
                        .build()
        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Extension.asResource(name));
    }
}
