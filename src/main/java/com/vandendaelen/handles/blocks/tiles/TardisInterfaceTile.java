package com.vandendaelen.handles.blocks.tiles;

import com.vandendaelen.handles.blocks.HandlesBlocks;
import com.vandendaelen.handles.exceptions.NotATardisException;
import com.vandendaelen.handles.misc.TardisInterfacePeripheral;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralTile;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.dimensions.TDimensions;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.tileentities.ConsoleTile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TardisInterfaceTile extends TileEntity implements IPeripheralTile {

    public TardisInterfaceTile() {
        super(HandlesBlocks.TARDISINTERFACE_TILE);
    }

    @Override
    public void read(CompoundNBT tag) {
        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        return super.write(tag);
    }

    private ConsoleTile getTardis() throws NotATardisException {
        if(this.getWorld().dimension.getType().equals(TDimensions.TARDIS)) throw new NotATardisException();
        return (ConsoleTile)this.getWorld().getTileEntity(TardisHelper.TARDIS_POS);
    }

    public Object[] getTardisLocation() throws NotATardisException {
        BlockPos pos = getTardis().getPos();
        return new Object[]{pos.getX(), pos.getY(), pos.getZ()};
    }

    @Nullable
    @Override
    public IPeripheral getPeripheral(@Nonnull Direction direction) {
        return new TardisInterfacePeripheral(this);
    }
}
