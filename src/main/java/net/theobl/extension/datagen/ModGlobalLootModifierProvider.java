package net.theobl.extension.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import net.theobl.extension.Extension;
import net.theobl.extension.item.ModItems;
import net.theobl.extension.loot.AddArchaeologyItemLootModifier;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Extension.MODID);
    }

    @Override
    protected void start() {
        this.add(
                // The name of the modifier. This will be the file name.
                "add_empty_pottery_sherd",
                // The loot modifier to add.
                new AddArchaeologyItemLootModifier(
                        new LootItemCondition[] {
                                LootTableIdCondition.builder(BuiltInLootTables.DESERT_WELL_ARCHAEOLOGY.identifier()).build()
                        },
                        IGlobalLootModifier.DEFAULT_PRIORITY,
                        ModItems.EMPTY_POTTERY_SHERD.asItem(),
                        2
                )
        );
    }
}
