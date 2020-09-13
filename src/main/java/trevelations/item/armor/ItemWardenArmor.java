package trevelations.item.armor;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import trevelations.common.ThaumRevLibrary;
import trevelations.util.wardenic.WardenicChargeHelper;

import java.util.List;

public class ItemWardenArmor extends ItemArmor implements ISpecialArmor, IVisDiscountGear {

	public ItemWardenArmor(int type) {
		super(ThaumRevLibrary.armorMaterialWarden, 2, type);
		setCreativeTab(ThaumRevLibrary.tabThaumRev);
		setMaxStackSize(1);
	}

	@Override
	public boolean getShareTag() {
		return true;
	}

	@Override
	public boolean isBookEnchantable(ItemStack armor, ItemStack book) {
		return false;
	}

	@Override
	public int getMaxDamage(ItemStack armor) {
		return 50;
	}

	@Override
	public boolean isDamageable() {
		return false;
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.epic;
	}

	@Override
	public void addInformation(ItemStack armor, EntityPlayer player, List list, boolean par4) {
		super.addInformation(armor, player, list, par4);

		list.add(EnumChatFormatting.AQUA + StatCollector.translateToLocal("tooltip.wardenic.charge") + ": " + (armor.getMaxDamage() - armor.getItemDamage()) + "/" + armor.getMaxDamage());
		list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("tooltip.wardenic.upgrade") + ": " + WardenicChargeHelper.getUpgrade(armor).getQuote());

		if (this.getVisDiscount(armor, player, Aspect.AIR) == 10) {
			list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + " (Aer): " + this.getVisDiscount(armor, player, Aspect.AIR) + "%");
		} else if (this.getVisDiscount(armor, player, Aspect.WATER) == 10) {
			list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + " (Aqua): " + this.getVisDiscount(armor, player, Aspect.WATER) + "%");
		} else if (this.getVisDiscount(armor, player, Aspect.FIRE) == 10) {
			list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + " (Ignis): " + this.getVisDiscount(armor, player, Aspect.FIRE) + "%");
		} else if (this.getVisDiscount(armor, player, Aspect.ORDER) == 10) {
			list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + " (Ordo): " + this.getVisDiscount(armor, player, Aspect.ORDER) + "%");
		} else if (this.getVisDiscount(armor, player, Aspect.ENTROPY) == 10) {
			list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + " (Perditio): " + this.getVisDiscount(armor, player, Aspect.ENTROPY) + "%");
		} else if (this.getVisDiscount(armor, player, Aspect.EARTH) == 10) {
			list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + " (Terra): " + this.getVisDiscount(armor, player, Aspect.EARTH) + "%");
		} else {
			list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + ": " + this.getVisDiscount(armor, player, null) + "%");
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		super.onArmorTick(world, player, armor);

		WardenicChargeHelper.getUpgrade(armor).onTick(world, player, armor);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		if (armor.getItemDamage() != armor.getMaxDamage()) {
			return new ArmorProperties(0, getArmorMaterial().getDamageReductionAmount(slot) / 25D, 20);
		} else {
			return new ArmorProperties(0, 0, 0);
		}
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return getArmorMaterial().getDamageReductionAmount(slot);
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
	}

	@Override
	public int getVisDiscount(ItemStack armor, EntityPlayer player, Aspect aspect) {
		if (WardenicChargeHelper.getUpgrade(armor).getUpgradeAspect().equals(Aspect.AIR.getName())) {
			return aspect == Aspect.AIR ? 10 : 0;
		} else if (WardenicChargeHelper.getUpgrade(armor).getUpgradeAspect().equals(Aspect.EARTH.getName())) {
			return aspect == Aspect.EARTH ? 10 : 0;
		} else if (WardenicChargeHelper.getUpgrade(armor).getUpgradeAspect().equals(Aspect.ENTROPY.getName()))  {
			return aspect == Aspect.ENTROPY ? 10 : 0;
		} else if (WardenicChargeHelper.getUpgrade(armor).getUpgradeAspect().equals(Aspect.FIRE.getName()))  {
			return aspect == Aspect.FIRE ? 10 : 0;
		} else if (WardenicChargeHelper.getUpgrade(armor).getUpgradeAspect().equals(Aspect.ORDER.getName()))  {
			return aspect == Aspect.ORDER ? 10 : 0;
		} else if (WardenicChargeHelper.getUpgrade(armor).getUpgradeAspect().equals(Aspect.WATER.getName()))  {
			return aspect == Aspect.WATER ? 10 : 0;
		} else {
			return 5;
		}
	}
}
