package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.FunctionHelper;
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
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        final boolean status = args.getBoolean(0);
        FunctionHelper.getTardisControl(tardis, RefuelerControl.class).setRefueling(status);
        return MethodResult.of();
    }
}
