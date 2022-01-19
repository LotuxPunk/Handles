package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.FunctionHelper;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.controls.ThrottleControl;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetSpeed implements IFunction {
    @Override
    public String getName() {
        return "setSpeed";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        final ThrottleControl throttleControl = FunctionHelper.getTardisControl(tardis, ThrottleControl.class);
        throttleControl.setAmount((float)(args.getDouble(0)));
        return MethodResult.of(throttleControl.getAmount());
    }
}
