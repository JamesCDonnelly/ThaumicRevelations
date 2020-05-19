package thaumrev.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import thaumrev.common.ThaumRevLibrary;
import thaumrev.common.ThaumicRevelations;

public class ItemWaslieHammer extends Item {

	public ItemWaslieHammer() {
		super();
		setUnlocalizedName("itemWaslieHammer");
		setCreativeTab(ThaumRevLibrary.tabThaumRev);
		setMaxStackSize(1);
		canRepair = false;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}

	@Override
	public boolean isItemTool(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.openGui(ThaumicRevelations.instance, 0, world, 0, 0, 0);
		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon("thaumrev:wasliehammer");
	}

}
