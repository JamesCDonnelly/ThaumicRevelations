package thaumrev.api.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.aspects.Aspect;
import thaumrev.item.ItemWardenWeapon;
import thaumrev.item.baubles.ItemWardenAmulet;
import thaumrev.api.wardenic.WardenicChargeHelper;

import java.util.Random;

public class WardenicUpgrade {
	
	public Aspect aspect;
	public Random random = new Random();

	public WardenicUpgrade(Aspect aspect) {
		this.aspect = aspect;
	}

	public void onAttack(ItemStack weapon, EntityPlayer player, Entity entity) {
		ItemStack amulet = ItemWardenAmulet.getAmulet(player);

		if (amulet == null) return;
		if (!(weapon != null && weapon.getItem() instanceof ItemWardenWeapon)) return;

		boolean activate = ItemWardenAmulet.shouldActivate(amulet, 500);

		if (!activate) return;
	}

	public void onIndirectAttack(@NotNull LivingHurtEvent event) {
		Entity entity = event.source.getEntity();

		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			ItemStack amulet = ItemWardenAmulet.getAmulet(player);

			if (amulet == null) return;

			boolean activate = ItemWardenAmulet.shouldActivate(amulet, 500);

			if (!activate) return;
		} else {
			return;
		}
	}

	public void onWornTick(ItemStack amulet, EntityLivingBase player) {
		short count = WardenicChargeHelper.getWardenicArmorCount((EntityPlayer) player);

		if (amulet == null) return;
		if (count < 4) return;

		boolean activate = ItemWardenAmulet.shouldActivate(amulet, 100);

		if (!activate) return;
	}

	public void onHurt(@NotNull LivingHurtEvent event) {
		Entity entity = event.entity;

		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			ItemStack amulet = ItemWardenAmulet.getAmulet(player);
			short count = WardenicChargeHelper.getWardenicArmorCount(player);

			if (amulet == null) return;
			if (count < 4) return;

			boolean activate = ItemWardenAmulet.shouldActivate(amulet, 1000);

			if (!activate) return;
		} else {
			return;
		}
	}

	public void onEquipped(ItemStack stack, EntityLivingBase entityLivingBase) {
		if (!(entityLivingBase instanceof EntityPlayer)) {
			return;
		}
	}

	public void onUnequipped(ItemStack stack, EntityLivingBase entityLivingBase) {
		if (!(entityLivingBase instanceof EntityPlayer)) {
			return;
		}
	}

	public String getQuote() {
		return StatCollector.translateToLocal("upgrade." + aspect.getName() + ".quote");
	}

	public String getUpgradeAspect() {
		return aspect.getName();
	}
}
