package net.theobl.extension.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.tags.ModBlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Extension.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Blocks.FENCES_NETHER_BRICK)
                .add(ModBlocks.RED_NETHER_BRICK_FENCE.get())
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE.get());

        tag(Tags.Blocks.GLASS_PANES).add(ModBlocks.TINTED_GLASS_PANE.get());
        tag(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.BLUE_NETHER_WART.get())
                .add(ModBlocks.SOUL_O_LANTERN.get())
                .add(ModBlocks.REDSTONE_O_LANTERN.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.BLUE_NETHER_WART.get())
                .add(ModBlocks.SOUL_O_LANTERN.get())
                .add(ModBlocks.COPPER_O_LANTERN.get())
                .add(ModBlocks.COPPER_CAMPFIRE.get())
                .add(ModBlocks.REDSTONE_O_LANTERN.get())
                .add(ModBlocks.REDSTONE_CAMPFIRE.get());

        tag(BlockTags.CAMPFIRES).add(ModBlocks.COPPER_CAMPFIRE.get(), ModBlocks.REDSTONE_CAMPFIRE.get());
        tag(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(ModBlocks.SOUL_O_LANTERN.get());
        tag(Tags.Blocks.PUMPKINS)
                .add(ModBlocks.SOUL_O_LANTERN.get())
                .add(ModBlocks.COPPER_O_LANTERN.get())
                .add(ModBlocks.REDSTONE_O_LANTERN.get());

        tag(ModBlockTags.COPPER_FIRE_BASE_BLOCKS)
                .addTag(BlockTags.COPPER)
                .remove(Blocks.OXIDIZED_COPPER)
                .add(Blocks.CHISELED_COPPER)
                .add(Blocks.EXPOSED_CHISELED_COPPER)
                .add(Blocks.WEATHERED_CHISELED_COPPER)
                .add(Blocks.WAXED_CHISELED_COPPER)
                .add(Blocks.WAXED_EXPOSED_CHISELED_COPPER)
                .add(Blocks.WAXED_WEATHERED_CHISELED_COPPER)
                .add(Blocks.WAXED_OXIDIZED_CHISELED_COPPER)
                .add(Blocks.COPPER_GRATE)
                .add(Blocks.EXPOSED_COPPER_GRATE)
                .add(Blocks.WEATHERED_COPPER_GRATE)
                .add(Blocks.WAXED_COPPER_GRATE)
                .add(Blocks.WAXED_EXPOSED_COPPER_GRATE)
                .add(Blocks.WAXED_WEATHERED_COPPER_GRATE)
                .add(Blocks.WAXED_OXIDIZED_COPPER_GRATE)
                .add(Blocks.CUT_COPPER)
                .add(Blocks.EXPOSED_CUT_COPPER)
                .add(Blocks.WEATHERED_CUT_COPPER)
                .add(Blocks.WAXED_CUT_COPPER)
                .add(Blocks.WAXED_EXPOSED_CUT_COPPER)
                .add(Blocks.WAXED_WEATHERED_CUT_COPPER)
                .add(Blocks.WAXED_OXIDIZED_CUT_COPPER)
                .add(Blocks.CUT_COPPER_STAIRS)
                .add(Blocks.EXPOSED_CUT_COPPER_STAIRS)
                .add(Blocks.WEATHERED_CUT_COPPER_STAIRS)
                .add(Blocks.WAXED_CUT_COPPER_STAIRS)
                .add(Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS)
                .add(Blocks.WAXED_WEATHERED_CUT_COPPER_STAIRS)
                .add(Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS)
                .add(Blocks.CUT_COPPER_SLAB)
                .add(Blocks.EXPOSED_CUT_COPPER_SLAB)
                .add(Blocks.WEATHERED_CUT_COPPER_SLAB)
                .add(Blocks.WAXED_CUT_COPPER_SLAB)
                .add(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB)
                .add(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB)
                .add(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB)
                .add(Blocks.COPPER_TRAPDOOR)
                .add(Blocks.EXPOSED_COPPER_TRAPDOOR)
                .add(Blocks.WEATHERED_COPPER_TRAPDOOR)
                .add(Blocks.WAXED_COPPER_TRAPDOOR)
                .add(Blocks.WAXED_EXPOSED_COPPER_TRAPDOOR)
                .add(Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR)
                .add(Blocks.WAXED_OXIDIZED_COPPER_TRAPDOOR)
                .add(Blocks.COPPER_BULB)
                .add(Blocks.EXPOSED_COPPER_BULB)
                .add(Blocks.WEATHERED_COPPER_BULB)
                .add(Blocks.WAXED_COPPER_BULB)
                .add(Blocks.WAXED_EXPOSED_COPPER_BULB)
                .add(Blocks.WAXED_WEATHERED_COPPER_BULB)
                .add(Blocks.WAXED_OXIDIZED_COPPER_BULB)
                .add(Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE)
                .add(ModBlocks.COPPER_O_LANTERN.get());

        for(DeferredHolder<Block, ? extends Block> deferredBlock : ModBlocks.BLOCKS.getEntries()){
            if(deferredBlock.get() instanceof StairBlock)
                tag(BlockTags.STAIRS).add(deferredBlock.get());

            if(deferredBlock.get() instanceof SlabBlock)
                tag(BlockTags.SLABS).add(deferredBlock.get());

            if(deferredBlock.get() instanceof WallBlock)
                tag(BlockTags.WALLS).add(deferredBlock.get());

            if(deferredBlock.get() instanceof FenceBlock) {
                tag(BlockTags.FENCES).add(deferredBlock.get());
                tag(Tags.Blocks.FENCES).add(deferredBlock.get());
            }

            if(deferredBlock.get() instanceof AbstractCauldronBlock) {
                tag(BlockTags.CAULDRONS).add(deferredBlock.get());
                tag(Tags.Blocks.VILLAGER_JOB_SITES).add(deferredBlock.get());
            }

            if(deferredBlock.get().toString().contains("soul_sandstone")) {
                tag(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(deferredBlock.get());
                if(deferredBlock.get() instanceof StairBlock)
                    tag(Tags.Blocks.SANDSTONE_STAIRS).add(deferredBlock.get());
                else if(deferredBlock.get() instanceof SlabBlock)
                    tag(Tags.Blocks.SANDSTONE_SLABS).add(deferredBlock.get());
                else if(!(deferredBlock.get() instanceof WallBlock))
                    tag(Tags.Blocks.SANDSTONE_BLOCKS).add(deferredBlock.get());
            }
            
            if(mineableWithPickaxe(deferredBlock.get()))
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(deferredBlock.get());
        }
    }

    private boolean mineableWithPickaxe(Block block) {
        return !block.toString().contains("wall") && // "#minecraft:wall" is already in the tag
                (block.toString().contains("soul_sandstone") ||
                        block.toString().contains("brick") ||
                        block.toString().contains("smooth") ||
                        block.toString().contains("polished") ||
                        block.defaultBlockState().is(ModBlocks.REDSTONE_LANTERN.get()) ||
                        block.defaultBlockState().is(ModBlocks.NETHERITE_STAIRS.get()));
    }
}
