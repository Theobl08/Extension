package net.theobl.extension.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.ChestRaft;
import net.minecraft.world.entity.vehicle.boat.Raft;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theobl.extension.Extension;
import net.theobl.extension.item.ModItems;

import java.util.function.Supplier;

public class ModEntityType {
    public static final DeferredRegister.Entities ENTITY_TYPES = DeferredRegister.createEntities(Extension.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<Raft>> POTATO_RAFT = ENTITY_TYPES.registerEntityType(
            "potato_raft",
            raftFactory(ModItems.POTATO_RAFT), MobCategory.MISC,
            b -> b.noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10)
    );

    public static final DeferredHolder<EntityType<?>, EntityType<ChestRaft>> POTATO_CHEST_RAFT = ENTITY_TYPES.registerEntityType(
            "potato_chest_raft",
            chestRaftFactory(ModItems.POTATO_CHEST_RAFT), MobCategory.MISC,
            b -> b.noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10)
    );

    private static EntityType.EntityFactory<Raft> raftFactory(Supplier<Item> dropItem) {
        return (entityType, level) -> new Raft(entityType, level, dropItem);
    }

    private static EntityType.EntityFactory<ChestRaft> chestRaftFactory(Supplier<Item> dropItem) {
        return (entityType, level) -> new ChestRaft(entityType, level, dropItem);
    }

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
