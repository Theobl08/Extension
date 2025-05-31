package net.theobl.extension.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidType;
import net.theobl.extension.entity.ModEntityType;
import net.theobl.extension.item.ModItems;

public class ModChestBoat extends ChestBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(ModChestBoat.class, EntityDataSerializers.INT);

    public ModChestBoat(EntityType<? extends ChestBoat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ModChestBoat(Level level, double pX, double pY, double pZ) {
        this(ModEntityType.MOD_CHEST_BOAT.get(), level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        switch (getModVariant()) {
            case CRIMSON -> {
                return ModItems.CRIMSON_CHEST_BOAT.get();
            }
            case WARPED -> {
                return ModItems.WARPED_CHEST_BOAT.get();
            }
            case WHITE -> {
                return ModItems.COLORED_CHEST_BOATS.get(0).get();
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

    public void setVariant(ModBoat.Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, ModBoat.Type.CRIMSON.ordinal());
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getModVariant().getName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type",8)) {
            this.setVariant(ModBoat.Type.byName(pCompound.getString("Type")));
        }
    }

    public ModBoat.Type getModVariant() {
        return ModBoat.Type.byId(this.entityData.get(DATA_ID_TYPE));
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
