package com.vandendaelen.handles.functions;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import com.vandendaelen.handles.config.HandlesConfig;
import com.vandendaelen.handles.functions.handles.GetAlarm;
import com.vandendaelen.handles.functions.handles.GetArtronBank;
import com.vandendaelen.handles.functions.handles.GetCurrentDimension;
import com.vandendaelen.handles.functions.handles.GetDimensions;
import com.vandendaelen.handles.functions.handles.GetHandbrake;
import com.vandendaelen.handles.functions.handles.GetLifeSigns;
import com.vandendaelen.handles.functions.handles.GetSpeed;
import com.vandendaelen.handles.functions.handles.GetTardisDestination;
import com.vandendaelen.handles.functions.handles.GetTardisDoors;
import com.vandendaelen.handles.functions.handles.GetTardisFacing;
import com.vandendaelen.handles.functions.handles.GetTardisLocation;
import com.vandendaelen.handles.functions.handles.GetTimeLeft;
import com.vandendaelen.handles.functions.handles.SetAlarm;
import com.vandendaelen.handles.functions.handles.SetDestinationAndDimension;
import com.vandendaelen.handles.functions.handles.SetDimension;
import com.vandendaelen.handles.functions.handles.SetFlight;
import com.vandendaelen.handles.functions.handles.SetHandbrake;
import com.vandendaelen.handles.functions.handles.SetRefuel;
import com.vandendaelen.handles.functions.handles.SetSpeed;
import com.vandendaelen.handles.functions.handles.SetTardisDestination;
import com.vandendaelen.handles.functions.handles.SetTardisDoors;
import com.vandendaelen.handles.functions.handles.SetTardisFacing;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.tardis.mod.tileentities.ConsoleTile;

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
                new GetDimensions(),
                new GetTardisDestination(),
                new GetTardisDoors(),
                new GetTardisFacing(),
                new GetTardisLocation(),
                new GetTimeLeft(),
                new SetTardisDestination(),
                new SetDimension(),
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
                new SetDestinationAndDimension()
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
            if (function.get().impactMoodAndLoyalty()){
                //Affect Loyalty values for all players being tracked by the Tardis' emotional handler
                for (UUID playerID : tardis.getEmotionHandler().getLoyaltyTrackingCrew()) {
                    if (playerID != null) {
                        PlayerEntity player = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(playerID);
                        if (player != null) {
                            tardis.getEmotionHandler().addLoyalty(player, -HandlesConfig.Server.getLoyaltyPenalty());
                        }
                    }
                }
                tardis.getEmotionHandler().addMood(-HandlesConfig.Server.getMoodPenalty());
            }
            return function.get().run(tardis, args);
        }
        return MethodResult.of();
    }

}
