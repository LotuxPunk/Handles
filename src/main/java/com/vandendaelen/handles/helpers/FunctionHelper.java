package com.vandendaelen.handles.helpers;

import dan200.computercraft.api.lua.LuaException;
import net.tardis.mod.controls.AbstractControl;
import net.tardis.mod.subsystem.Subsystem;
import net.tardis.mod.tileentities.ConsoleTile;

public class FunctionHelper {
    public static Subsystem getSubsystem(ConsoleTile tardis, String subSystemPath) throws LuaException {
        final Subsystem subsystem = tardis.getSubSystems()
                .stream()
                .filter(it -> it.getEntry().getRegistryName().getPath() == subSystemPath)
                .findFirst().orElseThrow(() ->new LuaException("SubSystem " + subSystemPath + " not found"));
        return subsystem;
    }

    public static <T extends AbstractControl> T getTardisControl(ConsoleTile tardis, Class<T> clazz) throws LuaException {
        return tardis.getControl(clazz).orElseThrow(() -> new LuaException(clazz.getName() + " not found"));
    }
}
