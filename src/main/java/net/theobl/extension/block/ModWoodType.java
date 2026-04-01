package net.theobl.extension.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.theobl.extension.Extension;

import static net.minecraft.world.level.block.state.properties.WoodType.register;

public class ModWoodType {

    public static final WoodType POTATO = register(
            new WoodType(
                    Extension.MODID + ":potato",
                    BlockSetType.CRIMSON,
                    SoundType.NETHER_WOOD,
                    SoundType.NETHER_WOOD_HANGING_SIGN,
                    SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE,
                    SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN
            )
    );
}
