package net.theobl.extension.item;

import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.block.custom.ModHangingSignBlock;
import net.theobl.extension.entity.custom.ModBoatEntity;
import net.theobl.extension.item.custom.ModBoatItem;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Extension.MOD_ID);

    public static final RegistryObject<Item> RED_NETHER_BRICK = ITEMS.register("red_nether_brick",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_NETHER_BRICK = ITEMS.register("blue_nether_brick",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> VIBRANT_RED_DYE = ITEMS.register("vibrant_red_dye",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DULL_ORANGE_DYE = ITEMS.register("dull_orange_dye",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRIGHT_YELLOW_DYE = ITEMS.register("bright_yellow_dye",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHARTREUSE_DYE = ITEMS.register("chartreuse_dye",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VIBRANT_GREEN_DYE = ITEMS.register("vibrant_green_dye",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPRING_GREEN_DYE = ITEMS.register("spring_green_dye",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRIGHT_CYAN_DYE = ITEMS.register("bright_cyan_dye",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CAPRI_DYE = ITEMS.register("capri_dye",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ULTRAMARINE_DYE = ITEMS.register("ultramarine_dye",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VIOLET_DYE = ITEMS.register("violet_dye",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VIBRANT_PURPLE_DYE = ITEMS.register("vibrant_purple_dye",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRIGHT_MAGENTA_DYE = ITEMS.register("bright_magenta_dye",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROSE_DYE = ITEMS.register("rose_dye",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DARK_GRAY_DYE = ITEMS.register("dark_gray_dye",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_DYE = ITEMS.register("silver_dye",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALPHA_WHITE_DYE = ITEMS.register("alpha_white_dye",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NETHERITE_HORSE_ARMOR = ITEMS.register("netherite_horse_armor",
            () -> new HorseArmorItem(15, "netherite", (new Item.Properties().stacksTo(1).fireResistant())));

    public static final List<RegistryObject<Item>> COLORED_SIGNS = registerColoredSign();
    private static List<RegistryObject<Item>> registerColoredSign(){
        List<RegistryObject<Item>> hangingSign = new ArrayList<>();
        for (DyeColor color : ModBlocks.COLORS) {
            int index = ModBlocks.COLORS.indexOf(color);
            RegistryObject<Item> item = ITEMS.register(color.getName() + "_sign",
                    () -> new SignItem(new Item.Properties().stacksTo(16),
                            ModBlocks.COLORED_SIGNS.get(index).get(), ModBlocks.COLORED_WALL_SIGNS.get(index).get()));
            hangingSign.add(item);
        }
        return hangingSign;
    }

    public static final List<RegistryObject<Item>> COLORED_HANGING_SIGNS = registerColoredHangingSign();
    private static List<RegistryObject<Item>> registerColoredHangingSign(){
        List<RegistryObject<Item>> hangingSign = new ArrayList<>();
        for (DyeColor color : ModBlocks.COLORS) {
            int index = ModBlocks.COLORS.indexOf(color);
            RegistryObject<Item> item = ITEMS.register(color.getName() + "_hanging_sign",
                    () -> new HangingSignItem(ModBlocks.COLORED_HANGING_SIGNS.get(index).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(index).get(),
                            new Item.Properties().stacksTo(16)));
            hangingSign.add(item);
        }
        return hangingSign;
    }

    public static final List<RegistryObject<Item>> COLORED_BOATS = registerColoredBoats(false);
    public static final List<RegistryObject<Item>> COLORED_CHEST_BOATS = registerColoredBoats(true);
    private static List<RegistryObject<Item>> registerColoredBoats(boolean hasChest){
        List<RegistryObject<Item>> boat = new ArrayList<>();
        String suffix;
        for (DyeColor color : ModBlocks.COLORS) {
            int index = ModBlocks.COLORS.indexOf(color);
            RegistryObject<Item> item = ITEMS.register(color.getName() + (hasChest ? "_chest_boat" : "_boat"),
                    () -> new ModBoatItem(hasChest, ModBoatEntity.Type.byId(index), new Item.Properties().stacksTo(1)));
            boat.add(item);
        }
        return boat;
    }

//    public static final RegistryObject<Item> WHITE_SIGN = ITEMS.register("white_sign",
//            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.WHITE_SIGN.get(), ModBlocks.WHITE_WALL_SIGN.get()));
//    public static final RegistryObject<Item> WHITE_HANGING_SIGN = ITEMS.register("white_hanging_sign",
//            () -> new HangingSignItem(ModBlocks.WHITE_HANGING_SIGN.get(), ModBlocks.WHITE_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> CRIMSON_BOAT = ITEMS.register("crimson_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.CRIMSON, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CRIMSON_CHEST_BOAT = ITEMS.register("crimson_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.CRIMSON, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> WARPED_BOAT = ITEMS.register("warped_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.WARPED, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> WARPED_CHEST_BOAT = ITEMS.register("warped_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.WARPED, new Item.Properties().stacksTo(1)));

//    public static final RegistryObject<Item> WHITE_BOAT = ITEMS.register("white_boat",
//            () -> new ModBoatItem(false, ModBoatEntity.Type.WHITE, new Item.Properties().stacksTo(1)));
//    public static final RegistryObject<Item> WHITE_CHEST_BOAT = ITEMS.register("white_chest_boat",
//            () -> new ModBoatItem(true, ModBoatEntity.Type.WHITE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SPAWNER_MINECART = ITEMS.register("spawner_minecart",
            () -> new MinecartItem(AbstractMinecart.Type.SPAWNER, new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
