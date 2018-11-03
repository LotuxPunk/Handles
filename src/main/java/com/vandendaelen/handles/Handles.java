package com.vandendaelen.handles;

import com.vandendaelen.handles.init.Registries;
import com.vandendaelen.handles.integration.CCIntegration;
import com.vandendaelen.handles.object.block.TardisInterfaceCC;
import com.vandendaelen.handles.object.block.TardisInterfaceOC;
import com.vandendaelen.handles.proxy.IProxy;
import com.vandendaelen.handles.utils.Reference;
import com.vandendaelen.handles.utils.RegUtils;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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
        if(Loader.isModLoaded(Reference.Dependencies.CC)){
            Registries.block_interface_tardis_cc = RegUtils.createBlock(new TardisInterfaceCC(),"tardis_interface_cc");
        }
        if (Loader.isModLoaded(Reference.Dependencies.OC)) {
            Registries.block_interface_tardis_oc = RegUtils.createBlock(new TardisInterfaceOC(), "tardis_interface_oc");
        }
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (Loader.isModLoaded(Reference.Dependencies.CC)){
            CCIntegration.init();
        }
        proxy.init();
    }
}
