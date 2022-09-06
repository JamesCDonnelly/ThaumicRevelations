package thaumrev.config;

import cpw.mods.fml.common.Loader;

import static thaumrev.ThaumicRevelations.log;

public final class ConfigIntegrations {

  public static boolean isThaumicDyesLoaded = false;
  public static boolean isThaumicBasesLoaded = false;

  public static void init() {
    if (Config.isThaumicDyesIntegrationEnabled)
      isThaumicDyesLoaded = Loader.isModLoaded("thaumicdyes");

    if (Config.isThaumicBasesIntegrationEnabled)
      isThaumicBasesLoaded = Loader.isModLoaded("thaumicbases");

    showIntegrationMessages();
  }

  private static void showIntegrationMessages() {
    if (isThaumicDyesLoaded)
      log.info("Thanks to you, Thaumic Dyes, I can dye my Wardencloth armor!");

    if (isThaumicBasesLoaded)
      log.info("Not so \"Base\" anymore, right Thaumic Bases?");
  }
}
