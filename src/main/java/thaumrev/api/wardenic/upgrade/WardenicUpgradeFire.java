package thaumrev.api.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.config.Config;
import thaumrev.api.wardenic.WardenicChargeHelper;

public class WardenicUpgradeFire extends WardenicUpgrade {

  public WardenicUpgradeFire(Aspect aspect) {
    super(aspect);
  }

  @Override
  public void onIndirectAttack(LivingHurtEvent event) {
    super.onIndirectAttack(event);

    Entity entity = event.entity;
    EntityPlayer player = (EntityPlayer)event.source.getEntity();
    EntityArrow entityArrow = (EntityArrow)event.source.getSourceOfDamage();
    short count = WardenicChargeHelper.getWardenicArmorCount(player);

    if (entityArrow.getIsCritical()) {
      entity.setFire(4 * (count + 1));
    }
  }

  @Override
  public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
    super.onAttack(stack, player, entity);

    short count = WardenicChargeHelper.getWardenicArmorCount(player);

    entity.setFire(4 * (count + 1));
  }

  @Override
  public void onHurt(LivingHurtEvent event) {
    super.onHurt(event);

    Entity sourceEntity = event.source.getEntity();

    if (sourceEntity != null) {
      sourceEntity.setFire(2);
    }

    if (event.source.isFireDamage()) {
      event.setCanceled(true);
    } else if (event.source.isExplosion()) {
      event.ammount /= 3;
    }
  }

  @Override
  public void onWornTick(ItemStack amulet, EntityLivingBase player) {
    super.onWornTick(amulet, player);

    player.removePotionEffect(Config.potionSunScornedID);
    player.extinguish();
  }
}
