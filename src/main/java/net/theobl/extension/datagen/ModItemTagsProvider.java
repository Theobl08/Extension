package net.theobl.extension.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.theobl.extension.Extension;
import net.theobl.extension.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, Extension.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Items.CROPS).add(ModItems.BLUE_NETHER_WART.get());
        tag(Tags.Items.BRICKS_NETHER).add(ModItems.RED_NETHER_BRICK.get(), ModItems.BLUE_NETHER_BRICK.get());
        tag(Tags.Items.BRICKS).add(ModItems.RED_NETHER_BRICK.get(), ModItems.BLUE_NETHER_BRICK.get());

        copy(BlockTags.STAIRS, ItemTags.STAIRS);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.WALLS, ItemTags.WALLS);
        copy(BlockTags.FENCES, ItemTags.FENCES);
        copy(BlockTags.SOUL_FIRE_BASE_BLOCKS, ItemTags.SOUL_FIRE_BASE_BLOCKS);
        copy(Tags.Blocks.FENCES, Tags.Items.FENCES);
        copy(Tags.Blocks.FENCES_NETHER_BRICK, Tags.Items.FENCES_NETHER_BRICK);
        copy(Tags.Blocks.GLASS_PANES, Tags.Items.GLASS_PANES);
        copy(Tags.Blocks.PUMPKINS, Tags.Items.PUMPKINS);

        copy(Tags.Blocks.SANDSTONE_BLOCKS, Tags.Items.SANDSTONE_BLOCKS);
        copy(Tags.Blocks.SANDSTONE_STAIRS, Tags.Items.SANDSTONE_STAIRS);
        copy(Tags.Blocks.SANDSTONE_SLABS, Tags.Items.SANDSTONE_SLABS);
    }
}
