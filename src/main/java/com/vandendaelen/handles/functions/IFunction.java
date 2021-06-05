package com.vandendaelen.handles.functions;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.tileentities.ConsoleTile;

public interface IFunction {
    public String getName();
    public boolean impactMoodAndLoyalty();
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException;
}
