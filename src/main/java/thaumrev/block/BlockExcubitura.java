package thaumrev.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import thaumrev.common.ThaumRevLibrary;

public class BlockExcubitura extends BlockBush {

	public BlockExcubitura() {
		super(Material.plants);
		setBlockName("blockExcubitura");
		setCreativeTab(ThaumRevLibrary.tabThaumRev);
		setStepSound(Block.soundTypeGrass);
	}

	@Override
	public Item getItemDropped(int par1, Random random, int par2) {
		return ThaumRevLibrary.itemResource;
	}

	@Override
	public int damageDropped(int par1) {
		return 0;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return true;
	}

	@Override
	public void registerBlockIcons(IIconRegister register) {
		blockIcon = register.registerIcon("thaumrev:excubitura");
	}
}
