package net.theobl.extension.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.theobl.extension.Extension;

public class ModModelLayers {
    public static final ModelLayerLocation POTATO_RAFT = register("boat/potato");
    public static final ModelLayerLocation POTATO_CHEST_RAFT = register("chest_boat/potato");

    private static ModelLayerLocation register(String model) {
        return createLocation(model, "main");
    }

    private static ModelLayerLocation createLocation(String model, String layer) {
        return new ModelLayerLocation(Extension.asResource(model), layer);
    }
}
