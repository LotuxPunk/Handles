package com.vandendaelen.handles.object.block;

import com.vandendaelen.handles.object.tileentity.TileTardisInterfaceCC;
import com.vandendaelen.handles.utils.BlockNames;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TardisInterfaceCC extends TardisInterfaceBase {
    public TardisInterfaceCC() {
        super();
        this.setRegistryName(new ResourceLocation(BlockNames.TARDIS_LINK_CC));
        this.setUnlocalizedName(BlockNames.TARDIS_LINK_CC);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileTardisInterfaceCC();
    }
}
