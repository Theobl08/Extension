package net.theobl.extension.datagen;

import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.theobl.extension.Config;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.item.ModItems;
import net.theobl.extension.item.alchemy.ModPotions;
import net.theobl.extension.stats.ModStats;
import net.theobl.extension.util.ModUtil;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ModLanguageProvider extends LanguageProvider {
    private static final List<String> POTIONS_TYPES = List.of("potion.", "splash_potion.", "lingering_potion.", "tipped_arrow.");
    public ModLanguageProvider(PackOutput output) {
        super(output, Extension.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.extension", "Extension");

        add("potion.longPotion", "Extended");
        add("potion.strongPotion", "Enhanced");

        for(DeferredHolder<Block, ? extends Block> block : ModBlocks.BLOCKS.getEntries()) {
            if(block.get() instanceof CarvedPumpkinBlock)
                add(block.get(),capitalizeString(filterBlockLang(block.get())).replace(" O ", " o'"));
            else if(block.get().defaultBlockState().is(ModBlocks.NETHERITE_STAIRS))
                add(block.get(), "Swaggiest stairs ever");
            else
                add(block.get(), capitalizeString(filterBlockLang(block.get())));
        }

        for(DeferredHolder<Item, ? extends Item> item : ModItems.ITEMS.getEntries()) {
            if(item.get() instanceof MinecartItem)
                add(item.get(),"Minecart with Monster Spawner");
            else if(item.get() == ModItems.BLUE_NETHER_WART.asItem() || !(item.get() instanceof BlockItem))
                add(item.get(), capitalizeString(filterItemLang(item.get())));
        }

        for(DeferredHolder<Potion, ? extends Potion> potion : ModPotions.POTIONS.getEntries()) {
            addPotion(potion);
        }

        addConfig(Config.BOAT_STEP_UP);
        addConfig(Config.CLEAR_VOID);
        addConfig(Config.NO_FIRE_OVERLAY);
        addConfig(Config.FARMLAND_TRAMPLE);
        addConfig(Config.UNBREAKABLE_AT_MAX_UNBREAKING_LEVEL);

        translateTags(BannerPatternTags.class);
        translateTags(BiomeTags.class);
        translateTags(BlockTags.class);
        translateTags(DamageTypeTags.class);
        translateTags(EnchantmentTags.class);
        translateTags(EntityTypeTags.class);
        translateTags(FlatLevelGeneratorPresetTags.class);
        translateTags(FluidTags.class);
        translateTags(GameEventTags.class);
        translateTags(InstrumentTags.class);
        translateTags(ItemTags.class);
        translateTags(PaintingVariantTags.class);
        translateTags(PoiTypeTags.class);
        translateTags(StructureTags.class);
        translateTags(WorldPresetTags.class);

        add("item.minecraft.tipped_arrow.effect.awkward", "Awkward Tipped Arrow");
        add("item.minecraft.tipped_arrow.effect.mundane", "Mundane Tipped Arrow");
        add("item.minecraft.tipped_arrow.effect.thick", "Thick Tipped Arrow");

        add("container.fletching", "Fletching");
        add(ModStats.INTERACT_WITH_FLETCHING_TABLE.toLanguageKey("stat"), "Interactions with Fletching Table");
    }

    private static @NotNull String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++){
            if(!found && Character.isLetter(chars[i])){
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '_') {
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    private static @NotNull String filterBlockLang(@NotNull Block key) {
        return key.getDescriptionId().replace("block.extension.","").replace("_"," ");
    }

    private static @NotNull String filterItemLang(@NotNull Item key) {
        return key.getDescriptionId().replace("item.extension.","").replace("_"," ");
    }

    private void addConfig(ModConfigSpec.ConfigValue<?> configValue) {
        String path = String.join(".", configValue.getPath());
        String key = "extension.configuration." + path;
        add(key, capitalizeString(path.replaceAll("(?<!_)(?=[A-Z])", " "))); //https://stackoverflow.com/questions/44644827/how-to-insert-space-before-capital-letter-for-a-string-using-java/44644996#44644996
    }

    private void addPotion(DeferredHolder<Potion, ?> potion) {
        String name = ModUtil.name(potion);
        String effect = "effect." + name;
        String prefix = "item.minecraft.";
        if(!name.startsWith("long_") && !name.startsWith("strong_")) {
            for(String type : POTIONS_TYPES) {
                add(prefix + type + effect, capitalizeString(
                        type.replace("tipped_", "")
                            .replace(".","")
                            .replace("_", " ")) + " of " + capitalizeString(name));
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void translateTags(Class<?> c) {
        for (Field field : c.getDeclaredFields()) {
            TagKey<Block> tag = null;
            try {
                tag = (TagKey<Block>) field.get(null);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(c.getName() + " is missing tag name: " + field.getName());
            }
            add(tag, capitalizeString(field.getName().replace("_", " ").toLowerCase()));
        }
    }
}
