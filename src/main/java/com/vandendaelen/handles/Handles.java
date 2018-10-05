package com.vandendaelen.handles;

import com.vandendaelen.handles.init.Registries;
import com.vandendaelen.handles.proxy.IProxy;
import com.vandendaelen.handles.utils.IHandlesPeripheral;
import com.vandendaelen.handles.utils.Reference;
import dan200.computercraft.api.ComputerCraftAPI;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID,name = Reference.MOD_NAME,version = Reference.VERSION,dependencies = Reference.DEP)
public class Handles {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_NAME);

    @Mod.Instance(Reference.MODID)
    public static Handles instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ComputerCraftAPI.registerPeripheralProvider(new IHandlesPeripheral.Provider());
    }
}
