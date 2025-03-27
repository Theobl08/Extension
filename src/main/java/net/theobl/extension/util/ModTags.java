package net.theobl.extension.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;

import java.util.ArrayList;
import java.util.List;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> AMETHYST_ORES = tag("amethyst_ores");
        public static final TagKey<Block> ANTIBLOCK = tag("antiblock");
//        public static final TagKey<Block> WHITE_LOGS = tag("white_logs");
//        public static final TagKey<Block> LIGHT_GRAY_LOGS = tag("light_gray_logs");
//        public static final TagKey<Block> GRAY_LOGS = tag("gray_logs");
//        public static final TagKey<Block> BLACK_LOGS = tag("black_logs");
        public static final List<TagKey<Block>> COLORED_LOGS = createColoredBlockTags();

        private static List<TagKey<Block>> createColoredBlockTags() {
            List<TagKey<Block>> coloredTag = new ArrayList<>();
            for (DyeColor color : ModBlocks.COLORS){
                TagKey<Block> tagKey = tag(color.getName() + "_logs");
                coloredTag.add(tagKey);
            }
            return coloredTag;
        }

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Extension.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> ANTIBLOCK = tag("antiblock");
        public static final TagKey<Item> DYEABLE_BOATS = tag("dyeable_boats");
        public static final TagKey<Item> DYEABLE_LOGS = tag("dyeable_logs");
        public static final TagKey<Item> DYEABLE_WOODS = tag("dyeable_woods");
        public static final TagKey<Item> DYEABLE_STRIPPED_LOGS = tag("dyeable_stripped_logs");
        public static final TagKey<Item> DYEABLE_STRIPPED_WOODS = tag("dyeable_stripped_woods");
        public static final List<TagKey<Item>> COLORED_LOGS = createColoredItemTags();

        private static List<TagKey<Item>> createColoredItemTags() {
            List<TagKey<Item>> coloredTag = new ArrayList<>();
            for (DyeColor color : ModBlocks.COLORS){
                TagKey<Item> tagKey = tag(color.getName() + "_logs");
                coloredTag.add(tagKey);
            }
            return coloredTag;
        }

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Extension.MOD_ID, name));
        }
    }

}
