package com.vandendaelen.handles.object.tileentity;

import com.vandendaelen.handles.utils.HandlesMount;
import com.vandendaelen.handles.utils.IHandlesPeripheral;
import dan200.computercraft.api.peripheral.IComputerAccess;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nonnull;

public abstract class HandlesPeripheralBase extends TileEntity implements IHandlesPeripheral {

    public HandlesPeripheralBase() {
        super();
    }

    @Override
    public void attach(@Nonnull IComputerAccess computer) {
        computer.mount(HandlesMount.DIRECTORY, new HandlesMount(this));
    }

    @Override
    public void detach(@Nonnull IComputerAccess computer) {
        computer.mount(HandlesMount.DIRECTORY, new HandlesMount(this));
        computer.unmount(HandlesMount.DIRECTORY);
    }
}
