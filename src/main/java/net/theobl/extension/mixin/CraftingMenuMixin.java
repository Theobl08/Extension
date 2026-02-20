package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.block.CraftingTableBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CraftingMenu.class)
public class CraftingMenuMixin {
    @Shadow
    @Final
    private ContainerLevelAccess access;

    @WrapMethod(method = "stillValid")
    private boolean stillValidForEveryCraftingTableLikeBlock(Player player, Operation<Boolean> original) {
        return original.call(player) || this.access.evaluate(
                (level, pos) -> !(level.getBlockState(pos).getBlock() instanceof CraftingTableBlock)
                        ? false
                        : player.isWithinBlockInteractionRange(pos, 4.0), true
        );
    }
}
