package thaumrev.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import thaumrev.ThaumRevLibrary;

import java.util.Random;

public class MobDropsHandler {
  public static void init() {
    MinecraftForge.EVENT_BUS.register(new MobDropsHandler());
  }

  Random random = new Random();

  @SubscribeEvent
  public void onMobDrops(LivingDropsEvent event) {
    if (event.entity instanceof EntityTameable) {
      ItemStack stack = new ItemStack(ThaumRevLibrary.itemLoveRing);
      EntityTameable tameable = (EntityTameable) event.entity;

      if (tameable.isTamed() && random.nextInt(4) == 0) {
        EntityItem drop = new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, stack);
        event.drops.add(drop);
      }
    }
  }
}
