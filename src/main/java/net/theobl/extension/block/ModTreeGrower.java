package net.theobl.extension.block;

import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.theobl.extension.Extension;
import net.theobl.extension.worldgen.feature.ModTreeFeatures;

public class ModTreeGrower {

    public static final TreeGrower POTATO = new TreeGrower(
            Extension.MODID + ":potato",
            WeightedList.of(new Weighted<>(ModTreeFeatures.POTATO_TREE, 95), new Weighted<>(ModTreeFeatures.POTATO_TREE_TALL, 5)),
            WeightedList.of(ModTreeFeatures.MOTHER_POTATO_TREE),
            WeightedList.of(),
            ModTreeFeatures.POTATO_TREE
    );
    public static final TreeGrower RED_POPLAR = registerSimple("red_poplar", TreeFeatures.RED_POPLAR);
    public static final TreeGrower ORANGE_POPLAR = registerSimple("red_poplar", TreeFeatures.ORANGE_POPLAR);
    public static final TreeGrower YELLOW_POPLAR = registerSimple("yellow_poplar", TreeFeatures.YELLOW_POPLAR);

    public static TreeGrower registerSimple(String name, ResourceKey<Feature> tree) {
        return new TreeGrower(Extension.asResource(name).toString(), WeightedList.of(tree), WeightedList.of(), WeightedList.of(), tree);
    }
}
