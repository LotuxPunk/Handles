package com.vandendaelen.handles.setup;

import com.vandendaelen.handles.Handles;
import com.vandendaelen.handles.blocks.HandlesBlocks;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
@Mod.EventBusSubscriber(modid = Handles.MODID)
public class ClientSetup {
	
	@SubscribeEvent
	public void register(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			RenderTypeLookup.setRenderLayer(HandlesBlocks.TARDISINTERFACEBLOCK.get(), RenderType.getCutout());
		});
	}

}
