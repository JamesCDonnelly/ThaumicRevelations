package thaumrev.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumrev.util.DamageSourceWarden;
import thaumrev.util.wardenic.WardenicChargeHelper;

import java.util.Random;

public class WardenicUpgradeEntropy extends WardenicUpgrade {

	Random random = new Random();

	public WardenicUpgradeEntropy(Aspect aspect) {
		super(aspect);
	}

	@Override
	public void onIndirectAttack(LivingHurtEvent event) {
		super.onIndirectAttack(event);

		EntityLivingBase entityLivingBase = (EntityLivingBase)event.entity;
		EntityPlayer player = (EntityPlayer)event.source.getEntity();
		EntityArrow entityArrow = (EntityArrow)event.source.getSourceOfDamage();

		DamageSource damageSource = new DamageSourceWarden("warden", player);

		int count = 0;

		for (int i = 0; i < 4; i++) {
			if ((player.getCurrentArmor(i) != null) &&
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
							.equals(Aspect.ENTROPY.getName())) {
				count++;
			}
		}

		if (entityArrow.getIsCritical()) {
			entityArrow.setDamage(0);
			entityLivingBase.attackEntityFrom(damageSource,
					random.nextInt(20) <= 2 * (count + 1) ?
							entityLivingBase.getMaxHealth() :
							(random.nextInt(4) + 1) * (count + 1));
		}
	}

	@Override
	public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
		super.onAttack(stack, player, entity);

		int count = 0;

		for (int i = 0; i < 4; i++) {
			if ((player.getCurrentArmor(i) != null) &&
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
							.equals(Aspect.ENTROPY.getName())) {
				count++;
			}
		}

		EntityLivingBase entityLivingBase = (EntityLivingBase)entity;

		DamageSource damageSource = new DamageSourceWarden("warden", player);

		if (count < 4) {
			if (random.nextInt(100) == 0) {
				entity.attackEntityFrom(damageSource,
						entityLivingBase.getMaxHealth());
			} else {
				entity.attackEntityFrom(damageSource,
						(random.nextInt(4) + 1) * (count + 1));
			}
		} else {
			if (random.nextInt(10) == 0) {
				entity.attackEntityFrom(damageSource,
						entityLivingBase.getMaxHealth());
			} else {
				entity.attackEntityFrom(damageSource,
						(random.nextInt(4) + 1) * (count + 1));
			}
		}
	}

	@Override
	public void onAttacked(LivingHurtEvent event) {
		super.onAttacked(event);

		int count = 0;

		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;

			for (int i = 0; i < 4; i++) {
				if ((player.getCurrentArmor(i) != null) &&
						WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
								.equals(Aspect.ENTROPY.getName())) {
					count++;
				}
			}

			if (random.nextInt(20) <= count) {
				Thaumcraft.addWarpToPlayer(player, 1, true);
			}

			if (event.source.getSourceOfDamage() != null) {
				Entity sourceEntity = event.source.getEntity();
				DamageSource damageSource = new DamageSourceWarden("warden", player);
				sourceEntity.attackEntityFrom(damageSource, event.ammount * (random.nextInt(4) * (count + 1)) / 4F);
			}

			event.ammount *= 1 + random.nextFloat() * (count + 1) / 12F;
		}
	}
}
