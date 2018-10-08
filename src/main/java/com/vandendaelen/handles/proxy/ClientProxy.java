package com.vandendaelen.handles.proxy;

import com.vandendaelen.handles.init.Registries;
import com.vandendaelen.handles.object.tileentity.TileTardisInterface;
import com.vandendaelen.handles.object.tileentity.renderer.RendererInterface;
import com.vandendaelen.handles.object.tileentity.renderer.RendererInterfaceItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy implements IProxy {
    @Override
    public void preInit() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileTardisInterface.class, new RendererInterface());
    }

    @Override
    public void init() {
        Item.getItemFromBlock(Registries.block_interface_tardis).setTileEntityItemStackRenderer(new RendererInterfaceItem());
    }
}
