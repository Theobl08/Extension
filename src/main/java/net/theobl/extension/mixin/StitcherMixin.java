package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.texture.SpriteContents;
import net.minecraft.client.renderer.texture.Stitcher;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Stitcher.class)
public class StitcherMixin {
    @Mutable
    @Shadow
    @Final
    private int padding;

    @Mutable
    @Shadow
    @Final
    private int mipLevel;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void partialFixToMC303675(int maxWidth, int maxHeight, int mipLevel, int anisotropyBit, CallbackInfo ci) {
        //This will fix MC303675 for all atlas but block atlas
        //Due to weird dark outline if we try to fix block atlas
        if(mipLevel == 0)
            this.padding = 0;
    }
}
