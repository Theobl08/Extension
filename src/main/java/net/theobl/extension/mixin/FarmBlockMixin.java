package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.theobl.extension.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FarmBlock.class)
public class FarmBlockMixin {
    @WrapOperation(method = "fallOn", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/common/CommonHooks;onFarmlandTrample(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;FLnet/minecraft/world/entity/Entity;)Z"))
    private boolean configurableFarmlandTrampling(Level level, BlockPos pos, BlockState state, float fallDistance, Entity entity, Operation<Boolean> original) {
        if(Config.farmlandTrample)
            return original.call(level, pos, state, fallDistance, entity);
        else
            return false;
    }
}
