package trevelations.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.util.ForgeDirection;

import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;

public class BlockWitor extends Block {

	public BlockWitor() {
		super(Config.airyMaterial);
		setBlockName("blockWitor");
		setStepSound(Block.soundTypeCloth);
		setBlockBounds(0.3F, 0.3F, 0.3F, 0.7F, 0.7F, 0.7F);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		blockIcon = register.registerIcon("thaumcraft:blank");
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public int getLightValue() {
		return 15;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return null;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return false;
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
					-0.025F);
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
