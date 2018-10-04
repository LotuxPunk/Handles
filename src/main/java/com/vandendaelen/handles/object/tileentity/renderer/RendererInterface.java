package com.vandendaelen.handles.object.tileentity.renderer;

import com.vandendaelen.handles.Handles;
import com.vandendaelen.handles.object.block.TardisInterface;
import com.vandendaelen.handles.object.block.model.ModelTardisInterface;
import com.vandendaelen.handles.object.tileentity.TileTardisInterface;
import com.vandendaelen.handles.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RendererInterface extends TileEntitySpecialRenderer<TileTardisInterface> {

    public ModelTardisInterface modelTardisInterface = new ModelTardisInterface();
    public static final ResourceLocation TEXTURE =new ResourceLocation(Reference.MODID, "textures/interface/tardis_interface.png");
    Minecraft mc;

    public RendererInterface() {
        mc = Minecraft.getMinecraft();
    }

    @Override
    public void render(TileTardisInterface te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        mc.getTextureManager().bindTexture(TEXTURE);
        modelTardisInterface.render(null, 0, 0, 0, 0, 0, 0.0625F);
        GlStateManager.popMatrix();
    }
}
