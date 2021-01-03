package thaumrev.util.wardenic;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumrev.item.armor.ItemWardenArmor;
import thaumrev.item.baubles.ItemWardenAmulet;

import java.util.Random;

public class WardenicChargeEvents {

	private final Random random = new Random();

	public static void init() {
		MinecraftForge.EVENT_BUS.register(new WardenicChargeEvents());
	}

	@SubscribeEvent
	public void onPlayerTick(LivingUpdateEvent event) {}

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;

			for (int i = 1; i < 5; i++) {
				if (player.getEquipmentInSlot(i) != null &&
					(player.getEquipmentInSlot(i).getItem() instanceof ItemWardenArmor)) {
						WardenicChargeHelper.getUpgrade(player.getEquipmentInSlot(i)).onAttacked(event);
				}
			}

			ItemWardenAmulet.amuletCharge++;

		} else if ((event.source.getSourceOfDamage() instanceof EntityArrow) &&
				(event.source.getEntity() instanceof EntityPlayer)) {

			EntityPlayer player = (EntityPlayer)event.source.getEntity();
			Entity entityArrow = event.source.getSourceOfDamage();
			NBTTagCompound tag = entityArrow.getEntityData();

			if (tag.getBoolean("WardenArrow")) {
				WardenicChargeHelper.getUpgrade(player.getEquipmentInSlot(0)).onIndirectAttack(event);
			}
		}
	}
}
