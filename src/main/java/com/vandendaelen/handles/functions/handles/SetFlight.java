package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.tardis.mod.controls.ThrottleControl;
import net.tardis.mod.subsystem.StabilizerSubsystem;
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
        StabilizerSubsystem stabilizer = tardis.getSubsystem(StabilizerSubsystem.class).orElse(null);
    	if (throttle != null & speed > 0) {
    		if (stabilizer.canBeUsed()) {
    			stabilizer.setControlActivated(!stabilizer.isActivated());
    			throttle.setAmount((float)speed);
    			tardis.getWorld().getServer().enqueue(new TickDelayedTask(1, tardis::takeoff));
    		}
    	}
        
        return null;
    }
}
