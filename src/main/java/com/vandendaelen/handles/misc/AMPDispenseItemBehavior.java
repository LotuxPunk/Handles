package com.vandendaelen.handles.misc;

import com.vandendaelen.handles.blocks.AmbiguousMineralsProcessorBlock;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

public class AMPDispenseItemBehavior implements IDispenseItemBehavior {

    @Override
    public ItemStack dispense(IBlockSource p_dispense_1_, ItemStack p_dispense_2_) {
        ItemStack itemstack = this.dispenseStack(p_dispense_1_, p_dispense_2_);
        return itemstack;
    }

    protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
        Direction direction = Direction.UP;
        IPosition iposition = AmbiguousMineralsProcessorBlock.getDispensePosition(source);
        ItemStack itemstack = stack.split(1);
        doDispense(source.getWorld(), itemstack, 6, direction, iposition);
        return stack;
    }

    public static void doDispense(World worldIn, ItemStack stack, int speed, Direction facing, IPosition position) {
        double d0 = position.getX();
        double d1 = position.getY();
        double d2 = position.getZ();
        if (facing.getAxis() == Direction.Axis.Y) {
            d1 = d1 - 0.125D;
        } else {
            d1 = d1 - 0.15625D;
        }

        ItemEntity itementity = new ItemEntity(worldIn, d0, d1, d2, stack);
        double d3 = worldIn.rand.nextDouble() * 0.1D + 0.2D;
        itementity.setMotion(worldIn.rand.nextGaussian() * (double)0.0075F * (double)speed + (double)facing.getXOffset() * d3, worldIn.rand.nextGaussian() * (double)0.0075F * (double)speed + (double)0.2F, worldIn.rand.nextGaussian() * (double)0.0075F * (double)speed + (double)facing.getZOffset() * d3);
        worldIn.addEntity(itementity);
    }
}
