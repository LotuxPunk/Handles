package com.vandendaelen.handles.tardis.upgrades;

import com.vandendaelen.handles.Handles;
import com.vandendaelen.handles.items.HandlesItems;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.tardis.mod.subsystem.FlightSubsystem;
import net.tardis.mod.upgrades.UpgradeEntry;

public class HandlesUpgrades {

    public static final DeferredRegister<UpgradeEntry> UPGRADES = DeferredRegister.create(UpgradeEntry.class, Handles.MODID);

    public static final RegistryObject<UpgradeEntry> APRIORITRON = UPGRADES.register("aprioritron", () ->  new UpgradeEntry(AprioritronUpgrade::new, HandlesItems.APRIORITRON.get(), FlightSubsystem.class));
}
