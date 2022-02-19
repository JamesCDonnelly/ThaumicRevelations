package thaumrev.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.config.Config;
import thaumrev.util.wardenic.WardenicChargeHelper;

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

    EntityPlayer player = (EntityPlayer) event.entity;
    short count = WardenicChargeHelper.getWardenicArmorCount(player);

    if (event.source.getEntity() != null) {
      event.source.getEntity().setFire(2);
    }

    if (event.source.isFireDamage()) {
      event.ammount *= 1 - (0.25F * count);
    } else if (event.source.isExplosion()) {
      event.ammount *= 1 - (0.15F * count);
    }
  }

  @Override
  public void onWornTick(World world, EntityPlayer player, ItemStack stack) {
    super.onWornTick(world, player, stack);

    short count = WardenicChargeHelper.getWardenicArmorCount(player);

    if (count == 4) {
      if (player.isPotionActive(Config.potionSunScornedID)) {
        player.removePotionEffect(Config.potionSunScornedID);
      }

      if (player.isBurning()) {
        player.extinguish();
      }
    }
  }
}
