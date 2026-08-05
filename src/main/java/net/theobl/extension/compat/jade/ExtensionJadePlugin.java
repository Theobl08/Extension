package net.theobl.extension.compat.jade;

import net.theobl.extension.block.PotionCauldronBlock;
import net.theobl.extension.block.entity.PotionCauldronBlockEntity;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class ExtensionJadePlugin implements IWailaPlugin {
    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(PotionCauldronProvider.INSTANCE, PotionCauldronBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(PotionCauldronProvider.Client.INSTANCE, PotionCauldronBlock.class);
    }
}
