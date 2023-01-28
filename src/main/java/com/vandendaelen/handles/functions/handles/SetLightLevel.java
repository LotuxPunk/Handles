package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetLightLevel implements IFunction {
    @Override
    public String getName() {
        return "setLight";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        int lightLevel = args.getInt(0);

       final int MAX_LIGHT_LEVEL = 15
       final int MIN_LIGHT_LEVEL = 0
       
        if (lightLevel > MAX_LIGHT_LEVEL) {
            lightLevel = MAX_LIGHT_LEVEL
        }
        else if (lightLevel < MIN_LIGHT_LEVEL) {
            lightLevel = MIN_LIGHT_LEVEL
        }
        
        tardis.getInteriorManager().setLight(lightLevel);
        return MethodResult.of(tardis.getInteriorManager().getLight());
    }
}
