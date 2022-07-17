package thaumrev;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thaumrev.client.gui.GuiHandler;
import thaumrev.common.CommonProxy;
import thaumrev.util.MobDropsHandler;
import thaumrev.util.TabThaumRev;
import thaumrev.util.wardenic.WardenicChargeEvents;
import thaumrev.util.wardenic.WardenicUpgrades;
import thaumrev.world.ThaumRevWorldGenerator;

import java.io.File;

@Mod(modid = "thaumrev", useMetadata = true)
public class ThaumicRevelations {
  public File modDir;
  public static final Logger log = LogManager.getLogger("thaumrev");

  public static final String networkChannelName = "thaumrev";
  public final static int PACKET_TYPE_AMULET_USE = 1;
  public final static int PACKET_TYPE_S2C_TEST = 1;
  public static FMLEventChannel channel;

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
      log.error("Thaumic Revelations has a problem loading its configuration");
    } finally {
      if (ThaumRevConfig.config != null) {
        ThaumRevConfig.save();
      }
    }

    ThaumRevConfig.save();

    ThaumRevLibrary.tabThaumRev = new TabThaumRev(ThaumRevLibrary.MOD_ID);

    MobDropsHandler.init();
    ThaumRevBlocks.init();
    ThaumRevItems.init();
    ThaumRevAspects.registerAspects();
    ThaumRevAspects.registerItemAspects();
    WardenicChargeEvents.init();
    WardenicUpgrades.init();

    // GameRegistry.addSubstitutionAlias();

    GameRegistry.registerWorldGenerator(new ThaumRevWorldGenerator(), 1);
  }

  @EventHandler
  public void init(FMLInitializationEvent event) {
    GuiHandler.init();

    proxy.initRenderers();
    proxy.keyBindings();
    proxy.initNetwork();
  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
    ThaumRevRecipes.registerRecipes();
    ThaumRevResearches.registerResearches();
  }
}
