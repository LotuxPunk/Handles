package com.vandendaelen.handles.utils;

import com.vandendaelen.handles.init.Registries;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.tardis.mod.common.blocks.interfaces.INeedItem;

public class RegUtils {

    public static Item createItem(Item item, String name) {
        item.setUnlocalizedName(Reference.MODID + ":" + name);
        item.setRegistryName(new ResourceLocation(Reference.MODID, name));
        Registries.ITEMS.add(item);
        return item;
    }

    public static Block createBlock(Block block, String name) {
        ResourceLocation rl = new ResourceLocation(Reference.MODID, name);
        block.setUnlocalizedName(rl.toString());
        block.setRegistryName(rl);
        Registries.BLOCKS.add(block);
        if(!(block instanceof INeedItem))Registries.ITEMS.add(new ItemBlock(block).setRegistryName(rl));
        else Registries.ITEMS.add(((INeedItem)block).getItem().setRegistryName(rl));
        return block;
    }

    public static void addTile(Class<? extends TileEntity> clazz, String name) {
        GameRegistry.registerTileEntity(clazz, new ResourceLocation(Reference.MODID, name));
    }

}
