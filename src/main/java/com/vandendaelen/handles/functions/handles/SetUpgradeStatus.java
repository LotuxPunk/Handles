package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.FunctionHelper;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.tardis.mod.tileentities.ConsoleTile;
import net.tardis.mod.upgrades.Upgrade;

public class SetUpgradeStatus implements IFunction {
    @Override
    public String getName() {
        return "setSubSystemStatus";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        final String upgradePath = args.getString(0);
        final boolean status = args.getBoolean(1);

        try {
            final Upgrade upgrade = FunctionHelper.getUpgrade(tardis, upgradePath);
            tardis.getLevel().getServer().tell(new TickDelayedTask(1,() -> upgrade.setActivated(status)));
        }
        catch (IllegalArgumentException ignored){
            return MethodResult.of(false);
        }
        return MethodResult.of(true);
    }
}
