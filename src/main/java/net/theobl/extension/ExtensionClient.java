package net.theobl.extension;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.theobl.extension.compat.jei.ExtensionJeiPlugin;

@Mod(value = Extension.MODID, dist = Dist.CLIENT)
public class ExtensionClient {
    public ExtensionClient(ModContainer modContainer) {
        // This will use NeoForge's ConfigurationScreen to display this mod's configs
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        if(ModList.get().isLoaded("jei")) {
            NeoForge.EVENT_BUS.addListener(ExtensionJeiPlugin::recipesReceived);
            NeoForge.EVENT_BUS.addListener(ExtensionJeiPlugin::clientLogOut);
        }
    }
}
