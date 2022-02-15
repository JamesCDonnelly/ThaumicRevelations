package thaumrev.client.renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.client.lib.UtilsFX;

import org.lwjgl.opengl.GL11;

import thaumrev.client.models.ModelKnowledgeReprocessor;
import thaumrev.tiles.TileKnowledgeReprocessor;

public class TileKnowledgeReprocessorRenderer extends TileEntitySpecialRenderer {
  public ModelKnowledgeReprocessor reprocessorModel = new ModelKnowledgeReprocessor();

  public void renderTileEntityAt(TileKnowledgeReprocessor reprocessor, double x, double y, double z, float arg4) {
    GL11.glPushMatrix();
    GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 1.5F);
    // GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
    UtilsFX.bindTexture("thaumrev", "textures/models/reprocessor.png");
    this.reprocessorModel.render(0.0625F);
    // GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    // GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
    GL11.glPopMatrix();
  }

  @Override
  public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float arg4) {
    this.renderTileEntityAt((TileKnowledgeReprocessor) tile, x, y, z, arg4);
  }
}
