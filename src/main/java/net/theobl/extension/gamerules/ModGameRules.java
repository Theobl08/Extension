package net.theobl.extension.gamerules;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;

import java.util.function.ToIntFunction;

public class ModGameRules {
    public static final DeferredRegister<GameRule<?>> GAME_RULES = DeferredRegister.create(BuiltInRegistries.GAME_RULE, Extension.MODID);

    public static final DeferredHolder<GameRule<?>, GameRule<Boolean>> BEETROOT_SOUP_SOURCE_CONVERSION = registerBoolean("beetroot_soup_source_conversion", GameRuleCategory.UPDATES, false);
    public static final DeferredHolder<GameRule<?>, GameRule<Boolean>> MUSHROOM_STEW_SOURCE_CONVERSION = registerBoolean("mushroom_stew_source_conversion", GameRuleCategory.UPDATES, false);


    private static DeferredHolder<GameRule<?>,GameRule<Boolean>> registerBoolean(String id, GameRuleCategory category, boolean defaultValue) {
        return registerGameRule(
                id,
                category,
                GameRuleType.BOOL,
                BoolArgumentType.bool(),
                Codec.BOOL,
                defaultValue,
                FeatureFlagSet.of(),
                GameRuleTypeVisitor::visitBoolean,
                b -> b ? 1 : 0
        );
    }

    private static <T> DeferredHolder<GameRule<?>, GameRule<T>> registerGameRule(
            String id,
            GameRuleCategory category,
            GameRuleType typeHint,
            ArgumentType<T> argumentType,
            Codec<T> codec,
            T defaultValue,
            FeatureFlagSet requiredFeatures,
            GameRules.VisitorCaller<T> visitorCaller,
            ToIntFunction<T> commandResultFunction
    ) {
        return GAME_RULES.register(
                id,
                () -> new GameRule<>(category, typeHint, argumentType, visitorCaller, codec, commandResultFunction, defaultValue, requiredFeatures)
        );
    }

    public static void register(IEventBus eventBus) {
        GAME_RULES.register(eventBus);
    }
}
