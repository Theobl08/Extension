package net.theobl.extension.item.alchemy;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, Extension.MODID);

    public static final DeferredHolder<Potion, Potion> DECAY = POTIONS.register(
            "decay", () -> new Potion("decay", new MobEffectInstance(MobEffects.WITHER, 800, 1)));

    public static void register(IEventBus bus) {
        POTIONS.register(bus);
    }
}
