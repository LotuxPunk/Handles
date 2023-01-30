package com.vandendaelen.handles.helpers;

import dan200.computercraft.api.lua.LuaException;
import net.tardis.mod.controls.AbstractControl;
import net.tardis.mod.subsystem.Subsystem;
import net.tardis.mod.tileentities.ConsoleTile;
import net.tardis.mod.upgrades.Upgrade;

public class FunctionHelper {
    public static Subsystem getSubsystem(ConsoleTile tardis, String subSystemPath) throws LuaException {
        return tardis.getSubSystems()
                .stream()
                .filter(it -> it.getEntry().getRegistryName().getPath().equals(subSystemPath))
                .findFirst().orElseThrow(() ->new LuaException("SubSystem " + subSystemPath + " not found"));
    }

    public static Upgrade getUpgrade(ConsoleTile tardis, String upgradePath) throws LuaException {
        return tardis.getUpgrades()
                .stream()
                .filter(it -> it.getEntry().getRegistryName().getPath().equals(upgradePath))
                .findFirst().orElseThrow(() ->new LuaException("Upgrade " + upgradePath + " not found"));
    }

    public static <T extends AbstractControl> T getTardisControl(ConsoleTile tardis, Class<T> clazz) throws IllegalArgumentException {
        return tardis.getControl(clazz).orElseThrow(() -> new IllegalArgumentException(clazz.getName() + " not found"));
    }

    public static <T extends Subsystem> T getTardisSubsystem(ConsoleTile tardis, Class<T> clazz) throws IllegalArgumentException {
        return tardis.getSubsystem(clazz).orElseThrow(() -> new IllegalArgumentException(clazz.getName() + " not found"));
    }
}
