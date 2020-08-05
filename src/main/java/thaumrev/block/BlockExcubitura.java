package thaumrev.block;

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

import java.util.Random;

public class BlockExcubitura extends BlockBush implements IGrowable {
	@SideOnly(Side.CLIENT)
	protected IIcon[] iIcon;

	public BlockExcubitura()
	{
		super(Material.plants);
		setBlockName("blockExcubitura");
		setBlockTextureName("thaumrev:excubitura_stage_0");
		setTickRandomly(true);
		setBlockBounds(0F, 0.0F, 0F, 1F, 0.25F, 1F);
		setCreativeTab(ThaumRevLibrary.tabThaumRev);
		setHardness(0.0F);
		setStepSound(soundTypeGrass);
	}

	@Override
	public int quantityDropped(int parMetadata, int parFortune, Random parRand) {
		return parMetadata / 2;
	}

	@Override
	public Item getItemDropped(int parMetadata, Random parRand, int parFortune) {
		return ThaumRevLibrary.itemResource;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister parIIconRegister)
	{
		iIcon = new IIcon[8];
		iIcon[0] = parIIconRegister.registerIcon("thaumrev:excubitura_stage_0");
		iIcon[1] = parIIconRegister.registerIcon("thaumrev:excubitura_stage_0");
		iIcon[2] = parIIconRegister.registerIcon("thaumrev:excubitura_stage_1");
		iIcon[3] = parIIconRegister.registerIcon("thaumrev:excubitura_stage_1");
		iIcon[4] = parIIconRegister.registerIcon("thaumrev:excubitura_stage_2");
		iIcon[5] = parIIconRegister.registerIcon("thaumrev:excubitura_stage_2");
		iIcon[6] = parIIconRegister.registerIcon("thaumrev:excubitura_stage_3");
		iIcon[7] = parIIconRegister.registerIcon("thaumrev:excubitura_stage_3");
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
	protected boolean canPlaceBlockOn(Block parBlock)
	{
		return parBlock == Blocks.farmland;
	}

	public void incrementGrowStage(World parWorld, Random parRand, int parX, int parY, int parZ) {
		int growStage = parWorld.getBlockMetadata(parX, parY, parZ) +
				MathHelper.getRandomIntegerInRange(parRand, 2, 5);

		if (growStage > 7) {
			growStage = 7;
		}

		parWorld.setBlockMetadataWithNotify(parX, parY, parZ, growStage, 2);
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int parSide, int parGrowthStage) {
		return iIcon[parGrowthStage];
	}

	@Override
	public boolean func_149851_a(World parWorld, int parX, int parY, int parZ, boolean p_149851_5_) {
		return parWorld.getBlockMetadata(parX, parY, parZ) != 7;
	}

	@Override
	public boolean func_149852_a(World p_149852_1_, Random parRand, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
		return true;
	}

	@Override
	public void func_149853_b(World parWorld, Random parRand, int parX, int parY, int parZ) {
		incrementGrowStage(parWorld, parRand, parX, parY, parZ);
	}

	@Override
	public void updateTick(World parWorld, int parX, int parY, int parZ, Random parRand) {
		super.updateTick(parWorld, parX, parY, parZ, parRand);
		int growStage = parWorld.getBlockMetadata(parX, parY, parZ) + 1;

		if (growStage > 7) {
			growStage = 7;
		}
		parWorld.setBlockMetadataWithNotify(parX, parY, parZ, growStage, 2);
	}
}
