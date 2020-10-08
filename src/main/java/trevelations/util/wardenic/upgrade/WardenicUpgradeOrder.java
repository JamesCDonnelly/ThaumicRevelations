package trevelations.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.monster.EntityFireBat;
import trevelations.util.DamageSourceWarden;
import trevelations.util.wardenic.WardenicChargeHelper;

public class WardenicUpgradeOrder extends WardenicUpgrade {

    public WardenicUpgradeOrder(Aspect aspect) {
        super(aspect);
    }

    private boolean isUndeadOrHell(Entity entity) {
        return entity instanceof EntityZombie ||
                entity instanceof EntitySkeleton ||
                entity instanceof EntityWither ||
                entity instanceof EntityGhast ||
                entity instanceof EntityFireBat ||
                entity instanceof EntityBlaze;
    }

    @Override
    public void onIndirectAttack(LivingHurtEvent event) {
        super.onIndirectAttack(event);

        Entity entity = event.entity;
        EntityPlayer player = (EntityPlayer)event.source.getEntity();
        EntityArrow entityArrow = (EntityArrow)event.source.getSourceOfDamage();

        int count = 0;

        for (int i = 0; i < 4; i++) {
            if ((player.getCurrentArmor(i) != null) &&
                    WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
                            .equals(Aspect.ORDER.getName())) {
                count++;
            }
        }

        if (entityArrow.getIsCritical()) {
            if (isUndeadOrHell(entity)) {
                DamageSource damageSource = new DamageSourceWarden("warden", player);

                entity.attackEntityFrom(damageSource, 8 * (count + 1));
            }
        }
    }

    @Override
    public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
        super.onAttack(stack, player, entity);

        int count = 0;

        for (int i = 0; i < 4; i++) {
            if ((player.getCurrentArmor(i) != null) &&
                    WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
                            .equals(Aspect.ORDER.getName())) {
                count++;
            }
        }

        if (isUndeadOrHell(entity)) {
            DamageSource damageSource = new DamageSourceWarden("warden", player);

            entity.attackEntityFrom(damageSource, 8 * (count + 1));
        }
    }

    @Override
    public void onAttacked(LivingHurtEvent event) {
        super.onAttacked(event);

        if (event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entity;
            int count = 0;

            for (int i = 0; i <= 3; i++) {
                if ((player.getCurrentArmor(i) != null) &&
                        WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
                                .equals(Aspect.ORDER.getName())) {
                    count++;
                }
            }

            if (count == 4) {
                if (isUndeadOrHell(event.source.getEntity())) {
                    event.setCanceled(true);
                }
            }

            Entity sourceEntity = event.source.getEntity();

            DamageSource damageSource = new DamageSourceWarden("warden", player);

            if (isUndeadOrHell(sourceEntity)) {
                sourceEntity.attackEntityFrom(damageSource, (event.ammount * count));
            }
        }
    }

    @Override
    public void onTick(World world, EntityPlayer player, ItemStack stack) {
        super.onTick(world, player, stack);

        int count = 0;

        for (int i = 0; i <= 3; i++) {
            if ((player.getCurrentArmor(i) != null) &&
                    WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
                            .equals(Aspect.ORDER.getName())) {
                count++;
            }
        }

        if (count == 4) {
            if (player.isPotionActive(Config.potionUnHungerID)) {
                player.removePotionEffect(Config.potionUnHungerID);
            }
            if (player.isPotionActive(Config.potionBlurredID)) {
                player.removePotionEffect(Config.potionBlurredID);
            }
            if (player.isPotionActive(Potion.wither.getId())) {
                player.removePotionEffect(Potion.wither.getId());
            }
            if (player.isPotionActive(Potion.blindness.getId())) {
                player.removePotionEffect(Potion.blindness.getId());
            }

            player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 0, 0));
        }
    }
}
