package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.tardis.mod.controls.ThrottleControl;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetFlight implements IFunction {

    @Override
    public String getName() {
        return "setFlight";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return true;
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        double speed = args.getDouble(0);
        if(speed > 1.0D){
            speed = 1.0D;
        }
        ThrottleControl throttle = tardis.getControl(ThrottleControl.class).get();
        /* 50ap5ud5: As requested, make the Tardis takeoff regardless of stabilisers.
         * This is to allow for users to manually set subsystem values in the future when we add the Subsystem State Modification feature.
         */
        if (throttle != null & speed > 0) { 
            throttle.setAmount((float)speed);
            tardis.getLevel().getServer().tell(new TickDelayedTask(1, tardis::takeoff)); //Schedule by 1 tick to allow the throttle animation to play correctly.
        }
        return MethodResult.of();
    }
}
