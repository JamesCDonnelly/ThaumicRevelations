package thaumrev.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.config.Config;
import thaumrev.util.DamageSourceWarden;

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

    if (isEldritchOrTainted(entity)) {
      DamageSource damageSource = new DamageSourceWarden("warden", player);

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

    if (isEldritchOrTainted(entity)) {
      DamageSource damageSource = new DamageSourceWarden("warden", player);

      entity.attackEntityFrom(damageSource, 4.0F);
    }
  }

  @Override
  public void onHurt(LivingHurtEvent event) {
    super.onHurt(event);

    if (event.entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) event.entity;
      Entity sourceEntity = event.source.getEntity();
      DamageSource damageSource = new DamageSourceWarden("warden", player);

      event.ammount /= 2;

      if (isEldritchOrTainted(sourceEntity)) {
        sourceEntity.attackEntityFrom(damageSource, event.ammount);
      }
    }
  }

  @Override
  public void onWornTick(World world, EntityPlayer player, ItemStack stack) {
    super.onWornTick(world, player, stack);

    player.removePotionEffect(Config.potionVisExhaustID);
    player.removePotionEffect(Config.potionInfVisExhaustID);
    player.removePotionEffect(Config.potionDeathGazeID);
    player.removePotionEffect(Config.potionTaintPoisonID);
    player.removePotionEffect(Potion.wither.getId());
  }

  @Override
  public void onEquipped(ItemStack stack, EntityLivingBase entityLivingBase) {
    super.onEquipped(stack, entityLivingBase);

    entityLivingBase.worldObj.playSoundAtEntity(entityLivingBase, "thaumrev:compramos", 1.0F, 1.0F);
  }
}
