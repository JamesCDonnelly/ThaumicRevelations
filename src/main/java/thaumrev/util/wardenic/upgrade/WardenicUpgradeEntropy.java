package thaumrev.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;

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
    EntityArrow entityArrow = (EntityArrow)event.source.getSourceOfDamage();

    float damage = random.nextInt(20) == 0 ? entityLivingBase.getMaxHealth() / 2 : random.nextInt(4);
    entityArrow.setDamage(0);

    if (entityArrow.getIsCritical()) {
      event.entity.attackEntityFrom(DamageSource.outOfWorld, damage * 2);

      if (event.entity instanceof EntityPlayer) Thaumcraft.addWarpToPlayer((EntityPlayer) event.entity, 1, true);
    } else {
      event.entity.attackEntityFrom(DamageSource.outOfWorld, damage * 2);
    }
  }

  @Override
  public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
    super.onAttack(stack, player, entity);
    EntityLivingBase entityLivingBase = (EntityLivingBase) entity;

    if (random.nextInt(20) <= 4) {
      entity.attackEntityFrom(
        DamageSource.outOfWorld,
        entityLivingBase.getMaxHealth() / 2
      );
    } else {
      entity.attackEntityFrom(
        DamageSource.outOfWorld,
        (random.nextInt(4)) * 2);
      entityLivingBase.addPotionEffect(new PotionEffect(Potion.wither.getId(), 20, 2));

      if (entity instanceof EntityPlayer) Thaumcraft.addWarpToPlayer((EntityPlayer) entity, 1, true);
    }
  }

  @Override
  public void onHurt(LivingHurtEvent event) {
    super.onHurt(event);

    if (event.entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) event.entity;

      if (random.nextInt(20) <= 4) {
        Thaumcraft.addWarpToPlayer(player, 1, true);
      }

      player.addExhaustion(event.ammount / 10);

      if (event.source.getEntity() != null) {
        Entity sourceEntity = event.source.getEntity();
        sourceEntity.attackEntityFrom(DamageSource.outOfWorld, random.nextInt(Math.round(event.ammount / 3)));

        event.ammount = 2;
      }
    }
  }
}
