package thaumrev;

import cpw.mods.fml.common.Loader;

import static thaumrev.ThaumicRevelations.log;

public abstract class ThaumRevIntegrations {

  public static boolean isThaumicDyesLoaded = false;
  public static boolean isThaumicBasesLoaded = false;

  public static void init() {
    if (ThaumRevConfig.isThaumicDyesIntegrationEnabled)
      isThaumicDyesLoaded = Loader.isModLoaded("thaumicdyes");

    if (ThaumRevConfig.isThaumicBasesIntegrationEnabled)
      isThaumicBasesLoaded = Loader.isModLoaded("thaumicbases");

    showIntegrationMessages();
  }

  private static void showIntegrationMessages() {
    if (isThaumicDyesLoaded)
      log.info("Ready for some new colors, Thaumic Dyes?");

    if (isThaumicBasesLoaded)
      log.info("Not so \"Base\" anymore, right Thaumic Bases?");
  }
}
