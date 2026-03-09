package net.theobl.extension.extensions;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.jspecify.annotations.Nullable;

public interface PistonHeadRenderStateExtension {
    default void setBlockEntityToRender(BlockEntity blockEntity) {}

    @Nullable
    default BlockEntity getBlockEntityToRender() {
        return null;
    }
}
