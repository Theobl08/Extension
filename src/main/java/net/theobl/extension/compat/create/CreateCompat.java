package net.theobl.extension.compat.create;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

public class CreateCompat {
    public static void init(IEventBus modEventBus) {
        modEventBus.addListener((FMLCommonSetupEvent event) -> event.enqueueWork(ModBlockSpoutingBehaviours::registerDefaults));
    }
}
