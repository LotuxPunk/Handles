package com.vandendaelen.handles.blocks.tiles;

import com.vandendaelen.handles.blocks.HandlesBlocks;
import com.vandendaelen.handles.exceptions.NotATardisException;
import com.vandendaelen.handles.helpers.DimensionHelper;
import com.vandendaelen.handles.misc.TardisInterfacePeripheral;
import com.vandendaelen.handles.tardis.subsystems.AprioritronSubsystem;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralTile;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.controls.RefuelerControl;
import net.tardis.mod.dimensions.TDimensions;
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
        ConsoleTile tardis = (ConsoleTile) world.loadedTileEntityList.stream().filter(tileEntity -> tileEntity instanceof ConsoleTile).findFirst().get();
        return tardis;
    }

    public void damageSubsystem() throws NotATardisException {
        this.getTardis().getSubsystem(AprioritronSubsystem.class).damage(null, 1);
    }

    public boolean canBeUsed() throws NotATardisException {
        return this.getTardis().getSubsystem(AprioritronSubsystem.class).canBeUsed();
    }

    public Object[] getTardisLocation() throws NotATardisException {
        BlockPos pos = getTardis().getLocation();
        return new Object[]{pos.getX(), pos.getY(), pos.getZ()};
    }

    public Object[] getTardisDestination() throws NotATardisException{
        BlockPos pos = getTardis().getDestination();
        return new Object[]{pos.getX(), pos.getY(), pos.getZ()};
    }

    public Object[] setTardisDestination(double x, double y, double z) throws NotATardisException{
        ConsoleTile tardis = getTardis();
        if (!tardis.isInFlight())
            tardis.setDestination(tardis.getDestinationDimension(), new BlockPos(x, y, z));
        return null;
    }

    public Object[] setTardisDimensionDestination(double id) throws NotATardisException {
        ConsoleTile tardis = getTardis();
        if (!tardis.isInFlight())
            tardis.setDestination(DimensionHelper.getDimension((int)id), tardis.getDestination());
        return null;
    }

    public Object[] startTardisFlight() throws NotATardisException {
        ConsoleTile tardis = getTardis();
        if (!tardis.isInFlight())
            tardis.takeoff();
        return null;
    }

    public Object[] setTardisRefuelMode(boolean status) throws NotATardisException {
        ConsoleTile tardis = getTardis();
        tardis.getControl(RefuelerControl.class).setRefuling(status);
        return null;
    }

    public Object[] getDimensions(){
        return DimensionHelper.getPrettyDimensionList().toArray();
    }

    @Nullable
    @Override
    public IPeripheral getPeripheral(@Nonnull Direction direction) {
        return new TardisInterfacePeripheral(this);
    }
}