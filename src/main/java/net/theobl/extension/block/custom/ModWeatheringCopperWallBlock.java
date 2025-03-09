package net.theobl.extension.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.theobl.extension.block.custom.interfaces.IModWaxableBlock;
import net.theobl.extension.block.custom.interfaces.IModWeatheringBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModWeatheringCopperWallBlock extends WallBlock implements IModWeatheringBlock, IModWaxableBlock {
    private final WeatheringCopper.WeatherState weatherState;

    public ModWeatheringCopperWallBlock(WeatheringCopper.WeatherState pWeatherState, Block pBlock, final FeatureFlag... featureFlags) {
        super(BlockBehaviour.Properties.copy(pBlock.defaultBlockState().getBlock()).requiredFeatures(featureFlags).forceSolidOn());
        this.weatherState = pWeatherState;
    }

    @Override
    public void randomTick(final @NotNull BlockState blockState, final @NotNull ServerLevel level, final @NotNull BlockPos blockPos, final @NotNull RandomSource randomSource) {
        IModWeatheringBlock.randomTick(this, blockState, level, blockPos, randomSource);
    }

    @Override
    public boolean isRandomlyTicking(final @NotNull BlockState pState) {
        return IModWeatheringBlock.isRandomlyTicking(pState);
    }

    @Override
    public WeatheringCopper.WeatherState getAge() {
        return this.weatherState;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(final BlockState blockState, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        final BlockState modifiedState = IModWeatheringBlock.getToolModifiedState(blockState, context, toolAction, isClient);
        return modifiedState != null ? modifiedState : super.getToolModifiedState(blockState, context, toolAction, isClient);
    }
}
