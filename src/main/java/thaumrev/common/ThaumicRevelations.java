package thaumrev.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import thaumrev.client.gui.GuiHandler;
import thaumrev.util.TabTRevelations;
import thaumrev.util.wardenic.WardenicChargeEvents;
import thaumrev.util.wardenic.WardenicUpgrades;
import thaumrev.world.WorldGenExcubitura;

@Mod(modid = ThaumRevLibrary.MOD_ID, name = "Thaumic Revelations", version = "v0.2.2.24", dependencies = "required-after:Thaumcraft")
public class ThaumicRevelations {

	@Instance(ThaumRevLibrary.MOD_ID)
	public static ThaumicRevelations instance;

	@SidedProxy(serverSide = "thaumrev.common.CommonProxy", clientSide = "thaumrev.client.ClientProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.initRenderers();
		GuiHandler.init();

		WardenicChargeEvents.init();
		WardenicUpgrades.init();

		ThaumRevLibrary.tabThaumRev = new TabTRevelations(ThaumRevLibrary.MOD_ID);
		ThaumRevContent.loadBlocks();
		ThaumRevContent.loadItems();

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
