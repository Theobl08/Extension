package net.theobl.extension.item;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Extension.MODID);

    // Creates a new food item with the id "extension:example_id", nutrition 1 and saturation 2
//    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item",
//            new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(2f).build()));


    public static final DeferredItem<Item> SPAWNER_MINECART = ITEMS.register("spawner_minecart",
            () -> new MinecartItem(AbstractMinecart.Type.SPAWNER, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> NETHERITE_HORSE_ARMOR = ITEMS.register("netherite_horse_armor",
            () -> new AnimalArmorItem(ArmorMaterials.NETHERITE, AnimalArmorItem.BodyType.EQUESTRIAN, false, (new Item.Properties().stacksTo(1).fireResistant())));

    public static final DeferredItem<Item> RED_NETHER_BRICK = ITEMS.register("red_nether_brick",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BLUE_NETHER_BRICK = ITEMS.register("blue_nether_brick",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BLUE_NETHER_WART = ITEMS.register("blue_nether_wart",
            () -> new ItemNameBlockItem(ModBlocks.BLUE_NETHER_WART.get(), new Item.Properties()));

    public static final DeferredItem<Item> ILLUSIONER_SPAWN_EGG = ITEMS.register("illusioner_spawn_egg",
            () -> new DeferredSpawnEggItem(() -> EntityType.ILLUSIONER, 9804699, 1267859, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
