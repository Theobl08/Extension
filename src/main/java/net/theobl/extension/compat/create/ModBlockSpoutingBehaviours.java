package net.theobl.extension.compat.create;

import com.simibubi.create.api.behaviour.spouting.CauldronSpoutingBehavior;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.theobl.extension.block.ModBlocks;

public class ModBlockSpoutingBehaviours {
    static void registerDefaults() {
        CauldronSpoutingBehavior.CAULDRON_INFO.register(NeoForgeMod.MILK.get(), new CauldronSpoutingBehavior.CauldronInfo(1000, ModBlocks.MILK_CAULDRON.get()));
    }
}
