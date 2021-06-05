package com.vandendaelen.handles.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class HandlesConfig {
    public static final Server SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;
    public static final Client CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;



    static {
        Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Server::new);
        SERVER_SPEC = specPair.getRight();
        SERVER = specPair.getLeft();

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

    public static class Server {
        public final ForgeConfigSpec.IntValue moodPenalty;
        public final ForgeConfigSpec.IntValue loyaltyPenalty;

        public Server(ForgeConfigSpec.Builder builder) {
            builder.push("Common settings");
            moodPenalty = builder
                    .comment("Mood penalty when user use a setter function")
                    .defineInRange("moodPenaltyOnSetter",10,0, Integer.MAX_VALUE);
            loyaltyPenalty = builder
                    .comment("Loyalty penalty when user use a setter function")
                    .defineInRange("loyaltyPenaltyOnSetter", 2,0, Integer.MAX_VALUE);
            builder.pop();
        }

        public static int getLoyaltyPenalty() {
            return SERVER.loyaltyPenalty.get();
        }

        public static int getMoodPenalty() {
            return SERVER.moodPenalty.get();
        }
    }
}
