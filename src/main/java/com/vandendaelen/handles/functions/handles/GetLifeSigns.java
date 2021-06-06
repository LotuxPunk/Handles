package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.MethodResult;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerWorld;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetLifeSigns implements IFunction {
    @Override
    public String getName() {
        return "getLifeSigns";
    }

    @Override
    public boolean impactMoodAndLoyalty() {
        return false;
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) {
        return MethodResult.of(((ServerWorld) tardis.getLevel()).getEntities().filter(entity -> entity instanceof MobEntity || entity instanceof ServerPlayerEntity).count());
    }
}
