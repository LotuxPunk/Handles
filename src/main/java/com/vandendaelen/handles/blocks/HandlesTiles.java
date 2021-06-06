package com.vandendaelen.handles.blocks;

import com.google.common.base.Supplier;
import com.vandendaelen.handles.Handles;
import com.vandendaelen.handles.blocks.tiles.TardisInterfaceTile;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HandlesTiles {

    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Handles.MODID);

    public static RegistryObject<TileEntityType<TardisInterfaceTile>> TARDISINTERFACE_TILE = TILES.register("tardisinterface", () -> registerTiles(TardisInterfaceTile::new, HandlesBlocks.TARDISINTERFACEBLOCK.get()));

    private static <T extends TileEntity> TileEntityType<T> registerTiles(Supplier<T> tile, Block... validBlock) {
        TileEntityType<T> type = TileEntityType.Builder.of(tile, validBlock).build(null);
        for(Block block : validBlock) {
            if(block instanceof TileEnabledBlock) {
                ((TileEnabledBlock)block).setTileEntity(type);
            }
        }
        return type;
    }
}
