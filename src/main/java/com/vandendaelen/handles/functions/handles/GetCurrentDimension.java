package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.DimensionHelper;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetCurrentDimension implements IFunction {
    @Override
    public String getName() {
        return "getDimension";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return false;
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) {
        return MethodResult.of(DimensionHelper.getDimensionId(tardis.getCurrentDimension()));
    }
}
