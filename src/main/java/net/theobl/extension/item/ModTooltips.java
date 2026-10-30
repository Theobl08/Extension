package net.theobl.extension.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.neoforged.neoforge.common.tooltip.TooltipAppender;
import net.neoforged.neoforge.common.tooltip.TooltipLocation;
import net.neoforged.neoforge.event.RegisterTooltipAppendersEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.theobl.extension.Config;
import net.theobl.extension.Extension;
import net.theobl.extension.block.FletchingTableBlock;
import net.theobl.extension.block.ModBlocks;

import java.util.function.Predicate;

public final class ModTooltips {
    ///The Minecraft Bedrock Edition {@code blue} formatting color
    private static final TextColor BEDROCK_EDITION_BLUE = TextColor.fromRgb(0x447FFF);
    ///The Minecraft Bedrock Edition {@code material_redstone} formatting color
    private static final TextColor MATERIAL_REDSTONE = TextColor.fromRgb(0xEE222C);

    //Minecraft BE additional item tooltips
    public static final Component CRAFTING_TABLE_TOOLTIP = itemTooltip(Blocks.CRAFTING_TABLE).withColor(BEDROCK_EDITION_BLUE);
    public static final Component FURNACE_TOOLTIP = itemTooltip(Blocks.FURNACE).withColor(BEDROCK_EDITION_BLUE);
    public static final Component BLAST_FURNACE_TOOLTIP = itemTooltip(Blocks.BLAST_FURNACE).withColor(BEDROCK_EDITION_BLUE);
    public static final Component SMOKER_TOOLTIP = itemTooltip(Blocks.SMOKER).withColor(BEDROCK_EDITION_BLUE);
    public static final Component STORAGE_ITEM_TOOLTIP = Component.translatable("tooltip.extension.block.storage").withColor(BEDROCK_EDITION_BLUE);
    public static final Component COMPOSTER_TOOLTIP = itemTooltip(Blocks.COMPOSTER).withColor(BEDROCK_EDITION_BLUE);
    public static final Component LOOM_TOOLTIP = itemTooltip(Blocks.LOOM).withColor(BEDROCK_EDITION_BLUE);
    public static final Component CARTOGRAPHY_TABLE_TOOLTIP = itemTooltip(Blocks.CARTOGRAPHY_TABLE).withColor(BEDROCK_EDITION_BLUE);
    public static final Component SMITHING_TABLE_TOOLTIP = itemTooltip(Blocks.SMITHING_TABLE).withColor(BEDROCK_EDITION_BLUE);
    public static final Component STONECUTTER_TOOLTIP = itemTooltip(Blocks.STONECUTTER).withColor(BEDROCK_EDITION_BLUE);
    public static final Component LECTERN_TOOLTIP = itemTooltip(Blocks.LECTERN).withColor(BEDROCK_EDITION_BLUE);
    public static final Component GRINDSTONE_TOOLTIP = itemTooltip(Blocks.GRINDSTONE).withColor(BEDROCK_EDITION_BLUE);
    public static final Component BREWING_STAND_TOOLTIP = itemTooltip(Blocks.BREWING_STAND).withColor(BEDROCK_EDITION_BLUE);
    public static final Component CAULDRON_TOOLTIP = itemTooltip(Blocks.CAULDRON).withColor(BEDROCK_EDITION_BLUE);
    public static final Component ANVIL_TOOLTIP = itemTooltip(Blocks.ANVIL).withColor(BEDROCK_EDITION_BLUE);
    public static final Component ENCHANTING_TABLE_TOOLTIP = itemTooltip(Blocks.ENCHANTING_TABLE).withColor(BEDROCK_EDITION_BLUE);
    public static final Component HOPPER_TOOLTIP = itemTooltip(Blocks.HOPPER).withColor(BEDROCK_EDITION_BLUE);
    public static final Component DROPPER_TOOLTIP = itemTooltip(Blocks.DROPPER).withColor(BEDROCK_EDITION_BLUE);
    public static final Component DISPENSER_TOOLTIP = itemTooltip(Blocks.DISPENSER).withColor(BEDROCK_EDITION_BLUE);
    public static final Component OBSERVER_TOOLTIP = itemTooltip(Blocks.OBSERVER).withColor(BEDROCK_EDITION_BLUE);
    public static final Component CRAFTER_TOOLTIP = itemTooltip(Blocks.CRAFTER).withColor(BEDROCK_EDITION_BLUE);
    public static final Component PISTON_TOOLTIP = itemTooltip(Blocks.PISTON).withColor(BEDROCK_EDITION_BLUE);
    public static final Component STICKY_PISTON_TOOLTIP = itemTooltip(Blocks.STICKY_PISTON).withColor(BEDROCK_EDITION_BLUE);
    public static final Component COMPARATOR_TOOLTIP = itemTooltip(Blocks.COMPARATOR).withColor(BEDROCK_EDITION_BLUE);
    public static final Component HOE_TOOLTIP = Component.translatable("tooltip.extension.item.hoe").withColor(BEDROCK_EDITION_BLUE);
    public static final Component SHEARS_TOOLTIP = itemTooltip(Items.SHEARS).withColor(BEDROCK_EDITION_BLUE);
    public static final Component BRUSH_TOOLTIP = itemTooltip(Items.BRUSH).withColor(BEDROCK_EDITION_BLUE);
    public static final Component FLINT_AND_STEEL_TOOLTIP = itemTooltip(Items.FLINT_AND_STEEL).withColor(BEDROCK_EDITION_BLUE);
    public static final Component COMPASS_TOOLTIP = itemTooltip(Items.COMPASS).withColor(BEDROCK_EDITION_BLUE);
    public static final Component LODESTONE_COMPASS_TOOLTIP = Component.translatable("tooltip.extension.item.compass.lodestone").withColor(BEDROCK_EDITION_BLUE);
    public static final Component RECOVERY_COMPASS_TOOLTIP = itemTooltip(Items.RECOVERY_COMPASS).withColor(BEDROCK_EDITION_BLUE);
    public static final Component REDSTONE_SOURCE_TOOLTIP = Component.translatable("tooltip.extension.item.redstone.source").withColor(MATERIAL_REDSTONE);
    public static final Component REDSTONE_CONDUCTOR_TOOLTIP = Component.translatable("tooltip.extension.item.redstone.conductor").withColor(MATERIAL_REDSTONE);
    public static final Component REDSTONE_POWERED_TOOLTIP = Component.translatable("tooltip.extension.item.redstone.powered").withColor(MATERIAL_REDSTONE);

    //Extension (this mod) additional item tooltips
    public static final Component CHISELED_BOOKSHELF_TOOLTIP = itemTooltip(Blocks.CHISELED_BOOKSHELF).withColor(BEDROCK_EDITION_BLUE);
    public static final Component FLETCHING_TABLE_TOOLTIP = itemTooltip(Blocks.FLETCHING_TABLE).withColor(BEDROCK_EDITION_BLUE);
    public static final Component CAMPFIRE_TOOLTIP = itemTooltip(Blocks.CAMPFIRE).withColor(BEDROCK_EDITION_BLUE);
    public static final Component TARGET_TOOLTIP = itemTooltip(Blocks.TARGET).withColor(BEDROCK_EDITION_BLUE);
    public static final Component SCULK_SENSOR_TOOLTIP = itemTooltip(Blocks.SCULK_SENSOR).withColor(BEDROCK_EDITION_BLUE);

    private static MutableComponent itemTooltip(ItemLike item) {
        return Component.translatable("tooltip." + Extension.MODID + "." + item.asItem().getDescriptionId().replace("minecraft.", ""));
    }

    private static TooltipAppender createSingleLineAppender(ItemLike item, Component tooltip) {
        return (stack, _, _, _, _, builder) -> {
            if(!Config.showAdditionalItemInformation) {
                return;
            }
            if(stack.is(item.asItem())) {
                builder.accept(tooltip);
            }
        };
    }

    private static TooltipAppender createCompassAppender() {
        return (stack, _, _, _, _, builder) -> {
            if(!Config.showAdditionalItemInformation) {
                return;
            }
            if(stack.getItem() instanceof CompassItem) {
                if(stack.has(DataComponents.LODESTONE_TRACKER)) {
                    builder.accept(LODESTONE_COMPASS_TOOLTIP);
                } else {
                    builder.accept(COMPASS_TOOLTIP);
                }
            }
        };
    }

    private static void addTooltipLine(RegisterTooltipAppendersEvent event, Predicate<Block> predicate, Component tooltip) {
        BuiltInRegistries.BLOCK.stream().filter(predicate).forEach(b -> event.registerAppender(TooltipLocation.HEAD, createSingleLineAppender(b, tooltip)));
    }

    private static void addTooltipLine(RegisterTooltipAppendersEvent event, Component tooltip, ItemLike... item) {
        for(ItemLike itemLike : item) {
            event.registerAppender(TooltipLocation.HEAD, createSingleLineAppender(itemLike, tooltip));
        }
    }

    @SafeVarargs
    private static void addTooltipLine(RegisterTooltipAppendersEvent event, Component tooltip, Class<? extends Block>... blockClasses) {
        for(var blockClass : blockClasses) {
            addTooltipLine(event, blockClass::isInstance, tooltip);
        }
    }

    public static void registerTooltipAppenders(RegisterTooltipAppendersEvent event) {
        //Add Extension (this mod) additional item tooltips

        // Add Minecraft Bedrock Edition additional item tooltips
        addTooltipLine(event, CRAFTING_TABLE_TOOLTIP, Blocks.CRAFTING_TABLE);
        addTooltipLine(event, CRAFTING_TABLE_TOOLTIP, ModBlocks.CRAFTING_TABLES.values().toArray(DeferredBlock[]::new));
        addTooltipLine(event, FURNACE_TOOLTIP, FurnaceBlock.class);
        addTooltipLine(event, BLAST_FURNACE_TOOLTIP, BlastFurnaceBlock.class);
        addTooltipLine(event, SMOKER_TOOLTIP, SmokerBlock.class);
        addTooltipLine(event, b -> (b instanceof ChestBlock || b instanceof BarrelBlock || b instanceof ShulkerBoxBlock), STORAGE_ITEM_TOOLTIP);
        addTooltipLine(event, COMPOSTER_TOOLTIP, ComposterBlock.class);
        addTooltipLine(event, LOOM_TOOLTIP, LoomBlock.class);
        addTooltipLine(event, CARTOGRAPHY_TABLE_TOOLTIP, CartographyTableBlock.class);
        addTooltipLine(event, SMITHING_TABLE_TOOLTIP, SmithingTableBlock.class);
        addTooltipLine(event, STONECUTTER_TOOLTIP, StonecutterBlock.class);
        addTooltipLine(event, LECTERN_TOOLTIP, LecternBlock.class);
        addTooltipLine(event, GRINDSTONE_TOOLTIP, GrindstoneBlock.class);
        addTooltipLine(event, BREWING_STAND_TOOLTIP, BrewingStandBlock.class);
        addTooltipLine(event, CAULDRON_TOOLTIP, CauldronBlock.class);
        addTooltipLine(event, ANVIL_TOOLTIP, AnvilBlock.class);
        addTooltipLine(event, ENCHANTING_TABLE_TOOLTIP, EnchantingTableBlock.class);
        addTooltipLine(event, HOPPER_TOOLTIP, HopperBlock.class);
        addTooltipLine(event, DROPPER_TOOLTIP, DropperBlock.class);
        addTooltipLine(event, DISPENSER_TOOLTIP, Blocks.DISPENSER);
        addTooltipLine(event, OBSERVER_TOOLTIP, ObserverBlock.class);
        addTooltipLine(event, CRAFTER_TOOLTIP, CrafterBlock.class);
        addTooltipLine(event, PISTON_TOOLTIP, Blocks.PISTON);
        addTooltipLine(event, STICKY_PISTON_TOOLTIP, Blocks.STICKY_PISTON);
        addTooltipLine(event, COMPARATOR_TOOLTIP, ComparatorBlock.class);
        BuiltInRegistries.ITEM.stream()
                .filter(i -> i.getDescriptionId().endsWith("_hoe"))
                .forEach(i -> event.registerAppender(TooltipLocation.HEAD, createSingleLineAppender(i, HOE_TOOLTIP)));
        addTooltipLine(event, SHEARS_TOOLTIP, Items.SHEARS);
        addTooltipLine(event, BRUSH_TOOLTIP, Items.BRUSH);
        addTooltipLine(event, FLINT_AND_STEEL_TOOLTIP, Items.FLINT_AND_STEEL);
        event.registerAppender(TooltipLocation.HEAD, createCompassAppender());
        addTooltipLine(event, RECOVERY_COMPASS_TOOLTIP, Items.RECOVERY_COMPASS);
        addTooltipLine(event, REDSTONE_SOURCE_TOOLTIP, Blocks.REDSTONE_TORCH, Blocks.OBSERVER, Blocks.DAYLIGHT_DETECTOR, Blocks.COMPARATOR);
        addTooltipLine(event, REDSTONE_CONDUCTOR_TOOLTIP, Blocks.REDSTONE_BLOCK, Items.REDSTONE);
        addTooltipLine(event, REDSTONE_POWERED_TOOLTIP, RedstoneLampBlock.class, DispenserBlock.class, CrafterBlock.class, PistonBaseBlock.class, PoweredRailBlock.class);

        //Add Extension (this mod) additional item tooltips
        addTooltipLine(event, CAMPFIRE_TOOLTIP, CampfireBlock.class);
        addTooltipLine(event, TARGET_TOOLTIP, TargetBlock.class);
        addTooltipLine(event, SCULK_SENSOR_TOOLTIP, SculkSensorBlock.class);
        addTooltipLine(event, REDSTONE_SOURCE_TOOLTIP, TargetBlock.class, SculkSensorBlock.class);
        addTooltipLine(event, REDSTONE_SOURCE_TOOLTIP, Blocks.DETECTOR_RAIL, ModBlocks.REDSTONE_LANTERN, ModBlocks.REDSTONE_CAMPFIRE, ModBlocks.REDSTONE_O_LANTERN);
        addTooltipLine(event, REDSTONE_CONDUCTOR_TOOLTIP, RepeaterBlock.class);
        addTooltipLine(event, CHISELED_BOOKSHELF_TOOLTIP, ChiseledBookShelfBlock.class);
        addTooltipLine(event, FLETCHING_TABLE_TOOLTIP, Blocks.FLETCHING_TABLE);
        addTooltipLine(event, FLETCHING_TABLE_TOOLTIP, FletchingTableBlock.class);
    }
}