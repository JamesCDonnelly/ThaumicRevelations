package thaumrev.client.renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.client.lib.UtilsFX;

import org.lwjgl.opengl.GL11;

import thaumrev.client.models.ModelKnowledgeReprocessor;
import thaumrev.tiles.TileKnowledgeReprocessor;

public class TileKnowledgeReprocessorRenderer extends TileEntitySpecialRenderer {
  public ModelKnowledgeReprocessor reprocessorModel = new ModelKnowledgeReprocessor();

  public void renderTileEntityAt(TileKnowledgeReprocessor reprocessor, double arg1, double arg2, double arg3, float arg4) {
    GL11.glPushMatrix();
    GL11.glTranslatef((float)arg1 + 0.5F, (float)arg2 + 1.0F, (float)arg3 + 0.5F);
    GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
    UtilsFX.bindTexture("thaumrev", "textures/models/reprocessor.png");
    this.reprocessorModel.render(0.0625F);
    // 
    // GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    // GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
    GL11.glPopMatrix();
  }

  @Override
  public void renderTileEntityAt(TileEntity tile, double arg1, double arg2, double arg3, float arg4) {
    System.out.println(String.format("x: %s, y: %s, z: %s", tile.xCoord, tile.yCoord, tile.zCoord));
    System.out.println(String.format("arg1: %s, arg2: %s, arg3: %s, arg4: %s", arg1, arg2, arg3, arg4));
    this.renderTileEntityAt((TileKnowledgeReprocessor) tile, arg3, arg3, arg3, arg4);
  }
}
