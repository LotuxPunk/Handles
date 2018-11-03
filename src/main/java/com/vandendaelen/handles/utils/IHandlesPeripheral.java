package com.vandendaelen.handles.utils;

import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IHandlesPeripheral extends IPeripheral {

    @Override
    default void attach(@Nonnull IComputerAccess computer){}

    @Override
    default void detach(@Nonnull IComputerAccess computer){}

    class Provider implements IPeripheralProvider {

        @Nullable
        @Override
        public IPeripheral getPeripheral(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull EnumFacing side) {
            TileEntity tile = world.getTileEntity(pos);
            return tile instanceof IHandlesPeripheral ? (IHandlesPeripheral) tile : null;
        }
    }
}
