package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetArtronBank implements IFunction {
    @Override
    public String getName() {
        return "getArtronBank";
    }

    @Override
    public Object[] run(ConsoleTile tardis, Object[] args) {
        return new Double[]{(double) tardis.getArtron()};
    }
}
