package thaumrev.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.monster.EntityFireBat;

public class WardenicUpgradeOrder extends WardenicUpgrade {

  public WardenicUpgradeOrder(Aspect aspect) {
    super(aspect);
  }

  private boolean isUndeadOrHell(Entity entity) {
    return ((EntityLivingBase)entity).isEntityUndead() ||
      entity instanceof EntityGhast ||
      entity instanceof EntityFireBat ||
      entity instanceof EntityBlaze;
  }

  @Override
  public void onIndirectAttack(LivingHurtEvent event) {
    super.onIndirectAttack(event);

    Entity entity = event.entity;
    EntityPlayer player = (EntityPlayer)event.source.getEntity();
    EntityArrow entityArrow = (EntityArrow)event.source.getSourceOfDamage();

    if (isUndeadOrHell(entity)) {
      DamageSource damageSource = DamageSource.causeThornsDamage(player);
      damageSource.setDamageBypassesArmor();

      if (entityArrow.getIsCritical()) {
        entity.attackEntityFrom(damageSource, 4.0F);
      } else {
        entity.attackEntityFrom(damageSource, 2.0F);
      }
    }
  }

  @Override
  public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
    super.onAttack(stack, player, entity);

    if (isUndeadOrHell(entity)) {
      DamageSource damageSource = DamageSource.causeThornsDamage(player);
      damageSource.setDamageBypassesArmor();

      entity.attackEntityFrom(damageSource, 4.0F);
    }
  }

  @Override
  public void onHurt(LivingHurtEvent event) {
    super.onHurt(event);

    if (event.entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) event.entity;

      event.ammount /= 2;

      Entity sourceEntity = event.source.getEntity();

      DamageSource damageSource = DamageSource.causeThornsDamage(player);
      damageSource.setDamageBypassesArmor();

      if (isUndeadOrHell(sourceEntity)) {
        sourceEntity.attackEntityFrom(damageSource, (event.ammount));
      }
    }
  }

  @Override
  public void onWornTick(World world, EntityPlayer player, ItemStack stack) {
    super.onWornTick(world, player, stack);

    player.removePotionEffect(Config.potionUnHungerID);
    player.removePotionEffect(Config.potionBlurredID);
    player.removePotionEffect(Potion.wither.getId());
    player.removePotionEffect(Potion.blindness.getId());
  }
}
