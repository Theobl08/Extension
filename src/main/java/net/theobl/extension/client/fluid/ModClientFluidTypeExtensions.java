package net.theobl.extension.client.fluid;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.theobl.extension.util.ModUtil;
import org.joml.Vector4f;
import org.jspecify.annotations.Nullable;

public final class ModClientFluidTypeExtensions {
    public static final IClientFluidTypeExtensions BEETROOT_SOUP_FLUID_TYPE_EXTENSION = createSimple(null, ModUtil.BEETROOT_SOUP_COLOR);
    public static final IClientFluidTypeExtensions MUSHROOM_STEW_SOUP_FLUID_TYPE_EXTENSION = createSimple(null, ModUtil.MUSHROOM_STEW_COLOR);

    private static IClientFluidTypeExtensions createSimple(@Nullable Identifier overlayTexture, int fogColor) {
        return new IClientFluidTypeExtensions() {
            @Override
            public @Nullable Identifier getRenderOverlayTexture(Minecraft mc) {
                return overlayTexture;
            }

            @Override
            public void modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
                fluidFogColor.set(ARGB.vector4fFromARGB32(fogColor));
            }

            @Override
            public void modifyFogRender(Camera camera, @Nullable FogEnvironment environment, float renderDistance, float partialTick, FogData fogData) {
                if (camera.entity() instanceof Entity entity && entity.isSpectator()) {
                    fogData.environmentalStart = 0.0F;
                    fogData.environmentalEnd = renderDistance * 0.5F;
                } else {
                    fogData.environmentalStart = 0.0F;
                    fogData.environmentalEnd = 5.0F;
                }

                fogData.skyEnd = fogData.environmentalEnd;
                fogData.cloudEnd = fogData.environmentalEnd;
            }
        };
    }
}
