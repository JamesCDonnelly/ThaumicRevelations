package thaumrev.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import thaumrev.common.ThaumRevLibrary;

public class BlockExcubitura extends BlockBush implements IGrowable {
	/** http://jabelarminecraft.blogspot.com/p/minecraft-forge-172-creating-custom.html **/

	@SideOnly(Side.CLIENT)
	protected IIcon[] iIcon;

	public BlockExcubitura()
	{
		super(Material.plants);
		setBlockName("blockExcubitura");
		setTickRandomly(true);
		float f = 0.5F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
		setCreativeTab(ThaumRevLibrary.tabThaumRev);
		setHardness(0.0F);
		setStepSound(soundTypeGrass);
	}

	@Override
	public Item getItemDropped(int par1, Random random, int fortune) {
		return ThaumRevLibrary.itemResource;
	}

	@Override
	public int damageDropped(int par1) {
		return 1;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return false;
	}

	@Override
	public void registerBlockIcons(IIconRegister register) {
		blockIcon = register.registerIcon("thaumrev:excubitura");
	}

	@Override
	protected boolean canPlaceBlockOn(Block parBlock)
	{
		return parBlock == Blocks.farmland;
	}

	public void incrementGrowStage(World parWorld, Random parRand, int parX, int parY, int parZ)
	{
		int growStage = parWorld.getBlockMetadata(parX, parY, parZ) +
				MathHelper.getRandomIntegerInRange(parRand, 2, 5);

		if (growStage > maxGrowthStage)
		{
			growStage = maxGrowthStage;
		}

		parWorld.setBlockMetadataWithNotify(parX, parY, parZ, growStage, 2);
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int parSide, int parGrowthStage)
	{
		return iIcon[parGrowthStage];
	}

	@Override
	// checks if finished growing (a grow stage of 7 is final stage)
	public boolean func_149851_a(World parWorld, int parX, int parY, int parZ,
								 boolean p_149851_5_)
	{
		return parWorld.getBlockMetadata(parX, parY, parZ) != 7;
	}

	@Override
	public boolean func_149852_a(World p_149852_1_, Random parRand, int p_149852_3_,
								 int p_149852_4_, int p_149852_5_)
	{
		return true;
	}

	@Override
	public void func_149853_b(World parWorld, Random parRand, int parX, int parY,
							  int parZ)
	{
		incrementGrowStage(parWorld, parRand, parX, parY, parZ);
	}
}
