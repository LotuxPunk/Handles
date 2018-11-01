package com.vandendaelen.handles.object.block;

import com.vandendaelen.handles.object.tileentity.TileTardisInterfaceOC;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TardisInterfaceOC extends TardisInterfaceBase {
    public TardisInterfaceOC() {
        super();
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileTardisInterfaceOC();
    }
}
