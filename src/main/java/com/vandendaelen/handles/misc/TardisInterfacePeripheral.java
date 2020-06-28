package com.vandendaelen.handles.misc;

import com.vandendaelen.handles.blocks.tiles.TardisInterfaceTile;
import com.vandendaelen.handles.exceptions.NotATardisException;
import com.vandendaelen.handles.functions.FunctionsHandler;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TardisInterfacePeripheral implements IPeripheral {

    private final TardisInterfaceTile tile;
    private FunctionsHandler handler;

    public TardisInterfacePeripheral(TardisInterfaceTile tile) {
        this.tile = tile;
        try {
            this.handler = new FunctionsHandler(tile.getTardis());
        } catch (NotATardisException e) {
            this.handler = null;
        }
    }

    @Nonnull
    @Override
    public String getType() {
        return "tardisinterface";
    }

    @Nonnull
    @Override
    public String[] getMethodNames() {
        return FunctionsHandler.getFunctionsNames();
    }

    @Nullable
    @Override
    public Object[] callMethod(@Nonnull IComputerAccess iComputerAccess, @Nonnull ILuaContext iLuaContext, int method, @Nonnull Object[] objects) throws LuaException, InterruptedException {
        try{
            if (handler != null && tile.canBeUsed()){
                tile.damageSubsystem();
                return this.handler.run(FunctionsHandler.getFunctionsNames()[method], objects);
            }
            return null;
        }
        catch (Exception e) {
            return null;
        }
    }

    @Nonnull
    @Override
    public Object getTarget() {
        return tile;
    }

    @Override
    public boolean equals(@Nullable IPeripheral other) {
        return this == other || other instanceof TardisInterfacePeripheral && ((TardisInterfacePeripheral) other).tile == tile;
    }
}
