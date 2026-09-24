package net.theobl.extension.compat.lithostitched;

import dev.worldgen.lithostitched.api.event.AddBiomeInjectorsEvent;
import dev.worldgen.lithostitched.api.event.AddRegionsEvent;
import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.ParameterBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
import net.theobl.extension.Extension;
import net.theobl.extension.biome.ModBiomes;

@Mod(value = Extension.MODID, depends = "lithostitched")
public class ExtensionLithostitchedCompat {
    public ExtensionLithostitchedCompat() {
        AddRegionsEvent.EVENT.register(((registries, consumer) -> {
            consumer.accept(
                    ModRegions.ARBORETUM,
                    Level.OVERWORLD,
                    HolderSet.direct(registries.getOrThrow(ModBiomes.ARBORETUM)),
                    100
            );
        }));
        AddBiomeInjectorsEvent.EVENT.register(((registries, consumer) -> {
            consumer.accept(
                    Extension.asResource("add_arboretum"),
                    BiomeInjector.builder(Level.OVERWORLD)
                            .forcePlacement(
                                    registries.getOrThrow(ModBiomes.ARBORETUM),
                                    ParameterBuilder.create()
//                                            .region(ModRegions.ARBORETUM)
                                            .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, 0.1F, 0.2F)
                                            .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, -0.1F, 0.1F)
                                            .climateRange(BiomeInjector.ClimateParameter.EROSION, -0.375F, 0.45F)
                                            .climateMin(BiomeInjector.ClimateParameter.WEIRDNESS, 0.0F))
            );
        }));
    }
}
