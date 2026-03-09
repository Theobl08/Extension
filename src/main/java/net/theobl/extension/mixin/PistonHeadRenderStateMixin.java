package net.theobl.extension.mixin;

import net.minecraft.client.renderer.blockentity.state.PistonHeadRenderState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.theobl.extension.extensions.PistonHeadRenderStateExtension;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PistonHeadRenderState.class)
public class PistonHeadRenderStateMixin implements PistonHeadRenderStateExtension {
    @Unique
    private BlockEntity extension$blockEntityToRender;

    @Override
    public @Nullable BlockEntity getBlockEntityToRender() {
        return extension$blockEntityToRender;
    }

    @Override
    public void setBlockEntityToRender(BlockEntity blockEntity) {
        this.extension$blockEntityToRender = blockEntity;
    }
}
