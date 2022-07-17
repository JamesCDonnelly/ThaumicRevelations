package thaumrev.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumrev.item.baubles.ItemWardenAmulet;
import thaumrev.util.wardenic.VisHelper;
import thaumrev.util.wardenic.WardenicChargeHelper;

import java.util.Random;

import static thaumrev.ThaumRevLibrary.EXCUBITOR;

public class WardenicUpgrade {
	public Aspect aspect;
	public Random random = new Random();

	public WardenicUpgrade(Aspect aspect) {
		this.aspect = aspect;
	}

	public void onAttack(ItemStack weapon, EntityPlayer player, Entity entity) {
		ItemStack amulet = ItemWardenAmulet.getAmulet(player);

		if (amulet == null) return;

		boolean activate = ItemWardenAmulet.shouldActivate(amulet, 1000);

		if (!activate) return;
	}

	public void onIndirectAttack(LivingHurtEvent event) {
		Entity entity = event.source.getEntity();

		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			ItemStack amulet = ItemWardenAmulet.getAmulet(player);

			if (amulet == null) return;

			boolean activate = ItemWardenAmulet.shouldActivate(amulet, 1000);

			if (!activate) return;
		}
	}

	public void onWornTick(World world, EntityPlayer player, ItemStack stack) {
		ItemStack amulet = ItemWardenAmulet.getAmulet(player);

		if (amulet == null) return;

		boolean activate = ItemWardenAmulet.shouldActivate(amulet, 1000);

		if (!activate) return;
	}

	public void onHurt(LivingHurtEvent event) {
		Entity entity = event.entity;

		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			ItemStack amulet = ItemWardenAmulet.getAmulet(player);

			if (amulet == null) return;

			boolean activate = ItemWardenAmulet.shouldActivate(amulet, 1000);

			if (!activate) return;
		}
	}

	public String getQuote() {
		return StatCollector.translateToLocal("upgrade." + aspect.getName() + ".quote");
	}

	public String getUpgradeAspect() {
		return aspect.getName();
	}
}
