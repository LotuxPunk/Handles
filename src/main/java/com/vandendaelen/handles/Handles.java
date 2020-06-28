package com.vandendaelen.handles;

import com.vandendaelen.handles.blocks.HandlesBlocks;
import com.vandendaelen.handles.blocks.TardisInterfaceBlock;
import com.vandendaelen.handles.blocks.tiles.TardisInterfaceTile;
import com.vandendaelen.handles.functions.FunctionsHandler;
import com.vandendaelen.handles.items.AprioritronItem;
import com.vandendaelen.handles.setup.ClientProxy;
import com.vandendaelen.handles.setup.HandlesSetup;
import com.vandendaelen.handles.setup.IProxy;
import com.vandendaelen.handles.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("handles")
public class Handles {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "handles";
    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    public static HandlesSetup setup = new HandlesSetup();

    public Handles() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        setup.init();
        proxy.init();
        FunctionsHandler.init();
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            blockRegistryEvent.getRegistry().register(new TardisInterfaceBlock());
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            Item.Properties properties = new Item.Properties()
                    .group(setup.itemGroup);
            itemRegistryEvent.getRegistry().register(new BlockItem(HandlesBlocks.TARDISINTERFACEBLOCK, properties).setRegistryName("tardisinterface"));
            itemRegistryEvent.getRegistry().register(new AprioritronItem());
        }

        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> tileEntityRegistryEvent){
            tileEntityRegistryEvent.getRegistry().register(TileEntityType.Builder.create(TardisInterfaceTile::new, HandlesBlocks.TARDISINTERFACEBLOCK).build(null).setRegistryName("tardisinterface"));
        }
    }
}
