package net.theobl.extension.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Extension.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.SPAWNER_MINECART.get());
        basicItem(ModItems.NETHERITE_HORSE_ARMOR.get());
        basicItem(ModItems.BLUE_NETHER_WART.get());
        basicItem(ModItems.RED_NETHER_BRICK.get());
        basicItem(ModItems.BLUE_NETHER_BRICK.get());
        basicItem(ModBlocks.REDSTONE_LANTERN.asItem());
        basicItem(ModBlocks.REDSTONE_CAMPFIRE.asItem());
        spawnEggItem(ModItems.ILLUSIONER_SPAWN_EGG.get());

        withExistingParent(ModBlocks.TINTED_GLASS_PANE.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0", mcLoc("block/tinted_glass"));
    }
}
