package net.theobl.extension;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.block.ModVanillaCompat;
import net.theobl.extension.entity.ModBlockEntities;
import net.theobl.extension.entity.ModEntities;
import net.theobl.extension.entity.client.ModBoatRenderer;
import net.theobl.extension.item.ModCreativeModeTabs;
import net.theobl.extension.item.ModItems;
import net.theobl.extension.util.ModWoodTypes;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Extension.MOD_ID)
public class Extension
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "extension";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();


    public Extension()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEntities.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(ModVanillaCompat::registerItemInVanillaTabs);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        ModVanillaCompat.setup();
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
//        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
//            event.accept(ModItems.CRIMSON_BOAT.get());
//            event.accept(ModItems.CRIMSON_CHEST_BOAT.get());
//            event.accept(ModItems.WARPED_BOAT.get());
//            event.accept(ModItems.WARPED_CHEST_BOAT.get());
//        }
        if (event.getTabKey() == CreativeModeTabs.OP_BLOCKS) {
            event.accept(Items.KNOWLEDGE_BOOK);
            event.accept(Items.ENDER_DRAGON_SPAWN_EGG);
            event.accept(Items.WITHER_SPAWN_EGG);
            //event.accept(ModBlocks.RED_ANTIBLOCK.get().asItem());
//            for (RegistryObject<Block> antiblock : ModBlocks.ANTIBLOCKS){
//                event.accept(antiblock);
//            }
        }
    }

    @SubscribeEvent
    public void onEntityUpdate(EntityEvent.EntityConstructing event) {
        if (event.getEntity() instanceof Boat boat) {
            boat.setMaxUpStep(1.1F); // Allow stepping up one full block
        }
    }

    @SubscribeEvent
    public void addPackFinders(AddPackFindersEvent event)
    {
        if (event.getPackType() == PackType.CLIENT_RESOURCES)
        {
            var resourcePath = ModList.get().getModFileById(MOD_ID).getFile().findResource("resourcepacks/sandstone_wall_stairs_fix");
            var pack = Pack.readMetaAndCreate("builtin/extension", Component.literal("Sandstone Wall and Stairs fix"), false,
                    (path) -> new PathPackResources(path, resourcePath, false), PackType.CLIENT_RESOURCES, Pack.Position.BOTTOM, PackSource.DEFAULT);
            event.addRepositorySource((packConsumer) -> packConsumer.accept(pack));
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            Sheets.addWoodType(ModWoodTypes.WHITE);
            Sheets.addWoodType(ModWoodTypes.LIGHT_GRAY);
            Sheets.addWoodType(ModWoodTypes.GRAY);
            Sheets.addWoodType(ModWoodTypes.BLACK);
            Sheets.addWoodType(ModWoodTypes.BROWN);
            Sheets.addWoodType(ModWoodTypes.RED);
            Sheets.addWoodType(ModWoodTypes.ORANGE);
            Sheets.addWoodType(ModWoodTypes.YELLOW);
            Sheets.addWoodType(ModWoodTypes.LIME);
            Sheets.addWoodType(ModWoodTypes.GREEN);
            Sheets.addWoodType(ModWoodTypes.CYAN);
            Sheets.addWoodType(ModWoodTypes.LIGHT_BLUE);
            Sheets.addWoodType(ModWoodTypes.BLUE);
            Sheets.addWoodType(ModWoodTypes.PURPLE);
            Sheets.addWoodType(ModWoodTypes.MAGENTA);
            Sheets.addWoodType(ModWoodTypes.PINK);

            EntityRenderers.register(ModEntities.MOD_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
            EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));

            ItemBlockRenderTypes.setRenderLayer(Fluids.LAVA, RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(Blocks.WATER_CAULDRON, RenderType.translucent());
        }
    }
}
