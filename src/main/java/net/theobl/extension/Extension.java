package net.theobl.extension;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.block.entity.ModBlockEntities;
import net.theobl.extension.block.entity.RedstoneCampfireBlockEntityRenderer;
import net.theobl.extension.item.ModItems;
import org.slf4j.Logger;

import static net.minecraft.world.item.CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Extension.MODID)
public class Extension {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "extension";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "extension" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "extension" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "extension" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a new BlockItem with the id "extension:example_block", combining the namespace and path
//    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", ModBlocks.EXAMPLE_BLOCK);

    // Creates a creative tab with the id "extension:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXTENSION_TAB = CREATIVE_MODE_TABS.register("extension_tab",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.extension"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> ModItems.SPAWNER_MINECART.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        //output.accept(ModItems.EXAMPLE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
                        for (DeferredHolder<Item, ? extends Item> deferredItem : ModItems.ITEMS.getEntries()) {
                            if(deferredItem.get() instanceof BlockItem && !(deferredItem.get() instanceof ItemNameBlockItem))
                                output.accept(deferredItem.get());
                        }
                        for (DeferredHolder<Item, ? extends Item> deferredItem : ModItems.ITEMS.getEntries()) {
                            if(!(deferredItem.get() instanceof BlockItem))
                                output.accept(deferredItem.get());
                        }
                        output.accept(ModItems.BLUE_NETHER_WART);
                    }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Extension(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        ModBlocks.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ModItems.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Extension) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        //if (Config.logDirtBlock) LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        //LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        //Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
//            event.accept(ModBlocks.EXAMPLE_BLOCK);
            event.insertAfter(Items.SMOOTH_STONE.getDefaultInstance(), ModBlocks.SMOOTH_STONE_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.NETHERITE_BLOCK.getDefaultInstance(), ModBlocks.NETHERITE_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.STONE_SLAB.getDefaultInstance(), ModBlocks.STONE_WALL.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.POLISHED_GRANITE_SLAB.getDefaultInstance(), ModBlocks.POLISHED_GRANITE_WALL.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.POLISHED_DIORITE_SLAB.getDefaultInstance(), ModBlocks.POLISHED_DIORITE_WALL.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.POLISHED_ANDESITE_SLAB.getDefaultInstance(), ModBlocks.POLISHED_ANDESITE_WALL.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.PRISMARINE_BRICK_SLAB.getDefaultInstance(), ModBlocks.PRISMARINE_BRICK_WALL.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.DARK_PRISMARINE_SLAB.getDefaultInstance(), ModBlocks.DARK_PRISMARINE_WALL.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.PURPUR_SLAB.getDefaultInstance(), ModBlocks.PURPUR_WALL.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.QUARTZ_SLAB.getDefaultInstance(), ModBlocks.QUARTZ_WALL.toStack(), PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.SMOOTH_BASALT.getDefaultInstance(), ModBlocks.SMOOTH_BASALT_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.SMOOTH_BASALT_STAIRS.toStack(), ModBlocks.SMOOTH_BASALT_SLAB.toStack(), PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.QUARTZ_BRICKS.getDefaultInstance(), ModBlocks.QUARTZ_BRICK_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.QUARTZ_BRICK_STAIRS.toStack(), ModBlocks.QUARTZ_BRICK_SLAB.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.QUARTZ_BRICK_SLAB.toStack(), ModBlocks.QUARTZ_BRICK_WALL.toStack(), PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.RED_NETHER_BRICKS.getDefaultInstance(), ModBlocks.CRACKED_RED_NETHER_BRICKS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.RED_NETHER_BRICK_WALL.getDefaultInstance(), ModBlocks.RED_NETHER_BRICK_FENCE.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.RED_NETHER_BRICK_FENCE.toStack(), ModBlocks.CHISELED_RED_NETHER_BRICKS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.CHISELED_RED_NETHER_BRICKS.toStack(), ModBlocks.BLUE_NETHER_BRICKS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.BLUE_NETHER_BRICKS.toStack(), ModBlocks.CRACKED_BLUE_NETHER_BRICKS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.CRACKED_BLUE_NETHER_BRICKS.toStack(), ModBlocks.BLUE_NETHER_BRICK_STAIRS.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.BLUE_NETHER_BRICK_STAIRS.toStack(), ModBlocks.BLUE_NETHER_BRICK_SLAB.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.BLUE_NETHER_BRICK_SLAB.toStack(), ModBlocks.BLUE_NETHER_BRICK_WALL.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.BLUE_NETHER_BRICK_WALL.toStack(), ModBlocks.BLUE_NETHER_BRICK_FENCE.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.BLUE_NETHER_BRICK_FENCE.toStack(), ModBlocks.CHISELED_BLUE_NETHER_BRICKS.toStack(), PARENT_AND_SEARCH_TABS);
            Item previous = Items.CUT_RED_SANDSTONE_SLAB;
            for (DeferredHolder<Block, ? extends Block> entry : ModBlocks.BLOCKS.getEntries()) {
                if (entry.get().toString().contains("soul_sandstone")) {
                    event.insertAfter(previous.getDefaultInstance(), entry.get().asItem().getDefaultInstance(), PARENT_AND_SEARCH_TABS);
                    previous = entry.get().asItem();
                }
            }
        }
        if (event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS)
            event.insertAfter(Items.GLASS_PANE.getDefaultInstance(), ModBlocks.TINTED_GLASS_PANE.toStack(), PARENT_AND_SEARCH_TABS);
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.insertAfter(Items.JACK_O_LANTERN.getDefaultInstance(), ModBlocks.SOUL_O_LANTERN.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.SOUL_O_LANTERN.toStack(), ModBlocks.REDSTONE_O_LANTERN.toStack(), PARENT_AND_SEARCH_TABS);
            }
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.insertAfter(Items.SOUL_LANTERN.getDefaultInstance(), ModBlocks.REDSTONE_LANTERN.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.SOUL_CAMPFIRE.getDefaultInstance(), ModBlocks.REDSTONE_CAMPFIRE.toStack(), PARENT_AND_SEARCH_TABS);
        }
        if(event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.insertAfter(Items.REDSTONE_TORCH.getDefaultInstance(), ModBlocks.REDSTONE_LANTERN.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.REDSTONE_LANTERN.toStack(), ModBlocks.REDSTONE_CAMPFIRE.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModBlocks.REDSTONE_CAMPFIRE.toStack(), ModBlocks.REDSTONE_O_LANTERN.toStack(), PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
            event.insertAfter(Items.TNT_MINECART.getDefaultInstance(), ModItems.SPAWNER_MINECART.toStack(), PARENT_AND_SEARCH_TABS);
        if (event.getTabKey() == CreativeModeTabs.COMBAT)
            event.insertAfter(Items.DIAMOND_HORSE_ARMOR.getDefaultInstance(), ModItems.NETHERITE_HORSE_ARMOR.toStack(), PARENT_AND_SEARCH_TABS);
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.insertAfter(Items.NETHER_BRICK.getDefaultInstance(), ModItems.RED_NETHER_BRICK.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.RED_NETHER_BRICK.toStack(), ModItems.BLUE_NETHER_BRICK.toStack(), PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.NETHER_WART.getDefaultInstance(), ModItems.BLUE_NETHER_WART.toStack(), PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS)
            event.insertAfter(new ItemStack(Items.HUSK_SPAWN_EGG), new ItemStack(ModItems.ILLUSIONER_SPAWN_EGG.asItem()), PARENT_AND_SEARCH_TABS);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TINTED_GLASS_PANE.get(), RenderType.TRANSLUCENT);
        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.REDSTONE_CAMPFIRE.get(), RedstoneCampfireBlockEntityRenderer::new);
        }
    }
}