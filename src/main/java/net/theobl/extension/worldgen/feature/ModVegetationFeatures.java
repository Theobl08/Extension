package net.theobl.extension.worldgen.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;

public class ModVegetationFeatures {
    public static final ResourceKey<Feature> ORANGE_SHRUB = createKey("orange_shrub");
    public static final ResourceKey<Feature> YELLOW_SHRUB = createKey("yellow_shrub");

    public static void bootstrap(BootstrapContext<Feature> context) {
        context.register(ORANGE_SHRUB, new SimpleBlockFeature(BlockStateProvider.of(ModBlocks.ORANGE_SHRUB.get())));
        context.register(YELLOW_SHRUB, new SimpleBlockFeature(BlockStateProvider.of(ModBlocks.YELLOW_SHRUB.get())));
    }

    public static ResourceKey<Feature> createKey(String name) {
        return ResourceKey.create(Registries.FEATURE, Extension.asResource(name));
    }
}
