package com.vandendaelen.handles.object.block;

import com.vandendaelen.handles.object.tileentity.TileTardisInterfaceCC;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TardisInterfaceCC extends TardisInterfaceBase {
    public TardisInterfaceCC() {
        super();
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileTardisInterfaceCC();
    }
}
