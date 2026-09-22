package net.theobl.extension.datagen;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.theobl.extension.biome.ModBiomes;
import net.theobl.extension.block.entity.ModDecoratedPotPatterns;
import net.theobl.extension.worldgen.ModBiomeModifiers;
import net.theobl.extension.worldgen.feature.ModTreeFeatures;
import net.theobl.extension.worldgen.feature.ModVegetationFeatures;
import net.theobl.extension.worldgen.placement.ModPlacementUtils;
import net.theobl.extension.worldgen.placement.ModTreePlacements;
import net.theobl.extension.worldgen.placement.ModVegetationPlacements;

import java.util.Collections;
import java.util.List;

public class ModDatapackBuiltInEntriesProvider {
    protected static final RegistrySetBuilder WORLD_BUILDER = new RegistrySetBuilder()
            .add(Registries.FEATURE, context -> { ModTreeFeatures.bootstrap(context); ModVegetationFeatures.bootstrap(context); })
            .add(Registries.PLACED_FEATURE, ModPlacementUtils::bootstrap)
            .add(Registries.BIOME, ModBiomes::bootstrap)
            .add(Registries.DECORATED_POT_PATTERN, ModDecoratedPotPatterns::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);
    protected static final RegistrySetBuilder RELOADABLE_BUILDER = new RegistrySetBuilder()
            .add(Registries.LOOT_TABLE, new LootTableProvider(Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK))))
            .add(ModRecipeProvider.create());
}
