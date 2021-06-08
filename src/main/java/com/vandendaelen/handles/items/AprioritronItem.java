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
              .stacksTo(1)
              .durability(2500)
              .tab(Handles.setup.itemGroup), type, requiredForFlight, requiresRepair, dependentItem);
	}

    
}
