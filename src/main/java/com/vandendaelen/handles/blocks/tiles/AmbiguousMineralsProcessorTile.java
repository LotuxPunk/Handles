package com.vandendaelen.handles.blocks.tiles;

import com.vandendaelen.handles.blocks.HandlesBlocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class AmbiguousMineralsProcessorTile extends TileEntity implements ITickableTileEntity {
    public AmbiguousMineralsProcessorTile() {
        super(HandlesBlocks.AMBIGUOUSMINERALSPROCESSOR_TILE);
    }

    @Override
    public void read(CompoundNBT tag) {
        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        return super.write(tag);
    }

    @Override
    public void tick() {
        System.out.println("Hello from AMP !");
    }
}
