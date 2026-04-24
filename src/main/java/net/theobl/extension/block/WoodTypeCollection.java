package net.theobl.extension.block;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public record WoodTypeCollection<T>(
        T oak, T spruce, T birch, T jungle, T acacia, T dark_oak, T crimson, T warped, T mangrove, T bamboo, T cherry, T pale_oak
) {
    public static final WoodTypeCollection<WoodType> TYPES = new WoodTypeCollection<>(
            WoodType.OAK,
            WoodType.SPRUCE,
            WoodType.BIRCH,
            WoodType.JUNGLE,
            WoodType.ACACIA,
            WoodType.DARK_OAK,
            WoodType.CRIMSON,
            WoodType.WARPED,
            WoodType.MANGROVE,
            WoodType.BAMBOO,
            WoodType.CHERRY,
            WoodType.PALE_OAK
    );
    public static final WoodTypeCollection<String> NAMES = TYPES.map(WoodType::name);
    public static final WoodTypeCollection<Block> BASE = new WoodTypeCollection<>(
            Blocks.OAK_PLANKS,
            Blocks.SPRUCE_PLANKS,
            Blocks.BIRCH_PLANKS,
            Blocks.JUNGLE_PLANKS,
            Blocks.ACACIA_PLANKS,
            Blocks.DARK_OAK_PLANKS,
            Blocks.CRIMSON_PLANKS,
            Blocks.WARPED_PLANKS,
            Blocks.MANGROVE_PLANKS,
            Blocks.BAMBOO_PLANKS,
            Blocks.CHERRY_PLANKS,
            Blocks.PALE_OAK_PLANKS
    );

    public static <B extends Block> WoodTypeCollection<DeferredBlock<B>> registerBlocksWithExistingVanillaBlock(
            String id,
            TriFunction<String, Function<BlockBehaviour.Properties, Block>, BlockBehaviour.Properties, DeferredBlock<B>> register,
            BiFunction<WoodType, BlockBehaviour.Properties, B> woodTypeBlockFactory,
            Function<WoodType, BlockBehaviour.Properties> propertiesSupplier,
            Block existingBlock,
            WoodType existingBlockWoodType
    ) {
        return zipMap(TYPES, prefixWithWood(id), ((woodType, s) -> {
            if(woodType == existingBlockWoodType) {
                return DeferredBlock.createBlock(BuiltInRegistries.BLOCK.getKey(existingBlock));
            }
            else {
                return register.apply(s, p -> woodTypeBlockFactory.apply(woodType, p), propertiesSupplier.apply(woodType));
            }
        }));
    }

    public static WoodTypeCollection<String> prefixWithWood(String id) {
        return NAMES.map(name -> name + "_" + id);
    }

    public void forEach(Consumer<T> consumer) {
        consumer.accept(this.oak);
        consumer.accept(this.spruce);
        consumer.accept(this.birch);
        consumer.accept(this.jungle);
        consumer.accept(this.acacia);
        consumer.accept(this.dark_oak);
        consumer.accept(this.crimson);
        consumer.accept(this.warped);
        consumer.accept(this.mangrove);
        consumer.accept(this.bamboo);
        consumer.accept(this.cherry);
        consumer.accept(this.pale_oak);
    }

    public T pick(WoodType woodType) {
        T t;
        if(woodType == WoodType.OAK) {
            t = oak;
        }
        else if(woodType == WoodType.SPRUCE) {
            t = spruce;
        }
        else if(woodType == WoodType.BIRCH) {
            t = birch;
        }
        else if(woodType == WoodType.JUNGLE) {
            t = jungle;
        }
        else if(woodType == WoodType.ACACIA) {
            t = acacia;
        }
        else if(woodType == WoodType.DARK_OAK) {
            t = dark_oak;
        }
        else if(woodType == WoodType.CRIMSON) {
            t = crimson;
        }
        else if(woodType == WoodType.WARPED) {
            t = warped;
        }
        else if(woodType == WoodType.MANGROVE) {
            t = mangrove;
        }
        else if(woodType == WoodType.BAMBOO) {
            t = bamboo;
        }
        else if(woodType == WoodType.CHERRY) {
            t = cherry;
        }
        else if(woodType == WoodType.PALE_OAK) {
            t = pale_oak;
        }
        else {
            t = oak;
        }
        return t;
    }

    public <U> WoodTypeCollection<U> map(Function<T, U> mapper) {
        return new WoodTypeCollection<>(
                mapper.apply(this.oak),
                mapper.apply(this.spruce),
                mapper.apply(this.birch),
                mapper.apply(this.jungle),
                mapper.apply(this.acacia),
                mapper.apply(this.dark_oak),
                mapper.apply(this.crimson),
                mapper.apply(this.warped),
                mapper.apply(this.mangrove),
                mapper.apply(this.bamboo),
                mapper.apply(this.cherry),
                mapper.apply(this.pale_oak)
        );
    }

    public static <T, U, R> WoodTypeCollection<R> zipMap(WoodTypeCollection<T> first, WoodTypeCollection<U> second, BiFunction<T, U, R> operation) {
        return  new WoodTypeCollection<>(
                operation.apply(first.oak, second.oak),
                operation.apply(first.spruce, second.spruce),
                operation.apply(first.birch, second.birch),
                operation.apply(first.jungle, second.jungle),
                operation.apply(first.acacia, second.acacia),
                operation.apply(first.dark_oak, second.dark_oak),
                operation.apply(first.crimson, second.crimson),
                operation.apply(first.warped, second.warped),
                operation.apply(first.mangrove, second.mangrove),
                operation.apply(first.bamboo, second.bamboo),
                operation.apply(first.cherry, second.cherry),
                operation.apply(first.pale_oak, second.pale_oak)
        );
    }
}
