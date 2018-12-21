package com.vandendaelen.handles.object.block;

import com.vandendaelen.handles.object.tileentity.TileTardisInterfaceOC;
import com.vandendaelen.handles.utils.BlockNames;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TardisInterfaceOC extends TardisInterfaceBase {
    public TardisInterfaceOC() {
        super();
        this.setRegistryName(new ResourceLocation(BlockNames.TARDIS_LINK_OC));
        this.setUnlocalizedName(BlockNames.TARDIS_LINK_OC);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileTardisInterfaceOC();
    }
}
