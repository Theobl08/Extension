package net.theobl.extension;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RenderBlockScreenEffectEvent;
import net.neoforged.neoforge.client.extensions.common.IClientBlockExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.compat.jei.ExtensionJeiPlugin;
import net.theobl.extension.inventory.FletchingScreen;
import net.theobl.extension.inventory.ModMenuType;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = Extension.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = Extension.MODID, value = Dist.CLIENT)
public class ExtensionClient {
    public ExtensionClient(ModContainer modContainer) {
        // This will use NeoForge's ConfigurationScreen to display this mod's configs
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        if(ModList.get().isLoaded("jei")) {
            NeoForge.EVENT_BUS.addListener(ExtensionJeiPlugin::recipesReceived);
            NeoForge.EVENT_BUS.addListener(ExtensionJeiPlugin::clientLogOut);
        }
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TINTED_GLASS_PANE.get(), ChunkSectionLayer.TRANSLUCENT);
    }

    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuType.FLETCHING.get(), FletchingScreen::new);
    }

    @SubscribeEvent
    public static void registerClientExtension(RegisterClientExtensionsEvent event) {
        // Fix https://bugs.mojang.com/browse/MC/issues/MC-132734
        if(!event.isBlockRegistered(Blocks.WATER_CAULDRON)) {
            event.registerBlock(new IClientBlockExtensions() {
                @Override
                public boolean areBreakingParticlesTinted(BlockState state, ClientLevel level, BlockPos pos) {
                    return false;
                }
            }, Blocks.WATER_CAULDRON);
        }
        event.registerBlock(new IClientBlockExtensions() {
            @Override
            public boolean areBreakingParticlesTinted(BlockState state, ClientLevel level, BlockPos pos) {
                return false;
            }
        }, ModBlocks.POTION_CAULDRON.values().stream().map(DeferredBlock::get).toArray(Block[]::new));
    }

    @SubscribeEvent
    public static void registerColorHandlers(RegisterColorHandlersEvent.Block event) {
        ModBlocks.POTION_CAULDRON.values().forEach(block -> {
            event.register(((state, level, pos, tintIndex) -> {
                if(level != null && pos != null && level.getBlockState(pos).is(block.get())) {
                    return PotionContents.getColorOptional(block.get().getPotion().value().getEffects()).orElse(PotionContents.BASE_POTION_COLOR);
                }
                else {
                    return -1;
                }
            }), block.get());
        });
    }

    @SubscribeEvent
    public static void renderBlockScreenEffect(RenderBlockScreenEffectEvent event) {
        Player player = event.getPlayer();
        BlockState blockState = event.getBlockState();
        MobEffectInstance mobEffectInstance = player.getEffect(MobEffects.FIRE_RESISTANCE);
        if(player.isCreative()) {
            event.setCanceled(true);
        } else if (Config.noFireOverlay &&
                player.hasEffect(MobEffects.FIRE_RESISTANCE) &&
                mobEffectInstance != null && !mobEffectInstance.endsWithin(400) &&
                !player.isHolding(Items.MILK_BUCKET) &&
                blockState == Blocks.FIRE.defaultBlockState()) {
            event.setCanceled(true);
        } else if(!Extension.gamerule_fire_damage && Config.noFireOverlay && blockState.is(Blocks.FIRE))
            event.setCanceled(true);
    }
}
