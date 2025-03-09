package net.theobl.extension.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.theobl.extension.Extension;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.item.ModItems;
import org.jetbrains.annotations.NotNull;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, Extension.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //Block Translations
//        ModBlocks.BLOCKS.getEntries().forEach(blockRegistryObject -> {
//            this.add(blockRegistryObject.get(), capitalizeString(filterBlockLang(blockRegistryObject.get())));
//        });

        //Item Translations
        ModItems.ITEMS.getEntries().forEach(itemRegistryObject -> {
            if(itemRegistryObject.get().toString().contains("boat")){
                if(itemRegistryObject.get().toString().contains("chest_boat")){
                    this.add(itemRegistryObject.get(), filterChestBoatLang(itemRegistryObject.get()));
                }
                else {
                    this.add(itemRegistryObject.get(), capitalizeString(filterItemLang(itemRegistryObject.get())));
                }
            }
            else if (itemRegistryObject.get().toString().contains("minecart")) {
                this.add(itemRegistryObject.get(), "Minecart with Monster Spawner");
            }
            else /*if (itemRegistryObject.get().toString().contains("dye"))*/ {
                this.add(itemRegistryObject.get(), capitalizeString(filterItemLang(itemRegistryObject.get())));
            }
        });

        this.add("itemGroup.extension_misc", "Extension Miscellaneous");
        this.add("itemGroup.wood_blocks", "Wood Blocks");
        this.add("itemGroup.alpha_colored_blocks", "Alpha Colored Blocks");
    }

    private static @NotNull String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++){
            if(!found && Character.isLetter(chars[i])){
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '_') {
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    private static @NotNull String filterBlockLang(@NotNull Block key){
        return key.getDescriptionId().replace("block.extension.","").replace("_"," ");
    }

    private static @NotNull String filterItemLang(@NotNull ItemLike key){
        return key.asItem().getDescriptionId().replace("item.extension.","").replace("_"," ")
                .replace("block.extension.","");
    }

    private static @NotNull String filterChestBoatLang(@NotNull ItemLike key){
        String type = key.asItem().getDescriptionId()
                .replace("item.extension.","")
                .replace("chest_boat","")
                .replace("_"," ");

        String name = capitalizeString(type)+"Boat with Chest";
        return name;
    }
}
