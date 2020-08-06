package trevelations.block;

import net.minecraft.block.BlockStairs;

import trevelations.common.ThaumRevLibrary;

public class BlockQuartzStair extends BlockStairs {

	public BlockQuartzStair() {
		super(ThaumRevLibrary.blockInfusedQuartzNormal, 0);
		setBlockName("blockInfusedQuartzStair");
		setCreativeTab(ThaumRevLibrary.tabThaumRev);
		setLightOpacity(0);
	}
}
