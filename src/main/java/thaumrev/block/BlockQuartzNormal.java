package thaumrev.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import thaumrev.ThaumRevLibrary;

public class BlockQuartzNormal extends Block {

	public IIcon topIcon;
	public IIcon botIcon;
	public IIcon sideIcon;

	public BlockQuartzNormal() {
        super(Material.rock);
        setUnlocalizedName("blockInfusedQuartzNormal");
        setCreativeTab(ThaumRevLibrary.tabThaumRev);
        setStepSound(Block.soundTypeStone);
        setHardness(0.8F);
    }

    /**
     * Client-side
     **/
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        topIcon = register.registerIcon("thaumrev:infusedquartztop");
        botIcon = register.registerIcon("thaumrev:infusedquartzbot");
        sideIcon = register.registerIcon("thaumrev:infusedquartzside");
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
