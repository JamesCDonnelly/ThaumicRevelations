package thaumrev.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import thaumcraft.common.entities.monster.EntityEldritchCrab;
import thaumcraft.common.entities.monster.EntityInhabitedZombie;
import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
import thaumcraft.common.entities.projectile.EntityEldritchOrb;
import thaumrev.util.DamageSourceWarden;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;

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

	@Override
	public void onTick(World world, EntityPlayer player, ItemStack stack) {
		super.onTick(world, player, stack);
		if (player.isPotionActive(Config.potionDeathGazeID)) {
			if (random.nextInt(5) == 0) {
				player.removePotionEffect(Config.potionDeathGazeID);
			}
		}

		if (player.isPotionActive(Config.potionTaintPoisonID)) {
			if (random.nextInt(5) == 0) {
				player.removePotionEffect(Config.potionTaintPoisonID);
			}
		}

		if (player.isPotionActive(Potion.wither.getId())) {
			if (random.nextInt(5) == 0) {
				player.removePotionEffect(Potion.wither.getId());
			}
		}
	}

	@Override
	public void onAttacked(LivingHurtEvent event) {
		super.onAttacked(event);
		if (isEldritchOrTainted(event.source.getEntity())) {
			event.setCanceled(true);
		}
	}

	public static boolean isEldritchOrTainted(Entity entity) {
		return entity instanceof EntityEldritchGuardian ||
				entity instanceof EntityEldritchCrab ||
				entity instanceof EntityEldritchWarden ||
				entity instanceof EntityEldritchGolem ||
				entity instanceof EntityInhabitedZombie ||
				entity instanceof ITaintedMob;
	}

}
