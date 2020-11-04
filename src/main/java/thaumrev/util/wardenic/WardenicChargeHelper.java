package thaumrev.util.wardenic;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumrev.ThaumRevLibrary;
import thaumrev.util.wardenic.upgrade.WardenicUpgrade;

import java.util.HashMap;

public class WardenicChargeHelper {

	public static HashMap<String, WardenicUpgrade> upgrades = new HashMap<String, WardenicUpgrade>();

	public static void addUpgrade(WardenicUpgrade upgrade) {
		addUpgrade(upgrade.aspect.getName(), upgrade);
	}

	public static void addUpgrade(String key, WardenicUpgrade upgrade) {
		upgrades.put(key, upgrade);
	}

	public static WardenicUpgrade getUpgrade(ItemStack stack) {
		if (stack.stackTagCompound != null) {
			if (stack.stackTagCompound.hasKey("upgrade")) {
				return upgrades.get(stack.stackTagCompound.getString("upgrade"));
			} else {
				return upgrades.get(ThaumRevLibrary.EXCUBITOR.getName());
			}
		} else {
			return upgrades.get(ThaumRevLibrary.EXCUBITOR.getName());
		}
	}

	public static void setUpgradeOnStack(ItemStack stack, String key) {
		if (stack.stackTagCompound == null) {
			stack.setTagCompound(new NBTTagCompound());
		}

		stack.stackTagCompound.setString("upgrade", key);
	}

}
