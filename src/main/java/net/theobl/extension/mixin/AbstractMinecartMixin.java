package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.theobl.extension.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractMinecart.class)
public abstract class AbstractMinecartMixin {
    @Shadow
    public abstract AbstractMinecart.Type getMinecartType();

    @ModifyReturnValue(method = "getPickResult", at = @At("RETURN"))
    private ItemStack pickSpawnerMinecart(ItemStack original){
        if(this.getMinecartType() == AbstractMinecart.Type.SPAWNER) {
            return new ItemStack(ModItems.SPAWNER_MINECART.asItem());
        }
        return original;
    }
}
