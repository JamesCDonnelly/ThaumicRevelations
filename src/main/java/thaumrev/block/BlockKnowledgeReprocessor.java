package thaumrev.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumrev.ThaumRevLibrary;
import thaumrev.tiles.TileKnowledgeReprocessor;

public class BlockKnowledgeReprocessor extends BlockContainer {
  public IIcon[] icons = new IIcon[3];

  public BlockKnowledgeReprocessor() {
    super(Material.rock);
    setCreativeTab(ThaumRevLibrary.tabThaumRev);
    setUnlocalizedName("blockKnowledgeReprocessor");

    this.setHardness(4.0F);
    this.setResistance(18.0F);
    this.setStepSound(Block.soundTypeStone);
    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public int getRenderType() { return 2; }

  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister ir) {
    this.icons[0] = ir.registerIcon("thaumcraft:arcaneearbottom");
    this.icons[1] = ir.registerIcon("thaumcraft:liftertop");
    this.icons[2] = ir.registerIcon("thaumcraft:lifterside");
  }

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

  @Override
  public TileEntity createNewTileEntity(World world, int metadata) {
    return new TileKnowledgeReprocessor();
  }

  // @Override
  // public TileEntity createTileEntity(World world, int metadata) {
  //   return new TileKnowledgeReprocessor();
  // }

  @Override
  public boolean renderAsNormalBlock() {
    return false;
  }
}
