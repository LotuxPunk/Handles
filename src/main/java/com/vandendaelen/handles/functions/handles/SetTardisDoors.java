package com.vandendaelen.handles.functions.handles;

import java.text.MessageFormat;
import java.util.Arrays;

import com.vandendaelen.handles.functions.IFunction;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.tardis.mod.enums.EnumDoorState;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetTardisDoors implements IFunction {
    @Override
    public String getName() {
        return "setDoors";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return true;
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        EnumDoorState status;
        try{
            status = EnumDoorState.valueOf(args.getString(0));
            tardis.getWorld().getServer().enqueue(new TickDelayedTask(1, ()->{
                tardis.getDoor().ifPresent(doorEntity -> {
                    doorEntity.setOpenState(status);
                    doorEntity.openAndUpdateExteriorDoor();
                });
            }));
        }
        catch (Exception e){
            throw new LuaException(MessageFormat.format("Value \"{0}\" doesn't exist. Accepted values [{1}]", args.getString(0), Arrays.stream(EnumDoorState.values()).toArray()));
        }

        return MethodResult.of();
    }
}
