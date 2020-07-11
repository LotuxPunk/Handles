package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import net.tardis.mod.controls.ThrottleControl;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetSpeed implements IFunction {
    @Override
    public String getName() {
        return "getSpeed";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return false;
    }

    @Override
    public Object[] run(ConsoleTile tardis, Object[] args) {
        return new Object[]{tardis.getControl(ThrottleControl.class).getAmount()};
    }
}
