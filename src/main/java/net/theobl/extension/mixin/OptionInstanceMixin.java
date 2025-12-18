package net.theobl.extension.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.theobl.extension.Extension;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;
import java.util.function.Function;

@Mixin(OptionInstance.class)
public abstract class OptionInstanceMixin {
    @Shadow
    @Final
    public Component caption;

    @Shadow
    @Final
    @Mutable
    public Function<Double, Component> toString;

    @Shadow
    @Final
    @Mutable
    private OptionInstance.ValueSet<Double> values;

    @Shadow
    @Final
    @Mutable
    private Codec<Double> codec;

    @Shadow
    @Final
    @Mutable
    private Consumer<Double> onValueUpdate;

    @Inject(at = @At("RETURN"), method = "<init>*", remap = false)
    protected void init(CallbackInfo info) {
        if (this.caption.getContents() instanceof TranslatableContents translatableContents && translatableContents.getKey().equals("options.gamma")) {
            this.onValueUpdate = this::onValueUpdate;
            this.toString = this::toString;
            this.values = Extension.BetterSlider.INSTANCE;
            this.codec = this.values.codec();
        }
    }

    private Component toString(Double gamma) {
        return Component.translatable("options.gamma").append(": ").append(Component.literal(Math.round(gamma * 100) + "%"));
    }

    private void onValueUpdate(Double brightness) {
        brightness = Math.round(brightness / Extension.BRIGHTNESS_STEP) * Extension.BRIGHTNESS_STEP;
        Minecraft.getInstance().options.gamma().set(brightness);
    }
}
