package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.tardis.mod.enums.EnumDoorState;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetTardisDoors implements IFunction {
    @Override
    public String getName() {
        return "setDoors";
    }

    @Override
    public Object[] run(ConsoleTile tardis, Object[] args) {
        String status = (String)args[0];
        tardis.getWorld().getServer().enqueue(new TickDelayedTask(1, ()->{
            tardis.getDoor().ifPresent(doorEntity -> {
                doorEntity.setOpenState(EnumDoorState.valueOf(status));
                doorEntity.openOther();
            });

        }));
        return null;
    }
}
