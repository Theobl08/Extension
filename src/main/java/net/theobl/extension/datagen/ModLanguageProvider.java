package net.theobl.extension.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.tags.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.neoforged.fml.util.ObfuscationReflectionHelper;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.theobl.extension.Config;
import net.theobl.extension.Extension;
import net.theobl.extension.biome.ModBiomes;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.effect.ModMobEffects;
import net.theobl.extension.entity.ModEntityType;
import net.theobl.extension.gamerules.ModGameRules;
import net.theobl.extension.item.ModCreativeModeTabs;
import net.theobl.extension.item.ModItems;
import net.theobl.extension.item.ModTooltips;
import net.theobl.extension.item.alchemy.ModPotions;
import net.theobl.extension.stats.ModStats;
import net.theobl.extension.util.ModUtil;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NullMarked;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ModLanguageProvider extends LanguageProvider {
    List<String> translatedKeys = new ArrayList<>();
    private static final List<String> POTIONS_TYPES = List.of("potion.", "splash_potion.", "lingering_potion.", "tipped_arrow.");
    public ModLanguageProvider(PackOutput output) {
        super(output, Extension.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("config.jade.plugin_extension.potion_cauldron", "Cauldron's Potion Info");

        add("itemGroup.extension", "Extension");
        addTranslatableComponent(ModCreativeModeTabs.VARIANT_VANILLA_BLOCKS.get().getDisplayName(), capitalizeString(ModCreativeModeTabs.VARIANT_VANILLA_BLOCKS.getId().getPath()));

        addTranslatableComponent(ModTooltips.CRAFTING_TABLE_TOOLTIP, "Make stuff");
        addTranslatableComponent(ModTooltips.FURNACE_TOOLTIP, "Smelt & cook");
        addTranslatableComponent(ModTooltips.BLAST_FURNACE_TOOLTIP, "Smelt metal faster");
        addTranslatableComponent(ModTooltips.SMOKER_TOOLTIP, "Cook food faster");
        addTranslatableComponent(ModTooltips.STORAGE_ITEM_TOOLTIP, "Storage");
        addTranslatableComponent(ModTooltips.COMPOSTER_TOOLTIP, "Make bone meal");
        addTranslatableComponent(ModTooltips.LOOM_TOOLTIP, "Decorate banners");
        addTranslatableComponent(ModTooltips.CARTOGRAPHY_TABLE_TOOLTIP, "Modify Maps");
        addTranslatableComponent(ModTooltips.SMITHING_TABLE_TOOLTIP, "Upgrade tools & armor");
        addTranslatableComponent(ModTooltips.STONECUTTER_TOOLTIP, "Cut stone & copper");
        addTranslatableComponent(ModTooltips.LECTERN_TOOLTIP, "Display books");
        addTranslatableComponent(ModTooltips.GRINDSTONE_TOOLTIP, "Remove enchantments");
        addTranslatableComponent(ModTooltips.BREWING_STAND_TOOLTIP, "Brew potions");
        addTranslatableComponent(ModTooltips.CAULDRON_TOOLTIP, "Holds liquids & potions"); // Since we have created PotionCauldronBlock, this is technically true
        addTranslatableComponent(ModTooltips.ANVIL_TOOLTIP, "Repair & enchant tools");
        addTranslatableComponent(ModTooltips.ENCHANTING_TABLE_TOOLTIP, "Enchant tools & books");
        addTranslatableComponent(ModTooltips.HOPPER_TOOLTIP, "Add items to chest");
        addTranslatableComponent(ModTooltips.DROPPER_TOOLTIP, "Eject items");
        addTranslatableComponent(ModTooltips.DISPENSER_TOOLTIP, "Automate actions");
        addTranslatableComponent(ModTooltips.OBSERVER_TOOLTIP, "Detects change");
        addTranslatableComponent(ModTooltips.CRAFTER_TOOLTIP, "Automate crafting");
        addTranslatableComponent(ModTooltips.PISTON_TOOLTIP, "Push blocks");
        addTranslatableComponent(ModTooltips.STICKY_PISTON_TOOLTIP, "Pull blocks");
        addTranslatableComponent(ModTooltips.COMPARATOR_TOOLTIP, "Reads signal strength");
        addTranslatableComponent(ModTooltips.HOE_TOOLTIP, "Farming");
        addTranslatableComponent(ModTooltips.SHEARS_TOOLTIP, "Cut & trim");
        addTranslatableComponent(ModTooltips.BRUSH_TOOLTIP, "Excavate & brush");
        addTranslatableComponent(ModTooltips.FLINT_AND_STEEL_TOOLTIP, "Make fire");
        addTranslatableComponent(ModTooltips.COMPASS_TOOLTIP, "Points to world spawn");
        addTranslatableComponent(ModTooltips.LODESTONE_COMPASS_TOOLTIP, "Points to lodestone");
        addTranslatableComponent(ModTooltips.RECOVERY_COMPASS_TOOLTIP, "See where you died");
        addTranslatableComponent(ModTooltips.REDSTONE_SOURCE_TOOLTIP, "Redstone power source");
        addTranslatableComponent(ModTooltips.REDSTONE_CONDUCTOR_TOOLTIP, "Redstone conductor");
        addTranslatableComponent(ModTooltips.REDSTONE_POWERED_TOOLTIP, "Redstone powered");

        addTranslatableComponent(ModTooltips.CHISELED_BOOKSHELF_TOOLTIP, "Store books");
        addTranslatableComponent(ModTooltips.FLETCHING_TABLE_TOOLTIP, "Make arrow efficiently");
        addTranslatableComponent(ModTooltips.CAMPFIRE_TOOLTIP, "Slowly cook food");
        addTranslatableComponent(ModTooltips.TARGET_TOOLTIP, "Detects arrow hits");
        addTranslatableComponent(ModTooltips.SCULK_SENSOR_TOOLTIP, "Detects sounds");

        addGameRule(ModGameRules.BEETROOT_SOUP_SOURCE_CONVERSION, "Beetroot soup converts to source", "When flowing beetroot soup is surrounded on two sides by beetroot soup sources, it converts into a source.");
        addGameRule(ModGameRules.MUSHROOM_STEW_SOURCE_CONVERSION, "Mushroom stew converts to source", "When flowing mushroom stew is surrounded on two sides by mushroom stew sources, it converts into a source.");

        add("potion.longPotion", "Extended");
        add("potion.strongPotion", "Enhanced");

        for(DeferredHolder<Block, ? extends Block> block : ModBlocks.BLOCKS.getEntries()) {
            if(block.get() instanceof CarvedPumpkinBlock)
                addBlock(block, capitalizeString(filterBlockLang(block.get())).replace(" O ", " o'"));
            else if(block.get().defaultBlockState().is(ModBlocks.NETHERITE_STAIRS))
                addBlock(block, "Swaggiest stairs ever");
            else
                addBlock(block, capitalizeString(filterBlockLang(block.get())));
        }

        for(DeferredHolder<Item, ? extends Item> item : ModItems.ITEMS.getEntries()) {
            if(item.get() instanceof MinecartItem)
                addItem(item,"Minecart with Monster Spawner");
            else if(item == ModItems.POTATO_CHEST_RAFT)
                addItem(item, "Potato Raft with Chest");
            else if(item == ModItems.BLUE_NETHER_WART || !(item.get() instanceof BlockItem))
                addItem(item, capitalizeString(filterItemLang(item.get())));
        }

        ModMobEffects.MOB_EFFECTS.getEntries().forEach(effect ->
                addEffect(effect, capitalizeString(effect.getId().getPath())));
        ModPotions.POTIONS.getEntries().forEach(this::addPotion);

        addEntityType(ModEntityType.POTATO_RAFT, "Potato Raft");
        addEntityType(ModEntityType.POTATO_CHEST_RAFT, "Potato Raft with Chest");

        addBiome(ModBiomes.ARBORETUM, "Arboretum");

        addConfig(Config.BOAT_STEP_UP);
        addConfig(Config.CLEAR_VOID);
        addConfig(Config.NO_FIRE_OVERLAY);
        addConfig(Config.FARMLAND_TRAMPLE);
        addConfig(Config.UNBREAKABLE_AT_MAX_UNBREAKING_LEVEL);
        addConfig(Config.PISTONS_CAN_PUSH_BLOCK_ENTITIES);
        addConfig(Config.SHOW_ADDITIONAL_ITEM_INFORMATION);

        translateTags(BannerPatternTags.class);
        translateTags(BiomeTags.class);
        translateTags(BlockTags.class);
        translateTags(DamageTypeTags.class);
        translateTags(DialogTags.class);
        translateTags(EnchantmentTags.class);
        translateTags(EntityTypeTags.class);
        translateTags(FeatureTags.class);
        translateTags(FlatLevelGeneratorPresetTags.class);
        translateTags(FluidTags.class);
        translateTags(GameEventTags.class);
        translateTags(InstrumentTags.class);
        translateTags(ItemTags.class);
        translateTags(PaintingVariantTags.class);
        translateTags(PoiTypeTags.class);
        translateTags(PotionTags.class);
        translateTags(StructureTags.class);
        translateTags(TimelineTags.class);
        translateTags(VillagerTradeTags.class);
        translateTags(WorldPresetTags.class);

        // Translate tags in BlockItemTags.class;
        for (Field field : BlockItemTags.class.getDeclaredFields()) {
            BlockItemTagId tagId;
            Map<?, ?> data = ObfuscationReflectionHelper.getPrivateValue(LanguageProvider.class, this, "data");
            try {
                tagId = (BlockItemTagId) field.get(null);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(BlockItemTags.class.getName() + " is missing tag name: " + field.getName());
            }
            //Arrays.stream(BlockTags.class.getFields()).noneMatch(f -> f.getName().equals(field.getName())) // https://stackoverflow.com/questions/7324991/see-if-field-exists-in-class/43953863#43953863
            if(!data.containsKey(Tags.getTagTranslationKey(tagId.block())))
                add(tagId.block(), capitalizeString(field.getName()));
            if(!data.containsKey(Tags.getTagTranslationKey(tagId.item())))
                add(tagId.item(), capitalizeString(field.getName()));
        }

        add("item.minecraft.tipped_arrow.effect.awkward", "Awkward Tipped Arrow");
        add("item.minecraft.tipped_arrow.effect.mundane", "Mundane Tipped Arrow");
        add("item.minecraft.tipped_arrow.effect.thick", "Thick Tipped Arrow");

        add("container.fletching", "Fletching");
        add(ModStats.INTERACT_WITH_FLETCHING_TABLE.toLanguageKey("stat"), "Interactions with Fletching Table");
    }

    private static @NotNull String capitalizeString(String string) {
        String[] words = string.split("_");

        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if(!sb.isEmpty()) sb.append(' ');
            sb.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase());
        }
        return sb.toString();
    }

    private static @NotNull String filterBlockLang(@NotNull Block key) {
        return key.getDescriptionId().replace("block.extension.","");
    }

    private static @NotNull String filterItemLang(@NotNull Item key) {
        return key.getDescriptionId().replace("item.extension.","");
    }

    private void addConfig(ModConfigSpec.ConfigValue<?> configValue) {
        String path = String.join(".", configValue.getPath());
        String key = "extension.configuration." + path;
        add(key, capitalizeString(path.replaceAll("(?<!_)(?=[A-Z])", "_"))); //https://stackoverflow.com/questions/44644827/how-to-insert-space-before-capital-letter-for-a-string-using-java/44644996#44644996
    }

    private void addPotion(DeferredHolder<Potion, ?> potion) {
        String name = ModUtil.name(potion);
        String effect = "effect." + name;
        String prefix = "item.minecraft.";
        if(!name.startsWith("long_") && !name.startsWith("strong_")) {
            for(String type : POTIONS_TYPES) {
                add(prefix + type + effect, capitalizeString(
                        type.replace("tipped_", "")
                            .replace(".","_")) + " of " + capitalizeString(name));
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
            add(tag, capitalizeString(field.getName()));
        }
    }

    @Override
    @NullMarked
    public void add(String key, String value) {
        if(!translatedKeys.contains(key)) {
            translatedKeys.add(key);
            super.add(key, value);
        }
    }
}
