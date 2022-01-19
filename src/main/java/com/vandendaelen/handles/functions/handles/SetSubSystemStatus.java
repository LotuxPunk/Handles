package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.FunctionHelper;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.tardis.mod.subsystem.Subsystem;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetSubSystemStatus implements IFunction {
    @Override
    public String getName() {
        return "setSubSystemStatus";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        final String subSystemPath = args.getString(0);
        final boolean status = args.getBoolean(1);
        final Subsystem subsystem = FunctionHelper.getSubsystem(tardis, subSystemPath);

        tardis.getLevel().getServer().tell(new TickDelayedTask(1,() -> subsystem.setActivated(status)));

        return MethodResult.of();
    }
}
