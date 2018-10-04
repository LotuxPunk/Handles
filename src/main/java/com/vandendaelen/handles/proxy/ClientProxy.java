package com.vandendaelen.handles.proxy;

import com.vandendaelen.handles.object.tileentity.TileTardisInterface;
import com.vandendaelen.handles.object.tileentity.renderer.RendererInterface;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy implements IProxy {
    @Override
    public void preInit() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileTardisInterface.class, new RendererInterface());
    }
}
