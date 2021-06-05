package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.DimensionHelper;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetDestinationAndDimension implements IFunction {
    @Override
    public String getName() {
        return "setDestinationAndDimension";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return true;
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        if (!tardis.isInFlight())
            tardis.setDestination(DimensionHelper.getDimension((int)Math.round(args.getDouble(3))), new BlockPos(args.getDouble(0), args.getDouble(1), args.getDouble(2)));
        return null;
    }
}
