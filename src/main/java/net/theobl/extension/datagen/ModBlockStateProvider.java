package net.theobl.extension.datagen;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Extension.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        wallBlockWithItem(ModBlocks.STONE_WALL, Blocks.STONE);
        wallBlockWithItem(ModBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE);
        wallBlockWithItem(ModBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE);
        wallBlockWithItem(ModBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE);
        wallBlockWithItem(ModBlocks.PRISMARINE_BRICK_WALL, Blocks.PRISMARINE_BRICKS);
        wallBlockWithItem(ModBlocks.DARK_PRISMARINE_WALL, Blocks.DARK_PRISMARINE);
        wallBlockWithItem(ModBlocks.PURPUR_WALL, Blocks.PURPUR_BLOCK);
        wallBlockWithItem(ModBlocks.QUARTZ_WALL, Blocks.QUARTZ_BLOCK, "_top");

        stairsBlockWithItem(ModBlocks.SMOOTH_STONE_STAIRS, Blocks.SMOOTH_STONE);
        stairsBlockWithItem(ModBlocks.NETHERITE_STAIRS, Blocks.NETHERITE_BLOCK);

        paneBlock((IronBarsBlock) ModBlocks.TINTED_GLASS_PANE.get(),
                blockTexture(Blocks.TINTED_GLASS), modLoc("block/tinted_glass_pane_top"));

        blockWithItem(ModBlocks.POLISHED_STONE);
        stairsBlockWithItem(ModBlocks.POLISHED_STONE_STAIRS, ModBlocks.POLISHED_STONE.get());
        slabBlockWithItem(ModBlocks.POLISHED_STONE_SLAB, ModBlocks.POLISHED_STONE.get());
        wallBlockWithItem(ModBlocks.POLISHED_STONE_WALL, ModBlocks.POLISHED_STONE.get());

        stairsBlockWithItem(ModBlocks.SMOOTH_BASALT_STAIRS, Blocks.SMOOTH_BASALT);
        slabBlockWithItem(ModBlocks.SMOOTH_BASALT_SLAB, Blocks.SMOOTH_BASALT);

        stairsBlockWithItem(ModBlocks.QUARTZ_BRICK_STAIRS, Blocks.QUARTZ_BRICKS);
        slabBlockWithItem(ModBlocks.QUARTZ_BRICK_SLAB, Blocks.QUARTZ_BRICKS);
        wallBlockWithItem(ModBlocks.QUARTZ_BRICK_WALL, Blocks.QUARTZ_BRICKS);

        blockWithItem(ModBlocks.NETHER_BRICK_TILES);
        stairsBlockWithItem(ModBlocks.NETHER_BRICK_TILE_STAIRS, ModBlocks.NETHER_BRICK_TILES.get());
        slabBlockWithItem(ModBlocks.NETHER_BRICK_TILE_SLAB, ModBlocks.NETHER_BRICK_TILES.get());
        wallBlockWithItem(ModBlocks.NETHER_BRICK_TILE_WALL, ModBlocks.NETHER_BRICK_TILES.get());

        blockWithItem(ModBlocks.CRACKED_RED_NETHER_BRICKS);
        fenceBlockWithItem(ModBlocks.RED_NETHER_BRICK_FENCE, Blocks.RED_NETHER_BRICKS);
        blockWithItem(ModBlocks.CHISELED_RED_NETHER_BRICKS);
        blockWithItem(ModBlocks.RED_NETHER_BRICK_TILES);
        stairsBlockWithItem(ModBlocks.RED_NETHER_BRICK_TILE_STAIRS, ModBlocks.RED_NETHER_BRICK_TILES.get());
        slabBlockWithItem(ModBlocks.RED_NETHER_BRICK_TILE_SLAB, ModBlocks.RED_NETHER_BRICK_TILES.get());
        wallBlockWithItem(ModBlocks.RED_NETHER_BRICK_TILE_WALL, ModBlocks.RED_NETHER_BRICK_TILES.get());

        createCrop((BushBlock) ModBlocks.BLUE_NETHER_WART.get(), NetherWartBlock.AGE, 0, 1, 1, 2);

        blockWithItem(ModBlocks.BLUE_NETHER_BRICKS);
        blockWithItem(ModBlocks.CRACKED_BLUE_NETHER_BRICKS);
        stairsBlockWithItem(ModBlocks.BLUE_NETHER_BRICK_STAIRS, ModBlocks.BLUE_NETHER_BRICKS.get());
        slabBlockWithItem(ModBlocks.BLUE_NETHER_BRICK_SLAB, ModBlocks.BLUE_NETHER_BRICKS.get());
        wallBlockWithItem(ModBlocks.BLUE_NETHER_BRICK_WALL, ModBlocks.BLUE_NETHER_BRICKS.get());
        fenceBlockWithItem(ModBlocks.BLUE_NETHER_BRICK_FENCE, ModBlocks.BLUE_NETHER_BRICKS.get());
        blockWithItem(ModBlocks.CHISELED_BLUE_NETHER_BRICKS);
        blockWithItem(ModBlocks.BLUE_NETHER_BRICK_TILES);
        stairsBlockWithItem(ModBlocks.BLUE_NETHER_BRICK_TILE_STAIRS, ModBlocks.BLUE_NETHER_BRICK_TILES.get());
        slabBlockWithItem(ModBlocks.BLUE_NETHER_BRICK_TILE_SLAB, ModBlocks.BLUE_NETHER_BRICK_TILES.get());
        wallBlockWithItem(ModBlocks.BLUE_NETHER_BRICK_TILE_WALL, ModBlocks.BLUE_NETHER_BRICK_TILES.get());

        simpleBlockWithItem(ModBlocks.SOUL_SANDSTONE.get(),
                models().cubeBottomTop(name(ModBlocks.SOUL_SANDSTONE.get()),
                        blockTexture(ModBlocks.SOUL_SANDSTONE.get()),
                        extend(blockTexture(ModBlocks.SOUL_SANDSTONE.get()), "_bottom"),
                        extend(blockTexture(ModBlocks.SOUL_SANDSTONE.get()),"_top")));
        stairsBlock((StairBlock) ModBlocks.SOUL_SANDSTONE_STAIRS.get(),
                name(ModBlocks.SOUL_SANDSTONE.get()),
                blockTexture(ModBlocks.SOUL_SANDSTONE.get()),
                extend(blockTexture(ModBlocks.SOUL_SANDSTONE.get()), "_bottom"),
                extend(blockTexture(ModBlocks.SOUL_SANDSTONE.get()),"_top"));
        blockItem(ModBlocks.SOUL_SANDSTONE_STAIRS);
        slabBlock((SlabBlock) ModBlocks.SOUL_SANDSTONE_SLAB.get(),
                blockTexture(ModBlocks.SOUL_SANDSTONE.get()),
                blockTexture(ModBlocks.SOUL_SANDSTONE.get()),
                extend(blockTexture(ModBlocks.SOUL_SANDSTONE.get()), "_bottom"),
                extend(blockTexture(ModBlocks.SOUL_SANDSTONE.get()),"_top"));
        blockItem(ModBlocks.SOUL_SANDSTONE_SLAB);
        wallBlockWithItem(ModBlocks.SOUL_SANDSTONE_WALL, ModBlocks.SOUL_SANDSTONE.get());


        simpleBlockWithItem(ModBlocks.CHISELED_SOUL_SANDSTONE.get(),
                models().cubeColumn(name(ModBlocks.CHISELED_SOUL_SANDSTONE.get()),
                        blockTexture(ModBlocks.CHISELED_SOUL_SANDSTONE.get()),
                        extend(blockTexture(ModBlocks.SOUL_SANDSTONE.get()),"_top")));

        simpleBlockWithItem(ModBlocks.SMOOTH_SOUL_SANDSTONE.get(),
                models().cubeAll(name(ModBlocks.SMOOTH_SOUL_SANDSTONE.get()), extend(blockTexture(ModBlocks.SOUL_SANDSTONE.get()), "_top")));
        stairsBlockWithItem(ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS, ModBlocks.SOUL_SANDSTONE.get(), "_top");
        slabBlockWithItem(ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB, ModBlocks.SMOOTH_SOUL_SANDSTONE.get(), ModBlocks.SOUL_SANDSTONE.get(), "_top");

        simpleBlockWithItem(ModBlocks.CUT_SOUL_SANDSTONE.get(),
                models().cubeColumn(name(ModBlocks.CUT_SOUL_SANDSTONE.get()),
                        blockTexture(ModBlocks.CUT_SOUL_SANDSTONE.get()),
                        extend(blockTexture(ModBlocks.SOUL_SANDSTONE.get()),"_top")));
        slabBlock((SlabBlock) ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get(),
                blockTexture(ModBlocks.CUT_SOUL_SANDSTONE.get()),
                blockTexture(ModBlocks.CUT_SOUL_SANDSTONE.get()),
                extend(blockTexture(ModBlocks.SOUL_SANDSTONE.get()), "_top"),
                extend(blockTexture(ModBlocks.SOUL_SANDSTONE.get()),"_top"));
        blockItem(ModBlocks.CUT_SOUL_SANDSTONE_SLAB);

        horizontalBlock(ModBlocks.SOUL_O_LANTERN.get(), mcLoc("block/pumpkin_side"), modLoc("block/soul_o_lantern"), mcLoc("block/pumpkin_top"));
        blockItem(ModBlocks.SOUL_O_LANTERN);
        horizontalBlock(ModBlocks.REDSTONE_O_LANTERN.get(), mcLoc("block/pumpkin_side"), modLoc("block/redstone_o_lantern"), mcLoc("block/pumpkin_top"));
        blockItem(ModBlocks.REDSTONE_O_LANTERN);

        lantern();
        campfire(ModBlocks.REDSTONE_CAMPFIRE);
    }

    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation extend(ResourceLocation rl, String suffix) {
        return ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), rl.getPath() + suffix);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void stairsBlockWithItem(DeferredBlock<?> deferredBlock, Block texture) {
        stairsBlockWithItem(deferredBlock, texture, "");
    }

    private void stairsBlockWithItem(DeferredBlock<?> deferredBlock, Block texture, String textureAppendix) {
        stairsBlock((StairBlock) deferredBlock.get(), extend(blockTexture(texture), textureAppendix));
        blockItem(deferredBlock);
    }

    private void slabBlockWithItem(DeferredBlock<?> deferredBlock, Block texture) {
        slabBlockWithItem(deferredBlock, texture, texture, "");
    }

    private void slabBlockWithItem(DeferredBlock<?> deferredBlock, Block doubleSlab, Block texture, String textureAppendix) {
        slabBlock((SlabBlock) deferredBlock.get(), blockTexture(doubleSlab), extend(blockTexture(texture), textureAppendix));
        blockItem(deferredBlock);
    }

    private void wallBlockWithItem(DeferredBlock<?> deferredBlock, Block texture) {
        wallBlockWithItem(deferredBlock, texture, "");
    }

    private void wallBlockWithItem(DeferredBlock<?> deferredBlock, Block texture, String textureAppendix) {
        wallBlock((WallBlock) deferredBlock.get(), extend(blockTexture(texture), textureAppendix));
        simpleBlockItem(deferredBlock.get(), itemModels().wallInventory("block/" + name(deferredBlock.get()) + "_inventory", extend(blockTexture(texture), textureAppendix)));
    }


    private void fenceBlockWithItem(DeferredBlock<?> deferredBlock, Block texture) {
        fenceBlock((FenceBlock) deferredBlock.get(), blockTexture(texture));
        simpleBlockItem(deferredBlock.get(), itemModels().fenceInventory("block/" + name(deferredBlock.get()) + "_inventory", blockTexture(texture)));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("extension:block/" + deferredBlock.getId().getPath()));
    }

    public void createCrop(BushBlock block, IntegerProperty ageProperties, int... ageToVisualStageMapping) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, name(block) + "_stage", ageProperties, ageToVisualStageMapping);
        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, String name, IntegerProperty ageProperties, int... ageToVisualStageMapping) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(name + ageToVisualStageMapping[state.getValue(ageProperties)],
                ResourceLocation.fromNamespaceAndPath(Extension.MODID, "block/" + name + ageToVisualStageMapping[state.getValue(ageProperties)]))
                .renderType("cutout"));
        return models;
    }

    private void lantern(){
        DeferredBlock<Block> lantern = ModBlocks.REDSTONE_LANTERN;
        getVariantBuilder(lantern.get())
                .partialState().with(LanternBlock.HANGING, false).with(RedstoneTorchBlock.LIT, false)
                .modelForState().modelFile(models().withExistingParent(name(lantern.get()) + "_off", mcLoc("block/template_lantern"))
                        .renderType("cutout")
                        .texture("lantern", blockTexture(lantern.get()) + "_off")).addModel()
                .partialState().with(LanternBlock.HANGING, false).with(RedstoneTorchBlock.LIT, true)
                .modelForState().modelFile(models().withExistingParent(name(lantern.get()), mcLoc("block/template_lantern"))
                        .renderType("cutout")
                        .texture("lantern", blockTexture(lantern.get()))).addModel()
                .partialState().with(LanternBlock.HANGING, true).with(RedstoneTorchBlock.LIT, false)
                .modelForState().modelFile(models().withExistingParent(name(lantern.get()) + "_hanging_off", mcLoc("block/template_hanging_lantern"))
                        .renderType("cutout")
                        .texture("lantern", blockTexture(lantern.get()) + "_off")).addModel()
                .partialState().with(LanternBlock.HANGING, true).with(RedstoneTorchBlock.LIT, true)
                .modelForState().modelFile(models().withExistingParent(name(lantern.get()) + "_hanging", mcLoc("block/template_hanging_lantern"))
                        .renderType("cutout")
                        .texture("lantern", blockTexture(lantern.get()))).addModel();
    }

    private void campfire(DeferredBlock<Block> campfire){
        getVariantBuilder(campfire.get())
                .partialState().with(CampfireBlock.FACING, Direction.EAST).with(CampfireBlock.LIT, false)
                .modelForState().rotationY(270).modelFile(models().getExistingFile(mcLoc("minecraft:block/campfire_off"))).addModel()
                .partialState().with(CampfireBlock.FACING, Direction.EAST).with(CampfireBlock.LIT, true)
                .modelForState().rotationY(270).modelFile(models().withExistingParent(name(campfire.get()), mcLoc("block/template_campfire"))
                        .renderType("cutout")
                        .texture("fire", blockTexture(campfire.get()) + "_fire")
                        .texture("lit_log", blockTexture(campfire.get()) + "_log_lit")).addModel()

                .partialState().with(CampfireBlock.FACING, Direction.NORTH).with(CampfireBlock.LIT, false)
                .modelForState().rotationY(180).modelFile(models().getExistingFile(mcLoc("minecraft:block/campfire_off"))).addModel()
                .partialState().with(CampfireBlock.FACING, Direction.NORTH).with(CampfireBlock.LIT, true)
                .modelForState().rotationY(180).modelFile(models().withExistingParent(name(campfire.get()), mcLoc("block/template_campfire"))
                        .renderType("cutout")
                        .texture("fire", blockTexture(campfire.get()) + "_fire")
                        .texture("lit_log", blockTexture(campfire.get()) + "_log_lit")).addModel()

                .partialState().with(CampfireBlock.FACING, Direction.SOUTH).with(CampfireBlock.LIT, false)
                .modelForState().modelFile(models().getExistingFile(mcLoc("minecraft:block/campfire_off"))).addModel()
                .partialState().with(CampfireBlock.FACING, Direction.SOUTH).with(CampfireBlock.LIT, true)
                .modelForState().modelFile(models().withExistingParent(name(campfire.get()), mcLoc("block/template_campfire"))
                        .renderType("cutout")
                        .texture("fire", blockTexture(campfire.get()) + "_fire")
                        .texture("lit_log", blockTexture(campfire.get()) + "_log_lit")).addModel()

                .partialState().with(CampfireBlock.FACING, Direction.WEST).with(CampfireBlock.LIT, false)
                .modelForState().rotationY(90).modelFile(models().getExistingFile(mcLoc("minecraft:block/campfire_off"))).addModel()
                .partialState().with(CampfireBlock.FACING, Direction.WEST).with(CampfireBlock.LIT, true)
                .modelForState().rotationY(90).modelFile(models().withExistingParent(name(campfire.get()), mcLoc("block/template_campfire"))
                        .renderType("cutout")
                        .texture("fire", blockTexture(campfire.get()) + "_fire")
                        .texture("lit_log", blockTexture(campfire.get()) + "_log_lit")).addModel();
    }
}
