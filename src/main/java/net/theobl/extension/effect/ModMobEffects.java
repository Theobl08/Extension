package net.theobl.extension.effect;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;

public final class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Extension.MODID);

    public static DeferredHolder<MobEffect, MobEffect> BIG = MOB_EFFECTS.register(
            "big",
            () -> new MobEffect(MobEffectCategory.NEUTRAL, 9687858)
                    .addAttributeModifier(Attributes.SCALE, Extension.asResource("effect.big"), 1.0, AttributeModifier.Operation.ADD_VALUE)
    );
    public static DeferredHolder<MobEffect, MobEffect> SMALL = MOB_EFFECTS.register(
            "small",
            () -> new MobEffect(MobEffectCategory.NEUTRAL, 13841099)
                    .addAttributeModifier(Attributes.SCALE, Extension.asResource("effect.small"), -0.5, AttributeModifier.Operation.ADD_VALUE)
    );

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
