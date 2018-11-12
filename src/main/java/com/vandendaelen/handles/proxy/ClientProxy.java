package com.vandendaelen.handles.proxy;

import com.vandendaelen.handles.init.Registries;
import com.vandendaelen.handles.object.tileentity.TileHandles;
import com.vandendaelen.handles.object.tileentity.TileTardisInterfaceBase;
import com.vandendaelen.handles.object.tileentity.renderer.RendererHandles;
import com.vandendaelen.handles.object.tileentity.renderer.RendererHandlesItem;
import com.vandendaelen.handles.object.tileentity.renderer.RendererInterface;
import com.vandendaelen.handles.object.tileentity.renderer.RendererInterfaceItem;
import com.vandendaelen.handles.utils.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;

public class ClientProxy implements IProxy {
    @Override
    public void preInit() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileTardisInterfaceBase.class, new RendererInterface());
        ClientRegistry.bindTileEntitySpecialRenderer(TileHandles.class,new RendererHandles());
    }

    @Override
    public void init() {
        if (Loader.isModLoaded(Reference.Dependencies.CC))Item.getItemFromBlock(Registries.block_interface_tardis_cc).setTileEntityItemStackRenderer(new RendererInterfaceItem());
        if (Loader.isModLoaded(Reference.Dependencies.OC))Item.getItemFromBlock(Registries.block_interface_tardis_oc).setTileEntityItemStackRenderer(new RendererInterfaceItem());
        Item.getItemFromBlock(Registries.block_handles).setTileEntityItemStackRenderer(new RendererHandlesItem());
    }
}
