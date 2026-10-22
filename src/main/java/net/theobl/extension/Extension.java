package net.theobl.extension;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gamerules.GameRules;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.tooltip.TooltipLocation;
import net.neoforged.neoforge.common.world.poi.ExtendPoiTypesEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.RegisterTooltipAppendersEvent;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.RegisterCauldronFluidContentEvent;
import net.theobl.extension.block.FourLayeredCauldronBlock;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.block.entity.ModBlockEntityType;
import net.theobl.extension.commands.HeldItemNameCommand;
import net.theobl.extension.entity.ModEntityType;
import net.theobl.extension.fluid.ModFluidTypes;
import net.theobl.extension.fluid.ModFluids;
import net.theobl.extension.gamerules.ModGameRules;
import net.theobl.extension.inventory.FletchingMenu;
import net.theobl.extension.inventory.ModMenuType;
import net.theobl.extension.item.ModCreativeModeTabs;
import net.theobl.extension.item.ModItems;
import net.theobl.extension.item.alchemy.ModPotions;
import net.theobl.extension.item.crafting.ModRecipeSerializer;
import net.theobl.extension.item.crafting.ModRecipeType;
import net.theobl.extension.loot.ModLootModifiers;
import net.theobl.extension.particles.ModParticleTypes;
import net.theobl.extension.stats.ModStats;
import net.theobl.extension.util.ModUtil;
import net.theobl.extension.worldgen.feature.treedecorators.ModTreeDecoratorType;
import net.theobl.extension.worldgen.feature.trunkplacers.ModTrunkPlacerType;
import org.slf4j.Logger;

import java.util.List;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Extension.MODID)
public class Extension {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "extension";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    static boolean gamerule_fire_damage = GameRules.FIRE_DAMAGE.defaultValue();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Extension(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ModPotions.register(modEventBus);

        NeoForgeMod.enableMilkFluid(); // We need this for our milk cauldron

        // Register the Deferred Register to the mod event bus so blocks get registered
        ModBlocks.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ModItems.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        ModCreativeModeTabs.register(modEventBus);

        ModFluidTypes.register(modEventBus);
        ModFluids.register(modEventBus);

        ModMenuType.register(modEventBus);
        ModRecipeType.register(modEventBus);
        ModRecipeSerializer.register(modEventBus);
        ModStats.register(modEventBus);
        ModGameRules.register(modEventBus);

        ModParticleTypes.register(modEventBus);

        ModBlockEntityType.register(modEventBus);
        ModEntityType.register(modEventBus);
        ModTreeDecoratorType.register(modEventBus);
        ModTrunkPlacerType.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Extension) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(ModCreativeModeTabs::addCreative);
        modEventBus.addListener(this::addBlockToBlockEntity);
        modEventBus.addListener(this::extendPoiTypes);
        modEventBus.addListener(this::registerCauldronFluidContent);
        modEventBus.addListener(this::registerTooltipAppenders);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.LOCAL, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        //LOGGER.info("HELLO FROM COMMON SETUP");
        event.enqueueWork(ModUtil::bootstrap);
        // For custom stats: add this line if the formatting is different from StatFormatter.DEFAULT to properly display it with the correct unit
        // Stats.CUSTOM.get(ModStats.INTERACT_WITH_FLETCHING_TABLE, StatFormatter.TIME);
    }

    private void addBlockToBlockEntity(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityTypes.CAMPFIRE, ModBlocks.REDSTONE_CAMPFIRE.get());
        event.modify(BlockEntityTypes.CAMPFIRE, ModBlocks.COPPER_CAMPFIRE.get());
        event.modify(BlockEntityTypes.CAMPFIRE, ModBlocks.ENDER_CAMPFIRE.get());
        event.modify(BlockEntityTypes.SHELF, ModBlocks.POTATO_SHELF.get());
        event.modify(BlockEntityTypes.SIGN, ModBlocks.POTATO_SIGN.get(), ModBlocks.POTATO_WALL_SIGN.get());
        event.modify(BlockEntityTypes.HANGING_SIGN, ModBlocks.POTATO_HANGING_SIGN.get(), ModBlocks.POTATO_WALL_HANGING_SIGN.get());
        event.modify(BlockEntityTypes.ENCHANTING_TABLE, ModBlocks.CRYING_ENCHANTING_TABLE.get());
        ModBlocks.BEEHIVES.values().forEach(block -> event.modify(BlockEntityTypes.BEEHIVE, block.get()));
        ModBlocks.CHISELED_BOOKSHELVES.values().forEach(block -> event.modify(BlockEntityTypes.CHISELED_BOOKSHELF, block.get()));
        ModBlocks.LECTERNS.values().forEach(block -> event.modify(BlockEntityTypes.LECTERN, block.get()));
        ModBlocks.BARRELS.values().forEach(block -> event.modify(BlockEntityTypes.BARREL, block.get()));
        ModBlocks.CRAFTERS.values().forEach(block -> event.modify(BlockEntityTypes.CRAFTER, block.get()));
    }

    private void extendPoiTypes(ExtendPoiTypesEvent event) {
        event.addBlockToPoi(PoiTypes.LEATHERWORKER, ModBlocks.MILK_CAULDRON.get());
        event.addBlockToPoi(PoiTypes.LEATHERWORKER, ModBlocks.BEETROOT_SOUP_CAULDRON.get());
        event.addBlockToPoi(PoiTypes.LEATHERWORKER, ModBlocks.MUSHROOM_STEW_CAULDRON.get());
        ModBlocks.CARTOGRAPHY_TABLES.values().forEach(block -> event.addBlockToPoi(PoiTypes.CARTOGRAPHER, block.get()));
        ModBlocks.FLETCHING_TABLES.values().forEach(block -> event.addBlockToPoi(PoiTypes.FLETCHER, block.get()));
        ModBlocks.BEEHIVES.values().forEach(block -> event.addBlockToPoi(PoiTypes.BEEHIVE, block.get()));
        ModBlocks.LECTERNS.values().forEach(block -> event.addBlockToPoi(PoiTypes.LIBRARIAN, block.get()));
        ModBlocks.BARRELS.values().forEach(block -> event.addBlockToPoi(PoiTypes.FISHERMAN, block.get()));
    }

    private void registerCauldronFluidContent(RegisterCauldronFluidContentEvent event) {
        event.register(ModBlocks.MILK_CAULDRON.get(), NeoForgeMod.MILK.get(), FluidType.BUCKET_VOLUME, null);
        event.register(ModBlocks.BEETROOT_SOUP_CAULDRON.get(), ModFluids.BEETROOT_SOUP.get(), FluidType.BUCKET_VOLUME, FourLayeredCauldronBlock.LEVEL);
        event.register(ModBlocks.MUSHROOM_STEW_CAULDRON.get(), ModFluids.MUSHROOM_STEW.get(), FluidType.BUCKET_VOLUME, FourLayeredCauldronBlock.LEVEL);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void useItemOnBlock(UseItemOnBlockEvent event) {
        Player player = event.getPlayer();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        UseOnContext context = event.getUseOnContext();
        if(event.getLevel().getBlockState(event.getPos()).getBlock() == Blocks.FLETCHING_TABLE && event.getPlayer() != null) {
            if(event.getUsePhase() == UseItemOnBlockEvent.UsePhase.BLOCK) {
                if (!level.isClientSide()) {
                    MenuProvider provider = new SimpleMenuProvider(
                            (i, inventory, player1) -> new FletchingMenu(i, inventory, ContainerLevelAccess.create(level, pos)), Component.translatable("container.fletching"));
                    player.openMenu(provider);
                    player.awardStat(ModStats.INTERACT_WITH_FLETCHING_TABLE);
                }
                event.cancelWithResult(InteractionResult.SUCCESS);
            }
        }
        if(level.getBlockState(pos).is(BlockTags.TURNS_INTO_DIRT_PATH) && event.getItemStack().is(ItemTags.SHOVELS) && event.getFace() != Direction.DOWN) {
            if(event.getUsePhase() == UseItemOnBlockEvent.UsePhase.ITEM_AFTER_BLOCK) {
                if(!level.getBlockState(pos.above()).isFaceSturdy(level, pos, Direction.DOWN, SupportType.FULL)) {
                    level.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
                    if(!level.isClientSide()) {
                        level.setBlock(pos, Blocks.DIRT_PATH.defaultBlockState(), Block.UPDATE_NEIGHBORS | Block.UPDATE_CLIENTS | Block.UPDATE_IMMEDIATE);
                        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, Blocks.DIRT_PATH.defaultBlockState()));
                        if(player != null) {
                            context.getItemInHand().hurtAndBreak(1, player, context.getHand().asEquipmentSlot());
                        }
                    }
                    event.cancelWithResult(InteractionResult.SUCCESS);
                }
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

    public void registerTooltipAppenders(RegisterTooltipAppendersEvent event) {
        event.registerAppender(TooltipLocation.PRE_ITEM_INFO, ((stack, _, _, _, _, builder) -> {
        if(stack.getItem() instanceof PotionItem) {
            PotionContents potionContents = stack.get(DataComponents.POTION_CONTENTS);
            if(potionContents != null && potionContents.potion().isPresent()) {
                Holder<Potion> potion = potionContents.potion().get();
                String potionName = potion.key().identifier().getPath();
                if(potionName.startsWith("long")) {
                    builder.accept(Component.translatable("potion.longPotion").withStyle(ChatFormatting.DARK_GRAY));
                } else if(potionName.startsWith("strong")) {
                    builder.accept(Component.translatable("potion.strongPotion").withStyle(ChatFormatting.DARK_GRAY));
                }
            }
        }
        }));
    }

    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        HeldItemNameCommand.register(dispatcher);
    }

    @SubscribeEvent
    public void datapackSync(OnDatapackSyncEvent event) {
        event.sendRecipes(ModRecipeType.FLETCHING.get());
    }

    public static Identifier asResource(String path) {
        return Identifier.fromNamespaceAndPath(MODID, path);
    }
}