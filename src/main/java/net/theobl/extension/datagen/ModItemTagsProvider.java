package net.theobl.extension.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CartographyTableBlock;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagCopyingItemTagProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.theobl.extension.Extension;
import net.theobl.extension.block.FletchingTableBlock;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.item.ModItems;
import net.theobl.extension.tags.ModBlockTags;
import net.theobl.extension.tags.ModItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends BlockTagCopyingItemTagProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, Extension.MODID);
    }

    @Override
    protected IntrinsicHolderTagAppender<Item> tag(TagKey<Item> tag) {
        return new IntrinsicHolderTagAppender<>(super.tag(tag)) {
            @Override
            public ResourceKey<Item> convertElement(Item item) {
                return item.builtInRegistryHolder().key();
            }
        };
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Items.CROPS).add(ModItems.BLUE_NETHER_WART);
        tag(Tags.Items.BRICKS_NETHER).add(ModItems.RED_NETHER_BRICK, ModItems.BLUE_NETHER_BRICK);
        tag(Tags.Items.BRICKS).add(ModItems.RED_NETHER_BRICK, ModItems.BLUE_NETHER_BRICK);

        tag(ItemTags.BOATS).add(ModItems.POTATO_RAFT);
        tag(ItemTags.CHEST_BOATS).add(ModItems.POTATO_CHEST_RAFT);

        tag(ItemTags.DECORATED_POT_SHERDS).add(ModItems.EMPTY_POTTERY_SHERD);
        copy(BlockItemTags.LANTERNS);

        copy(BlockItemTags.LEAVES);
        copy(BlockItemTags.SAPLINGS);
        copy(BlockItemTags.LOGS);
        copy(Tags.BlockItems.NATURAL_WOODS);
        copy(BlockItemTags.PLANKS);
        copy(BlockItemTags.WOODEN_STAIRS);
        copy(BlockItemTags.WOODEN_SLABS);
        copy(BlockItemTags.WOODEN_FENCES);
        copy(BlockItemTags.FENCE_GATES);
        copy(Tags.BlockItems.FENCE_GATES_WOODEN);
        copy(BlockItemTags.WOODEN_DOORS);
        copy(BlockItemTags.WOODEN_TRAPDOORS);
        copy(BlockItemTags.WOODEN_PRESSURE_PLATES);
        copy(BlockItemTags.WOODEN_BUTTONS);
        copy(BlockItemTags.SIGNS);
        copy(BlockItemTags.HANGING_SIGNS);

        copy(BlockItemTags.BAMBOO_BLOCKS);
        copy(Tags.BlockItems.BOOKSHELVES);

        copy(BlockItemTags.STAIRS);
        copy(BlockItemTags.SLABS);
        copy(BlockItemTags.WALLS);
        copy(BlockItemTags.FENCES);
        copy(BlockItemTags.SOUL_FIRE_BASE_BLOCKS);
        copy(ModBlockTags.COPPER_FIRE_BASE_BLOCKS, ModItemTags.COPPER_FIRE_BASE_BLOCKS);
        copy(ModBlockTags.ENDER_FIRE_BASE_BLOCKS, ModItemTags.ENDER_FIRE_BASE_BLOCKS);
        copy(ModBlockTags.REDSTONE_FIRE_BASE_BLOCKS, ModItemTags.REDSTONE_FIRE_BASE_BLOCKS);
        copy(BlockTags.PIGLIN_REPELLENTS, ItemTags.PIGLIN_REPELLENTS);
        copy(Tags.BlockItems.FENCES);
        copy(Tags.BlockItems.FENCES_NETHER_BRICK);
        copy(Tags.BlockItems.GLASS_PANES);
        copy(Tags.BlockItems.PUMPKINS);

        copy(Tags.BlockItems.SANDSTONE_BLOCKS);
        copy(Tags.BlockItems.SANDSTONE_STAIRS);
        copy(Tags.BlockItems.SANDSTONE_SLABS);

        tag(ItemTags.SULFUR_CUBE_ARCHETYPE_FAST_FLAT).add(ModBlocks.CERULEAN_FROGLIGHT.asItem(), ModBlocks.CARMINE_FROGLIGHT.asItem());
        copy(Tags.BlockItems.FROGLIGHTS);

        tag(Tags.Items.BUCKETS).add(ModItems.BEETROOT_SOUP_BUCKET);

        copy(Tags.BlockItems.BARRELS_WOODEN);
        copy(Tags.BlockItems.PLAYER_WORKSTATIONS_CRAFTING_TABLES);
        tag(Tags.Items.VILLAGER_JOB_SITES)
                .addAll(ModBlocks.LECTERNS.values().stream().map(DeferredBlock::asItem).toList())
                .addAll(ModBlocks.BARRELS.values().stream().map(DeferredBlock::asItem).toList());
        for (Block block : ModBlocks.BLOCKS.getEntries().stream().map(DeferredHolder::get).toList()) {
            if(block instanceof CartographyTableBlock || block instanceof FletchingTableBlock) {
                tag(Tags.Items.VILLAGER_JOB_SITES).add(block.asItem());
            }
        }
    }
}
