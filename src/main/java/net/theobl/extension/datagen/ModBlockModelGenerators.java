package net.theobl.extension.datagen;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.theobl.extension.block.ModBlocks;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModBlockModelGenerators extends BlockModelGenerators {
    final Consumer<BlockStateGenerator> blockStateOutput;
    final BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput;
    final Map<Block, TexturedModel> texturedModels = ImmutableMap.<Block, TexturedModel>builder()
            .put(ModBlocks.SOUL_SANDSTONE.get(), TexturedModel.TOP_BOTTOM_WITH_WALL.get(ModBlocks.SOUL_SANDSTONE.get()))
            .put(ModBlocks.SMOOTH_SOUL_SANDSTONE.get(), TexturedModel.createAllSame(TextureMapping.getBlockTexture(ModBlocks.SOUL_SANDSTONE.get(), "_top")))
            .put(ModBlocks.CUT_SOUL_SANDSTONE.get(), TexturedModel.COLUMN.get(ModBlocks.SOUL_SANDSTONE.get())
                    .updateTextures(textureMapping -> textureMapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.CUT_SANDSTONE)))
            )
            .build();

    public ModBlockModelGenerators(Consumer<BlockStateGenerator> blockStateOutput, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput, Consumer<Item> skippedAutoModelsOutput) {
        super(blockStateOutput, modelOutput, skippedAutoModelsOutput);
        this.modelOutput = modelOutput;
        this.blockStateOutput = blockStateOutput;
    }

    private void createWall(Block wallBlock) {
        this.blockStateOutput.accept(
                createWall(wallBlock,
                        ModelTemplates.WALL_POST.create(wallBlock, texturedModels.getOrDefault(wallBlock, TexturedModel.CUBE.get(wallBlock)).getMapping(), modelOutput),
                        ModelTemplates.WALL_TALL_SIDE.create(wallBlock, texturedModels.getOrDefault(wallBlock, TexturedModel.CUBE.get(wallBlock)).getMapping(), modelOutput),
                        ModelTemplates.WALL_LOW_SIDE.create(wallBlock, texturedModels.getOrDefault(wallBlock, TexturedModel.CUBE.get(wallBlock)).getMapping(), modelOutput)));
//        delegateItemModel(ModBlocks.STONE_WALL.get(),
//                ModelTemplates.WALL_INVENTORY.create(wallBlock, texturedModels.getOrDefault(wallBlock, TexturedModel.CUBE.get(wallBlock)).getMapping(), modelOutput));

    }

    @Override
    public void run() {
        ModBlockFamilies.getAllFamilies()
                .filter(BlockFamily::shouldGenerateModel)
                .forEach(blockFamily -> this.family(blockFamily.getBaseBlock()).generateFor(blockFamily));

        createWall(ModBlocks.STONE_WALL.get());
        createWall(ModBlocks.POLISHED_GRANITE_WALL.get());
        createWall(ModBlocks.POLISHED_DIORITE_WALL.get());
        createWall(ModBlocks.POLISHED_ANDESITE_WALL.get());
        createWall(ModBlocks.PRISMARINE_BRICK_WALL.get());
        createWall(ModBlocks.DARK_PRISMARINE_WALL.get());
        createWall(ModBlocks.PURPUR_WALL.get());
        createWall(ModBlocks.QUARTZ_WALL.get());

        createTrivialCube(ModBlocks.CRACKED_RED_NETHER_BRICKS.get());
        createTrivialCube(ModBlocks.CHISELED_RED_NETHER_BRICKS.get());
        blockStateOutput.accept(
                createFence(ModBlocks.RED_NETHER_BRICK_FENCE.get(),
                        ModelTemplates.FENCE_POST.create(ModBlocks.RED_NETHER_BRICK_FENCE.get(), texturedModels.getOrDefault(ModBlocks.RED_NETHER_BRICK_FENCE.get(), TexturedModel.CUBE.get(ModBlocks.RED_NETHER_BRICK_FENCE.get())).getMapping(), modelOutput),
                        ModelTemplates.FENCE_SIDE.create(ModBlocks.RED_NETHER_BRICK_FENCE.get(), texturedModels.getOrDefault(ModBlocks.RED_NETHER_BRICK_FENCE.get(), TexturedModel.CUBE.get(ModBlocks.RED_NETHER_BRICK_FENCE.get())).getMapping(), modelOutput)));
        TextureMapping textureMapping = TextureMapping.pane(Blocks.TINTED_GLASS, ModBlocks.TINTED_GLASS_PANE.get());
        ResourceLocation resourcelocation = ModelTemplates.STAINED_GLASS_PANE_POST.create(ModBlocks.TINTED_GLASS_PANE.get(), textureMapping, this.modelOutput);
        ResourceLocation resourcelocation1 = ModelTemplates.STAINED_GLASS_PANE_SIDE.create(ModBlocks.TINTED_GLASS_PANE.get(), textureMapping, this.modelOutput);
        ResourceLocation resourcelocation2 = ModelTemplates.STAINED_GLASS_PANE_SIDE_ALT.create(ModBlocks.TINTED_GLASS_PANE.get(), textureMapping, this.modelOutput);
        ResourceLocation resourcelocation3 = ModelTemplates.STAINED_GLASS_PANE_NOSIDE.create(ModBlocks.TINTED_GLASS_PANE.get(), textureMapping, this.modelOutput);
        ResourceLocation resourcelocation4 = ModelTemplates.STAINED_GLASS_PANE_NOSIDE_ALT.create(ModBlocks.TINTED_GLASS_PANE.get(), textureMapping, this.modelOutput);
        blockStateOutput.accept(
                MultiPartGenerator.multiPart(ModBlocks.TINTED_GLASS_PANE.get())
                        .with(Variant.variant().with(VariantProperties.MODEL, resourcelocation))
                        .with(Condition.condition().term(BlockStateProperties.NORTH, true), Variant.variant().with(VariantProperties.MODEL, resourcelocation1))
                        .with(
                                Condition.condition().term(BlockStateProperties.EAST, true),
                                Variant.variant().with(VariantProperties.MODEL, resourcelocation1).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                        )
                        .with(Condition.condition().term(BlockStateProperties.SOUTH, true), Variant.variant().with(VariantProperties.MODEL, resourcelocation2))
                        .with(
                                Condition.condition().term(BlockStateProperties.WEST, true),
                                Variant.variant().with(VariantProperties.MODEL, resourcelocation2).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                        )
                        .with(Condition.condition().term(BlockStateProperties.NORTH, false), Variant.variant().with(VariantProperties.MODEL, resourcelocation3))
                        .with(Condition.condition().term(BlockStateProperties.EAST, false), Variant.variant().with(VariantProperties.MODEL, resourcelocation4))
                        .with(
                                Condition.condition().term(BlockStateProperties.SOUTH, false),
                                Variant.variant().with(VariantProperties.MODEL, resourcelocation4).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                        )
                        .with(
                                Condition.condition().term(BlockStateProperties.WEST, false),
                                Variant.variant().with(VariantProperties.MODEL, resourcelocation3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                        )
        );

    }

    public BlockModelGenerators.BlockFamilyProvider family(Block block) {
        TexturedModel texturedmodel = this.texturedModels.getOrDefault(block, TexturedModel.CUBE.get(block));
        return new ModBlockModelGenerators.ModBlockFamilyProvider(texturedmodel.getMapping()).fullBlock(block, texturedmodel.getTemplate());
    }

    public class ModBlockFamilyProvider extends BlockFamilyProvider {
        public ModBlockFamilyProvider(TextureMapping mapping) {
            super(mapping);
        }

        @Override
        public BlockModelGenerators.BlockFamilyProvider fullBlockVariant(Block block) {
            TexturedModel texturedmodel = ModBlockModelGenerators.this.texturedModels.getOrDefault(block, TexturedModel.CUBE.get(block));
            ResourceLocation resourcelocation = texturedmodel.create(block, ModBlockModelGenerators.this.modelOutput);
            ModBlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, resourcelocation));
            return this;
        }
    }
}
