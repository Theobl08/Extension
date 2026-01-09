package net.theobl.extension;

import com.mojang.datafixers.util.Either;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RenderBlockScreenEffectEvent;
import net.neoforged.neoforge.client.extensions.common.IClientBlockExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.world.poi.ExtendPoiTypesEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.RegisterCauldronFluidContentEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.theobl.extension.block.ExtendedCauldronInteraction;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.inventory.FletchingMenu;
import net.theobl.extension.inventory.FletchingScreen;
import net.theobl.extension.inventory.ModMenuType;
import net.theobl.extension.item.ModCreativeModeTabs;
import net.theobl.extension.item.ModItems;
import net.theobl.extension.item.crafting.ModRecipeSerializer;
import net.theobl.extension.item.crafting.ModRecipeType;
import net.theobl.extension.stats.ModStats;
import org.slf4j.Logger;

import java.util.Optional;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Extension.MODID)
public class Extension {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "extension";

    public static final double BRIGHTNESS_MIN = -1;
    public static final double BRIGHTNESS_MAX = 12;
    public static final double BRIGHTNESS_STEP = 0.05;
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    private static boolean gamerule_fire_damage = GameRules.FIRE_DAMAGE.defaultValue();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Extension(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        NeoForgeMod.enableMilkFluid(); // We need this for our milk cauldron
        ExtendedCauldronInteraction.init();

        // Register the Deferred Register to the mod event bus so blocks get registered
        ModBlocks.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ModItems.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        ModCreativeModeTabs.register(modEventBus);

        ModMenuType.register(modEventBus);
        ModRecipeType.register(modEventBus);
        ModRecipeSerializer.register(modEventBus);
        ModStats.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Extension) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(ModCreativeModeTabs::addCreative);
        modEventBus.addListener(this::addBlockToBlockEntity);
        modEventBus.addListener(this::extendPoiTypes);
        modEventBus.addListener(this::registerCauldronFluidContent);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        //LOGGER.info("HELLO FROM COMMON SETUP");
        ExtendedCauldronInteraction.bootStrap();
        // For custom stats: add this line if the formatting is different from StatFormatter.DEFAULT to properly display it with the correct unit
        // Stats.CUSTOM.get(ModStats.INTERACT_WITH_FLETCHING_TABLE, StatFormatter.TIME);
    }

    private void addBlockToBlockEntity(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.CAMPFIRE, ModBlocks.REDSTONE_CAMPFIRE.get());
        event.modify(BlockEntityType.CAMPFIRE, ModBlocks.COPPER_CAMPFIRE.get());
    }

    private void extendPoiTypes(ExtendPoiTypesEvent event) {
        event.addBlockToPoi(PoiTypes.LEATHERWORKER, ModBlocks.MILK_CAULDRON.get());
    }

    private void registerCauldronFluidContent(RegisterCauldronFluidContentEvent event) {
        event.register(ModBlocks.MILK_CAULDRON.get(), NeoForgeMod.MILK.get(), FluidType.BUCKET_VOLUME, null);
    }

    @SubscribeEvent
    public void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        // Gets the builder to add recipes to
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(Potions.MUNDANE, ModItems.BLUE_NETHER_WART.asItem(), Potions.AWKWARD);
        builder.addMix(Potions.AWKWARD, Items.RABBIT_HIDE, Potions.LUCK);
        ItemStack stack = new ItemStack(Items.POTION);
        stack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.AWKWARD));
        builder.addRecipe(Ingredient.of(Items.POTION), Ingredient.of(ModItems.BLUE_NETHER_WART), stack);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void useItemOnBlock(UseItemOnBlockEvent event) {
        if(event.getLevel().getBlockState(event.getPos()).getBlock() == Blocks.FLETCHING_TABLE && event.getPlayer() != null) {
            if(event.getUsePhase() == UseItemOnBlockEvent.UsePhase.BLOCK) {
                Player player = event.getPlayer();
                Level level = event.getLevel();
                BlockPos pos = event.getPos();
                if (!level.isClientSide()) {
                    MenuProvider provider = new SimpleMenuProvider(
                            (i, inventory, player1) -> new FletchingMenu(i, inventory, ContainerLevelAccess.create(level, pos)), Component.translatable("container.fletching"));
                    player.openMenu(provider);
                    player.awardStat(ModStats.INTERACT_WITH_FLETCHING_TABLE);
                }
                event.cancelWithResult(InteractionResult.SUCCESS);
            }
        }
    }

    @SubscribeEvent
    public void onFarmlandTrample(BlockEvent.FarmlandTrampleEvent event) {
        if(!Config.farmlandTrample)
            event.setCanceled(true);
        else
            event.setCanceled(false);
    }

    @SubscribeEvent
    public void levelTick(LevelTickEvent.Pre event) {
        if(event.getLevel() instanceof ServerLevel serverLevel) {
            gamerule_fire_damage = serverLevel.getGameRules().get(GameRules.FIRE_DAMAGE);
        }
    }

    @SubscribeEvent
    public void entityInvulnerabilityCheck(EntityInvulnerabilityCheckEvent event) {
        Entity entity = event.getEntity();
        if(entity instanceof ServerPlayer player &&
                event.getSource().is(DamageTypeTags.IS_FIRE) &&
                !player.level().getGameRules().get(GameRules.FIRE_DAMAGE)) {
            event.setInvulnerable(true);
        }
    }

    @SubscribeEvent
    public void datapackSync(OnDatapackSyncEvent event) {
        event.sendRecipes(ModRecipeType.FLETCHING.get());
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

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
//            event.register((state, level, pos, tintIndex) -> {
//                if(level != null && pos != null && level.getBlockState(pos).is(ModBlocks.FIRE_RESISTANCE_CAULDRON.get())) {
//                    return PotionContents.getColorOptional(ModBlocks.FIRE_RESISTANCE_CAULDRON.get().getPotion().value().getEffects()).orElse(PotionContents.BASE_POTION_COLOR);
//                }
//                else {
//                    return -1;
//                }
//            }, ModBlocks.FIRE_RESISTANCE_CAULDRON.get());
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
            } else if(!gamerule_fire_damage && Config.noFireOverlay && blockState.is(Blocks.FIRE))
                event.setCanceled(true);
        }
    }

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