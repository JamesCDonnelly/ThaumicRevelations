package thaumrev.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumrev.ThaumRevLibrary;

import java.util.Random;

public class BlockQuartzSlab extends BlockSlab {

	public BlockQuartzSlab() {
		super(false, Material.rock);
		setUnlocalizedName("blockInfusedQuartzSlab");
		setCreativeTab(ThaumRevLibrary.tabThaumRev);
		setStepSound(Block.soundTypeStone);
		setHardness(0.8F);
		setLightOpacity(0);
	}

	/** Overrides - String **/
//	public String func_150002_b(int var1) {
//		return "tile.blockInfusedQuartzSlab";
//	}


	/**
	 * Overrides - Item
	 **/
	@Override
	public Item getItemDropped(int par1, Random par2, int par3) {
		return Item.getItemFromBlock(ThaumRevLibrary.blockInfusedQuartzSlab);
	}


	/**
	 * Overrides  - ItemStack
	 **/
	@Override
	public ItemStack createStackedBlock(int par1) {
		return new ItemStack(ThaumRevLibrary.blockInfusedQuartzSlab);
	}


	/**
	 * Client-side
	 **/
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return ThaumRevLibrary.blockInfusedQuartzNormal.getBlockTextureFromSide(par1);
	}

	@Override
	public String getFullSlabName(int i) {
		return null;
	}
}
