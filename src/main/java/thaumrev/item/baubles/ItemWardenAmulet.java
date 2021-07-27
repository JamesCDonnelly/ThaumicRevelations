package thaumrev.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.INpc;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumrev.ThaumRevLibrary;
import thaumrev.util.DamageSourceWarden;

import java.util.List;

public class ItemWardenAmulet extends Item implements IBauble {
	public ItemWardenAmulet() {
		super();
		setUnlocalizedName("itemWardenAmulet");
		setCreativeTab(ThaumRevLibrary.tabThaumRev);
		setMaxStackSize(1);
	}

	public static void useAmulet(EntityPlayer player) {
		if (getAmulet(player) == null) return;
		ItemStack amulet = getAmulet(player);
		DamageSource damageSource = new DamageSourceWarden("warden", player);

		if (amulet.getItemDamage() > 0) {
			List entities = player.worldObj.getEntitiesWithinAABBExcludingEntity(
					player,
					AxisAlignedBB.getBoundingBox(
							player.posX - 8,
							player.posY - 8,
							player.posZ - 8,
							player.posX + 8,
							player.posY + 8,
							player.posZ + 8)
			);

			amulet.setItemDamage(amulet.getItemDamage() - 5);

			for (Object entity : entities) {
				if (entity instanceof IMob ||
						entity instanceof INpc ||
						entity instanceof EntityPlayer ||
						entity instanceof EntityWaterMob) {

					/* TODO: Network Packets
					PurityHelper.purifyEntity((Entity) entity);

					if (PurityHelper.isEldritchOrTainted((Entity) entity)) {
						((Entity) entity).attackEntityFrom(damageSource, ((EntityLivingBase) entity).getMaxHealth());
					}
					*/

					summonParticles(
							player.worldObj,
							((Entity) entity).posX,
							((Entity) entity).posY,
							((Entity) entity).posZ
					);
				}

				if (entity instanceof EntityPlayer) {
					// TODO: Network Packets
					// getAmulet((EntityPlayer) entity).setItemDamage(getAmulet((EntityPlayer) entity).getItemDamage() - 1);
					amulet.setItemDamage(amulet.getItemDamage() + 1);
				}
			}
		}
	}

	private static ItemStack getAmulet(EntityPlayer player) {
		InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);

		for (int i = 0; i < baubles.getSizeInventory(); i++) {
			if (baubles.getStackInSlot(i) != null &&
					baubles.getStackInSlot(i).getItem() instanceof ItemWardenAmulet) {
				return baubles.getStackInSlot(i);
			}
		}

		return null;
	}

	private static void summonParticles(World worldObj, double posX, double posY, double posZ) {
		for (int i = 0; i < 3; i++) {
			float fx = (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.3F;
			float fy = (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.3F;
			float fz = (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.3F;
			Thaumcraft.proxy.wispFX3(worldObj,
					posX + fx, posY + fy, posZ + fz,
					posX + fx * 8.0F, posY + fy * 8.0F, posZ + fz * 8.0F,
					0.3F, i % 2 == 0 ? 0 : 2, true, 0.02F);
		}
	}


	/**
	 * Overrides - void
	 **/
	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase entityLivingBase) {
	}

	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase entityLivingBase) {
	}

	@Override
	public void onUnequipped(ItemStack stack, EntityLivingBase entityLivingBase) {
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		String chargeInformation;

		if (stack.getItemDamage() > 50) stack.setItemDamage(50);
		else if (stack.getItemDamage() < 0) stack.setItemDamage(0);

		if (stack.getItemDamage() < 8) {
			chargeInformation = EnumChatFormatting.DARK_RED.toString();
		} else if (stack.getItemDamage() < 16) {
			chargeInformation = EnumChatFormatting.RED.toString();
		} else if (stack.getItemDamage() < 25) {
			chargeInformation = EnumChatFormatting.GOLD.toString();
		} else if (stack.getItemDamage() < 33) {
			chargeInformation = EnumChatFormatting.YELLOW.toString();
		} else if (stack.getItemDamage() < 41) {
			chargeInformation = EnumChatFormatting.GREEN.toString();
		} else {
			chargeInformation = EnumChatFormatting.DARK_GREEN.toString();
		}

		chargeInformation += stack.getItemDamage() + "/50";
		list.add(chargeInformation);

		super.addInformation(stack, player, list, par4);
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

	@Override
	public boolean isDamageable() {
		return false;
	}


	/**
	 * Overrides - int
	 **/
	@Override
	public int getMaxDamage() {
		return 50;
	}


	/**
	 * Overrides - BaubleType
	 **/
	@Override
	public BaubleType getBaubleType(ItemStack stack) {
		return BaubleType.AMULET;
	}


	/**
	 * Overrides - EnumRarity
	 **/
	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}


	/**
	 * Client-side
	 **/
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon("thaumrev:wardenamulet");
	}
}
