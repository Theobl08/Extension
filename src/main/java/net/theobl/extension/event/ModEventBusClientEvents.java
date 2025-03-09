package net.theobl.extension.event;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.entity.ModBlockEntities;
import net.theobl.extension.entity.client.ModModelLayers;

@Mod.EventBusSubscriber(modid = Extension.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.CRIMSON_BOAT, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.CRIMSON_CHEST_BOAT, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(ModModelLayers.WARPED_BOAT, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.WARPED_CHEST_BOAT, ChestBoatModel::createBodyModel);

//        event.registerLayerDefinition(ModModelLayers.WHITE_BOAT, BoatModel::createBodyModel);
//        event.registerLayerDefinition(ModModelLayers.WHITE_CHEST_BOAT, ChestBoatModel::createBodyModel);
        for (ModelLayerLocation layerLocation : ModModelLayers.COLORED_BOATS) {
            event.registerLayerDefinition(layerLocation, BoatModel::createBodyModel);
        }
        for (ModelLayerLocation layerLocation : ModModelLayers.COLORED_CHEST_BOATS) {
            event.registerLayerDefinition(layerLocation, ChestBoatModel::createBodyModel);
        }
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
}
