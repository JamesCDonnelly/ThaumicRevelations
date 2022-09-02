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
import thaumrev.item.ItemWardenWeapon;
import thaumrev.item.armor.ItemWardenArmor;
import thaumrev.item.baubles.ItemWardenAmulet;
import thaumrev.util.wardenic.upgrade.WardenicUpgrade;

public class WardenicChargeEvents {
  public static void init() {
    MinecraftForge.EVENT_BUS.register(new WardenicChargeEvents());
  }

  @SubscribeEvent
  public void onPlayerTick(@NotNull LivingUpdateEvent event) {
    if (event.entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) event.entity;

      for (int i = 0; i < 4; i++) {
        ItemStack armor = player.getCurrentArmor(i);

        if (armor != null && (armor.getItem() instanceof ItemWardenArmor)) {
          armor.setMetadata(0);
        }
      }

      if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemWardenWeapon) {
        player.getHeldItem().setMetadata(0);
      }
    }
  }

  @SubscribeEvent
  public void onHurt(@NotNull LivingHurtEvent event) {
    Entity entity = event.entity;
    Entity sourceEntity = event.source.getEntity();
    Entity indirectEntity = event.source.getSourceOfDamage();

    if (entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) entity;
      ItemStack amulet = ItemWardenAmulet.getAmulet(player);

      if (amulet == null) {
        return;
      }

      amulet.setMetadata((int)(amulet.getMetadata() - event.ammount));

      WardenicChargeHelper.getUpgrade(amulet).onHurt(event);
    }

    if (sourceEntity instanceof EntityPlayer && indirectEntity instanceof EntityArrow) {
      EntityPlayer player = (EntityPlayer) sourceEntity;
      ItemStack amulet = ItemWardenAmulet.getAmulet(player);
      NBTTagCompound tag = indirectEntity.getEntityData();
      String value = tag.getString("WardenArrow");

      if (amulet == null) {
        return;
      }

      if (value != null) {
        WardenicUpgrade upgrade = WardenicChargeHelper.getUpgrade(value);

        if (upgrade != null) upgrade.onIndirectAttack(event);
      }
    }
  }
}
