package com.vandendaelen.handles.blocks.tiles;

import com.vandendaelen.handles.blocks.HandlesBlocks;
import com.vandendaelen.handles.misc.TardisInterfacePeripheral;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralTile;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.tileentities.ConsoleTile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TardisInterfaceTile extends TileEntity implements IPeripheralTile {
    private ResourceLocation dimension;

    public TardisInterfaceTile() {
        super(HandlesBlocks.TARDISINTERFACE_TILE);
    }

    @Override
    public void read(CompoundNBT tag) {
        dimension = new ResourceLocation(tag.getString("dimension"));
        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        tag.putString("dimension", dimension.toString());
        return super.write(tag);
    }

    public void setTileDimension(ResourceLocation dimension) {
        this.dimension = dimension;
        markDirty();
    }

    private ConsoleTile getTardis(){
        return TardisHelper.getConsole(DimensionType.byName(dimension));
    }

    public Object[] getTardisLocation(){
        return new Object[]{getTardis().getPos().getX(), getTardis().getPos().getY(), getTardis().getPos().getZ()};
    }

    @Nullable
    @Override
    public IPeripheral getPeripheral(@Nonnull Direction direction) {
        return new TardisInterfacePeripheral(this);
    }
}
