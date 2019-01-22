package com.vandendaelen.handles.event;

import com.vandendaelen.handles.init.HandlesKeyBinds;
import com.vandendaelen.handles.network.NetworkHandler;
import com.vandendaelen.handles.network.messages.MessageSnap;
import com.vandendaelen.handles.utils.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ClientEventHandler {
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent e) {
        if (HandlesKeyBinds.SNAP.isPressed()){
            NetworkHandler.NETWORK.sendToServer(new MessageSnap());
        }
    }
}
