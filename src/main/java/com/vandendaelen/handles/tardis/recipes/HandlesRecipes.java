package com.vandendaelen.handles.tardis.recipes;

import com.vandendaelen.handles.Handles;
import com.vandendaelen.handles.items.HandlesItems;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.tardis.mod.items.TItems;
import net.tardis.mod.recipe.WeldRecipe;

@Mod.EventBusSubscriber(modid = Handles.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HandlesRecipes {

    @SubscribeEvent
    public static void register(FMLCommonSetupEvent event){
        WeldRecipe.WELD_RECIPE.add(new WeldRecipe(HandlesItems.APRIORITRON, true, TItems.CIRCUITS));
    }
}
