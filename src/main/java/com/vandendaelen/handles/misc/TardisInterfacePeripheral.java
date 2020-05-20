package com.vandendaelen.handles.misc;

import com.vandendaelen.handles.blocks.tiles.TardisInterfaceTile;
import com.vandendaelen.handles.exceptions.NoSubsystemException;
import com.vandendaelen.handles.exceptions.NotATardisException;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TardisInterfacePeripheral implements IPeripheral {

    private final TardisInterfaceTile tile;

    public TardisInterfacePeripheral(TardisInterfaceTile tile) {
        this.tile = tile;
    }

    @Nonnull
    @Override
    public String getType() {
        return "tardisinterface";
    }

    @Nonnull
    @Override
    public String[] getMethodNames() {
        return new String[]{
                "getLocation",
                "getDestination",
                "setDestination",
                "setDimension",
                "getDimensions",
                "startFlight",
                "setRefuel",
                "setDestinationAndDimension",
                "getDimension"
        };
    }

    @Nullable
    @Override
    public Object[] callMethod(@Nonnull IComputerAccess iComputerAccess, @Nonnull ILuaContext iLuaContext, int method, @Nonnull Object[] objects) throws LuaException, InterruptedException {
        try{
            if (tile.canBeUsed()){
                tile.damageSubsystem();
                switch (method){
                    case 0://getLocation
                        return tile.getTardisLocation();
                    case 1://getDestination
                        return tile.getTardisDestination();
                    case 2://setDestination
                        return tile.setTardisDestination((double)objects[0], (double)objects[1], (double)objects[2]);
                    case 3://setDimension
                        return tile.setTardisDimensionDestination((double)objects[0]);
                    case 4://getDimensions
                        return tile.getDimensions();
                    case 5://startFlight
                        return tile.startTardisFlight((double)objects[0]);
                    case 6://setRefuel
                        return tile.setTardisRefuelMode((boolean)objects[0]);
                    case 7://setDestinationAndDimension
                        return tile.setTardisDestinationAndDimension((double)objects[0], (double)objects[1], (double)objects[2], (double)objects[3]);
                    case 8://getDimension
                        return tile.getDimension();
                    default:
                        return null;
                }
            }
            return null;
        }
        catch (NotATardisException | NoSubsystemException e) {
            return null;
        }
    }

    @Nonnull
    @Override
    public Object getTarget() {
        return tile;
    }

    @Override
    public boolean equals(@Nullable IPeripheral other) {
        return this == other || other instanceof TardisInterfacePeripheral && ((TardisInterfacePeripheral) other).tile == tile;
    }
}
