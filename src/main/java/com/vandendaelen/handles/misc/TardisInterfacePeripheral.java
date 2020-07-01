package com.vandendaelen.handles.misc;

import com.vandendaelen.handles.blocks.tiles.TardisInterfaceTile;
import com.vandendaelen.handles.exceptions.NoSubsystemException;
import com.vandendaelen.handles.exceptions.NotATardisException;
import com.vandendaelen.handles.helpers.DimensionHelper;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.util.Direction;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.controls.RefuelerControl;
import net.tardis.mod.controls.StabilizerControl;
import net.tardis.mod.controls.ThrottleControl;
import net.tardis.mod.enums.EnumDoorState;
import net.tardis.mod.tileentities.ConsoleTile;

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
                "getArtronBank",
                "getDimension",
                "getDimensions",
                "getDestination",
                "getDoors",
                "getFacing",
                "getLocation",
                "getTimeLeft",
                "setDestinationAndDimension",
                "setDimension",
                "setDestination",
                "setRefuel",
                "setDoors",
                "setFacing",
                "setFlight"
        };
    }

    @Nullable
    @Override
    public Object[] callMethod(@Nonnull IComputerAccess iComputerAccess, @Nonnull ILuaContext iLuaContext, int method, @Nonnull Object[] objects) throws LuaException, InterruptedException {
        ConsoleTile tardis;
        try {
            tardis = tile.getTardis();
            if (tile.canBeUsed()) {
                tile.damageSubsystem();
            }
        } catch (NotATardisException e) {
            throw new LuaException("TARDIS not found");
        } catch (NoSubsystemException e) {
            throw new LuaException("Aprioritron not found");
        }

        switch (method){
            case 0:
                return new Double[]{(double) tardis.getArtron()};
            case 1:
                return new Integer[]{DimensionHelper.getDimensionId(tardis.getDimension())};
            case 2:
                return DimensionHelper.getPrettyDimensionList().toArray();
            case 3:
                BlockPos destination = tardis.getDestination();
                return new Integer[]{destination.getX(), destination.getY(), destination.getZ()};
            case 4:
                return new String[]{tardis.getDoor().orElseThrow(LuaException::new).getOpenState().name()};
            case 5:
                return new String[]{tardis.getDirection().name()};
            case 6:
                BlockPos location = tardis.getLocation();
                return new Integer[]{location.getX(), location.getY(), location.getZ()};
            case 7:
                return new Integer[]{tardis.getTimeLeft()};
            case 8:
                if (!tardis.isInFlight())
                    tardis.setDestination(DimensionHelper.getDimension(((Double)objects[0]).intValue()), new BlockPos((double)objects[1], (double)objects[2], (double)objects[3]));
                BlockPos tardisDestination = tardis.getDestination();
                return new Integer[]{DimensionHelper.getDimensionId(tardis.getDimension()), tardisDestination.getX(), tardisDestination.getY(), tardisDestination.getZ()};
            case 9:
                if (!tardis.isInFlight())
                    tardis.setDestination(DimensionHelper.getDimension(((Double)objects[0]).intValue()), tardis.getDestination());
                return new Integer[]{DimensionHelper.getDimensionId(tardis.getDestinationDimension())};
            case 10:
                if (!tardis.isInFlight())
                    tardis.setDestination(tardis.getDestinationDimension(), new BlockPos((double)objects[0], (double)objects[1], (double)objects[2]));
                BlockPos tardisDestination1 = tardis.getDestination();
                return new Integer[]{tardisDestination1.getX(), tardisDestination1.getY(), tardisDestination1.getZ()};
            case 11:
                boolean status = (boolean)objects[0];
                tardis.getControl(RefuelerControl.class).setRefuling(status);
                return new Boolean[]{tardis.getControl(RefuelerControl.class).isRefueling()};
            case 12:
                String doorsStatus = (String)objects[0];
                tardis.getWorld().getServer().enqueue(new TickDelayedTask(1, ()->{
                    tardis.getDoor().ifPresent(doorEntity -> {
                        doorEntity.setOpenState(EnumDoorState.valueOf(doorsStatus));
                        doorEntity.openOther();
                    });
                }));
                return null;
            case 13:
                String facing = (String)objects[0];
                tardis.setDirection(Direction.valueOf(facing));
                return new String[]{tardis.getDirection().name()};
            case 14:
                double speed = (double)objects[0];
                if(speed > 1.0D){
                    speed = 1.0D;
                }

                StabilizerControl stabilizer = tardis.getControl(StabilizerControl.class);
                ThrottleControl throttle = tardis.getControl(ThrottleControl.class);

                if (stabilizer != null && throttle != null && speed > 0){
                    stabilizer.setStabilized(true);
                    throttle.setAmount((float)speed);
                    tardis.getWorld().getServer().enqueue(new TickDelayedTask(1, tardis::takeoff));
                    return new Double[]{(double) throttle.getAmount()};
                }
                return null;
            default:
                throw new IllegalStateException("Method index out of range!");
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
