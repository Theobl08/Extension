package net.theobl.extension.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Flammable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.util.ModUtil;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    /**
     * Create a new provider.
     *
     * @param packOutput     the output location
     * @param lookupProvider a {@linkplain CompletableFuture} supplying the registries
     */
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        Builder<Flammable, Block> flammable = builder(NeoForgeDataMaps.FLAMMABLE);
        flammable.add(ModBlocks.ORANGE_SHRUB, new Flammable(60, 100), false);
        flammable.add(ModBlocks.YELLOW_SHRUB, new Flammable(60, 100), false);
        ModBlocks.BEEHIVES.values().stream().filter(ModUtil::isFlammableWood).forEach(block -> flammable.add(block, new Flammable(5, 20), false));
        ModBlocks.BOOKSHELVES.values().stream().filter(ModUtil::isFlammableWood).forEach(block -> flammable.add(block, new Flammable(30, 20), false));
        ModBlocks.LECTERNS.values().stream().filter(ModUtil::isFlammableWood).forEach(block -> flammable.add(block, new Flammable(30, 20), false));
    }
}
