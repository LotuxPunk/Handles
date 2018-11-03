package com.vandendaelen.handles.integration;

import com.vandendaelen.handles.utils.IHandlesPeripheral;
import dan200.computercraft.api.ComputerCraftAPI;

public class CCIntegration {

    public static void init(){
        ComputerCraftAPI.registerPeripheralProvider(new IHandlesPeripheral.Provider());
    }
}
