package thaumrev.util.wardenic;

import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumrev.ThaumRevLibrary;
import thaumrev.item.ItemWardenWeapon;
import thaumrev.item.armor.ItemWardenArmor;
import thaumrev.item.baubles.ItemWardenAmulet;

import java.util.Random;

public class WardenicChargeEvents {

	private final Random random = new Random();

	public static void init() {
		MinecraftForge.EVENT_BUS.register(new WardenicChargeEvents());
	}

	@SubscribeEvent
	public void onPlayerTick(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;

			if (player.getCurrentArmor(0) != null) {
				if ((WardenicChargeHelper.getUpgrade(player.getCurrentArmor(0))
						.getUpgradeAspect().equals(Aspect.AIR.getName()) && !player.isInWater()) ||
						(WardenicChargeHelper.getUpgrade(player.getCurrentArmor(0))
								.getUpgradeAspect().equals(Aspect.WATER.getName()) && player.isInWater())) {
					player.stepHeight = 1.0F;
				}
			} else {
				player.stepHeight = 0.5F;
			}

			for (int i = 0; i < 4; i++) {
				if (player.getCurrentArmor(i) != null && (player.getCurrentArmor(i).getItem() instanceof ItemWardenArmor)) {
					player.getCurrentArmor(i).setItemDamage(0);
				}
			}

			if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemWardenWeapon) {
				player.getHeldItem().setItemDamage(0);
			}
		}
	}

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;

			for (int i = 1; i < 4; i++) {
				if (player.getCurrentArmor(i) != null &&
						(player.getCurrentArmor(i).getItem() instanceof ItemWardenArmor)) {
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).onAttacked(event);
				}
			}

			InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);

			for (int i = 0; i < baubles.getSizeInventory(); i++) {
				if (baubles.getStackInSlot(i).getItem() instanceof ItemWardenAmulet) {
					baubles.getStackInSlot(i).setItemDamage(baubles.getStackInSlot(i).getItemDamage() + 1);
					break;
				}
			}
		}

		if (event.source.getEntity() instanceof EntityPlayer && event.source.getSourceOfDamage() instanceof EntityArrow) {
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			Entity entityArrow = event.source.getSourceOfDamage();
			NBTTagCompound tag = entityArrow.getEntityData();

			if (tag.getString("WardenArrow") != null) {
				WardenicChargeHelper.getUpgrade(player.getHeldItem()).onIndirectAttack(event);
			}

			if (event.entity instanceof EntityPlayer &&
					tag.getString("WardenArrow").equals(ThaumRevLibrary.EXCUBITOR.getName())) {
				EntityPlayer target = (EntityPlayer) event.entity;
				InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(target);

				for (int i = 0; i < baubles.getSizeInventory(); i++) {
					if (baubles.getStackInSlot(i) != null &&
							baubles.getStackInSlot(i).getItem() instanceof ItemWardenAmulet) {
						baubles.getStackInSlot(i).setItemDamage(baubles.getStackInSlot(i).getItemDamage() - 5);
						break;
					}
				}
			}
		}
	}
}
