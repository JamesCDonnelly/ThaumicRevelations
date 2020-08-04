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
import thaumrev.common.ThaumRevLibrary;

import java.util.List;

public class ItemWardenAmulet extends Item implements IBauble {

	public ItemWardenAmulet() {
		super();
		setUnlocalizedName("itemWardenAmulet");
		setCreativeTab(ThaumRevLibrary.tabThaumRev);
	}

	public static int amuletCharge = 0;
	String chargeInformation = "";

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon("thaumrev:wardenamulet");
	}

	@Override
	public BaubleType getBaubleType(ItemStack stack) {
		return BaubleType.AMULET;
	}

	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase entityLivingBase) {}

	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase entityLivingBase) {}

	@Override
	public void onUnequipped(ItemStack stack, EntityLivingBase entityLivingBase) {}

	@Override
	public boolean canEquip(ItemStack stack, EntityLivingBase entityLivingBase) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack stack, EntityLivingBase entityLivingBase) {
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		if (amuletCharge > 100) amuletCharge = 0;

		if (amuletCharge < 20) {
			chargeInformation = EnumChatFormatting.DARK_RED.toString();
		} else if (amuletCharge < 40) {
			chargeInformation = EnumChatFormatting.RED.toString();
		} else if (amuletCharge < 60) {
			chargeInformation = EnumChatFormatting.GOLD.toString();
		} else if (amuletCharge < 80) {
			chargeInformation = EnumChatFormatting.YELLOW.toString();
		} else {
			chargeInformation = EnumChatFormatting.GREEN.toString();
		}
		chargeInformation += amuletCharge + "/100";
		list.add(chargeInformation);
	}
}

