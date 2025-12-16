package net.theobl.extension.inventory;

import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.theobl.extension.item.crafting.FletchingRecipe;
import net.theobl.extension.item.crafting.ModRecipeType;

import javax.annotation.Nullable;
import java.util.Optional;

public class FletchingMenu extends AbstractContainerMenu {
    public static final int RESULT_SLOT = 0;
    private static final int CRAFT_SLOT_START = 1;
    private static final int CRAFT_SLOT_END = 4;
    private static final int INV_SLOT_START = 4;
    private static final int INV_SLOT_END = 31;
    private static final int USE_ROW_SLOT_START = 31;
    private static final int USE_ROW_SLOT_END = 40;
    private final ContainerLevelAccess access;
    private final Player player;
    private final Level level;
    protected final CraftingContainer craftSlots = new TransientCraftingContainer(this, 1, 3);
    protected final ResultContainer resultSlots = new ResultContainer();

    public FletchingMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, ContainerLevelAccess.NULL);
    }

    public FletchingMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(ModMenuType.FLETCHING.get(), containerId);
        this.access = access;
        this.player = playerInventory.player;
        this.level = playerInventory.player.level();
        this.addSlot(new ResultSlot(playerInventory.player, this.craftSlots, this.resultSlots, 0, 124, 35));
        for (int i = 0; i < 3; ++i) {
            this.addSlot(new Slot(this.craftSlots, i, 48, 17 + i * SLOT_SIZE));
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
            if (index == RESULT_SLOT) {
                itemstack1.getItem().onCraftedBy(itemstack1, player);
                if (!this.moveItemStackTo(itemstack1, INV_SLOT_START, USE_ROW_SLOT_END, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            }
            else if (index >= INV_SLOT_START && index < USE_ROW_SLOT_END) {
                if (!this.moveItemStackTo(itemstack1, CRAFT_SLOT_START, CRAFT_SLOT_END, false)) {
                    if (index < INV_SLOT_END) {
                        if (!this.moveItemStackTo(itemstack1, USE_ROW_SLOT_START, USE_ROW_SLOT_END, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                    else if (!this.moveItemStackTo(itemstack1, INV_SLOT_START, INV_SLOT_END, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }
            else if (!this.moveItemStackTo(itemstack1, INV_SLOT_START, USE_ROW_SLOT_END, false)) {
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
            if (index == RESULT_SLOT) {
                player.drop(itemstack1, false);
            }
        }

        return itemstack;
    }

    protected static void slotChangedCraftingGrid(
            AbstractContainerMenu menu,
            ServerLevel level,
            Player player,
            CraftingContainer craftSlots,
            ResultContainer resultSlots
    ) {
        if (!level.isClientSide()) {
            CraftingInput craftMatrix = craftSlots.asCraftInput();
            ServerPlayer serverPlayer = (ServerPlayer) player;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<RecipeHolder<FletchingRecipe>> optional = level.getServer()
                    .getRecipeManager()
                    .getRecipeFor(ModRecipeType.FLETCHING.get(), craftMatrix, level);
            if (optional.isPresent()) {
                FletchingRecipe fletchingRecipe = optional.get().value();
                itemstack = fletchingRecipe.assemble(craftMatrix, player.level().registryAccess());
            }
            resultSlots.setItem(RESULT_SLOT, itemstack);
            serverPlayer.connection.send(new ClientboundContainerSetSlotPacket(menu.containerId, menu.incrementStateId(), RESULT_SLOT, itemstack));
        }
    }

    @Override
    public void slotsChanged(Container inventory) {
        this.access.execute((level, blockPos) -> {
            if (level instanceof ServerLevel serverlevel) {
                slotChangedCraftingGrid(this, serverlevel, this.player, this.craftSlots, this.resultSlots);
            }
        });
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.access.execute((p_39371_, p_39372_) -> this.clearContainer(player, this.craftSlots));
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
