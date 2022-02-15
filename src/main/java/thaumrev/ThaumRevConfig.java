package thaumrev;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class ThaumRevConfig {
  public static Configuration config;
  public static final String CATEGORY_SPEED = "Feature_Speed";
  public static int roseGrowthDivider = 1;

  public ThaumRevConfig() {
  }

  public static void save() {
    config.save();
  }

  public static void initialize(File file) {
    config = new Configuration(file);
    config.addCustomCategoryComment("Feature_Speed", "Growth speed and other regeneration");
    config.load();
    Property rGDP = config.get("Feature_Speed", "roseGrowthDivider", 1);
    rGDP.comment = "Increasing this value will result in decreasing growth speed of Wardenic Roses (default: 1)";
    roseGrowthDivider = rGDP.getInt();
  }
}
