package trevelations.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import trevelations.util.wardenic.WardenicChargeHelper;

public class WardenicUpgradeAir extends WardenicUpgrade {

	public WardenicUpgradeAir(Aspect aspect) {
		super(aspect);
	}

	@Override
	public void onIndirectAttack(LivingHurtEvent event) {
		super.onIndirectAttack(event);

		EntityLivingBase entityLivingBase = (EntityLivingBase)event.entity;
		EntityPlayer player = (EntityPlayer)event.source.getEntity();
		EntityArrow entityArrow = (EntityArrow)event.source.getSourceOfDamage();

		int count = 0;

		for (int i = 0; i <= 3; i++) {
			if ((player.getCurrentArmor(i) != null) &&
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
							.equals(Aspect.AIR.getName())) {
				count++;
			}
		}

		if (entityArrow.getIsCritical()) {
			entityArrow.setDamage(2 * (count + 2));

			entityLivingBase.addPotionEffect(new PotionEffect(Potion.confusion.id, 20 * (count + 1), 1));
			entityLivingBase.addPotionEffect(new PotionEffect(Potion.hunger.id, 40 * (count + 1), 1));
		}
	}

	@Override
	public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
		super.onAttack(stack, player, entity);

		EntityLivingBase entityLivingBase = (EntityLivingBase)entity;

		int count = 0;

		for (int i = 0; i <= 3; i++) {
			if ((player.getCurrentArmor(i) != null) &&
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
							.equals(Aspect.AIR.getName())) {
				count++;
			}
		}

		entityLivingBase.addPotionEffect(new PotionEffect(Potion.confusion.id, 20 * (count + 1), 0));
		entityLivingBase.addPotionEffect(new PotionEffect(Potion.hunger.id, 40 * (count + 1), 0));
	}

	@Override
	public void onAttacked(LivingHurtEvent event) {
		super.onAttacked(event);

		int count = 0;
		EntityPlayer player = (EntityPlayer)event.entity;

		for (int i = 0; i <= 3; i++) {
			if ((player.getCurrentArmor(i) != null) &&
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
							.equals(Aspect.AIR.getName())) {
				count++;
			}
		}

		if (event.source.damageType.equals("arrow") ||
				event.source.damageType.equals("thrown")) {
			event.ammount *= 1 - (0.15F * count);
		}
	}

	@Override
	public void onTick(World world, EntityPlayer player, ItemStack stack) {
		float helmetBoost;
		float chestBoost;
		float legsBoost;
		float bootsBoost;

		if (!(player.isInWater())) {
			if (player.getCurrentArmor(3) != null) {
				ItemStack helm = player.getCurrentArmor(3);
				if (WardenicChargeHelper.getUpgrade(helm).getUpgradeAspect().equals(Aspect.AIR.getName())) {
					helmetBoost = 0.025F;
				} else {
					helmetBoost = 0;
				}
			} else {
				helmetBoost = 0;
			}
			if (player.getCurrentArmor(2) != null) {
				ItemStack chest = player.getCurrentArmor(2);
				if (WardenicChargeHelper.getUpgrade(chest).getUpgradeAspect().equals(Aspect.AIR.getName())) {
					chestBoost = 0.025F;
				} else {
					chestBoost = 0;
				}
			} else {
				chestBoost = 0;
			}
			if (player.getCurrentArmor(1) != null) {
				ItemStack legs = player.getCurrentArmor(1);
				if (WardenicChargeHelper.getUpgrade(legs).getUpgradeAspect().equals(Aspect.AIR.getName())) {
					legsBoost = 0.025F;
				} else {
					legsBoost = 0;
				}
			} else {
				legsBoost = 0;
			}
			if (player.getCurrentArmor(0) != null) {
				ItemStack boots = player.getCurrentArmor(0);
				if (WardenicChargeHelper.getUpgrade(boots).getUpgradeAspect().equals(Aspect.AIR.getName())) {
					bootsBoost = 0.025F;
					player.stepHeight = 1F;
				} else {
					bootsBoost = 0;
					player.stepHeight = 0.5F;
				}
			} else {
				bootsBoost = 0;
				player.stepHeight = 0.5F;
			}
		} else {
			helmetBoost = 0;
			chestBoost = 0;
			legsBoost = 0;
			bootsBoost = 0;
			player.stepHeight = 0.5F;
		}

		if ((helmetBoost + chestBoost + legsBoost + bootsBoost) == 0.1F) {
			if (player.isPotionActive(Potion.moveSlowdown.getId())) {
				player.removePotionEffect(Potion.moveSlowdown.getId());
			}
			if (player.isPotionActive(Potion.confusion.getId())) {
				player.removePotionEffect(Potion.confusion.getId());
			}
		}

		player.fallDistance = player.fallDistance * (1 - 10 * (helmetBoost + chestBoost + legsBoost + bootsBoost));
		player.capabilities.setPlayerWalkSpeed(0.1F + helmetBoost + chestBoost + legsBoost + bootsBoost);
	}
}
