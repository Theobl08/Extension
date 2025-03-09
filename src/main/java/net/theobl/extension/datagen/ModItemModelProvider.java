package net.theobl.extension.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Extension.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.RED_NETHER_BRICK);
        simpleItem(ModItems.BLUE_NETHER_BRICK);

        paneItem(ModBlocks.TINTED_GLASS_PANE, Items.TINTED_GLASS);

        simpleBlockItem(ModBlocks.REDSTONE_LANTERN);

        simpleItem(ModItems.VIBRANT_RED_DYE);
        simpleItem(ModItems.DULL_ORANGE_DYE);
        simpleItem(ModItems.BRIGHT_YELLOW_DYE);
        simpleItem(ModItems.CHARTREUSE_DYE);
        simpleItem(ModItems.VIBRANT_GREEN_DYE);
        simpleItem(ModItems.SPRING_GREEN_DYE);
        simpleItem(ModItems.BRIGHT_CYAN_DYE);
        simpleItem(ModItems.CAPRI_DYE);
        simpleItem(ModItems.ULTRAMARINE_DYE);
        simpleItem(ModItems.VIOLET_DYE);
        simpleItem(ModItems.VIBRANT_PURPLE_DYE);
        simpleItem(ModItems.BRIGHT_MAGENTA_DYE);
        simpleItem(ModItems.ROSE_DYE);
        simpleItem(ModItems.DARK_GRAY_DYE);
        simpleItem(ModItems.SILVER_DYE);
        simpleItem(ModItems.ALPHA_WHITE_DYE);

        simpleItem(ModItems.CRIMSON_BOAT);
        simpleItem(ModItems.CRIMSON_CHEST_BOAT);
        simpleItem(ModItems.WARPED_BOAT);
        simpleItem(ModItems.WARPED_CHEST_BOAT);

        for (RegistryObject<Item> item : ModItems.COLORED_SIGNS) {
            simpleItem(item);
        }

        for (RegistryObject<Item> item : ModItems.COLORED_HANGING_SIGNS) {
            simpleItem(item);
        }

        for (RegistryObject<Item> item : ModItems.COLORED_BOATS) {
            simpleItem(item);
        }

        for (RegistryObject<Item> item : ModItems.COLORED_CHEST_BOATS) {
            simpleItem(item);
        }

        for (RegistryObject<Block> block : ModBlocks.COLORED_DOORS) {
            simpleBlockItem(block);
        }

        simpleItem(ModItems.NETHERITE_HORSE_ARMOR);
        simpleItem(ModItems.SPAWNER_MINECART);

//        simpleItem(ModItems.WHITE_SIGN);
//        simpleItem(ModItems.WHITE_HANGING_SIGN);

        //fenceInventory("white_fence", modLoc("block/white_planks"));
        //fenceGate("white_fence_gate", modLoc("block/white_planks"));
//        simpleBlockItem(ModBlocks.WHITE_DOOR);
//        simpleBlockItem(ModBlocks.LIGHT_GRAY_DOOR);
//        simpleBlockItem(ModBlocks.GRAY_DOOR);
//        simpleBlockItem(ModBlocks.BLACK_DOOR);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Extension.MOD_ID, "item/"+item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Extension.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder paneItem(RegistryObject<Block> item, Item texture) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation("block/" + ForgeRegistries.ITEMS.getKey(texture).getPath())).renderType("translucent");
    }
}
