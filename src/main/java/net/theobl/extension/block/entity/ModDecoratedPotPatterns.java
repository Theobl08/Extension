package net.theobl.extension.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;

public class ModDecoratedPotPatterns {
    public static final ResourceKey<DecoratedPotPattern> EMPTY = ResourceKey.create(Registries.DECORATED_POT_PATTERN, Extension.asResource("empty"));

    public static void bootstrap(BootstrapContext<DecoratedPotPattern> registry) {
        registry.register(EMPTY, new DecoratedPotPattern(Extension.asResource("empty_pottery_pattern")));
    }
}
