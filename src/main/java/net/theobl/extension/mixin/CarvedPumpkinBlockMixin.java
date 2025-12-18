package net.theobl.extension.mixin;

import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Predicate;

@Mixin(CarvedPumpkinBlock.class)
public abstract class CarvedPumpkinBlockMixin {
    @Shadow
    private static final Predicate<BlockState> PUMPKINS_PREDICATE = state -> state != null
            && (state.getBlock() instanceof CarvedPumpkinBlock);
}
