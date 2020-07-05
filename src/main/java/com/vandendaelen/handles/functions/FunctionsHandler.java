package com.vandendaelen.handles.functions;

import com.vandendaelen.handles.functions.handles.*;
import net.tardis.mod.tileentities.ConsoleTile;

import java.util.HashMap;
import java.util.Optional;

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
                new SetTardisDestination(),
                new SetTardisDoors(),
                new SetTardisFacing(),
                new StartFlight()
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
        return functions.entrySet().stream().map(entry -> entry.getKey()).toArray(String[]::new);
    }

    public Object[] run(String functionName, Object[] args){
        Optional<IFunction> function = Optional.ofNullable(functions.get(functionName));
        return function.map(iFunction -> iFunction.run(tardis, args)).orElse(null);
    }
}