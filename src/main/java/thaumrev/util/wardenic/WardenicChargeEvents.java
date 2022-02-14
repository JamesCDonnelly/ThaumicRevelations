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
  public void onPlayerTick(@NotNull LivingUpdateEvent event) {
    if (event.entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) event.entity;

      if (player.getCurrentArmor(0) != null) {
        if ((WardenicChargeHelper.getUpgrade(player.getCurrentArmor(0))
          .getUpgradeAspect().equals(Aspect.AIR.getName()) && !player.isInWater()) ||
          (WardenicChargeHelper.getUpgrade(player.getCurrentArmor(0))
            .getUpgradeAspect().equals(Aspect.WATER.getName()) && player.isInWater())) {
          player.stepHeight = 1.0F;
        }
      } else {
        player.stepHeight = 0.5F;
      }

      for (int i = 0; i < 4; i++) {
        ItemStack armor = player.getCurrentArmor(i);

        if (armor != null && (armor.getItem() instanceof ItemWardenArmor)) {
          armor.setMetadata(0);

          if (i == 0) {
            String upgradeAspect = WardenicChargeHelper.getUpgrade(armor).getUpgradeAspect();

            if (!(upgradeAspect.equals(Aspect.AIR.getName()) || upgradeAspect.equals(Aspect.WATER.getName()))) {
              player.stepHeight = 0.5F;
            }
          }
        } else if (i == 0) {
          player.stepHeight = 0.5F;
        }
      }

      if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemWardenWeapon) {
        player.getHeldItem().setMetadata(0);
      }
    }
  }

  @SubscribeEvent
  public void onHurt(@NotNull LivingHurtEvent event) {
    if (event.entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) event.entity;
      ItemStack amulet = ItemWardenAmulet.getAmulet(player);

      if (amulet == null) {
        return;
      }

      amulet.setMetadata(amulet.getMetadata() - 1);

      for (int i = 1; i < 4; i++) {
        ItemStack armor = player.getCurrentArmor(i);

        if (armor != null && (armor.getItem() instanceof ItemWardenArmor)) {
          Aspect aspect = WardenicChargeHelper.getUpgrade(armor).aspect;
          boolean activate = false;

          if (aspect.getName().equals(EXCUBITOR.getName())) {
            AspectList aspectList = VisHelper.getAllVis(amulet);
            Aspect[] aspects = aspectList.getAspects();
            boolean doit = true;

            for (Aspect a : aspects) {
              if (aspectList.getAmount(a) < 100) {
                doit = false;
                break;
              }
            }

            if (doit) {
              for (Aspect a : aspects) {
                VisHelper.addRealVis(amulet, a, -100);
              }

              activate = true;
            }
          } else {
            int vis = VisHelper.getVis(amulet, aspect);

            if (vis > 1000) {
              VisHelper.addRealVis(amulet, aspect, -1000);
              activate = true;
            }
          }

          if (activate) {
            WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).onHurt(event);
          }
        }
      }
    }

    if (event.source.getEntity() instanceof EntityPlayer && event.source.getSourceOfDamage() instanceof EntityArrow) {
      EntityPlayer player = (EntityPlayer) event.source.getEntity();
      Entity entityArrow = event.source.getSourceOfDamage();
      NBTTagCompound tag = entityArrow.getEntityData();

      if (tag.getString("WardenArrow") != null) {
        WardenicChargeHelper.getUpgrade(player.getHeldItem()).onIndirectAttack(event);
      }

      if (event.entity instanceof EntityPlayer &&
        tag.getString("WardenArrow").equals(EXCUBITOR.getName())) {
        ItemStack amulet = ItemWardenAmulet.getAmulet((EntityPlayer) event.entity);

        if (amulet != null) {
          amulet.setMetadata(amulet.getMetadata() + 5);
        }
      }
    }
  }
}
