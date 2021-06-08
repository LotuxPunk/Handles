package com.vandendaelen.handles.setup;

import com.vandendaelen.handles.Handles;
import com.vandendaelen.handles.blocks.HandlesBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class HandlesSetup {
    public ItemGroup itemGroup = new ItemGroup(Handles.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(HandlesBlocks.TARDISINTERFACEBLOCK.get());
        }
    };
    public void init(){

    }
}
