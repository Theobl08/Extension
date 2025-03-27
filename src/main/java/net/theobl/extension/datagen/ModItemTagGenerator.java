package net.theobl.extension.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.item.ModItems;
import net.theobl.extension.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Extension.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(Tags.Items.GLASS_PANES).add(ModBlocks.TINTED_GLASS_PANE.get().asItem());

        this.tag(ItemTags.BOATS).add(ModItems.CRIMSON_BOAT.get(), ModItems.WARPED_BOAT.get());
        this.tag(ItemTags.CHEST_BOATS).add(ModItems.CRIMSON_CHEST_BOAT.get(), ModItems.WARPED_CHEST_BOAT.get());

        for (RegistryObject<Block> antiblock : ModBlocks.ANTIBLOCKS) {
            this.tag(ModTags.Items.ANTIBLOCK).add(antiblock.get().asItem());
        }

//        this.tag(ModTags.Items.WHITE_LOGS)
//                .add(ModBlocks.WHITE_LOG.get().asItem(),
//                        ModBlocks.WHITE_WOOD.get().asItem(),
//                        ModBlocks.COLORED_STRIPPED_LOGS.get(0).get().asItem(),
//                        ModBlocks.COLORED_STRIPPED_WOODS.get(0).get().asItem());
////                        ModBlocks.STRIPPED_WHITE_LOG.get().asItem(),
////                        ModBlocks.STRIPPED_WHITE_WOOD.get().asItem());
//
//        this.tag(ModTags.Items.LIGHT_GRAY_LOGS)
//                .add(ModBlocks.LIGHT_GRAY_LOG.get().asItem(),
//                        ModBlocks.LIGHT_GRAY_WOOD.get().asItem(),
//                        ModBlocks.COLORED_STRIPPED_LOGS.get(1).get().asItem(),
//                        ModBlocks.COLORED_STRIPPED_WOODS.get(1).get().asItem());
////                        ModBlocks.STRIPPED_LIGHT_GRAY_LOG.get().asItem(),
////                        ModBlocks.STRIPPED_LIGHT_GRAY_WOOD.get().asItem());
//
//        this.tag(ModTags.Items.GRAY_LOGS)
//                .add(ModBlocks.GRAY_LOG.get().asItem(),
//                        ModBlocks.GRAY_WOOD.get().asItem(),
//                        ModBlocks.COLORED_STRIPPED_LOGS.get(2).get().asItem(),
//                        ModBlocks.COLORED_STRIPPED_WOODS.get(2).get().asItem());
////                        ModBlocks.STRIPPED_GRAY_LOG.get().asItem(),
////                        ModBlocks.STRIPPED_GRAY_WOOD.get().asItem());
//
//        this.tag(ModTags.Items.BLACK_LOGS)
//                .add(ModBlocks.BLACK_LOG.get().asItem(),
//                        ModBlocks.BLACK_WOOD.get().asItem(),
//                        ModBlocks.COLORED_STRIPPED_LOGS.get(3).get().asItem(),
//                        ModBlocks.COLORED_STRIPPED_WOODS.get(3).get().asItem());
////                        ModBlocks.STRIPPED_BLACK_LOG.get().asItem(),
////                        ModBlocks.STRIPPED_BLACK_WOOD.get().asItem());
//
//        this.tag(ItemTags.LOGS_THAT_BURN)
//                .addTag(ModTags.Items.WHITE_LOGS)
//                .addTag(ModTags.Items.LIGHT_GRAY_LOGS)
//                .addTag(ModTags.Items.GRAY_LOGS)
//                .addTag(ModTags.Items.BLACK_LOGS);

        for(TagKey<Item> tag : ModTags.Items.COLORED_LOGS){
            int index = ModTags.Items.COLORED_LOGS.indexOf(tag);
            this.copy(ModTags.Blocks.COLORED_LOGS.get(index), tag);
        }

        this.copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);

        this.copy(BlockTags.WOOL, ItemTags.WOOL);
        this.copy(BlockTags.WOOL_CARPETS, ItemTags.WOOL_CARPETS);

        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);

        this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        this.copy(BlockTags.STAIRS, ItemTags.STAIRS);

        this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        this.copy(BlockTags.SLABS, ItemTags.SLABS);

        this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        this.copy(BlockTags.FENCES, ItemTags.FENCES);

        this.copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);

        this.copy(BlockTags.WALLS, ItemTags.WALLS);

        this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);

        this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);

        this.copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
        this.copy(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS);

        this.copy(BlockTags.LEAVES, ItemTags.LEAVES);

        for (RegistryObject<Item> boat : ModItems.COLORED_BOATS) {
            this.tag(ItemTags.BOATS).add(boat.get());
            this.tag(ModTags.Items.DYEABLE_BOATS).add(boat.get());
        }
        for (RegistryObject<Item> chestBoat : ModItems.COLORED_CHEST_BOATS) {
            this.tag(ItemTags.CHEST_BOATS).add(chestBoat.get());
        }

        for (RegistryObject<Block> coloredLog : ModBlocks.COLORED_LOGS) {
            int index = ModBlocks.COLORED_LOGS.indexOf(coloredLog);
            this.tag(ModTags.Items.DYEABLE_LOGS).add(coloredLog.get().asItem());
            this.tag(ModTags.Items.DYEABLE_STRIPPED_LOGS).add(ModBlocks.COLORED_STRIPPED_LOGS.get(index).get().asItem());
            this.tag(ModTags.Items.DYEABLE_WOODS).add(ModBlocks.COLORED_WOODS.get(index).get().asItem());
            this.tag(ModTags.Items.DYEABLE_STRIPPED_WOODS).add(ModBlocks.COLORED_STRIPPED_WOODS.get(index).get().asItem());
        }

        this.tag(Tags.Items.DYES)
                .add(ModItems.VIBRANT_RED_DYE.get(),
                        ModItems.DULL_ORANGE_DYE.get(),
                        ModItems.BRIGHT_YELLOW_DYE.get(),
                        ModItems.CHARTREUSE_DYE.get(),
                        ModItems.VIBRANT_GREEN_DYE.get(),
                        ModItems.SPRING_GREEN_DYE.get(),
                        ModItems.BRIGHT_CYAN_DYE.get(),
                        ModItems.CAPRI_DYE.get(),
                        ModItems.ULTRAMARINE_DYE.get(),
                        ModItems.VIOLET_DYE.get(),
                        ModItems.VIBRANT_PURPLE_DYE.get(),
                        ModItems.BRIGHT_MAGENTA_DYE.get(),
                        ModItems.ROSE_DYE.get(),
                        ModItems.DARK_GRAY_DYE.get(),
                        ModItems.SILVER_DYE.get(),
                        ModItems.ALPHA_WHITE_DYE.get());

        this.tag(ModTags.Items.DYEABLE_BOATS)
                .add(Items.OAK_BOAT)
                .add(Items.SPRUCE_BOAT)
                .add(Items.BIRCH_BOAT)
                .add(Items.JUNGLE_BOAT)
                .add(Items.ACACIA_BOAT)
                .add(Items.DARK_OAK_BOAT)
                .add(Items.MANGROVE_BOAT)
                .add(Items.BAMBOO_RAFT)
                .add(Items.CHERRY_BOAT)
                .add(ModItems.CRIMSON_BOAT.get())
                .add(ModItems.WARPED_BOAT.get());

        this.tag(ModTags.Items.DYEABLE_LOGS)
                .add(Items.OAK_LOG)
                .add(Items.SPRUCE_LOG)
                .add(Items.BIRCH_LOG)
                .add(Items.JUNGLE_LOG)
                .add(Items.ACACIA_LOG)
                .add(Items.DARK_OAK_LOG)
                .add(Items.MANGROVE_LOG)
                .add(Items.BAMBOO_BLOCK)
                .add(Items.CHERRY_LOG)
                .add(Items.CRIMSON_STEM)
                .add(Items.WARPED_STEM);

        this.tag(ModTags.Items.DYEABLE_WOODS)
                .add(Items.OAK_WOOD)
                .add(Items.SPRUCE_WOOD)
                .add(Items.BIRCH_WOOD)
                .add(Items.JUNGLE_WOOD)
                .add(Items.ACACIA_WOOD)
                .add(Items.DARK_OAK_WOOD)
                .add(Items.MANGROVE_WOOD)
//                .add(Items.BAMBOO_BLOCK)
                .add(Items.CHERRY_WOOD)
                .add(Items.CRIMSON_HYPHAE)
                .add(Items.WARPED_HYPHAE);

        this.tag(ModTags.Items.DYEABLE_STRIPPED_LOGS)
                .add(Items.STRIPPED_OAK_LOG)
                .add(Items.STRIPPED_SPRUCE_LOG)
                .add(Items.STRIPPED_BIRCH_LOG)
                .add(Items.STRIPPED_JUNGLE_LOG)
                .add(Items.STRIPPED_ACACIA_LOG)
                .add(Items.STRIPPED_DARK_OAK_LOG)
                .add(Items.STRIPPED_MANGROVE_LOG)
                .add(Items.STRIPPED_BAMBOO_BLOCK)
                .add(Items.STRIPPED_CHERRY_LOG)
                .add(Items.STRIPPED_CRIMSON_STEM)
                .add(Items.STRIPPED_WARPED_STEM);

        this.tag(ModTags.Items.DYEABLE_STRIPPED_WOODS)
                .add(Items.STRIPPED_OAK_WOOD)
                .add(Items.STRIPPED_SPRUCE_WOOD)
                .add(Items.STRIPPED_BIRCH_WOOD)
                .add(Items.STRIPPED_JUNGLE_WOOD)
                .add(Items.STRIPPED_ACACIA_WOOD)
                .add(Items.STRIPPED_DARK_OAK_WOOD)
                .add(Items.STRIPPED_MANGROVE_WOOD)
//                .add(Items.STRIPPED_BAMBOO_BLOCK)
                .add(Items.STRIPPED_CHERRY_WOOD)
                .add(Items.STRIPPED_CRIMSON_HYPHAE)
                .add(Items.STRIPPED_WARPED_HYPHAE);
    }
}
