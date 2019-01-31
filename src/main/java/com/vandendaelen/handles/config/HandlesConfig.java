package com.vandendaelen.handles.config;

import com.vandendaelen.handles.utils.Reference;
import net.minecraftforge.common.config.Config;

@Config(modid = Reference.MODID,name = Reference.MOD_NAME)
public class HandlesConfig {
    public static final Drop DROP = new Drop();
    public static final Properties PROPERTIES = new Properties();
    public static final Gameplay GAMEPLAY = new Gameplay();

    public static class Drop{
        @Config.LangKey("handles.config.drop")
        @Config.Comment("Chance to get a Handles block when a cyberman is killed. 1/X [Default : 1/10]")
        public int chanceHandlesDrop = 10;
    }

    public static class Properties{
        @Config.LangKey("handles.config.updatechecker")
        @Config.Comment("Enable Update Checker ?")
        public boolean updateChecker = true;
    }

    public static class Gameplay{
        @Config.LangKey("handles.config.subsystem")
        @Config.Comment("Does TARDIS LINK need to have an Aprioritron installed in your TARDIS to work ?")
        public boolean subsystem = true;
    }
}
