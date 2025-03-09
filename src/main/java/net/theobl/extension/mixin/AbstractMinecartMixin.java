package net.theobl.extension.mixin;

import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.theobl.extension.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractMinecart.class)
public abstract class AbstractMinecartMixin {

    @Shadow
    public abstract AbstractMinecart.Type getMinecartType();

    @Inject(at = @At("RETURN"), method = "getPickResult", cancellable = true)
    private void extension$pickBlockSpawnerMinecart(CallbackInfoReturnable<ItemStack> cir){
        if(this.getMinecartType() == AbstractMinecart.Type.SPAWNER){
            cir.setReturnValue(new ItemStack(ModItems.SPAWNER_MINECART.get()));
        }
    }
}
