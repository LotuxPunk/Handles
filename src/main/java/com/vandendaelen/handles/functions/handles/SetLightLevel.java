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

        if (lightLevel > 15) {
            tardis.getInteriorManager().setLight(15);
            return MethodResult.of(tardis.getInteriorManager().getLight());
        }
        else if (lightLevel < 0) {
            tardis.getInteriorManager().setLight(0);
            return MethodResult.of(tardis.getInteriorManager().getLight());
        }

        System.out.println("[Handles] " + args.getInt(0));
        tardis.getInteriorManager().setLight(args.getInt(0));
        return MethodResult.of();
    }
}
