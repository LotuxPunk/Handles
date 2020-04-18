package com.vandendaelen.handles.blocks;

import com.vandendaelen.handles.blocks.tiles.TardisInterfaceTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class HandlesBlocks {
    @ObjectHolder("handles:tardisinterface")
    public static TardisInterfaceBlock TARDISINTERFACEBLOCK;

    @ObjectHolder("handles:tardisinterface")
    public static TileEntityType<TardisInterfaceTile> TARDISINTERFACE_TILE;
}
