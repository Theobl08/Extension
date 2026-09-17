package net.theobl.extension.block;

import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.theobl.extension.Extension;
import net.theobl.extension.worldgen.ModTreeFeatures;

import java.util.Optional;

public class ModTreeGrower {

    public static final TreeGrower POTATO = new TreeGrower(
            Extension.MODID + ":potato",
            WeightedList.of(new Weighted<>(ModTreeFeatures.POTATO_TREE, 95), new Weighted<>(ModTreeFeatures.POTATO_TREE_TALL, 5)),
            WeightedList.of(ModTreeFeatures.MOTHER_POTATO_TREE),
            WeightedList.of(),
            ModTreeFeatures.POTATO_TREE
    );
}
