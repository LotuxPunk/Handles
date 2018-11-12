package com.vandendaelen.handles.object.tileentity.renderer;

import com.vandendaelen.handles.object.block.model.ModelHandles;
import com.vandendaelen.handles.object.tileentity.TileHandles;
import com.vandendaelen.handles.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RendererHandles extends TileEntitySpecialRenderer<TileHandles> {
    public ModelHandles modelHandles = new ModelHandles();
    public static final ResourceLocation TEXTURE =new ResourceLocation(Reference.MODID, "textures/block/handles.png");
    Minecraft mc;

    public RendererHandles() {
        mc = Minecraft.getMinecraft();
    }

    @Override
    public void render(TileHandles te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5, y+1.5, z + 0.5);
        GlStateManager.rotate(180.0F,90.0F, 0.0F, 90.0F);
        mc.getTextureManager().bindTexture(TEXTURE);
        modelHandles.render(null, 0, 0, 0, 0, 0, 0.0625F);
        GlStateManager.popMatrix();
    }
}
