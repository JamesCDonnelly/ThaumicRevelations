package thaumrev.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import thaumrev.ThaumRevLibrary;

import java.util.List;

public class ItemWardenAmulet extends Item implements IBauble {

	public static int amuletCharge = 0;
	String chargeInformation = "";

	public ItemWardenAmulet() {
		super();
		setUnlocalizedName("itemWardenAmulet");
		setCreativeTab(ThaumRevLibrary.tabThaumRev);
		setMaxStackSize(1);
	}


	/** Overrides - void **/
	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase entityLivingBase) {}

	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase entityLivingBase) {}

	@Override
	public void onUnequipped(ItemStack stack, EntityLivingBase entityLivingBase) {}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		if (amuletCharge > 50) amuletCharge = 0;

		if (amuletCharge < 8) {
			chargeInformation = EnumChatFormatting.DARK_RED.toString();
		} else if (amuletCharge < 16) {
			chargeInformation = EnumChatFormatting.RED.toString();
		} else if (amuletCharge < 25) {
			chargeInformation = EnumChatFormatting.GOLD.toString();
		} else if (amuletCharge < 33) {
			chargeInformation = EnumChatFormatting.YELLOW.toString();
		} else if (amuletCharge < 41) {
			chargeInformation = EnumChatFormatting.GREEN.toString();
		} else {
			chargeInformation = EnumChatFormatting.DARK_GREEN.toString();
		}
		chargeInformation += amuletCharge + "/50";
		list.add(chargeInformation);
	}


	/** Overrides - boolean **/
	@Override
	public boolean canEquip(ItemStack stack, EntityLivingBase entityLivingBase) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack stack, EntityLivingBase entityLivingBase) {
		return true;
	}


	/** Overrides - BaubleType **/
	@Override
	public BaubleType getBaubleType(ItemStack stack) {
		return BaubleType.AMULET;
	}


	/** Overrides - EnumRarity **/
	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}


	/** Client-side **/
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon("thaumrev:wardenamulet");
	}
}

