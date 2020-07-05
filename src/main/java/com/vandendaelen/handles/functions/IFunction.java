package com.vandendaelen.handles.functions;

import net.tardis.mod.tileentities.ConsoleTile;

public interface IFunction {
    public String getName();
    public boolean impactMoodAndLoyalty();
    public Object[] run(ConsoleTile tardis, Object[] args);
}
