package com.vandendaelen.handles.setup;

import net.minecraft.world.World;

public interface IProxy {
    void init();
    World getClientWorld();
}
