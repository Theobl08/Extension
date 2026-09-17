package net.theobl.extension.loot;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.theobl.extension.Extension;

import java.util.HashSet;
import java.util.Set;

public class ModLootTables {
    private static final Set<ResourceKey<LootTable>> LOCATIONS = new HashSet<>();
    public static final ResourceKey<LootTable> DROP_RED_POPLAR_SAPLING = register("add_tables/drop_red_poplar_sapling");
    public static final ResourceKey<LootTable> DROP_ORANGE_POPLAR_SAPLING = register("add_tables/drop_orange_poplar_sapling");
    public static final ResourceKey<LootTable> DROP_YELLOW_POPLAR_SAPLING = register("add_tables/drop_yellow_poplar_sapling");

    private static ResourceKey<LootTable> register(String location) {
        return register(ResourceKey.create(Registries.LOOT_TABLE, Extension.asResource(location)));
    }

    private static ResourceKey<LootTable> register(ResourceKey<LootTable> location) {
        if (LOCATIONS.add(location)) {
            return location;
        } else {
            throw new IllegalArgumentException(location.identifier() + " is already a registered built-in loot table");
        }
    }
}
