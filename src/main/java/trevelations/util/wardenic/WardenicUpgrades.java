package trevelations.util.wardenic;

import trevelations.common.ThaumRevLibrary;
import thaumcraft.api.aspects.Aspect;
import trevelations.util.wardenic.upgrade.*;

public class WardenicUpgrades {

	public static final WardenicUpgrade WARDEN = new WardenicUpgradeWarden(ThaumRevLibrary.EXCUBITOR);
	public static final WardenicUpgrade FIRE = new WardenicUpgradeFire(Aspect.FIRE);
	public static final WardenicUpgrade ARMOR = new WardenicUpgradeArmor(Aspect.ARMOR);
	public static final WardenicUpgrade WATER = new WardenicUpgradeWater(Aspect.WATER);
	public static final WardenicUpgrade AIR = new WardenicUpgradeAir(Aspect.AIR);
	public static final WardenicUpgrade EARTH = new WardenicUpgradeEarth(Aspect.EARTH);
	public static final WardenicUpgrade HEAL = new WardenicUpgradeHeal(Aspect.HEAL);

	public static void init() {
		WardenicChargeHelper.addUpgrade(WARDEN);
		WardenicChargeHelper.addUpgrade(FIRE);
		WardenicChargeHelper.addUpgrade(ARMOR);
		WardenicChargeHelper.addUpgrade(WATER);
		WardenicChargeHelper.addUpgrade(AIR);
		WardenicChargeHelper.addUpgrade(EARTH);
		WardenicChargeHelper.addUpgrade(HEAL);
	}

}
