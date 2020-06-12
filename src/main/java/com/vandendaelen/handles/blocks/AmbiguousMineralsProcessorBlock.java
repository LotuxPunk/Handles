package com.vandendaelen.handles.blocks;

import com.vandendaelen.handles.blocks.tiles.AmbiguousMineralsProcessorTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.Position;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class AmbiguousMineralsProcessorBlock extends Block {
    public static final DirectionProperty FACING = DirectionalBlock.FACING;

    public AmbiguousMineralsProcessorBlock() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(2.0f)
        );
        setRegistryName("ambiguousmineralsprocessor");
    }

    public static IPosition getDispensePosition(IBlockSource coords) {
        Direction direction = Direction.UP;
        double d0 = coords.getX() + 0.7D * (double)direction.getXOffset();
        double d1 = coords.getY() + 0.7D * (double)direction.getYOffset();
        double d2 = coords.getZ() + 0.7D * (double)direction.getZOffset();
        return new Position(d0, d1, d2);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new AmbiguousMineralsProcessorTile();
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getStateForPlacement(context)
                .with(BlockStateProperties.HORIZONTAL_FACING, context.getPlayer()
                        .getHorizontalFacing().getOpposite());

    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
