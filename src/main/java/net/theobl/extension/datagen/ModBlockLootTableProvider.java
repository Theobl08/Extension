package net.theobl.extension.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.item.ModItems;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.dropSelf(ModBlocks.STONE_WALL.get());
        this.dropSelf(ModBlocks.POLISHED_GRANITE_WALL.get());
        this.dropSelf(ModBlocks.POLISHED_DIORITE_WALL.get());
        this.dropSelf(ModBlocks.POLISHED_ANDESITE_WALL.get());
        this.dropSelf(ModBlocks.PRISMARINE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.DARK_PRISMARINE_WALL.get());
        this.dropSelf(ModBlocks.PURPUR_WALL.get());
        this.dropSelf(ModBlocks.QUARTZ_WALL.get());

        this.dropSelf(ModBlocks.SMOOTH_STONE_STAIRS.get());
        this.dropSelf(ModBlocks.NETHERITE_STAIRS.get());

        this.dropSelf(ModBlocks.TINTED_GLASS_PANE.get());

        this.dropSelf(ModBlocks.SMOOTH_BASALT_STAIRS.get());
        this.add(ModBlocks.SMOOTH_BASALT_SLAB.get(), block -> createSlabItemTable(ModBlocks.SMOOTH_BASALT_SLAB.get()));

        this.dropSelf(ModBlocks.QUARTZ_BRICK_STAIRS.get());
        this.add(ModBlocks.QUARTZ_BRICK_SLAB.get(), block -> createSlabItemTable(ModBlocks.QUARTZ_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.QUARTZ_BRICK_WALL.get());

        this.dropSelf(ModBlocks.CRACKED_RED_NETHER_BRICKS.get());
        this.dropSelf(ModBlocks.RED_NETHER_BRICK_FENCE.get());
        this.dropSelf(ModBlocks.CHISELED_RED_NETHER_BRICKS.get());

        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BLUE_NETHER_WART.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(NetherWartBlock.AGE, 3));
        this.add(ModBlocks.BLUE_NETHER_WART.get(), block ->
                LootTable.lootTable().withPool(this.applyExplosionDecay(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(ModItems.BLUE_NETHER_WART)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))
                                        .when(lootItemConditionBuilder))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))
                                        .when(lootItemConditionBuilder))))));

        this.dropSelf(ModBlocks.BLUE_NETHER_BRICKS.get());
        this.dropSelf(ModBlocks.CRACKED_BLUE_NETHER_BRICKS.get());
        this.dropSelf(ModBlocks.BLUE_NETHER_BRICK_STAIRS.get());
        this.add(ModBlocks.BLUE_NETHER_BRICK_SLAB.get(), block -> createSlabItemTable(ModBlocks.BLUE_NETHER_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.BLUE_NETHER_BRICK_WALL.get());
        this.dropSelf(ModBlocks.BLUE_NETHER_BRICK_FENCE.get());
        this.dropSelf(ModBlocks.CHISELED_BLUE_NETHER_BRICKS.get());

        this.dropSelf(ModBlocks.SOUL_SANDSTONE.get());
        this.dropSelf(ModBlocks.SOUL_SANDSTONE_STAIRS.get());
        this.add(ModBlocks.SOUL_SANDSTONE_SLAB.get(), block -> createSlabItemTable(ModBlocks.SOUL_SANDSTONE_SLAB.get()));
        this.dropSelf(ModBlocks.SOUL_SANDSTONE_WALL.get());
        this.dropSelf(ModBlocks.CHISELED_SOUL_SANDSTONE.get());
        this.dropSelf(ModBlocks.SMOOTH_SOUL_SANDSTONE.get());
        this.dropSelf(ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get());
        this.add(ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get(), block -> createSlabItemTable(ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get()));
        this.dropSelf(ModBlocks.CUT_SOUL_SANDSTONE.get());
        this.add(ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get(), block -> createSlabItemTable(ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get()));

        this.dropSelf(ModBlocks.SOUL_O_LANTERN.get());
        this.dropSelf(ModBlocks.REDSTONE_O_LANTERN.get());

        this.dropSelf(ModBlocks.REDSTONE_LANTERN.get());
        this.add(ModBlocks.REDSTONE_CAMPFIRE.get(),
                block -> this.createSilkTouchDispatchTable(block, this.applyExplosionCondition(block, LootItem.lootTableItem(Items.REDSTONE)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
