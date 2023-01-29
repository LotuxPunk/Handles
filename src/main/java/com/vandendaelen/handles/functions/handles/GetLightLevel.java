package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetLightLevel implements IFunction {
    @Override
    public String getName() {
        return "getLight";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        return MethodResult.of(tardis.getInteriorManager().getLight());
    }
}
