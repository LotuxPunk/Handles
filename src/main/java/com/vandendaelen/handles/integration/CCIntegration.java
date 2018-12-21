package com.vandendaelen.handles.integration;

import com.vandendaelen.handles.utils.IHandlesPeripheral;
import com.vandendaelen.handles.utils.Reference;
import dan200.computercraft.api.ComputerCraftAPI;
import net.minecraftforge.fml.common.Loader;

public class CCIntegration {

    public static void init(){
        ComputerCraftAPI.registerPeripheralProvider(new IHandlesPeripheral.Provider());
    }

    public static boolean isModLoaded(){
        return Loader.isModLoaded(Reference.Dependencies.CC);
    }
}
