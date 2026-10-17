package net.theobl.extension.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.event.GatherDataRegistryEntriesEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.theobl.extension.Extension;
import net.theobl.extension.biome.ModBiomes;
import net.theobl.extension.block.entity.ModDecoratedPotPatterns;
import net.theobl.extension.worldgen.ModBiomeModifiers;
import net.theobl.extension.worldgen.feature.ModFeatureUtils;
import net.theobl.extension.worldgen.placement.ModPlacementUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = Extension.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherDataRegistryEntries(GatherDataRegistryEntriesEvent event) {
        event.gatherFor(Extension.MODID, Identifier.DEFAULT_NAMESPACE)
                .add(Registries.FEATURE, ModFeatureUtils::bootstrap)
                .add(Registries.PLACED_FEATURE, ModPlacementUtils::bootstrap)
                .add(Registries.BIOME, ModBiomes::bootstrap)
                .add(Registries.DECORATED_POT_PATTERN, ModDecoratedPotPatterns::bootstrap)
                .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
                .lootTable(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK))
                .recipe(ModRecipeProvider::new);
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(ModRecipePrioritiesProvider::new);
        event.createProvider(ModDataMapProvider::new);
        event.createProvider(ModGlobalLootModifierProvider::new);

        event.createBlockAndItemTags(ModBlockTagsProvider::new, ModItemTagsProvider::new);
        event.createProvider(ModFluidTagsProvider::new);

        event.createProvider(ModModelProvider::new);
        event.createProvider(ModLanguageProvider::new);

        event.createProvider(ModParticleDescriptionProvider::new);

        event.createProvider(ModBiomeTagsProvider::new);
    }
}
