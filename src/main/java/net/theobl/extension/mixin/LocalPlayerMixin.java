package net.theobl.extension.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.HangingSignEditScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.theobl.extension.block.entity.ModHangingSignBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
    @Shadow
    @Final
    protected Minecraft minecraft;
    @Inject(at = @At("HEAD"), method = "openTextEdit", cancellable = true)
    public void extension$openTextEdit (SignBlockEntity pSignEntity, boolean pIsFrontText, CallbackInfo ci) {
        if(pSignEntity instanceof ModHangingSignBlockEntity hangingSignBlockEntity) {
            this.minecraft.setScreen(new HangingSignEditScreen(hangingSignBlockEntity, pIsFrontText, this.minecraft.isTextFilteringEnabled()));
            ci.cancel();
        }
    }
}
