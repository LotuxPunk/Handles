package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetTardisDestination implements IFunction {
    @Override
    public String getName() {
        return "getDestination";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return false;
    }

    @Override
    public Object[] run(ConsoleTile tardis, Object[] args) {
        BlockPos pos = tardis.getDestination();
        return new Object[]{pos.getX(), pos.getY(), pos.getZ()};
    }
}
