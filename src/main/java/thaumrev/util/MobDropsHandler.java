package thaumrev.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
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
        if (event.entity instanceof EntityWolf) {
            ItemStack stack = new ItemStack(ThaumRevLibrary.itemLoveRing);
            EntityWolf wolf = (EntityWolf) event.entity;

            if (wolf.isTamed() && MathHelper.getRandomIntegerInRange(random, 1, 4) == 1) {
                EntityItem drop = new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, stack);
                event.drops.add(drop);
            }
        }
    }
}
