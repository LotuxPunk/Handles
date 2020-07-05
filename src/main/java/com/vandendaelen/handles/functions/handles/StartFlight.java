package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.tardis.mod.controls.StabilizerControl;
import net.tardis.mod.controls.ThrottleControl;
import net.tardis.mod.tileentities.ConsoleTile;

public class StartFlight implements IFunction {
    @Override
    public String getName() {
        return "startFlight";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return true;
    }

    @Override
    public Object[] run(ConsoleTile tardis, Object[] args) {
        double speed = (double)args[0];
        if(speed > 1.0D){
            speed = 1.0D;
        }

        StabilizerControl stabilizer = tardis.getControl(StabilizerControl.class);
        ThrottleControl throttle = tardis.getControl(ThrottleControl.class);

        if (stabilizer != null && throttle != null && speed > 0){
            stabilizer.setStabilized(true);
            throttle.setAmount((float)speed);
            tardis.getWorld().getServer().enqueue(new TickDelayedTask(1, tardis::takeoff));
        }
        return null;
    }
}
