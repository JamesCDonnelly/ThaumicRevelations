package thaumrev.block;

import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumrev.ThaumRevLibrary;


public class BlockSlabQuartz extends BlockSlab {
	public static final String[] types = { "InfusedQuartz" };

	public BlockSlabQuartz(boolean isDouble) {
		super(isDouble, Material.rock);
		setUnlocalizedName("blockSlab");
		if (!isDouble) {
			setCreativeTab(ThaumRevLibrary.tabThaumRev);
		}
		setStepSound(Block.soundTypeStone);
		setHardness(0.8F);
		setLightOpacity(0);
	}

	/* Overrides - Item */
	@Override
	public Item getItemDropped(int par1, Random par2, int par3) {
		return Item.getItemFromBlock(ThaumRevLibrary.blockSlabInfusedQuartz);
	}

	@Override
	public String getFullSlabName(int slab) {
		if (slab < 0 || slab >= types.length) {
			slab = 0;
		}

		return getUnlocalizedName() + types[slab];
	}

	/* Overrides  - ItemStack */
	@Override
	public ItemStack createStackedBlock(int stacked) {
		return new ItemStack(
			Item.getItemFromBlock(ThaumRevLibrary.blockSlabInfusedQuartz), 2, stacked & 7
		);
	}


	/* Client-side */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int par2) {
		return ThaumRevLibrary.blockInfusedQuartzNormal.getBlockTextureFromSide(side);
	}
}
