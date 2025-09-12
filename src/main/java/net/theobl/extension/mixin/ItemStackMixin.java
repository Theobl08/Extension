package net.theobl.extension.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.theobl.extension.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V", at = @At("HEAD"))
    private void unbreakable(int amount, ServerLevel level, LivingEntity entity, Consumer<Item> onBreak, CallbackInfo ci) {
        if(Config.unbreakableAtMaxUnbreakingLevel) {
            ItemStack stack = (ItemStack) (Object) this;
            TooltipDisplay tooltipdisplay = stack.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT);
            if (stack.has(DataComponents.UNBREAKABLE) && tooltipdisplay.shows(DataComponents.UNBREAKABLE))
                return;
            Holder<Enchantment> unbreaking = level.registryAccess().holderOrThrow(Enchantments.UNBREAKING);
            int unbreakingLevel = stack.getEnchantmentLevel(unbreaking);
            if (unbreakingLevel == unbreaking.value().getMaxLevel()) {
                extension$addUnbreakableTag(stack, level, unbreaking);
            }
        }
    }

    @Unique
    private void extension$addUnbreakableTag(ItemStack stack, ServerLevel level, Holder<Enchantment> unbreaking) {
        Holder<Enchantment> mending = level.registryAccess().holderOrThrow(Enchantments.MENDING);
        int mendingLevel = stack.getEnchantmentLevel(mending);

        stack.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);
        stack.setDamageValue(0);

        EnchantmentHelper.updateEnchantments(stack, (mutable) -> mutable.set(unbreaking, 0)); // removing unbreaking
        if(mendingLevel > 0)
            EnchantmentHelper.updateEnchantments(stack, (mutable) -> mutable.set(mending, 0)); // removing mending
    }
}
