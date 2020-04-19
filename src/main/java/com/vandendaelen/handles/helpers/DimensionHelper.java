package com.vandendaelen.handles.helpers;

import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DimensionHelper {
    public static DimensionType getDimension(int id){
        ArrayList<ServerWorld> worlds = new ArrayList<>();
        ServerLifecycleHooks.getCurrentServer().getWorlds().iterator().forEachRemaining(worlds::add);
        return worlds.get(id).getDimension().getType();
    }

    public static ArrayList<String> getPrettyDimensionList(){
        ArrayList<ServerWorld> worlds = new ArrayList<>();
        ServerLifecycleHooks.getCurrentServer().getWorlds().iterator().forEachRemaining(worlds::add);
        return (ArrayList<String>) worlds.stream().map(w -> MessageFormat.format("{0} - {1}", worlds.indexOf(w), w.getDimension().getType().getRegistryName().getNamespace())).collect(Collectors.toList());
    }
}
