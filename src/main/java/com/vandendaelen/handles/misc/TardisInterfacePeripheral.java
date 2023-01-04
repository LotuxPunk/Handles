package com.vandendaelen.handles.misc;

import com.vandendaelen.handles.Handles;
import com.vandendaelen.handles.blocks.tiles.TardisInterfaceTile;
import com.vandendaelen.handles.config.HandlesConfig;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class TardisInterfacePeripheral implements IDynamicPeripheral {
    private final TardisInterfaceTile tile;
    private final Set<IComputerAccess> connectedComputers = new LinkedHashSet<>();

    public TardisInterfacePeripheral(TardisInterfaceTile tile) {
        this.tile = tile;
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
            if (this.tile.getFunctionsHandler() != null && tile.canBeUsed()){
                final String functionName = FunctionsHandler.getFunctionsNames()[method];
                tile.damageUpgrade(HandlesConfig.Common.getDamage(functionName));
                return this.tile.getFunctionsHandler().run(functionName, arguments);
            }
            else {
                throw new LuaException(new NoUpgradeException().getMessage());
            }
        }
        catch (Exception e) {
            throw new LuaException(e.getMessage());
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

    public void queueEvent(String eventName, Object... var2) {
        connectedComputers.forEach(computer -> computer.queueEvent(eventName, var2));
    }

    @Override
    public void attach(@Nonnull IComputerAccess computer) {
        IDynamicPeripheral.super.attach(computer);
        connectedComputers.add(computer);
    }

    @Override
    public void detach(@Nonnull IComputerAccess computer) {
        IDynamicPeripheral.super.detach(computer);
        connectedComputers.remove(computer);
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
