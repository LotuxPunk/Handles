package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.DimensionHelper;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetDestinationAndDimension implements IFunction {
    @Override
    public String getName() {
        return "setDestinationAndDimension";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return true;
    }

    @Override
    public Object[] run(ConsoleTile tardis, Object[] args) {
        if (!tardis.isInFlight())
            tardis.setDestination(DimensionHelper.getDimension((int)Math.round((double)args[3])), new BlockPos((double)args[0], (double)args[1], (double)args[2]));
        return null;
    }
}
