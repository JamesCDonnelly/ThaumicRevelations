package thaumrev.item.armor;

import java.util.List;

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

import thaumrev.common.ThaumRevLibrary;
import thaumrev.util.wardenic.WardenicChargeHelper;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;

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
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
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
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add(EnumChatFormatting.AQUA + StatCollector.translateToLocal("tooltip.wardenic.charge") + ": " + (stack.getMaxDamage() - stack.getItemDamage()) + "/" + stack.getMaxDamage());
		list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("tooltip.wardenic.upgrade") + ": " + WardenicChargeHelper.getUpgrade(stack).getQuote());

		super.addInformation(stack, player, list, par4);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		WardenicChargeHelper.getUpgrade(itemStack).onTick(world, player, itemStack);

		super.onArmorTick(world, player, itemStack);
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
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {}

	@Override
	public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
		return 5;
	}
}
