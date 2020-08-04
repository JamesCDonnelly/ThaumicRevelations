package thaumrev.util.wardenic.upgrade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumrev.item.armor.ItemWardenLegs;
import thaumrev.util.wardenic.WardenicChargeHelper;

import static thaumrev.common.ThaumRevLibrary.*;

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

		if (player.fallDistance > 4) {
			player.fallDistance = 4;
		}

		if (player.getCurrentArmor(3) != null) {
			ItemStack helm = player.getCurrentArmor(0);
			if ((helm.getItem() == itemWardenHelm) &&
					(WardenicChargeHelper.getUpgrade(stack).getQuote().equals("Free like wind"))) {
				helmetBoost = 0.025F;
			} else {
				helmetBoost = 0;
			}
		}
		if (player.getCurrentArmor(2) != null) {
			ItemStack chest = player.getCurrentArmor(1);
			if ((chest.getItem() == itemWardenChest) &&
					(WardenicChargeHelper.getUpgrade(stack).getQuote().equals("Free like wind"))) {
				chestBoost = 0.025F;
			} else {
				chestBoost = 0;
			}
		}
		if (player.getCurrentArmor(1) != null) {
			ItemStack legs = player.getCurrentArmor(2);
			if ((legs.getItem() == itemWardenLegs) &&
					(WardenicChargeHelper.getUpgrade(stack).getQuote().equals("Free like wind")) &&
					player.getCurrentArmor(2).getItem() instanceof ItemWardenLegs) {
				legsBoost = 0.025F;
			} else {
				legsBoost = 0;
			}
		}
		if (player.getCurrentArmor(0) != null) {
			ItemStack boots = player.getCurrentArmor(3);
			if ((boots.getItem()) == itemWardenBoots &&
					(WardenicChargeHelper.getUpgrade(stack).getQuote().equals("Free like wind"))) {
				bootsBoost = 0.025F;
				player.stepHeight = 1;
			} else {
				bootsBoost = 0;
				player.stepHeight = 0F;
			}
		}

		player.capabilities.setPlayerWalkSpeed(0.1F + helmetBoost + chestBoost + legsBoost + bootsBoost);
	}
}
