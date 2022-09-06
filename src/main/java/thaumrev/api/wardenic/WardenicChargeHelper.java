package thaumrev.api.wardenic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import thaumrev.config.ConfigLibrary;
import thaumrev.item.armor.ItemWardenArmor;
import thaumrev.api.wardenic.upgrade.WardenicUpgrade;

import java.util.HashMap;

public abstract class WardenicChargeHelper {

	public enum WardenicUpgradeEnum {
		Excubitor,
		Aer,
		Aqua,
		Ignis,
		Order,
		Perditio,
		Terra
	}

	public static HashMap<String, WardenicUpgrade> upgrades = new HashMap<>();

	public static void addUpgrade(WardenicUpgrade upgrade) {
		upgrades.put(upgrade.aspect.getName(), upgrade);
	}

	public static WardenicUpgrade getUpgrade(@NotNull ItemStack stack) {
		if (stack.getMetadata() > 6) {
			setUpgradeOnStack(stack, ConfigLibrary.EXCUBITOR.getName());
		}

		return upgrades.get(
			WardenicUpgradeEnum.values()[stack.getMetadata()].name()
		);
	}

	public static WardenicUpgrade getUpgrade(String aspectName) {
		return upgrades.get(aspectName);
	}

	public static void setUpgradeOnStack(@NotNull ItemStack stack, @NotNull String key) {
		stack.setMetadata(WardenicUpgradeEnum.valueOf(key).ordinal());
	}

	public static short getWardenicArmorCount(EntityPlayer player) {
		short count = 0;

		for (int i = 0; i < 4; ++i) {
			ItemStack armor = player.getCurrentArmor(i);

			if (armor != null && (armor.getItem() instanceof ItemWardenArmor)) {
				count++;
			}
		}

		return count;
	}
}
