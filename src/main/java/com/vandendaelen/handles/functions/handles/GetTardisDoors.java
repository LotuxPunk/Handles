package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetTardisDoors implements IFunction {
    @Override
    public String getName() {
        return "getDoors";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        return MethodResult.of(tardis.getDoor().orElseThrow(() -> new LuaException("doors not found")).getOpenState().name());
    }
}
