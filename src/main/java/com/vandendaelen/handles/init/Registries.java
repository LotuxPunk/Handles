package com.vandendaelen.handles.init;

import com.vandendaelen.handles.integration.CCIntegration;
import com.vandendaelen.handles.integration.OCIntegration;
import com.vandendaelen.handles.object.block.Handles;
import com.vandendaelen.handles.object.block.TardisInterfaceCC;
import com.vandendaelen.handles.object.block.TardisInterfaceOC;
import com.vandendaelen.handles.object.item.ItemHandles;
import com.vandendaelen.handles.object.item.ItemTardisInterface;
import com.vandendaelen.handles.object.tileentity.TileHandles;
import com.vandendaelen.handles.object.tileentity.TileTardisInterfaceCC;
import com.vandendaelen.handles.object.tileentity.TileTardisInterfaceOC;
import com.vandendaelen.handles.object.tileentity.renderer.RendererHandlesItem;
import com.vandendaelen.handles.object.tileentity.renderer.RendererInterfaceItem;
import com.vandendaelen.handles.utils.BlockNames;
import com.vandendaelen.handles.utils.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid= Reference.MODID)
public class Registries {
    @GameRegistry.ObjectHolder(BlockNames.TARDIS_LINK_CC)
    public static Block block_interface_tardis_cc;
    @GameRegistry.ObjectHolder(BlockNames.TARDIS_LINK_OC)
    public static Block block_interface_tardis_oc;
    @GameRegistry.ObjectHolder(BlockNames.HANDLES)
    public static Block block_handles;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> e) {
        IForgeRegistry<Block> reg = e.getRegistry();

        //Handles
        GameRegistry.registerTileEntity(TileHandles.class,new ResourceLocation(Reference.MODID,"TileHandles"));
        reg.register(new Handles());

        //CC
        if (CCIntegration.isModLoaded()){
            GameRegistry.registerTileEntity(TileTardisInterfaceCC.class,new ResourceLocation(Reference.MODID,"TileTardisInterfaceCC"));
            reg.register(new TardisInterfaceCC());
        }

        //OC
        if (OCIntegration.isModLoaded()){
            GameRegistry.registerTileEntity(TileTardisInterfaceOC.class,new ResourceLocation(Reference.MODID,"TileTardisInterfaceOC"));
            reg.register(new TardisInterfaceOC());
        }


    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        IForgeRegistry<Item> reg = e.getRegistry();

        //Handles
        reg.register(new ItemHandles(block_handles));

        //CC
        if(CCIntegration.isModLoaded())
            reg.register(new ItemTardisInterface(block_interface_tardis_cc));

        //OC
        if(OCIntegration.isModLoaded())
            reg.register(new ItemTardisInterface(block_interface_tardis_oc));
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        //Handles
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block_handles), 0, new ModelResourceLocation(block_handles.getRegistryName(), "normal"));

        //CC
        if(CCIntegration.isModLoaded())
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block_interface_tardis_cc), 0, new ModelResourceLocation(block_interface_tardis_cc.getRegistryName(), "normal"));

        //OC
        if(OCIntegration.isModLoaded())
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block_interface_tardis_oc), 0, new ModelResourceLocation(block_interface_tardis_oc.getRegistryName(), "normal"));
    }
}
