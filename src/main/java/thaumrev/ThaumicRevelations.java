package thaumrev;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.registry.GameRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import thaumrev.client.gui.GuiHandler;
import thaumrev.common.CommonProxy;
import thaumrev.config.*;
import thaumrev.lib.utils.MobDropsHandler;
import thaumrev.lib.CreativeTabThaumRev;
import thaumrev.api.wardenic.WardenicChargeEvents;
import thaumrev.api.wardenic.WardenicUpgrades;
import thaumrev.lib.world.ThaumRevWorldGenerator;

import java.io.File;


@Mod(modid=ConfigLibrary.MOD_ID, version=ConfigLibrary.VERSION, useMetadata=true)
public final class ThaumicRevelations {
  public File modDir;
  public static final Logger log = LogManager.getLogger("Thaumic Revelations");

  public static final String networkChannelName = "thaumrev";
  public final static int PACKET_TYPE_AMULET_USE = 1;
  public final static int PACKET_TYPE_S2C_TEST = 1;
  public static FMLEventChannel channel;

  @Instance(ConfigLibrary.MOD_ID)
  public static ThaumicRevelations instance;

  @SidedProxy(
    serverSide = "thaumrev.common.CommonProxy",
    clientSide = "thaumrev.client.ClientProxy"
  )

  public static CommonProxy proxy;

  @EventHandler
  public void preInit(@NotNull FMLPreInitializationEvent event) {
    this.modDir = event.getModConfigurationDirectory();

    try {
      Config.init(event.getSuggestedConfigurationFile());
    } catch (Exception var8) {
      log.error("Thaumic Revelations had a problem loading its configuration");
    } finally {
      if (Config.config != null) {
        Config.save();
      }
    }

    Config.save();

    ConfigLibrary.tabThaumRev = new CreativeTabThaumRev(ConfigLibrary.MOD_ID);

    ConfigIntegrations.init();
    MobDropsHandler.init();
    ConfigBlocks.init();
    ConfigItems.init();
    ConfigAspects.registerAspects();
    WardenicChargeEvents.init();
    WardenicUpgrades.init();

    // GameRegistry.addSubstitutionAlias();

    GameRegistry.registerWorldGenerator(new ThaumRevWorldGenerator(), 1);
  }

  @EventHandler
  public void init(@NotNull FMLInitializationEvent event) {
    GuiHandler.init();

    proxy.initRenderers();
    proxy.keyBindings();
    proxy.initNetwork();
  }

  @EventHandler
  public void postInit(@NotNull FMLPostInitializationEvent event) {
    ConfigRecipes.registerRecipes();
    ConfigAspects.registerItemAspects();
    ConfigResearches.registerResearches();
  }

  @SubscribeEvent
  public void onConfigChanged(ConfigChangedEvent.@NotNull OnConfigChangedEvent event) {
    if (event.modID.equals("thaumrev")) {
      Config.sync();

      if (Config.config != null && Config.config.hasChanged()) {
        Config.save();
      }
    }
  }
}

