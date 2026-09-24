package net.theobl.extension.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.theobl.extension.Extension;
import net.theobl.extension.worldgen.placement.ModVegetationPlacements;

public class ModBiomes {
    public static final ResourceKey<Biome> ARBORETUM = ResourceKey.create(Registries.BIOME, Extension.asResource("arboretum"));

    public static void bootstrap(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<WorldCarver> carvers = context.lookup(Registries.CARVER);
        context.register(ARBORETUM, arboretum(placedFeatures, carvers));
    }

    public static Biome arboretum(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<WorldCarver> carvers) {
        //Forest & Flower Forest
        BackgroundMusic music = new BackgroundMusic(SoundEvents.MUSIC_BIOME_FOREST);
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(placedFeatures, carvers);
        OverworldBiomes.globalOverworldGeneration(generation);
        BiomeDefaultFeatures.addFerns(generation);
        BiomeDefaultFeatures.addDappledForestVegetation(generation); // Dappled Forest
        BiomeDefaultFeatures.addLightBambooVegetation(generation); // Jungle
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PALE_GARDEN_FLOWERS); // Dark Forest & Pale Garden
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_FOREST_FLOWERS);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModVegetationPlacements.TREES_ARBORETUM);

        BiomeDefaultFeatures.addBushes(generation);
        BiomeDefaultFeatures.addDefaultFlowers(generation);
        BiomeDefaultFeatures.addForestGrass(generation);
        BiomeDefaultFeatures.addTaigaGrass(generation);
        BiomeDefaultFeatures.addDefaultMushrooms(generation);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation, true);

        BiomeDefaultFeatures.addDefaultOres(generation);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);


        MobSpawnSettings.Builder mobs = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobs);
        BiomeDefaultFeatures.commonSpawns(mobs);
        mobs.addSpawn(EntityTypes.RABBIT, 4, 2, 3);
//        mobs.addSpawn(EntityTypes.WOLF, 5, 4, 4);

        //Taiga
        BiomeDefaultFeatures.addCommonBerryBushes(generation);

        //Cherry Grove
        BiomeDefaultFeatures.addExtraEmeralds(generation);

        //(Sparse) Jungle
        BiomeDefaultFeatures.addSparseJungleMelons(generation);

        return OverworldBiomes.baseBiome(0.7F, 0.8F)
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, music)
                .mobSpawnSettings(mobs.build())
                .generationSettings(generation.build())
                .build();
    }
}
