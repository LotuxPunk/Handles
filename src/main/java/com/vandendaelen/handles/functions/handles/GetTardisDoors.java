package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetTardisDoors implements IFunction {
    @Override
    public String getName() {
        return "getDoors";
    }

    @Override
    public Object[] run(ConsoleTile tardis, Object[] args) {
        return new String[]{tardis.getDoor().orElseThrow(NullPointerException::new).getOpenState().name()};
    }
}
