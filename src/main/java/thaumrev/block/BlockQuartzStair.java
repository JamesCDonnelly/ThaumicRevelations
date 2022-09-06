package thaumrev.block;

import net.minecraft.block.BlockStairs;
import thaumrev.config.ConfigLibrary;

public class BlockQuartzStair extends BlockStairs {

	public BlockQuartzStair() {
        super(ConfigLibrary.blockInfusedQuartzNormal, 0);
        setUnlocalizedName("blockInfusedQuartzStair");
        setCreativeTab(ConfigLibrary.tabThaumRev);
        setLightOpacity(0);
    }
}
