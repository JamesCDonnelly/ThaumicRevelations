package thaumrev.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumrev.util.DamageSourceWarden;
import thaumrev.util.wardenic.WardenicChargeHelper;

import java.util.ArrayList;
import java.util.List;

public class WardenicUpgradeEarth extends WardenicUpgrade {

	public WardenicUpgradeEarth(Aspect aspect) {
		super(aspect);
	}

	@Override
	public void onIndirectAttack(LivingHurtEvent event) {
		super.onIndirectAttack(event);

		EntityLivingBase entityLivingBase = (EntityLivingBase)event.entity;
		EntityPlayer player = (EntityPlayer)event.source.getEntity();
		EntityArrow entityArrow = (EntityArrow)event.source.getSourceOfDamage();

		int count = 0;

		for (int i = 0; i < 4; i++) {
			if ((player.getCurrentArmor(i) != null) &&
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
							.equals(Aspect.EARTH.getName())) {
				count++;
			}
		}

		if (entityArrow.getIsCritical()) {
			entityLivingBase.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 10 * (count + 1), 1));
			entityLivingBase.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 10 * (count + 1), 1));
		}
	}

	@Override
	public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
		super.onAttack(stack, player, entity);

		EntityLivingBase entityLivingBase = (EntityLivingBase) entity;

		int count = 0;

		for (int i = 0; i < 4; i++) {
			if ((player.getCurrentArmor(i) != null) &&
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
							.equals(Aspect.EARTH.getName())) {
				count++;
			}
		}

		entityLivingBase.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 10 * (count + 1), 3));
		entityLivingBase.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 10 * (count + 1), 3));
		entityLivingBase.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 10 * (count + 1), 0));
	}

	@Override
	public void onAttacked(LivingHurtEvent event) {
		super.onAttacked(event);

		int count = 0;

		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;

			if (event.source.damageType.equals("fall")) {
				List entities = new ArrayList<Entity>();
				DamageSource damageSource = new DamageSourceWarden("warden", event.entity);

				entities = event.entity.worldObj.getEntitiesWithinAABBExcludingEntity(
						event.entity,
						AxisAlignedBB.getBoundingBox(
								event.entity.posX - 6,
								event.entity.posY - 6,
								event.entity.posZ - 6,
								event.entity.posX + 6,
								event.entity.posY + 6,
								event.entity.posZ + 6));

				for (Object entity : entities) {
					if (entity instanceof Entity) {
						((Entity) entity).attackEntityFrom(damageSource, 4);
					}
				}
			}

			for (int i = 0; i < 4; i++) {
				if ((player.getCurrentArmor(i) != null) &&
						WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
								.equals(Aspect.EARTH.getName())) {
					count++;
				}
			}

			event.ammount *= 1 - (0.10F * count);
		}
	}
}
