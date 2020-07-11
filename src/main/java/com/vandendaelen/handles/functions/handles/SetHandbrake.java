package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
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
    public Object[] run(ConsoleTile tardis, Object[] args) {
        tardis.getControl(HandbrakeControl.class).setFree((boolean)args[0]);
        return new Object[]{tardis.getControl(HandbrakeControl.class).isFree()};
    }
}
