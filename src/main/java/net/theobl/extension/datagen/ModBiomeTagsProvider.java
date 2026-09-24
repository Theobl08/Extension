package net.theobl.extension.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.neoforged.neoforge.common.Tags;
import net.theobl.extension.Extension;
import net.theobl.extension.biome.ModBiomes;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsProvider extends BiomeTagsProvider {
    public ModBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Extension.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        this.tag(BiomeTags.IS_OVERWORLD).add(ModBiomes.ARBORETUM);
        this.tag(BiomeTags.HAS_TRIAL_CHAMBERS).add(ModBiomes.ARBORETUM);
        this.tag(BiomeTags.IS_FOREST).add(ModBiomes.ARBORETUM);
        this.tag(BiomeTags.STRONGHOLD_BIASED_TO).add(ModBiomes.ARBORETUM);
        this.tag(BiomeTags.HAS_ABANDONED_CAMP_FOREST).add(ModBiomes.ARBORETUM);

        this.tag(Tags.Biomes.IS_TEMPERATE_OVERWORLD).add(ModBiomes.ARBORETUM);
        this.tag(Tags.Biomes.PRIMARY_WOOD_TYPE_ACACIA).add(ModBiomes.ARBORETUM);
        this.tag(Tags.Biomes.PRIMARY_WOOD_TYPE_BIRCH).add(ModBiomes.ARBORETUM);
        this.tag(Tags.Biomes.PRIMARY_WOOD_TYPE_CHERRY).add(ModBiomes.ARBORETUM);
        this.tag(Tags.Biomes.PRIMARY_WOOD_TYPE_DARK_OAK).add(ModBiomes.ARBORETUM);
        this.tag(Tags.Biomes.PRIMARY_WOOD_TYPE_JUNGLE).add(ModBiomes.ARBORETUM);
        this.tag(Tags.Biomes.PRIMARY_WOOD_TYPE_MANGROVE).add(ModBiomes.ARBORETUM);
        this.tag(Tags.Biomes.PRIMARY_WOOD_TYPE_OAK).add(ModBiomes.ARBORETUM);
        this.tag(Tags.Biomes.PRIMARY_WOOD_TYPE_PALE_OAK).add(ModBiomes.ARBORETUM);
        this.tag(Tags.Biomes.PRIMARY_WOOD_TYPE_SPRUCE).add(ModBiomes.ARBORETUM);
//        this.tag(Tags.Biomes.PRIMARY_WOOD_TYPE_POPLAR).add(ModBiomes.ARBORETUM);
    }
}
