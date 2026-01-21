package net.theobl.extension.stats;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;

public class ModStats {
    public static final DeferredRegister<Identifier> STATS = DeferredRegister.create(BuiltInRegistries.CUSTOM_STAT, Extension.MODID);

    public static final Identifier INTERACT_WITH_FLETCHING_TABLE = makeCustomStat("interact_with_fletching_table");

    private static Identifier makeCustomStat(String id) {
        Identifier location = Extension.asResource(id);
        STATS.register(id, () -> location);
        return location;
    }

    public static void register(IEventBus eventBus) {
        STATS.register(eventBus);
    }
}
