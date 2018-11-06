package com.vandendaelen.handles.utils;

public class Reference {
    public static final String MODID = "handles";
    public static final String MOD_NAME = "Handles";
    public static final String DEP = "after:tardis; required-after:forge@[14.23.4.2768,)";
    public static final String CLIENT_PROXY_CLASS = "com.vandendaelen.handles.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "com.vandendaelen.handles.proxy.ServerProxy";
    public static final String UPDATE_JSON = "https://raw.githubusercontent.com/LotuxPunk/Handles/master/update.json";

    public static class Dependencies{
        public static final String CC = "computercraft";
        public static final String OC = "opencomputers";
    }


    public static class VERSION{
        public static final String MCVERSION = "1.12.2";
        public static final String MAJORMOD = "1";
        public static final String MAJORAPI = "2";
        public static final String MINOR = "3";
        public static final String PATCH = "0";
        public static final String VERSION = MCVERSION+"-"+MAJORMOD+"."+MAJORAPI+"."+MINOR+"."+PATCH;
    }
}
