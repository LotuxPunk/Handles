package com.vandendaelen.handles.tardis;

import com.vandendaelen.handles.object.item.ItemApipotron;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.systems.TardisSystems;

public class SystemApipotron extends TardisSystems.BaseSystem {
    @Override
    public void onUpdate(World world, BlockPos consolePos) {

    }

    @Override
    public void damage() {

    }

    @Override
    public Item getRepairItem() {
        return new ItemApipotron();
    }

    @Override
    public String getNameKey() {
        return "system.handles.apipotron";
    }

    @Override
    public void wear() {

    }

    @Override
    public boolean shouldStopFlight() {
        return false;
    }
}
