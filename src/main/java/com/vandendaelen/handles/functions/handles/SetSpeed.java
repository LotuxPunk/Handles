package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.FunctionHelper;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.tardis.mod.controls.HandbrakeControl;
import net.tardis.mod.controls.ThrottleControl;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetSpeed implements IFunction {
    @Override
    public String getName() {
        return "setSpeed";
    }

    @Override
    public MethodResult run(ConsoleTile console, IArguments args) throws LuaException {
        final var throttleControl = FunctionHelper.getTardisControl(console, ThrottleControl.class);
        final var handbrakeControl = FunctionHelper.getTardisControl(console, HandbrakeControl.class);

        final var speed = args.getDouble(0);
        throttleControl.setAmount((float)(speed));

        if (speed == 0 && console.isInFlight()) {
            console.getLevel().getServer().tell(new TickDelayedTask(1, console::initLand));
        } else {
            if (handbrakeControl.isFree() && !console.isInFlight()) {
                console.getLevel().getServer().tell(new TickDelayedTask(1, console::takeoff));
            } else {
                console.updateFlightTime();
            }
        }

        return MethodResult.of(throttleControl.getAmount());
    }
}
