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
import thaumrev.config.ConfigLibrary;

import java.util.Random;

import static thaumrev.config.Config.roseGrowthDivider;

public class BlockExcubitura extends BlockBush implements IGrowable {

	public BlockExcubitura() {
		super(Material.plants);
		setUnlocalizedName("blockExcubitura");
		setTickRandomly(true);
		setBlockBounds(0F, 0.0F, 0F, 1F, 0.25F, 1F);
		setHardness(0.0F);
		setStepSound(soundTypeGrass);
	}

	public void incrementGrowStage(World world, Random random, int x, int y, int z) {
		int growStage = world.getBlockMetadata(x, y, z) +
				MathHelper.getRandomIntegerInRange(random, 2, 5);

		if (growStage > 15) {
			growStage = 15;
		}

		world.setBlockMetadataWithNotify(x, y, z, growStage, 2);
	}

	/* Overrides - void */
	@Override
	public void fertilize(World world, Random random, int x, int y, int z) {
		incrementGrowStage(world, random, x, y, z);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		super.updateTick(world, x, y, z, random);
		int growStage = world.getBlockMetadata(x, y, z);

		if (MathHelper.getRandomIntegerInRange(random, 1, roseGrowthDivider) == 1) {
			growStage += 1;
		}

		if (growStage > 15) {
			growStage = 15;
		}

		world.setBlockMetadataWithNotify(x, y, z, growStage, 2);
	}


	/* Overrides - boolean */
	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return world.getBlock(x, y - 1, z).equals(Blocks.dirt) ||
				world.getBlock(x, y - 1, z).equals(Blocks.farmland) ||
				world.getBlock(x, y - 1, z).equals(Blocks.grass);
	}

	@Override
	protected boolean canPlaceBlockOn(Block block) {
		return block == Blocks.farmland;
	}

	@Override
	public boolean canFertilize(World world, int x, int y, int z, boolean b) {
		return world.getBlockMetadata(x, y, z) != 15;
	}

	@Override
	public boolean shouldFertilize(World world, Random random, int i, int i1, int i2) {
		return true;
	}


	/* Overrides - int */
	@Override
	public int quantityDropped(int metadata, int fortune, Random random) {
		return metadata / 7;
	}

	@Override
	public int damageDropped(int par1) {
		return 0;
	}

	@Override
	public int getRenderType() {
		return 1;
	}


	/** Overrides - Item **/
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return random.nextInt(4) < 2 ? ConfigLibrary.itemExcubituraSeeds : ConfigLibrary.itemResource;
	}

	protected IIcon[] iIcon;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		iIcon = new IIcon[16];
		iIcon[0] = register.registerIcon("thaumrev:excubitura/excubitura_stage_0");
		iIcon[1] = register.registerIcon("thaumrev:excubitura/excubitura_stage_0");
		iIcon[2] = register.registerIcon("thaumrev:excubitura/excubitura_stage_1");
		iIcon[3] = register.registerIcon("thaumrev:excubitura/excubitura_stage_1");
		iIcon[4] = register.registerIcon("thaumrev:excubitura/excubitura_stage_2");
		iIcon[5] = register.registerIcon("thaumrev:excubitura/excubitura_stage_2");
		iIcon[6] = register.registerIcon("thaumrev:excubitura/excubitura_stage_3");
		iIcon[7] = register.registerIcon("thaumrev:excubitura/excubitura_stage_3");
		iIcon[8] = register.registerIcon("thaumrev:excubitura/excubitura_stage_4");
		iIcon[9] = register.registerIcon("thaumrev:excubitura/excubitura_stage_4");
		iIcon[10] = register.registerIcon("thaumrev:excubitura/excubitura_stage_5");
		iIcon[11] = register.registerIcon("thaumrev:excubitura/excubitura_stage_5");
		iIcon[12] = register.registerIcon("thaumrev:excubitura/excubitura_stage_6");
		iIcon[13] = register.registerIcon("thaumrev:excubitura/excubitura_stage_6");
		iIcon[14] = register.registerIcon("thaumrev:excubitura/excubitura_stage_7");
		iIcon[15] = register.registerIcon("thaumrev:excubitura/excubitura_stage_7");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return iIcon[meta];
	}
}
