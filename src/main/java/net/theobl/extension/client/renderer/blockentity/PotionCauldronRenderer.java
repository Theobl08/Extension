package net.theobl.extension.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ARGB;
import net.minecraft.world.phys.Vec3;
import net.theobl.extension.block.PotionCauldronBlock;
import net.theobl.extension.block.entity.PotionCauldronBlockEntity;
import net.theobl.extension.client.renderer.blockentity.state.PotionCauldronRenderState;
import net.theobl.extension.util.ModUtil;
import org.joml.Matrix4f;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public class PotionCauldronRenderer implements BlockEntityRenderer<PotionCauldronBlockEntity, PotionCauldronRenderState> {
    private static final double[] HEIGHT_PER_LEVEL = { 0.375f, 0.5625f, 0.75f, 0.9375f };
    @Override
    public PotionCauldronRenderState createRenderState() {
        return new PotionCauldronRenderState();
    }

    @Override
    public void extractRenderState(PotionCauldronBlockEntity blockEntity, PotionCauldronRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.potionColor = ModUtil.getPotionColor(blockEntity.getPotion());
        state.cauldronLevel = blockEntity.getBlockState().getValue(PotionCauldronBlock.LEVEL);
    }

    @Override
    public void submit(PotionCauldronRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        TextureAtlasSprite water = Minecraft.getInstance().getAtlasManager().get(Sheets.BLOCKS_MAPPER.defaultNamespaceApply("water_still"));
        poseStack.pushPose();
        poseStack.translate(0, HEIGHT_PER_LEVEL[state.cauldronLevel - 1], 0);

        int red = ARGB.red(state.potionColor);
        int green = ARGB.green(state.potionColor);
        int blue = ARGB.blue(state.potionColor);
        int alpha = 255;

        float sizeFactor = 0.125f; // The inside of cauldron is a square of 14p*14p instead of the full 16p*16p square
        float minU = (water.getU1() - water.getU0()) * (1 - sizeFactor);
        float maxU = (water.getU1() - water.getU0()) * sizeFactor;
        float minV = (water.getV1() - water.getV0()) * (1 - sizeFactor);
        float maxV = (water.getV1() - water.getV0()) * sizeFactor;

        submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.entityTranslucent(TextureAtlas.LOCATION_BLOCKS), (pose, consumer) -> {
            Matrix4f matrix = pose.pose();

            consumer.addVertex(matrix, 0.0f + sizeFactor, 0.0f, 1.0f - sizeFactor)
                    .setColor(red, green, blue, alpha)
                    .setUv(water.getU0() + maxU, water.getV0() + minV)
                    .setLight(state.lightCoords)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setNormal(0, 1, 0);

            consumer.addVertex(matrix, 1.0f - sizeFactor, 0.0f, 1.0f - sizeFactor)
                    .setColor(red, green, blue, alpha)
                    .setUv(water.getU0() + minU, water.getV0() + minV)
                    .setLight(state.lightCoords)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setNormal(0, 1, 0);

            consumer.addVertex(matrix, 1.0f - sizeFactor, 0.0f, 0.0f + sizeFactor)
                    .setColor(red, green, blue, alpha)
                    .setUv(water.getU0() + minU, water.getV0() + maxV)
                    .setLight(state.lightCoords)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setNormal(0, 1, 0);

            consumer.addVertex(matrix, 0.0f + sizeFactor, 0.0f, 0.0f + sizeFactor)
                    .setColor(red, green, blue, alpha)
                    .setUv(water.getU0() + maxU, water.getV0() + maxV)
                    .setLight(state.lightCoords)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setNormal(0, 1, 0);
        });
        poseStack.popPose();
    }

    @Override
    public int getViewDistance() {
        return Minecraft.getInstance().options.getEffectiveRenderDistance() * 16;
    }
}
