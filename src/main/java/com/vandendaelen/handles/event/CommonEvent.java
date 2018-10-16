package com.vandendaelen.handles.event;

import com.vandendaelen.handles.utils.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class CommonEvent {
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e){
        EntityPlayer player = e.player;
        if (!player.world.isRemote){
            ForgeVersion.CheckResult version = ForgeVersion.getResult(Loader.instance().activeModContainer());
            if (version.status == ForgeVersion.Status.OUTDATED){
                TextComponentString url = new TextComponentString(TextFormatting.DARK_AQUA + TextFormatting.BOLD.toString() + "UPDATE");
                url.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft.curseforge.com/projects/handles"));
                url.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("Open link")));

                player.sendMessage(new TextComponentString(TextFormatting.DARK_RED + "[Handles] : ").appendSibling(url));
                String changes = String.valueOf(version.changes).replace("{" + version.target + "=", "").replace("}", "");
                player.sendMessage(new TextComponentString(TextFormatting.DARK_AQUA + "Changelog: " + TextFormatting.AQUA + changes));
            }
        }
    }
}
