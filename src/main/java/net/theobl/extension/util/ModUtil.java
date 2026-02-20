package net.theobl.extension.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Block;
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
            case Block block -> BuiltInRegistries.BLOCK.getKey(block).getPath();
            case null, default -> "";
        };
    }

    public static void showPotionInteractParticles(ServerLevel level, PotionContents potionContents, BlockPos pos, double yOffset) {
        level.sendParticles(
                ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, potionContents.getColorOr(PotionContents.BASE_POTION_COLOR)),
                pos.getX() + 0.5,
                pos.getY() + 0.4 + yOffset,
                pos.getZ() + 0.5,
                7,
                0.1, 0.0, 0.1, 0.0);
    }

    static {
        POTIONS.addAll(ModPotions.POTIONS.getEntries());
    }
}
