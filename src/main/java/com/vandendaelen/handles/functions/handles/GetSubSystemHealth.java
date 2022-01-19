package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.FunctionHelper;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetSubSystemHealth implements IFunction {
    @Override
    public String getName() {
        return "getSubSystemHealth";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        final String subSystemPath = args.getString(0);
        try {
            return MethodResult.of(FunctionHelper.getSubsystem(tardis, subSystemPath).getHealth());
        }
        catch (IllegalArgumentException exception){
            return MethodResult.of(0F);
        }
    }
}
