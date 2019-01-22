package com.vandendaelen.handles.network;

import com.vandendaelen.handles.network.messages.MessageSnap;
import com.vandendaelen.handles.utils.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
    public static SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

    public static void init() {
        NETWORK.registerMessage(MessageSnap.Handler.class,MessageSnap.class,1, Side.SERVER);
    }

}
