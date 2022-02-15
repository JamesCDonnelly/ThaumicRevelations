package thaumrev.client.renderers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thaumrev.client.models.ModelKnowledgeReprocessor;
import thaumrev.tiles.TileKnowledgeReprocessor;

@SideOnly(Side.CLIENT)
public class TileKnowledgeReprocessorRenderer extends TileEntitySpecialRenderer {
  private static final ResourceLocation rl = new ResourceLocation("thaumrev", "textures/models/reprocessor.png");
  public ModelKnowledgeReprocessor reprocessorModel = new ModelKnowledgeReprocessor();

  public void renderTileEntityAt(TileKnowledgeReprocessor tile, double x, double y, double z, float arg4) {
    int facing = 0;
    if (tile.hasWorldObj()) {
      facing = tile.getBlockMetadata();
    }

    this.bindTexture(rl);
    GL11.glPushMatrix();
    GL11.glEnable(32826);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);

    float rotation = 0;
    switch(facing) {
      case 2:
        rotation = 180.0F;
        break;
      case 3:
        rotation = 90.0F;
        break;
      case 4:
        rotation = 0.0F;
        break;
      case 5:
        rotation = -90.0F;
        break;
      default:
        break;
    }

    GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
    this.reprocessorModel.render(0.0625F);
    GL11.glDisable(32826);
    GL11.glPopMatrix();
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
  }

  @Override
  public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float arg4) {
    this.renderTileEntityAt((TileKnowledgeReprocessor) tile, x, y, z, arg4);
  }
}
