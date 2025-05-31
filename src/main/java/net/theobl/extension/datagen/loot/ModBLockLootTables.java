package net.theobl.extension.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.item.ModItems;

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
        this.add(ModBlocks.AMETHYST_ORE.get(), block -> createCopperLikeOreDrops(ModBlocks.AMETHYST_ORE.get(),Items.AMETHYST_SHARD));
        this.add(ModBlocks.DEEPSLATE_AMETHYST_ORE.get(), block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_AMETHYST_ORE.get(),Items.AMETHYST_SHARD));

        this.dropSelf(ModBlocks.STONE_WALL.get());
        this.dropSelf(ModBlocks.POLISHED_GRANITE_WALL.get());
        this.dropSelf(ModBlocks.POLISHED_DIORITE_WALL.get());
        this.dropSelf(ModBlocks.POLISHED_ANDESITE_WALL.get());
        this.dropSelf(ModBlocks.PRISMARINE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.DARK_PRISMARINE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.PURPUR_WALL.get());
        this.dropSelf(ModBlocks.QUARTZ_WALL.get());
        this.dropSelf(ModBlocks.SMOOTH_QUARTZ_WALL.get());

        this.dropSelf(ModBlocks.CUT_COPPER_WALL.get());
        this.dropSelf(ModBlocks.EXPOSED_CUT_COPPER_WALL.get());
        this.dropSelf(ModBlocks.WEATHERED_CUT_COPPER_WALL.get());
        this.dropSelf(ModBlocks.OXIDIZED_CUT_COPPER_WALL.get());
        this.dropSelf(ModBlocks.WAXED_CUT_COPPER_WALL.get());
        this.dropSelf(ModBlocks.WAXED_EXPOSED_CUT_COPPER_WALL.get());
        this.dropSelf(ModBlocks.WAXED_WEATHERED_CUT_COPPER_WALL.get());
        this.dropSelf(ModBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL.get());

        this.dropSelf(ModBlocks.TINTED_GLASS_PANE.get());

        this.dropSelf(ModBlocks.NETHER_BRICK_FENCE_GATE.get());
        this.dropSelf(ModBlocks.RED_NETHER_BRICK_FENCE_GATE.get());
        this.dropSelf(ModBlocks.CRACKED_RED_NETHER_BRICKS.get());
        this.dropSelf(ModBlocks.RED_NETHER_BRICK_FENCE.get());
        this.dropSelf(ModBlocks.CHISELED_RED_NETHER_BRICKS.get());

        this.dropSelf(ModBlocks.BLUE_NETHER_BRICKS.get());
        this.dropSelf(ModBlocks.CRACKED_BLUE_NETHER_BRICKS.get());
        this.dropSelf(ModBlocks.BLUE_NETHER_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.BLUE_NETHER_BRICK_SLAB.get());
        this.dropSelf(ModBlocks.BLUE_NETHER_BRICK_WALL.get());
        this.dropSelf(ModBlocks.BLUE_NETHER_BRICK_FENCE.get());
        this.dropSelf(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get());
        this.dropSelf(ModBlocks.CHISELED_BLUE_NETHER_BRICKS.get());

        this.dropSelf(ModBlocks.SOUL_SANDSTONE.get());
        this.dropSelf(ModBlocks.SOUL_SANDSTONE_STAIRS.get());
        this.dropSelf(ModBlocks.SOUL_SANDSTONE_SLAB.get());
        this.dropSelf(ModBlocks.SOUL_SANDSTONE_WALL.get());
        this.dropSelf(ModBlocks.CHISELED_SOUL_SANDSTONE.get());
        this.dropSelf(ModBlocks.SMOOTH_SOUL_SANDSTONE.get());
        this.dropSelf(ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get());
        this.dropSelf(ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get());
        this.dropSelf(ModBlocks.CUT_SOUL_SANDSTONE.get());
        this.dropSelf(ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get());

        this.dropSelf(ModBlocks.REDSTONE_LANTERN.get());

        for (RegistryObject<Block> quiltedConcrete : ModBlocks.QUILTED_CONCRETES){
            this.dropSelf(quiltedConcrete.get());
        }

        for (RegistryObject<Block> glazedConcrete : ModBlocks.GLAZED_CONCRETES){
            this.dropSelf(glazedConcrete.get());
        }

        for (RegistryObject<Block> antiblock : ModBlocks.ANTIBLOCKS) {
            this.dropSelf(antiblock.get());
        }

        this.dropSelf(ModBlocks.VIBRANT_RED_WOOL.get());
        this.dropSelf(ModBlocks.DULL_ORANGE_WOOL.get());
        this.dropSelf(ModBlocks.BRIGHT_YELLOW_WOOL.get());
        this.dropSelf(ModBlocks.CHARTREUSE_WOOL.get());
        this.dropSelf(ModBlocks.VIBRANT_GREEN_WOOL.get());
        this.dropSelf(ModBlocks.SPRING_GREEN_WOOL.get());
        this.dropSelf(ModBlocks.BRIGHT_CYAN_WOOL.get());
        this.dropSelf(ModBlocks.CAPRI_WOOL.get());
        this.dropSelf(ModBlocks.ULTRAMARINE_WOOL.get());
        this.dropSelf(ModBlocks.VIOLET_WOOL.get());
        this.dropSelf(ModBlocks.VIBRANT_PURPLE_WOOL.get());
        this.dropSelf(ModBlocks.BRIGHT_MAGENTA_WOOL.get());
        this.dropSelf(ModBlocks.ROSE_WOOL.get());
        this.dropSelf(ModBlocks.DARK_GRAY_WOOL.get());
        this.dropSelf(ModBlocks.SILVER_WOOL.get());
        this.dropSelf(ModBlocks.ALPHA_WHITE_WOOL.get());

        this.dropSelf(ModBlocks.VIBRANT_RED_CARPET.get());
        this.dropSelf(ModBlocks.DULL_ORANGE_CARPET.get());
        this.dropSelf(ModBlocks.BRIGHT_YELLOW_CARPET.get());
        this.dropSelf(ModBlocks.CHARTREUSE_CARPET.get());
        this.dropSelf(ModBlocks.VIBRANT_GREEN_CARPET.get());
        this.dropSelf(ModBlocks.SPRING_GREEN_CARPET.get());
        this.dropSelf(ModBlocks.BRIGHT_CYAN_CARPET.get());
        this.dropSelf(ModBlocks.CAPRI_CARPET.get());
        this.dropSelf(ModBlocks.ULTRAMARINE_CARPET.get());
        this.dropSelf(ModBlocks.VIOLET_CARPET.get());
        this.dropSelf(ModBlocks.VIBRANT_PURPLE_CARPET.get());
        this.dropSelf(ModBlocks.BRIGHT_MAGENTA_CARPET.get());
        this.dropSelf(ModBlocks.ROSE_CARPET.get());
        this.dropSelf(ModBlocks.DARK_GRAY_CARPET.get());
        this.dropSelf(ModBlocks.SILVER_CARPET.get());
        this.dropSelf(ModBlocks.ALPHA_WHITE_CARPET.get());

        this.dropSelf(ModBlocks.SPRUCE_CRAFTING_TABLE.get());
        this.dropSelf(ModBlocks.BIRCH_CRAFTING_TABLE.get());
        this.dropSelf(ModBlocks.JUNGLE_CRAFTING_TABLE.get());
        this.dropSelf(ModBlocks.ACACIA_CRAFTING_TABLE.get());
        this.dropSelf(ModBlocks.DARK_OAK_CRAFTING_TABLE.get());
        this.dropSelf(ModBlocks.MANGROVE_CRAFTING_TABLE.get());
        this.dropSelf(ModBlocks.BAMBOO_CRAFTING_TABLE.get());
        this.dropSelf(ModBlocks.CHERRY_CRAFTING_TABLE.get());
        this.dropSelf(ModBlocks.CRIMSON_CRAFTING_TABLE.get());
        this.dropSelf(ModBlocks.WARPED_CRAFTING_TABLE.get());

        this.dropSelf(ModBlocks.OAK_MOSAIC.get());
        this.dropSelf(ModBlocks.SPRUCE_MOSAIC.get());
        this.dropSelf(ModBlocks.BIRCH_MOSAIC.get());
        this.dropSelf(ModBlocks.JUNGLE_MOSAIC.get());
        this.dropSelf(ModBlocks.ACACIA_MOSAIC.get());
        this.dropSelf(ModBlocks.DARK_OAK_MOSAIC.get());
        this.dropSelf(ModBlocks.MANGROVE_MOSAIC.get());
        this.dropSelf(ModBlocks.CHERRY_MOSAIC.get());
        this.dropSelf(ModBlocks.CRIMSON_MOSAIC.get());
        this.dropSelf(ModBlocks.WARPED_MOSAIC.get());

        this.add(ModBlocks.SPRUCE_BOOKSHELF.get(), block -> createBookshelfDrop(ModBlocks.SPRUCE_BOOKSHELF.get()));
        this.add(ModBlocks.BIRCH_BOOKSHELF.get(), block -> createBookshelfDrop(ModBlocks.BIRCH_BOOKSHELF.get()));
        this.add(ModBlocks.JUNGLE_BOOKSHELF.get(), block -> createBookshelfDrop(ModBlocks.JUNGLE_BOOKSHELF.get()));
        this.add(ModBlocks.ACACIA_BOOKSHELF.get(), block -> createBookshelfDrop(ModBlocks.ACACIA_BOOKSHELF.get()));
        this.add(ModBlocks.DARK_OAK_BOOKSHELF.get(), block -> createBookshelfDrop(ModBlocks.DARK_OAK_BOOKSHELF.get()));
        this.add(ModBlocks.MANGROVE_BOOKSHELF.get(), block -> createBookshelfDrop(ModBlocks.MANGROVE_BOOKSHELF.get()));
        this.add(ModBlocks.BAMBOO_BOOKSHELF.get(), block -> createBookshelfDrop(ModBlocks.BAMBOO_BOOKSHELF.get()));
        this.add(ModBlocks.CHERRY_BOOKSHELF.get(), block -> createBookshelfDrop(ModBlocks.CHERRY_BOOKSHELF.get()));
        this.add(ModBlocks.CRIMSON_BOOKSHELF.get(), block -> createBookshelfDrop(ModBlocks.CRIMSON_BOOKSHELF.get()));
        this.add(ModBlocks.WARPED_BOOKSHELF.get(), block -> createBookshelfDrop(ModBlocks.WARPED_BOOKSHELF.get()));

        for (RegistryObject<Block> log : ModBlocks.COLORED_LOGS) {
            this.dropSelf(log.get());
        }
        for (RegistryObject<Block> wood : ModBlocks.COLORED_WOODS) {
            this.dropSelf(wood.get());
        }
        for (RegistryObject<Block> block : ModBlocks.COLORED_STRIPPED_LOGS) {
            this.dropSelf(block.get());
        }
        for (RegistryObject<Block> log : ModBlocks.COLORED_STRIPPED_WOODS) {
            this.dropSelf(log.get());
        }
        for (RegistryObject<Block> plank : ModBlocks.COLORED_PLANKS) {
            this.dropSelf(plank.get());
        }
        for (RegistryObject<Block> stairs : ModBlocks.COLORED_STAIRS) {
            this.dropSelf(stairs.get());
        }
        for (RegistryObject<Block> slab : ModBlocks.COLORED_SLABS) {
            this.add(slab.get(), block -> createSlabItemTable(slab.get()));
        }
        for (RegistryObject<Block> fence : ModBlocks.COLORED_FENCES) {
            this.dropSelf(fence.get());
        }
        for (RegistryObject<Block> gate : ModBlocks.COLORED_FENCE_GATES) {
            this.dropSelf(gate.get());
        }
        for (RegistryObject<Block> door : ModBlocks.COLORED_DOORS) {
            this.add(door.get(), block -> createDoorTable(door.get()));
        }
        for (RegistryObject<Block> trapdoor : ModBlocks.COLORED_TRAPDOORS) {
            this.dropSelf(trapdoor.get());
        }
        for (RegistryObject<Block> plate : ModBlocks.COLORED_PRESSURE_PLATES) {
            this.dropSelf(plate.get());
        }
        for (RegistryObject<Block> button : ModBlocks.COLORED_BUTTONS) {
            this.dropSelf(button.get());
        }
        for (RegistryObject<Item> sign : ModItems.COLORED_SIGNS) {
            int index = ModItems.COLORED_SIGNS.indexOf(sign);
            this.add(ModBlocks.COLORED_SIGNS.get(index).get(), block -> createSingleItemTable(sign.get()));
            this.add(ModBlocks.COLORED_WALL_SIGNS.get(index).get(), block -> createSingleItemTable(sign.get()));
        }
        for (RegistryObject<Item> hanging : ModItems.COLORED_HANGING_SIGNS) {
            int index = ModItems.COLORED_HANGING_SIGNS.indexOf(hanging);
            this.add(ModBlocks.COLORED_HANGING_SIGNS.get(index).get(), block -> createSingleItemTable(hanging.get()));
            this.add(ModBlocks.COLORED_WALL_HANGING_SIGNS.get(index).get(), block -> createSingleItemTable(hanging.get()));
        }

        for (RegistryObject<Block> leaves : ModBlocks.COLORED_LEAVES) {
            int index = ModBlocks.COLORED_LEAVES.indexOf(leaves);
            this.add(leaves.get(), block -> createLeavesDrops(block, ModBlocks.COLORED_LOGS.get(index).get(), NORMAL_LEAVES_SAPLING_CHANCES));
        }

        this.dropSelf(ModBlocks.PAINTING_PLANKS.get());
        this.dropSelf(ModBlocks.PAINTING_STAIRS.get());
        this.add(ModBlocks.PAINTING_SLAB.get(), block -> createSlabItemTable(ModBlocks.PAINTING_SLAB.get()));
        this.dropSelf(ModBlocks.PAINTING_FENCE.get());
        this.dropSelf(ModBlocks.PAINTING_FENCE_GATE.get());
        this.add(ModBlocks.PAINTING_DOOR.get(), block -> createDoorTable(ModBlocks.PAINTING_DOOR.get()));
        this.dropSelf(ModBlocks.PAINTING_TRAPDOOR.get());
        this.dropSelf(ModBlocks.PAINTING_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.PAINTING_BUTTON.get());

        // Modify some minecraft loot tables
        this.add(Blocks.DIRT_PATH, block -> this.createSingleItemTableWithSilkTouch(block, Blocks.DIRT));
    }

    protected LootTable.Builder createBookshelfDrop (Block pBlock) {
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
