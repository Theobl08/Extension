package net.theobl.extension.mixin;

import net.minecraft.world.entity.ai.gossip.GossipType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(GossipType.class)
public class GossipTypeMixin {
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/gossip/GossipType;<init>(Ljava/lang/String;ILjava/lang/String;IIII)V"))
    private static void reintroduceDiscountStacking(Args args) {
        switch (args.<String>get(2)) {
            case "minor_positive" -> args.set(4, 200);
            case "major_positive" -> {
                args.set(4, 100);
                args.set(6, 100);
            }
        }
    }
}
