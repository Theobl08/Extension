package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SoulFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SoulFireBlock.class)
public abstract class SoulFireBlockMixin {
    @WrapMethod(method = "canSurvive")
    private boolean canSurviveAccurate(BlockState state, LevelReader level, BlockPos pos, Operation<Boolean> original) {
        return original.call(state, level, pos) && level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP);
    }
}
