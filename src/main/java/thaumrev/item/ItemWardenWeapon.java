package thaumrev.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
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


	/**
	 * Overrides - void
	 **/
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, @NotNull List list, boolean par4) {
		list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("tooltip.wardenic.upgrade") +
				": " + WardenicChargeHelper.getUpgrade(stack).getQuote());

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
		try {
			DamageSource damageSource = new DamageSourceWarden("warden", player);
			entity.attackEntityFrom(damageSource, 5);
			WardenicChargeHelper.getUpgrade(stack).onAttack(stack, player, entity);
		} catch (Exception ignored) {
		}

		stack.setMetadata(0);

		return super.onLeftClickEntity(stack, player, entity);
	}

	@Override
	public boolean onBlockDestroyed(@NotNull ItemStack stack, World world, Block block, int i1, int i2, int i3, EntityLivingBase entityLivingBase) {
		stack.setMetadata(0);
		return super.onBlockDestroyed(stack, world, block, i1, i2, i3, entityLivingBase);
	}

	@Override
	public boolean onItemUse(@NotNull ItemStack stack, EntityPlayer player, World world, int i1, int i2, int i3, int i4, float f1, float f2, float f3) {
		stack.setMetadata(0);
		return super.onItemUse(stack, player, world, i1, i2, i3, i4, f1, f2, f3);
	}


	/**
	 * Overrides - int
	 **/
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}


	/**
	 * Overrides - EnumRarity
	 **/
	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.epic;
	}


	/**
	 * Overrides - EnumAction
	 **/
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.block;
	}


	/**
	 * Client-side
	 **/
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(@NotNull IIconRegister register) {
		itemIcon = register.registerIcon("thaumrev:wardensword");
	}
}
