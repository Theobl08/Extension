package net.theobl.extension.datagen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.model.SingleVariant;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.generators.blockstate.CompositeBlockStateModelBuilder;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.block.PotionCauldronBlock;
import net.theobl.extension.item.ModItems;

import java.util.List;
import java.util.Map;

import static net.minecraft.client.data.models.BlockModelGenerators.*;

public class ModModelProvider extends ModelProvider {
    static ModelTemplate CAULDRON_CONTENT_LEVEL1 = ModelTemplates.create(
            Extension.MODID + ":template_cauldron_content_level1", TextureSlot.CONTENT);
    static ModelTemplate CAULDRON_CONTENT_LEVEL2 = ModelTemplates.create(
            Extension.MODID + ":template_cauldron_content_level2", TextureSlot.CONTENT);
    static ModelTemplate CAULDRON_CONTENT_LEVEL3 = ModelTemplates.create(
            Extension.MODID + ":template_cauldron_content_level3", TextureSlot.CONTENT);
    static ModelTemplate CAULDRON_CONTENT_FULL = ModelTemplates.create(
            Extension.MODID + ":template_cauldron_content_full", TextureSlot.CONTENT);

    public ModModelProvider(PackOutput output) {
        super(output, Extension.MODID);
    }

    public final List<BlockFamily> familyWithExistingFullBlock = ImmutableList.of(ModBlockFamilies.QUARTZ_BRICKS, ModBlockFamilies.SMOOTH_BASALT);

    public final Map<Block, TexturedModel> texturedModels = ImmutableMap.<Block, TexturedModel>builder()
            .put(ModBlocks.SOUL_SANDSTONE.get(), TexturedModel.TOP_BOTTOM_WITH_WALL.get(ModBlocks.SOUL_SANDSTONE.get()))
            .put(ModBlocks.SMOOTH_SOUL_SANDSTONE.get(), TexturedModel.createAllSame(TextureMapping.getBlockTexture(ModBlocks.SOUL_SANDSTONE.get(), "_top")))
            .put(
                    ModBlocks.CUT_SOUL_SANDSTONE.get(),
                    TexturedModel.COLUMN
                            .get(ModBlocks.SOUL_SANDSTONE.get())
                            .updateTextures(textureMapping -> textureMapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(ModBlocks.CUT_SOUL_SANDSTONE.get())))
            )
            .put(Blocks.QUARTZ_BLOCK, TexturedModel.COLUMN.get(Blocks.QUARTZ_BLOCK))
            .put(ModBlocks.CHISELED_SOUL_SANDSTONE.get(), TexturedModel.COLUMN.get(ModBlocks.CHISELED_SOUL_SANDSTONE.get()).updateTextures(textureMapping -> {
                textureMapping.put(TextureSlot.END, TextureMapping.getBlockTexture(ModBlocks.SOUL_SANDSTONE.get(), "_top"));
                textureMapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(ModBlocks.CHISELED_SOUL_SANDSTONE.get()));
            }))
            .build();

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        ModBlockFamilies.getAllFamilies()
                .filter(BlockFamily::shouldGenerateModel)
                .forEach(blockFamily -> {
                    if(familyWithExistingFullBlock.contains(blockFamily))
                        blockModels.familyWithExistingFullBlock(blockFamily.getBaseBlock()).generateFor(blockFamily);
                    else
                        family(blockFamily.getBaseBlock(), blockModels).generateFor(blockFamily);
                });

        itemModels.generateFlatItem(ModItems.SPAWNER_MINECART.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RED_NETHER_BRICK.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BLUE_NETHER_BRICK.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ILLUSIONER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);

        this.createGlassPaneBlocks(Blocks.TINTED_GLASS, ModBlocks.TINTED_GLASS_PANE.get(), blockModels);

        createWall(ModBlocks.STONE_WALL.get(), TextureMapping.getBlockTexture(Blocks.STONE), blockModels);
        createWall(ModBlocks.POLISHED_GRANITE_WALL.get(), TextureMapping.getBlockTexture(Blocks.POLISHED_GRANITE), blockModels);
        createWall(ModBlocks.POLISHED_DIORITE_WALL.get(), TextureMapping.getBlockTexture(Blocks.POLISHED_DIORITE), blockModels);
        createWall(ModBlocks.POLISHED_ANDESITE_WALL.get(), TextureMapping.getBlockTexture(Blocks.POLISHED_ANDESITE), blockModels);
        createWall(ModBlocks.PRISMARINE_BRICK_WALL.get(), TextureMapping.getBlockTexture(Blocks.PRISMARINE_BRICKS), blockModels);
        createWall(ModBlocks.DARK_PRISMARINE_WALL.get(), TextureMapping.getBlockTexture(Blocks.DARK_PRISMARINE), blockModels);
        createWall(ModBlocks.PURPUR_WALL.get(), TextureMapping.getBlockTexture(Blocks.PURPUR_BLOCK), blockModels);
        createWall(ModBlocks.QUARTZ_WALL.get(), TextureMapping.getBlockTexture(Blocks.QUARTZ_BLOCK, "_top"), blockModels);

        blockModels.createTrivialCube(ModBlocks.CHISELED_GRANITE.get());
        blockModels.createTrivialCube(ModBlocks.CHISELED_DIORITE.get());
        blockModels.createTrivialCube(ModBlocks.CHISELED_ANDESITE.get());
        blockModels.createTrivialCube(ModBlocks.CHISELED_BRICKS.get());
        blockModels.createTrivialCube(ModBlocks.CHISELED_MUD_BRICKS.get());
        blockModels.createTrivialCube(ModBlocks.CHISELED_PRISMARINE.get());
        blockModels.createTrivialCube(ModBlocks.CHISELED_END_STONE_BRICKS.get());

        blockModels.familyWithExistingFullBlock(Blocks.SMOOTH_STONE).stairs(ModBlocks.SMOOTH_STONE_STAIRS.get());
        blockModels.familyWithExistingFullBlock(Blocks.NETHERITE_BLOCK).stairs(ModBlocks.NETHERITE_STAIRS.get());

        blockModels.createTrivialCube(ModBlocks.CRACKED_RED_NETHER_BRICKS.get());
        blockModels.familyWithExistingFullBlock(Blocks.RED_NETHER_BRICKS).fence(ModBlocks.RED_NETHER_BRICK_FENCE.get());
        blockModels.createTrivialCube(ModBlocks.CHISELED_RED_NETHER_BRICKS.get());

        blockModels.familyWithExistingFullBlock(ModBlocks.BLUE_NETHER_BRICKS.get()).fence(ModBlocks.BLUE_NETHER_BRICK_FENCE.get());

        this.createCropBlock(ModBlocks.BLUE_NETHER_WART.get(), BlockStateProperties.AGE_3, blockModels, 0, 1, 1, 2);
        this.createRedStoneLantern(ModBlocks.REDSTONE_LANTERN.get(), blockModels);
        this.createCampfires(blockModels, ModBlocks.REDSTONE_CAMPFIRE.get(), ModBlocks.COPPER_CAMPFIRE.get());
        createPumpkins(blockModels);
        createCauldrons(blockModels);
        createFire(ModBlocks.COPPER_FIRE.get(), blockModels);
    }

    public BlockModelGenerators.BlockFamilyProvider family(Block block, BlockModelGenerators blockModels) {
        TexturedModel texturedmodel = this.texturedModels.getOrDefault(block, TexturedModel.CUBE.get(block));
        return new ModBlockFamilyProvider(texturedmodel.getMapping(), blockModels).fullBlock(block, texturedmodel.getTemplate());
    }

    public void createWall(Block wallBlock, Identifier texture, BlockModelGenerators blockModels) {
        TextureMapping mapping = new TextureMapping().put(TextureSlot.WALL, texture);
        Identifier post = ModelTemplates.WALL_POST.create(wallBlock, mapping, blockModels.modelOutput);
        Identifier low = ModelTemplates.WALL_LOW_SIDE.create(wallBlock, mapping, blockModels.modelOutput);
        Identifier high = ModelTemplates.WALL_TALL_SIDE.create(wallBlock, mapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createWall(wallBlock, plainVariant(post), plainVariant(low), plainVariant(high)));
        Identifier inventory = ModelTemplates.WALL_INVENTORY.create(wallBlock, mapping, blockModels.modelOutput);
        blockModels.registerSimpleItemModel(wallBlock, inventory);
    }

    public void createGlassPaneBlocks(Block glassBlock, Block paneBlock, BlockModelGenerators blockModels) {
        TextureMapping texturemapping = TextureMapping.pane(glassBlock, paneBlock);
        Identifier post = ModelTemplates.STAINED_GLASS_PANE_POST.create(paneBlock, texturemapping, blockModels.modelOutput);
        Identifier side = ModelTemplates.STAINED_GLASS_PANE_SIDE.create(paneBlock, texturemapping, blockModels.modelOutput);
        Identifier sideAlt = ModelTemplates.STAINED_GLASS_PANE_SIDE_ALT.create(paneBlock, texturemapping, blockModels.modelOutput);
        Identifier noSide = ModelTemplates.STAINED_GLASS_PANE_NOSIDE.create(paneBlock, texturemapping, blockModels.modelOutput);
        Identifier noSideAlt = ModelTemplates.STAINED_GLASS_PANE_NOSIDE_ALT.create(paneBlock, texturemapping, blockModels.modelOutput);
        Item item = paneBlock.asItem();
        blockModels.registerSimpleItemModel(item, blockModels.createFlatItemModelWithBlockTexture(item, glassBlock));
        blockModels.blockStateOutput
                .accept(
                        MultiPartGenerator.multiPart(paneBlock)
                                .with(plainVariant(post))
                                .with(condition().term(BlockStateProperties.NORTH, true), plainVariant(side))
                                .with(condition().term(BlockStateProperties.EAST, true),
                                        plainVariant(side).with(Y_ROT_90)
                                )
                                .with(condition().term(BlockStateProperties.SOUTH, true), plainVariant(sideAlt))
                                .with(
                                        condition().term(BlockStateProperties.WEST, true),
                                        plainVariant(sideAlt).with(Y_ROT_90)
                                )
                                .with(condition().term(BlockStateProperties.NORTH, false), plainVariant(noSide))
                                .with(condition().term(BlockStateProperties.EAST, false), plainVariant(noSideAlt))
                                .with(
                                        condition().term(BlockStateProperties.SOUTH, false),
                                        plainVariant(noSideAlt).with(Y_ROT_90)
                                )
                                .with(
                                        condition().term(BlockStateProperties.WEST, false),
                                        plainVariant(noSide).with(Y_ROT_270)
                                )
                );
    }

    public void createPumpkins(BlockModelGenerators blockModels) {
        TextureMapping texturemapping = TextureMapping.column(Blocks.PUMPKIN);
        blockModels.createPumpkinVariant(ModBlocks.SOUL_O_LANTERN.get(), texturemapping);
        blockModels.createPumpkinVariant(ModBlocks.REDSTONE_O_LANTERN.get(), texturemapping);
        blockModels.createPumpkinVariant(ModBlocks.COPPER_O_LANTERN.get(), texturemapping);
    }

    public void createFire(Block fireBlock, BlockModelGenerators blockModels) {
        MultiVariant floorFireModels = variants(
                plainModel(
                        ModelTemplates.FIRE_FLOOR.extend().renderType("cutout").build()
                                .create(ModelLocationUtils.getModelLocation(fireBlock, "_floor0"), TextureMapping.fire0(fireBlock), blockModels.modelOutput)
                ),
                plainModel(
                        ModelTemplates.FIRE_FLOOR.extend().renderType("cutout").build()
                                .create(ModelLocationUtils.getModelLocation(fireBlock, "_floor1"), TextureMapping.fire1(fireBlock), blockModels.modelOutput)
                )
        );
        MultiVariant sideFireModels = variants(
                plainModel(
                        ModelTemplates.FIRE_SIDE.extend().renderType("cutout").build()
                                .create(ModelLocationUtils.getModelLocation(fireBlock, "_side0"), TextureMapping.fire0(fireBlock), blockModels.modelOutput)
                ),
                plainModel(
                        ModelTemplates.FIRE_SIDE.extend().renderType("cutout").build()
                                .create(ModelLocationUtils.getModelLocation(fireBlock, "_side1"), TextureMapping.fire1(fireBlock), blockModels.modelOutput)
                ),
                plainModel(
                        ModelTemplates.FIRE_SIDE_ALT.extend().renderType("cutout").build()
                                .create(ModelLocationUtils.getModelLocation(fireBlock, "_side_alt0"), TextureMapping.fire0(fireBlock), blockModels.modelOutput)
                ),
                plainModel(
                        ModelTemplates.FIRE_SIDE_ALT.extend().renderType("cutout").build()
                                .create(ModelLocationUtils.getModelLocation(fireBlock, "_side_alt1"), TextureMapping.fire1(fireBlock), blockModels.modelOutput)
                )
        );
        blockModels.blockStateOutput
                .accept(
                        MultiPartGenerator.multiPart(fireBlock)
                                .with(floorFireModels)
                                .with(sideFireModels)
                                .with(sideFireModels.with(Y_ROT_90))
                                .with(sideFireModels.with(Y_ROT_180))
                                .with(sideFireModels.with(Y_ROT_270))
                );
    }

    public void createRedStoneLantern(Block lanternBlock, BlockModelGenerators blockModels) {
        ModelTemplate lanternTemplate = ModelTemplates.LANTERN.extend().renderType("cutout").build();
        ModelTemplate hangingTemplate = ModelTemplates.HANGING_LANTERN.extend().renderType("cutout").build();
        Identifier lantern = TexturedModel.LANTERN.updateTemplate(template -> template.extend().renderType("cutout").build()).create(lanternBlock, blockModels.modelOutput);
        Identifier hangingLantern = TexturedModel.HANGING_LANTERN.updateTemplate(template -> template.extend().renderType("cutout").build()).create(lanternBlock, blockModels.modelOutput);
        Identifier lanternOff = blockModels.createSuffixedVariant(lanternBlock, "_off", lanternTemplate,
                location -> new TextureMapping().put(TextureSlot.LANTERN, Identifier.parse("extension:block/redstone_lantern_off")));
        Identifier hangingLanternOff = blockModels.createSuffixedVariant(lanternBlock, "_off", hangingTemplate,
                location -> new TextureMapping().put(TextureSlot.LANTERN, Identifier.parse("extension:block/redstone_lantern_off")));
        blockModels.registerSimpleFlatItemModel(lanternBlock.asItem());
        blockModels.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(lanternBlock)
                                .with(PropertyDispatch.initial(BlockStateProperties.HANGING, BlockStateProperties.LIT)
                                        .select(false, false, plainVariant(lanternOff))
                                        .select(false, true, plainVariant(lantern))
                                        .select(true, false, plainVariant(hangingLanternOff))
                                        .select(true, true, plainVariant(hangingLantern))
                                )
                );
    }

    public void createCropBlock(Block cropBlock, Property<Integer> ageProperty, BlockModelGenerators blockModels, int... ageToVisualStageMapping) {
        if (ageProperty.getPossibleValues().size() != ageToVisualStageMapping.length) {
            throw new IllegalArgumentException();
        } else {
            Int2ObjectMap<Identifier> int2objectmap = new Int2ObjectOpenHashMap<>();
            PropertyDispatch<MultiVariant> propertydispatch = PropertyDispatch.initial(ageProperty)
                    .generate(
                            p_388091_ -> {
                                int i = ageToVisualStageMapping[p_388091_];
                                Identifier identifier = int2objectmap.computeIfAbsent(
                                        i, p_387534_ -> blockModels.createSuffixedVariant(cropBlock, "_stage" + i, ModelTemplates.CROP.extend().renderType("cutout").build(), TextureMapping::crop)
                                );
                                return plainVariant(identifier);
                            }
                    );
            blockModels.registerSimpleFlatItemModel(cropBlock.asItem());
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(cropBlock).with(propertydispatch));
        }
    }

    public void createCampfires(BlockModelGenerators blockModels, Block... campfireBlocks) {
        Identifier offModel = Identifier.parse("campfire_off").withPrefix("block/");

        for (Block block : campfireBlocks) {
            Identifier identifier1 = ModelTemplates.CAMPFIRE.extend().renderType("cutout").build().create(block, TextureMapping.campfire(block), blockModels.modelOutput);
            blockModels.registerSimpleFlatItemModel(block.asItem());
            blockModels.blockStateOutput
                    .accept(
                            MultiVariantGenerator.dispatch(block)
                                    .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, plainVariant(identifier1), plainVariant(offModel)))
                                    .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING_ALT)
                    );
        }
    }

    private void createCauldrons(BlockModelGenerators blockModels) {
        blockModels.blockStateOutput
                .accept(
                        createSimpleBlock(
                                ModBlocks.MILK_CAULDRON.get(),
                                plainVariant(
                                        ModelTemplates.CAULDRON_FULL
                                                .create(ModBlocks.MILK_CAULDRON.get(), TextureMapping.cauldron(Identifier.fromNamespaceAndPath("neoforge", "block/milk_still")), blockModels.modelOutput)
                                )
                        )
                );
        Identifier potionLevel1 = CAULDRON_CONTENT_LEVEL1.extend().renderType("translucent").build()
                .create(Extension.asResource("cauldron_potion_level1").withPrefix("block/"),
                        new TextureMapping().put(TextureSlot.CONTENT, TextureMapping.getBlockTexture(Blocks.WATER, "_still")),
                        blockModels.modelOutput);
        Identifier potionLevel2 = CAULDRON_CONTENT_LEVEL2.extend().renderType("translucent").build()
                .create(Extension.asResource("cauldron_potion_level2").withPrefix("block/"),
                        new TextureMapping().put(TextureSlot.CONTENT, TextureMapping.getBlockTexture(Blocks.WATER, "_still")),
                        blockModels.modelOutput);
        Identifier potionLevel3 = CAULDRON_CONTENT_LEVEL3.extend().renderType("translucent").build()
                .create(Extension.asResource("cauldron_potion_level3").withPrefix("block/"),
                        new TextureMapping().put(TextureSlot.CONTENT, TextureMapping.getBlockTexture(Blocks.WATER, "_still")),
                        blockModels.modelOutput);
        Identifier potionFull = CAULDRON_CONTENT_FULL.extend().renderType("translucent").build()
                .create(Extension.asResource("cauldron_potion_full").withPrefix("block/"),
                        new TextureMapping().put(TextureSlot.CONTENT, TextureMapping.getBlockTexture(Blocks.WATER, "_still")),
                        blockModels.modelOutput);
        Identifier cauldron = ModelLocationUtils.getModelLocation(Blocks.CAULDRON);

        ModBlocks.POTION_CAULDRON.values().forEach(block ->
                blockModels.blockStateOutput
                        .accept(
                                MultiVariantGenerator.dispatch(block.get())
                                        .with(
                                                PropertyDispatch.initial(PotionCauldronBlock.LEVEL)
                                                        .select(1, createLayeredCauldron(cauldron, potionLevel1))
                                                        .select(2, createLayeredCauldron(cauldron, potionLevel2))
                                                        .select(3, createLayeredCauldron(cauldron, potionLevel3))
                                                        .select(4, createLayeredCauldron(cauldron, potionFull))
                                        )
                        )
        );
    }

    public static MultiVariant createLayeredCauldron(Identifier contentLevel, Identifier emptyCauldron) {
        CompositeBlockStateModelBuilder compositeBlockStateModelBuilder = new CompositeBlockStateModelBuilder();
        compositeBlockStateModelBuilder.addPartModel(new SingleVariant.Unbaked(plainModel(emptyCauldron)));
        compositeBlockStateModelBuilder.addPartModel(new SingleVariant.Unbaked(plainModel(contentLevel)));
        return MultiVariant.of(compositeBlockStateModelBuilder);
    }

    public class ModBlockFamilyProvider extends BlockModelGenerators.BlockFamilyProvider {
        private final BlockModelGenerators blockModels;
        public ModBlockFamilyProvider(TextureMapping mapping, BlockModelGenerators blockModels) {
            blockModels.super(mapping); // https://coderanch.com/t/588820/java/Extend-class-code-top-level https://codemia.io/knowledge-hub/path/is_not_an_enclosing_class_java
            this.blockModels = blockModels;
        }

        @Override
        public BlockModelGenerators.BlockFamilyProvider fullBlockVariant(Block block) {
            TexturedModel texturedmodel = ModModelProvider.this.texturedModels.getOrDefault(block, TexturedModel.CUBE.get(block));
            Identifier identifier = texturedmodel.create(block, blockModels.modelOutput);
            blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, plainVariant(identifier)));
            return this;
        }
    }
}
