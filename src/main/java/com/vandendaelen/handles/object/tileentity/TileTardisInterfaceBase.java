package com.vandendaelen.handles.object.tileentity;

import com.vandendaelen.handles.object.waypoint.Waypoint;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.SpaceTimeCoord;
import net.tardis.mod.util.helpers.TardisHelper;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TileTardisInterfaceBase extends TileEntity {
    private UUID ownerID = null;
    public static final String peripheralName = "tardisinterface";
    public static final List<String> METHODS = Arrays.asList("getTardisPos", "setTardisPos","startFlight", "setDoors", "isInFlight","setFueling", "getFuel","isDoorsOpenned","canFly","getTravelTime","getWaypoints","setWaypoint");

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

    public UUID getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(UUID ownerID) {
        this.ownerID = ownerID;
        markDirty();
    }

    public TileEntityTardis getTardis() {
        BlockPos pos = TardisHelper.getTardis(ownerID);
        TileEntityTardis te = (TileEntityTardis) world.getTileEntity(pos);
        return te;
    }

    public Object[] getTardisPos(TileEntityTardis te) {
        BlockPos tardisPos = te.getLocation();
        return new Object[]{tardisPos.getX(), tardisPos.getY(), tardisPos.getZ()};
    }

    public Object[] setTardisPos(Object[] arguments, TileEntityTardis te){
        Double x = (Double)arguments[0];
        Double y = (Double)arguments[1];
        Double z = (Double)arguments[2];
        Double dimID = (Double)arguments[3];

        BlockPos pos = new BlockPos(x,y,z);
        te.setDesination(pos,dimID.intValue());
        return new Object[0];
    }

    public Object[] startFlight(TileEntityTardis te){
        te.startFlight();
        return new Object[0];
    }

   public Object[] setDoors(Object[] arguments, TileEntityTardis te){
        te.getDoor().setOpen((boolean)arguments[0]);
        return new Object[0];
    }

    public Object[] isInFlight(TileEntityTardis te){
        return new Object[]{te.isInFlight()};
    }

    public Object[] getFuel(TileEntityTardis te){
        Double result = new Float(te.fuel).doubleValue();
        return new Object[]{result};
    }

    public Object[] setFueling(Object[] arguments, TileEntityTardis te){
        te.setFueling((boolean)arguments[0]);
        return new Object[0];
    }

    public Object[] isDoorsOpenned(TileEntityTardis te){
        return new Object[]{te.getDoor().isOpen()};
    }

    public Object[] canFly(TileEntityTardis te){
        return new Object[]{te.getCanFly()};
    }

    public Object[] getTravelTime(TileEntityTardis te){
        int timeLeftInSeconds = te.getTimeLeft() / 20;
        return new Object[]{timeLeftInSeconds};
    }

    public Object[] getWaypoints(TileEntityTardis te){
        List<Waypoint> waypoints = Arrays.asList();
        int i = 0;
        for (SpaceTimeCoord coord: te.saveCoords) {
            waypoints.add(new Waypoint(coord.getPos(),coord.getDimension()));
        }

        return waypoints.toArray();
    }

    public Object[] setWaypoint(Object[] arguments, TileEntityTardis te){
        if ((int)arguments[0]> te.saveCoords.size()){
            te.saveCoords.add((int)arguments[0],new SpaceTimeCoord(new BlockPos((double)arguments[1],(double)arguments[2],(double)arguments[3]),(int)arguments[4]));
            return new Object[]{true};
        }
        return new Object[]{false};
    }


}
