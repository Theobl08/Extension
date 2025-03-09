package net.theobl.extension.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;

import java.util.ArrayList;
import java.util.List;

public class ModModelLayers {
    public static final ModelLayerLocation CRIMSON_BOAT = new ModelLayerLocation(
            new ResourceLocation(Extension.MOD_ID, "boat/crimson"), "main");
    public static final ModelLayerLocation CRIMSON_CHEST_BOAT = new ModelLayerLocation(
            new ResourceLocation(Extension.MOD_ID, "chest_boat/crimson"), "main");

    public static final ModelLayerLocation WARPED_BOAT = new ModelLayerLocation(
            new ResourceLocation(Extension.MOD_ID, "boat/warped"), "main");
    public static final ModelLayerLocation WARPED_CHEST_BOAT = new ModelLayerLocation(
            new ResourceLocation(Extension.MOD_ID, "chest_boat/warped"), "main");

//    public static final ModelLayerLocation WHITE_BOAT = new ModelLayerLocation(
//            new ResourceLocation(Extension.MOD_ID, "boat/white"), "main");
//    public static final ModelLayerLocation WHITE_CHEST_BOAT = new ModelLayerLocation(
//            new ResourceLocation(Extension.MOD_ID, "chest_boat/white"), "main");
    public static final List<ModelLayerLocation> COLORED_BOATS = registerColoredBoatLayers(false);
    public static final List<ModelLayerLocation> COLORED_CHEST_BOATS = registerColoredBoatLayers(true);
    private static List<ModelLayerLocation> registerColoredBoatLayers(boolean hasChest){
        List<ModelLayerLocation> boat = new ArrayList<>();
        for (DyeColor color : ModBlocks.COLORS) {
            ModelLayerLocation layerLocation = new ModelLayerLocation(
                    new ResourceLocation(Extension.MOD_ID,(hasChest ? "chest_boat/" : "boat/") + color.getName()), "main");
            boat.add(layerLocation);
        }
        return boat;
    }
}
