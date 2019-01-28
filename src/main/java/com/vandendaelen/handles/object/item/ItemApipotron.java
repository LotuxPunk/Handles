package com.vandendaelen.handles.object.item;

import com.vandendaelen.handles.utils.BlockNames;
import com.vandendaelen.handles.utils.Reference;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.common.items.components.ItemComponent;

public class ItemApipotron extends ItemComponent {

    public ItemApipotron() {
        super();
        ResourceLocation rl = new ResourceLocation(Reference.MODID,"apipotron");
        this.setTranslationKey(BlockNames.APIPOTRON);
        this.setRegistryName(rl);
    }
}
