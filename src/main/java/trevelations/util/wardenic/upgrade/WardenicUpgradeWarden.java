package trevelations.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.monster.EntityEldritchCrab;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.entities.monster.EntityInhabitedZombie;
import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
import trevelations.common.ThaumRevLibrary;
import trevelations.util.DamageSourceWarden;
import trevelations.util.wardenic.WardenicChargeHelper;

public class WardenicUpgradeWarden extends WardenicUpgrade {

	public WardenicUpgradeWarden(Aspect aspect) {super(aspect);}

	@Override
	public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
		super.onAttack(stack, player, entity);
		if (isEldritchOrTainted(entity)) {
			DamageSource damageSource = new DamageSourceWarden("warden", player);
			entity.attackEntityFrom(damageSource, 20);
		}
	}

	public static boolean isEldritchOrTainted(Entity entity) {
		return entity instanceof EntityEldritchGuardian ||
				entity instanceof EntityEldritchCrab ||
				entity instanceof EntityEldritchWarden ||
				entity instanceof EntityEldritchGolem ||
				entity instanceof EntityInhabitedZombie ||
				entity instanceof EntityEnderman ||
				entity instanceof EntityDragon ||
				entity instanceof ITaintedMob;
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
		}
	}

}
