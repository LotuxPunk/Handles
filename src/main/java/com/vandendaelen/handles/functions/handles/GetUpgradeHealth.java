package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.FunctionHelper;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.tileentities.ConsoleTile;
import net.tardis.mod.upgrades.Upgrade;

public class GetUpgradeHealth implements IFunction {
    @Override
    public String getName() {
        return "getUpgradeHealth";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        final String upgradePath = args.getString(0);
        try {
            final Upgrade upgrade = FunctionHelper.getUpgrade(tardis, upgradePath);
            final int maxHealth = upgrade.getStack().getMaxDamage();
            final int damageValue = upgrade.getStack().getDamageValue();
            return MethodResult.of((maxHealth - damageValue)/(float)maxHealth);
        }
        catch (IllegalArgumentException exception){
            return MethodResult.of(0F);
        }
    }
}
