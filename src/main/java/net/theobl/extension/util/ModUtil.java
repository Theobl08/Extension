package net.theobl.extension.util;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.theobl.extension.item.alchemy.ModPotions;

import java.util.ArrayList;
import java.util.List;

public class ModUtil {
    @SuppressWarnings("deprecation")
    public static List<Holder<Potion>> POTIONS = new ArrayList<>(BuiltInRegistries.POTION.stream().map(BuiltInRegistries.POTION::wrapAsHolder)
            .filter(p -> !p.is(Potions.WATER)).toList());

    public static ItemStack createFilledResult(ItemStack itemStack, Player player, ItemStack newItemStack, boolean limitCreativeStackSize, int consumedAmount) {
        boolean isCreative = player.hasInfiniteMaterials();
        if (limitCreativeStackSize && isCreative) {
            if (!player.getInventory().contains(newItemStack)) {
                player.getInventory().add(newItemStack);
            }

            return itemStack;
        } else {
            itemStack.consume(consumedAmount, player);
            if (itemStack.isEmpty()) {
                return newItemStack;
            } else {
                if (!player.getInventory().add(newItemStack)) {
                    player.drop(newItemStack, false);
                }

                return itemStack;
            }
        }
    }

    public static <T> String name(T value) {
        return switch (value) {
            case Holder<?> holder -> holder.getKey() != null ? holder.getKey().identifier().getPath() : holder.toString();
            case null, default -> "";
        };
    }

    static {
        POTIONS.addAll(ModPotions.POTIONS.getEntries());
    }
}
