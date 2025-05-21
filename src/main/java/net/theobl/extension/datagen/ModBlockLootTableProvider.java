package net.theobl.extension.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.item.ModItems;

import java.util.ArrayList;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        for (DeferredHolder<Block, ? extends Block> block : ModBlocks.BLOCKS.getEntries()) {
            if(block.get() instanceof SlabBlock) {
                this.add(block.get(), this::createSlabItemTable);
            }
            else if (block.get().defaultBlockState().is(ModBlocks.BLUE_NETHER_WART.get())) {
                LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(NetherWartBlock.AGE, 3));
                this.add(block.get(),
                        block1 -> LootTable.lootTable().withPool(this.applyExplosionDecay(block1, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(block.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))
                                                .when(lootItemConditionBuilder))
                                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))
                                                .when(lootItemConditionBuilder))))));
            }
            else if (block.get().defaultBlockState().is(ModBlocks.REDSTONE_CAMPFIRE.get())) {
                this.add(block.get(),
                        block1 -> this.createSilkTouchDispatchTable(block1, this.applyExplosionCondition(block1, LootItem.lootTableItem(Items.REDSTONE)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))));
            }
            else {
                this.dropSelf(block.get());
            }
        }

        // Modify some minecraft loot tables
        this.add(Blocks.DIRT_PATH, block -> this.createSingleItemTableWithSilkTouch(block, Blocks.DIRT));
        this.dropWhenSilkTouch(Blocks.BUDDING_AMETHYST);
        this.dropWhenSilkTouch(Blocks.REINFORCED_DEEPSLATE);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        //return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
        ArrayList<Block> knownBlocks = new ArrayList<>();
        ModBlocks.BLOCKS.getEntries().stream().map(Holder::value).forEach(knownBlocks::add);
        knownBlocks.add(Blocks.DIRT_PATH);
        knownBlocks.add(Blocks.BUDDING_AMETHYST);
        knownBlocks.add(Blocks.REINFORCED_DEEPSLATE);
        return knownBlocks;
    }
}
