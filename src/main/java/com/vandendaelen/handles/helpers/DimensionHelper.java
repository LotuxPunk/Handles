package com.vandendaelen.handles.helpers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.tardis.mod.helper.WorldHelper;

public class DimensionHelper {

    public static RegistryKey<World> getDimension(int id){
        return getTardisableDimensionList().get(id);
    }

    public static ArrayList<String> getPrettyDimensionList(){
        ArrayList<RegistryKey<World>> dimensions = getTardisableDimensionList();
        return (ArrayList<String>) dimensions.stream().map(dim -> MessageFormat.format("{0} - {1}", getDimensionId(dim), dim.location().toString())).collect(Collectors.toList());
    }

    public static ArrayList<RegistryKey<World>> getTardisableDimensionList(){
        ArrayList<RegistryKey<World>> list = new ArrayList<>();
        for (World world : ServerLifecycleHooks.getCurrentServer().getAllLevels()) {
            if (WorldHelper.canTravelToDimension(world)) {
                list.add(world.dimension());
            }
        }
        return list;
    }

    public static int getDimensionId(RegistryKey<World> dim){
        ArrayList<RegistryKey<World>> dimensions = getTardisableDimensionList();
        return dimensions.indexOf(dim);
    }
}
