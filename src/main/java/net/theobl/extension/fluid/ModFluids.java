package net.theobl.extension.fluid;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.item.ModItems;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, Extension.MODID);

    public static final DeferredHolder<Fluid, FlowingFluid> BEETROOT_SOUP = FLUIDS.register(
            "beetroot_soup",
            () -> new BaseFlowingFluid.Source(ModFluids.BEETROOT_SOUP_PROPERTIES)
    );
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_BEETROOT_SOUP = FLUIDS.register(
            "flowing_beetroot_soup",
            () -> new BaseFlowingFluid.Flowing(ModFluids.BEETROOT_SOUP_PROPERTIES)
    );
    public static final BaseFlowingFluid.Properties BEETROOT_SOUP_PROPERTIES =
            new BaseFlowingFluid.Properties(ModFluidTypes.BEETROOT_SOUP_TYPE, BEETROOT_SOUP, FLOWING_BEETROOT_SOUP)
                    .block(ModBlocks.BEETROOT_SOUP)
                    .bucket(ModItems.BEETROOT_SOUP_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
