package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetTardisFacing implements IFunction {
    @Override
    public String getName() {
        return "getFacing";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) {
        return MethodResult.of(tardis.getExteriorFacingDirection().name());
    }
}
