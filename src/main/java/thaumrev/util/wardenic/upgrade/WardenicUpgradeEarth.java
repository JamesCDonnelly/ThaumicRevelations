package thaumrev.util.wardenic.upgrade;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumrev.util.AttributeHelper;
import thaumrev.util.DamageSourceWarden;
import thaumrev.util.wardenic.WardenicChargeHelper;

import java.util.List;
import java.util.UUID;

import static thaumrev.ThaumRevLibrary.ATTRIBUTE_MODIFIER_UUID;

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
      entityLivingBase.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 20 * (count + 1), 1));
      entityLivingBase.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 20 * (count + 1), 1));
    }
  }

  @Override
  public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
    super.onAttack(stack, player, entity);

    EntityLivingBase entityLivingBase = (EntityLivingBase) entity;

    entityLivingBase.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 20 * 2, 3));
    entityLivingBase.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 20 * 2, 3));
  }

  @Override
  public void onHurt(LivingHurtEvent event) {
    super.onHurt(event);

    if (event.entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) event.entity;

      short count = WardenicChargeHelper.getWardenicArmorCount(player);

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
          if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase) entity).attackEntityFrom(damageSource, count);
          }
        }
      }

      Block block = event.entity.worldObj.getBlock((int) Math.round(player.posX), (int) Math.round(player.posY - 1), (int) Math.round(player.posZ));
      
      if (count == 4 && (block instanceof BlockDirt || block instanceof BlockGrass)) {
        event.setCanceled(true);
      }
    }
  }

  @Override
  public void onEquipped(ItemStack stack, EntityLivingBase entityLivingBase) {
    super.onEquipped(stack, entityLivingBase);
    
    if (entityLivingBase instanceof EntityPlayer) {
      AttributeHelper.addAttributeModToLiving(
        entityLivingBase,
        SharedMonsterAttributes.knockbackResistance,
        new UUID(ATTRIBUTE_MODIFIER_UUID, 3),
        "TR_KNOCKBACK_RESISTANCE",
        1.0F,
        0
      );
    }
  }

  @Override
  public void onUnequipped(ItemStack stack, EntityLivingBase entityLivingBase) {
    super.onUnequipped(stack, entityLivingBase);

    AttributeHelper.removeAttributeModFromLiving(
      entityLivingBase,
      SharedMonsterAttributes.knockbackResistance,
      new UUID(ATTRIBUTE_MODIFIER_UUID, 3),
      "TR_KNOCKBACK_RESISTANCE",
      1.0F,
      0
    );
  }
}
