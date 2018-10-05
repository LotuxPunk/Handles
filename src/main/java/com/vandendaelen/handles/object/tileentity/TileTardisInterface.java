package com.vandendaelen.handles.object.tileentity;

import com.vandendaelen.handles.object.block.TardisInterface;
import com.vandendaelen.handles.utils.IHandlesPeripheral;
import com.vandendaelen.handles.utils.Reference;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.TardisHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.UUID;


public class TileTardisInterface extends TileEntity implements IHandlesPeripheral {

    private World world;
    private String ownerID;
    private HashMap<IComputerAccess,Boolean> computers = new HashMap<IComputerAccess,Boolean>();

    public TileTardisInterface(World world) {
        super();
        this.world = world;
    }

    @Nonnull
    @Override
    public String getType() {
        return "tardisInterface";
    }

    @Nonnull
    @Override
    public String[] getMethodNames() {
        return new String[]{"getTardisPos"};
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
            default:{
                return new Object[0];
            }
        }
    }

    @Override
    public boolean equals(@Nullable IPeripheral other){
        return this == other;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setString("placer",ownerID);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        ownerID = compound.getString("placer");
    }

    public TileEntityTardis getTardis(){
        BlockPos pos = TardisHelper.getTardis(UUID.fromString(ownerID));
        TileEntityTardis te = (TileEntityTardis) world.getTileEntity(pos);
        return te;
    }
}
