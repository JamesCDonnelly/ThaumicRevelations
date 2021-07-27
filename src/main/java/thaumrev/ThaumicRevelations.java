package thaumrev;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thaumrev.client.gui.GuiHandler;
import thaumrev.common.CommonProxy;
import thaumrev.util.MobDropsHandler;
import thaumrev.util.TabThaumRev;
import thaumrev.util.wardenic.WardenicChargeEvents;
import thaumrev.util.wardenic.WardenicUpgrades;
import thaumrev.world.WorldGenExcubitura;

import java.io.File;

@Mod(modid = "thaumrev", useMetadata = true)
public class ThaumicRevelations {

	public File modDir;
	public static final Logger log = LogManager.getLogger("THAUMREV");

	@Instance(ThaumRevLibrary.MOD_ID)
	public static ThaumicRevelations instance;

	@SidedProxy(
			serverSide = "thaumrev.common.CommonProxy",
			clientSide = "thaumrev.client.ClientProxy"
	)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		this.modDir = event.getModConfigurationDirectory();

		try {
			ThaumRevConfig.initialize(event.getSuggestedConfigurationFile());
		} catch (Exception var8) {
			log.error("Thaumic Revelations has a problem loading it's configuration");
		} finally {
			if (ThaumRevConfig.config != null) {
				ThaumRevConfig.save();
			}
		}

		proxy.initRenderers();
		GuiHandler.init();

		MobDropsHandler.init();

		ThaumRevLibrary.tabThaumRev = new TabThaumRev(ThaumRevLibrary.MOD_ID);
		ThaumRevBlocks.registerBlocks();
		ThaumRevItems.registerItems();
		ThaumRevAspects.registerAspects();
		ThaumRevAspects.registerItemAspects();

		// GameRegistry.addSubstitutionAlias();

		GameRegistry.registerWorldGenerator(new WorldGenExcubitura(), 1);

		ThaumRevConfig.save();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		WardenicChargeEvents.init();
		WardenicUpgrades.init();
		proxy.keyBindings();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		ThaumRevRecipes.registerRecipes();
		ThaumRevResearches.registerResearches();
	}
}
