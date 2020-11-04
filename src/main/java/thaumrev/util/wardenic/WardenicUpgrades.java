package thaumrev.util.wardenic;

import thaumcraft.api.aspects.Aspect;
import thaumrev.ThaumRevLibrary;
import thaumrev.util.wardenic.upgrade.*;

public class WardenicUpgrades {

	public static final WardenicUpgrade WARDEN = new WardenicUpgradeWarden(ThaumRevLibrary.EXCUBITOR);
	public static final WardenicUpgrade FIRE = new WardenicUpgradeFire(Aspect.FIRE);
	public static final WardenicUpgrade WATER = new WardenicUpgradeWater(Aspect.WATER);
	public static final WardenicUpgrade AIR = new WardenicUpgradeAir(Aspect.AIR);
	public static final WardenicUpgrade EARTH = new WardenicUpgradeEarth(Aspect.EARTH);
	public static final WardenicUpgrade ORDER = new WardenicUpgradeOrder(Aspect.ORDER);
	public static final WardenicUpgrade ENTROPY = new WardenicUpgradeEntropy(Aspect.ENTROPY);

	public static void init() {
		WardenicChargeHelper.addUpgrade(WARDEN);
		WardenicChargeHelper.addUpgrade(FIRE);
		WardenicChargeHelper.addUpgrade(WATER);
		WardenicChargeHelper.addUpgrade(AIR);
		WardenicChargeHelper.addUpgrade(EARTH);
		WardenicChargeHelper.addUpgrade(ORDER);
		WardenicChargeHelper.addUpgrade(ENTROPY);
	}

}
