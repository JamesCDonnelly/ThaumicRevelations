package thaumrev.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumrev.config.ConfigLibrary;
import thaumrev.tiles.TileKnowledgeReprocessor;

public class BlockKnowledgeReprocessor extends BlockContainer {
  public IIcon[] icons = new IIcon[3];

  public BlockKnowledgeReprocessor() {
    super(Material.rock);
    setCreativeTab(ConfigLibrary.tabThaumRev);
    setUnlocalizedName("blockKnowledgeReprocessor");

    this.setHardness(4.0F);
    this.setResistance(18.0F);
    this.setStepSound(Block.soundTypeStone);
    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public int getRenderType() { return -1; }

  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister ir) { }

	@Override
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int side, int meta) {
    if (side == 0) {
      return this.icons[0];
    } else if (side == 1) {
      return this.icons[1];
    } else {
      return this.icons[2];
    }
  }

  public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
    byte rotation = 0;
    int rotationYaw = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

    switch (rotationYaw) {
      case 0:
        rotation = 2;
        break;

      case 1:
        rotation = 5;
        break;

      case 2:
        rotation = 4;
        break;

      case 3:
        rotation = 3;
        break;

      default:
        break;
    }

    world.setBlockMetadataWithNotify(x, y, z, rotation, 2);
}

  @Override
  public TileEntity createNewTileEntity(World world, int metadata) {
    return new TileKnowledgeReprocessor();
  }

  @Override
  public boolean renderAsNormalBlock() { return false; }

  @Override
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return true;
  }
}
