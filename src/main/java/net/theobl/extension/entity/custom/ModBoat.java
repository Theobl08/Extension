package net.theobl.extension.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.extensions.IForgeBoat;
import net.minecraftforge.fluids.FluidType;
import net.theobl.extension.block.ModBlocks;
import net.theobl.extension.entity.ModEntityType;
import net.theobl.extension.item.ModItems;

import java.util.Arrays;

public class ModBoat extends Boat implements IForgeBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(ModBoat.class, EntityDataSerializers.INT);

    public ModBoat(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ModBoat(Level level, double pX, double pY, double pZ) {
        this(ModEntityType.MOD_BOAT.get(), level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        switch (getModVariant()) {
            case CRIMSON -> {
                return ModItems.CRIMSON_BOAT.get();
            }
            case WARPED -> {
                return ModItems.WARPED_BOAT.get();
            }
            case WHITE -> {
                return ModItems.COLORED_BOATS.get(0).get();
            }
            case LIGHT_GRAY -> {
                return ModItems.COLORED_BOATS.get(1).get();
            }
            case GRAY -> {
                return ModItems.COLORED_BOATS.get(2).get();
            }
            case BLACK -> {
                return ModItems.COLORED_BOATS.get(3).get();
            }
            case BROWN -> {
                return ModItems.COLORED_BOATS.get(4).get();
            }
            case RED -> {
                return ModItems.COLORED_BOATS.get(5).get();
            }
            case ORANGE -> {
                return ModItems.COLORED_BOATS.get(6).get();
            }
            case YELLOW -> {
                return ModItems.COLORED_BOATS.get(7).get();
            }
            case LIME -> {
                return ModItems.COLORED_BOATS.get(8).get();
            }
            case GREEN -> {
                return ModItems.COLORED_BOATS.get(9).get();
            }
            case CYAN -> {
                return ModItems.COLORED_BOATS.get(10).get();
            }
            case LIGHT_BLUE -> {
                return ModItems.COLORED_BOATS.get(11).get();
            }
            case BLUE -> {
                return ModItems.COLORED_BOATS.get(12).get();
            }
            case PURPLE -> {
                return ModItems.COLORED_BOATS.get(13).get();
            }
            case MAGENTA -> {
                return ModItems.COLORED_BOATS.get(14).get();
            }
            case PINK -> {
                return ModItems.COLORED_BOATS.get(15).get();
            }
        };
        return super.getDropItem();
    }

    public void setVariant(Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    public Type getModVariant() {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, Type.CRIMSON.ordinal());
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getModVariant().getName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type",8)) {
            this.setVariant(Type.byName(pCompound.getString("Type")));
        }
    }

    public static enum Type {
        WHITE(ModBlocks.COLORED_PLANKS.get(0).get(), "white"),
        LIGHT_GRAY(ModBlocks.COLORED_PLANKS.get(1).get(), "light_gray"),
        GRAY(ModBlocks.COLORED_PLANKS.get(2).get(), "gray"),
        BLACK(ModBlocks.COLORED_PLANKS.get(3).get(), "black"),
        BROWN(ModBlocks.COLORED_PLANKS.get(4).get(), "brown"),
        RED(ModBlocks.COLORED_PLANKS.get(5).get(), "red"),
        ORANGE(ModBlocks.COLORED_PLANKS.get(6).get(), "orange"),
        YELLOW(ModBlocks.COLORED_PLANKS.get(7).get(), "yellow"),
        LIME(ModBlocks.COLORED_PLANKS.get(8).get(), "lime"),
        GREEN(ModBlocks.COLORED_PLANKS.get(9).get(), "green"),
        CYAN(ModBlocks.COLORED_PLANKS.get(10).get(), "cyan"),
        LIGHT_BLUE(ModBlocks.COLORED_PLANKS.get(11).get(), "light_blue"),
        BLUE(ModBlocks.COLORED_PLANKS.get(12).get(), "blue"),
        PURPLE(ModBlocks.COLORED_PLANKS.get(13).get(), "purple"),
        MAGENTA(ModBlocks.COLORED_PLANKS.get(14).get(), "magenta"),
        PINK(ModBlocks.COLORED_PLANKS.get(15).get(), "pink"),
        CRIMSON(Blocks.CRIMSON_PLANKS, "crimson"),
        WARPED(Blocks.WARPED_PLANKS, "warped");


        private final String name;
        private final Block planks;
        /*public static final StringRepresentable.EnumCodec<ModBoatEntity.Type> CODEC = StringRepresentable.fromEnum(ModBoatEntity.Type::values);
        private static final IntFunction<ModBoatEntity.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);*/

        private Type(Block pPlanks, String pName) {
            this.name = pName;
            this.planks = pPlanks;
        }

//        public String getSerializedName() {
//            return this.name;
//        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        public String toString() {
            return this.name;
        }

        /**
         * Get a boat type by its enum ordinal
         */
        public static Type byId(int pId) {
//            return BY_ID.apply(pId);
            Type[] Type = values();
            if (pId < 0 || pId >= Type.length) {
                return Type[0];
            }
            else {
                return Type[pId];
            }
        }

        public static Type byName(String pName) {
//            return CODEC.byName(pName, CRIMSON);
            Type[] type = values();
            return Arrays.stream(type).filter(t ->
                    t.getName().equals(pName)).findFirst().orElse(type[0]);
        }
    }

    @Override
    public boolean fireImmune() {
        return this.isNetherWood(this.getModVariant());
    }

    @Override
    public boolean canBoatInFluid(FluidState state) {
        return state.supportsBoating(this) ||
                (state.getFluidType().equals(Fluids.LAVA.getFluidType()) && this.isNetherWood(this.getModVariant()));
    }

    @Override
    public boolean canBoatInFluid(FluidType type) {
        return type.supportsBoating(this) ||
                (type.equals(Fluids.LAVA.getFluidType()) && this.isNetherWood(this.getModVariant()));
    }

    public boolean isNetherWood(ModBoat.Type boatType){
        return ((FireBlock) Blocks.FIRE).getBurnOdds(boatType.getPlanks().defaultBlockState()) == 0;
    }
}
