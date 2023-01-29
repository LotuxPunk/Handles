package com.vandendaelen.handles.functions;

import com.vandendaelen.handles.config.HandlesConfig;
import com.vandendaelen.handles.functions.handles.*;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.tardis.mod.tileentities.ConsoleTile;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class FunctionsHandler {
    private static HashMap<String, IFunction> functions = new HashMap<>();
    private ConsoleTile tardis;

    public FunctionsHandler(ConsoleTile tardis) {
        this.tardis = tardis;
    }

    public static void init(){
        registerAll(
                new GetArtronBank(),
                new GetCurrentDimension(),
                new GetDestinationDimension(),
                new GetDimensions(),
                new GetTardisDestination(),
                new GetTardisDoors(),
                new GetTardisFacing(),
                new GetTardisLocation(),
                new GetTimeLeft(),
                new SetTardisDestination(),
                new SetDimension(),
                new GetRefuel(),
                new SetRefuel(),
                new SetTardisDoors(),
                new SetTardisFacing(),
                new SetFlight(),
                new GetLifeSigns(),
                new GetHandbrake(),
                new SetHandbrake(),
                new SetSpeed(),
                new GetSpeed(),
                new GetAlarm(),
                new SetAlarm(),
                new SetDestinationAndDimension(),
                new GetSubSystems(),
                new GetSubSystemStatus(),
                new GetSubSystemHealth(),
                new SetSubSystemStatus(),
                new GetUpgrades(),
                new GetUpgradeHealth(),
                new GetUpgradeStatus(),
                new SetUpgradeStatus(),
                new GetLightLevel(),
                new SetLightLevel()
        );
    }

    public static void register(IFunction function){
        functions.put(function.getName(), function);
    }

    public static void registerAll(IFunction... functions){
        for (IFunction function : functions) {
            register(function);
        }
    }

    public static String[] getFunctionsNames(){
        return functions.keySet().toArray(new String[0]);
    }

    public MethodResult run(String functionName, IArguments args) throws LuaException {
        Optional<IFunction> function = Optional.ofNullable(functions.get(functionName));
        if (function.isPresent()){
            for (UUID playerID : tardis.getEmotionHandler().getLoyaltyTrackingCrew()) {
                if (playerID != null) {
                    PlayerEntity player = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(playerID);
                    if (player != null) {
                        tardis.getEmotionHandler().addLoyalty(player, -HandlesConfig.Common.getLoyaltyPenalty(functionName));
                    }
                }
            }
            tardis.getEmotionHandler().addMood(-HandlesConfig.Common.getMoodPenalty(functionName));
            return function.get().run(tardis, args);
        }
        return MethodResult.of();
    }

}
