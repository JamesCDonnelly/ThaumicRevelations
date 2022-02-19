package thaumrev.util.wardenic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumrev.ThaumRevLibrary;
import thaumrev.item.armor.ItemWardenArmor;
import thaumrev.util.wardenic.upgrade.WardenicUpgrade;

import java.util.HashMap;

public abstract class WardenicChargeHelper {

	public static HashMap<String, WardenicUpgrade> upgrades = new HashMap<String, WardenicUpgrade>();

	public static void addUpgrade(WardenicUpgrade upgrade) {
		addUpgrade(upgrade.aspect.getName(), upgrade);
	}

	public static void addUpgrade(String key, WardenicUpgrade upgrade) {
		upgrades.put(key, upgrade);
	}

	public static WardenicUpgrade getUpgrade(ItemStack stack) {
		if (stack.stackTagCompound != null) {
			if (!stack.stackTagCompound.hasKey("upgrade")) {
				setUpgradeOnStack(stack, ThaumRevLibrary.EXCUBITOR.getName());
			}
		} else {
			setUpgradeOnStack(stack, ThaumRevLibrary.EXCUBITOR.getName());
		}

		return upgrades.get(stack.stackTagCompound.getString("upgrade"));
	}

	public static void setUpgradeOnStack(ItemStack stack, String key) {
		if (stack.stackTagCompound == null) {
			stack.setTagCompound(new NBTTagCompound());
		}

		stack.stackTagCompound.setString("upgrade", key);
	}

	public static short getWardenicArmorCount(EntityPlayer player) {
		short count = 0;

    for (int i = 0; i < 4; i++) {
      ItemStack armor = player.getCurrentArmor(i);

      if (armor != null && (armor.getItem() instanceof ItemWardenArmor)) {
        count++;
      }
    }

		return count;
	}
}
