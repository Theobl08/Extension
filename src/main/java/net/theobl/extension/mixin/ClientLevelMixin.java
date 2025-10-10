package net.theobl.extension.mixin;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.LevelHeightAccessor;
import net.theobl.extension.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientLevel.ClientLevelData.class)
public class ClientLevelMixin {
    @Inject(at = @At("RETURN"), method = "getHorizonHeight", cancellable = true)
    private void getHorizonHeight(LevelHeightAccessor level, CallbackInfoReturnable<Double> cir) {
        if(Config.clearVoid)
            cir.setReturnValue((double) level.getMinY());
    }

    @Inject(at = @At("RETURN"), method = "voidDarknessOnsetRange", cancellable = true)
    private void getClearColorScale(CallbackInfoReturnable<Float> cir) {
        if(Config.clearVoid)
            cir.setReturnValue(1.0F);
    }
}
