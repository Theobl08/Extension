package net.theobl.extension.block;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.theobl.extension.Extension;
import net.theobl.extension.worldgen.ModTreeFeatures;

import java.util.Optional;

public class ModTreeGrower {

    public static final TreeGrower POTATO = new TreeGrower(
            Extension.MODID + ":potato",
            0.05F,
            Optional.of(ModTreeFeatures.MOTHER_POTATO_TREE),
            Optional.empty(),
            Optional.of(ModTreeFeatures.POTATO_TREE),
            Optional.of(ModTreeFeatures.POTATO_TREE_TALL),
            Optional.empty(),
            Optional.empty()
    );
}
