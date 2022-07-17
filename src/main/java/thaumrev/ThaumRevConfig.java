package thaumrev;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class ThaumRevConfig {
  public static Configuration config;
  public static final String CATEGORY_VEGETATION = "Category_Vegetation";
  public static final String CATEGORY_FOCI = "Category_Foci";
  public static int roseGrowthDivider = 1;
  public static int basePurityCooldown = 500;
  public static int basePurityVisCost = 100;

  public ThaumRevConfig() {}

  public static void save() {
    config.save();
  }

  public static void initialize(File file) {
    config = new Configuration(file);
    config.load();

    initializeVegetationCategory();
    initializeFociCategory();
  }

  private static void initializeVegetationCategory() {
    config.addCustomCategoryComment(CATEGORY_VEGETATION, "Growth speed and other regeneration");

    Property roseGrowthDividerProperty = config.get(CATEGORY_VEGETATION, "roseGrowthDivider", 1);
    roseGrowthDividerProperty.setMinValue(1);
    roseGrowthDividerProperty.comment = "Increasing this value will result in decreasing growth speed of Wardenic Roses (default: 1)";
    roseGrowthDivider = roseGrowthDividerProperty.getInt();
  }

  private static void initializeFociCategory() {
    config.addCustomCategoryComment(CATEGORY_FOCI, "Base values for Thaumic Revelations' foci");

    Property basePurityCooldownProperty = config.get(CATEGORY_FOCI, "basePurityCooldown", 500);
    Property basePurityVisCostProperty = config.get(CATEGORY_FOCI, "basePurityVisCost", 100);

    basePurityCooldownProperty.setMinValue(1);
    basePurityVisCostProperty.setMinValue(1);

    basePurityCooldownProperty.comment = "Wand Focus: Purity base cooldown (in ms - milliseconds)";
    basePurityVisCostProperty.comment = "Wand Focus: Purity base vis cost (in cv - centivis)";

    basePurityCooldown = basePurityCooldownProperty.getInt();
    basePurityVisCost = basePurityVisCostProperty.getInt();
  }
}
