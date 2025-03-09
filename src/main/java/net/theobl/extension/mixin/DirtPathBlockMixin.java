package net.theobl.extension.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DirtPathBlock.class)
public class DirtPathBlockMixin {
    @Inject(at = @At("RETURN"), method = "canSurvive", cancellable = true)
    public void extension$pathUnderNonSolidBlocks(BlockState state, LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> cir){
        if(cir.getReturnValue()) return;
        cir.setReturnValue(!level.getBlockState(pos.above()).isFaceSturdy(level, pos, Direction.DOWN, SupportType.FULL));
    }
}
