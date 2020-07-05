package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetTardisLocation implements IFunction {
    @Override
    public String getName() {
        return "getLocation";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return false;
    }

    @Override
    public Object[] run(ConsoleTile tardis, Object[] args) {
        BlockPos pos = tardis.getLocation();
        return new Object[]{pos.getX(), pos.getY(), pos.getZ()};
    }
}
