package net.theobl.extension.mixin;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Blocks.class)
public abstract class BlocksMixin {
    @ModifyVariable(method = "shulkerBoxProperties", argsOnly = true, at = @At("HEAD"))
    private static MapColor fixMC201332(MapColor original) {
        if(original == MapColor.TERRACOTTA_PURPLE) {
            return MapColor.COLOR_PURPLE;
        }
        else {
            return original;
        }
    }
}
