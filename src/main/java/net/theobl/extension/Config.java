package net.theobl.extension;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = Extension.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

    static {COMMON_BUILDER.comment("Extension Common Configuration");}

    private static final ForgeConfigSpec.BooleanValue LOG_DIRT_BLOCK = COMMON_BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    private static final ForgeConfigSpec.IntValue MAGIC_NUMBER = COMMON_BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    public static final ForgeConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = COMMON_BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");

    // a list of strings that are treated as resource locations for items
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = COMMON_BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), Config::validateItemName);

    private static final ForgeConfigSpec.BooleanValue DARK_OAK = COMMON_BUILDER.comment("Whether a little dark oak can grow from a single sapling")
            .define("singleSaplingCanGrow", true);

    private static final ForgeConfigSpec.BooleanValue CLEAR_VOID = COMMON_BUILDER.comment("Clear the black void between y = -64 and y = 63 in non superflat world.\nUseful for skyblocks or floating_islands worlds")
            .define("clearVoid", true);

    private static final ForgeConfigSpec.BooleanValue IRON_DOOR = COMMON_BUILDER.comment("Set to true if door like iron door can be opened by hand")
            .define("ironDoorCanBeOpenedByHand", false);

    private static final ForgeConfigSpec.BooleanValue MC237017 = COMMON_BUILDER.comment("Set to true to fix lava level hardcoded at y = -54")
            .define("hardcodedLavaLevelFix", true);

    static final ForgeConfigSpec COMMON_SPEC = COMMON_BUILDER.build();


    public static boolean logDirtBlock;
    public static int magicNumber;
    public static String magicNumberIntroduction;
    public static Set<Item> items;
    public static boolean darkOak;
    public static boolean clearVoid;
    public static boolean ironDoor;
    public static boolean mc237017fix;

    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        clearVoid = CLEAR_VOID.get();
        logDirtBlock = LOG_DIRT_BLOCK.get();
        magicNumber = MAGIC_NUMBER.get();
        magicNumberIntroduction = MAGIC_NUMBER_INTRODUCTION.get();
        darkOak = DARK_OAK.get();
        ironDoor = IRON_DOOR.get();
        mc237017fix = MC237017.get();


        // convert the list of strings into a set of items
        items = ITEM_STRINGS.get().stream()
                .map(itemName -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName)))
                .collect(Collectors.toSet());
    }

    @SubscribeEvent
    static void loadingClientConfig(final ModConfigEvent event){

    }
}
