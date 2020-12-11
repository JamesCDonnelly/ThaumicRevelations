package thaumrev;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import thaumrev.client.ClientProxy;
import thaumrev.client.gui.GuiHandler;
import thaumrev.common.CommonProxy;
import thaumrev.util.KeyEventHandler;
import thaumrev.util.MobDropsHandler;
import thaumrev.util.TabThaumRev;
import thaumrev.util.wardenic.WardenicChargeEvents;
import thaumrev.util.wardenic.WardenicUpgrades;
import thaumrev.world.WorldGenExcubitura;

@Mod(modid = "thaumrev", useMetadata = true)
public class ThaumicRevelations {

	@Instance(ThaumRevLibrary.MOD_ID)
	public static ThaumicRevelations instance;

	@SidedProxy(
			serverSide = "thaumrev.common.CommonProxy",
			clientSide = "thaumrev.client.ClientProxy")

	public static CommonProxy commonProxy;
	public static ClientProxy clientProxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		commonProxy.initRenderers();
		clientProxy.initRenderers();
		GuiHandler.init();

		MobDropsHandler.init();
		KeyEventHandler.init();

		ThaumRevLibrary.tabThaumRev = new TabThaumRev(ThaumRevLibrary.MOD_ID);
		ThaumRevBlocks.registerBlocks();
		ThaumRevItems.registerItems();
		ThaumRevAspects.registerAspects();
		ThaumRevAspects.registerItemAspects();

		// GameRegistry.addSubstitutionAlias();

		GameRegistry.registerWorldGenerator(new WorldGenExcubitura(), 1);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		WardenicChargeEvents.init();
		WardenicUpgrades.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		ThaumRevRecipes.registerRecipes();
		ThaumRevResearches.registerResearches();
	}
}
