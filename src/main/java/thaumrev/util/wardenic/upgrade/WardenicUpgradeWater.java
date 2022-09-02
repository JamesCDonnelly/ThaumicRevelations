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

public class WardenicUpgradeWater extends WardenicUpgrade {

  public WardenicUpgradeWater(Aspect aspect) {
    super(aspect);
  }

  @Override
  public void onIndirectAttack(LivingHurtEvent event) {
    super.onIndirectAttack(event);

    EntityLivingBase entityLivingBase = (EntityLivingBase)event.entity;
    EntityArrow entityArrow = (EntityArrow)event.source.getSourceOfDamage();

    if (entityArrow.getIsCritical()) {
      entityLivingBase.addPotionEffect(new PotionEffect(Potion.poison.getId(), 20, 2));
    }
  }

  @Override
  public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
    super.onAttack(stack, player, entity);

    EntityLivingBase entityLivingBase = (EntityLivingBase) entity;

    entityLivingBase.addPotionEffect(new PotionEffect(Potion.poison.getId(), 20 * 2, 2));
  }

  @Override
  public void onWornTick(World world, EntityPlayer player, ItemStack stack) {
    super.onWornTick(world, player, stack);

    // player.addPotionEffect(new PotionEffect(<potion id>, <time>, <amplitude>)
    // amplitude is equal to potion_level - 1
    // time is provided in ticks (1 second = 20 ticks), so: time (in seconds) = time (in ticks) / 20
    player.addPotionEffect(new PotionEffect(Potion.waterBreathing.getId(), 0, 0));
    player.removePotionEffect(Potion.poison.getId());
    

    if (player.isInWater()) {
      player.moveFlying(0.0F, 1.0F, player.isSprinting() ? 0.02F : 0.01F);
    }
  }

  @Override
  public void onEquipped(ItemStack stack, EntityLivingBase entityLivingBase) {
    super.onEquipped(stack, entityLivingBase);

    entityLivingBase.stepHeight += 0.5F;
  }

  @Override
  public void onUnequipped(ItemStack stack, EntityLivingBase entityLivingBase) {
    super.onUnequipped(stack, entityLivingBase);

    entityLivingBase.stepHeight -= 0.5F;
  }
}
