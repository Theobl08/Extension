package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.LevelHeightAccessor;
import net.theobl.extension.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientLevel.ClientLevelData.class)
public abstract class ClientLevelMixin {
    @ModifyReturnValue(at = @At("RETURN"), method = "getHorizonHeight")
    private double getHorizonHeight(double original, @Local(argsOnly = true) LevelHeightAccessor level) {
        return Config.clearVoid ? level.getMinY() : original;
    }

    @ModifyReturnValue(at = @At("RETURN"), method = "voidDarknessOnsetRange")
    private float getClearColorScale(float original) {
        return Config.clearVoid ? 1.0F : original;
    }
}
