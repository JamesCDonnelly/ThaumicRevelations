package trevelations.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import trevelations.util.DamageSourceWarden;
import trevelations.util.wardenic.WardenicChargeHelper;

import java.util.Random;

public class WardenicUpgradeEntropy extends WardenicUpgrade {

	Random random = new Random();

	public WardenicUpgradeEntropy(Aspect aspect) {
		super(aspect);
	}

	@Override
	public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
		super.onAttack(stack, player, entity);

		int count = 0;

		for (int i = 0; i <= 3; i++) {
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
						MathHelper.getRandomIntegerInRange(random, 1, 4) * count);
			}
		} else {
			if (random.nextInt(10) == 0) {
				entity.attackEntityFrom(damageSource,
						entityLivingBase.getMaxHealth());
			} else {
				entity.attackEntityFrom(damageSource,
						MathHelper.getRandomIntegerInRange(random, 1, 4) * count);
			}
		}
	}

	@Override
	public void onAttacked(LivingHurtEvent event) {
		super.onAttacked(event);

		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			int count = 0;

			for (int i = 0; i <= 3; i++) {
				if ((player.getCurrentArmor(i) != null) &&
						WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
								.equals(Aspect.ENTROPY.getName())) {
					count++;
				}
			}

			Entity sourceEntity = event.source.getEntity();

			DamageSource damageSource = new DamageSourceWarden("warden", player);

			sourceEntity.attackEntityFrom(damageSource,
				MathHelper.getRandomIntegerInRange(random, 1, 4) * count);

			event.ammount *= 1 + (float)(MathHelper.getRandomDoubleInRange(random, 0, 0.5D) * count);
		}
	}
}
