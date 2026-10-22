package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.level.block.EnchantingTableBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EnchantmentMenu.class)
public class EnchantmentMenuMixin {
    @Shadow
    @Final
    private ContainerLevelAccess access;

    @WrapMethod(method = "stillValid")
    private boolean stillValidForEveryEnchantingTableLikeBlock(Player player, Operation<Boolean> original) {
        return original.call(player) || this.access.evaluate(
                (level, pos) -> !(level.getBlockState(pos).getBlock() instanceof EnchantingTableBlock)
                        ? false
                        : player.isWithinBlockInteractionRange(pos, 4.0), true
        );
    }
}
