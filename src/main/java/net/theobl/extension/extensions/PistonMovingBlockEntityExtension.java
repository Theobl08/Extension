package net.theobl.extension.extensions;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jspecify.annotations.Nullable;

public interface PistonMovingBlockEntityExtension {
    @Nullable
    default BlockEntity getRenderBlockEntity() {
        return null;
    }

    default void setCarriedBlockEntity(@Nullable BlockEntity blockentity, Level level) {

    };
}
