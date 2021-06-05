package com.vandendaelen.handles.tardis.subsystems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tardis.mod.subsystem.Subsystem;
import net.tardis.mod.tileentities.ConsoleTile;
import net.tardis.mod.tileentities.console.misc.SparkingLevel;

public class AprioritronSubsystem extends Subsystem {

	public AprioritronSubsystem(ConsoleTile console, Item item) {
		super(console, item);
	}

	@Override
	public void onFlightSecond() {
	}

	@Override
	public void onLand() {
	}

	@Override
	public void onTakeoff() {
	}
	
	@Override
    public SparkingLevel getSparkState() {
        ItemStack stack = this.getItem();
        if (stack == ItemStack.EMPTY){
            return SparkingLevel.SPARKS;
        }
        return (double)(1.0F - (float)stack.getDamage() / (float)stack.getMaxDamage()) < 0.3D ? SparkingLevel.NONE : SparkingLevel.SPARKS;
    }

}
