package net.theobl.extension.compat.jade;

import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.theobl.extension.Extension;
import net.theobl.extension.block.entity.PotionCauldronBlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.StreamServerDataProvider;
import snownee.jade.api.config.IPluginConfig;

public class PotionCauldronProvider implements StreamServerDataProvider<BlockAccessor, PotionCauldronProvider.Data> {
    public static final PotionCauldronProvider INSTANCE = new PotionCauldronProvider();
    private static final Identifier POTION_CAULDRON = Extension.asResource("potion_cauldron");
    @Override
    public Data streamData(BlockAccessor accessor) {
        PotionCauldronBlockEntity blockEntity = accessor.typedBlockEntity();
        return new Data(
                blockEntity.getPotion(),
                blockEntity.getLevel() != null ? blockEntity.getLevel().tickRateManager().tickrate() : 20.0F);
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, Data> streamCodec() {
        return Data.STREAM_CODEC;
    }

    @Override
    public Identifier getUid() {
        return POTION_CAULDRON;
    }

    public record Data(Holder<Potion> potion, float tickrate) {
        public static final StreamCodec<RegistryFriendlyByteBuf, Data> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.fromCodecWithRegistries(Potion.CODEC),
                Data::potion,
                ByteBufCodecs.FLOAT,
                Data::tickrate,
                Data::new
        );
    }

    public static class Client implements IBlockComponentProvider {
        public static Client INSTANCE = new Client();

        @Override
        public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
            Data data = PotionCauldronProvider.INSTANCE.decodeFromData(accessor).orElse(null);
            if (data == null || !data.potion.isBound()) {
                return;
            }
            Potion potion = data.potion.value();
            PotionContents.addPotionTooltip(potion.getEffects(), tooltip::add, 1.0F, data.tickrate);

        }

        @Override
        public Identifier getUid() {
            return POTION_CAULDRON;
        }
    }
}
