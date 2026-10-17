package net.theobl.extension.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.Tags;
import net.theobl.extension.Extension;
import net.theobl.extension.fluid.ModFluids;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagsProvider extends FluidTagsProvider {
    public ModFluidTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Extension.MODID);
    }

    @Override
    protected IntrinsicHolderTagAppender<Fluid> tag(TagKey<Fluid> tag) {
        return new IntrinsicHolderTagAppender<>(super.tag(tag)) {
            @Override
            public ResourceKey<Fluid> convertElement(Fluid fluid) {
                return fluid.defaultFluidState().typeHolder().key();
            }
        };
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(Tags.Fluids.BEETROOT_SOUP).add(ModFluids.BEETROOT_SOUP, ModFluids.FLOWING_BEETROOT_SOUP);
    }
}
