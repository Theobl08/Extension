package net.theobl.extension.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.theobl.extension.block.ModBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModBLockLootTables extends BlockLootSubProvider {
    private final List<Block> VANILLA_BLOCKS_CHANGED = List.of(Blocks.DIRT_PATH);

    public ModBLockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        for (RegistryObject<Block> block : ModBlocks.BLOCKS.getEntries()) {
            if(block.get() instanceof SlabBlock) {
                this.add(block.get(), this::createSlabItemTable);
            }
            else if (block.get() instanceof DoorBlock) {
                this.add(block.get(), this::createDoorTable);
            }
            else if (block.get() instanceof LeavesBlock) {
                continue;
            }
            else if (block.get().toString().contains("ore")) {
                this.add(block.get(), ore -> createCopperLikeOreDrops(ore, Items.AMETHYST_SHARD));
            }
            else if (block.get().toString().contains("bookshelf")) {
                this.add(block.get(), this::createBookshelfDrop);
            }
            else {
                this.dropSelf(block.get());
            }
        }

        for (RegistryObject<Block> leaves : ModBlocks.COLORED_LEAVES) {
            int index = ModBlocks.COLORED_LEAVES.indexOf(leaves);
            this.add(leaves.get(), block -> createLeavesDrops(block, ModBlocks.COLORED_LOGS.get(index).get(), NORMAL_LEAVES_SAPLING_CHANCES));
        }

        // Modify some minecraft loot tables
        this.add(Blocks.DIRT_PATH, block -> this.createSingleItemTableWithSilkTouch(block, Blocks.DIRT));
    }

    protected LootTable.Builder createBookshelfDrop(Block pBlock) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                LootItem.lootTableItem(Items.BOOK)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))));
    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item pItem) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(pItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        //return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
        ArrayList<Block> iterableBlocks = new ArrayList<>(); // define empty list
        // Iterate through each block registered into ModBlocks.BLOCKS and add it into our list:
        ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(iterableBlocks::add);
        // The above line requires that we need to have defined a loot table for every block in ModBlocks.BLOCKS.
        // Also add the vanilla blocks we are updating: found at https://forums.minecraftforge.net/topic/142575-loot-table-for-custom-crop-datagen-issue/
        iterableBlocks.addAll(VANILLA_BLOCKS_CHANGED);

        return iterableBlocks;
    }
}
