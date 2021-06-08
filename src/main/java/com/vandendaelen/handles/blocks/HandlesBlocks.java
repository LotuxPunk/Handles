package com.vandendaelen.handles.blocks;

import java.util.function.Supplier;

import com.vandendaelen.handles.Handles;
import com.vandendaelen.handles.items.HandlesItems;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HandlesBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Handles.MODID);

    public static RegistryObject<Block> TARDISINTERFACEBLOCK = register("tardisinterface", () -> new TardisInterfaceBlock(), Handles.setup.itemGroup);

    /**
     * Registers a Block and BlockItem to the ItemGroup of your choice
     * @param <T>
     * @param id
     * @param blockSupplier
     * @param itemGroup
     * @return
     */
    private static <T extends Block> RegistryObject<T> register(String id, Supplier<T> blockSupplier, ItemGroup itemGroup){
        RegistryObject<T> registryObject = BLOCKS.register(id, blockSupplier);
        HandlesItems.ITEMS.register(id, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(itemGroup)));
        return registryObject;
    }
}
