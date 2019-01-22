package com.vandendaelen.handles.init;

import com.vandendaelen.handles.utils.Reference;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class HandlesKeyBinds {
    public static KeyBinding SNAP;

    public static void init(){
        SNAP = new KeyBinding(Reference.MODID+".keybinds.snap", Keyboard.KEY_H, Reference.MOD_NAME);
        ClientRegistry.registerKeyBinding(SNAP);
    }
}
