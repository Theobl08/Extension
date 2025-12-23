package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.StairsShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DoorBlock.class)
public class DoorBlockMixin {
    @Definition(id = "isFaceSturdy", method = "Lnet/minecraft/world/level/block/state/BlockState;isFaceSturdy(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Z")
    @Expression("?.isFaceSturdy(?,?,?)")
    @ModifyExpressionValue(method = "canSurvive", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean canSurviveOnStairsUnderConditions(boolean original, @Local(argsOnly = true)BlockState state, @Local(ordinal = 1)BlockState blockState) {
        boolean canSurvive = false;
        if(blockState.is(BlockTags.STAIRS)) {
            StairsShape stairsShape = blockState.getValue(StairBlock.SHAPE);
            Direction stairDirection = blockState.getValue(StairBlock.FACING).getOpposite();
            Direction doorDirection = state.getValue(DoorBlock.FACING);
            if(doorDirection == stairDirection && !stairsShape.getSerializedName().contains("outer"))
                canSurvive = true;
            else if(stairsShape == StairsShape.INNER_LEFT && doorDirection == stairDirection.getCounterClockWise())
                canSurvive = true;
            else if(stairsShape == StairsShape.INNER_RIGHT && doorDirection == stairDirection.getClockWise())
                canSurvive = true;
        }
        return original || canSurvive;
    }
}
