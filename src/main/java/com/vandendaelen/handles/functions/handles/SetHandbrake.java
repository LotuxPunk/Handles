package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.controls.HandbrakeControl;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetHandbrake implements IFunction {
    @Override
    public String getName() {
        return "setHandbrake";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return true;
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        tardis.getControl(HandbrakeControl.class).get().setFree(args.getBoolean(0));
        return MethodResult.of(tardis.getControl(HandbrakeControl.class).get().isFree());
    }
}
