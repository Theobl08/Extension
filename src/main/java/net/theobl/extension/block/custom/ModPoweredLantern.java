package net.theobl.extension.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModPoweredLantern extends LanternBlock {
    public ModPoweredLantern(Properties pProperties) {
        super(pProperties);
    }

    @SuppressWarnings("deprecation")
    public boolean isSignalSource(BlockState pState) {
        return true;
    }

    @SuppressWarnings("deprecation")
    public int getSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
        return 15;
    }
}
