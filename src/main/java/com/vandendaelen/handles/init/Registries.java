package com.vandendaelen.handles.init;

import com.vandendaelen.handles.object.block.TardisInterface;
import com.vandendaelen.handles.object.tileentity.TileTardisInterface;
import com.vandendaelen.handles.utils.Reference;
import com.vandendaelen.handles.utils.RegUtils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid= Reference.MODID)
public class Registries {
    public static List<Block> BLOCKS = new ArrayList<Block>();
    public static List<Item> ITEMS = new ArrayList<>();

    public final static Block block_interface_tardis = RegUtils.createBlock(new TardisInterface(),"tardis_interface");

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> e) {
        IForgeRegistry<Block> reg = e.getRegistry();
        regTiles();
        reg.registerAll(BLOCKS.toArray(new Block[BLOCKS.size()]));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        IForgeRegistry<Item> reg = e.getRegistry();
        reg.registerAll(ITEMS.toArray(new Item[ITEMS.size()]));
    }

    private static void regTiles() {
        RegUtils.addTile(TileTardisInterface.class, "TileTardisInterface");
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for (Block block : BLOCKS) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
        }
        for (Item item : ITEMS) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }
}
