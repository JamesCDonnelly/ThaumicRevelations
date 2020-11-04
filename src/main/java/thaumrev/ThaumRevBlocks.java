package thaumrev;

import cpw.mods.fml.common.registry.GameRegistry;
import thaumrev.block.*;

import static thaumrev.ThaumRevLibrary.*;

public class ThaumRevBlocks {
    public static void registerBlocks() {
        loadBlocks();

        GameRegistry.registerBlock(blockExcubitura, "blockExcubitura");
        GameRegistry.registerBlock(blockInfusedQuartzNormal, "blockInfusedQuartzNormal");
        GameRegistry.registerBlock(blockInfusedQuartzChiseled, "blockInfusedQuartzChiseled");
        GameRegistry.registerBlock(blockInfusedQuartzPillar, "blockInfusedQuartzPillar");
        GameRegistry.registerBlock(blockInfusedQuartzSlab, "blockInfusedQuartzSlab");
        GameRegistry.registerBlock(blockInfusedQuartzStair, "blockInfusedQuartzStair");
        GameRegistry.registerBlock(blockWitor, "blockWitor");
    }

    private static void loadBlocks() {
        blockExcubitura = new BlockExcubitura();
        blockInfusedQuartzNormal = new BlockQuartzNormal();
        blockInfusedQuartzChiseled = new BlockQuartzChiseled();
        blockInfusedQuartzPillar = new BlockQuartzPillar();
        blockInfusedQuartzSlab = new BlockQuartzSlab();
        blockInfusedQuartzStair = new BlockQuartzStair();
        blockWitor = new BlockWitor();
    }
}
