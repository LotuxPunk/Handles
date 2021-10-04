package com.vandendaelen.handles.items;

import com.vandendaelen.handles.Handles;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tardis.mod.constants.TardisConstants;

public class HandlesItems {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Handles.MODID);
	
	public static final RegistryObject<Item> APRIORITRON = ITEMS.register("aprioritron", () -> new AprioritronItem(TardisConstants.Part.PartType.UPGRADE, false, true, TardisConstants.Translations.DEMAT_CIRCUIT));
}
