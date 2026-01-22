package net.theobl.extension.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.theobl.extension.block.ExtendedCauldronInteraction;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.util.ModUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CauldronInteraction.class)
public interface CauldronInteractionMixin {
    @Definition(id = "TRY_WITH_EMPTY_HAND", field = "Lnet/minecraft/world/InteractionResult;TRY_WITH_EMPTY_HAND:Lnet/minecraft/world/InteractionResult$TryEmptyHandInteraction;")
    @Expression("return TRY_WITH_EMPTY_HAND")
    @ModifyReturnValue(method = "lambda$bootStrap$1(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/InteractionResult;",
            at = @At("MIXINEXTRAS:EXPRESSION"))
    private static InteractionResult test(InteractionResult original, @Local PotionContents potionContents, @Local(argsOnly = true) BlockState state, @Local(argsOnly = true) Level level, @Local(argsOnly = true) BlockPos pos, @Local(argsOnly = true) Player player, @Local(argsOnly = true) InteractionHand hand, @Local(argsOnly = true) ItemStack itemInHand) {
        if(potionContents != null && potionContents.potion().isPresent() && ModUtil.POTIONS.contains(potionContents.potion().get())) {
            if (!level.isClientSide()) {
                Item usedItem = itemInHand.getItem();
                player.setItemInHand(hand, ItemUtils.createFilledResult(itemInHand, player, new ItemStack(Items.GLASS_BOTTLE)));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(usedItem));
                level.setBlockAndUpdate(pos, ModBlocks.POTION_CAULDRON.get(potionContents.potion().orElse(Potions.AWKWARD)).get().defaultBlockState());
                level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }
            return InteractionResult.SUCCESS;
        }
        else {
            return original;
        }
    }
}
