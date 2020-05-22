package thaumrev.common;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumrev.block.tile.TileWitor;
import thaumrev.block.*;
import thaumrev.item.*;
import thaumrev.util.ThaumRevResearchItem;

import static thaumrev.common.ThaumRevLibrary.*;

public class ThaumRevContent {

	public static void loadBlocks() {
		blockExcubitura = new BlockExcubitura();
		blockInfusedQuartzNormal = new BlockQuartzNormal();
		blockInfusedQuartzChiseled = new BlockQuartzChiseled();
		blockInfusedQuartzPillar = new BlockQuartzPillar();
		blockInfusedQuartzSlab = new BlockQuartzSlab();
		blockInfusedQuartzStair = new BlockQuartzStair();
		blockWitor = new BlockWitor();

		GameRegistry.registerBlock(blockExcubitura, "blockExcubitura");
		GameRegistry.registerBlock(blockInfusedQuartzNormal, "blockInfusedQuartzNormal");
		GameRegistry.registerBlock(blockInfusedQuartzChiseled, "blockInfusedQuartzChiseled");
		GameRegistry.registerBlock(blockInfusedQuartzPillar, "blockInfusedQuartzPillar");
		GameRegistry.registerBlock(blockInfusedQuartzSlab, "blockInfusedQuartzSlab");
		GameRegistry.registerBlock(blockInfusedQuartzStair, "blockInfusedQuartzStair");
		GameRegistry.registerBlock(blockWitor, "blockWitor");

		GameRegistry.registerTileEntity(TileWitor.class, "tileWitor"); //TODO: Make non TileEntity through advanced shecanery
	}

	public static void loadItems() {
		armorMaterialWarden = EnumHelper.addArmorMaterial("WARDEN", 50,
				new int[] {3, 8, 6, 3}, 22);
		toolMaterialWarden = EnumHelper.addToolMaterial("WARDEN", 50,
				50, 12.0F, 3.0F, 22);

		itemResource = new ItemResource();
		itemWardenAmulet = new ItemWardenAmulet();
		itemWardenWeapon = new ItemWardenWeapon();
		//itemFocusPurity = new ItemFocusPurity(); //Broken
		itemWardenHelm = new ItemWardenHelm();
		itemWardenChest = new ItemWardenChest();
		itemWardenLegs = new ItemWardenLegs();
		itemWardenBoots = new ItemWardenBoots();
		//itemLoveRing = new ItemLoveRing(); //Broken
		itemWaslieHammer = new ItemWaslieHammer();
		itemFocusIllumination = new ItemFocusIllumination();

		GameRegistry.registerItem(itemResource, "itemResource");
		//GameRegistry.registerItem(itemFocusPurity, "itemFocusPurity"); //Broken
		GameRegistry.registerItem(itemWardenWeapon, "itemWardenWeapon");
		GameRegistry.registerItem(itemWardenAmulet, "itemWardenAmulet");
		GameRegistry.registerItem(itemWardenHelm, "itemWardenHelm");
		GameRegistry.registerItem(itemWardenChest, "itemWardenChest");
		GameRegistry.registerItem(itemWardenLegs, "itemWardenLegs");
		GameRegistry.registerItem(itemWardenBoots, "itemWardenBoots");
		//GameRegistry.registerItem(itemLoveRing, "itemLoveRing"); //Broken
		GameRegistry.registerItem(itemWaslieHammer, "itemWaslieHammer");
		GameRegistry.registerItem(itemFocusIllumination, "itemFocusIllumination");
	}

	public static void loadRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(blockInfusedQuartzNormal),
				"XX", "XX", 'X', new ItemStack(itemResource, 1, 2));
		GameRegistry.addShapedRecipe(new ItemStack(blockInfusedQuartzChiseled),
				"X", "X", 'X', new ItemStack(blockInfusedQuartzSlab));
		GameRegistry.addShapedRecipe(new ItemStack(blockInfusedQuartzPillar, 2),
				"X", "X", 'X', new ItemStack(blockInfusedQuartzNormal));
		GameRegistry.addShapedRecipe(new ItemStack(blockInfusedQuartzSlab, 6),
				"XXX", 'X', new ItemStack(blockInfusedQuartzNormal));
		GameRegistry.addShapedRecipe(new ItemStack(blockInfusedQuartzStair, 4),
				"X  ", "XX ", "XXX", 'X', new ItemStack(blockInfusedQuartzNormal));

		recipeQuartz = ThaumcraftApi.addCrucibleRecipe("QUARTZ", new ItemStack(itemResource, 1, 2), new ItemStack(Items.quartz), new AspectList().add(Aspect.MAGIC, 4).add(Aspect.ELDRITCH, 4));
		recipeCrystal = ThaumcraftApi.addCrucibleRecipe("CRYSTAL", new ItemStack(itemResource, 1, 1), new ItemStack(itemResource, 1, 0), new AspectList().add(Aspect.MAGIC, 32).add(Aspect.CRYSTAL, 32));

		recipeWardenHelm = ThaumcraftApi.addArcaneCraftingRecipe("WARDENARMOR", new ItemStack(itemWardenHelm, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125), "XXX", "XOX", 'X', new ItemStack(itemResource, 1, 2), 'O', new ItemStack(itemResource, 1, 1));
		recipeWardenChest = ThaumcraftApi.addArcaneCraftingRecipe("WARDENARMOR", new ItemStack(itemWardenChest, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125), "X X", "XOX", "XXX", 'X', new ItemStack(itemResource, 1, 2), 'O', new ItemStack(itemResource, 1, 1));
		recipeWardenLegs = ThaumcraftApi.addArcaneCraftingRecipe("WARDENARMOR", new ItemStack(itemWardenLegs, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125), "XXX", "XOX", "X X", 'X', new ItemStack(itemResource, 1, 2), 'O', new ItemStack(itemResource, 1, 1));
		recipeWardenBoots = ThaumcraftApi.addArcaneCraftingRecipe("WARDENARMOR", new ItemStack(itemWardenBoots, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125), "XOX", "X X", 'X', new ItemStack(itemResource, 1, 2), 'O', new ItemStack(itemResource, 1, 1));
		recipeWardenWeapon = ThaumcraftApi.addArcaneCraftingRecipe("WARDENWEAPON", new ItemStack(itemWardenWeapon, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125), "X", "X", "O", 'X', new ItemStack(itemResource, 1, 2), 'O', new ItemStack(itemResource, 1, 1));

		recipeWaslieHammer = ThaumcraftApi.addArcaneCraftingRecipe("WASLIEHAMMER", new ItemStack(itemWaslieHammer, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125), "XXX", "XOX", " I ", 'X', new ItemStack(itemResource, 1, 1), 'O', new ItemStack(itemResource, 1, 2), 'I', new ItemStack(ConfigBlocks.blockMagicalLog));

		recipeFocusIllumination = ThaumcraftApi.addArcaneCraftingRecipe("ILLUMINATION", new ItemStack(itemFocusIllumination, 1), new AspectList().add(Aspect.AIR, 50).add(Aspect.FIRE, 50), " X ", "XOX", " X ", 'X', new ItemStack(ConfigItems.itemResource, 1, 1), 'O', new ItemStack(ConfigItems.itemFocusFire));
	}

	public static void loadResearch() {
		ResearchCategories.registerCategory("trevelations", new ResourceLocation("thaumrev", "textures/items/wardenamulet.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));

		researchTWarden = new ThaumRevResearchItem("TREVELATIONS", "trevelations", new AspectList(), 0, 0, 0, new ItemStack(itemWardenAmulet)).setRound().setSpecial().setAutoUnlock().registerResearchItem();
		researchTWarden.setPages(new ResearchPage("0"));
		researchExcubitura = new ThaumRevResearchItem("EXCUBITURA", "trevelations", new AspectList(), 0, -2, 0, new ItemStack(blockExcubitura)).setParents("TREVELATIONS").setAutoUnlock().registerResearchItem();
		researchExcubitura.setPages(new ResearchPage("0"));
		researchQuartz = new ThaumRevResearchItem("QUARTZ", "trevelations", new AspectList().add(EXCUBITOR, 4).add(Aspect.CRYSTAL, 4), 2, 0, 2, new ItemStack(itemResource, 0, 2)).setParents("TREVELATIONS").setRound().registerResearchItem();
		researchQuartz.setPages(new ResearchPage("0"), new ResearchPage(recipeQuartz));
		researchCrystal = new ThaumRevResearchItem("CRYSTAL", "trevelations", new AspectList().add(EXCUBITOR, 4).add(Aspect.CRYSTAL, 4), -2, 0, 2, new ItemStack(itemResource, 0, 1)).setParents("TREVELATIONS").setSecondary().registerResearchItem();
		researchCrystal.setPages(new ResearchPage("0"), new ResearchPage(recipeCrystal));
		researchWardenArmor = new ThaumRevResearchItem("WARDENARMOR", "trevelations", new AspectList().add(EXCUBITOR, 4).add(Aspect.CRYSTAL, 4).add(Aspect.ARMOR, 4), 6, 2, 3, new ItemStack(itemWardenChest)).setParents("WASLIEHAMMER").setRound().setSpecial().registerResearchItem();
		researchWardenArmor.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenHelm), new ResearchPage(recipeWardenChest), new ResearchPage(recipeWardenLegs), new ResearchPage(recipeWardenBoots));
		researchWardenWeapon = new ThaumRevResearchItem("WARDENWEAPON", "trevelations", new AspectList().add(EXCUBITOR, 4).add(Aspect.CRYSTAL, 4).add(Aspect.WEAPON, 4), 6, 6, 3, new ItemStack(itemWardenWeapon)).setParents("WASLIEHAMMER").setRound().setSpecial().registerResearchItem();
		researchWardenWeapon.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenWeapon));
		researchWaslieHammer = new ThaumRevResearchItem("WASLIEHAMMER", "trevelations", new AspectList().add(EXCUBITOR, 4).add(Aspect.CRYSTAL, 4), 4, 4, 3, new ItemStack(itemWaslieHammer)).setParentsHidden("CRYSTAL", "QUARTZ").setParents("LORE4").setRound().setSpecial().registerResearchItem();
		researchWaslieHammer.setPages(new ResearchPage("0"), new ResearchPage(recipeWaslieHammer));

		researchIllumination = new ThaumRevResearchItem("ILLUMINATION", "trevelations", new AspectList().add(Aspect.AIR, 2).add(Aspect.FIRE, 2), 0, 4, 1, new ItemStack(itemFocusIllumination)).setRound().setParentsHidden("FOCUSFIRE").registerResearchItem();
		researchIllumination.setPages(new ResearchPage("0"), new ResearchPage(recipeFocusIllumination));

		researchLore1 = new ThaumRevResearchItem("LORE1", "trevelations", new AspectList().add(EXCUBITOR, 4).add(Aspect.MIND, 4), 0, 2, 3, new ItemStack(itemWardenAmulet)).setParents("TREVELATIONS").setRound().setSpecial().registerResearchItem();
		researchLore1.setPages(new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"));
		researchLore2 = new ThaumRevResearchItem("LORE2", "trevelations", new AspectList().add(EXCUBITOR, 4).add(Aspect.MIND, 4), -2, 4, 3, new ItemStack(itemWardenAmulet)).setParents("LORE1").setRound().setSpecial().registerResearchItem();
		researchLore2.setPages(new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"));
		researchLore3 = new ThaumRevResearchItem("LORE3", "trevelations", new AspectList().add(EXCUBITOR, 4).add(Aspect.MIND, 4), 0, 6, 3, new ItemStack(itemWardenAmulet)).setParents("LORE2").setRound().setSpecial().registerResearchItem();
		researchLore3.setPages(new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"), new ResearchPage("4"), new ResearchPage("5"));
		researchLore4 = new ThaumRevResearchItem("LORE4", "trevelations", new AspectList().add(EXCUBITOR, 4).add(Aspect.MIND, 4), 2, 4, 3, new ItemStack(itemWardenAmulet)).setParents("LORE3").setRound().setSpecial().registerResearchItem();
		researchLore4.setPages(new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"));
	}

	public static void loadAspects() {
		ThaumcraftApi.registerObjectTag(new ItemStack(itemResource, 0, 0), new AspectList().add(EXCUBITOR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(itemResource, 0, 1), new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.ELDRITCH, 4).add(EXCUBITOR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(itemResource, 0, 2), new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.ELDRITCH, 4).add(EXCUBITOR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(itemWardenHelm), new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(EXCUBITOR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(itemWardenChest), new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(EXCUBITOR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(itemWardenLegs), new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(EXCUBITOR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(itemWardenBoots), new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(EXCUBITOR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(itemWardenWeapon), new AspectList().add(Aspect.WEAPON, 4).add(Aspect.ELDRITCH, 4).add(EXCUBITOR, 1));
	}
}
