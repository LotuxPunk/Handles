package com.vandendaelen.handles.helpers;

import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.helper.Helper;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DimensionHelper {
    public static DimensionType getDimension(int id){
        return getTardisableDimensionList().get(id);
    }

    public static ArrayList<String> getPrettyDimensionList(){
        ArrayList<DimensionType> dimensions = getTardisableDimensionList();
        return (ArrayList<String>) dimensions.stream().map(dim -> MessageFormat.format("{0} - {1}", dimensions.indexOf(dim), dim.getRegistryName().toString())).collect(Collectors.toList());
    }

    public static ArrayList<DimensionType> getTardisableDimensionList(){
        return (ArrayList<DimensionType>) DimensionManager.getRegistry().stream().filter(Helper::canTravelToDimension).collect(Collectors.toList());
    }
}
