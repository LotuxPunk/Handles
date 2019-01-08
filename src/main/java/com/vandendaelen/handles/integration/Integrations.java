package com.vandendaelen.handles.integration;

import com.vandendaelen.handles.utils.Reference;
import net.minecraftforge.fml.common.Loader;

public class Integrations {
    public static boolean isOCLoaded(){
        return Loader.isModLoaded(Reference.Dependencies.OC);
    }
    public static boolean isCCLoaded(){
        return Loader.isModLoaded(Reference.Dependencies.CC);
    }
}
