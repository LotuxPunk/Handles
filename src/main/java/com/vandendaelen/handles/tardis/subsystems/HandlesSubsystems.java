package com.vandendaelen.handles.tardis.subsystems;

import com.vandendaelen.handles.Handles;
import com.vandendaelen.handles.items.HandlesItems;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.tardis.mod.subsystem.SubsystemEntry;

public class HandlesSubsystems {
	
    public static final DeferredRegister<SubsystemEntry> SUBSYSTEMS = DeferredRegister.create(SubsystemEntry.class, Handles.MODID);
	
    public static final RegistryObject<SubsystemEntry> APRIORITRON = SUBSYSTEMS.register("aprioritron", () ->  new SubsystemEntry(AprioritronSubsystem::new, HandlesItems.APRIORITRON.get()));
}
