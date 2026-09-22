package net.theobl.extension.worldgen.placement;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.material.Fluids;
import net.theobl.extension.Extension;
import net.theobl.extension.worldgen.feature.ModVegetationFeatures;

public class ModVegetationPlacements {
    public static final ResourceKey<PlacedFeature> PATCH_ORANGE_SHRUB = ModPlacementUtils.createKey("patch_orange_shrub");
    public static final ResourceKey<PlacedFeature> PATCH_YELLOW_SHRUB = ModPlacementUtils.createKey("patch_yellow_shrub");
    public static final ResourceKey<PlacedFeature> TREES_ARBORETUM = ModPlacementUtils.createKey("trees_arboretum");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<Feature> features = context.lookup(Registries.FEATURE);
        Holder<Feature> orangeShrub = features.getOrThrow(ModVegetationFeatures.ORANGE_SHRUB);
        Holder<Feature> yellowShrub = features.getOrThrow(ModVegetationFeatures.YELLOW_SHRUB);
        PlacementUtils.register(
                context,
                PATCH_ORANGE_SHRUB,
                orangeShrub,
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome(),
                CountPlacement.of(8),
                OffsetPlacement.ofTriangle(7, 3),
                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
        );
        PlacementUtils.register(
                context,
                PATCH_YELLOW_SHRUB,
                yellowShrub,
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome(),
                CountPlacement.of(8),
                OffsetPlacement.ofTriangle(7, 3),
                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
        );
        PlacementUtils.register(
                context,
                TREES_ARBORETUM,
                features.getOrThrow(ModVegetationFeatures.TREES_ARBORETUM),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
    }
}
