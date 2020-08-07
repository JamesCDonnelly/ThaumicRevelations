package trevelations.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import trevelations.util.DamageSourceWarden;
import trevelations.util.wardenic.WardenicChargeHelper;

import java.util.ArrayList;
import java.util.List;

public class WardenicUpgradeEarth extends WardenicUpgrade {

	public WardenicUpgradeEarth(Aspect aspect) {
		super(aspect);
	}

	/*@Override
	public void onTick(World world, EntityPlayer player, ItemStack stack) {
		super.onTick(world, player, stack);
		player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 0, 5));
	}*/

	@Override
	public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
		super.onAttack(stack, player, entity);

		EntityLivingBase entityLivingBase = (EntityLivingBase) entity;

		int count = 0;

		for (int i = 0; i <= 3; i++) {
			if ((player.getCurrentArmor(i) != null) &&
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
							.equals(Aspect.EARTH.getName())) {
				count++;
			}
		}

		if (count == 4) {
			entityLivingBase.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 40, 3));
			entityLivingBase.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 40, 3));
		} else {
			entityLivingBase.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 40, 0));
			entityLivingBase.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 40, 0));
		}
	}

	@Override
	public void onAttacked(LivingHurtEvent event) {
		super.onAttacked(event);

		if (event.source.damageType == "fall") {
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
	}
}
