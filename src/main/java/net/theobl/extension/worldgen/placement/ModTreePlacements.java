package net.theobl.extension.worldgen.placement;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.worldgen.feature.ModTreeFeatures;

public class ModTreePlacements {
    public static final ResourceKey<PlacedFeature> POTATO_CHECKED = ModPlacementUtils.createKey("potato_checked");

    protected static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<Feature> configuredFeature = context.lookup(Registries.FEATURE);
        PlacementUtils.register(
                context,
                POTATO_CHECKED,
                configuredFeature.getOrThrow(ModTreeFeatures.POTATO_TREE),
                PlacementUtils.filteredByBlockSurvival(ModBlocks.POTATO_SPROUTS.get())
        );
    }
}
