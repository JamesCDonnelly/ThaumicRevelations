package thaumrev.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumrev.util.wardenic.WardenicChargeHelper;

public class WardenicUpgradeAir extends WardenicUpgrade {

  public WardenicUpgradeAir(Aspect aspect) {
    super(aspect);
  }

  @Override
  public void onIndirectAttack(LivingHurtEvent event) {
    super.onIndirectAttack(event);

    EntityLivingBase entityLivingBase = (EntityLivingBase)event.entity;
    EntityPlayer player = (EntityPlayer)event.source.getEntity();
    EntityArrow entityArrow = (EntityArrow)event.source.getSourceOfDamage();

    int count = 0;

    for (int i = 0; i <= 3; i++) {
      if ((player.getCurrentArmor(i) != null) &&
        WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
          .equals(Aspect.AIR.getName())) {
        count++;
      }
    }

    if (entityArrow.getIsCritical()) {
      entityArrow.setDamage(4 * (count + 2)); //TODO: Why it won't work?!!!

      entityLivingBase.addPotionEffect(new PotionEffect(Potion.confusion.id, 20 * (count + 1), 1));
      entityLivingBase.addPotionEffect(new PotionEffect(Potion.hunger.id, 40 * (count + 1), 1));
    }
  }

  @Override
  public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
    super.onAttack(stack, player, entity);

    EntityLivingBase entityLivingBase = (EntityLivingBase)entity;

    int count = 0;

    for (int i = 0; i <= 3; i++) {
      if ((player.getCurrentArmor(i) != null) &&
        WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
          .equals(Aspect.AIR.getName())) {
        count++;
      }
    }

    entityLivingBase.addPotionEffect(new PotionEffect(Potion.confusion.id, 20 * (count + 1), 0));
    entityLivingBase.addPotionEffect(new PotionEffect(Potion.hunger.id, 40 * (count + 1), 0));
  }

  @Override
  public void onHurt(LivingHurtEvent event) {
    super.onHurt(event);

    int count = 0;
    EntityPlayer player = (EntityPlayer)event.entity;

    for (int i = 0; i < 4; i++) {
      if ((player.getCurrentArmor(i) != null) &&
        WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
          .equals(Aspect.AIR.getName())) {
        count++;
      }
    }

    if (event.source.damageType.equals("arrow") ||
      event.source.damageType.equals("thrown")) {
      event.ammount *= 1 - (0.15F * count);
    } else if (event.source.damageType.equals("fall")) {
      player.fallDistance -= 0.25F;

      if (count < 4) {
        event.ammount *= 1 - (0.25F * count);
      } else {
        event.setCanceled(true);
      }
    }
  }

  @Override
  public void onTick(World world, EntityPlayer player, ItemStack stack) {
    super.onTick(world, player, stack);

    int count = 0;

    for (int i = 0; i < 4; i++) {
      if ((player.getCurrentArmor(i) != null) &&
        WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
          .equals(Aspect.AIR.getName())) {
        count++;
      }
    }

    if (count == 4) {
      if (player.isPotionActive(Potion.moveSlowdown.getId())) {
        player.removePotionEffect(Potion.moveSlowdown.getId());
      }
      if (player.isPotionActive(Potion.confusion.getId())) {
        player.removePotionEffect(Potion.confusion.getId());
      }
    }
  }
}
