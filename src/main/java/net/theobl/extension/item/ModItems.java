package net.theobl.extension.item;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.entity.ModEntityType;

public class ModItems {
    // Create a Deferred Register to hold Items which will all be registered under the "extension" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Extension.MODID);

    public static final DeferredItem<Item> SPAWNER_MINECART = ITEMS.registerItem("spawner_minecart",
            properties -> new MinecartItem(EntityType.SPAWNER_MINECART, properties.stacksTo(1)));

    public static final DeferredItem<Item> RED_NETHER_BRICK = ITEMS.registerSimpleItem("red_nether_brick");

    public static final DeferredItem<Item> BLUE_NETHER_BRICK = ITEMS.registerSimpleItem("blue_nether_brick");

    public static final DeferredItem<Item> BLUE_NETHER_WART = ITEMS.registerItem("blue_nether_wart",
            properties -> new BlockItem(ModBlocks.BLUE_NETHER_WART.get(), properties));

    public static final DeferredItem<Item> ENDER_TORCH = ITEMS.registerItem("ender_torch",
            p -> new StandingAndWallBlockItem(ModBlocks.ENDER_TORCH.get(), ModBlocks.ENDER_WALL_TORCH.get(), Direction.DOWN, p),
            Item.Properties::useBlockDescriptionPrefix);

    public static final DeferredItem<Item> ILLUSIONER_SPAWN_EGG = ITEMS.registerItem("illusioner_spawn_egg",
            SpawnEggItem::new, p -> p.spawnEgg(EntityType.ILLUSIONER));

    public static final DeferredItem<Item> POTATO_SIGN = ITEMS.registerItem(
            "potato_sign", p -> new SignItem(ModBlocks.POTATO_SIGN.get(), ModBlocks.POTATO_WALL_SIGN.get(), p.stacksTo(23).useBlockDescriptionPrefix())
    );
    public static final DeferredItem<Item> POTATO_HANGING_SIGN = ITEMS.registerItem(
            "potato_hanging_sign", p -> new HangingSignItem(ModBlocks.POTATO_HANGING_SIGN.get(), ModBlocks.POTATO_WALL_HANGING_SIGN.get(), p.stacksTo(13).useBlockDescriptionPrefix())
    );
    public static final DeferredItem<Item> POTATO_RAFT = ITEMS.registerItem("potato_raft", p -> new BoatItem(ModEntityType.POTATO_RAFT.get(), p), p -> p.stacksTo(1));
    public static final DeferredItem<Item> POTATO_CHEST_RAFT = ITEMS.registerItem(
            "potato_chest_raft", p -> new BoatItem(ModEntityType.POTATO_CHEST_RAFT.get(), p), p -> p.stacksTo(1)
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
