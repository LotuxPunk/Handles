package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.DimensionHelper;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetDimension implements IFunction {
    @Override
    public String getName() {
        return "setDimension";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return true;
    }

    @Override
    public Object[] run(ConsoleTile tardis, Object[] args) {
        if (!tardis.isInFlight())
            tardis.setDestination(DimensionHelper.getDimension((int)args[0]), tardis.getDestination());
        return null;
    }
}
