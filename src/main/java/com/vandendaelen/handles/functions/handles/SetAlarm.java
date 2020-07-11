package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetAlarm implements IFunction {
    @Override
    public String getName() {
        return "setAlarm";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return true;
    }

    @Override
    public Object[] run(ConsoleTile tardis, Object[] args) {
        tardis.getInteriorManager().setAlarmOn((boolean)args[0]);
        return new Object[]{tardis.getInteriorManager().isAlarmOn()};
    }
}
