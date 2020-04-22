package com.vandendaelen.handles.tardis.subsystems;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.tardis.mod.subsystem.Subsystem;
import net.tardis.mod.tileentities.ConsoleTile;

public class AprioritronSubsystem extends Subsystem {

    public AprioritronSubsystem(ConsoleTile console, Item item) {
        super(console, item);
    }

    @Override
    public CompoundNBT serializeNBT() {
        return new CompoundNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {

    }


}
