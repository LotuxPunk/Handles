package com.vandendaelen.handles.blocks.tiles;

import com.vandendaelen.handles.blocks.HandlesBlocks;
import com.vandendaelen.handles.exceptions.NoSubsystemException;
import com.vandendaelen.handles.exceptions.NotATardisException;
import com.vandendaelen.handles.functions.FunctionsHandler;
import com.vandendaelen.handles.misc.TardisInterfacePeripheral;
import com.vandendaelen.handles.tardis.subsystems.AprioritronSubsystem;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralTile;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
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

    @Nullable
    @Override
    public IPeripheral getPeripheral(@Nonnull Direction direction) {
        try {
            return new TardisInterfacePeripheral(this, new FunctionsHandler(getTardis()));
        } catch (NotATardisException e) {
            e.printStackTrace();
            return new TardisInterfacePeripheral(this, null);
        }
    }

    public ConsoleTile getTardis() throws NotATardisException {
        if(this.getWorld().dimension.getType().equals(TDimensions.TARDIS)) throw new NotATardisException();
        ConsoleTile tardis = (ConsoleTile) world.loadedTileEntityList.stream().filter(tileEntity -> tileEntity instanceof ConsoleTile).findFirst().get();
        return tardis;
    }

    public void damageSubsystem() throws NotATardisException {
        this.getTardis().getSubsystem(AprioritronSubsystem.class).ifPresent(aprioritronSubsystem -> aprioritronSubsystem.damage(null, 1));
    }

    public boolean canBeUsed() throws NotATardisException, NoSubsystemException {
        return this.getTardis().getSubsystem(AprioritronSubsystem.class).orElseThrow(NoSubsystemException::new).canBeUsed();
    }
}
