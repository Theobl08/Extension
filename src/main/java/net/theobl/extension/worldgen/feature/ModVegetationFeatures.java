package net.theobl.extension.worldgen.feature;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.worldgen.placement.ModTreePlacements;

import java.util.List;

public class ModVegetationFeatures {
    public static final ResourceKey<Feature> ORANGE_SHRUB = ModFeatureUtils.createKey("orange_shrub");
    public static final ResourceKey<Feature> YELLOW_SHRUB = ModFeatureUtils.createKey("yellow_shrub");
    public static final ResourceKey<Feature> TREES_ARBORETUM = ModFeatureUtils.createKey("trees_arboretum");

    @SuppressWarnings("deprecation")
    protected static void bootstrap(BootstrapContext<Feature> context) {
        HolderGetter<Feature> features = context.lookup(Registries.FEATURE);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        context.register(ORANGE_SHRUB, new SimpleBlockFeature(BlockStateProvider.of(ModBlocks.ORANGE_SHRUB.get())));
        context.register(YELLOW_SHRUB, new SimpleBlockFeature(BlockStateProvider.of(ModBlocks.YELLOW_SHRUB.get())));
        context.register(
                TREES_ARBORETUM,
                new RandomSelectorFeature(
                        List.of(
                                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.OAK_CHECKED), 0.05F),
                                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.BIRCH_CHECKED), 0.05F),
                                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.FANCY_OAK_CHECKED), 0.05F),
                                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.SPRUCE_CHECKED), 0.05F),
                                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.PINE_CHECKED), 0.05F),
                                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.ACACIA_CHECKED), 0.05F),
                                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.CHERRY_CHECKED), 0.02F),
                                new WeightedPlacedFeature(placedFeatures.getOrThrow(ModTreePlacements.POTATO_CHECKED), 0.02F),
                                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.JUNGLE_BUSH), 0.02F),
                                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.JUNGLE_TREE_CHECKED), 0.05F),
                                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.MANGROVE_CHECKED), 0.05F), // 0.02F in 24w14potato
                                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(TreeFeatures.HUGE_BROWN_MUSHROOM)), 0.025F),
                                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(TreeFeatures.HUGE_RED_MUSHROOM)), 0.025F),
                                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.DARK_OAK_CHECKED), 0.05F), // 0.01F in 24w14potato
                                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.PALE_OAK_CHECKED), 0.05F),
                                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.RED_POPLAR), 0.02F),
                                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.ORANGE_POPLAR), 0.02F),
                                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.YELLOW_POPLAR), 0.01F)
                        ),
                        placedFeatures.getOrThrow(TreePlacements.OAK_CHECKED)
                )
        );
    }
}
