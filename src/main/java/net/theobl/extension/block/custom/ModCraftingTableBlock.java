package net.theobl.extension.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.theobl.extension.screen.ModCraftingTableMenu;

public class ModCraftingTableBlock extends CraftingTableBlock {
    private static final Component GUI_TITLE = Component.translatable("container.crafting");

    public ModCraftingTableBlock(Properties pProperties) {
        super(pProperties);
    }

    public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
        return new SimpleMenuProvider((id, inventory, entity) ->
                new ModCraftingTableMenu(id, inventory, ContainerLevelAccess.create(worldIn, pos), this), GUI_TITLE);
    }
}
