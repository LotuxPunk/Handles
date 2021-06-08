package com.vandendaelen.handles.functions;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.tileentities.ConsoleTile;
/** Template for Handles Mod Methods*/
public interface IFunction {
	/** Defines the method name and registry id. E.g. setDestination*/
    public String getName();
    /** If this function should cause mood and loyalty impacts*/
    public boolean impactMoodAndLoyalty();
    /** The logic to run when this function is executed. If we don't want to return anything, return an empty {@linkplain MethodResult} to prevent NPE*/
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException;
}
