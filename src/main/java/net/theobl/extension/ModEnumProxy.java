package net.theobl.extension;

import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

import java.util.function.UnaryOperator;

public class ModEnumProxy {
    public static final EnumProxy<Rarity> POTATO_RARITY = new EnumProxy<>(
            Rarity.class, -1, "extension:potato", (UnaryOperator<Style>) style -> style.withColor(TextColor.GREEN)
    );
}
