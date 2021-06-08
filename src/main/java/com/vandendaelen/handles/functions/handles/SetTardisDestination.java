package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetTardisDestination implements IFunction {
    @Override
    public String getName() {
        return "setDestination";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return true;
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        if (!tardis.isInFlight())
            tardis.setDestination(tardis.getDestinationDimension(), new BlockPos(args.getDouble(0), args.getDouble(1), args.getDouble(2)));
        return MethodResult.of();
    }
}
