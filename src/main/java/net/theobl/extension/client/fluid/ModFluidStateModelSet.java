package net.theobl.extension.client.fluid;

import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterFluidModelsEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.theobl.extension.Extension;
import net.theobl.extension.fluid.ModFluids;
import net.theobl.extension.util.ModUtil;

@EventBusSubscriber(modid = Extension.MODID, value = Dist.CLIENT)
public class ModFluidStateModelSet {
    @SubscribeEvent
    private static void registerFluidModels(RegisterFluidModelsEvent event) {
        event.register(new FluidModel.Unbaked(
                new Material(Identifier.fromNamespaceAndPath(NeoForgeMod.MOD_ID, "block/milk_still")),
                new Material(Identifier.fromNamespaceAndPath(NeoForgeMod.MOD_ID, "block/milk_flowing")),
                null,
                state -> ARGB.opaque(ModUtil.BEETROOT_SOUP_COLOR)), ModFluids.BEETROOT_SOUP, ModFluids.FLOWING_BEETROOT_SOUP);
        event.register(new FluidModel.Unbaked(
                new Material(Identifier.fromNamespaceAndPath(NeoForgeMod.MOD_ID, "block/milk_still")),
                new Material(Identifier.fromNamespaceAndPath(NeoForgeMod.MOD_ID, "block/milk_flowing")),
                null,
                state -> ModUtil.MUSHROOM_STEW_COLOR), ModFluids.MUSHROOM_STEW, ModFluids.FLOWING_MUSHROOM_STEW);
    }
}
