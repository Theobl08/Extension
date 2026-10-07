package net.theobl.extension.tags;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.theobl.extension.item.ModItems;

public final class ModItemTags {
    public static final TagKey<Item> COPPER_FIRE_BASE_BLOCKS = create("copper_fire_base_blocks");
    public static final TagKey<Item> REDSTONE_FIRE_BASE_BLOCKS = create("redstone_fire_base_blocks");
    public static final TagKey<Item> ENDER_FIRE_BASE_BLOCKS = create("ender_fire_base_blocks");

    private static TagKey<Item> create(String name) {
        return ModItems.ITEMS.createTagKey(name);
    }
}
