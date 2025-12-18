package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DirtPathBlock.class)
public abstract class DirtPathBlockMixin {
    @ModifyReturnValue(method = "canSurvive", at = @At("RETURN"))
    private boolean canSurviveUnderNonFullBlocks(boolean original, @Local(argsOnly = true) LevelReader level, @Local(argsOnly = true) BlockPos pos, @Local(ordinal = 1) BlockState blockState) {
        return original || !blockState.isFaceSturdy(level, pos, Direction.DOWN, SupportType.FULL);
    }
}
