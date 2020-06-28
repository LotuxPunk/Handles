package com.vandendaelen.handles.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class HandlesConfig {
    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Client CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;



    static {
        Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();

        Pair<Client, ForgeConfigSpec> specClientPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = specClientPair.getRight();
        CLIENT = specClientPair.getLeft();
    }



    public static class Client {
        public final ForgeConfigSpec.BooleanValue discordAdvertising;

        public Client(ForgeConfigSpec.Builder builder) {
            builder.push("Client settings");
            discordAdvertising = builder
                    .comment("Enable LotuxPunk's discord server advertising ?")
                    .define("discordAdvertising", true);
            builder.pop();
        }
        public static boolean getDiscordAdvertising() {
            return CLIENT.discordAdvertising.get();
        }
    }

    public static class Common {
        public Common(ForgeConfigSpec.Builder builder) {
            //builder.push("common");
        }
    }
}
