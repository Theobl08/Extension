package net.theobl.extension.datagen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.client.renderer.block.dispatch.multipart.CombinedCondition;
import net.minecraft.client.renderer.block.dispatch.multipart.Condition;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.theobl.extension.block.FourLayeredCauldronBlock;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModBlockModelGenerators extends BlockModelGenerators {
    static final TextureSlot BASE = TextureSlot.create("base", TextureSlot.END);
    static final TextureSlot SIDES = TextureSlot.create("sides",TextureSlot.ALL);
    static final ModelTemplate CHISELED_BOOKSHELF = ModelTemplates.create("chiseled_bookshelf", TextureSlot.TOP, TextureSlot.SIDE);
    static final ModelTemplate CHISELED_BOOKSHELF_INVENTORY = ModelTemplates.create("chiseled_bookshelf_inventory", "_inventory", TextureSlot.TOP, TextureSlot.SIDE, TextureSlot.FRONT);
    static final ModelTemplate LECTERN =  ModelTemplates.create("lectern", BASE, TextureSlot.TOP, TextureSlot.BOTTOM, SIDES, TextureSlot.FRONT, TextureSlot.PARTICLE);
    static final ModelTemplate CRAFTER =  ModelTemplates.create("crafter");
    public static final ModelTemplate CAULDRON_LEVEL0 = ModelTemplates.create(
            "extension:template_cauldron_level0", TextureSlot.CONTENT, TextureSlot.INSIDE, TextureSlot.PARTICLE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE
    );

    public ModBlockModelGenerators(Consumer<BlockModelDefinitionGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        super(blockStateOutput, itemModelOutput, modelOutput);
    }

    ModBlockModelGenerators(BlockModelGenerators blockModels) {
        this(blockModels.blockStateOutput, blockModels.itemModelOutput, blockModels.modelOutput);
    }

    public void createCartographyTable(Block block, Block planks) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block, "_side3"))
                .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(planks))
                .put(TextureSlot.UP, TextureMapping.getBlockTexture(block, "_top"))
                .put(TextureSlot.NORTH, TextureMapping.getBlockTexture(block, "_side3"))
                .put(TextureSlot.EAST, TextureMapping.getBlockTexture(block, "_side3"))
                .put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(block, "_side1"))
                .put(TextureSlot.WEST, TextureMapping.getBlockTexture(block, "_side2"));
        this.blockStateOutput
                .accept(createSimpleBlock(block, plainVariant(ModelTemplates.CUBE.create(block, mapping, this.modelOutput))));
    }

    void createChiseledBookshelf(DeferredBlock<Block> deferredBlock) {
        Block block = deferredBlock.get();
        MultiVariant body = plainVariant(CHISELED_BOOKSHELF.create(block, TextureMapping.cubeTop(block), this.modelOutput));
        MultiPartGenerator multiPartGenerator = MultiPartGenerator.multiPart(block);
        forEachHorizontalDirection((direction, rotation) -> {
            Condition facingCondition = condition().term(BlockStateProperties.HORIZONTAL_FACING, direction).build();
            multiPartGenerator.with(facingCondition, body.with(rotation).with(UV_LOCK));
            this.addSlotStateAndRotationVariants(block, multiPartGenerator, facingCondition, rotation);
        });
        this.blockStateOutput.accept(multiPartGenerator);
        Identifier inventory = CHISELED_BOOKSHELF_INVENTORY.create(block, TextureMapping.cubeTop(block).put(TextureSlot.FRONT, TextureMapping.getBlockTexture(block, "_empty")), this.modelOutput);
        this.registerSimpleItemModel(block, inventory);
        CHISELED_BOOKSHELF_SLOT_MODEL_CACHE.clear();
    }

    public void addSlotStateAndRotationVariants(Block block, MultiPartGenerator multiPartGenerator, Condition facingCondition, VariantMutator mutator) {
        List.of(
                        Pair.of(ChiseledBookShelfBlock.SLOT_0_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_TOP_LEFT),
                        Pair.of(ChiseledBookShelfBlock.SLOT_1_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_TOP_MID),
                        Pair.of(ChiseledBookShelfBlock.SLOT_2_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_TOP_RIGHT),
                        Pair.of(ChiseledBookShelfBlock.SLOT_3_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_BOTTOM_LEFT),
                        Pair.of(ChiseledBookShelfBlock.SLOT_4_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_BOTTOM_MID),
                        Pair.of(ChiseledBookShelfBlock.SLOT_5_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_BOTTOM_RIGHT)
                )
                .forEach(pair -> {
                    BooleanProperty stateProperty = pair.getFirst();
                    ModelTemplate modelTemplate = pair.getSecond();
                    this.addBookSlotModel(block, multiPartGenerator, facingCondition, mutator, stateProperty, modelTemplate, true);
                    this.addBookSlotModel(block, multiPartGenerator, facingCondition, mutator, stateProperty, modelTemplate, false);
                });
    }

    public void addBookSlotModel(
            Block block,
            MultiPartGenerator multiPartGenerator,
            Condition facingCondition,
            VariantMutator mutator,
            BooleanProperty stateProperty,
            ModelTemplate template,
            boolean isSlotOccupied
    ) {
        String suffix = isSlotOccupied ? "_occupied" : "_empty";
        TextureMapping mapping = new TextureMapping().put(TextureSlot.TEXTURE, TextureMapping.getBlockTexture(block, suffix));
        BlockModelGenerators.BookSlotModelCacheKey cacheKey = new BlockModelGenerators.BookSlotModelCacheKey(template, suffix);
        MultiVariant model = plainVariant(
                CHISELED_BOOKSHELF_SLOT_MODEL_CACHE.computeIfAbsent(
                        cacheKey, key -> template.createWithSuffix(block, suffix, mapping, this.modelOutput)
                )
        );
        multiPartGenerator.with(
                new CombinedCondition(CombinedCondition.Operation.AND, List.of(facingCondition, condition().term(stateProperty, isSlotOccupied).build())),
                model.with(mutator)
        );
    }

    public void createLecterns(Block block, Block planks) {
        TextureMapping textureMapping = new TextureMapping()
                .put(BASE, TextureMapping.getBlockTexture(block, "_base"))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(planks))
                .put(SIDES, TextureMapping.getBlockTexture(block, "_sides"))
                .put(TextureSlot.FRONT, TextureMapping.getBlockTexture(block, "_front"))
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block, "_sides"));
        MultiVariant model = plainVariant(LECTERN.create(block, textureMapping, this.modelOutput));
        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, model).with(ROTATION_HORIZONTAL_FACING));
    }

    public void createBarrel(DeferredBlock<Block> deferredBlock) {
        Block block = deferredBlock.get();
        Material openTop = TextureMapping.getBlockTexture(block, "_top_open");
        MultiVariant closedModel = plainVariant(TexturedModel.CUBE_BOTTOM_TOP.create(block, this.modelOutput));
        MultiVariant openModel = plainVariant(
                TexturedModel.CUBE_BOTTOM_TOP
                        .get(block)
                        .updateTextures(t -> t.put(TextureSlot.TOP, openTop))
                        .createWithSuffix(block, "_open", this.modelOutput)
        );
        this.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(block)
                                .with(PropertyDispatch.initial(BlockStateProperties.OPEN).select(false, closedModel).select(true, openModel))
                                .with(ROTATIONS_COLUMN_WITH_FACING)
                );
    }

    public void createCrafterBlock(DeferredBlock<Block> deferredBlock) {
        Block block = deferredBlock.get();
        TextureMapping offMappings = new TextureMapping()
                .put(TextureSlot.NORTH, TextureMapping.getBlockTexture(block, "_north"))
                .put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(block, "_south"))
                .put(TextureSlot.EAST, TextureMapping.getBlockTexture(block, "_east"))
                .put(TextureSlot.WEST, TextureMapping.getBlockTexture(block, "_west"));

        TextureMapping triggeredMappings = new TextureMapping()
                .put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(block, "_south_triggered"))
                .put(TextureSlot.EAST, TextureMapping.getBlockTexture(block, "_east_triggered"))
                .put(TextureSlot.WEST, TextureMapping.getBlockTexture(block, "_west_triggered"));

        TextureMapping craftingMappings = new TextureMapping()
                .put(TextureSlot.NORTH, TextureMapping.getBlockTexture(block, "_north_crafting"))
                .put(TextureSlot.EAST, TextureMapping.getBlockTexture(block, "_east_crafting"))
                .put(TextureSlot.WEST, TextureMapping.getBlockTexture(block, "_west_crafting"));

        MultiVariant off = plainVariant(
                CRAFTER.extend()
                .requiredTextureSlot(TextureSlot.NORTH)
                .requiredTextureSlot(TextureSlot.SOUTH)
                .requiredTextureSlot(TextureSlot.EAST)
                .requiredTextureSlot(TextureSlot.WEST)
                .build().create(block, offMappings, this.modelOutput)
        );
        MultiVariant triggeredLocation = plainVariant(
                CRAFTER.extend()
                .suffix("_triggered")
                .parent(off.variants().unwrap().getFirst().value().modelLocation())
                .requiredTextureSlot(TextureSlot.SOUTH)
                .requiredTextureSlot(TextureSlot.EAST)
                .requiredTextureSlot(TextureSlot.WEST)
                .build().create(block, triggeredMappings, this.modelOutput)
        );
        MultiVariant craftingLocation = plainVariant(
                CRAFTER.extend()
                .suffix("_crafting")
                .parent(triggeredLocation.variants().unwrap().getFirst().value().modelLocation())
                .requiredTextureSlot(TextureSlot.NORTH)
                .requiredTextureSlot(TextureSlot.EAST)
                .requiredTextureSlot(TextureSlot.WEST)
                .build().create(block, craftingMappings, this.modelOutput)
        );
        MultiVariant craftingTriggeredLocation = plainVariant(
                CRAFTER.extend()
                .suffix("_crafting_triggered")
                .parent(craftingLocation.variants().unwrap().getFirst().value().modelLocation())
                .build().create(block, new TextureMapping(), this.modelOutput)
        );
        this.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(block)
                                .with(
                                        PropertyDispatch.initial(BlockStateProperties.TRIGGERED, CrafterBlock.CRAFTING)
                                                .select(false, false, off)
                                                .select(true, true, craftingTriggeredLocation)
                                                .select(true, false, triggeredLocation)
                                                .select(false, true, craftingLocation)
                                )
                                .with(PropertyDispatch.modify(BlockStateProperties.ORIENTATION).generate(BlockModelGenerators::applyRotation))
                );
    }

    public void createLiquidBlock(DeferredBlock<LiquidBlock> block, Identifier stillTexture) {
        // This does the EXACT same thing as createAirLikeBlock(Block, Material)
        ModelTemplates.PARTICLE_ONLY.create(block.get(), TextureMapping.particle(new Material(stillTexture)), this.modelOutput);
        createNonTemplateModelBlock(block.get());
    }

    public void createFourLayeredCauldrons(DeferredBlock<Block> deferredBlock, Identifier stillTexture) {
        Block block = deferredBlock.get();
        Material stillLocation = new Material(stillTexture);
        this.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(block)
                                .with(
                                        PropertyDispatch.initial(FourLayeredCauldronBlock.LEVEL)
                                                .select(
                                                        1,
                                                        plainVariant(
                                                                CAULDRON_LEVEL0
                                                                        .createWithSuffix(
                                                                                block,
                                                                                "_level0",
                                                                                TextureMapping.cauldron(stillLocation),
                                                                                this.modelOutput
                                                                        )
                                                        )
                                                )
                                                .select(
                                                        2,
                                                        plainVariant(
                                                                ModelTemplates.CAULDRON_LEVEL1
                                                                        .createWithSuffix(
                                                                                block,
                                                                                "_level1",
                                                                                TextureMapping.cauldron(stillLocation),
                                                                                this.modelOutput
                                                                        )
                                                        )
                                                )
                                                .select(
                                                        3,
                                                        plainVariant(
                                                                ModelTemplates.CAULDRON_LEVEL2
                                                                        .createWithSuffix(
                                                                                block,
                                                                                "_level2",
                                                                                TextureMapping.cauldron(stillLocation),
                                                                                this.modelOutput
                                                                        )
                                                        )
                                                )
                                                .select(
                                                        4,
                                                        plainVariant(
                                                                ModelTemplates.CAULDRON_FULL
                                                                        .createWithSuffix(
                                                                                block,
                                                                                "_full",
                                                                                TextureMapping.cauldron(stillLocation),
                                                                                this.modelOutput
                                                                        )
                                                        )
                                                )
                                )
                );
    }
}
