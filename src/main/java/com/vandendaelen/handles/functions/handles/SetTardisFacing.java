package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.minecraft.util.Direction;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetTardisFacing implements IFunction {
    @Override
    public String getName() {
        return "setFacing";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        String status = args.getString(0);
        tardis.setExteriorFacingDirection(Direction.valueOf(status));
        return MethodResult.of();
    }
}
