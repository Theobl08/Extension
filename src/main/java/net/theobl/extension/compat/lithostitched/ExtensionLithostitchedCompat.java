package net.theobl.extension.compat.lithostitched;

import com.mojang.datafixers.util.Pair;
import dev.worldgen.lithostitched.api.event.AddBiomeInjectorsEvent;
import dev.worldgen.lithostitched.api.event.AddRegionsEvent;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.ParameterBuilder;
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.region.Region;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
import net.theobl.extension.Extension;
import net.theobl.extension.biome.ExtensionOverworldBiomeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod(value = Extension.MODID, depends = "lithostitched")
public class ExtensionLithostitchedCompat {
    public ExtensionLithostitchedCompat() {
        AddRegionsEvent.EVENT.register(((registries, consumer) -> {
            consumer.accept(
                    ModRegions.ARBORETUM,
                    Level.OVERWORLD,
                    registries.lookupOrThrow(Registries.BIOME).getOrThrow(Tags.Biomes.IS_OVERWORLD),
                    100
            );
        }));
        AddBiomeInjectorsEvent.EVENT.register(((registries, consumer) -> {
            addBiomeInjector(registries, consumer, Extension.asResource("add_arboretum"), new ExtensionOverworldBiomeBuilder()::addBiomes, ModRegions.ARBORETUM);
        }));
    }

    private static void addBiomeInjector(RegistryAccess registries, BiConsumer<Identifier, BiomeInjector> consumer, Identifier id,
                                         BiomeParameterProvider parameterProvider, ResourceKey<Region> region) {
        List<Pair<Climate.ParameterPoint, Holder<Biome>>> regionPoints = new ArrayList<>();
        parameterProvider.addBiomes(point -> {
            Holder<Biome> holder = registries.lookupOrThrow(Registries.BIOME).getOrThrow(point.getSecond());
            regionPoints.add(Pair.of(point.getFirst(), holder));
        });

        Climate.ParameterList<Holder<Biome>> regionLayout = new Climate.ParameterList<>(regionPoints);
        consumer.accept(
                id,
                BiomeInjector.builder(Level.OVERWORLD)
                        .dispatchAlternateLayout(
                                ParameterBuilder.create().region(region),
                                regionLayout)
        );
    }

    @FunctionalInterface
    private interface BiomeParameterProvider {
        void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters);
    }
}
