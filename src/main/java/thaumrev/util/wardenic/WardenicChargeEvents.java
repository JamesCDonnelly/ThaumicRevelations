package thaumrev.util.wardenic;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumrev.item.ItemWardenWeapon;
import thaumrev.item.armor.ItemWardenArmor;
import thaumrev.item.baubles.ItemWardenAmulet;

import java.util.Random;

import static thaumrev.ThaumRevLibrary.EXCUBITOR;

public class WardenicChargeEvents {
  private final Random random = new Random();

  public static void init() {
    MinecraftForge.EVENT_BUS.register(new WardenicChargeEvents());
  }

  @SubscribeEvent
  public void onPlayerTick(LivingUpdateEvent event) {
    if (event.entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) event.entity;
      ItemStack amulet = ItemWardenAmulet.getAmulet(player);

      for (int i = 0; i < 4; i++) {
        ItemStack armor = player.getCurrentArmor(i);

        if (armor != null && (armor.getItem() instanceof ItemWardenArmor)) {
          armor.setMetadata(0);
        }
      }

      if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemWardenWeapon) {
        player.getHeldItem().setMetadata(0);
      }

      if (amulet == null) {
        return;
      }

      String upgrade = WardenicChargeHelper.getUpgrade(amulet).getUpgradeAspect();

      ItemStack boots = player.getCurrentArmor(0);

      if (boots != null && boots.getItem() instanceof ItemWardenArmor &&
        (
          (upgrade.equals(Aspect.AIR.getName()) && !player.isInWater()) ||
          (upgrade.equals(Aspect.WATER.getName()) && player.isInWater())
        )
      ) {
        player.stepHeight = 1.0F;
      } else {
        player.stepHeight = 0.5F;
      }
    }
  }

  @SubscribeEvent
  public void onHurt(LivingHurtEvent event) {
    if (event.entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) event.entity;
      ItemStack amulet = ItemWardenAmulet.getAmulet(player);

      if (amulet == null) {
        return;
      }

      amulet.setMetadata((int)(amulet.getMetadata() - event.ammount));

      short count = WardenicChargeHelper.getWardenicArmorCount(player);

      if (count == 4) {
        WardenicChargeHelper.getUpgrade(amulet).onHurt(event);
      }
    }

    if (event.source.getEntity() instanceof EntityPlayer && event.source.getSourceOfDamage() instanceof EntityArrow) {
      EntityPlayer player = (EntityPlayer) event.source.getEntity();
      ItemStack amulet = ItemWardenAmulet.getAmulet(player);
      Entity entityArrow = event.source.getSourceOfDamage();
      NBTTagCompound tag = entityArrow.getEntityData();

      if (amulet == null) {
        return;
      }

      if (tag.getString("WardenArrow") != null) {
        WardenicChargeHelper.getUpgrade(amulet).onIndirectAttack(event);
      }

      if (event.entity instanceof EntityPlayer &&
        tag.getString("WardenArrow").equals(EXCUBITOR.getName())) {
        ItemStack a = ItemWardenAmulet.getAmulet((EntityPlayer) event.entity);

        if (a == null) {
          return;
        }

        a.setMetadata((int)(a.getMetadata() + event.ammount));
      }
    }
  }
}
