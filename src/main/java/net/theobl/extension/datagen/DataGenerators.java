package net.theobl.extension.datagen;

import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.theobl.extension.Extension;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = Extension.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(ModRecipePrioritiesProvider::new);
        event.createProvider(ModDataMapProvider::new);
        event.createProvider(ModGlobalLootModifierProvider::new);

        event.createBlockAndItemTags(ModBlockTagsProvider::new, ModItemTagsProvider::new);

        event.createProvider(ModModelProvider::new);
        event.createProvider(ModLanguageProvider::new);

        event.createProvider(ModParticleDescriptionProvider::new);

        event.createWorldRegistryObjects(ModDatapackBuiltInEntriesProvider.WORLD_BUILDER, Set.of(Extension.MODID, Identifier.DEFAULT_NAMESPACE));
        event.createReloadableRegistryObjects(ModDatapackBuiltInEntriesProvider.RELOADABLE_BUILDER, Set.of(Extension.MODID, Identifier.DEFAULT_NAMESPACE));
        event.createProvider(ModBiomeTagsProvider::new);
    }
}
