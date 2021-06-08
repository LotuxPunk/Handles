package com.vandendaelen.handles.tardis.upgrades;

import net.tardis.mod.subsystem.Subsystem;
import net.tardis.mod.tileentities.ConsoleTile;
import net.tardis.mod.upgrades.Upgrade;
import net.tardis.mod.upgrades.UpgradeEntry;
/** Upgrade used to allow the Tardis to interact with ComputerCraft.
 * <br> We no longer use subsystems because currently the Tardis Engine Container will not accept custom subsystems*/
public class AprioritronUpgrade extends Upgrade {

	protected AprioritronUpgrade(UpgradeEntry entry, ConsoleTile tile, Class<? extends Subsystem> clazz) {
		super(entry, tile, clazz);
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
}
