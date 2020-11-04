package thaumrev.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import thaumrev.ThaumRevLibrary;
import thaumrev.util.DamageSourceWarden;
import thaumrev.util.wardenic.WardenicChargeHelper;

import java.util.List;

public class ItemWardenWeapon extends ItemSword {

	public ItemWardenWeapon() {
		super(ThaumRevLibrary.toolMaterialWarden);
		setUnlocalizedName("itemWardenWeapon");
		setCreativeTab(ThaumRevLibrary.tabThaumRev);
		setMaxStackSize(1);
		setFull3D();
	}


	/** Overrides - void **/
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add(EnumChatFormatting.AQUA + StatCollector.translateToLocal("tooltip.wardenic.charge") + ": " + (stack.getMaxDamage() - stack.getItemDamage()) + "/" + stack.getMaxDamage());
		list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("tooltip.wardenic.upgrade") + ": " + WardenicChargeHelper.getUpgrade(stack).getQuote());

		super.addInformation(stack, player, list, par4);
	}


	/** Overrides - boolean **/
	@Override
	public boolean getShareTag() {
		return true;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	@Override
	public boolean isDamageable() {
		return false;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if (stack.getItemDamage() != stack.getMaxDamage()) {
			DamageSource damageSource = new DamageSourceWarden("warden", player);
			entity.attackEntityFrom(damageSource, 5);

			WardenicChargeHelper.getUpgrade(stack).onAttack(stack, player, entity);

			stack.setItemDamage(stack.getItemDamage() + 1);
		}
		return super.onLeftClickEntity(stack, player, entity);
	}


	/** Overrides - int **/
	@Override
	public int getMaxDamage(ItemStack stack) {
		return 50;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}


	/** Overrides - EnumRarity **/
	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.epic;
	}


	/** Overrides - EnumAction **/
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.block;
	}


	/** Client-side **/
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon("thaumrev:wardensword");
	}
}
