package com.vandendaelen.handles.integration;

import com.vandendaelen.handles.utils.Reference;
import net.minecraftforge.fml.common.Loader;

public class OCIntegration{
    public static boolean isModLoaded(){
        return Loader.isModLoaded(Reference.Dependencies.OC);
    }
}
