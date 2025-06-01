package net.theobl.extension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.theobl.extension.item.ModItems;

public class BlueNetherWartBlock extends NetherWartBlock {
    public BlueNetherWartBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(ModItems.BLUE_NETHER_WART.get());
    }
}
