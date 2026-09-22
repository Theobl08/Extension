package net.theobl.extension.worldgen.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.theobl.extension.Extension;

public class ModFeatureUtils {
    public static void bootstrap(BootstrapContext<Feature> context) {
        ModTreeFeatures.bootstrap(context);
        ModVegetationFeatures.bootstrap(context);
    }

    public static ResourceKey<Feature> createKey(String name) {
        return ResourceKey.create(Registries.FEATURE, Extension.asResource(name));
    }
}
