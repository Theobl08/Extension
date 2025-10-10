package net.theobl.extension;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = Extension.MODID)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue CLEAR_VOID =
            BUILDER.comment("Clear the black void between y = -64 and y = 63 in non superflat world.\nUseful for skyblocks or floating_islands worlds")
                    .define("clearVoid", false);

    public static final ModConfigSpec.BooleanValue NO_FIRE_OVERLAY =
            BUILDER.comment("Remove the annoying fire overlay when the player has Fire Resistance effect or if gamerule \"fireDamage\" is set to false")
                    .define("noFireOverlay", true);

    public static final ModConfigSpec.BooleanValue BOAT_STEP_UP =
            BUILDER.comment("Whether boats can step up 1 full block")
                    .define("boatStepUp", true);

    public static final ModConfigSpec.BooleanValue FARMLAND_TRAMPLE =
            BUILDER.comment("Set to false to disable farmland trampling")
                    .define("farmlandTrampling", false);

    public static final ModConfigSpec.BooleanValue UNBREAKABLE_AT_MAX_UNBREAKING_LEVEL =
            BUILDER.comment("Whether damageable items becomes unbreakable when the unbreaking enchantment is at max level")
                    .define("unbreakableAtMaxUnbreakingLevel", false);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static Boolean clearVoid;
    public static Boolean noFireOverlay;
    public static Boolean boatStepUp;
    public static Boolean farmlandTrample;
    public static Boolean unbreakableAtMaxUnbreakingLevel;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        clearVoid = CLEAR_VOID.get();
        noFireOverlay = NO_FIRE_OVERLAY.get();
        boatStepUp = BOAT_STEP_UP.get();
        farmlandTrample = FARMLAND_TRAMPLE.get();
        unbreakableAtMaxUnbreakingLevel = UNBREAKABLE_AT_MAX_UNBREAKING_LEVEL.get();
    }
}
