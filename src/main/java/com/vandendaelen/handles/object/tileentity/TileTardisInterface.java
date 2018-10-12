package com.vandendaelen.handles.object.tileentity;

import com.vandendaelen.handles.utils.IHandlesPeripheral;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.TardisHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;


public class TileTardisInterface extends TileEntity implements IHandlesPeripheral {

    private UUID ownerID = null;

    @Nonnull
    @Override
    public String getType() {
        return "tardisInterface";
    }

    @Nonnull
    @Override
    public String[] getMethodNames() {
        return new String[]{"getTardisPos", "setTardisPos","startFlight", "setDoors", "isInFlight","setFueling", "getFuel"};
    }

    @Nullable
    @Override
    public Object[] callMethod(@Nonnull IComputerAccess computer, @Nonnull ILuaContext context, int method, @Nonnull Object[] arguments) throws LuaException, InterruptedException {
        switch (method){
            case 0:{
                TileEntityTardis te = getTardis();
                BlockPos tardisPos = te.getLocation();
                return new Object[]{tardisPos.getX(), tardisPos.getY(), tardisPos.getZ()};
            }
            case 1:{
                if (arguments.length < 4)
                    throw new LuaException("Not enough argument : setTardisPos(x,y,z,dimensionID)");
                if (arguments.length > 4)
                    throw new LuaException("Too many arguments : setTardisPos(x,y,z,dimensionID)");
                TileEntityTardis te = getTardis();
                Double x = (Double)arguments[0];
                Double y = (Double)arguments[1];
                Double z = (Double)arguments[2];
                Double dimID = (Double)arguments[3];

                BlockPos pos = new BlockPos(x,y,z);
                te.setDesination(pos,dimID.intValue());
                return new Object[0];
            }
            case 2:{
                if (arguments.length >= 1)
                    throw new LuaException("Too many arguments : startFlight()");
                TileEntityTardis te = getTardis();
                te.startFlight();
                return new Object[0];
            }
            case 3:{
                if (arguments.length < 1)
                    throw new LuaException("Not enough argument : setDoors(boolean)");
                if (arguments.length > 1)
                    throw new LuaException("Too many arguments : setDoors(boolean)");
                TileEntityTardis te = getTardis();
                te.getDoor().setOpen((boolean)arguments[0]);
                return new Object[0];
            }
            case 4:{
                if (arguments.length >= 1)
                    throw new LuaException("Too many arguments : isInFlight()");
                TileEntityTardis te = getTardis();
                return new Object[]{te.isInFlight()};
            }
            case 5:{
                if (arguments.length < 1)
                    throw new LuaException("Not enough argument : setFueling(boolean)");
                if (arguments.length > 1)
                    throw new LuaException("Too many arguments : setFueling(boolean)");
                TileEntityTardis te = getTardis();
                te.setFueling((boolean)arguments[0]);
                return new Object[0];
            }
            case 6:{
                if (arguments.length >= 1)
                    throw new LuaException("Too many arguments : getFuel()");
                TileEntityTardis te = getTardis();
                Double result = new Float(te.fuel).doubleValue();
                return new Object[]{result};
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

    public void setOwnerID(UUID ownerID) {
        this.ownerID = ownerID;
        markDirty();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasUniqueId("placer"))
            ownerID = compound.getUniqueId("placer");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (ownerID != null)
            compound.setUniqueId("placer",ownerID);
        return compound;
    }

    public TileEntityTardis getTardis() {
        BlockPos pos = TardisHelper.getTardis(ownerID);
        TileEntityTardis te = (TileEntityTardis) world.getTileEntity(pos);
        return te;
    }
}
