package com.vandendaelen.handles;

import com.vandendaelen.handles.integration.CCIntegration;
import com.vandendaelen.handles.integration.Integrations;
import com.vandendaelen.handles.network.NetworkHandler;
import com.vandendaelen.handles.proxy.IProxy;
import com.vandendaelen.handles.tardis.SystemApipotron;
import com.vandendaelen.handles.utils.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.tardis.mod.common.systems.TardisSystems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID,name = Reference.MOD_NAME,version = Reference.VERSION.VERSION,dependencies = Reference.DEP, updateJSON = Reference.UPDATE_JSON)
public class Handles {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_NAME);

    @Mod.Instance(Reference.MODID)
    public static Handles instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit();
        TardisSystems.register("apipotron", SystemApipotron.class);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (Integrations.isCCLoaded()){
            CCIntegration.init();
        }
        proxy.init();
        NetworkHandler.init();
    }
}
