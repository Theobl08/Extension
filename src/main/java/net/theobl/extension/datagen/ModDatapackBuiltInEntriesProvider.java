package net.theobl.extension.datagen;

import net.minecraft.core.Direction;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.component.BlockTransformer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.component.BlockTransformers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.theobl.extension.block.entity.ModDecoratedPotPatterns;
import net.theobl.extension.worldgen.ModTreeFeatures;

import java.util.Collections;
import java.util.List;

public class ModDatapackBuiltInEntriesProvider {
    protected static final RegistrySetBuilder WORLD_BUILDER = new RegistrySetBuilder()
            .add(Registries.FEATURE, ModTreeFeatures::bootstrap)
            .add(Registries.DECORATED_POT_PATTERN, ModDecoratedPotPatterns::bootstrap);
    protected static final RegistrySetBuilder RELOADABLE_BUILDER = new RegistrySetBuilder()
            .add(Registries.LOOT_TABLE, new LootTableProvider(Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK))))
            .add(ModRecipeProvider.create());
}
