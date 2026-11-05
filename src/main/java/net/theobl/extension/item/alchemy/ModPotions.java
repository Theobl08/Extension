package net.theobl.extension.item.alchemy;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;
import net.theobl.extension.effect.ModMobEffects;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, Extension.MODID);

    public static final DeferredHolder<Potion, Potion> DECAY = POTIONS.register(
            "decay", () -> new Potion("decay", new MobEffectInstance(MobEffects.WITHER, 800, 1)));
    public static final DeferredHolder<Potion, Potion> BIG = POTIONS.register(
            "big", () -> new Potion("big", new MobEffectInstance(ModMobEffects.BIG, 3600))
    );
    public static final DeferredHolder<Potion, Potion> LONG_BIG = POTIONS.register(
            "long_big", () -> new Potion("big", new MobEffectInstance(ModMobEffects.BIG, 9600))
    );
    public static final DeferredHolder<Potion, Potion> STRONG_BIG = POTIONS.register(
            "strong_big", () -> new Potion("big", new MobEffectInstance(ModMobEffects.BIG, 1800, 1))
    );
    public static final DeferredHolder<Potion, Potion> SMALL = POTIONS.register(
            "small", () -> new Potion("small", new MobEffectInstance(ModMobEffects.SMALL, 3600))
    );
    public static final DeferredHolder<Potion, Potion> LONG_SMALL = POTIONS.register(
            "long_small", () -> new Potion("small", new MobEffectInstance(ModMobEffects.SMALL, 9600))
    );
    public static final DeferredHolder<Potion, Potion> STRONG_SMALL = POTIONS.register(
            "strong_small", () -> new Potion("small", new MobEffectInstance(ModMobEffects.SMALL, 1800, 1))
    );

    public static void register(IEventBus bus) {
        POTIONS.register(bus);
    }
}
