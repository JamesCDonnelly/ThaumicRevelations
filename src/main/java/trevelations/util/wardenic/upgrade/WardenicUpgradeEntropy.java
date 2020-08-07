package trevelations.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import thaumcraft.api.aspects.Aspect;
import trevelations.util.DamageSourceWarden;
import trevelations.util.wardenic.WardenicChargeHelper;

import java.util.Random;

public class WardenicUpgradeEntropy extends WardenicUpgrade {

	Random random = new Random();

	public WardenicUpgradeEntropy(Aspect aspect) {
		super(aspect);
	}

	@Override
	public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
		super.onAttack(stack, player, entity);

		int count = 0;

		for (int i = 0; i <= 3; i++) {
			if ((player.getCurrentArmor(i) != null) &&
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
							.equals(Aspect.FIRE.getName())) {
				count++;
			}
		}

		DamageSource damageSource = new DamageSourceWarden("warden", entity);
		entity.attackEntityFrom(damageSource, (random.nextInt(1) + 1) * count);
	}
}
