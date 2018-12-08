package com.vandendaelen.handles.object.tileentity.renderer;

import com.vandendaelen.handles.object.block.Handles;
import com.vandendaelen.handles.object.block.model.ModelHandles;
import com.vandendaelen.handles.object.block.model.ModelSantaHandles;
import com.vandendaelen.handles.object.tileentity.TileHandles;
import com.vandendaelen.handles.utils.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.util.common.helpers.Helper;

import java.sql.Date;
import java.time.Instant;
import java.util.Calendar;

public class RendererHandles extends TileEntitySpecialRenderer<TileHandles> {
    public ModelHandles modelHandles = new ModelHandles();
    public ModelSantaHandles modelSantaHandles = new ModelSantaHandles();
    public static final ResourceLocation TEXTURE =new ResourceLocation(Reference.MODID, "textures/block/handles.png");
    public static final ResourceLocation TEXTURE_SANTA =new ResourceLocation(Reference.MODID, "textures/block/santa_handles.png");

    Minecraft mc;

    public RendererHandles() {
        mc = Minecraft.getMinecraft();
    }

    @Override
    public void render(TileHandles te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5, y+1.5, z + 0.5);
        GlStateManager.rotate(180.0F,90.0F, 0.0F, 90.0F);
        IBlockState state = te.getWorld().getBlockState(te.getPos());
        if(state.getBlock() instanceof Handles) {
            GlStateManager.rotate(Helper.getAngleFromFacing(state.getValue(Handles.FACING)), 0, 1, 0);
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(Instant.now()));
        int week = cal.get(Calendar.WEEK_OF_YEAR);

        if (week >= 49){
            mc.getTextureManager().bindTexture(TEXTURE_SANTA);
            modelSantaHandles.render(null, 0, 0, 0, 0, 0, 0.0625F);
        }
        else{
            mc.getTextureManager().bindTexture(TEXTURE);
            modelHandles.render(null, 0, 0, 0, 0, 0, 0.0625F);
        }
        GlStateManager.popMatrix();
    }
}
