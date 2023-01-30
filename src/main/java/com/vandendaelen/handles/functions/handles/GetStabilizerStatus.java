package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.FunctionHelper;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.subsystem.StabilizerSubsystem;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetStabilizerStatus implements IFunction {
    @Override
    public String getName() {
        return "getStabilizerStatus";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        try {
            final StabilizerSubsystem stabilizerSubsystem = FunctionHelper.getTardisSubsystem(tardis, StabilizerSubsystem.class);
            return MethodResult.of(stabilizerSubsystem.isControlActivated());
        } catch (IllegalArgumentException e) {
            throw new LuaException(e.getMessage());
        }
    }
}
