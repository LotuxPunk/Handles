package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.controls.RefuelerControl;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetRefuel implements IFunction {
    @Override
    public String getName() {
        return "setRefuel";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return true;
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        boolean status = args.getBoolean(0);
        tardis.getControl(RefuelerControl.class).get().setRefueling(status);
        return null;
    }
}
