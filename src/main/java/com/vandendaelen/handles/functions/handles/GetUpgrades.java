package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.tileentities.ConsoleTile;

import java.util.stream.Collectors;

public class GetUpgrades implements IFunction {
    @Override
    public String getName() {
        return "getUpgrades";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        return MethodResult.of(tardis.getUpgrades().stream().map(upgrade -> upgrade.getEntry().getRegistryName().getPath()).collect(Collectors.toSet()));
    }
}
