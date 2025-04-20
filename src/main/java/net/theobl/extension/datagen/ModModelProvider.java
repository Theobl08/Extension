package net.theobl.extension.datagen;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ModelProvider;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.data.models.model.DelegatedModel;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.theobl.extension.block.ModBlocks;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModModelProvider extends ModelProvider {
    private final PackOutput.PathProvider blockStatePathProvider;
    private final PackOutput.PathProvider modelPathProvider;
    public ModModelProvider(PackOutput output) {
        super(output);
        this.blockStatePathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
        this.modelPathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        Map<Block, BlockStateGenerator> map = Maps.newHashMap();
        Consumer<BlockStateGenerator> consumer = blockStateGenerator -> {
            Block block = blockStateGenerator.getBlock();
            BlockStateGenerator blockstategenerator = map.put(block, blockStateGenerator);
            if (blockstategenerator != null) {
                throw new IllegalStateException("Duplicate blockstate definition for " + block);
            }
        };
        Map<ResourceLocation, Supplier<JsonElement>> map1 = Maps.newHashMap();
        Set<Item> set = Sets.newHashSet();
        BiConsumer<ResourceLocation, Supplier<JsonElement>> biconsumer = (resourceLocation, supplier1) -> {
            Supplier<JsonElement> supplier = map1.put(resourceLocation, supplier1);
            if (supplier != null) {
                throw new IllegalStateException("Duplicate model definition for " + resourceLocation);
            }
        };
        Consumer<Item> consumer1 = set::add;
        new ModBlockModelGenerators(consumer, biconsumer, consumer1).run();
        List<Block> list = ModBlocks.BLOCKS
                .getEntries()
                .stream()
                .filter(blockEntry -> true)
                .map(Holder::value)
                .filter(block -> !map.containsKey(block))
                .toList();
        if (!list.isEmpty()) {
            throw new IllegalStateException("Missing blockstate definitions for: " + list);
        } else {
            BuiltInRegistries.BLOCK.forEach(block -> {
                Item item = Item.BY_BLOCK.get(block);
                if (item != null) {
                    if (set.contains(item)) {
                        return;
                    }

                    ResourceLocation resourcelocation = ModelLocationUtils.getModelLocation(item);
                    if (!map1.containsKey(resourcelocation)) {
                        map1.put(resourcelocation, new DelegatedModel(ModelLocationUtils.getModelLocation(block)));
                    }
                }
            });
            return CompletableFuture.allOf(
                    this.saveCollection(output, map, block -> this.blockStatePathProvider.json(block.builtInRegistryHolder().key().location())),
                    this.saveCollection(output, map1, this.modelPathProvider::json)
            );
        }
    }
    private <T> CompletableFuture<?> saveCollection(CachedOutput output, Map<T, ? extends Supplier<JsonElement>> objectToJsonMap, Function<T, Path> resolveObjectPath) {
        return CompletableFuture.allOf(objectToJsonMap.entrySet().stream().map(entry -> {
            Path path = resolveObjectPath.apply(entry.getKey());
            JsonElement jsonelement = entry.getValue().get();
            return DataProvider.saveStable(output, jsonelement, path);
        }).toArray(CompletableFuture[]::new));
    }
}
