package com.vandendaelen.handles.object.tileentity.renderer;

import com.vandendaelen.handles.object.block.TardisInterfaceBase;
import com.vandendaelen.handles.object.block.model.ModelTardisInterface;
import com.vandendaelen.handles.object.tileentity.TileTardisInterfaceBase;
import com.vandendaelen.handles.utils.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.util.common.helpers.Helper;

public class RendererInterface extends TileEntitySpecialRenderer<TileTardisInterfaceBase> {

    public ModelTardisInterface modelTardisInterface = new ModelTardisInterface();
    public static final ResourceLocation TEXTURE =new ResourceLocation(Reference.MODID, "textures/block/tardis_interface.png");
    Minecraft mc;

    public RendererInterface() {
        mc = Minecraft.getMinecraft();
    }

    @Override
    public void render(TileTardisInterfaceBase te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5, y+1.5, z + 0.5);
        GlStateManager.rotate(180.0F,90.0F, 0.0F, 90.0F);
        IBlockState state = te.getWorld().getBlockState(te.getPos());
        if(state.getBlock() instanceof TardisInterfaceBase) {
            GlStateManager.rotate(Helper.getAngleFromFacing(state.getValue(TardisInterfaceBase.FACING)), 0, 1, 0);
        }
        mc.getTextureManager().bindTexture(TEXTURE);
        modelTardisInterface.render(null, 0, 0, 0, 0, 0, 0.0625F);
        GlStateManager.popMatrix();
    }
}
