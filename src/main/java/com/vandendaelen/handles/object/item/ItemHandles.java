package com.vandendaelen.handles.object.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemHandles extends ItemBlock {
    public ItemHandles(Block block) {
        super(block);
        this.setRegistryName(block.getRegistryName());
        this.setMaxDamage(0);
        this.setHasSubtypes(false);
    }
}
