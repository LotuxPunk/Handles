package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetTimeLeft implements IFunction {
    @Override
    public String getName() {
        return "getTimeLeft";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return false;
    }

    @Override
    public Object[] run(ConsoleTile tardis, Object[] args) {
        return new Integer[]{tardis.getTimeLeft()/20};
    }
}
