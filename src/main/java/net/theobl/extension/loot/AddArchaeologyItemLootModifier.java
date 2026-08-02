package net.theobl.extension.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.List;

public class AddArchaeologyItemLootModifier extends LootModifier {
    public static final MapCodec<AddArchaeologyItemLootModifier> CODEC = RecordCodecBuilder.mapCodec(i -> codecStart(i)
            .and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(AddArchaeologyItemLootModifier::item))
            .and(Codec.INT.fieldOf("weight").forGetter(AddArchaeologyItemLootModifier::itemWeight))
            .apply(i, AddArchaeologyItemLootModifier::new));
    private final Item item;
    private final int itemWeight;

    public AddArchaeologyItemLootModifier(LootItemCondition[] conditions, int priority, Item item, int itemWeight) {
        super(conditions, priority);
        this.item = item;
        this.itemWeight = itemWeight;
    }

    public Item item() {
        return item;
    }

    public int itemWeight() {
        return itemWeight;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        float totalWeight = 0.0F; //Hold the total weight including our own

        Holder.Reference<LootTable> lootTable = context.getResolver().getOrThrow(
                ResourceKey.create(Registries.LOOT_TABLE, context.getQueriedLootTableId()) // Get dynamically the resource key of the loot table
        );

        List<LootPoolEntryContainer> entries = lootTable.value().getPool("main").entries;
        for (LootPoolEntryContainer poolEntry : entries) {
            if(poolEntry instanceof LootItem lootItem) {
                totalWeight += lootItem.weight;
            }
        }
        totalWeight += itemWeight;

        float chance = 1.0F / totalWeight;
        if(context.getRandom().nextFloat() < chance) {
            generatedLoot.clear();
            generatedLoot.add(new ItemStack(item));
        }
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
