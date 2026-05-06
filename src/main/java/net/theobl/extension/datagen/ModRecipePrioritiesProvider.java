package net.theobl.extension.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.common.data.RecipePrioritiesProvider;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.block.WoodTypeCollection;
import net.theobl.extension.util.ModUtil;

import java.util.concurrent.CompletableFuture;

public class ModRecipePrioritiesProvider extends RecipePrioritiesProvider {
    public ModRecipePrioritiesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Extension.MODID);
    }

    @Override
    protected void start() {
        this.add(Identifier.withDefaultNamespace(ModUtil.name(Blocks.CRAFTING_TABLE)), -1);
        this.add(Identifier.withDefaultNamespace(ModUtil.name(Blocks.CARTOGRAPHY_TABLE)), -1);
    }
}
