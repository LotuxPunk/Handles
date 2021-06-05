package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.MethodResult;
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
    public MethodResult run(ConsoleTile tardis, IArguments args) {
        BlockPos pos = tardis.getDestinationPosition();
        return MethodResult.of(pos.getX(), pos.getY(), pos.getZ());
    }
}
