package net.theobl.extension.mixin;

import net.caffeinemc.mods.sodium.api.config.StorageEventHandler;
import net.caffeinemc.mods.sodium.api.config.option.*;
import net.caffeinemc.mods.sodium.client.config.structure.IntegerOption;
import net.caffeinemc.mods.sodium.client.config.value.ConstantValue;
import net.caffeinemc.mods.sodium.client.config.value.DependentValue;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.Identifier;
import net.theobl.extension.Extension;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

@Mixin(IntegerOption.class)
public class SliderControlMixin {
    @Shadow
    @Final
    @Mutable
    private DependentValue<? extends SteppedValidator> validator;

    @Inject(method = "<init>", at = @At("RETURN"), remap = false)
    public void init(Identifier id, Collection dependencies, Component name, DependentValue enabled, StorageEventHandler storage, Function tooltipProvider, OptionImpact impact, Set flags, DependentValue defaultValue, Boolean controlHiddenWhenDisabled, OptionBinding binding, Consumer applyHook, DependentValue validator, ControlValueFormatter valueFormatter, CallbackInfo ci) {
        if (((IntegerOption) (Object) this).getName().getContents() instanceof TranslatableContents component && component.getKey().equals("options.gamma")) {
            int min = (int) (Extension.BRIGHTNESS_MIN * 100);
            int max = (int) (Extension.BRIGHTNESS_MAX * 100);
            int interval = (int) (Extension.BRIGHTNESS_STEP * 100);
            this.validator = new ConstantValue<>(new Range(min, max, interval));
        }
    }
}
