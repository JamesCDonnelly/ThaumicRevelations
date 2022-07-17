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
import thaumrev.item.baubles.ItemWardenAmulet;
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

    ItemStack amulet = ItemWardenAmulet.getAmulet(player);

    // if (entityArrow.getIsCritical()) {
    //   entityArrow.setDamage(4 * (count + 2)); // TODO: Why it won't work?!!!

    //   entityLivingBase.addPotionEffect(new PotionEffect(Potion.confusion.id, 20 * (count + 1), 1));
    //   entityLivingBase.addPotionEffect(new PotionEffect(Potion.hunger.id, 40 * (count + 1), 1));
    // }
  }

  @Override
  public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
    super.onAttack(stack, player, entity);

    EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
    short count = WardenicChargeHelper.getWardenicArmorCount(player);

    entityLivingBase.addPotionEffect(new PotionEffect(Potion.confusion.id, 20 * (count + 1), 0));
    entityLivingBase.addPotionEffect(new PotionEffect(Potion.hunger.id, 40 * (count + 1), 0));
  }

  @Override
  public void onHurt(LivingHurtEvent event) {
    super.onHurt(event);

    EntityPlayer player = (EntityPlayer) event.entity;
    short count = WardenicChargeHelper.getWardenicArmorCount(player);

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
  public void onWornTick(World world, EntityPlayer player, ItemStack stack) {
    super.onWornTick(world, player, stack);
    // DEBUG System.out.println(stack.getDisplayName());

    short count = WardenicChargeHelper.getWardenicArmorCount(player);

    if (count == 4) {
      if (player.isPotionActive(Potion.moveSlowdown.getId())) {
        player.removePotionEffect(Potion.moveSlowdown.getId());
      }

      if (player.isPotionActive(Potion.confusion.getId())) {
        player.removePotionEffect(Potion.confusion.getId());
      }

      if (player.moveForward > 0.0F && player.isInWater()) {
        player.moveFlying(0.0F, 1.0F, -0.001F);
      }
    }
  }
}
