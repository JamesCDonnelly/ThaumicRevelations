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

public class WardenicUpgradeWater extends WardenicUpgrade {

  public WardenicUpgradeWater(Aspect aspect) {
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
      entityLivingBase.addPotionEffect(new PotionEffect(Potion.poison.getId(), 40, count / 2));
    }
  }

  @Override
  public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
    super.onAttack(stack, player, entity);

    EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
    short count = WardenicChargeHelper.getWardenicArmorCount(player);

    entityLivingBase.addPotionEffect(new PotionEffect(Potion.poison.getId(), 40, count));
  }

  @Override
  public void onWornTick(World world, EntityPlayer player, ItemStack stack) {
    super.onWornTick(world, player, stack);

    short count = WardenicChargeHelper.getWardenicArmorCount(player);

    if (count == 4) {
      // player.addPotionEffect(new PotionEffect(<potion id>, <time>, <amplitude>)
      // amplitude is equal to potion_level - 1
      // time is provided in ticks (1 second = 20 ticks), so: time (in seconds) = time (in ticks) / 20
      player.addPotionEffect(new PotionEffect(Potion.waterBreathing.getId(), 0, 0));

      if (player.isPotionActive(Potion.poison.getId())) {
        player.removePotionEffect(Potion.poison.getId());
      }

      if (player.isInWater()) {
        player.moveFlying(0.0F, 1.0F, player.isSprinting() ? 0.02F : 0.01F);
      }
    }
  }
}
