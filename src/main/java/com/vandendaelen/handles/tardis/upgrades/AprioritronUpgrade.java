package com.vandendaelen.handles.tardis.upgrades;

import com.vandendaelen.handles.Handles;
import com.vandendaelen.handles.blocks.tiles.TardisInterfaceTile;
import com.vandendaelen.handles.misc.TardisInterfacePeripheral;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.subsystem.Subsystem;
import net.tardis.mod.tileentities.ConsoleTile;
import net.tardis.mod.upgrades.Upgrade;
import net.tardis.mod.upgrades.UpgradeEntry;

import java.util.LinkedHashSet;
import java.util.Set;

import static dan200.computercraft.shared.Capabilities.CAPABILITY_PERIPHERAL;

/** Upgrade used to allow the Tardis to interact with ComputerCraft.
 * <br> We no longer use subsystems because currently the Tardis Engine Container will not accept custom subsystems*/
public class AprioritronUpgrade extends Upgrade {
	Set<TardisInterfaceTile> activeInterfaces = new LinkedHashSet<>();
	protected AprioritronUpgrade(UpgradeEntry entry, ConsoleTile tile, Class<? extends Subsystem> clazz) {
		super(entry, tile, clazz);
	}

	@Override
	public void onFlightSecond() {

	}

	@Override
	public void onLand() {
		for(TardisInterfaceTile tile : activeInterfaces) {
			tile.getCapability(CAPABILITY_PERIPHERAL, null).ifPresent(iPeripheral -> {
				TardisInterfacePeripheral tardisInterface = (TardisInterfacePeripheral) iPeripheral;
				final BlockPos blockPos = getConsole().getCurrentLocation();
				tardisInterface.queueEvent("onLandTardis", blockPos.getX(), blockPos.getY(), blockPos.getZ());
			});
		}
	}

	@Override
	public void onTakeoff() {
		for(TardisInterfaceTile tile : activeInterfaces) {
			tile.getCapability(CAPABILITY_PERIPHERAL, null).ifPresent(iPeripheral -> {
				TardisInterfacePeripheral tardisInterface = (TardisInterfacePeripheral) iPeripheral;
				final BlockPos blockPos = getConsole().getDestinationPosition();
				tardisInterface.queueEvent("onTakeoffTardis", blockPos.getX(), blockPos.getY(), blockPos.getZ());
			});
		}
	}

	public void attachInterface(TardisInterfaceTile interfaceTile) {
		activeInterfaces.add(interfaceTile);
		Handles.LOGGER.info("Attaching interface to upgrade.");
	}

	public void detachInterface(TardisInterfaceTile interfaceTile) {
		activeInterfaces.remove(interfaceTile);
		Handles.LOGGER.info("Detaching interface from upgrade.");
	}
}
