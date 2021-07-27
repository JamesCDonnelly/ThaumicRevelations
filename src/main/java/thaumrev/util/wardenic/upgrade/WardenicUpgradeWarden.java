package thaumrev.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.config.Config;
import thaumrev.ThaumRevLibrary;
import thaumrev.util.DamageSourceWarden;
import thaumrev.util.wardenic.WardenicChargeHelper;

import static thaumrev.util.PurityHelper.isEldritchOrTainted;

public class WardenicUpgradeWarden extends WardenicUpgrade {

	public WardenicUpgradeWarden(Aspect aspect) {
		super(aspect);
	}

	@Override
	public void onIndirectAttack(LivingHurtEvent event) {
		super.onIndirectAttack(event);

		Entity entity = event.entity;
		EntityPlayer player = (EntityPlayer)event.source.getEntity();
		EntityArrow entityArrow = (EntityArrow)event.source.getSourceOfDamage();

		int count = 0;

		for (int i = 0; i < 4; i++) {
			if ((player.getCurrentArmor(i) != null) &&
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
							.equals(ThaumRevLibrary.EXCUBITOR.getName())) {
				count++;
			}
		}

		if (entityArrow.getIsCritical()) {
			if (isEldritchOrTainted(entity)) {
				DamageSource damageSource = new DamageSourceWarden("warden", player);

				entity.attackEntityFrom(damageSource, 8 * (count + 1));
			}
		}
	}

	@Override
	public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
		super.onAttack(stack, player, entity);

		int count = 0;

		for (int i = 0; i < 4; i++) {
			if ((player.getCurrentArmor(i) != null) &&
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
							.equals(ThaumRevLibrary.EXCUBITOR.getName())) {
				count++;
			}
		}

		if (isEldritchOrTainted(entity)) {
			DamageSource damageSource = new DamageSourceWarden("warden", player);

			entity.attackEntityFrom(damageSource, 8 * (count + 1));
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
								.equals(ThaumRevLibrary.EXCUBITOR.getName())) {
					count++;
				}
			}

			if (count == 4) {
				if (isEldritchOrTainted(event.source.getEntity())) {
					event.setCanceled(true);
				}
			}

			Entity sourceEntity = event.source.getEntity();

			DamageSource damageSource = new DamageSourceWarden("warden", player);

			if (isEldritchOrTainted(sourceEntity)) {
				sourceEntity.attackEntityFrom(damageSource, (event.ammount * count));
			}
		}
	}

	@Override
	public void onTick(World world, EntityPlayer player, ItemStack stack) {
		super.onTick(world, player, stack);

		int count = 0;

		for (int i = 0; i <= 3; i++) {
			if ((player.getCurrentArmor(i) != null) &&
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
							.equals(ThaumRevLibrary.EXCUBITOR.getName())) {
				count++;
			}
		}

		if (count == 4) {
			if (player.isPotionActive(Config.potionVisExhaustID)) {
				player.removePotionEffect(Config.potionVisExhaustID);
			}
			if (player.isPotionActive(Config.potionInfVisExhaustID)) {
				player.removePotionEffect(Config.potionInfVisExhaustID);
			}
			if (player.isPotionActive(Config.potionDeathGazeID)) {
				player.removePotionEffect(Config.potionDeathGazeID);
			}
			if (player.isPotionActive(Config.potionTaintPoisonID)) {
				player.removePotionEffect(Config.potionTaintPoisonID);
			}
			if (player.isPotionActive(Potion.wither.getId())) {
				player.removePotionEffect(Potion.wither.getId());
			}
		}
	}
}
