package thaumrev.util.wardenic.upgrade;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumrev.util.AttributeHelper;
import thaumrev.util.wardenic.WardenicChargeHelper;

import static thaumrev.ThaumRevLibrary.ATTRIBUTE_MODIFIER_UUID;

public class WardenicUpgradeAir extends WardenicUpgrade {

  public WardenicUpgradeAir(Aspect aspect) {
    super(aspect);
  }

  @Override
  public void onIndirectAttack(LivingHurtEvent event) {
    super.onIndirectAttack(event);

    EntityLivingBase entityLivingBase = (EntityLivingBase)event.entity;
    EntityArrow entityArrow = (EntityArrow)event.source.getSourceOfDamage();

    if (entityArrow.getIsCritical()) {
      entityArrow.setDamage(entityArrow.getDamage() + 8.0D);

      entityLivingBase.addPotionEffect(new PotionEffect(Potion.confusion.id, 20, 1));
      entityLivingBase.addPotionEffect(new PotionEffect(Potion.hunger.id, 20, 1));
    } else {
      entityArrow.setDamage(entityArrow.getDamage() + 4.0D);
    }
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

    player.removePotionEffect(Potion.moveSlowdown.getId());
    player.removePotionEffect(Potion.confusion.getId());
  }

  @Override
  public void onEquipped(ItemStack stack, EntityLivingBase entityLivingBase) {
    super.onEquipped(stack, entityLivingBase);

    AttributeHelper.addAttributeModToLiving(
      entityLivingBase,
      SharedMonsterAttributes.movementSpeed,
      new UUID(ATTRIBUTE_MODIFIER_UUID, 4),
      "TR_MOVEMENT_SPEED",
      0.1F,
      0
    );

    entityLivingBase.stepHeight += 0.5F;
  }

  @Override
  public void onUnequipped(ItemStack stack, EntityLivingBase entityLivingBase) {
    super.onUnequipped(stack, entityLivingBase);
    
    AttributeHelper.removeAttributeModFromLiving(
      entityLivingBase,
      SharedMonsterAttributes.movementSpeed,
      new UUID(ATTRIBUTE_MODIFIER_UUID, 4),
      "TR_MOVEMENT_SPEED",
      0.1F,
      0
    );

    entityLivingBase.stepHeight -= 0.5F;
  }
}
