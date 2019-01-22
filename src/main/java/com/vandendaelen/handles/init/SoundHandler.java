package com.vandendaelen.handles.init;

import com.vandendaelen.handles.utils.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class SoundHandler {
    private static ArrayList<SoundEvent> sounds = new ArrayList<>();

    public static SoundEvent snap = register("snap");

    public static SoundEvent register(String name) {
        ResourceLocation rl = new ResourceLocation(Reference.MODID, name);
        SoundEvent event = new SoundEvent(rl);
        event.setRegistryName(rl);
        sounds.add(event);
        return event;
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        for (SoundEvent sound : sounds) {
            event.getRegistry().register(sound);
        }
    }
}
