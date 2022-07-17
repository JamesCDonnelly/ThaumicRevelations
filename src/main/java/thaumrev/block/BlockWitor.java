package thaumrev.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;

import java.util.Random;

public class BlockWitor extends Block {
	public BlockWitor() {
		super(Config.airyMaterial);
		setUnlocalizedName("blockWitor");
		setStepSound(Block.soundTypeCloth);
		setBlockBounds(0.3F, 0.3F, 0.3F, 0.7F, 0.7F, 0.7F);
	}

	/* Overrides - boolean */
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return false;
	}


	/* Overrides - int */
	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public int getLightValue() {
		return 15;
	}


	/* Overrides - Item */
	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return null;
	}


	/* Overrides - AxisAlignedBB */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}


	/* Client-side */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
			blockIcon = register.registerIcon("thaumcraft:blank");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		if (rand.nextInt(9 - Thaumcraft.proxy.particleCount(2)) <= 5) {
			Thaumcraft.proxy.wispFX3(
				world,
				x + 0.5F,
				y + 0.5F,
				z + 0.5F,
				x + 0.3F + rand.nextFloat() * 0.4F,
				y + 0.5F,
				z + 0.3F + rand.nextFloat() * 0.4F,
				0.5F,
				0,
				true,
				-0.025F
			);
		}

		if (rand.nextInt(15 - Thaumcraft.proxy.particleCount(4)) <= 8) {
			Thaumcraft.proxy.wispFX3(
				world,
				x + 0.5F,
				y + 0.5F,
				z + 0.5F,
				x + 0.4F + rand.nextFloat() * 0.2F,
				y + 0.5F,
				z + 0.4F + rand.nextFloat() * 0.2F,
				0.25F,
				2,
				true,
				-0.02F);
		}
	}
}
