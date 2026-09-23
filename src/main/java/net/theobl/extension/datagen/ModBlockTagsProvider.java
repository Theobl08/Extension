package net.theobl.extension.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.references.BlockItemIds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
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
    protected IntrinsicHolderTagAppender<Block> tag(TagKey<Block> tag) {
        return new IntrinsicHolderTagAppender<>(super.tag(tag)) {
            @Override
            public ResourceKey<Block> convertElement(Block block) {
                return BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow();
            }
        };
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Blocks.FENCES_NETHER_BRICK)
                .add(ModBlocks.RED_NETHER_BRICK_FENCE)
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE);

        tag(Tags.Blocks.GLASS_PANES).add(ModBlocks.TINTED_GLASS_PANE);
        tag(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.BLUE_NETHER_WART)
                .add(ModBlocks.SOUL_O_LANTERN)
                .add(ModBlocks.REDSTONE_O_LANTERN)
                .add(ModBlocks.COPPER_O_LANTERN)
                .add(ModBlocks.ENDER_O_LANTERN);

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.BLUE_NETHER_WART)
                .add(ModBlocks.SOUL_O_LANTERN)
                .add(ModBlocks.COPPER_O_LANTERN)
                .add(ModBlocks.COPPER_CAMPFIRE)
                .add(ModBlocks.REDSTONE_O_LANTERN)
                .add(ModBlocks.REDSTONE_CAMPFIRE)
                .add(ModBlocks.ENDER_O_LANTERN)
                .add(ModBlocks.ENDER_CAMPFIRE);

        tag(BlockTags.WALL_POST_OVERRIDE).add(ModBlocks.ENDER_TORCH);
        tag(BlockTags.CAMPFIRES).add(ModBlocks.COPPER_CAMPFIRE, ModBlocks.REDSTONE_CAMPFIRE, ModBlocks.ENDER_CAMPFIRE);
        tag(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(ModBlocks.SOUL_O_LANTERN);
        tag(Tags.Blocks.PUMPKINS)
                .add(ModBlocks.SOUL_O_LANTERN)
                .add(ModBlocks.COPPER_O_LANTERN)
                .add(ModBlocks.REDSTONE_O_LANTERN)
                .add(ModBlocks.ENDER_O_LANTERN);

        tag(BlockTags.PIGLIN_REPELLENTS).add(ModBlocks.SOUL_O_LANTERN);

        tag(BlockTags.FIRE).add(ModBlocks.COPPER_FIRE, ModBlocks.REDSTONE_FIRE, ModBlocks.ENDER_FIRE);
        tag(BlockTags.REPLACEABLE).add(ModBlocks.COPPER_FIRE, ModBlocks.REDSTONE_FIRE, ModBlocks.ENDER_FIRE);

        tag(ModBlockTags.COPPER_FIRE_BASE_BLOCKS)
                .addTag(BlockTags.COPPER)
                .remove(BlockItemIds.COPPER_BLOCK.weathering().oxidized().block())
                .addAll(Blocks.CHISELED_COPPER.asList().stream().filter(this::isNotOxidized).toList())
                .addAll(Blocks.COPPER_GRATE.asList().stream().filter(this::isNotOxidized).toList())
                .addAll(Blocks.CUT_COPPER.asList().stream().filter(this::isNotOxidized).toList())
                .addAll(Blocks.CUT_COPPER_STAIRS.asList().stream().filter(this::isNotOxidized).toList())
                .addAll(Blocks.CUT_COPPER_SLAB.asList().stream().filter(this::isNotOxidized).toList())
                .addAll(Blocks.COPPER_TRAPDOOR.asList().stream().filter(this::isNotOxidized).toList())
                .addAll(Blocks.COPPER_BULB.asList().stream().filter(this::isNotOxidized).toList())
                .add(Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE)
                .add(ModBlocks.COPPER_O_LANTERN);

        tag(ModBlockTags.REDSTONE_FIRE_BASE_BLOCKS)
                .addTag(BlockItemTags.REDSTONE_ORES.block())
                .add(Blocks.REDSTONE_BLOCK)
                .add(ModBlocks.REDSTONE_O_LANTERN);

        tag(ModBlockTags.ENDER_FIRE_BASE_BLOCKS)
                .addTag(Tags.Blocks.END_STONES)
                .add(Blocks.END_STONE_BRICKS)
                .add(Blocks.END_STONE_BRICK_STAIRS)
                .add(Blocks.END_STONE_BRICK_SLAB)
                .add(Blocks.END_STONE_BRICK_WALL)
                .add(Blocks.PURPUR_BLOCK)
                .add(Blocks.PURPUR_STAIRS)
                .add(Blocks.PURPUR_SLAB)
                .add(Blocks.PURPUR_PILLAR)
                .add(ModBlocks.CHISELED_END_STONE_BRICKS)
                .add(ModBlocks.POLISHED_END_STONE)
                .add(ModBlocks.POLISHED_END_STONE_STAIRS)
                .add(ModBlocks.POLISHED_END_STONE_SLAB)
                .add(ModBlocks.POLISHED_END_STONE_WALL)
                .add(ModBlocks.PURPUR_WALL)
                .add(ModBlocks.ENDER_O_LANTERN);

        tag(BlockTags.SWORD_EFFICIENT).add(ModBlocks.POTATO_FRUIT);
        tag(BlockTags.LOGS).add(ModBlocks.POTATO_STEM, ModBlocks.POTATO_HYPHAE);
        tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(ModBlocks.POTATO_STEM);
        tag(Tags.Blocks.NATURAL_WOODS).add(ModBlocks.POTATO_HYPHAE);
        tag(BlockTags.PLANKS).add(ModBlocks.POTATO_PLANKS);
        tag(BlockTags.WOODEN_STAIRS).add(ModBlocks.POTATO_STAIRS);
        tag(BlockTags.WOODEN_SLABS).add(ModBlocks.POTATO_SLAB);
        tag(BlockTags.WOODEN_FENCES).add(ModBlocks.POTATO_FENCE);
        tag(BlockTags.FENCE_GATES).add(ModBlocks.POTATO_FENCE_GATE);
        tag(Tags.Blocks.FENCE_GATES_WOODEN).add(ModBlocks.POTATO_FENCE_GATE);
        tag(BlockTags.WOODEN_DOORS).add(ModBlocks.POTATO_DOOR);
        tag(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.POTATO_TRAPDOOR);
        tag(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.POTATO_PRESSURE_PLATE);
        tag(BlockTags.WOODEN_BUTTONS).add(ModBlocks.POTATO_BUTTON);
        tag(BlockTags.STANDING_SIGNS).add(ModBlocks.POTATO_SIGN);
        tag(BlockTags.WALL_SIGNS).add(ModBlocks.POTATO_WALL_SIGN);
        tag(BlockTags.CEILING_HANGING_SIGNS).add(ModBlocks.POTATO_HANGING_SIGN);
        tag(BlockTags.WALL_HANGING_SIGNS).add(ModBlocks.POTATO_WALL_HANGING_SIGN);

        tag(BlockTags.BAMBOO_BLOCKS).add(ModBlocks.BAMBOO_WOOD);

        tag(BlockTags.REPLACEABLE_BY_MUSHROOMS).add(ModBlocks.ORANGE_SHRUB, ModBlocks.YELLOW_SHRUB);

        for(DeferredHolder<Block, ? extends Block> deferredBlock : ModBlocks.BLOCKS.getEntries()){
            if(deferredBlock.get() instanceof StairBlock)
                tag(BlockTags.STAIRS).add(deferredBlock);

            if(deferredBlock.get() instanceof SlabBlock)
                tag(BlockTags.SLABS).add(deferredBlock);

            if(deferredBlock.get() instanceof WallBlock)
                tag(BlockTags.WALLS).add(deferredBlock);

            if(deferredBlock.get() instanceof FenceBlock) {
                tag(BlockTags.FENCES).add(deferredBlock);
                tag(Tags.Blocks.FENCES).add(deferredBlock);
            }

            if(deferredBlock.get() instanceof AbstractCauldronBlock) {
                tag(BlockTags.CAULDRONS).add(deferredBlock);
                tag(Tags.Blocks.VILLAGER_JOB_SITES).add(deferredBlock);
            }

            if(deferredBlock.get() instanceof CraftingTableBlock) {
                tag(Tags.Blocks.PLAYER_WORKSTATIONS_CRAFTING_TABLES).add(deferredBlock);
            }
            if(deferredBlock.get() instanceof CartographyTableBlock) {
                tag(Tags.Blocks.VILLAGER_JOB_SITES).add(deferredBlock);
            }

            if(deferredBlock.get() instanceof SaplingBlock)
                tag(BlockItemTags.SAPLINGS.block()).add(deferredBlock);

            if(deferredBlock.get() instanceof LeavesBlock)
                tag(BlockTags.LEAVES).add(deferredBlock);

            if(deferredBlock.get().toString().contains("soul_sandstone")) {
                tag(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(deferredBlock);
                if(deferredBlock.get() instanceof StairBlock)
                    tag(Tags.Blocks.SANDSTONE_STAIRS).add(deferredBlock);
                else if(deferredBlock.get() instanceof SlabBlock)
                    tag(Tags.Blocks.SANDSTONE_SLABS).add(deferredBlock);
                else if(!(deferredBlock.get() instanceof WallBlock))
                    tag(Tags.Blocks.SANDSTONE_BLOCKS).add(deferredBlock);
            }
            
            if(mineableWithPickaxe(deferredBlock.get()))
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(deferredBlock);

            if(mineableWithAxe(deferredBlock.get()))
                tag(BlockTags.MINEABLE_WITH_AXE).add(deferredBlock);
        }
    }

    private boolean mineableWithPickaxe(Block block) {
        return !(block instanceof WallBlock) && // "#minecraft:wall" is already in the tag
                (block.toString().contains("soul_sandstone") ||
                        block.toString().contains("brick") ||
                        block.toString().contains("smooth") ||
                        block.toString().contains("polished") ||
                        block.toString().contains("chiseled") ||
                        block instanceof LanternBlock ||
                        block.defaultBlockState().is(ModBlocks.NETHERITE_STAIRS));
    }

    private boolean mineableWithAxe(Block block) {
        return block instanceof CraftingTableBlock
                || block instanceof CartographyTableBlock;
    }

    private boolean isNotOxidized(Block block) {
        return !(block instanceof WeatheringCopper copper)
                || copper.getAge() != WeatheringCopper.WeatherState.OXIDIZED;
    }
}
