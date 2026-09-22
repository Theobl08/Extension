package net.theobl.extension.worldgen.placement;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.theobl.extension.Extension;

public class ModPlacementUtils {
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        ModTreePlacements.bootstrap(context);
        ModVegetationPlacements.bootstrap(context);
    }

    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Extension.asResource(name));
    }
}
