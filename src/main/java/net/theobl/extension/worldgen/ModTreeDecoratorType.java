package net.theobl.extension.worldgen;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;

public class ModTreeDecoratorType {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = DeferredRegister.create(BuiltInRegistries.TREE_DECORATOR_TYPE, Extension.MODID);

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<AttachedListToLeavesDecorator>> ATTACHED_LIST_TO_LEAVES = registerTreeDecorator("attached_list_to_leaves", AttachedListToLeavesDecorator.CODEC);

    private static <P extends TreeDecorator> DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<P>> registerTreeDecorator(String name, MapCodec<P> codec) {
        return TREE_DECORATOR_TYPES.register(name, () -> new TreeDecoratorType<>(codec));
    }

    public static void register(IEventBus eventBus) {
        TREE_DECORATOR_TYPES.register(eventBus);
    }
}
