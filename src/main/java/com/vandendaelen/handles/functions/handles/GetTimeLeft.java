package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetTimeLeft implements IFunction {
    @Override
    public String getName() {
        return "getTimeLeft";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) {
        final int currentFlightTicks = tardis.getTimeInFlight();
        final int reachDestinationTick = tardis.getReachDestinationTick();
        return MethodResult.of(((reachDestinationTick - currentFlightTicks) /20), currentFlightTicks, reachDestinationTick);
    }
}
