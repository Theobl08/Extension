package net.theobl.extension.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.theobl.extension.Extension;
import net.theobl.extension.worldgen.placement.ModVegetationPlacements;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_ORANGE_SHRUB = createKey("add_orange_shrub");
    public static final ResourceKey<BiomeModifier> ADD_YELLOW_SHRUB = createKey("add_yellow_shrub");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var biomes = context.lookup(Registries.BIOME);
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        context.register(
                ADD_ORANGE_SHRUB,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(biomes.getOrThrow(Biomes.DAPPLED_FOREST)),
                        HolderSet.direct(placedFeatures.getOrThrow(ModVegetationPlacements.PATCH_ORANGE_SHRUB)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );
        context.register(
                ADD_YELLOW_SHRUB,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(biomes.getOrThrow(Biomes.DAPPLED_FOREST)),
                        HolderSet.direct(placedFeatures.getOrThrow(ModVegetationPlacements.PATCH_YELLOW_SHRUB)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );
    }

    public static ResourceKey<BiomeModifier> createKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, Extension.asResource(name));
    }
}
