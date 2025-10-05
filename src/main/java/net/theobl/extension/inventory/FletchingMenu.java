package net.theobl.extension.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class FletchingMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;
    private final Level level;
    protected final CraftingContainer craftSlots = new TransientCraftingContainer(this, 1, 3);
    protected final ResultContainer resultSlots = new ResultContainer();

    public FletchingMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, ContainerLevelAccess.NULL);
    }

    public FletchingMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(ModMenuType.FLETCHING.get(), containerId);
        this.access = access;
        this.level = playerInventory.player.level();
        this.addSlot(new ResultSlot(playerInventory.player, this.craftSlots, this.resultSlots, 0, 124, 35));
        for (int i = 0; i < 3; ++i) {
            this.addSlot(new Slot(this.craftSlots, i, 48, 17 + i * 18));
        }
        this.addStandardInventorySlots(playerInventory, 8, 84);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 0) {
                itemstack1.getItem().onCraftedBy(itemstack1, player);
                if (!this.moveItemStackTo(itemstack1, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            }
            else if (index >= 4 && index < 31) {
                if (!this.moveItemStackTo(itemstack1, 31, 40, false)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (index >= 31 && index < 40) {
                if (!this.moveItemStackTo(itemstack1, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.moveItemStackTo(itemstack1, 4, 40, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
            if (index == 0) {
                player.drop(itemstack1, false);
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, Blocks.FLETCHING_TABLE);
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
