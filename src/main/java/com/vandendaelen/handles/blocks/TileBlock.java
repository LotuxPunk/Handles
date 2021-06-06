package com.vandendaelen.handles.blocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class TileBlock extends Block {

    TileEntityType<?> type;

    public TileBlock(Block.Properties prop) {
        super(prop.noOcclusion().isSuffocating((blockState, reader, blockPos) -> false));
    }

    public void setTileEntity(TileEntityType<?> type) {
        this.type = type;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return type != null;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return type.create();
    }

    @Override
    public int getLightBlock(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 0;
    }
}

