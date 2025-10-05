package net.theobl.extension.inventory;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;

public class ModMenuType {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, Extension.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<FletchingMenu>> FLETCHING = registerMenuType("fletching", FletchingMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, MenuType.MenuSupplier<T> factory) {
        return MENUS.register(name, () -> new MenuType<>(factory, FeatureFlags.VANILLA_SET));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
