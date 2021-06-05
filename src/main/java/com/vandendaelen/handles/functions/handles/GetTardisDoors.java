package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetTardisDoors implements IFunction {
    @Override
    public String getName() {
        return "getDoors";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return false;
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) {
        return MethodResult.of(tardis.getDoor().orElseThrow(NullPointerException::new).getOpenState().name());
    }
}
