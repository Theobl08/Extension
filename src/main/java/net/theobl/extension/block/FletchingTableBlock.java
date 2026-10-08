package net.theobl.extension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.theobl.extension.inventory.FletchingMenu;
import net.theobl.extension.stats.ModStats;

public class FletchingTableBlock extends Block {
    private static final Component CONTAINER_TITLE = Component.translatable("container.fletching");

    public FletchingTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            player.openMenu(state.getMenuProvider(level, pos));
            player.awardStat(ModStats.INTERACT_WITH_FLETCHING_TABLE);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider(
                (containerId, inventory, player) -> new FletchingMenu(containerId, inventory, ContainerLevelAccess.create(level, pos)), CONTAINER_TITLE
        );
    }
}
