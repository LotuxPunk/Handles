package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
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
    public Object[] run(ConsoleTile tardis, Object[] args) {
        tardis.getControl(ThrottleControl.class).setAmount((float)((double)args[0]));
        return new Object[]{tardis.getControl(ThrottleControl.class).getAmount()};
    }
}
