package net.theobl.extension.block.custom.interfaces;

import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public interface IModChangeOverTimeBlock extends ChangeOverTimeBlock<WeatheringCopper.WeatherState> {

    static BlockState getAdjustedBlockState(BlockState blockState) {
        if(blockState.hasProperty(BlockStateProperties.POWER)) {
            blockState = blockState.setValue(BlockStateProperties.POWER, 0);
        }
        return blockState;
    }

    default float getChanceModifier() {
        return this.getAge().equals(WeatheringCopper.WeatherState.UNAFFECTED) ? 0.75F : 1.0F;
    }
}
