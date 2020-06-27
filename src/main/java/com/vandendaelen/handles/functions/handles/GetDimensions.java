package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.DimensionHelper;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetDimensions implements IFunction {
    @Override
    public String getName() {
        return "getDimensions";
    }

    @Override
    public Object[] run(ConsoleTile tardis, Object[] args) {
        return DimensionHelper.getPrettyDimensionList().toArray();
    }
}
