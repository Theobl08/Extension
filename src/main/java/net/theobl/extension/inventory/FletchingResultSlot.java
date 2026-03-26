package net.theobl.extension.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.level.Level;
import net.theobl.extension.item.crafting.ModRecipeType;

public class FletchingResultSlot extends ResultSlot {
    private final CraftingContainer craftSlots;
    private final Player player;
    public FletchingResultSlot(Player player, CraftingContainer craftSlots, Container container, int id, int x, int y) {
        super(player, craftSlots, container, id, x, y);
        this.player = player;
        this.craftSlots = craftSlots;
    }

    private static NonNullList<ItemStack> copyAllInputItems(CraftingInput input) {
        NonNullList<ItemStack> result = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        for (int slot = 0; slot < result.size(); slot++) {
            result.set(slot, input.getItem(slot));
        }

        return result;
    }

    private NonNullList<ItemStack> getRemainingItems(CraftingInput input, Level level) {
        return level instanceof ServerLevel serverLevel
                ? serverLevel.recipeAccess()
                .getRecipeFor(ModRecipeType.FLETCHING.get(), input, serverLevel)
                .map(recipe -> recipe.value().getRemainingItems(input))
                .orElseGet(() -> copyAllInputItems(input))
                : CraftingRecipe.defaultCraftingReminder(input);
    }

    @Override
    public void onTake(Player player, ItemStack carried) {
        this.checkTakeAchievements(carried);
        CraftingInput.Positioned positionedRecipe = this.craftSlots.asPositionedCraftInput();
        CraftingInput input = positionedRecipe.input();
        int recipeLeft = positionedRecipe.left();
        int recipeTop = positionedRecipe.top();
        net.neoforged.neoforge.common.CommonHooks.setCraftingPlayer(player);
        NonNullList<ItemStack> remaining = this.getRemainingItems(input, player.level());
        net.neoforged.neoforge.common.CommonHooks.setCraftingPlayer(null);

        for (int y = 0; y < input.height(); y++) {
            for (int x = 0; x < input.width(); x++) {
                int slot = x + recipeLeft + (y + recipeTop) * this.craftSlots.getWidth();
                ItemStack itemStack = this.craftSlots.getItem(slot);
                ItemStack replacement = remaining.get(x + y * input.width());
                if (!itemStack.isEmpty()) {
                    this.craftSlots.removeItem(slot, 1);
                    itemStack = this.craftSlots.getItem(slot);
                }

                if (!replacement.isEmpty()) {
                    if (itemStack.isEmpty()) {
                        this.craftSlots.setItem(slot, replacement);
                    } else if (ItemStack.isSameItemSameComponents(itemStack, replacement)) {
                        replacement.grow(itemStack.getCount());
                        this.craftSlots.setItem(slot, replacement);
                    } else if (!this.player.getInventory().add(replacement)) {
                        this.player.drop(replacement, false);
                    }
                }
            }
        }
    }
}
