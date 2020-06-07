package com.vandendaelen.handles.blocks;

import com.vandendaelen.handles.blocks.tiles.AmbiguousMineralsProcessorTile;
import com.vandendaelen.handles.blocks.tiles.TardisInterfaceTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class HandlesBlocks {
    @ObjectHolder("handles:tardisinterface")
    public static TardisInterfaceBlock TARDISINTERFACE_BLOCK;

    @ObjectHolder("handles:tardisinterface")
    public static TileEntityType<TardisInterfaceTile> TARDISINTERFACE_TILE;

    @ObjectHolder("handles:ambiguousmineralsprocessor")
    public static AmbiguousMineralsProcessorBlock AMBIGUOUSMINERALSPROCESSOR_BLOCK;

    @ObjectHolder("handles:ambiguousmineralsprocessor")
    public static TileEntityType<AmbiguousMineralsProcessorTile> AMBIGUOUSMINERALSPROCESSOR_TILE;
}
