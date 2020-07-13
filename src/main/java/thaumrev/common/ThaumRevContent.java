package thaumrev.common;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumrev.block.*;
import thaumrev.item.ItemResource;
import thaumrev.item.ItemWardenBow;
import thaumrev.item.ItemWardenWeapon;
import thaumrev.item.ItemWaslieHammer;
import thaumrev.item.armor.*;
import thaumrev.item.baubles.ItemLoveRing;
import thaumrev.item.baubles.ItemWardenAmulet;
import thaumrev.item.focus.ItemFocusIllumination;
import thaumrev.item.focus.ItemFocusPurity;
import thaumrev.util.ThaumRevResearchItem;

import static thaumcraft.api.ThaumcraftApi.addWarpToResearch;
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
	}

	public static void loadItems() {
		armorMaterialWarden = EnumHelper.addArmorMaterial("WARDEN", 50,
				new int[] {3, 8, 6, 3}, 0);
		toolMaterialWarden = EnumHelper.addToolMaterial("WARDEN", 9,
				50, 12.0F, 3.0F, 0);

		armorMaterialWardencloth = EnumHelper.addArmorMaterial("WARDENCLOTH", 50,
				new int[] {1, 3, 2, 1}, 15);

		armorMaterialCrimsoncloth = EnumHelper.addArmorMaterial("CRIMSONCLOTH", 50,
				new int[] {2, 3, 2, 1}, 15);

		itemResource = new ItemResource();
		itemWardenAmulet = new ItemWardenAmulet();
		itemWardenWeapon = new ItemWardenWeapon();
		itemWardenBow = new ItemWardenBow();
		itemFocusPurity = new ItemFocusPurity(); //Broken
		itemCrimsonHat = new ItemCrimsonHat();
		itemWardenHelm = new ItemWardenHelm();
		itemWardenChest = new ItemWardenChest();
		itemWardenLegs = new ItemWardenLegs();
		itemWardenBoots = new ItemWardenBoots();
		itemLoveRing = new ItemLoveRing();
		itemWaslieHammer = new ItemWaslieHammer();
		itemFocusIllumination = new ItemFocusIllumination();

		GameRegistry.registerItem(itemResource, "itemResource");
		GameRegistry.registerItem(itemFocusPurity, "itemFocusPurity"); //Broken
		GameRegistry.registerItem(itemWardenWeapon, "itemWardenWeapon");
		GameRegistry.registerItem(itemWardenBow, "itemWardenBow");
		GameRegistry.registerItem(itemWardenAmulet, "itemWardenAmulet");
		GameRegistry.registerItem(itemWardenHelm, "itemWardenHelm");
		GameRegistry.registerItem(itemWardenChest, "itemWardenChest");
		GameRegistry.registerItem(itemWardenLegs, "itemWardenLegs");
		GameRegistry.registerItem(itemWardenBoots, "itemWardenBoots");
		GameRegistry.registerItem(itemCrimsonHat, "itemCrimsonHat");
		GameRegistry.registerItem(itemLoveRing, "itemLoveRing");
		GameRegistry.registerItem(itemWaslieHammer, "itemWaslieHammer");
		GameRegistry.registerItem(itemFocusIllumination, "itemFocusIllumination");
	}

	public static void loadRecipes() {
		GameRegistry.addShapedRecipe(
				new ItemStack(blockInfusedQuartzNormal),
				"XX", "XX",
				'X', new ItemStack(itemResource, 1, 2));
		GameRegistry.addShapedRecipe(
				new ItemStack(blockInfusedQuartzChiseled),
				"X", "X",
				'X', new ItemStack(blockInfusedQuartzSlab));
		GameRegistry.addShapedRecipe(
				new ItemStack(blockInfusedQuartzPillar, 2),
				"X", "X",
				'X', new ItemStack(blockInfusedQuartzNormal));
		GameRegistry.addShapedRecipe(
				new ItemStack(blockInfusedQuartzSlab, 6),
				"XXX",
				'X', new ItemStack(blockInfusedQuartzNormal));
		GameRegistry.addShapedRecipe(
				new ItemStack(blockInfusedQuartzStair, 4),
				"X  ", "XX ", "XXX",
				'X', new ItemStack(blockInfusedQuartzNormal));

		recipeQuartz = ThaumcraftApi.addCrucibleRecipe(
				"QUARTZ",
				new ItemStack(itemResource, 1, 2),
				new ItemStack(Items.quartz),
				new AspectList().add(Aspect.MAGIC, 4).add(Aspect.ELDRITCH, 4));
		recipeCrystal = ThaumcraftApi.addCrucibleRecipe(
				"CRYSTAL",
				new ItemStack(itemResource, 1, 1),
				new ItemStack(itemResource, 1, 0),
				new AspectList().add(Aspect.MAGIC, 32).add(Aspect.CRYSTAL, 32));

		recipeWardencloth = ThaumcraftApi.addCrucibleRecipe(
				"WARDENCLOTH",
				new ItemStack(itemResource, 1, 3),
				new ItemStack(ConfigItems.itemResource, 1, 7),
				new AspectList().add(Aspect.MAGIC, 8).add(Aspect.CLOTH, 8));

		recipeCrimsoncloth = ThaumcraftApi.addCrucibleRecipe(
				"CRIMSONCLOTH",
				new ItemStack(itemResource, 1, 4),
				new ItemStack(ConfigItems.itemResource, 1, 7),
				new AspectList().add(Aspect.WEAPON, 8).add(Aspect.CLOTH, 8));

		recipeHelmetCultistRobe = ThaumcraftApi.addArcaneCraftingRecipe(
				"CRIMSONCLOTH",
				new ItemStack(ConfigItems.itemHelmetCultistRobe, 1),
				new AspectList().add(Aspect.ORDER, 5).add(Aspect.ENTROPY, 5).add(Aspect.AIR, 5).add(Aspect.EARTH, 5).add(Aspect.FIRE, 5).add(Aspect.WATER, 5),
				"XXX", "X X",
				'X', new ItemStack(itemResource, 1, 4));
		recipeChestCultistRobe = ThaumcraftApi.addArcaneCraftingRecipe(
				"CRIMSONCLOTH",
				new ItemStack(ConfigItems.itemChestCultistRobe, 1),
				new AspectList().add(Aspect.ORDER, 5).add(Aspect.ENTROPY, 5).add(Aspect.AIR, 5).add(Aspect.EARTH, 5).add(Aspect.FIRE, 5).add(Aspect.WATER, 5),
				"X X", "XXX", "XXX",
				'X', new ItemStack(itemResource, 1, 4));
		recipeLegsCultistRobe = ThaumcraftApi.addArcaneCraftingRecipe(
				"CRIMSONCLOTH",
				new ItemStack(ConfigItems.itemLegsCultistRobe, 1),
				new AspectList().add(Aspect.ORDER, 5).add(Aspect.ENTROPY, 5).add(Aspect.AIR, 5).add(Aspect.EARTH, 5).add(Aspect.FIRE, 5).add(Aspect.WATER, 5),
				"XXX", "X X", "X X",
				'X', new ItemStack(itemResource, 1, 4));
		recipeBootsCultist = ThaumcraftApi.addArcaneCraftingRecipe(
				"CRIMSONCLOTH",
				new ItemStack(ConfigItems.itemBootsCultist),
				new AspectList().add(Aspect.ORDER, 5).add(Aspect.ENTROPY, 5).add(Aspect.AIR, 5).add(Aspect.EARTH, 5).add(Aspect.FIRE, 5).add(Aspect.WATER, 5),
				"X X", "X X",
				'X', new ItemStack(itemResource, 1, 4));

		recipeCrimsonHat = ThaumcraftApi.addArcaneCraftingRecipe(
				"CRIMSONHAT",
				new ItemStack(itemCrimsonHat),
				new AspectList().add(Aspect.ORDER, 40).add(Aspect.ENTROPY, 40).add(Aspect.AIR, 40).add(Aspect.EARTH, 40).add(Aspect.FIRE, 40).add(Aspect.WATER, 40),
				" X ", "XOX", " X ",
				'X', new ItemStack(itemResource, 1, 4),
				'O', new ItemStack(ConfigItems.itemGolemDecoration, 1));

		recipeVoidcloth = ThaumcraftApi.addCrucibleRecipe(
				"VOIDCLOTH",
				new ItemStack(itemResource, 1, 5),
				new ItemStack(ConfigItems.itemResource, 1, 7),
				new AspectList().add(Aspect.ELDRITCH, 8).add(Aspect.CLOTH, 8));

		recipeWardenHelm = ThaumcraftApi.addArcaneCraftingRecipe(
				"WARDENARMOR",
				new ItemStack(itemWardenHelm, 1),
				new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				"XXX", "XOX",
				'X', new ItemStack(itemResource, 1, 2),
				'O', new ItemStack(itemResource, 1, 1));
		recipeWardenChest = ThaumcraftApi.addArcaneCraftingRecipe(
				"WARDENARMOR",
				new ItemStack(itemWardenChest, 1),
				new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				"X X", "XOX", "XXX",
				'X', new ItemStack(itemResource, 1, 2),
				'O', new ItemStack(itemResource, 1, 1));
		recipeWardenLegs = ThaumcraftApi.addArcaneCraftingRecipe(
				"WARDENARMOR",
				new ItemStack(itemWardenLegs, 1),
				new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				"XXX", "XOX", "X X",
				'X', new ItemStack(itemResource, 1, 2),
				'O', new ItemStack(itemResource, 1, 1));
		recipeWardenBoots = ThaumcraftApi.addArcaneCraftingRecipe(
				"WARDENARMOR",
				new ItemStack(itemWardenBoots, 1),
				new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				"XOX", "X X",
				'X', new ItemStack(itemResource, 1, 2),
				'O', new ItemStack(itemResource, 1, 1));

		recipeWardenWeapon = ThaumcraftApi.addArcaneCraftingRecipe(
				"WARDENWEAPON",
				new ItemStack(itemWardenWeapon, 1),
				new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				"X", "X", "O",
				'X', new ItemStack(itemResource, 1, 2),
				'O', new ItemStack(itemResource, 1, 1));

		recipeWaslieHammer = ThaumcraftApi.addArcaneCraftingRecipe(
				"WASLIEHAMMER",
				new ItemStack(itemWaslieHammer, 1),
				new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				"XXX", "XOX", " I ",
				'X', new ItemStack(itemResource, 1, 1),
				'O', new ItemStack(itemResource, 1, 2),
				'I', new ItemStack(ConfigBlocks.blockMagicalLog));

		recipeFocusIllumination = ThaumcraftApi.addArcaneCraftingRecipe(
				"ILLUMINATION",
				new ItemStack(itemFocusIllumination, 1),
				new AspectList().add(Aspect.AIR, 50).add(Aspect.FIRE, 50), " X ", "XOX", " X ",
				'X', new ItemStack(ConfigItems.itemResource, 1, 1),
				'O', new ItemStack(ConfigItems.itemFocusFire));
	}

	public static void loadResearch() {
		ResearchCategories.registerCategory(
				"trevelations",
				new ResourceLocation("thaumrev", "textures/items/wardenamulet.png"),
				new ResourceLocation("thaumrev", "textures/gui/gui_researchbackthaumrev.png"));

		researchThaumRev = new ThaumRevResearchItem(
				"TREVELATIONS",
				"trevelations",
				new AspectList(), 0, -5, 0,
				new ItemStack(itemWardenAmulet)).setRound().setSpecial().setAutoUnlock().registerResearchItem();
		researchThaumRev.setPages(
				new ResearchPage("0"));

		researchWardens = new ThaumRevResearchItem(
				"WARDENS",
				"trevelations",
				new AspectList(), -8, -3, 0,
				new ItemStack(itemWardenAmulet)).setParents("TREVELATIONS").setSpecial().setAspectTriggers(EXCUBITOR).registerResearchItem();
		researchWardens.setPages(
				new ResearchPage("0"));

		researchExcubitura = new ThaumRevResearchItem(
				"EXCUBITURA",
				"trevelations",
				new AspectList(),
				-6, 0, 0,
				new ItemStack(blockExcubitura)).setParents("WARDENS").setRound().setAutoUnlock().registerResearchItem();
		researchExcubitura.setPages(
				new ResearchPage("0"));

		researchCrystal = new ThaumRevResearchItem(
				"CRYSTAL",
				"trevelations",
				new AspectList().add(EXCUBITOR, 4).add(Aspect.CRYSTAL, 4),
				-6, 2, 2,
				new ItemStack(
						itemResource,
						0,
						1)).setParents("EXCUBITURA").setSecondary().registerResearchItem();
		researchCrystal.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeCrystal));

		researchQuartz = new ThaumRevResearchItem(
				"QUARTZ",
				"trevelations",
				new AspectList().add(EXCUBITOR, 4).add(Aspect.CRYSTAL, 4),
				-6, 4, 2,
				new ItemStack(
						itemResource,
						0,
						2)).setParents("LORE4").registerResearchItem();
		researchQuartz.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeQuartz));

		researchWardencloth = new ThaumRevResearchItem(
				"WARDENCLOTH",
				"trevelations",
				new AspectList(),
				-10, 0, 0,
				new ItemStack(
						itemResource,
						0,
						3)).setParents("WARDENS").setParentsHidden("ENCHFABRIC").registerResearchItem();
		researchWardencloth.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeWardencloth));

		researchWardenclothWand = new ThaumRevResearchItem(
				"ROD_WARDENCLOTH",
				"trevelations",
				new AspectList(),
				-10, 2, 1,
				new ItemStack(
						itemResource,
						0,
						3)).setParents("WARDENCLOTH").setParentsHidden("ROD_silverwood").registerResearchItem();
		researchWardenclothWand.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeWardencloth));

		researchWardenclothStaff = new ThaumRevResearchItem(
				"ROD_WARDENCLOTH_STAFF",
				"trevelations",
				new AspectList(),
				-10, 4, 1,
				new ItemStack(
						itemResource,
						0,
						3)).setParents("ROD_WARDENCLOTH").setParentsHidden("ROD_silverwood_staff").registerResearchItem();
		researchWardenclothStaff.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeWardencloth));

		researchWardenArmor = new ThaumRevResearchItem(
				"WARDENARMOR",
				"trevelations",
				new AspectList().add(EXCUBITOR, 4).add(Aspect.CRYSTAL, 4).add(Aspect.ARMOR, 4),
				-4, 1, 3,
				new ItemStack(itemWardenChest)).setParents("WASLIEHAMMER").setRound().setSpecial().registerResearchItem();
		researchWardenArmor.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeWardenHelm),
				new ResearchPage(recipeWardenChest),
				new ResearchPage(recipeWardenLegs),
				new ResearchPage(recipeWardenBoots));

		researchWardenWeapon = new ThaumRevResearchItem(
				"WARDENWEAPON",
				"trevelations",
				new AspectList().add(EXCUBITOR, 4).add(Aspect.CRYSTAL, 4).add(Aspect.WEAPON, 4),
				-4, 5, 3,
				new ItemStack(itemWardenWeapon)).setParents("WASLIEHAMMER").setRound().setSpecial().registerResearchItem();
		researchWardenWeapon.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeWardenWeapon));

		researchWaslieHammer = new ThaumRevResearchItem(
				"WASLIEHAMMER",
				"trevelations",
				new AspectList().add(EXCUBITOR, 4).add(Aspect.CRYSTAL, 4),
				-4, 3, 3,
				new ItemStack(itemWaslieHammer)).setParents("CRYSTAL", "QUARTZ").setParentsHidden("ROD_WARDENCLOTH_STAFF").setRound().setSpecial().registerResearchItem();
		researchWaslieHammer.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeWaslieHammer));

		researchIllumination = new ThaumRevResearchItem(
				"ILLUMINATION",
				"trevelations",
				new AspectList().add(Aspect.AIR, 2).add(Aspect.FIRE, 2),
				8, 4, 1,
				new ItemStack(itemFocusIllumination)).setRound().setParentsHidden("FOCUSFIRE").registerResearchItem();
		researchIllumination.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeFocusIllumination));

		researchLore1 = new ThaumRevResearchItem(
				"LORE1",
				"trevelations",
				new AspectList().add(EXCUBITOR, 4).add(Aspect.MIND, 4),
				-8, -1, 3,
				new ItemStack(itemWardenAmulet)).setParents("WARDENS").setSecondary().setSpecial().registerResearchItem();
		researchLore1.setPages(
				new ResearchPage("0"),
				new ResearchPage("1"),
				new ResearchPage("2"));
		researchLore2 = new ThaumRevResearchItem("LORE2", "trevelations",
				new AspectList().add(EXCUBITOR, 4).add(Aspect.MIND, 4),
				-8, 1, 3,
				new ItemStack(itemWardenAmulet)).setParents("LORE1").setSecondary().setSpecial().registerResearchItem();
		researchLore2.setPages(
				new ResearchPage("0"),
				new ResearchPage("1"),
				new ResearchPage("2"),
				new ResearchPage("3"));
		researchLore3 = new ThaumRevResearchItem(
				"LORE3",
				"trevelations",
				new AspectList().add(EXCUBITOR, 4).add(Aspect.MIND, 4),
				-8, 3, 3,
				new ItemStack(itemWardenAmulet)).setParents("LORE2").setSecondary().setSpecial().registerResearchItem();
		researchLore3.setPages(
				new ResearchPage("0"),
				new ResearchPage("1"),
				new ResearchPage("2"),
				new ResearchPage("3"),
				new ResearchPage("4"),
				new ResearchPage("5"));
		researchLore4 = new ThaumRevResearchItem(
				"LORE4", "trevelations",
				new AspectList().add(EXCUBITOR, 4).add(Aspect.MIND, 4),
				-8, 5, 3,
				new ItemStack(itemWardenAmulet)).setParents("LORE3").setSecondary().setSpecial().registerResearchItem();
		researchLore4.setPages(
				new ResearchPage("0"),
				new ResearchPage("1"),
				new ResearchPage("2"),
				new ResearchPage("3"));

		researchCrimsonCult = new ThaumRevResearchItem(
				"CRIMSONCULT",
				"trevelations",
				new AspectList(),
				0, -3, 0,
				new ItemStack(
						ConfigItems.itemEldritchObject,
						0,
						1)).setRound().setSpecial().setParents("TREVELATIONS").setItemTriggers(
								new ItemStack(
										ConfigItems.itemEldritchObject,
										0,
										1)).registerResearchItem();
		researchCrimsonCult.setPages(
				new ResearchPage("0"));
		addWarpToResearch("CRIMSONCULT", 4);

		researchCrimsoncloth = new ThaumRevResearchItem(
				"CRIMSONCLOTH",
				"trevelations",
				new AspectList(),
				0, -1, 0,
				new ItemStack(
						itemResource,
						0,
						4)).setParents("CRIMSONCULT").setParentsHidden("ENCHFABRIC").registerResearchItem();
		researchCrimsoncloth.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeCrimsoncloth),
				new ResearchPage(recipeHelmetCultistRobe),
				new ResearchPage(recipeChestCultistRobe),
				new ResearchPage(recipeLegsCultistRobe),
				new ResearchPage(recipeBootsCultist));

		researchEldritch = new ThaumRevResearchItem(
				"ELDRITCH",
				"trevelations",
				new AspectList(),
				8, -3, 0,
				new ItemStack(ConfigItems.itemEldritchObject)).setRound().setSpecial().setParents("TREVELATIONS").setItemTriggers(
				new ItemStack(ConfigItems.itemEldritchObject)).registerResearchItem();
		researchEldritch.setPages(
				new ResearchPage("0"));
		addWarpToResearch("ELDRITCH", 4);


		researchVoidcloth = new ThaumRevResearchItem(
				"VOIDCLOTH",
				"trevelations",
				new AspectList(),
				8, -1, 0,
				new ItemStack(
						itemResource,
						0,
						5)).setParents("ELDRITCH").setParentsHidden("ENCHFABRIC").registerResearchItem();
		researchVoidcloth.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeVoidcloth));
	}

	public static void loadAspects() {
		ThaumcraftApi.registerObjectTag(
				new ItemStack(itemResource, 0, 0),
				new AspectList().add(EXCUBITOR, 1));
		ThaumcraftApi.registerObjectTag(
				new ItemStack(itemResource, 0, 1),
				new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.ELDRITCH, 4).add(EXCUBITOR, 1));
		ThaumcraftApi.registerObjectTag(
				new ItemStack(itemResource, 0, 2),
				new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.ELDRITCH, 4).add(EXCUBITOR, 1));
		ThaumcraftApi.registerObjectTag(
				new ItemStack(itemWardenHelm),
				new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(EXCUBITOR, 1));
		ThaumcraftApi.registerObjectTag(
				new ItemStack(itemWardenChest),
				new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(EXCUBITOR, 3));
		ThaumcraftApi.registerObjectTag(
				new ItemStack(itemWardenLegs),
				new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(EXCUBITOR, 2));
		ThaumcraftApi.registerObjectTag(
				new ItemStack(itemWardenBoots),
				new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(EXCUBITOR, 1));
		ThaumcraftApi.registerObjectTag(
				new ItemStack(itemWardenWeapon),
				new AspectList().add(Aspect.WEAPON, 4).add(Aspect.ELDRITCH, 4).add(EXCUBITOR, 1));
		ThaumcraftApi.registerObjectTag(
				new ItemStack(ConfigItems.itemHelmetCultistRobe),
				new AspectList().add(RED, 1));
	}
}
