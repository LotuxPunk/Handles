package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import net.tardis.mod.controls.RefuelerControl;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetRefuel implements IFunction {
    @Override
    public String getName() {
        return "setRefuel";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return true;
    }

    @Override
    public Object[] run(ConsoleTile tardis, Object[] args) {
        boolean status = (boolean)args[0];
        tardis.getControl(RefuelerControl.class).setRefuling(status);
        return null;
    }
}
