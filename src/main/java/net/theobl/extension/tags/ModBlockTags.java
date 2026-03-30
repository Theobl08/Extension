package net.theobl.extension.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.theobl.extension.Extension;

public class ModBlockTags {
    public static final TagKey<Block> COPPER_FIRE_BASE_BLOCKS = create("copper_fire_base_blocks");
    public static final TagKey<Block> REDSTONE_FIRE_BASE_BLOCKS = create("redstone_fire_base_blocks");
    public static final TagKey<Block> ENDER_FIRE_BASE_BLOCKS = create("ender_fire_base_blocks");

    private static TagKey<Block> create(String name) {
        return BlockTags.create(Extension.asResource(name));
    }
}
