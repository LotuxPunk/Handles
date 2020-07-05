package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.DimensionHelper;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetCurrentDimension implements IFunction {
    @Override
    public String getName() {
        return "getDimension";
    }

    @Override
    public Object[] run(ConsoleTile tardis, Object[] args) {
        return new Object[]{DimensionHelper.getDimensionId(tardis.getDimension())};
    }
}
