package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetTardisDestination implements IFunction {
    @Override
    public String getName() {
        return "setDestination";
    }

    @Override
    public Object[] run(ConsoleTile tardis, Object[] args) {
        if (!tardis.isInFlight())
            tardis.setDestination(tardis.getDestinationDimension(), new BlockPos((double)args[0], (double)args[1], (double)args[2]));
        return null;
    }
}
