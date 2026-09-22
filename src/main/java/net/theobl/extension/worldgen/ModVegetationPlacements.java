package net.theobl.extension.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.*;
import net.theobl.extension.Extension;

public class ModVegetationPlacements {
    public static final ResourceKey<PlacedFeature> PATCH_ORANGE_SHRUB = createKey("patch_orange_shrub");
    public static final ResourceKey<PlacedFeature> PATCH_YELLOW_SHRUB = createKey("patch_yellow_shrub");

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
    }

    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Extension.asResource(name));
    }
}
