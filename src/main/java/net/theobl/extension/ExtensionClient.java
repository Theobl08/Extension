package net.theobl.extension;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.model.object.boat.RaftModel;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.renderer.entity.RaftRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.block.entity.ModBlockEntityType;
import net.theobl.extension.client.ModModelLayers;
import net.theobl.extension.client.renderer.blockentity.PotionCauldronRenderer;
import net.theobl.extension.compat.jei.ExtensionJeiPlugin;
import net.theobl.extension.entity.ModEntityType;
import net.theobl.extension.inventory.FletchingScreen;
import net.theobl.extension.inventory.ModMenuType;
import net.theobl.extension.particles.ModParticleTypes;

import java.util.List;
import java.util.Optional;

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
    }

    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuType.FLETCHING.get(), FletchingScreen::new);
    }

    @SubscribeEvent
    public static void registerClientExtension(RegisterClientExtensionsEvent event) {
        // Fix https://bugs.mojang.com/browse/MC/issues/MC-132734
    }

    @SubscribeEvent
    public static void registerColorHandlers(RegisterColorHandlersEvent.BlockTintSources event) {
        event.register(List.of(BlockTintSources.foliage()), ModBlocks.POTATO_LEAVES.get());
        event.register(List.of(BlockTintSources.grassBlock()), Blocks.MOSS_BLOCK);
    }

    @SubscribeEvent
    public static void addPackFinders(AddPackFindersEvent event) {
        event.addPackFinders(Extension.asResource("resourcepacks/biome_tinted_moss_blocks"), PackType.CLIENT_RESOURCES,
                Component.literal("Biome Tinted Moss Blocks"), PackSource.DEFAULT, false, Pack.Position.TOP);
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

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.ENDER_FIRE_FLAME.get(), FlameParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.POTATO_RAFT, RaftModel::createRaftModel);
        event.registerLayerDefinition(ModModelLayers.POTATO_CHEST_RAFT, RaftModel::createChestRaftModel);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityType.POTATO_RAFT.get(), context -> new RaftRenderer(context, ModModelLayers.POTATO_RAFT));
        event.registerEntityRenderer(ModEntityType.POTATO_CHEST_RAFT.get(), context -> new RaftRenderer(context, ModModelLayers.POTATO_CHEST_RAFT));
        event.registerBlockEntityRenderer(ModBlockEntityType.POTION_CAULDRON.get(), context -> new PotionCauldronRenderer());
    }

    public static final double BRIGHTNESS_MIN = -1;
    public static final double BRIGHTNESS_MAX = 12;
    public static final double BRIGHTNESS_STEP = 0.05;

    public enum BetterSlider implements OptionInstance.SliderableValueSet<Double> {
        INSTANCE;

        @Override
        public double toSliderValue(Double value) {
            return (value - BRIGHTNESS_MIN) / (BRIGHTNESS_MAX - BRIGHTNESS_MIN);
        }

        @Override
        public Double fromSliderValue(double value) {
            return value * (BRIGHTNESS_MAX - BRIGHTNESS_MIN) + BRIGHTNESS_MIN;
        }

        @Override
        public Optional<Double> validateValue(Double value) {
            return value >= BRIGHTNESS_MIN && value <= BRIGHTNESS_MAX ? Optional.of(value) : Optional.empty();
        }

        @Override
        public Codec<Double> codec() {
            return Codec.either(Codec.doubleRange(BRIGHTNESS_MIN, BRIGHTNESS_MAX), Codec.BOOL)
                    .xmap(either -> either.map(value -> value, value -> value ? 1.0 : 0.0), Either::left);
        }
    }
}
