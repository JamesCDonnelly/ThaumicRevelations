package thaumrev.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import thaumrev.common.ThaumRevLibrary;

public class BlockQuartzChiseled extends Block {

	public IIcon topIcon;
	public IIcon botIcon;
	public IIcon sideIcon;

	public BlockQuartzChiseled() {
		super(Material.rock);
		setBlockName("blockInfusedQuartzChiseled");
		setCreativeTab(ThaumRevLibrary.tabThaumRev);
		setStepSound(Block.soundTypeStone);
		setHardness(0.8F);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register) {
		topIcon = register.registerIcon("thaumrev:infusedquartzchiseledtop");
		botIcon = register.registerIcon("thaumrev:infusedquartzchiseledtop");
		sideIcon = register.registerIcon("thaumrev:infusedquartzchiseledside");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side == 0) {
			return botIcon;
		} else if (side == 1) {
			return topIcon;
		} else {
			return sideIcon;
		}
	}
}
