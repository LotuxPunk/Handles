package com.vandendaelen.handles.items;

import com.vandendaelen.handles.Handles;
import net.minecraft.item.Item;

public class AprioritronItem extends Item {

    public AprioritronItem() {
        super(new Item.Properties()
                .maxStackSize(1)
                .maxDamage(2500)
                .group(Handles.setup.itemGroup));
        setRegistryName("aprioritron");
    }
}
