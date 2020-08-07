package trevelations.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import trevelations.util.wardenic.WardenicChargeHelper;

public class WardenicUpgradeWater extends WardenicUpgrade {

	public WardenicUpgradeWater(Aspect aspect) {
		super(aspect);
	}

	@Override
	public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
		super.onAttack(stack, player, entity);

		EntityLivingBase entityLivingBase = (EntityLivingBase) entity;

		int count = 0;

		for (int i = 0; i <= 3; i++) {
			if ((player.getCurrentArmor(i) != null) &&
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
							.equals(Aspect.WATER.getName())) {
				count++;
			}
		}

		if (count == 4) {
			entityLivingBase.addPotionEffect(new PotionEffect(Potion.poison.getId(), 40, 3));
		} else {
			entityLivingBase.addPotionEffect(new PotionEffect(Potion.poison.getId(), 40, 0));
		}
	}

	@Override
	public void onTick(World world, EntityPlayer player, ItemStack stack) {
		super.onTick(world, player, stack);

		if (player.isInWater()) {
			int count = 0;

			for (int i = 0; i <= 3; i++) {
				if ((player.getCurrentArmor(i) != null) &&
						WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
								.equals(Aspect.WATER.getName())) {
					count++;
				}
			}

			if (count == 4) {
				// p.addPotionEffect(new PotionEffect(<potion id>, <time>, <amplitude>)
				// amplitude is equal to level - 1
				// time is provided in ticks (1 second = 20 ticks), so: time (in seconds) = time (in ticks) / 20

				player.addPotionEffect(new PotionEffect(Potion.waterBreathing.getId(), 0, 0));

				if (player.isPotionActive(Potion.poison.getId())) {
					player.removePotionEffect(Potion.poison.getId());
				}
			}
		}
	}
}
