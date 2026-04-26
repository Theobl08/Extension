package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CartographyTableMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.CartographyTableBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CartographyTableMenu.class)
public class CartographyTableMenuMixin {
    @Shadow
    @Final
    private ContainerLevelAccess access;

    @WrapMethod(method = "stillValid")
    private boolean stillValidForEveryCartographyTableBlock(Player player, Operation<Boolean> original) {
        return original.call(player) || this.access.evaluate(
                (level, pos) -> !(level.getBlockState(pos).getBlock() instanceof CartographyTableBlock)
                        ? false
                        : player.isWithinBlockInteractionRange(pos, 4.0), true
        );
    }
}
