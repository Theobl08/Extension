package net.theobl.extension.mixin.create;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.fluids.pipes.VanillaFluidTargets;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidStack;
import net.theobl.extension.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(VanillaFluidTargets.class)
public abstract class VanillaFluidTargetsMixin {
    @Expression("return false")
    @ModifyReturnValue(method = "canProvideFluidWithoutCapability", at = @At("MIXINEXTRAS:EXPRESSION"))
    private static boolean extension$canProvideMilkWithoutCapability(boolean original, @Local(argsOnly = true) BlockState state) {
        if(state.is(ModBlocks.MILK_CAULDRON)) {
            return true;
        }
        return original;
    }

    @Definition(id = "EMPTY", field = "Lnet/neoforged/neoforge/fluids/FluidStack;EMPTY:Lnet/neoforged/neoforge/fluids/FluidStack;")
    @Expression("return EMPTY")
    @ModifyReturnValue(method = "drainBlock", at = @At(value = "MIXINEXTRAS:EXPRESSION", ordinal = 1))
    private static FluidStack extension$drainBlock(FluidStack original, Level level, BlockPos pos, BlockState state, boolean simulate) {
        if (state.is(ModBlocks.MILK_CAULDRON)) {
            if (!simulate)
                level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), Block.UPDATE_ALL);
            return new FluidStack(NeoForgeMod.MILK, 1000);
        }
        return original;
    }
}
