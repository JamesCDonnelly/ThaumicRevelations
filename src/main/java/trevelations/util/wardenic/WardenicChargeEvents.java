package trevelations.util.wardenic;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import trevelations.item.ItemWardenBow;
import trevelations.item.ItemWardenWeapon;
import trevelations.item.armor.ItemWardenArmor;
import trevelations.item.baubles.ItemWardenAmulet;

import java.util.Random;

public class WardenicChargeEvents {

	private final Random random = new Random();

	public static void init() {
		MinecraftForge.EVENT_BUS.register(new WardenicChargeEvents());
	}

	@SubscribeEvent
	public void onTick(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			int count = 0;

			for (int i = 0; i <= 4; i++) {
				if (player.getEquipmentInSlot(i) != null) {
					if (player.getEquipmentInSlot(i).getItem() instanceof ItemWardenArmor ||
							player.getEquipmentInSlot(i).getItem() instanceof ItemWardenWeapon ||
							player.getEquipmentInSlot(i).getItem() instanceof ItemWardenBow) {
						if (player.getEquipmentInSlot(i).getItemDamage() != player.getEquipmentInSlot(i).getMaxDamage()) {
							if (random.nextInt(50) == 0) {
								player.getEquipmentInSlot(i).setItemDamage(player.getEquipmentInSlot(i).getItemDamage() - 1);
							}
						}
					}
				}
			}
			for (int i = 0; i <= 3; i++) {
				if (player.getCurrentArmor(i) == null ||
						!(player.getCurrentArmor(i).getItem() instanceof ItemWardenArmor) ||
						!(WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
								.equals(Aspect.AIR.getName()))||
						!(WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
								.equals(Aspect.WATER.getName()))) {
					count++;
					if (count == 4) {
						player.capabilities.setPlayerWalkSpeed(0.1F);
						player.stepHeight = 0.5F;
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			for (int i = 1; i <= 4; i++) {
				if (player.getEquipmentInSlot(i) != null &&
						(player.getEquipmentInSlot(i).getItem() instanceof ItemWardenArmor)) {
					if (player.getEquipmentInSlot(i).getItemDamage() != player.getEquipmentInSlot(i).getMaxDamage()) {
						player.getEquipmentInSlot(i).setItemDamage(player.getEquipmentInSlot(i).getItemDamage() + 1);
						WardenicChargeHelper.getUpgrade(player.getEquipmentInSlot(i)).onAttacked(event);
					}
				}
			}
			++ItemWardenAmulet.amuletCharge;
		}
	}
}
