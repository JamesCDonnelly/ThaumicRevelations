package thaumrev.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import thaumrev.ThaumRevLibrary;

public class MobDropsHandler {

    public static void init() {
        MinecraftForge.EVENT_BUS.register(new MobDropsHandler());
    }

    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event)
    {
        if (event.entity instanceof EntityWolf) {
            ItemStack stack = new ItemStack(ThaumRevLibrary.itemLoveRing);
            EntityItem drop = new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, stack);

            event.drops.add(drop);
        }
    }
}
