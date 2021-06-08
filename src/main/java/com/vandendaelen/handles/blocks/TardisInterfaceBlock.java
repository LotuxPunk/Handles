package com.vandendaelen.handles.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;

public class TardisInterfaceBlock extends TileEnabledBlock {
    public TardisInterfaceBlock() {
        super(Properties.of(Material.METAL)
                .sound(SoundType.METAL)
                .strength(2.0f)
        );
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction direction = context.getClickedFace();
        Direction horizontalFacing = context.getPlayer().getDirection();
        return super.getStateForPlacement(context)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, horizontalFacing.getOpposite())
                .setValue(BlockStateProperties.UP, direction.getOpposite() == Direction.DOWN ? false : true)
                .setValue(BlockStateProperties.DOWN, direction.getOpposite() == Direction.UP ? false : true);

    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.WATERLOGGED, BlockStateProperties.UP, BlockStateProperties.DOWN);
    }
}
