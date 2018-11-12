package com.vandendaelen.handles.object.tileentity;

import com.vandendaelen.handles.object.waypoint.Waypoint;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.common.systems.TardisSystems;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.SpaceTimeCoord;
import net.tardis.mod.util.common.helpers.TardisHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TileTardisInterfaceBase extends TileEntity {
    private UUID ownerID = null;
    public static final String peripheralName = "tardisinterface";
    public static final List<String> METHODS = Arrays.asList("getTardisPos", "setTardisPos","startFlight", "setDoors", "isInFlight","setFueling", "getFuel","isDoorsOpenned","canFly","getTravelTime","getWaypoint","setWaypoint","getHealthComponent","getDimensionsID","getDimensionName");

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
        List<Waypoint> waypoints = new ArrayList<>();
        for (SpaceTimeCoord coord: te.saveCoords) {
            waypoints.add(new Waypoint(coord.getPos(),coord.getDimension()));
        }
        return new Object[]{waypoints.toArray()};
    }

    public Object[] getWaypoint(Object[] arguments, TileEntityTardis te){
        if ((double)arguments[0] < te.saveCoords.size()){
            int id = (int)Math.round((double)arguments[0]);
            SpaceTimeCoord spaceTimeCoord = te.saveCoords.get(id);
            return new Object[]{spaceTimeCoord.getPos().getX(),spaceTimeCoord.getPos().getY(),spaceTimeCoord.getPos().getZ(),spaceTimeCoord.getDimension()};
        }
        return new Object[]{null};
    }

    public Object[] setWaypoint(Object[] arguments, TileEntityTardis te){
        if ((double)arguments[0]< te.saveCoords.size()){
            SpaceTimeCoord spaceTimeCoord = new SpaceTimeCoord(new BlockPos((double)arguments[1],(double)arguments[2],(double)arguments[3]),(int)Math.round((double)arguments[4]));
            te.saveCoords.set((int)Math.round((double)arguments[0]),spaceTimeCoord);
            return new Object[]{true};
        }
        return new Object[]{false};
    }

    public Object[] getHealthComponent(Object[] arguments, TileEntityTardis te){
        TardisSystems.BaseSystem[] systems = te.systems;
        if ((double)arguments[0] < systems.length){
            return new Object[]{Double.valueOf(systems[(int)Math.round((double)arguments[0])].getHealth())};
        }
        return new Object[]{null};
    }

    public Object[] getDimensionsID(){
        List<Object> ids = Arrays.asList((Object[])DimensionManager.getStaticDimensionIDs());
        return ids.toArray();
    }

    public Object[] getDimensionName(Object[] arguments){
        return new Object[]{DimensionManager.getProviderType((int)Math.round((double)arguments[0])).getName()};
    }

}
