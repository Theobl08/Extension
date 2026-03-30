package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.theobl.extension.block.CopperFireBlock;
import net.theobl.extension.block.EnderFireBlock;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.block.RedstoneFireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BaseFireBlock.class)
public abstract class BaseFireBlockMixin {
    @ModifyReturnValue(method = "getState", at = @At("RETURN"))
    private static BlockState getCopperFireState(BlockState original, @Local BlockState belowState) {
        if(CopperFireBlock.canSurviveOnBlock(belowState)) {
            return ModBlocks.COPPER_FIRE.get().defaultBlockState();
        } else if (RedstoneFireBlock.canSurviveOnBlock(belowState)) {
            return ModBlocks.REDSTONE_FIRE.get().defaultBlockState();
        } else if (EnderFireBlock.canSurviveOnBlock(belowState)) {
            return ModBlocks.ENDER_FIRE.get().defaultBlockState();
        } else {
            return original;
        }
    }
}
