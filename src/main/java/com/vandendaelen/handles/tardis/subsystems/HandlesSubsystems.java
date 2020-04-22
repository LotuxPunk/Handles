package com.vandendaelen.handles.tardis.subsystems;

import com.vandendaelen.handles.Handles;
import com.vandendaelen.handles.items.HandlesItems;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.tardis.mod.registries.TardisRegistries;
import net.tardis.mod.subsystem.Subsystem;
import net.tardis.mod.subsystem.SubsystemEntry;

@Mod.EventBusSubscriber(modid = Handles.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class HandlesSubsystems {
    public static SubsystemEntry<AprioritronSubsystem> APRIORITRON_SUBSYSTEM;

    @SubscribeEvent
    public static void registerAll(FMLCommonSetupEvent event) {
        TardisRegistries.registerRegisters(() -> {
            APRIORITRON_SUBSYSTEM = register("aprioritron", new SubsystemEntry<AprioritronSubsystem>(AprioritronSubsystem::new, HandlesItems.APRIORITRON));
        });
    }

    public static <T extends Subsystem> SubsystemEntry<T> register(ResourceLocation key, SubsystemEntry<T> system) {
        TardisRegistries.SUBSYSTEM_REGISTRY.register(key, system);
        return system;
    }

    public static <T extends Subsystem> SubsystemEntry<T> register(String key, SubsystemEntry<T> system) {
        return register(new ResourceLocation(Handles.MODID, key), system);
    }
}
