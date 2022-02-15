package thaumrev;

import cpw.mods.fml.common.registry.GameRegistry;
import thaumrev.block.*;
import thaumrev.tiles.*;

import static thaumrev.ThaumRevLibrary.*;

public class ThaumRevBlocks {
  public static void init() {
    initializeBlocks();
    registerBlocks();
    registerTileEntities();
  }

  private static void initializeBlocks() {
    blockExcubitura = new BlockExcubitura();
    blockInfusedQuartzNormal = new BlockQuartzNormal();
    blockInfusedQuartzChiseled = new BlockQuartzChiseled();
    blockInfusedQuartzPillar = new BlockQuartzPillar();
    blockSlabInfusedQuartz = new BlockSlabQuartz(false);
    blockSlabDoubleInfusedQuartz = new BlockSlabQuartz(true);
    blockInfusedQuartzStair = new BlockQuartzStair();
    blockWitor = new BlockWitor();
    blockKnowledgeReprocessor = new BlockKnowledgeReprocessor();
  }

  private static void registerBlocks() {
    GameRegistry.registerBlock(blockExcubitura, "blockExcubitura");
    GameRegistry.registerBlock(blockInfusedQuartzNormal, "blockInfusedQuartzNormal");
    GameRegistry.registerBlock(blockInfusedQuartzChiseled, "blockInfusedQuartzChiseled");
    GameRegistry.registerBlock(blockInfusedQuartzPillar, "blockInfusedQuartzPillar");
    GameRegistry.registerBlock(blockSlabInfusedQuartz, BlockCosmeticSlabQuartzItem.class, "blockSlabInfusedQuartz");
    GameRegistry.registerBlock(blockSlabDoubleInfusedQuartz, "blockDoubleSlabInfusedQuartz");
    GameRegistry.registerBlock(blockInfusedQuartzStair, "blockInfusedQuartzStair");
    GameRegistry.registerBlock(blockWitor, "blockWitor");
    GameRegistry.registerBlock(blockKnowledgeReprocessor, "blockKnowledgeReprocessor");
  }

  private static void registerTileEntities() {
    GameRegistry.registerTileEntity(TileKnowledgeReprocessor.class, "TileKnowledgeReprocessor");
  }
}
