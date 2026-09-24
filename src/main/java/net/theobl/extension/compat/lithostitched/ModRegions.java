package net.theobl.extension.compat.lithostitched;

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.region.Region;
import net.minecraft.resources.ResourceKey;
import net.theobl.extension.Extension;

public class ModRegions {
    public static final ResourceKey<Region> ARBORETUM = ResourceKey.create(LithostitchedRegistries.REGION, Extension.asResource("arboretum"));


}
