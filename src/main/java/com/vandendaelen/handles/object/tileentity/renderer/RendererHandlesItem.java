package com.vandendaelen.handles.object.tileentity.renderer;

import com.vandendaelen.handles.object.block.model.ModelHandles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;

public class RendererHandlesItem extends TileEntityItemStackRenderer {
    Minecraft mc;
    ModelHandles modelHandles = new ModelHandles();

    public RendererHandlesItem() {
        this.mc = Minecraft.getMinecraft();
    }

    @Override
    public void renderByItem(ItemStack itemStackIn) {
        GlStateManager.pushMatrix();
        mc.getTextureManager().bindTexture(RendererInterface.TEXTURE);
        modelHandles.render(null, 0, 0, 0, 0, 0, 0.0625F);
        GlStateManager.popMatrix();
    }
}
