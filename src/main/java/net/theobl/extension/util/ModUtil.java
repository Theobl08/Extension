package net.theobl.extension.util;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Block;
import net.theobl.extension.item.alchemy.ModPotions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    /**Apply chat formating to the given {@link Component} using the soon-to-be removed §-based formatting system*/
    public static Component applyChatFormattingLegacy(Component text, Rarity rarity) {
        String resetCode = switch (rarity) {
            case COMMON -> "§f";
            case UNCOMMON -> "§e";
            case RARE -> "§b";
            case EPIC -> "§d";
            case null, default -> "§r";
        };

        String str = text.getString()
                .replace("&0", "§0")
                .replace("&1", "§1")
                .replace("&2", "§2")
                .replace("&3", "§3")
                .replace("&4", "§4")
                .replace("&5", "§5")
                .replace("&6", "§6")
                .replace("&7", "§7")
                .replace("&8", "§8")
                .replace("&9", "§9")
                .replace("&a", "§a")
                .replace("&b", "§b")
                .replace("&c", "§c")
                .replace("&d", "§d")
                .replace("&e", "§e")
                .replace("&f", "§f")
                .replace("&k", "§k")
                .replace("&l", "§l")
                .replace("&m", "§m")
                .replace("&n", "§n")
                .replace("&o", "§o")
                .replace("&r", resetCode);
        return Component.literal(str);
    }

    /**Apply chat formating to the given {@link Component} using the newly text component format*/
    public static Component applyChatFormatting(Component text, Rarity rarity) {
        String plainText = text.getString();
        boolean startWithAndSymbol = plainText.startsWith("&");
        List<String> stringList = new ArrayList<>(Arrays.asList(plainText.split("&")));
        if(Objects.equals(stringList.getFirst(), ""))
            stringList.removeFirst();
        if (stringList.size() == 1 && !startWithAndSymbol) {
            return text;
        }
        for (String s : stringList) {
            stringList.set(stringList.indexOf(s), "&" + s);
        }

        MutableComponent component = Component.empty();
        var style = Style.EMPTY;
        for (String s : stringList) {
            if(stringList.indexOf(s) == 0 && !s.startsWith("&")) {
                component.append(s);
                continue;
            }

            if(s.length() <= 2 && s.startsWith("&")) { //case where at least two &-based consecutive are in the text (e.g. &5Hello &3&mWorld, &2Bookshelf&&3cie)
                if(isFormattingCode(s)) {
                    style = applyFormatting(style, s, rarity);
                } else {
                    component.append(Component.literal(s).withStyle(style)); // We have just a "&" alone (or followed by a non formatting character), so we keep is as-is
                }
                continue;
            }

            String format = s.substring(0, 2);
            int startIndex = 0;
            if(isFormattingCode(format)) {
                startIndex = 2;
            }
            String value = s.substring(startIndex);
            style = applyFormatting(style, format, rarity);
            component.append(Component.literal(value).withStyle(style));
        }
        return component;
    }

    private static boolean isFormattingCode(String format) {
        return switch (format) {
            case "&0", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9", "&a", "&b", "&c", "&d", "&e", "&f",
                 "&k", "&l", "&m", "&n", "&o", "&r" -> true;
            case null, default -> false;
        };
    }

    private static Style applyFormatting(Style style, String format, Rarity rarity) {
        if(!style.isItalic())
            style = style.withItalic(false); //We have to do this because DataComponents.CUSTOM_NAME always display custom item name in italic by default
        return switch (format) {
            case "&0" -> style.withColor(ChatFormatting.BLACK);
            case "&1" -> style.withColor(ChatFormatting.DARK_BLUE);
            case "&2" -> style.withColor(ChatFormatting.DARK_GREEN);
            case "&3" -> style.withColor(ChatFormatting.DARK_AQUA);
            case "&4" -> style.withColor(ChatFormatting.DARK_RED);
            case "&5" -> style.withColor(ChatFormatting.DARK_PURPLE);
            case "&6" -> style.withColor(ChatFormatting.GOLD);
            case "&7" -> style.withColor(ChatFormatting.GRAY);
            case "&8" -> style.withColor(ChatFormatting.DARK_GRAY);
            case "&9" -> style.withColor(ChatFormatting.BLUE);
            case "&a" -> style.withColor(ChatFormatting.GREEN);
            case "&b" -> style.withColor(ChatFormatting.AQUA);
            case "&c" -> style.withColor(ChatFormatting.RED);
            case "&d" -> style.withColor(ChatFormatting.LIGHT_PURPLE);
            case "&e" -> style.withColor(ChatFormatting.YELLOW);
            case "&f" -> style.withColor(ChatFormatting.WHITE);
            case "&k" -> style.withObfuscated(true);
            case "&l" -> style.withBold(true);
            case "&m" -> style.withStrikethrough(true);
            case "&n" -> style.withUnderlined(true);
            case "&o" -> style.withItalic(true);
            case "&r" -> rarity.getStyleModifier().apply(Style.EMPTY).withItalic(false);
            case null, default -> style; // not a formatting code -> the style stay as-is.
        };
    }

    static {
        POTIONS.addAll(ModPotions.POTIONS.getEntries());
    }
}
