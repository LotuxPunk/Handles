package com.vandendaelen.handles.misc;

import java.util.Arrays;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.vandendaelen.handles.blocks.tiles.TardisInterfaceTile;
import com.vandendaelen.handles.exceptions.NoUpgradeException;
import com.vandendaelen.handles.functions.FunctionsHandler;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.ILuaCallback;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IDynamicPeripheral;
import dan200.computercraft.api.peripheral.IPeripheral;

public class TardisInterfacePeripheral implements IDynamicPeripheral {
    private final TardisInterfaceTile tile;
    private final FunctionsHandler handler;

    public TardisInterfacePeripheral(TardisInterfaceTile tile, FunctionsHandler functionsHandler) {
        this.tile = tile;
        this.handler = functionsHandler;
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
    public MethodResult callMethod(IComputerAccess computer, ILuaContext context, int method, IArguments arguments)
            throws LuaException {
        try{
            if (handler != null && tile.canBeUsed()){
                tile.damageUpgrade();
                return this.handler.run(FunctionsHandler.getFunctionsNames()[method], arguments);
            }
            else {
                throw new LuaException(new NoUpgradeException().getMessage());
            }
        }
        catch (Exception e) {
            throw new LuaException(new NoUpgradeException().getMessage());
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
    
    /**
     * Copy of dan200.computercraft.core.asm.TaskCallback as suggested on https://github.com/SquidDev-CC/CC-Tweaked/discussions/728 due to there not being a
     * method via the API to do this. Ideally eventually it will be replaced by a method on ILuaContext that we can just call
     *
     * https://github.com/SquidDev-CC/CC-Tweaked/blob/mc-1.16.x/LICENSE
     */
    public static class HandlesTaskCallback implements ILuaCallback {

        private final MethodResult pull = MethodResult.pullEvent("task_complete", this);
        private final long task;

        private HandlesTaskCallback(long task) {
            this.task = task;
        }

        @Nonnull
        @Override
        public MethodResult resume(Object[] response) throws LuaException {
            if (response.length >= 3 && response[1] instanceof Number && response[2] instanceof Boolean) {
                if (((Number) response[1]).longValue() != this.task) {
                    return this.pull;
                } else if ((Boolean) response[2]) {
                    return MethodResult.of(Arrays.copyOfRange(response, 3, response.length));
                } else if (response.length >= 4 && response[3] instanceof String) {
                    throw new LuaException((String) response[3]);
                }
                throw new LuaException("error");
            }
            return this.pull;
        }
    }

}
