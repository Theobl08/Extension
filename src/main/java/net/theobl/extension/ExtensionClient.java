package net.theobl.extension;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
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
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.compat.jei.ExtensionJeiPlugin;
import net.theobl.extension.inventory.FletchingScreen;
import net.theobl.extension.inventory.ModMenuType;

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
        ModBlocks.POTION_CAULDRON.values().forEach(block -> {
            event.register(List.of(new BlockTintSource() {
                @Override
                public int color(BlockState state) {
                    return -1;
                }

                @Override
                public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
                    return PotionContents.getColorOptional(block.get().getPotion().value().getEffects()).orElse(PotionContents.BASE_POTION_COLOR);
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
