package net.theobl.extension.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.PistonHeadRenderer;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.blockentity.state.PistonHeadRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PistonHeadRenderer.class)
public class PistonHeadBlockRendererMixin {
    @Unique
    private float extension$partialTick;

    @Inject(method = "extractRenderState(Lnet/minecraft/world/level/block/piston/PistonMovingBlockEntity;Lnet/minecraft/client/renderer/blockentity/state/PistonHeadRenderState;FLnet/minecraft/world/phys/Vec3;Lnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V",
            at = @At("TAIL"))
    private void getNeededValues(PistonMovingBlockEntity blockEntity, PistonHeadRenderState renderState, float partialTick, Vec3 p_445923_, ModelFeatureRenderer.CrumblingOverlay breakProgress, CallbackInfo ci) {
        this.extension$partialTick = partialTick;
        renderState.setBlockEntityToRender(blockEntity.getRenderBlockEntity());
    }

    @Inject(method = "submit(Lnet/minecraft/client/renderer/blockentity/state/PistonHeadRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V",
            at = @At("TAIL"))
    private void renderPushedBlockEntities(PistonHeadRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera, CallbackInfo ci) {
        if(state.block != null) {
            BlockEntity blockEntity = state.getBlockEntityToRender();
            if(blockEntity != null) {
                BlockEntityRenderDispatcher dispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
                BlockEntityRenderer<? extends BlockEntity, ? extends BlockEntityRenderState> blockEntityRenderer = dispatcher.getRenderer(blockEntity);
                if(blockEntityRenderer != null) {
                    BlockEntityRenderState renderState = dispatcher.tryExtractRenderState(blockEntity, this.extension$partialTick, state.breakProgress, null);
                    if(renderState != null) {
                        poseStack.pushPose();
                        poseStack.translate(state.xOffset, state.yOffset, state.zOffset);
                        dispatcher.submit(renderState, poseStack, submitNodeCollector, camera);
                        poseStack.popPose();
                    }
                }
            }
        }
    }
}
