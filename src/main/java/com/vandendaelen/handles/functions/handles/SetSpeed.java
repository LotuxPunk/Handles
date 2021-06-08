package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;

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
    public boolean impactMoodAndLoyalty() {
        return true;
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        tardis.getControl(ThrottleControl.class).get().setAmount((float)(args.getDouble(0)));
        return MethodResult.of(tardis.getControl(ThrottleControl.class).get().getAmount());
    }
}
