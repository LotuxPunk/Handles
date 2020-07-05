package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import net.minecraft.util.Direction;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetTardisFacing implements IFunction {
    @Override
    public String getName() {
        return "setFacing";
    }

    @Override
    public Object[] run(ConsoleTile tardis, Object[] args) {
        String status = (String)args[0];
        tardis.setDirection(Direction.valueOf(status));
        return null;
    }
}
