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

public class SetFlight implements IFunction {

    @Override
    public String getName() {
        return "setFlight";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        double speed = args.getDouble(0);
        final ThrottleControl throttle = FunctionHelper.getTardisControl(tardis, ThrottleControl.class);
        final HandbrakeControl handbrakeControl = FunctionHelper.getTardisControl(tardis, HandbrakeControl.class);

        if(speed > 1.0D){
            speed = 1.0D;
        }
        /* 50ap5ud5: As requested, make the Tardis takeoff regardless of stabilisers.
         * This is to allow for users to manually set subsystem values in the future when we add the Subsystem State Modification feature.
         */
        if (speed > 0 && !tardis.isInFlight() && !handbrakeControl.isFree()) {
            throttle.setAmount((float)speed);
            tardis.getLevel().getServer().tell(new TickDelayedTask(1,() -> handbrakeControl.onRightClicked(tardis, null))); //Schedule by 1 tick to allow the throttle animation to play correctly.
        }
        return MethodResult.of();
    }
}
