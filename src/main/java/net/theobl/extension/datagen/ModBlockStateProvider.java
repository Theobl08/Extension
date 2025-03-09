package net.theobl.extension.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;

import java.util.List;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Extension.MOD_ID, exFileHelper);
    }

    private static final List<Block> SHULKER_BOXES =
            List.of(Blocks.WHITE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX,
                    Blocks.BROWN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX,
                    Blocks.LIME_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX,
                    Blocks.BLUE_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.PINK_SHULKER_BOX);

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.AMETHYST_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_AMETHYST_ORE);

        wallWithItem(ModBlocks.STONE_WALL, Blocks.STONE);
        wallWithItem(ModBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE);
        wallWithItem(ModBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE);
        wallWithItem(ModBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE);
        wallWithItem(ModBlocks.PRISMARINE_BRICK_WALL, Blocks.PRISMARINE_BRICKS);
        wallWithItem(ModBlocks.DARK_PRISMARINE_BRICK_WALL, Blocks.DARK_PRISMARINE);
        wallWithItem(ModBlocks.PURPUR_WALL, Blocks.PURPUR_BLOCK);
        wallWithItem(ModBlocks.QUARTZ_WALL, "quartz_block_side");
        wallWithItem(ModBlocks.SMOOTH_QUARTZ_WALL, "quartz_block_bottom");
        wallWithItem(ModBlocks.CUT_COPPER_WALL, Blocks.CUT_COPPER);
        wallWithItem(ModBlocks.EXPOSED_CUT_COPPER_WALL, Blocks.EXPOSED_CUT_COPPER);
        wallWithItem(ModBlocks.WEATHERED_CUT_COPPER_WALL, Blocks.WEATHERED_CUT_COPPER);
        wallWithItem(ModBlocks.OXIDIZED_CUT_COPPER_WALL, Blocks.OXIDIZED_CUT_COPPER);
        wallWithItem(ModBlocks.WAXED_CUT_COPPER_WALL, Blocks.CUT_COPPER);
        wallWithItem(ModBlocks.WAXED_EXPOSED_CUT_COPPER_WALL, Blocks.EXPOSED_CUT_COPPER);
        wallWithItem(ModBlocks.WAXED_WEATHERED_CUT_COPPER_WALL, Blocks.WEATHERED_CUT_COPPER);
        wallWithItem(ModBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL, Blocks.OXIDIZED_CUT_COPPER);

        paneBlockWithRenderType(((IronBarsBlock) ModBlocks.TINTED_GLASS_PANE.get()), mcLoc("block/tinted_glass"), modLoc("block/tinted_glass_pane_top"), "translucent");

        lantern(ModBlocks.REDSTONE_LANTERN);

        fenceGateWithItem(ModBlocks.NETHER_BRICK_FENCE_GATE, Blocks.NETHER_BRICKS);
        fenceGateWithItem(ModBlocks.RED_NETHER_BRICK_FENCE_GATE, Blocks.RED_NETHER_BRICKS);
        blockWithItem(ModBlocks.CRACKED_RED_NETHER_BRICKS);
        fenceWithItem(ModBlocks.RED_NETHER_BRICK_FENCE, Blocks.RED_NETHER_BRICKS);
        blockWithItem(ModBlocks.CHISELED_RED_NETHER_BRICKS);

        blockWithItem(ModBlocks.BLUE_NETHER_BRICKS);
        blockWithItem(ModBlocks.CRACKED_BLUE_NETHER_BRICKS);
        stairsWithItem(ModBlocks.BLUE_NETHER_BRICK_STAIRS, ModBlocks.BLUE_NETHER_BRICKS);
        slabWithItem(ModBlocks.BLUE_NETHER_BRICK_SLAB, ModBlocks.BLUE_NETHER_BRICKS);
        wallWithItem(ModBlocks.BLUE_NETHER_BRICK_WALL, ModBlocks.BLUE_NETHER_BRICKS.get());
        fenceWithItem(ModBlocks.BLUE_NETHER_BRICK_FENCE, ModBlocks.BLUE_NETHER_BRICKS.get());
        fenceGateWithItem(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE, ModBlocks.BLUE_NETHER_BRICKS.get());
        blockWithItem(ModBlocks.CHISELED_BLUE_NETHER_BRICKS);

        pillarBlockWithItem(ModBlocks.SOUL_SANDSTONE, "soul_sandstone", "soul_sandstone_bottom", "soul_sandstone_top");
        stairsWithItem(ModBlocks.SOUL_SANDSTONE_STAIRS, "soul_sandstone", "soul_sandstone_bottom", "soul_sandstone_top");
        slabWithItem(ModBlocks.SOUL_SANDSTONE_SLAB, ModBlocks.SOUL_SANDSTONE, "soul_sandstone", "soul_sandstone_bottom", "soul_sandstone_top");
        wallWithItem(ModBlocks.SOUL_SANDSTONE_WALL, ModBlocks.SOUL_SANDSTONE.get());
        columnBlockWithItem(ModBlocks.CHISELED_SOUL_SANDSTONE, "chiseled_soul_sandstone", "soul_sandstone_top");
        simpleBlockWithItem(ModBlocks.SMOOTH_SOUL_SANDSTONE.get(),
                models().cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.SMOOTH_SOUL_SANDSTONE.get()).getPath(),
                        new ResourceLocation(Extension.MOD_ID, "block/soul_sandstone_top")));
        stairsWithItem(ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS, "soul_sandstone_top", "soul_sandstone_top", "soul_sandstone_top");
        slabWithItem(ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB, ModBlocks.SMOOTH_SOUL_SANDSTONE, "soul_sandstone_top", "soul_sandstone_top", "soul_sandstone_top");
        columnBlockWithItem(ModBlocks.CUT_SOUL_SANDSTONE, "cut_soul_sandstone", "soul_sandstone_top");
        slabWithItem(ModBlocks.CUT_SOUL_SANDSTONE_SLAB, ModBlocks.CUT_SOUL_SANDSTONE, "cut_soul_sandstone", "soul_sandstone_top", "soul_sandstone_top");



        for (RegistryObject<Block> quiltedConcrete : ModBlocks.QUILTED_CONCRETES) {
            int index = ModBlocks.QUILTED_CONCRETES.indexOf(quiltedConcrete);
            simpleBlockWithItem(quiltedConcrete.get(), cubeAll(SHULKER_BOXES.get(index)));
        }

        for (RegistryObject<Block> glazedConcrete : ModBlocks.GLAZED_CONCRETES) {
            glazedBlockWithItem(glazedConcrete);
        }

        for (RegistryObject<Block> antiblock : ModBlocks.ANTIBLOCKS) {
            blockWithItem(antiblock);
        }

        blockWithItem(ModBlocks.VIBRANT_RED_WOOL);
        blockWithItem(ModBlocks.DULL_ORANGE_WOOL);
        blockWithItem(ModBlocks.BRIGHT_YELLOW_WOOL);
        blockWithItem(ModBlocks.CHARTREUSE_WOOL);
        blockWithItem(ModBlocks.VIBRANT_GREEN_WOOL);
        blockWithItem(ModBlocks.SPRING_GREEN_WOOL);
        blockWithItem(ModBlocks.BRIGHT_CYAN_WOOL);
        blockWithItem(ModBlocks.CAPRI_WOOL);
        blockWithItem(ModBlocks.ULTRAMARINE_WOOL);
        blockWithItem(ModBlocks.VIOLET_WOOL);
        blockWithItem(ModBlocks.VIBRANT_PURPLE_WOOL);
        blockWithItem(ModBlocks.BRIGHT_MAGENTA_WOOL);
        blockWithItem(ModBlocks.ROSE_WOOL);
        blockWithItem(ModBlocks.DARK_GRAY_WOOL);
        blockWithItem(ModBlocks.SILVER_WOOL);
        blockWithItem(ModBlocks.ALPHA_WHITE_WOOL);

        carpetBlockWithItem(ModBlocks.VIBRANT_RED_CARPET,"vibrant_red");
        carpetBlockWithItem(ModBlocks.DULL_ORANGE_CARPET,"dull_orange");
        carpetBlockWithItem(ModBlocks.BRIGHT_YELLOW_CARPET,"bright_yellow");
        carpetBlockWithItem(ModBlocks.CHARTREUSE_CARPET,"chartreuse");
        carpetBlockWithItem(ModBlocks.VIBRANT_GREEN_CARPET,"vibrant_green");
        carpetBlockWithItem(ModBlocks.SPRING_GREEN_CARPET,"spring_green");
        carpetBlockWithItem(ModBlocks.BRIGHT_CYAN_CARPET,"bright_cyan");
        carpetBlockWithItem(ModBlocks.CAPRI_CARPET,"capri");
        carpetBlockWithItem(ModBlocks.ULTRAMARINE_CARPET,"ultramarine");
        carpetBlockWithItem(ModBlocks.VIOLET_CARPET,"violet");
        carpetBlockWithItem(ModBlocks.VIBRANT_PURPLE_CARPET,"vibrant_purple");
        carpetBlockWithItem(ModBlocks.BRIGHT_MAGENTA_CARPET,"bright_magenta");
        carpetBlockWithItem(ModBlocks.ROSE_CARPET,"rose");
        carpetBlockWithItem(ModBlocks.DARK_GRAY_CARPET,"dark_gray");
        carpetBlockWithItem(ModBlocks.SILVER_CARPET,"silver");
        carpetBlockWithItem(ModBlocks.ALPHA_WHITE_CARPET,"alpha_white");

        blockWithItem(ModBlocks.OAK_MOSAIC);
        blockWithItem(ModBlocks.SPRUCE_MOSAIC);
        blockWithItem(ModBlocks.BIRCH_MOSAIC);
        blockWithItem(ModBlocks.JUNGLE_MOSAIC);
        blockWithItem(ModBlocks.ACACIA_MOSAIC);
        blockWithItem(ModBlocks.DARK_OAK_MOSAIC);
        blockWithItem(ModBlocks.MANGROVE_MOSAIC);
        blockWithItem(ModBlocks.CHERRY_MOSAIC);
        blockWithItem(ModBlocks.CRIMSON_MOSAIC);
        blockWithItem(ModBlocks.WARPED_MOSAIC);

        bookshelfBlockWithItem(ModBlocks.SPRUCE_BOOKSHELF,"spruce");
        bookshelfBlockWithItem(ModBlocks.BIRCH_BOOKSHELF,"birch");
        bookshelfBlockWithItem(ModBlocks.JUNGLE_BOOKSHELF,"jungle");
        bookshelfBlockWithItem(ModBlocks.ACACIA_BOOKSHELF,"acacia");
        bookshelfBlockWithItem(ModBlocks.DARK_OAK_BOOKSHELF,"dark_oak");
        bookshelfBlockWithItem(ModBlocks.MANGROVE_BOOKSHELF,"mangrove");
        bookshelfBlockWithItem(ModBlocks.BAMBOO_BOOKSHELF,"bamboo");
        bookshelfBlockWithItem(ModBlocks.CHERRY_BOOKSHELF,"cherry");
        bookshelfBlockWithItem(ModBlocks.CRIMSON_BOOKSHELF,"crimson");
        bookshelfBlockWithItem(ModBlocks.WARPED_BOOKSHELF,"warped");

        craftingTableBlockWithItem(ModBlocks.SPRUCE_CRAFTING_TABLE,"spruce");
        craftingTableBlockWithItem(ModBlocks.BIRCH_CRAFTING_TABLE,"birch");
        craftingTableBlockWithItem(ModBlocks.JUNGLE_CRAFTING_TABLE,"jungle");
        craftingTableBlockWithItem(ModBlocks.ACACIA_CRAFTING_TABLE,"acacia");
        craftingTableBlockWithItem(ModBlocks.DARK_OAK_CRAFTING_TABLE,"dark_oak");
        craftingTableBlockWithItem(ModBlocks.MANGROVE_CRAFTING_TABLE,"mangrove");
        craftingTableBlockWithItem(ModBlocks.BAMBOO_CRAFTING_TABLE,"bamboo");
        craftingTableBlockWithItem(ModBlocks.CHERRY_CRAFTING_TABLE,"cherry");
        craftingTableBlockWithItem(ModBlocks.CRIMSON_CRAFTING_TABLE,"crimson");
        craftingTableBlockWithItem(ModBlocks.WARPED_CRAFTING_TABLE,"warped");

        for (RegistryObject<Block> plank : ModBlocks.COLORED_PLANKS) {
            int index = ModBlocks.COLORED_PLANKS.indexOf(plank);
            blockWithItem(plank);
            stairsWithItem(ModBlocks.COLORED_STAIRS.get(index), plank);
            slabWithItem(ModBlocks.COLORED_SLABS.get(index), plank);
            fenceWithItem(ModBlocks.COLORED_FENCES.get(index), plank.get());
            fenceGateWithItem(ModBlocks.COLORED_FENCE_GATES.get(index), plank.get());
            pressurePlateWithItem(ModBlocks.COLORED_PRESSURE_PLATES.get(index), plank);
            buttonWithItem(ModBlocks.COLORED_BUTTONS.get(index), plank);
            signBlock((StandingSignBlock) ModBlocks.COLORED_SIGNS.get(index).get(), (WallSignBlock) ModBlocks.COLORED_WALL_SIGNS.get(index).get(),
                    blockTexture(plank.get()));
        }

        for (RegistryObject<Block> strippedLog : ModBlocks.COLORED_STRIPPED_LOGS) {
            int index = ModBlocks.COLORED_STRIPPED_LOGS.indexOf(strippedLog);
            logWithItem(strippedLog);
            woodWithItem(ModBlocks.COLORED_STRIPPED_WOODS.get(index), strippedLog);
            hangingSignBlock(ModBlocks.COLORED_HANGING_SIGNS.get(index).get(), ModBlocks.COLORED_WALL_HANGING_SIGNS.get(index).get(),
                    blockTexture(strippedLog.get()));
        }

        for (RegistryObject<Block> log : ModBlocks.COLORED_LOGS) {
            int index = ModBlocks.COLORED_LOGS.indexOf(log);
            logWithItem(log);
            woodWithItem(ModBlocks.COLORED_WOODS.get(index), log);
        }

        for (RegistryObject<Block> door : ModBlocks.COLORED_DOORS) {
            doorBlockWithRenderType(((DoorBlock) door.get()),
                    modLoc("block/" + name(door.get()) + "_bottom"), modLoc("block/" + name(door.get()) + "_top"), "cutout");
        }

        for (RegistryObject<Block> trapdoor : ModBlocks.COLORED_TRAPDOORS) {
            trapdoorWithItem(trapdoor);
        }

        for (RegistryObject<Block> trapdoor : ModBlocks.COLORED_LEAVES) {
            leavesBlock(trapdoor);
        }

//        logWithItem(ModBlocks.WHITE_LOG);
//        woodWithItem(ModBlocks.WHITE_WOOD, ModBlocks.WHITE_LOG);
//        strippedLogWithItem(ModBlocks.STRIPPED_WHITE_LOG);
//        woodWithItem(ModBlocks.STRIPPED_WHITE_WOOD, ModBlocks.STRIPPED_WHITE_LOG);
//        blockWithItem(ModBlocks.WHITE_PLANKS);
//        leavesBlock(ModBlocks.WHITE_LEAVES);
//        stairsWithItem(ModBlocks.WHITE_STAIRS, ModBlocks.WHITE_PLANKS);
//        slabWithItem(ModBlocks.WHITE_SLAB, ModBlocks.WHITE_PLANKS);
//        buttonWithItem(ModBlocks.WHITE_BUTTON, ModBlocks.WHITE_PLANKS);
//        pressurePlateWithItem(ModBlocks.WHITE_PRESSURE_PLATE, ModBlocks.WHITE_PLANKS);
//        fenceWithItem(ModBlocks.WHITE_FENCE, ModBlocks.WHITE_PLANKS.get());
//        fenceGateWithItem(ModBlocks.WHITE_FENCE_GATE, ModBlocks.WHITE_PLANKS.get());
//        trapdoorWithItem(ModBlocks.WHITE_TRAPDOOR);
//        doorBlockWithRenderType(((DoorBlock) ModBlocks.WHITE_DOOR.get()),
//                modLoc("block/white_door_bottom"), modLoc("block/white_door_top"), "cutout");

//        signBlock((StandingSignBlock) ModBlocks.WHITE_SIGN.get(), (WallSignBlock) ModBlocks.WHITE_WALL_SIGN.get(),
//                blockTexture(ModBlocks.COLORED_PLANKS.get(0).get()));

//        hangingSignBlock(ModBlocks.WHITE_HANGING_SIGN.get(), ModBlocks.WHITE_WALL_HANGING_SIGN.get(),
//                blockTexture(ModBlocks.COLORED_PLANKS.get(0).get()));


//        logWithItem(ModBlocks.LIGHT_GRAY_LOG);
//        woodWithItem(ModBlocks.LIGHT_GRAY_WOOD, ModBlocks.LIGHT_GRAY_LOG);
//        strippedLogWithItem(ModBlocks.STRIPPED_LIGHT_GRAY_LOG);
//        woodWithItem(ModBlocks.STRIPPED_LIGHT_GRAY_WOOD, ModBlocks.STRIPPED_LIGHT_GRAY_LOG);
//        blockWithItem(ModBlocks.LIGHT_GRAY_PLANKS);
//        leavesBlock(ModBlocks.LIGHT_GRAY_LEAVES);
//        stairsWithItem(ModBlocks.LIGHT_GRAY_STAIRS, ModBlocks.LIGHT_GRAY_PLANKS);
//        slabWithItem(ModBlocks.LIGHT_GRAY_SLAB, ModBlocks.LIGHT_GRAY_PLANKS);
//        buttonWithItem(ModBlocks.LIGHT_GRAY_BUTTON, ModBlocks.LIGHT_GRAY_PLANKS);
//        pressurePlateWithItem(ModBlocks.LIGHT_GRAY_PRESSURE_PLATE, ModBlocks.LIGHT_GRAY_PLANKS);
//        fenceWithItem(ModBlocks.LIGHT_GRAY_FENCE, ModBlocks.LIGHT_GRAY_PLANKS.get());
//        fenceGateWithItem(ModBlocks.LIGHT_GRAY_FENCE_GATE, ModBlocks.LIGHT_GRAY_PLANKS.get());
//        trapdoorWithItem(ModBlocks.LIGHT_GRAY_TRAPDOOR);
//        doorBlockWithRenderType(((DoorBlock) ModBlocks.LIGHT_GRAY_DOOR.get()),
//                modLoc("block/light_gray_door_bottom"), modLoc("block/light_gray_door_top"), "cutout");


//        logWithItem(ModBlocks.GRAY_LOG);
//        woodWithItem(ModBlocks.GRAY_WOOD, ModBlocks.GRAY_LOG);
//        strippedLogWithItem(ModBlocks.STRIPPED_GRAY_LOG);
//        woodWithItem(ModBlocks.STRIPPED_GRAY_WOOD, ModBlocks.STRIPPED_GRAY_LOG);
//        blockWithItem(ModBlocks.GRAY_PLANKS);
//        leavesBlock(ModBlocks.GRAY_LEAVES);
//        stairsWithItem(ModBlocks.GRAY_STAIRS, ModBlocks.GRAY_PLANKS);
//        slabWithItem(ModBlocks.GRAY_SLAB, ModBlocks.GRAY_PLANKS);
//        buttonWithItem(ModBlocks.GRAY_BUTTON, ModBlocks.GRAY_PLANKS);
//        pressurePlateWithItem(ModBlocks.GRAY_PRESSURE_PLATE, ModBlocks.GRAY_PLANKS);
//        fenceWithItem(ModBlocks.GRAY_FENCE, ModBlocks.GRAY_PLANKS.get());
//        fenceGateWithItem(ModBlocks.GRAY_FENCE_GATE, ModBlocks.GRAY_PLANKS.get());
//        trapdoorWithItem(ModBlocks.GRAY_TRAPDOOR);
//        doorBlockWithRenderType(((DoorBlock) ModBlocks.GRAY_DOOR.get()),
//                modLoc("block/gray_door_bottom"), modLoc("block/gray_door_top"), "cutout");


//        logWithItem(ModBlocks.BLACK_LOG);
//        woodWithItem(ModBlocks.BLACK_WOOD, ModBlocks.BLACK_LOG);
//        strippedLogWithItem(ModBlocks.STRIPPED_BLACK_LOG);
//        woodWithItem(ModBlocks.STRIPPED_BLACK_WOOD, ModBlocks.STRIPPED_BLACK_LOG);
//        blockWithItem(ModBlocks.BLACK_PLANKS);
//        leavesBlock(ModBlocks.BLACK_LEAVES);
//        stairsWithItem(ModBlocks.BLACK_STAIRS, ModBlocks.BLACK_PLANKS);
//        slabWithItem(ModBlocks.BLACK_SLAB, ModBlocks.BLACK_PLANKS);
//        buttonWithItem(ModBlocks.BLACK_BUTTON, ModBlocks.BLACK_PLANKS);
//        pressurePlateWithItem(ModBlocks.BLACK_PRESSURE_PLATE, ModBlocks.BLACK_PLANKS);
//        fenceWithItem(ModBlocks.BLACK_FENCE, ModBlocks.BLACK_PLANKS.get());
//        fenceGateWithItem(ModBlocks.BLACK_FENCE_GATE, ModBlocks.BLACK_PLANKS.get());
//        trapdoorWithItem(ModBlocks.BLACK_TRAPDOOR);
//        doorBlockWithRenderType(((DoorBlock) ModBlocks.BLACK_DOOR.get()),
//                modLoc("block/black_door_bottom"), modLoc("block/black_door_top"), "cutout");
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private void leavesBlock(RegistryObject<Block> pLeaves) {
        simpleBlockWithItem(pLeaves.get(),
                models().singleTexture(name(pLeaves.get()), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(pLeaves.get())).renderType("cutout"));
    }

    private void logWithItem(RegistryObject<Block> pLog) {
        logBlock(((RotatedPillarBlock) pLog.get()));
        blockItem(pLog);
    }

    private void woodWithItem(RegistryObject<Block> pWood, RegistryObject<Block> texture) {
        axisBlock(((RotatedPillarBlock) pWood.get()), blockTexture(texture.get()), blockTexture(texture.get()));
        blockItem(pWood);
    }

    private void strippedLogWithItem(RegistryObject<Block> strippedLog) {
        axisBlock(((RotatedPillarBlock) strippedLog.get()), blockTexture(strippedLog.get()),
                new ResourceLocation(Extension.MOD_ID, "block/" + name(strippedLog.get()) + "_top"));
        blockItem(strippedLog);
    }

    private void stairsWithItem(RegistryObject<Block> pStairs, RegistryObject<Block> texture) {
        stairsBlock(((StairBlock) pStairs.get()), blockTexture(texture.get()));
        blockItem(pStairs);
    }

    private void stairsWithItem(RegistryObject<Block> pStairs, String side, String bottom, String top) {
        stairsBlock(((StairBlock) pStairs.get()),
                new ResourceLocation(Extension.MOD_ID,"block/" + side),
                new ResourceLocation(Extension.MOD_ID,"block/" + bottom),
                new ResourceLocation(Extension.MOD_ID,"block/" + top));
        blockItem(pStairs);
    }

    private void slabWithItem(RegistryObject<Block> blockRegistryObject, RegistryObject<Block> texture) {
        slabBlock(((SlabBlock) blockRegistryObject.get()), blockTexture(texture.get()), blockTexture(texture.get()));
        blockItem(blockRegistryObject);
    }

    private void slabWithItem(RegistryObject<Block> blockRegistryObject, RegistryObject<Block> doubleslab, String side, String bottom, String top) {
        slabBlock(((SlabBlock) blockRegistryObject.get()), blockTexture(doubleslab.get()),
                new ResourceLocation(Extension.MOD_ID,"block/" + side),
                new ResourceLocation(Extension.MOD_ID,"block/" + bottom),
                new ResourceLocation(Extension.MOD_ID,"block/" + top));
        blockItem(blockRegistryObject);
    }

    private void pressurePlateWithItem(RegistryObject<Block> blockRegistryObject, RegistryObject<Block> texture) {
        pressurePlateBlock(((PressurePlateBlock) blockRegistryObject.get()), blockTexture(texture.get()));
        blockItem(blockRegistryObject);
    }

    private void fenceGateWithItem(RegistryObject<Block> blockRegistryObject, Block texture) {
        fenceGateBlock(((FenceGateBlock) blockRegistryObject.get()), blockTexture(texture));
        blockItem(blockRegistryObject);
    }

    private void trapdoorWithItem(RegistryObject<Block> trapdoor) {
        trapdoorBlockWithRenderType(((TrapDoorBlock) trapdoor.get()), blockTexture(trapdoor.get()), true, "cutout");
        simpleBlockItem(trapdoor.get(), new ModelFile.UncheckedModelFile(Extension.MOD_ID +
                ":block/" + name(trapdoor.get()) + "_bottom"));
    }

    private void buttonWithItem(RegistryObject<Block> button, RegistryObject<Block> texture) {
        buttonBlock(((ButtonBlock) button.get()), blockTexture(texture.get()));
        simpleBlockItem(button.get(),
                models().singleTexture(name(button.get()) + "_inventory",
                        new ResourceLocation("minecraft:block/button_inventory"),
                        "texture", blockTexture(texture.get())));
    }

    private void wallWithItem(RegistryObject<Block> wall, Block texture) {
        wallBlock(((WallBlock) wall.get()), blockTexture(texture));
        simpleBlockItem(wall.get(),
                models().wallInventory(name(wall.get()) + "_inventory", blockTexture(texture)));
    }

    private void wallWithItem(RegistryObject<Block> wall, String texture) {
        wallBlock(((WallBlock) wall.get()), new ResourceLocation("block/" + texture));
        simpleBlockItem(wall.get(),
                models().wallInventory(name(wall.get()) + "_inventory", new ResourceLocation("block/" + texture)));
    }

    private void fenceWithItem(RegistryObject<Block> fence, Block texture) {
        fenceBlock(((FenceBlock) fence.get()), blockTexture(texture));
        simpleBlockItem(fence.get(),
                models().singleTexture(name(fence.get()) + "_inventory",
                        new ResourceLocation("minecraft:block/fence_inventory"),
                        "texture", blockTexture(texture)));
    }

    private void pillarBlockWithItem(RegistryObject<Block> blockRegistryObject, String side, String bottom, String top) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().cubeBottomTop(name(blockRegistryObject.get()),
                        new ResourceLocation(Extension.MOD_ID,"block/" + side),
                        new ResourceLocation(Extension.MOD_ID,"block/" + bottom),
                        new ResourceLocation(Extension.MOD_ID,"block/" + top)));
    }

    private void glazedBlockWithItem(RegistryObject<Block> glazedBlock) {
        horizontalBlock(glazedBlock.get(),
                models().withExistingParent(name(glazedBlock.get()), mcLoc("block/template_glazed_terracotta"))
                        .texture("pattern", blockTexture(glazedBlock.get())),0);
        blockItem(glazedBlock);
    }

    private void columnBlockWithItem(RegistryObject<Block> blockRegistryObject, String side, String end) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().cubeColumn(name(blockRegistryObject.get()),
                        new ResourceLocation(Extension.MOD_ID,"block/" + side),
                        new ResourceLocation(Extension.MOD_ID,"block/" + end)));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(Extension.MOD_ID +
                ":block/" + name(blockRegistryObject.get())));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void carpetBlockWithItem(RegistryObject<Block> blockRegistryObject, String color) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().carpet(color + "_carpet", new ResourceLocation(Extension.MOD_ID,"block/"+ color + "_wool")));
    }

    private void bookshelfBlockWithItem(RegistryObject<Block> blockRegistryObject, String woodType) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().cubeColumn(woodType + "_bookshelf", new ResourceLocation(Extension.MOD_ID, "block/" + woodType + "_bookshelf"),
                        new ResourceLocation("minecraft","block/" + woodType + "_planks")));
    }
    private BlockModelBuilder cubeWithParticleTexture(String name, ResourceLocation particle, ResourceLocation down, ResourceLocation up, ResourceLocation north, ResourceLocation south, ResourceLocation east, ResourceLocation west) {
        return models().withExistingParent(name, "cube")
                .texture("particle", particle)
                .texture("down", down)
                .texture("up", up)
                .texture("north", north)
                .texture("south", south)
                .texture("east", east)
                .texture("west", west);
    }

    private void craftingTableBlockWithItem(RegistryObject<Block> blockRegistryObject, String woodType) {
        simpleBlockWithItem(blockRegistryObject.get(),
                cubeWithParticleTexture(woodType + "_crafting_table",
                        new ResourceLocation(Extension.MOD_ID, "block/" + woodType + "_crafting_table_front"),
                        new ResourceLocation("minecraft", "block/" + woodType + "_planks"),
                        new ResourceLocation(Extension.MOD_ID, "block/" + woodType + "_crafting_table_top"),
                        new ResourceLocation(Extension.MOD_ID, "block/" + woodType + "_crafting_table_front"),
                        new ResourceLocation(Extension.MOD_ID, "block/" + woodType + "_crafting_table_side"),
                        new ResourceLocation(Extension.MOD_ID, "block/" + woodType + "_crafting_table_side"),
                        new ResourceLocation(Extension.MOD_ID, "block/" + woodType + "_crafting_table_front")));
    }

    private void lantern(RegistryObject<Block> pLantern){
        getVariantBuilder(pLantern.get())
                .partialState().with(LanternBlock.HANGING, false)
                .modelForState().modelFile(models().withExistingParent(name(pLantern.get()), mcLoc("block/template_lantern"))
                        .renderType("cutout")
                        .texture("lantern", blockTexture(pLantern.get()))).addModel()
                .partialState().with(LanternBlock.HANGING, true)
                .modelForState().modelFile(models().withExistingParent(name(pLantern.get()) + "_hanging", mcLoc("block/template_hanging_lantern"))
                        .renderType("cutout")
                        .texture("lantern", blockTexture(pLantern.get()))).addModel();
    }
//    private void craftingTableBlockWithItem(RegistryObject<Block> blockRegistryObject, String woodType) {
//        simpleBlockWithItem(blockRegistryObject.get(),
//                models().cube(woodType + "_crafting_table",
//                        new ResourceLocation("minecraft", "block/" + woodType + "_planks"),
//                        new ResourceLocation(Extension.MOD_ID, "block/" + woodType + "_crafting_table_top"),
//                        new ResourceLocation(Extension.MOD_ID, "block/" + woodType + "_crafting_table_front"),
//                        new ResourceLocation(Extension.MOD_ID, "block/" + woodType + "_crafting_table_side"),
//                        new ResourceLocation(Extension.MOD_ID, "block/" + woodType + "_crafting_table_side"),
//                        new ResourceLocation(Extension.MOD_ID, "block/" + woodType + "_crafting_table_front")));
//    }
}
