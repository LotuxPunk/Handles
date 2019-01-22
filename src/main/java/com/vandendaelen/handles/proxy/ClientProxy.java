package com.vandendaelen.handles.proxy;

import com.vandendaelen.handles.init.HandlesKeyBinds;
import com.vandendaelen.handles.init.Registries;
import com.vandendaelen.handles.integration.Integrations;
import com.vandendaelen.handles.object.tileentity.TileHandles;
import com.vandendaelen.handles.object.tileentity.TileTardisInterfaceBase;
import com.vandendaelen.handles.object.tileentity.renderer.RendererHandles;
import com.vandendaelen.handles.object.tileentity.renderer.RendererHandlesItem;
import com.vandendaelen.handles.object.tileentity.renderer.RendererInterface;
import com.vandendaelen.handles.object.tileentity.renderer.RendererInterfaceItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy implements IProxy {
    @Override
    public void preInit() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileTardisInterfaceBase.class, new RendererInterface());
        ClientRegistry.bindTileEntitySpecialRenderer(TileHandles.class,new RendererHandles());
    }

    @Override
    public void init() {
        if (Integrations.isCCLoaded())Item.getItemFromBlock(Registries.block_interface_tardis_cc).setTileEntityItemStackRenderer(new RendererInterfaceItem());
        if (Integrations.isOCLoaded())Item.getItemFromBlock(Registries.block_interface_tardis_oc).setTileEntityItemStackRenderer(new RendererInterfaceItem());
        Item.getItemFromBlock(Registries.block_handles).setTileEntityItemStackRenderer(new RendererHandlesItem());

        //Keybinds
        HandlesKeyBinds.init();
    }
}
