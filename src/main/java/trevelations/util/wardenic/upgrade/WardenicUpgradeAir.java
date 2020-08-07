package trevelations.util.wardenic.upgrade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import trevelations.util.wardenic.WardenicChargeHelper;

public class WardenicUpgradeAir extends WardenicUpgrade {

	public WardenicUpgradeAir(Aspect aspect) { super(aspect); }

	@Override
	public void onTick(World world, EntityPlayer player, ItemStack stack)
	{
		//TODO: set a FOR loop 'ere

		float helmetBoost = 0;
		float chestBoost = 0;
		float legsBoost = 0;
		float bootsBoost = 0;

		// int count = 0;

		if (player.fallDistance > 4) {
			player.fallDistance = 4;
		}

		if (player.getCurrentArmor(3) != null) {
			ItemStack helm = player.getCurrentArmor(3);
			if (WardenicChargeHelper.getUpgrade(helm).getQuote().equals("Free like wind")) {
				helmetBoost = 0.025F;
			} else {
				helmetBoost = 0;
			}
		} else {
			helmetBoost = 0;
		}
		if (player.getCurrentArmor(2) != null) {
			ItemStack chest = player.getCurrentArmor(2);
			if (WardenicChargeHelper.getUpgrade(chest).getQuote().equals("Free like wind")) {
				chestBoost = 0.025F;
			} else {
				chestBoost = 0;
			}
		} else {
			chestBoost = 0;
		}
		if (player.getCurrentArmor(1) != null) {
			ItemStack legs = player.getCurrentArmor(1);
			if (WardenicChargeHelper.getUpgrade(legs).getQuote().equals("Free like wind")) {
				legsBoost = 0.025F;
			} else {
				legsBoost = 0;
			}
		} else {
			legsBoost = 0;
		}
		if (player.getCurrentArmor(0) != null) {
			ItemStack boots = player.getCurrentArmor(0);
			if (WardenicChargeHelper.getUpgrade(boots).getQuote().equals("Free like wind")) {
				bootsBoost = 0.025F;
				player.stepHeight = 1F;
			} else {
				bootsBoost = 0;
				player.stepHeight = 0.5F;
			}
		} else {
			bootsBoost = 0;
			player.stepHeight = 0.5F;
		}

		player.capabilities.setPlayerWalkSpeed(0.1F + helmetBoost + chestBoost + legsBoost + bootsBoost);
	}
}
