package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.theobl.extension.block.CopperFireBlock;
import net.theobl.extension.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BaseFireBlock.class)
public abstract class BaseFireBlockMixin {
    @ModifyReturnValue(method = "getState", at = @At("RETURN"))
    private static BlockState test(BlockState original, @Local BlockState blockState) {
        if(CopperFireBlock.canSurviveOnBlock(blockState)) {
            return ModBlocks.COPPER_FIRE.get().defaultBlockState();
        } else {
            return original;
        }
    }
}
