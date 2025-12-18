package net.theobl.extension.mixin;

import net.minecraft.world.entity.vehicle.minecart.MinecartSpawner;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.theobl.extension.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecartSpawner.class)
public abstract class MinecartSpawnerMixin {
    @Inject(method = "getDropItem", at = @At("RETURN"), cancellable = true)
    private void dropSpawnerMinecart(CallbackInfoReturnable<Item> cir) {
        cir.setReturnValue(ModItems.SPAWNER_MINECART.asItem());
    }

    @Inject(method = "getPickResult", at = @At("RETURN"), cancellable = true)
    private void pickSpawnerMinecart(CallbackInfoReturnable<ItemStack> cir){
        cir.setReturnValue(new ItemStack(ModItems.SPAWNER_MINECART.asItem()));
    }
}
