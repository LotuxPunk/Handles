package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import net.tardis.mod.controls.HandbrakeControl;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetHandbrake implements IFunction {
    @Override
    public String getName() {
        return "isHandbrakeFree";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return false;
    }

    @Override
    public Object[] run(ConsoleTile tardis, Object[] args) {
        return new Object[]{tardis.getControl(HandbrakeControl.class).isFree()};
    }
}
