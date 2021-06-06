package com.vandendaelen.handles.items;

import com.vandendaelen.handles.Handles;

import net.minecraft.item.Item;
import net.minecraft.util.text.TranslationTextComponent;
import net.tardis.mod.constants.Constants.Part.PartType;
import net.tardis.mod.items.TardisPartItem;

public class AprioritronItem extends TardisPartItem {

	public AprioritronItem(PartType type, boolean requiredForFlight, boolean requiresRepair,
	        TranslationTextComponent dependentItem) {
		super(new Item.Properties()
              .maxStackSize(1)
              .maxDamage(2500)
              .group(Handles.setup.itemGroup), type, requiredForFlight, requiresRepair, dependentItem);
	}

    
}
