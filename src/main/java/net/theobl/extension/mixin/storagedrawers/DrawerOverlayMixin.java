package net.theobl.extension.mixin.storagedrawers;

import com.jaquadro.minecraft.storagedrawers.integration.DrawerOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(DrawerOverlay.class)
public abstract class DrawerOverlayMixin {
    @ModifyArg(method = "getOverlay(Lcom/jaquadro/minecraft/storagedrawers/block/tile/BlockEntityDrawers;)Ljava/util/List;",
            at = @At(value = "INVOKE", target = "Lcom/jaquadro/minecraft/storagedrawers/integration/DrawerOverlay;getOverlay(Lcom/jaquadro/minecraft/storagedrawers/block/tile/BlockEntityDrawers;Z)Ljava/util/List;"))
    private boolean alwaysIncludeContent(boolean value) {
        return true;
    }
}
