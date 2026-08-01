package net.theobl.extension.mixin;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.minecraft.world.level.block.entity.DecoratedPotPatterns;
import net.theobl.extension.block.entity.ModDecoratedPotPatterns;
import net.theobl.extension.item.ModItems;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiConsumer;

@Debug(export = true)
@Mixin(DecoratedPotPatterns.class)
public abstract class DecoratedPotPatternsMixin {
    @Inject(method = "itemToPatternMappings", at = @At("TAIL"))
    private static void extension$addDecoratedPotPatterns(BiConsumer<ResourceKey<Item>, ResourceKey<DecoratedPotPattern>> itemToPattern, CallbackInfo ci) {
        itemToPattern.accept(ModItems.EMPTY_POTTERY_SHERD.getKey(), ModDecoratedPotPatterns.EMPTY.getKey());
    }
}
