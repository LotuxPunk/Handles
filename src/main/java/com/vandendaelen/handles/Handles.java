package com.vandendaelen.handles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vandendaelen.handles.blocks.HandlesBlocks;
import com.vandendaelen.handles.blocks.HandlesTiles;
import com.vandendaelen.handles.config.HandlesConfig;
import com.vandendaelen.handles.functions.FunctionsHandler;
import com.vandendaelen.handles.items.HandlesItems;
import com.vandendaelen.handles.setup.ClientSetup;
import com.vandendaelen.handles.setup.HandlesSetup;
import com.vandendaelen.handles.tardis.upgrades.HandlesUpgrades;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("handles")
public class Handles {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "handles";
    public static HandlesSetup setup = new HandlesSetup();

    public Handles() {
    	IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
    	modBus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerLoggedIn);
        MinecraftForge.EVENT_BUS.register(new ClientSetup());
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, HandlesConfig.SERVER_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, HandlesConfig.CLIENT_SPEC);
        HandlesBlocks.BLOCKS.register(modBus);
        HandlesItems.ITEMS.register(modBus);
        HandlesTiles.TILES.register(modBus);
        HandlesUpgrades.UPGRADES.register(modBus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        setup.init();
        FunctionsHandler.init();
    }

    private void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerEntity player = event.getPlayer();
        if (HandlesConfig.Client.getDiscordAdvertising())
            player.displayClientMessage(ForgeHooks.newChatWithLinks("[Handles] Discord's server : https://discord.gg/6cq3skc"), false);
    }
}
