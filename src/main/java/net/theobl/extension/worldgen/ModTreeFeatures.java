package net.theobl.extension.worldgen;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BlockStateProviders;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;

import java.util.List;

public class ModTreeFeatures {
    public static final ResourceKey<Feature> POTATO_TREE_TALL = createKey("potato_tree_tall");
    public static final ResourceKey<Feature> POTATO_TREE = createKey("potato_tree");
    public static final ResourceKey<Feature> MOTHER_POTATO_TREE = createKey("mother_potato_tree");

    public static void bootstrap(BootstrapContext<Feature> context) {
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        HolderGetter<BlockStateProvider> blockStateProviders = context.lookup(Registries.BLOCK_STATE_PROVIDER);
        Holder<BlockStateProvider> belowTrunkProvider = blockStateProviders.getOrThrow(BlockStateProviders.SOIL_BENEATH_TREE);
        BeehiveDecorator beehive001 = new BeehiveDecorator(0.01F);
        BeehiveDecorator beehive05 = new BeehiveDecorator(0.5F);
        BeehiveDecorator beehive = new BeehiveDecorator(1.0F);
        context.register(
                MOTHER_POTATO_TREE,
                new TreeFeature.Builder(
                        BlockStateProvider.of(ModBlocks.POTATO_STEM.get()),
                        new PotatoTrunkPlacer(32, 1, 20, UniformInt.of(1, 10), 0.4F, UniformInt.of(0, 1), blocks.getOrThrow(BlockTags.LOGS), false),
                        BlockStateProvider.of(ModBlocks.POTATO_LEAVES.get()),
                        new AcaciaFoliagePlacer(UniformInt.of(3, 4), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(3, 0, 2),
                        belowTrunkProvider
                )
                        .decorators(
                                List.of(
                                        new AttachedListToLeavesDecorator(
                                                0.1F,
                                                false,
                                                1,
                                                0,
                                                List.of(
                                                        BlockStateProvider.holderOf(ModBlocks.POTATO_PEDICULE.get().defaultBlockState()),
                                                        BlockStateProvider.holderOf(ModBlocks.POTATO_FRUIT.get().defaultBlockState())
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
                                                        BlockStateProvider.holderOf(ModBlocks.POTATO_PEDICULE.get().defaultBlockState()),
                                                        BlockStateProvider.holderOf(ModBlocks.POTATO_FRUIT.get().defaultBlockState())
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
        context.register(
                POTATO_TREE_TALL,
                new TreeFeature.Builder(
                        BlockStateProvider.of(ModBlocks.POTATO_STEM.get()),
                        new PotatoTrunkPlacer(4, 20, 20, UniformInt.of(1, 8), 0.4F, UniformInt.of(0, 1), blocks.getOrThrow(BlockTags.LOGS), false),
                        BlockStateProvider.of(ModBlocks.POTATO_LEAVES.get()),
                        new AcaciaFoliagePlacer(UniformInt.of(2, 4), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(3, 0, 2),
                        belowTrunkProvider
                )
                        .decorators(
                                List.of(
                                        new AttachedListToLeavesDecorator(
                                                0.02F,
                                                false,
                                                1,
                                                1,
                                                List.of(
                                                        BlockStateProvider.holderOf(ModBlocks.POTATO_PEDICULE.get().defaultBlockState()),
                                                        BlockStateProvider.holderOf(ModBlocks.POTATO_FRUIT.get().defaultBlockState())
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
                                                        BlockStateProvider.holderOf(ModBlocks.POTATO_PEDICULE.get().defaultBlockState()),
                                                        BlockStateProvider.holderOf(ModBlocks.POTATO_FRUIT.get().defaultBlockState())
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
        context.register(
                POTATO_TREE,
                new TreeFeature.Builder(
                        BlockStateProvider.of(ModBlocks.POTATO_STEM.get()),
                        new PotatoTrunkPlacer(2, 1, 12, UniformInt.of(1, 6), 0.5F, UniformInt.of(0, 1), blocks.getOrThrow(BlockTags.LOGS), false),
                        BlockStateProvider.of(ModBlocks.POTATO_LEAVES.get()),
                        new AcaciaFoliagePlacer(UniformInt.of(2, 3), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(3, 0, 2),
                        belowTrunkProvider
                )
                        .decorators(
                                List.of(
                                        new AttachedListToLeavesDecorator(
                                                0.005F,
                                                false,
                                                1,
                                                0,
                                                List.of(
                                                        BlockStateProvider.holderOf(ModBlocks.POTATO_PEDICULE.get().defaultBlockState()),
                                                        BlockStateProvider.holderOf(ModBlocks.POTATO_FRUIT.get().defaultBlockState())
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
                                                        BlockStateProvider.holderOf(ModBlocks.POTATO_PEDICULE.get().defaultBlockState()),
                                                        BlockStateProvider.holderOf(ModBlocks.POTATO_FRUIT.get().defaultBlockState())
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

    public static ResourceKey<Feature> createKey(String name) {
        return ResourceKey.create(Registries.FEATURE, Extension.asResource(name));
    }
}
