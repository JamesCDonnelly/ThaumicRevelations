package thaumrev.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import thaumrev.config.ConfigLibrary;
import thaumrev.ThaumicRevelations;

public class ItemWaslieHammer extends Item {

	public ItemWaslieHammer() {
		super();
		setUnlocalizedName("itemWaslieHammer");
		setCreativeTab(ConfigLibrary.tabThaumRev);
		setMaxStackSize(1);
		canRepair = false;
	}


	/** Overrides - boolean **/
	@Override
	public boolean isItemTool(ItemStack stack) {
		return true;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}


	/**
	 * Overrides - ItemStack
	 **/
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, @NotNull EntityPlayer player) {
		player.openGui(ThaumicRevelations.instance, 0, world, 0, 0, 0);
		return stack;
	}


	/** Overrides - EnumRarity **/
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}


	/**
	 * Client-side
	 **/
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(@NotNull IIconRegister register) {
		itemIcon = register.registerIcon("thaumrev:wasliehammer");
	}
}
