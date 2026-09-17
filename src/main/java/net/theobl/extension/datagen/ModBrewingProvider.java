package net.theobl.extension.datagen;

import net.minecraft.data.recipes.BrewingProvider;
import net.minecraft.data.recipes.BrewingRecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Recipe;
import net.theobl.extension.Extension;
import net.theobl.extension.item.ModItems;

public class ModBrewingProvider extends BrewingProvider {
    private final RecipeOutput output;

    protected ModBrewingProvider(RecipeOutput output) {
        super(output);
        this.output = output;
    }

    @Override
    protected void addContainers() {
        this.addContainer(Items.LINGERING_POTION);
        this.addContainer(Items.POTION);
        this.addContainer(Items.SPLASH_POTION);
    }

    @Override
    protected void addContainerTransformations() {
        this.addContainerTransformation(Items.POTION, Items.GUNPOWDER, Items.SPLASH_POTION);
        this.addContainerTransformation(Items.SPLASH_POTION, Items.DRAGON_BREATH, Items.LINGERING_POTION);
    }

    @Override
    protected void buildMixes() {
        this.buildMix(Potions.WATER, ModItems.BLUE_NETHER_WART.get(), Potions.AWKWARD);
        this.buildStartMix(Items.RABBIT_HIDE, Potions.LUCK);
    }

    @Override
    protected void save(BrewingRecipeBuilder builder) {
        ResourceKey<Recipe<?>> id = builder.defaultId();
        builder.save(this.output, Extension.MODID + ":" + id.identifier().getPath());
    }
}
