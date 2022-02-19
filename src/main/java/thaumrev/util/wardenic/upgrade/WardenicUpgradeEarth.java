package thaumrev.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumrev.util.DamageSourceWarden;
import thaumrev.util.wardenic.WardenicChargeHelper;

import java.util.List;

public class WardenicUpgradeEarth extends WardenicUpgrade {

  public WardenicUpgradeEarth(Aspect aspect) {
    super(aspect);
  }

  @Override
  public void onIndirectAttack(LivingHurtEvent event) {
    super.onIndirectAttack(event);

    EntityLivingBase entityLivingBase = (EntityLivingBase)event.entity;
    EntityPlayer player = (EntityPlayer)event.source.getEntity();
    EntityArrow entityArrow = (EntityArrow)event.source.getSourceOfDamage();

    short count = WardenicChargeHelper.getWardenicArmorCount(player);

    if (entityArrow.getIsCritical()) {
      entityLivingBase.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 10 * (count + 1), 1));
      entityLivingBase.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 10 * (count + 1), 1));
    }
  }

  @Override
  public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
    super.onAttack(stack, player, entity);

    EntityLivingBase entityLivingBase = (EntityLivingBase) entity;

    short count = WardenicChargeHelper.getWardenicArmorCount(player);

    entityLivingBase.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 10 * (count + 1), 3));
    entityLivingBase.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 10 * (count + 1), 3));
    entityLivingBase.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 10 * (count + 1), 0));
  }

  @Override
  public void onHurt(LivingHurtEvent event) {
    super.onHurt(event);

    if (event.entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) event.entity;

      if (event.source.damageType.equals("fall")) {
        DamageSource damageSource = new DamageSourceWarden("warden", player);

        List entities = event.entity.worldObj.getEntitiesWithinAABBExcludingEntity(
          player,
          AxisAlignedBB.getBoundingBox(
            player.posX - 6,
            player.posY - 6,
            player.posZ - 6,
            player.posX + 6,
            player.posY + 6,
            player.posZ + 6));

        for (Object entity : entities) {
          if (entity instanceof Entity) {
            ((Entity) entity).attackEntityFrom(damageSource, 4);
          }
        }
      }

      short count = WardenicChargeHelper.getWardenicArmorCount(player);

      event.ammount *= 1 - (0.10F * count);
    }
  }
}
