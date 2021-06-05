package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.DimensionHelper;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetDimension implements IFunction {
    @Override
    public String getName() {
        return "setDimension";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return true;
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        if (!tardis.isInFlight())
            tardis.setDestination(DimensionHelper.getDimension((int)Math.round(args.getDouble(0))), tardis.getDestinationPosition());
        return null;
    }
}
