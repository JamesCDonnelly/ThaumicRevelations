package thaumrev.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.monster.EntityFireBat;
import thaumrev.util.DamageSourceWarden;
import thaumrev.util.wardenic.WardenicChargeHelper;

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
    short count = WardenicChargeHelper.getWardenicArmorCount(player);

    if (entityArrow.getIsCritical()) {
      if (isUndeadOrHell(entity)) {
        DamageSource damageSource = new DamageSourceWarden("warden", player);

        entity.attackEntityFrom(damageSource, 8 * (count + 1));
      }
    }
  }

  @Override
  public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
    super.onAttack(stack, player, entity);

    short count = WardenicChargeHelper.getWardenicArmorCount(player);

    if (isUndeadOrHell(entity)) {
      DamageSource damageSource = new DamageSourceWarden("warden", player);

      entity.attackEntityFrom(damageSource, 8 * (count + 1));
    }
  }

  @Override
  public void onHurt(LivingHurtEvent event) {
    super.onHurt(event);

    if (event.entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) event.entity;
      short count = WardenicChargeHelper.getWardenicArmorCount(player);

      if (count == 4) {
        if (isUndeadOrHell(event.source.getEntity())) {
          event.setCanceled(true);
        }
      }

      Entity sourceEntity = event.source.getEntity();

      DamageSource damageSource = new DamageSourceWarden("warden", player);

      if (isUndeadOrHell(sourceEntity)) {
        sourceEntity.attackEntityFrom(damageSource, (event.ammount * count));
      }
    }
  }

  @Override
  public void onWornTick(World world, EntityPlayer player, ItemStack stack) {
    super.onWornTick(world, player, stack);

    short count = WardenicChargeHelper.getWardenicArmorCount(player);

    if (count == 4) {
      if (player.isPotionActive(Config.potionUnHungerID)) {
        player.removePotionEffect(Config.potionUnHungerID);
      }
      if (player.isPotionActive(Config.potionBlurredID)) {
        player.removePotionEffect(Config.potionBlurredID);
      }
      if (player.isPotionActive(Potion.wither.getId())) {
        player.removePotionEffect(Potion.wither.getId());
      }
      if (player.isPotionActive(Potion.blindness.getId())) {
        player.removePotionEffect(Potion.blindness.getId());
      }

      player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 0, 0));
    }
  }
}
