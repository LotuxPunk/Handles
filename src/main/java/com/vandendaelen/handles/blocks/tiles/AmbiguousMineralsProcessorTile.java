package com.vandendaelen.handles.blocks.tiles;

import com.vandendaelen.handles.blocks.HandlesBlocks;
import com.vandendaelen.handles.misc.AMPDispenseItemBehavior;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.dispenser.ProxyBlockSource;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;

public class AmbiguousMineralsProcessorTile extends TileEntity implements ITickableTileEntity {
    private static final IDispenseItemBehavior DISPENSE_BEHAVIOR = new AMPDispenseItemBehavior();

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
        if (this.getWorld().getGameTime() % 20 == 0) {
            ProxyBlockSource proxyblocksource = new ProxyBlockSource(this.getWorld(), pos);
            ItemStack itemStack = new ItemStack(Items.EMERALD, 1);
            Direction direction = Direction.UP;
            IInventory iinventory = HopperTileEntity.getInventoryAtPosition(this.getWorld(), pos.offset(direction));
            if (iinventory == null) {
                DISPENSE_BEHAVIOR.dispense(proxyblocksource, itemStack);
            } else {
                HopperTileEntity.putStackInInventoryAllSlots(null, iinventory, itemStack, direction.getOpposite());
            }
        }
    }
}
