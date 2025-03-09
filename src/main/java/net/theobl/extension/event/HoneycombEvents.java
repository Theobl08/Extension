package net.theobl.extension.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.theobl.extension.Extension;
import net.theobl.extension.block.custom.interfaces.IModWaxableBlock;

@Mod.EventBusSubscriber(modid = Extension.MOD_ID)
public final class HoneycombEvents {

    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        final ItemStack itemStack = event.getItemStack();
        if(!event.isCanceled() && itemStack.getItem() instanceof HoneycombItem honeyComb) {
            final Level level = event.getLevel();
            final BlockPos blockPos = event.getPos();
            final BlockState blockState = level.getBlockState(blockPos);
            if(blockState.getBlock() instanceof IModWaxableBlock) {
                event.setCancellationResult(IModWaxableBlock.applyWax(blockState, itemStack, event.getEntity(), blockPos, event.getHand(), level));
            }
        }
    }
}
