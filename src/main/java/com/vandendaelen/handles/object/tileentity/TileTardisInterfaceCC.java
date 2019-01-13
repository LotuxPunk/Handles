package com.vandendaelen.handles.object.tileentity;

import com.vandendaelen.handles.utils.IHandlesPeripheral;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.tardis.mod.common.tileentity.TileEntityTardis;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileTardisInterfaceCC extends TileTardisInterfaceBase implements IHandlesPeripheral {

    @Nonnull
    @Override
    public String getType() {
        return peripheralName;
    }

    @Nonnull
    @Override
    public String[] getMethodNames() {
        return METHODS.toArray(new String[0]);
    }

    @Nullable
    @Override
    public Object[] callMethod(@Nonnull IComputerAccess computer, @Nonnull ILuaContext context, int method, @Nonnull Object[] arguments) throws LuaException, InterruptedException {
        TileEntityTardis te = getTardis();
        switch (method){
            case 0:{
                if (arguments.length >= 1)
                    throw new LuaException("Too many arguments : getTardisPos()");
                return getTardisPos(te);
            }
            case 1:{
                if (arguments.length < 3)
                    throw new LuaException("Not enough argument : setTardisDestination(x,y,z,[dimensionID])");
                if (arguments.length > 4)
                    throw new LuaException("Too many arguments : setTardisDestination(x,y,z,[dimensionID])");
                return setTardisDestination(arguments,te);
            }
            case 2:{
                if (arguments.length >= 1)
                    throw new LuaException("Too many arguments : startFlight()");
                return startFlight(te);
            }
            case 3:{
                if (arguments.length < 1)
                    throw new LuaException("Not enough argument : setDoors(boolean)");
                if (arguments.length > 1)
                    throw new LuaException("Too many arguments : setDoors(boolean)");
                return setDoors(arguments,te);
            }
            case 4:{
                if (arguments.length >= 1)
                    throw new LuaException("Too many arguments : isInFlight()");
                return isInFlight(te);
            }
            case 5:{
                if (arguments.length < 1)
                    throw new LuaException("Not enough argument : setFueling(boolean)");
                if (arguments.length > 1)
                    throw new LuaException("Too many arguments : setFueling(boolean)");
                return setFueling(arguments,te);
            }
            case 6:{
                if (arguments.length >= 1)
                    throw new LuaException("Too many arguments : getFuel()");
                return getFuel(te);
            }
            case 7:{
                if (arguments.length >= 1)
                    throw new LuaException("Too many arguments : isDoorsOpened()");
                return isDoorsOpened(te);
            }
            case 8:{
                if (arguments.length >= 1)
                    throw new LuaException("Too many arguments : canFly()");
                return canFly(te);
            }
            case 9:{
                if (arguments.length >= 1)
                    throw new LuaException("Too many arguments : getTravelTime()");
                return getTravelTime(te);
            }
            case 10 :{
                if (arguments.length < 1)
                    throw new LuaException("Not enough argument : getWaypoint(id)");
                if (arguments.length > 1)
                    throw new LuaException("Too many arguments : getWaypoint(id)");
                return getWaypoint(arguments,te);
            }
            case 11:{
                if (arguments.length < 6)
                    throw new LuaException("Not enough argument : setWaypoint(id,x,y,z,dimensionID, name)");
                if (arguments.length > 6)
                    throw new LuaException("Too many arguments : setWaypoint(id,x,y,z,dimensionID, name)");
                return setWaypoint(arguments,te);
            }
            case 12:{
                if (arguments.length < 1)
                    throw new LuaException("Not enough argument : getHealthComponent(id)");
                if (arguments.length > 1)
                    throw new LuaException("Too many arguments : getHealthComponent(id)");
                return getHealthComponent(arguments,te);
            }
            case 13:{
                if (arguments.length >= 1)
                    throw new LuaException("Too many arguments : getDimensionsID()");
                return getDimensionsID();
            }
            case 14:{
                if (arguments.length < 1)
                    throw new LuaException("Not enough argument : getDimensionName(dimensionID)");
                if (arguments.length > 1)
                    throw new LuaException("Too many arguments : getDimensionName(dimensionID)");
                return getDimensionName(arguments);
            }
            case 15:{ //setRelativePos(x,y,z)
                if (arguments.length < 3)
                    throw new LuaException("Not enough argument : setRelativePos(x,y,z)");
                if (arguments.length > 3)
                    throw new LuaException("Too many arguments : setRelativePos(x,y,z)");
                return setRelativePos(arguments,te);
            }
            case 16:{//setDimensionPos(dimensionID)
                if (arguments.length < 1)
                    throw new LuaException("Not enough argument : setDimensionPos(dimensionID)");
                if (arguments.length > 1)
                    throw new LuaException("Too many arguments : setDimensionPos(dimensionID)");
                return setDimensionPos(arguments,te);
            }
            case 17:{ //getTardisDestination
                if (arguments.length >= 1)
                    throw new LuaException("Too many arguments : getTardisDestination()");
                return getTardisDestination(te);
            }
            case 18:{ //getDimension
                if (arguments.length >= 1)
                    throw new LuaException("Too many arguments : getDimension()");
                return getDimension(te);
            }
            case 19:{ //getTargetDim
                if (arguments.length >= 1)
                    throw new LuaException("Too many arguments : getTargetDimension()");
                return getTargetDimension(te);
            }
            case 20:{ //setRepairing
                if (arguments.length < 1)
                    throw new LuaException("Not enough argument : setRepairing(boolean)");
                if (arguments.length > 1)
                    throw new LuaException("Too many arguments : setRepairing(boolean)");
                return setRepairing(arguments,te);
            }
            case 21:{//getHull
                if (arguments.length >= 1)
                    throw new LuaException("Too many arguments : getHull()");
                return getHull(te);
            }
            default:{
                return new Object[0];
            }
        }
    }

    @Override
    public boolean equals(@Nullable IPeripheral other){
        return super.equals(other);
    }
}
