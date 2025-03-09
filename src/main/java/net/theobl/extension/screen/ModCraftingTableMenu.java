package net.theobl.extension.screen;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.block.Block;

public class ModCraftingTableMenu extends CraftingMenu {
    private final ContainerLevelAccess worldPos;
    private final Block craftingTable;

    public ModCraftingTableMenu(int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess worldPos, Block craftingTable) {
        super(pContainerId, pPlayerInventory, worldPos);
        this.worldPos = worldPos;
        this.craftingTable = craftingTable;
    }

    protected static boolean isWithinUsableDistance(ContainerLevelAccess worldPos, Player playerIn, Block targetBlock) {
        return (Boolean) worldPos.evaluate((world, pos) ->
                world.getBlockState(pos).getBlock() == targetBlock && playerIn.distanceToSqr((double) pos.getX() + 0.5,
                        (double) pos.getY() + 0.5, (double) pos.getZ() + 0.5) <= 64.0, (Object) true);
    }

    public boolean stillValid(Player playerIn) {
        return ModCraftingTableMenu.isWithinUsableDistance(this.worldPos, playerIn, this.craftingTable);
    }
}
