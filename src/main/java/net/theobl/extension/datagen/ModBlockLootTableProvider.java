package net.theobl.extension.datagen;

import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.UniformContainerBase;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchBlock;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.loot.ModLootTables;

import java.util.ArrayList;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    private static final float[] NORMAL_LEAVES_STICK_CHANCES = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};

    protected ModBlockLootTableProvider(LootTableSubProvider.Context context) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), context);
    }

    @Override
    protected void generate() {
        for (DeferredHolder<Block, ? extends Block> block : ModBlocks.BLOCKS.getEntries()) {
            if(block.get() instanceof SlabBlock) {
                this.add(block.get(), this::createSlabItemTable);
            }
            else if(block.get() instanceof DoorBlock) {
                this.add(block.get(), this::createDoorTable);
            }
            else if(block.is(ModBlocks.POTATO_LEAVES.getId())) {
                this.add(ModBlocks.POTATO_LEAVES.get(), leaves -> this.createLeavesDrops(leaves, ModBlocks.POTATO_SPROUTS.get(), NORMAL_LEAVES_SAPLING_CHANCES));
            }
            else if (block.get().defaultBlockState().is(ModBlocks.BLUE_NETHER_WART.get())) {
                LootItemCondition.Builder lootItemConditionBuilder = MatchBlock.blockMatches(this.blocks, block.get(),
                        StatePropertiesPredicate.Builder.properties().hasProperty(NetherWartBlock.AGE, 3));
                this.add(block.get(),
                        block1 -> LootTable.lootTable().withPool(this.applyExplosionDecay(block1, LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(block.get())
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4))
                                                .when(lootItemConditionBuilder))
                                        .apply(ApplyBonusCount.addUniformBonusCount(this.enchantments.getOrThrow(Enchantments.FORTUNE))
                                                .when(lootItemConditionBuilder))))));
            }
            else if (block.get().defaultBlockState().is(ModBlocks.REDSTONE_CAMPFIRE.get())) {
                this.add(block.get(),
                        block1 -> this.createSilkTouchDispatchTable(block1, this.applyExplosionCondition(block1, LootItem.lootTableItem(Items.REDSTONE)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))))));
            }
            else if (block.get().defaultBlockState().is(ModBlocks.COPPER_CAMPFIRE.get())) {
                this.add(block.get(),
                        block1 -> this.createSilkTouchDispatchTable(block1, this.applyExplosionCondition(block1, LootItem.lootTableItem(Items.COPPER_NUGGET)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))))));
            }
            else if (block.get().defaultBlockState().is(ModBlocks.ENDER_CAMPFIRE.get())) {
                this.add(block.get(),
                        block1 -> this.createSilkTouchDispatchTable(block1, this.applyExplosionCondition(block1, LootItem.lootTableItem(Items.END_STONE)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))))));
            }
            else if(block.get() instanceof FlowerPotBlock) {
                this.dropPottedContents(block.get());
            }
            else if(!(block.get() instanceof BaseFireBlock)) {
                this.dropSelf(block.get());
            }
        }

        // Create loot tables that will be passed in the "Add Table" Neoforge loot table modifier
        output.accept(
                ModLootTables.DROP_RED_POPLAR_SAPLING,
                createAdditionalLeavesDrops(Blocks.RED_POPLAR_LEAVES, ModBlocks.RED_POPLAR_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
        );
        output.accept(
                ModLootTables.DROP_ORANGE_POPLAR_SAPLING,
                createAdditionalLeavesDrops(Blocks.ORANGE_POPLAR_LEAVES, ModBlocks.ORANGE_POPLAR_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
        );
        output.accept(
                ModLootTables.DROP_YELLOW_POPLAR_SAPLING,
                createAdditionalLeavesDrops(Blocks.YELLOW_POPLAR_LEAVES, ModBlocks.YELLOW_POPLAR_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
        );

        // Modify some minecraft loot tables
        this.add(Blocks.DIRT_PATH, block -> this.createSingleItemTableWithSilkTouch(block, Blocks.DIRT));
        this.dropWhenSilkTouch(Blocks.BUDDING_AMETHYST);
        this.dropWhenSilkTouch(Blocks.REINFORCED_DEEPSLATE);
    }

    protected LootTable.Builder createAdditionalLeavesDrops(Block original, Block sapling, float... saplingChances) {
//        return this.createSilkTouchOrShearsDispatchTable(
//                        original,
//                        this.applyExplosionCondition(original, LootItem.lootTableItem(sapling))
//                                .when(BonusLevelTableCondition.bonusLevelFlatChance(this.enchantments.getOrThrow(Enchantments.FORTUNE), saplingChances))
//                )
        return LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .when(new AnyOfCondition.Builder().or(this.hasShears()).or(this.hasSilkTouch()).invert())
                                .add(
                                        this.applyExplosionCondition(original, LootItem.lootTableItem(sapling))
                                                .when(BonusLevelTableCondition.bonusLevelFlatChance(this.enchantments.getOrThrow(Enchantments.FORTUNE), saplingChances))

                                )
                )
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .when(new AnyOfCondition.Builder().or(this.hasShears()).or(this.hasSilkTouch()).invert())
                                .add(
                                        this.applyExplosionDecay(
                                                original, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                        )
                                                .when(
                                                        BonusLevelTableCondition.bonusLevelFlatChance(this.enchantments.getOrThrow(Enchantments.FORTUNE), NORMAL_LEAVES_STICK_CHANCES)
                                                )
                                )
                );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        //return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
        ArrayList<Block> knownBlocks = new ArrayList<>();
        ModBlocks.BLOCKS.getEntries().stream()
                .filter(block -> !(block.get() instanceof BaseFireBlock))
                .map(Holder::value)
                .forEach(knownBlocks::add);
        knownBlocks.add(Blocks.DIRT_PATH);
        knownBlocks.add(Blocks.BUDDING_AMETHYST);
        knownBlocks.add(Blocks.REINFORCED_DEEPSLATE);
        return knownBlocks;
    }
}
