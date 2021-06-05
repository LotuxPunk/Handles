package com.vandendaelen.handles.blocks.tiles;

import com.vandendaelen.handles.blocks.HandlesTiles;
import com.vandendaelen.handles.exceptions.NoSubsystemException;
import com.vandendaelen.handles.exceptions.NotATardisException;
import com.vandendaelen.handles.functions.FunctionsHandler;
import com.vandendaelen.handles.misc.TardisInterfacePeripheral;
import com.vandendaelen.handles.tardis.subsystems.AprioritronSubsystem;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.tardis.mod.helper.WorldHelper;
import net.tardis.mod.tileentities.ConsoleTile;
import net.tardis.mod.world.dimensions.TDimensions;

public class TardisInterfaceTile extends TileEntity implements IPeripheralProvider{
	private LazyOptional<IPeripheral> peripheral;
	
    public TardisInterfaceTile() {
        super(HandlesTiles.TARDISINTERFACE_TILE.get());
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        super.read(state, tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        return super.write(tag);
    }

    public ConsoleTile getTardis() throws NotATardisException {
        if(WorldHelper.areDimensionTypesSame(world, TDimensions.DimensionTypes.TARDIS_TYPE)) throw new NotATardisException();
        ConsoleTile tardis = (ConsoleTile) world.loadedTileEntityList.stream().filter(tileEntity -> tileEntity instanceof ConsoleTile).findFirst().get();
        return tardis;
    }

    public void damageSubsystem() throws NotATardisException {
        this.getTardis().getSubsystem(AprioritronSubsystem.class).ifPresent(aprioritronSubsystem -> aprioritronSubsystem.damage(null, 1));
    }

    public boolean canBeUsed() throws NotATardisException, NoSubsystemException {
        return this.getTardis().getSubsystem(AprioritronSubsystem.class).orElseThrow(NoSubsystemException::new).canBeUsed();
    }

	@Override
	public LazyOptional<IPeripheral> getPeripheral(World world, BlockPos pos, Direction side) {
		 if(peripheral == null)
         	peripheral = LazyOptional.of(() -> {
				try {
					return new TardisInterfacePeripheral(this, new FunctionsHandler(getTardis()));
				} catch (NotATardisException e) {
					e.printStackTrace();
					return null;
				}
			});
         return peripheral.cast();
	}

}
