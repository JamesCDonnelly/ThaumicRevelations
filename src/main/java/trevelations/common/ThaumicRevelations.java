package trevelations.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import trevelations.client.gui.GuiHandler;
import trevelations.util.MobDropsHandler;
import trevelations.util.TabTRevelations;
import trevelations.util.wardenic.WardenicChargeEvents;
import trevelations.util.wardenic.WardenicUpgrades;
import trevelations.world.WorldGenExcubitura;

@Mod(modid = "trevelations", useMetadata = true)
public class ThaumicRevelations {

	@Instance(ThaumRevLibrary.MOD_ID)
	public static ThaumicRevelations instance;

	@SidedProxy(
			serverSide = "trevelations.common.CommonProxy",
			clientSide = "trevelations.client.ClientProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.initRenderers();
		GuiHandler.init();

		MobDropsHandler.init();
		WardenicChargeEvents.init();
		WardenicUpgrades.init();

		ThaumRevLibrary.tabThaumRev = new TabTRevelations(ThaumRevLibrary.MOD_ID);
		ThaumRevContent.loadBlocks();
		ThaumRevContent.loadItems();

		// GameRegistry.addSubstitutionAlias();

		GameRegistry.registerWorldGenerator(new WorldGenExcubitura(), 1);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ThaumRevContent.loadRecipes();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		ThaumRevContent.loadResearch();
		ThaumRevContent.loadAspects();
	}
}
