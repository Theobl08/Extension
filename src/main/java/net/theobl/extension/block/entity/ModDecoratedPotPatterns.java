package net.theobl.extension.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;

public class ModDecoratedPotPatterns {
    public static final DeferredRegister<DecoratedPotPattern> DECORATED_POT_PATTERNS = DeferredRegister.create(BuiltInRegistries.DECORATED_POT_PATTERN, Extension.MODID);

    public static final DeferredHolder<DecoratedPotPattern, DecoratedPotPattern> EMPTY = DECORATED_POT_PATTERNS.register(
            "empty",
            () -> new DecoratedPotPattern(Extension.asResource("empty_pottery_pattern"))
    );

    public static void register(IEventBus eventBus) {
        DECORATED_POT_PATTERNS.register(eventBus);
    }
}
