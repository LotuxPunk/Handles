package com.vandendaelen.handles.blocks.tiles;

import static dan200.computercraft.shared.Capabilities.CAPABILITY_PERIPHERAL;

import com.vandendaelen.handles.blocks.HandlesTiles;
import com.vandendaelen.handles.exceptions.NoUpgradeException;
import com.vandendaelen.handles.exceptions.NotATardisException;
import com.vandendaelen.handles.functions.FunctionsHandler;
import com.vandendaelen.handles.misc.TardisInterfacePeripheral;
import com.vandendaelen.handles.tardis.upgrades.AprioritronUpgrade;

import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.tardis.mod.tileentities.ConsoleTile;
import net.tardis.mod.upgrades.Upgrade.DamageType;
import net.tardis.mod.world.dimensions.TDimensions;

public class TardisInterfaceTile extends TileEntity{
    private LazyOptional<IPeripheral> peripheral;
    private FunctionsHandler functionsHandler;

    public TardisInterfaceTile() {
        super(HandlesTiles.TARDISINTERFACE_TILE.get());
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        super.load(state, tag);
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        return super.save(tag);
    }

    public ConsoleTile getTardis() throws NotATardisException {
        DimensionType type = level.getServer().registryAccess().dimensionTypes().get(TDimensions.DimensionTypes.TARDIS_TYPE);
        if(!level.dimensionType().equalTo(type))
            throw new NotATardisException();
        ConsoleTile tardis = (ConsoleTile) level.blockEntityList.stream().filter(tileEntity -> tileEntity instanceof ConsoleTile).findFirst().get();
        return tardis;
    }

    public void damageUpgrade() throws NotATardisException {
        this.getTardis().getUpgrade(AprioritronUpgrade.class).ifPresent(aprioritron -> aprioritron.damage(1, DamageType.ITEM, null));
    }

    public boolean canBeUsed() throws NotATardisException, NoUpgradeException {
        AprioritronUpgrade upgrade = this.getTardis().getUpgrade(AprioritronUpgrade.class).orElseThrow(NoUpgradeException::new);
        return upgrade.isUsable() && upgrade.isActivated();
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if(cap == CAPABILITY_PERIPHERAL){
            if(peripheral == null) {
                peripheral = LazyOptional.of(() -> {
                    try {
                        this.functionsHandler = new FunctionsHandler(getTardis());
                    } catch (NotATardisException e) {
                        e.printStackTrace();
                    }
                    return new TardisInterfacePeripheral(this);
                });
            }
            return peripheral.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.getBlockPos(), -1, this.serializeNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.deserializeNBT(pkt.getTag());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        super.handleUpdateTag(state, tag);
        this.deserializeNBT(tag);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.serializeNBT();
    }

    public FunctionsHandler getFunctionsHandler() {
        return this.functionsHandler;
    }

}
