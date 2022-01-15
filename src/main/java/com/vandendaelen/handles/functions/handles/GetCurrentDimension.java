package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.DimensionHelper;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.MethodResult;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetCurrentDimension implements IFunction {
    @Override
    public String getName() {
        return "getCurrentDimension";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return false;
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) {
        final RegistryKey<World> currentDimension = tardis.getCurrentDimension();
        return MethodResult.of(DimensionHelper.getDimensionId(tardis.getCurrentDimension()), currentDimension.location().getPath());
    }
}
