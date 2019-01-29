package com.vandendaelen.handles.tardis;

import com.vandendaelen.handles.init.Registries;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.systems.TardisSystems;

public class SystemAprioritron extends TardisSystems.BaseSystem {
    private static final float DAMAGE_PER_USE = 0.01F;

    @Override
    public void onUpdate(World world, BlockPos consolePos) {

    }

    @Override
    public void damage() {
        this.setHealth(this.getHealth() - DAMAGE_PER_USE);
    }

    @Override
    public Item getRepairItem() {
        return Registries.aprioritron;
    }

    @Override
    public String getNameKey() {
        return "system.handles.aprioritron";
    }

    @Override
    public void wear() {

    }

    @Override
    public boolean shouldStopFlight() {
        return false;
    }


}
