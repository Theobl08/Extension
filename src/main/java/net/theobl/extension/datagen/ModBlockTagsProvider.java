package net.theobl.extension.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockItemTags;
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

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Blocks.FENCES_NETHER_BRICK)
                .add(ModBlocks.RED_NETHER_BRICK_FENCE.getKey())
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE.getKey());

        tag(Tags.Blocks.GLASS_PANES).add(ModBlocks.TINTED_GLASS_PANE.getKey());
        tag(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.BLUE_NETHER_WART.getKey())
                .add(ModBlocks.SOUL_O_LANTERN.getKey())
                .add(ModBlocks.REDSTONE_O_LANTERN.getKey())
                .add(ModBlocks.COPPER_O_LANTERN.getKey())
                .add(ModBlocks.ENDER_O_LANTERN.getKey());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.BLUE_NETHER_WART.getKey())
                .add(ModBlocks.SOUL_O_LANTERN.getKey())
                .add(ModBlocks.COPPER_O_LANTERN.getKey())
                .add(ModBlocks.COPPER_CAMPFIRE.getKey())
                .add(ModBlocks.REDSTONE_O_LANTERN.getKey())
                .add(ModBlocks.REDSTONE_CAMPFIRE.getKey())
                .add(ModBlocks.ENDER_O_LANTERN.getKey())
                .add(ModBlocks.ENDER_CAMPFIRE.getKey());

        tag(BlockTags.WALL_POST_OVERRIDE).add(ModBlocks.ENDER_TORCH.getKey());
        tag(BlockTags.CAMPFIRES).add(ModBlocks.COPPER_CAMPFIRE.getKey(), ModBlocks.REDSTONE_CAMPFIRE.getKey(), ModBlocks.ENDER_CAMPFIRE.getKey());
        tag(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(ModBlocks.SOUL_O_LANTERN.getKey());
        tag(Tags.Blocks.PUMPKINS)
                .add(ModBlocks.SOUL_O_LANTERN.getKey())
                .add(ModBlocks.COPPER_O_LANTERN.getKey())
                .add(ModBlocks.REDSTONE_O_LANTERN.getKey())
                .add(ModBlocks.ENDER_O_LANTERN.getKey());

        tag(BlockTags.PIGLIN_REPELLENTS).add(ModBlocks.SOUL_O_LANTERN.getKey());

        tag(BlockTags.FIRE).add(ModBlocks.COPPER_FIRE.getKey(), ModBlocks.REDSTONE_FIRE.getKey(), ModBlocks.ENDER_FIRE.getKey());
        tag(BlockTags.REPLACEABLE).add(ModBlocks.COPPER_FIRE.getKey(), ModBlocks.REDSTONE_FIRE.getKey(), ModBlocks.ENDER_FIRE.getKey());

        tag(ModBlockTags.COPPER_FIRE_BASE_BLOCKS)
                .addTag(BlockTags.COPPER)
                .remove(BlockItemIds.COPPER_BLOCK.weathering().oxidized().block())
                .add(BlockItemIds.CHISELED_COPPER.weathering().unaffected().block())
                .add(BlockItemIds.CHISELED_COPPER.weathering().exposed().block())
                .add(BlockItemIds.CHISELED_COPPER.weathering().weathered().block())
                .add(BlockItemIds.CHISELED_COPPER.waxed().unaffected().block())
                .add(BlockItemIds.CHISELED_COPPER.waxed().exposed().block())
                .add(BlockItemIds.CHISELED_COPPER.waxed().weathered().block())
                .add(BlockItemIds.CHISELED_COPPER.waxed().oxidized().block())
                .add(BlockItemIds.COPPER_GRATE.weathering().unaffected().block())
                .add(BlockItemIds.COPPER_GRATE.weathering().exposed().block())
                .add(BlockItemIds.COPPER_GRATE.weathering().weathered().block())
                .add(BlockItemIds.COPPER_GRATE.waxed().unaffected().block())
                .add(BlockItemIds.COPPER_GRATE.waxed().exposed().block())
                .add(BlockItemIds.COPPER_GRATE.waxed().weathered().block())
                .add(BlockItemIds.COPPER_GRATE.waxed().oxidized().block())
                .add(BlockItemIds.CUT_COPPER.weathering().unaffected().block())
                .add(BlockItemIds.CUT_COPPER.weathering().exposed().block())
                .add(BlockItemIds.CUT_COPPER.weathering().weathered().block())
                .add(BlockItemIds.CUT_COPPER.waxed().unaffected().block())
                .add(BlockItemIds.CUT_COPPER.waxed().exposed().block())
                .add(BlockItemIds.CUT_COPPER.waxed().weathered().block())
                .add(BlockItemIds.CUT_COPPER.waxed().oxidized().block())
                .add(BlockItemIds.CUT_COPPER_STAIRS.weathering().unaffected().block())
                .add(BlockItemIds.CUT_COPPER_STAIRS.weathering().exposed().block())
                .add(BlockItemIds.CUT_COPPER_STAIRS.weathering().weathered().block())
                .add(BlockItemIds.CUT_COPPER_STAIRS.waxed().unaffected().block())
                .add(BlockItemIds.CUT_COPPER_STAIRS.waxed().exposed().block())
                .add(BlockItemIds.CUT_COPPER_STAIRS.waxed().weathered().block())
                .add(BlockItemIds.CUT_COPPER_STAIRS.waxed().oxidized().block())
                .add(BlockItemIds.CUT_COPPER_SLAB.weathering().unaffected().block())
                .add(BlockItemIds.CUT_COPPER_SLAB.weathering().exposed().block())
                .add(BlockItemIds.CUT_COPPER_SLAB.weathering().weathered().block())
                .add(BlockItemIds.CUT_COPPER_SLAB.waxed().unaffected().block())
                .add(BlockItemIds.CUT_COPPER_SLAB.waxed().exposed().block())
                .add(BlockItemIds.CUT_COPPER_SLAB.waxed().weathered().block())
                .add(BlockItemIds.CUT_COPPER_SLAB.waxed().oxidized().block())
                .add(BlockItemIds.COPPER_TRAPDOOR.weathering().unaffected().block())
                .add(BlockItemIds.COPPER_TRAPDOOR.weathering().exposed().block())
                .add(BlockItemIds.COPPER_TRAPDOOR.weathering().weathered().block())
                .add(BlockItemIds.COPPER_TRAPDOOR.waxed().unaffected().block())
                .add(BlockItemIds.COPPER_TRAPDOOR.waxed().exposed().block())
                .add(BlockItemIds.COPPER_TRAPDOOR.waxed().weathered().block())
                .add(BlockItemIds.COPPER_TRAPDOOR.waxed().oxidized().block())
                .add(BlockItemIds.COPPER_BULB.weathering().unaffected().block())
                .add(BlockItemIds.COPPER_BULB.weathering().exposed().block())
                .add(BlockItemIds.COPPER_BULB.weathering().weathered().block())
                .add(BlockItemIds.COPPER_BULB.waxed().unaffected().block())
                .add(BlockItemIds.COPPER_BULB.waxed().exposed().block())
                .add(BlockItemIds.COPPER_BULB.waxed().weathered().block())
                .add(BlockItemIds.COPPER_BULB.waxed().oxidized().block())
                .add(BlockItemIds.COPPER_ORE.block(), BlockItemIds.DEEPSLATE_COPPER_ORE.block())
                .add(ModBlocks.COPPER_O_LANTERN.getKey());

        tag(ModBlockTags.REDSTONE_FIRE_BASE_BLOCKS)
                .addTag(BlockItemTags.REDSTONE_ORES.block())
                .add(BlockItemIds.REDSTONE_BLOCK.block())
                .add(ModBlocks.REDSTONE_O_LANTERN.getKey());

        tag(ModBlockTags.ENDER_FIRE_BASE_BLOCKS)
                .addTag(Tags.Blocks.END_STONES)
                .add(BlockItemIds.END_STONE_BRICKS.block())
                .add(BlockItemIds.END_STONE_BRICK_STAIRS.block())
                .add(BlockItemIds.END_STONE_BRICK_SLAB.block())
                .add(BlockItemIds.END_STONE_BRICK_WALL.block())
                .add(BlockItemIds.PURPUR_BLOCK.block())
                .add(BlockItemIds.PURPUR_STAIRS.block())
                .add(BlockItemIds.PURPUR_SLAB.block())
                .add(BlockItemIds.PURPUR_PILLAR.block())
                .add(ModBlocks.CHISELED_END_STONE_BRICKS.getKey())
                .add(ModBlocks.POLISHED_END_STONE.getKey())
                .add(ModBlocks.POLISHED_END_STONE_STAIRS.getKey())
                .add(ModBlocks.POLISHED_END_STONE_SLAB.getKey())
                .add(ModBlocks.POLISHED_END_STONE_WALL.getKey())
                .add(ModBlocks.PURPUR_WALL.getKey())
                .add(ModBlocks.ENDER_O_LANTERN.getKey());

        tag(BlockTags.SWORD_EFFICIENT).add(ModBlocks.POTATO_FRUIT.getKey());
        tag(BlockTags.LOGS).add(ModBlocks.POTATO_STEM.getKey(), ModBlocks.POTATO_HYPHAE.getKey());
        tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(ModBlocks.POTATO_STEM.getKey());
        tag(Tags.Blocks.NATURAL_WOODS).add(ModBlocks.POTATO_HYPHAE.getKey());
        tag(BlockTags.PLANKS).add(ModBlocks.POTATO_PLANKS.getKey());
        tag(BlockTags.WOODEN_STAIRS).add(ModBlocks.POTATO_STAIRS.getKey());
        tag(BlockTags.WOODEN_SLABS).add(ModBlocks.POTATO_SLAB.getKey());
        tag(BlockTags.WOODEN_FENCES).add(ModBlocks.POTATO_FENCE.getKey());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.POTATO_FENCE_GATE.getKey());
        tag(Tags.Blocks.FENCE_GATES_WOODEN).add(ModBlocks.POTATO_FENCE_GATE.getKey());
        tag(BlockTags.WOODEN_DOORS).add(ModBlocks.POTATO_DOOR.getKey());
        tag(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.POTATO_TRAPDOOR.getKey());
        tag(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.POTATO_PRESSURE_PLATE.getKey());
        tag(BlockTags.WOODEN_BUTTONS).add(ModBlocks.POTATO_BUTTON.getKey());
        tag(BlockTags.STANDING_SIGNS).add(ModBlocks.POTATO_SIGN.getKey());
        tag(BlockTags.WALL_SIGNS).add(ModBlocks.POTATO_WALL_SIGN.getKey());
        tag(BlockTags.CEILING_HANGING_SIGNS).add(ModBlocks.POTATO_HANGING_SIGN.getKey());
        tag(BlockTags.WALL_HANGING_SIGNS).add(ModBlocks.POTATO_WALL_HANGING_SIGN.getKey());

        for(DeferredHolder<Block, ? extends Block> deferredBlock : ModBlocks.BLOCKS.getEntries()){
            if(deferredBlock.get() instanceof StairBlock)
                tag(BlockTags.STAIRS).add(deferredBlock.getKey());

            if(deferredBlock.get() instanceof SlabBlock)
                tag(BlockTags.SLABS).add(deferredBlock.getKey());

            if(deferredBlock.get() instanceof WallBlock)
                tag(BlockTags.WALLS).add(deferredBlock.getKey());

            if(deferredBlock.get() instanceof FenceBlock) {
                tag(BlockTags.FENCES).add(deferredBlock.getKey());
                tag(Tags.Blocks.FENCES).add(deferredBlock.getKey());
            }

            if(deferredBlock.get() instanceof AbstractCauldronBlock) {
                tag(BlockTags.CAULDRONS).add(deferredBlock.getKey());
                tag(Tags.Blocks.VILLAGER_JOB_SITES).add(deferredBlock.getKey());
            }

            if(deferredBlock.get() instanceof CraftingTableBlock) {
                tag(Tags.Blocks.PLAYER_WORKSTATIONS_CRAFTING_TABLES).add(deferredBlock.getKey());
            }
            if(deferredBlock.get() instanceof CartographyTableBlock) {
                tag(Tags.Blocks.VILLAGER_JOB_SITES).add(deferredBlock.getKey());
            }

            if(deferredBlock.get() instanceof SaplingBlock)
                tag(BlockItemTags.SAPLINGS.block()).add(deferredBlock.getKey());

            if(deferredBlock.get() instanceof LeavesBlock)
                tag(BlockTags.LEAVES).add(deferredBlock.getKey());

            if(deferredBlock.get().toString().contains("soul_sandstone")) {
                tag(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(deferredBlock.getKey());
                if(deferredBlock.get() instanceof StairBlock)
                    tag(Tags.Blocks.SANDSTONE_STAIRS).add(deferredBlock.getKey());
                else if(deferredBlock.get() instanceof SlabBlock)
                    tag(Tags.Blocks.SANDSTONE_SLABS).add(deferredBlock.getKey());
                else if(!(deferredBlock.get() instanceof WallBlock))
                    tag(Tags.Blocks.SANDSTONE_BLOCKS).add(deferredBlock.getKey());
            }
            
            if(mineableWithPickaxe(deferredBlock.get()))
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(deferredBlock.getKey());

            if(mineableWithAxe(deferredBlock.get()))
                tag(BlockTags.MINEABLE_WITH_AXE).add(deferredBlock.getKey());
        }
    }

    private boolean mineableWithPickaxe(Block block) {
        return !block.toString().contains("wall") && // "#minecraft:wall" is already in the tag
                (block.toString().contains("soul_sandstone") ||
                        block.toString().contains("brick") ||
                        block.toString().contains("smooth") ||
                        block.toString().contains("polished") ||
                        block.toString().contains("chiseled") ||
                        block instanceof LanternBlock ||
                        block.defaultBlockState().is(ModBlocks.NETHERITE_STAIRS.get()));
    }

    private boolean mineableWithAxe(Block block) {
        return block instanceof CraftingTableBlock
                || block instanceof CartographyTableBlock;
    }
}
