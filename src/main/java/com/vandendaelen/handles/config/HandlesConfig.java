package com.vandendaelen.handles.config;

import com.vandendaelen.handles.functions.FunctionsHandler;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashMap;

public class HandlesConfig {
    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_CONFIG;
    public static final Client CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;



    static {
        Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_CONFIG = specPair.getRight();
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
                    .translation("config.handles.client.discord_advertising")
                    .comment("Enable LotuxPunk's discord server advertising ?")
                    .define("discordAdvertising", true);
            builder.pop();
        }
        public static boolean getDiscordAdvertising() {
            return CLIENT.discordAdvertising.get();
        }
    }

    public static class Common {
        public final HashMap<String, ForgeConfigSpec.IntValue> moodPenalties = new HashMap<>();
        public final HashMap<String, ForgeConfigSpec.IntValue> loyaltyPenalties = new HashMap<>();
        public final HashMap<String, ForgeConfigSpec.IntValue> damageAmount = new HashMap<>();

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("functions");

            Arrays.stream(FunctionsHandler.getFunctionsNames()).forEach(functionName -> {
                boolean isGetFunction = functionName.startsWith("get") || functionName.startsWith("is");

                moodPenalties.put(functionName, builder
                        .translation("config.handles.server.mood_penalty." + functionName)
                        .comment("Mood penalty when user use " + functionName)
                        .defineInRange(functionName + "MoodPenalty",isGetFunction ? 0 : 2,0, Integer.MAX_VALUE));

                loyaltyPenalties.put(functionName, builder
                        .translation("config.handles.server.loyalty_penalty." + functionName)
                        .comment("Loyalty penalty when user use " + functionName)
                        .defineInRange(functionName + "LoyaltyPenalty",isGetFunction ? 0 : 10,0, Integer.MAX_VALUE));

                damageAmount.put(functionName, builder
                        .translation("config.handles.server.damage." + functionName)
                        .comment("Aprioritron damage when user use " + functionName)
                        .defineInRange(functionName + "Damage",isGetFunction ? 0 : 1,0, Integer.MAX_VALUE));
            });
            builder.pop();
            builder.build();
        }

        public static int getLoyaltyPenalty(String functionName) {
            return COMMON.loyaltyPenalties.get(functionName).get();
        }

        public static int getMoodPenalty(String functionName) {
            return COMMON.moodPenalties.get(functionName).get();
        }
        public static int getDamage(String functionName) {
            return COMMON.damageAmount.get(functionName).get();
        }
    }
}
