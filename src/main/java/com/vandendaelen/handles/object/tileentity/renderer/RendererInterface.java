package com.vandendaelen.handles.object.tileentity.renderer;

import com.vandendaelen.handles.object.block.model.ModelTardisInterface;
import com.vandendaelen.handles.object.tileentity.TileTardisInterface;
import com.vandendaelen.handles.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RendererInterface extends TileEntitySpecialRenderer<TileTardisInterface> {

    public ModelTardisInterface modelTardisInterface = new ModelTardisInterface();
    public static final ResourceLocation TEXTURE =new ResourceLocation(Reference.MODID, "textures/block/tardis_interface.png");
    Minecraft mc;

    public RendererInterface() {
        mc = Minecraft.getMinecraft();
    }

    @Override
    public void render(TileTardisInterface te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5, y+1.5, z + 0.5);
        GlStateManager.rotate(180.0F,90.0F, 0.0F, 90.0F);
        mc.getTextureManager().bindTexture(TEXTURE);
        modelTardisInterface.render(null, 0, 0, 0, 0, 0, 0.0625F);
        GlStateManager.popMatrix();
    }
}
