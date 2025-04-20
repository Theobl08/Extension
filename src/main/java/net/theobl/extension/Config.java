package net.theobl.extension;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = Extension.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

//    private static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER.comment("Whether to log the dirt block on common setup").define("logDirtBlock", true);

//    private static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER.comment("A magic number").defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

//    public static final ModConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER.comment("What you want the introduction message to be for the magic number").define("magicNumberIntroduction", "The magic number is... ");

    public static final ModConfigSpec.BooleanValue CLEAR_VOID =
            BUILDER.comment("Clear the black void between y = -64 and y = 63 in non superflat world.\nUseful for skyblocks or floating_islands worlds")
                    .define("clearVoid", false);

    public static final ModConfigSpec.BooleanValue NO_FIRE_OVERLAY =
            BUILDER.comment("Remove the annoying fire overlay when the player has Fire Resistance effect or if gamerule \"fireDamage\" is set to false")
                    .define("noFireOverlay", true);

    public static final ModConfigSpec.BooleanValue BOAT_STEP_UP =
            BUILDER.comment("Whether boats can step up 1 full block")
                    .define("boatStepUp", true);

    // a list of strings that are treated as resource locations for items
//    private static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER.comment("A list of items to log on common setup.").defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), Config::validateItemName);

    static final ModConfigSpec SPEC = BUILDER.build();

    //public static boolean logDirtBlock;
    //public static int magicNumber;
    //public static String magicNumberIntroduction;
    //public static Set<Item> items;
    public static Boolean clearVoid;
    public static Boolean noFireOverlay;
    public static Boolean boatStepUp;

//    private static boolean validateItemName(final Object obj) {
//        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
//    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
//        logDirtBlock = LOG_DIRT_BLOCK.get();
//        magicNumber = MAGIC_NUMBER.get();
//        magicNumberIntroduction = MAGIC_NUMBER_INTRODUCTION.get();
        clearVoid = CLEAR_VOID.get();
        noFireOverlay = NO_FIRE_OVERLAY.get();
        boatStepUp = BOAT_STEP_UP.get();

        // convert the list of strings into a set of items
//        items = ITEM_STRINGS.get().stream().map(itemName -> BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemName))).collect(Collectors.toSet());
    }
}
