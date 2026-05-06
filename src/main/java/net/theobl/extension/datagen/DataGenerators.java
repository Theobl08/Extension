package net.theobl.extension.datagen;

import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.theobl.extension.Extension;

import java.util.Collections;
import java.util.List;

@EventBusSubscriber(modid = Extension.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider((packOutput, lookupProvider) -> new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        event.createProvider(ModRecipeProvider.Runner::new);
        event.createProvider(ModRecipePrioritiesProvider::new);
        event.createProvider(ModDataMapProvider::new);

        event.createBlockAndItemTags(ModBlockTagsProvider::new, ModItemTagsProvider::new);

        event.createProvider(ModModelProvider::new);
        event.createProvider(ModLanguageProvider::new);

        event.createProvider(ModParticleDescriptionProvider::new);

        event.createProvider(ModDatapackBuiltInEntriesProvider::new);
    }
}
