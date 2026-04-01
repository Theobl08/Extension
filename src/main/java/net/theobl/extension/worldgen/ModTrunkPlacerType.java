package net.theobl.extension.worldgen;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;

public class ModTrunkPlacerType {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = DeferredRegister.create(BuiltInRegistries.TRUNK_PLACER_TYPE, Extension.MODID);

    public static DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<PotatoTrunkPlacer>> POTATO_TRUNK_PLACER = register("potato_trunk_placer", PotatoTrunkPlacer.CODEC);


    private static <P extends TrunkPlacer> DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<P>> register(String name, MapCodec<P> codec) {
        return TRUNK_PLACER_TYPES.register(name, () -> new TrunkPlacerType<>(codec));
    }

    public static void register(IEventBus eventBus) {
        TRUNK_PLACER_TYPES.register(eventBus);
    }
}
