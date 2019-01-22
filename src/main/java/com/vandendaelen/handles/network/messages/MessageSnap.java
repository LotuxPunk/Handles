package com.vandendaelen.handles.network.messages;

import com.vandendaelen.handles.init.SoundHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.TardisHelper;

public class MessageSnap implements IMessage {

    public MessageSnap() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<MessageSnap,IMessage> {
        @Override
        public IMessage onMessage(MessageSnap message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                TileEntityTardis tardis = (TileEntityTardis) FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(TDimensions.TARDIS_ID).getTileEntity(TardisHelper.getTardis(ctx.getServerHandler().player.getUniqueID()));
                if (tardis != null && !tardis.isInFlight()){
                    EntityPlayerMP player = ctx.getServerHandler().player;
                    //If the exterior distance < 30 (!= TDimension) and if the interior distance is below 10 (==TDimension)
                    if (ctx.getServerHandler().player.dimension != TDimensions.TARDIS_ID && tardis.getLocation().getDistance((int)player.posX,(int)player.posY, (int)player.posZ) > 30 || ctx.getServerHandler().player.dimension == TDimensions.TARDIS_ID && tardis.getDoor().getDistance(player) > 10) return;

                    //Then, we can open the doors
                    ctx.getServerHandler().player.world.playSound(null, player.getPosition(), SoundHandler.snap, SoundCategory.AMBIENT, 1F, 1F);
                    TileEntity door = player.server.worlds[tardis.dimension].getTileEntity(tardis.getLocation().up());
                    ((TileEntityDoor)door).toggleLockedNoKey(player);
                    tardis.getDoor().setOpen(!((TileEntityDoor)door).isLocked);

                    if (((TileEntityDoor)door).isLocked)
                        ctx.getServerHandler().player.world.playSound(null, player.getPosition(), TSounds.door_closed, SoundCategory.BLOCKS, 0.2F, 1F);
                    else
                        ctx.getServerHandler().player.world.playSound(null, player.getPosition(), TSounds.door_open, SoundCategory.BLOCKS, 0.2F, 1F);

                }
            });
            return null;
        }
    }
}
