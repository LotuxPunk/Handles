package com.vandendaelen.handles.object.tileentity.renderer;

import com.vandendaelen.handles.object.block.model.ModelTardisInterface;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;

public class RendererInterfaceItem extends TileEntityItemStackRenderer {

    Minecraft mc;
    ModelTardisInterface modelTardisInterface = new ModelTardisInterface();

    public RendererInterfaceItem() {
        this.mc = Minecraft.getMinecraft();
    }

    @Override
    public void renderByItem(ItemStack itemStackIn) {
        GlStateManager.pushMatrix();
        mc.getTextureManager().bindTexture(RendererInterface.TEXTURE);
        GlStateManager.translate(0.5,-0.75,0);
        GlStateManager.rotate(0,0.0F, 0.0F, 0.0F);
        modelTardisInterface.render(null, 0, 0, 0, 0, 0, 0.0625F);
        GlStateManager.popMatrix();
    }
}
