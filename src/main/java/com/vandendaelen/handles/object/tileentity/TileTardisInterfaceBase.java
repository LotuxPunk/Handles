package com.vandendaelen.handles.object.tileentity;

import com.vandendaelen.handles.config.HandlesConfig;
import com.vandendaelen.handles.tardis.SystemAprioritron;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.SpaceTimeCoord;
import net.tardis.mod.util.common.helpers.TardisHelper;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TileTardisInterfaceBase extends TileEntity {
    private UUID ownerID = null;
    public static final String peripheralName = "tardisinterface";
    public static final List<String> METHODS = Arrays.asList("getTardisPos", "setTardisDestination","startFlight", "setDoors", "isInFlight","setFueling", "getArtron","isDoorsOpened","canFly","getTravelTime", "getWaypoint","setWaypoint","getHealthComponent","getDimensionsID","getDimensionName","setRelativePos", "setDimensionPos", "getTardisDestination", "getDimension", "getTargetDimension", "setField", "getField", "getSystemName", "setStealth", "getStealh", "setHADS", "getHADS");

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

    public TileEntityDoor getTardisDoor(TileEntityTardis tardis){
        if(!world.isRemote){
            WorldServer ser = world.getMinecraftServer().getWorld(tardis.dimension);
            if(ser != null){
                TileEntity te = ser.getTileEntity(tardis.getLocation());
                if (te instanceof TileEntityDoor){
                    return (TileEntityDoor) te;
                }
            }
        }
        return null;
    }

    public void damageAprioritron(){
        getTardis().getSystem(SystemAprioritron.class).damage();
    }
    /**
     * @return boolean if the Tardis link can run (Subsystem or not)
     */
    protected boolean canRun(){
        if (HandlesConfig.GAMEPLAY.subsystem){
            if (getTardis().getSystem(SystemAprioritron.class).getHealth() <= 0.0F) {
                return false;
            }
            damageAprioritron();
        }
        return true;
    }


    public Object[] getTardisPos(TileEntityTardis te) {
        BlockPos tardisPos = te.getLocation();
        return new Object[]{tardisPos.getX(), tardisPos.getY(), tardisPos.getZ(), te.dimension};
    }

    public Object[] setTardisDestination(Object[] arguments, TileEntityTardis te){
        if (!canRun())
            return new Object[]{"Aprioritron broken"};
        Double x = (Double)arguments[0];
        Double y = (Double)arguments[1];
        Double z = (Double)arguments[2];
        Double dimID;
        if (arguments.length > 3)
            dimID = (Double)arguments[3];
        else
            dimID = (double)te.dimension;

        BlockPos pos = new BlockPos(x,y,z);
        te.setDesination(pos,dimID.intValue());
        return new Object[0];
    }

    public Object[] getTardisDestination(TileEntityTardis te){
        BlockPos destination = te.getDestination();
        int dimension = te.getTargetDim();
        return new Object[]{destination.getX(), destination.getY(), destination.getZ(), dimension};
    }

    public Object[] startFlight(TileEntityTardis te){
        if (!canRun())
            return new Object[]{"Aprioritron broken"};
        te.startFlight();
        return new Object[0];
    }

   public Object[] setDoors(Object[] arguments, TileEntityTardis te){
       if (!canRun())
           return new Object[]{"Aprioritron broken"};
        te.getDoor().setOpen((boolean)arguments[0]);
        te.getDoor().setOtherDoors((boolean)arguments[0]);
        return new Object[0];
    }

    public Object[] isInFlight(TileEntityTardis te){
        return new Object[]{te.isInFlight()};
    }

    public Object[] getFuel(TileEntityTardis te){
        return new Object[]{Float.valueOf(te.getArtron()).doubleValue()};
    }

    public Object[] setFueling(Object[] arguments, TileEntityTardis te){
        if (!canRun())
            return new Object[]{"Aprioritron broken"};
        te.setFueling((boolean)arguments[0]);
        return new Object[0];
    }

    public Object[] isDoorsOpened(TileEntityTardis te){
        return new Object[]{te.getDoor().isOpen()};
    }

    public Object[] canFly(TileEntityTardis te){
        return new Object[]{te.getCanFly()};
    }

    public Object[] getTravelTime(TileEntityTardis te){
        return new Object[]{te.getTimeLeft() / 20};
    }

    public Object[] getWaypoint(Object[] arguments, TileEntityTardis te){
        if ((double)arguments[0] < te.saveCoords.size()){
            int id = (int)Math.round((double)arguments[0]);
            SpaceTimeCoord spaceTimeCoord = te.saveCoords.get(id);
            return new Object[]{spaceTimeCoord.getPos().getX(),spaceTimeCoord.getPos().getY(),spaceTimeCoord.getPos().getZ(),spaceTimeCoord.getDimension(), spaceTimeCoord.name};
        }
        return new Object[]{0};
    }

    public Object[] setWaypoint(Object[] arguments, TileEntityTardis te){
        if (!canRun())
            return new Object[]{"Aprioritron broken"};
        if ((double)arguments[0]< te.saveCoords.size()){
            SpaceTimeCoord spaceTimeCoord = new SpaceTimeCoord(new BlockPos((double)arguments[1],(double)arguments[2],(double)arguments[3]),(int)Math.round((double)arguments[4]), (String)arguments[5]);
            te.saveCoords.set((int)Math.round((double)arguments[0]),spaceTimeCoord);
            return new Object[]{true};
        }
        return new Object[]{false};
    }

    public Object[] getHealthComponent(Object[] arguments, TileEntityTardis te){
        if ((double)arguments[0] < te.systems.length){
            return new Object[]{Double.valueOf(te.systems[(int)Math.round((double)arguments[0])].getHealth())};
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

    public Object[] setRelativePos(Object[] arguments, TileEntityTardis te){
        if (!canRun())
            return new Object[]{"Aprioritron broken"};
        BlockPos pos = te.getDestination();
        BlockPos relativePos = new BlockPos(pos.getX()+(double)arguments[0],pos.getY()+(double)arguments[1],pos.getZ()+(double)arguments[2]);
        te.setDesination(relativePos,te.getTargetDim());
        return new Object[] {null};
    }

    public Object[] setDimensionPos(Object[] arguments, TileEntityTardis te){
        if (!canRun())
            return new Object[]{"Aprioritron broken"};
        te.setDesination(te.getDestination(),(int)Math.round((double)arguments[0]));
        return new Object[] {null};
    }

    public Object[] getDimension(TileEntityTardis te){
        return new Object[]{te.dimension};
    }

    public Object[] getTargetDimension(TileEntityTardis te){
        return new Object[]{te.getTargetDim()};
    }

    public Object[] setField(Object[] arguments, TileEntityTardis te){
        if (!canRun())
            return new Object[]{"Aprioritron broken"};
        te.setForceFieldEnabled((boolean)arguments[0]);
        TileEntityDoor td = getTardisDoor(te);
        if(td != null){
            td.setForcefield((boolean)arguments[0]);
        }
        return new Object[] {null};
    }

    public Object[] getField(TileEntityTardis te){
        return new Object[] {te.isForceFieldEnabled()};
    }

    public Object[] getSystemName(Object[] arguments, TileEntityTardis te){
        if ((double)arguments[0] < te.systems.length){
            return new Object[]{te.systems[(int)Math.round((double)arguments[0])].getNameKey()};
        }
        return new Object[]{null};
    }

    public Object[] setStealth(Object[] arguments, TileEntityTardis te){
        if (!canRun())
            return new Object[]{"Aprioritron broken"};
        te.setStealthMode((boolean)arguments[0]);
        return new Object[] {null};
    }

    public Object[] getStealth(TileEntityTardis te){
        return new Object[] {te.isStealthMode()};
    }

    public Object[] setHADS(Object[] arguments, TileEntityTardis te){
        if (!canRun())
            return new Object[]{"Aprioritron broken"};
        te.setHADS((boolean)arguments[0]);
        return new Object[] {null};
    }

    public Object[] getHADS(TileEntityTardis te){
        return new Object[] {te.isHADSEnabled()};
    }
}
