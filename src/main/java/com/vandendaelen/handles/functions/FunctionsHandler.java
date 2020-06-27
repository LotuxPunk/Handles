package com.vandendaelen.handles.functions;

import net.tardis.mod.tileentities.ConsoleTile;

import java.util.HashMap;
import java.util.Optional;

public class FunctionsHandler {
    private HashMap<String, IFunction> functions = new HashMap<>();
    private ConsoleTile tardis;

    public FunctionsHandler(ConsoleTile tardis) {
        this.tardis = tardis;
    }

    private void init(){

    }

    public void register(IFunction function){
        functions.put(function.getName(), function);
    }

    public void registerAll(IFunction... functions){
        for (IFunction function : functions) {
            register(function);
        }
    }

    public Object[] run(String functionName, Object[] args){
        Optional<IFunction> function = Optional.ofNullable(functions.get(functionName));
        return function.map(iFunction -> iFunction.run(tardis, args)).orElse(null);
    }
}
