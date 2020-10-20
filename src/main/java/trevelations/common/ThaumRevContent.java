package trevelations.common;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.api.wands.StaffRod;
import thaumcraft.api.wands.WandRod;
import trevelations.block.*;
import trevelations.item.*;
import trevelations.item.armor.ItemWardenArmor;
import trevelations.item.armor.ItemWardenclothArmor;
import trevelations.item.baubles.ItemLoveRing;
import trevelations.item.baubles.ItemWardenAmulet;
import trevelations.item.focus.ItemFocusIllumination;
import trevelations.item.wands.ItemWandCore;
import trevelations.util.ThaumRevResearchItem;

import static thaumcraft.api.ThaumcraftApi.addWarpToResearch;
import static trevelations.common.ThaumRevLibrary.*;

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
		armorMaterialWarden = EnumHelper.addArmorMaterial(
				"WARDEN",
				50,
				new int[] {3, 6, 8, 3},
				0);
		armorMaterialWardencloth = EnumHelper.addArmorMaterial(
				"WARDENCLOTH",
				50,
				new int[] {0, 2, 1, 0},
				15);
		armorMaterialCrimsoncloth = EnumHelper.addArmorMaterial(
				"CRIMSONCLOTH",
				50,
				new int[] {2, 3, 2, 1},
				15);

		toolMaterialWarden = EnumHelper.addToolMaterial(
				"WARDEN",
				9,
				50,
				12.0F,
				4,
				0);

		WAND_ROD_WARDENCLOTH = new WandRod(
				"wardencloth",
				150,
				new ItemStack(
						itemWandCore,
						1,
						0),
				12,
				new ResourceLocation("trevelations", "textures/models/wand_rod_wardencloth.png"));

		WAND_ROD_WARDENCLOTH.setGlowing(true);

		STAFF_ROD_WARDENCLOTH = new StaffRod(
				"wardencloth",
				375,
				new ItemStack(
						itemWandCore,
						1,
						10),
				32,
				new ResourceLocation("trevelations", "textures/models/rod_wardencloth_staff.png"));

		STAFF_ROD_WARDENCLOTH.setGlowing(true);

		/*WAND_ROD_VOIDWOOD = new WandRod(
				"voidwood",
				100,
				new ItemStack(
						itemWandCore,
						1,
						1),
				9,
				new ResourceLocation("trevelations", "textures/models/wand_rod_tainted.png"));
		WAND_ROD_CRIMSONCRYSTAL = new WandRod(
				"crimsoncrystal",
				50,
				new ItemStack(
						itemWandCore,
						1,
						2),
				6,
				new ResourceLocation("trevelations", "textures/models/wand_rod_crimsoncrystal.png"));


		STAFF_ROD_VOIDWOOD = new StaffRod(
				"voidwood",
				250,
				new ItemStack(
						itemWandCore,
						1,
						11),
				24,
				new ResourceLocation("trevelations", "textures/models/rod_voidwood_staff.png"));
		STAFF_ROD_CRIMSONCULT = new StaffRod(
				"crimsoncult",
				500,
				new ItemStack(
						itemWandCore,
						1,
						12),
				64,
				new ResourceLocation("trevelations", "textures/models/rod_crimsoncult_staff.png"));

		STAFF_ROD_VOIDWOOD.setRunes(true);
		STAFF_ROD_CRIMSONCULT.setRunes(true);
		STAFF_ROD_CRIMSONCULT.setRunes(true);
		 */

		itemWandCore = new ItemWandCore();

		itemResource = new ItemResource();
		itemWardenAmulet = new ItemWardenAmulet();
		itemWardenWeapon = new ItemWardenWeapon();
		itemWardenBow = new ItemWardenBow();
		// itemFocusPurity = new ItemFocusPurity(); // Broken

		// itemCrimsonHat = new ItemCrimsonHat();

		itemWardenHelm = new ItemWardenArmor(0, "itemWardenHelm");
		itemWardenChest = new ItemWardenArmor(1, "itemWardenChest");
		itemWardenLegs = new ItemWardenArmor(2, "itemWardenLegs");
		itemWardenBoots = new ItemWardenArmor(3, "itemWardenBoots");

		itemWardenclothChest = new ItemWardenclothArmor(1, "itemWardenclothChest");
		itemWardenclothLegs = new ItemWardenclothArmor(2, "itemWardenclothLegs");
		itemWardenclothBoots = new ItemWardenclothArmor(3, "itemWardenclothBoots");

		itemLoveRing = new ItemLoveRing();
		itemWaslieHammer = new ItemWaslieHammer();
		itemFocusIllumination = new ItemFocusIllumination();

		itemExcubituraSeeds = new ItemExcubituraSeeds();

		GameRegistry.registerItem(itemResource, "itemResource");
		// GameRegistry.registerItem(itemFocusPurity, "itemFocusPurity"); // Broken
		GameRegistry.registerItem(itemWardenWeapon, "itemWardenWeapon");
		GameRegistry.registerItem(itemWardenBow, "itemWardenBow");
		GameRegistry.registerItem(itemWardenAmulet, "itemWardenAmulet");

		// GameRegistry.registerItem(itemCrimsonHat, "itemCrimsonHat");

		GameRegistry.registerItem(itemWardenHelm, "itemWardenHelm");
		GameRegistry.registerItem(itemWardenChest, "itemWardenChest");
		GameRegistry.registerItem(itemWardenLegs, "itemWardenLegs");
		GameRegistry.registerItem(itemWardenBoots, "itemWardenBoots");

		GameRegistry.registerItem(itemWardenclothChest, "itemWardenclothChest");
		GameRegistry.registerItem(itemWardenclothLegs, "itemWardenclothLegs");
		GameRegistry.registerItem(itemWardenclothBoots, "itemWardenclothBoots");

		GameRegistry.registerItem(itemLoveRing, "itemLoveRing");
		GameRegistry.registerItem(itemWaslieHammer, "itemWaslieHammer");
		GameRegistry.registerItem(itemFocusIllumination, "itemFocusIllumination");

		GameRegistry.registerItem(itemExcubituraSeeds, "itemExcubituraSeeds");

		GameRegistry.registerItem(itemWandCore, "itemWandCore");
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

		recipeWardenclothWand = ThaumcraftApi.addInfusionCraftingRecipe(
				"ROD_wardencloth",
				new ItemStack(itemWandCore, 1, 0),
				5,
				new AspectList()
						.add(Aspect.AIR, 10)
						.add(Aspect.EARTH, 10)
						.add(Aspect.ENTROPY, 10)
						.add(Aspect.FIRE, 10)
						.add(Aspect.ORDER, 10)
						.add(EXCUBITOR, 20)
						.add(Aspect.WATER, 10),
				ItemApi.getItem("itemWandRod", 2),
				new ItemStack[] {
						new ItemStack(itemResource, 1),
						new ItemStack(itemResource, 1, 3),
						ItemApi.getItem("itemResource", 14),
						new ItemStack(itemResource, 1, 1),
						ItemApi.getItem("itemResource", 14),
						new ItemStack(itemResource, 1, 3),
				}
		);

		/*recipeVoidwoodWand = ThaumcraftApi.addArcaneCraftingRecipe(
				"ROD_voidcloth",
				new ItemStack(itemWandCore, 1, 0),
				new AspectList()
						.add(Aspect.AIR, WandRod.rods.get("wardencloth").getCraftCost() / 6)
						.add(Aspect.EARTH, WandRod.rods.get("wardencloth").getCraftCost() / 6)
						.add(Aspect.ENTROPY, WandRod.rods.get("wardencloth").getCraftCost() / 6)
						.add(Aspect.FIRE, WandRod.rods.get("wardencloth").getCraftCost() / 6)
						.add(Aspect.ORDER, WandRod.rods.get("wardencloth").getCraftCost() / 6)
						.add(Aspect.WATER, WandRod.rods.get("wardencloth").getCraftCost() / 6),
				" X", "X ",
				'X', new ItemStack(itemResource, 1, 0));
		recipeRedCrystalWand = ThaumcraftApi.addArcaneCraftingRecipe(
				"ROD_crimsoncrystal",
				new ItemStack(itemWandCore, 1, 0),
				new AspectList()
						.add(Aspect.AIR, WandRod.rods.get("wardencloth").getCraftCost())
						.add(Aspect.EARTH, WandRod.rods.get("wardencloth").getCraftCost())
						.add(Aspect.ENTROPY, WandRod.rods.get("wardencloth").getCraftCost())
						.add(Aspect.FIRE, WandRod.rods.get("wardencloth").getCraftCost())
						.add(Aspect.ORDER, WandRod.rods.get("wardencloth").getCraftCost())
						.add(Aspect.WATER, WandRod.rods.get("wardencloth").getCraftCost()),
				" X", "X ",
				'X', new ItemStack(itemResource, 1, 0));
		 */

		recipeWardenclothStaff = ThaumcraftApi.addArcaneCraftingRecipe(
				"ROD_wardencloth_staff",
				new ItemStack(itemWandCore, 1, 10),
				new AspectList()
						.add(Aspect.AIR, StaffRod.rods.get("wardencloth_staff").getCraftCost() / 6)
						.add(Aspect.EARTH, StaffRod.rods.get("wardencloth_staff").getCraftCost() / 6)
						.add(Aspect.ENTROPY, StaffRod.rods.get("wardencloth_staff").getCraftCost() / 6)
						.add(Aspect.FIRE, StaffRod.rods.get("wardencloth_staff").getCraftCost() / 6)
						.add(Aspect.ORDER, StaffRod.rods.get("wardencloth_staff").getCraftCost() / 6)
						.add(Aspect.WATER, StaffRod.rods.get("wardencloth_staff").getCraftCost() / 6),
				"  O", " X ", "X  ",
				'X', new ItemStack(itemWandCore, 1, 0),
				'O', ItemApi.getItem("itemResource", 15));

		// recipeVoidwoodStaff = crafting

		// recipeCrimsonStaff = ThaumcraftApi.addInfusionCraftingRecipe(
		//		"ROD_crimsoncult_staff",
		//		new ItemStack(itemWandCore, 1, 20),
		//		20,
		//		new AspectList(),
		//		new ItemStack(itemWandCore, 1, 2),
		//		new ItemStack[] {
		//			new ItemStack(),
		//		}
		// ;

		recipeQuartz = ThaumcraftApi.addCrucibleRecipe(
				"QUARTZ",
				new ItemStack(itemResource, 1, 2),
				new ItemStack(Items.quartz),
				new AspectList()
					.add(Aspect.MAGIC, 4)
					.add(Aspect.ELDRITCH, 4));
		recipeCrystal = ThaumcraftApi.addCrucibleRecipe(
				"CRYSTAL",
				new ItemStack(itemResource, 1, 1),
				new ItemStack(itemResource, 1, 0),
				new AspectList()
					.add(Aspect.MAGIC, 32)
					.add(Aspect.CRYSTAL, 32));

		recipeWardencloth = ThaumcraftApi.addCrucibleRecipe(
				"WARDENCLOTH",
				new ItemStack(itemResource, 1, 3),
				ItemApi.getItem("itemResource", 7),
				new AspectList()
					.add(Aspect.MAGIC, 8)
					.add(Aspect.CLOTH, 8));

		recipeWardenclothChest = ThaumcraftApi.addArcaneCraftingRecipe(
				"WARDENCLOTH",
				new ItemStack(itemWardenclothChest, 1),
				new AspectList()
						.add(Aspect.ORDER, 8)
						.add(Aspect.ENTROPY, 8)
						.add(Aspect.AIR, 8)
						.add(Aspect.EARTH, 8)
						.add(Aspect.FIRE, 8)
						.add(Aspect.WATER, 8),
				"X X", "XXX", "XXX",
				'X', new ItemStack(itemResource, 1, 3));
		recipeWardenclothLegs = ThaumcraftApi.addArcaneCraftingRecipe(
				"WARDENCLOTH",
				new ItemStack(itemWardenclothLegs, 1),
				new AspectList()
						.add(Aspect.ORDER, 7)
						.add(Aspect.ENTROPY, 7)
						.add(Aspect.AIR, 7)
						.add(Aspect.EARTH, 7)
						.add(Aspect.FIRE, 7)
						.add(Aspect.WATER, 7),
				"XXX", "X X", "X X",
				'X', new ItemStack(itemResource, 1, 3));
		recipeWardenclothBoots = ThaumcraftApi.addArcaneCraftingRecipe(
				"WARDENCLOTH",
				new ItemStack(itemWardenclothBoots),
				new AspectList()
						.add(Aspect.ORDER, 4)
						.add(Aspect.ENTROPY, 4)
						.add(Aspect.AIR, 4)
						.add(Aspect.EARTH, 4)
						.add(Aspect.FIRE, 4)
						.add(Aspect.WATER, 4),
				"X X", "X X",
				'X', new ItemStack(itemResource, 1, 3));

		recipeCrimsoncloth = ThaumcraftApi.addCrucibleRecipe(
				"CRIMSONCLOTH",
				new ItemStack(itemResource, 1, 4),
				ItemApi.getItem("itemResource", 7),
				new AspectList()
					.add(Aspect.WEAPON, 8)
					.add(Aspect.CLOTH, 8));

		recipeHelmetCultistRobe = ThaumcraftApi.addArcaneCraftingRecipe(
				"CRIMSONCLOTH",
				ItemApi.getItem("itemHelmetCultistRobe", 0),
				new AspectList().add(Aspect.ORDER, 5)
					.add(Aspect.ENTROPY, 5)
					.add(Aspect.AIR, 5)
					.add(Aspect.EARTH, 5)
					.add(Aspect.FIRE, 5)
					.add(Aspect.WATER, 5),
				"XXX", "X X",
				'X', new ItemStack(itemResource, 1, 4));
		recipeChestCultistRobe = ThaumcraftApi.addArcaneCraftingRecipe(
				"CRIMSONCLOTH",
				ItemApi.getItem("itemChestCultistRobe", 0),
				new AspectList()
					.add(Aspect.ORDER, 5)
					.add(Aspect.ENTROPY, 5)
					.add(Aspect.AIR, 5)
					.add(Aspect.EARTH, 5)
					.add(Aspect.FIRE, 5)
					.add(Aspect.WATER, 5),
				"X X", "XXX", "XXX",
				'X', new ItemStack(itemResource, 1, 4));
		recipeLegsCultistRobe = ThaumcraftApi.addArcaneCraftingRecipe(
				"CRIMSONCLOTH",
				ItemApi.getItem("itemLegsCultistRobe", 0),
				new AspectList()
					.add(Aspect.ORDER, 5)
					.add(Aspect.ENTROPY, 5)
					.add(Aspect.AIR, 5)
					.add(Aspect.EARTH, 5)
					.add(Aspect.FIRE, 5)
					.add(Aspect.WATER, 5),
				"XXX", "X X", "X X",
				'X', new ItemStack(itemResource, 1, 4));
		recipeBootsCultist = ThaumcraftApi.addArcaneCraftingRecipe(
				"CRIMSONCLOTH",
				ItemApi.getItem("itemBootsCultist", 0),
				new AspectList()
					.add(Aspect.ORDER, 5)
					.add(Aspect.ENTROPY, 5)
					.add(Aspect.AIR, 5)
					.add(Aspect.EARTH, 5)
					.add(Aspect.FIRE, 5)
					.add(Aspect.WATER, 5),
				"X X", "X X",
				'X', new ItemStack(itemResource, 1, 4));

		/* recipeCrimsonHat = ThaumcraftApi.addArcaneCraftingRecipe(
				"CRIMSONHAT",
				new ItemStack(itemCrimsonHat),
				new AspectList().add(Aspect.ORDER, 40).add(Aspect.ENTROPY, 40).add(Aspect.AIR, 40).add(Aspect.EARTH, 40).add(Aspect.FIRE, 40).add(Aspect.WATER, 40),
				" X ", "XOX", " X ",
				'X', new ItemStack(itemResource, 1, 4),
				'O', ItemApi.getItem("itemGolemDecoration", 0)); */

		recipeVoidcloth = ThaumcraftApi.addCrucibleRecipe(
				"VOIDCLOTH",
				new ItemStack(itemResource, 1, 5),
				ItemApi.getItem("itemResource", 7),
				new AspectList()
					.add(Aspect.ELDRITCH, 8)
					.add(Aspect.CLOTH, 8));

		recipeWardenHelm = ThaumcraftApi.addArcaneCraftingRecipe(
				"WARDENARMOR",
				new ItemStack(itemWardenHelm, 1),
				new AspectList()
						.add(Aspect.ORDER, 125)
						.add(Aspect.ENTROPY, 125)
						.add(Aspect.AIR, 125)
						.add(Aspect.EARTH, 125)
						.add(Aspect.FIRE, 125)
						.add(Aspect.WATER, 125),
				"XXX", "XOX",
				'X', new ItemStack(itemResource, 1, 2),
				'O', new ItemStack(itemResource, 1, 1));
		recipeWardenChest = ThaumcraftApi.addArcaneCraftingRecipe(
				"WARDENARMOR",
				new ItemStack(itemWardenChest, 1),
				new AspectList()
						.add(Aspect.ORDER, 125)
						.add(Aspect.ENTROPY, 125)
						.add(Aspect.AIR, 125)
						.add(Aspect.EARTH, 125)
						.add(Aspect.FIRE, 125)
						.add(Aspect.WATER, 125),
				"X X", "XOX", "XXX",
				'X', new ItemStack(itemResource, 1, 2),
				'O', new ItemStack(itemResource, 1, 1));
		recipeWardenLegs = ThaumcraftApi.addArcaneCraftingRecipe(
				"WARDENARMOR",
				new ItemStack(itemWardenLegs, 1),
				new AspectList()
					.add(Aspect.ORDER, 125)
					.add(Aspect.ENTROPY, 125)
					.add(Aspect.AIR, 125)
					.add(Aspect.EARTH, 125)
					.add(Aspect.FIRE, 125)
					.add(Aspect.WATER, 125),
				"XXX", "XOX", "X X",
				'X', new ItemStack(itemResource, 1, 2),
				'O', new ItemStack(itemResource, 1, 1));
		recipeWardenBoots = ThaumcraftApi.addArcaneCraftingRecipe(
				"WARDENARMOR",
				new ItemStack(itemWardenBoots, 1),
				new AspectList()
					.add(Aspect.ORDER, 125)
					.add(Aspect.ENTROPY, 125)
					.add(Aspect.AIR, 125)
					.add(Aspect.EARTH, 125)
					.add(Aspect.FIRE, 125)
					.add(Aspect.WATER, 125),
				"XOX", "X X",
				'X', new ItemStack(itemResource, 1, 2),
				'O', new ItemStack(itemResource, 1, 1));

		recipeWardenWeapon = ThaumcraftApi.addArcaneCraftingRecipe(
				"WARDENWEAPON",
				new ItemStack(itemWardenWeapon, 1),
				new AspectList()
					.add(Aspect.ORDER, 125)
					.add(Aspect.ENTROPY, 125)
					.add(Aspect.AIR, 125)
					.add(Aspect.EARTH, 125)
					.add(Aspect.FIRE, 125)
					.add(Aspect.WATER, 125),
				"X", "X", "O",
				'X', new ItemStack(itemResource, 1, 2),
				'O', new ItemStack(itemResource, 1, 1));

		recipeWaslieHammer = ThaumcraftApi.addArcaneCraftingRecipe(
				"WASLIEHAMMER",
				new ItemStack(itemWaslieHammer, 1),
				new AspectList()
					.add(Aspect.ORDER, 125)
					.add(Aspect.ENTROPY, 125)
					.add(Aspect.AIR, 125)
					.add(Aspect.EARTH, 125)
					.add(Aspect.FIRE, 125)
					.add(Aspect.WATER, 125),
				"XXX", "XOX", " I ",
				'X', new ItemStack(itemResource, 1, 1),
				'O', new ItemStack(itemResource, 1, 2),
				'I', ItemApi.getBlock("blockMagicalLog", 0));

		recipeFocusIllumination = ThaumcraftApi.addArcaneCraftingRecipe(
				"ILLUMINATION",
				new ItemStack(itemFocusIllumination, 1),
				new AspectList()
					.add(Aspect.AIR, 20)
					.add(Aspect.FIRE, 20),
				" X ", "XOX", " X ",
				'X', ItemApi.getItem("itemResource", 1),
				'O', ItemApi.getItem("itemFocusFire", 0));
	}

	public static void loadResearch() {
		ResearchCategories.registerCategory(
				"trevelations",
				new ResourceLocation("trevelations", "textures/items/wardenamulet.png"),
				new ResourceLocation("trevelations", "textures/gui/gui_researchbackthaumrev.png"));

		researchThaumRev = new ThaumRevResearchItem(
				"TREVELATIONS",
				"trevelations",
				new AspectList(),
				0, -5, 0,
				new ItemStack(itemWardenAmulet))
					.setRound()
					.setSpecial()
					.setAutoUnlock()
					.registerResearchItem();
		researchThaumRev.setPages(
				new ResearchPage("0"));

		researchWardens = new ThaumRevResearchItem(
				"WARDENS",
				"trevelations",
				new AspectList()
					.add(EXCUBITOR, 8)
					.add(Aspect.WEAPON, 6)
					.add(Aspect.ELDRITCH, 6),
				-8, -3, 3,
				new ItemStack(itemWardenAmulet))
					.setSpecial()
					.setParents("TREVELATIONS")
					.registerResearchItem();
		researchWardens.setPages(
				new ResearchPage("0"));

		researchExcubitura = new ThaumRevResearchItem(
				"EXCUBITURA",
				"trevelations",
				new AspectList()
					.add(EXCUBITOR, 4)
					.add(Aspect.PLANT, 4),
				-6, 0, 0,
				new ItemStack(itemResource, 0, 0))
					.setConcealed()
					.setItemTriggers(new ItemStack(itemResource, 0, 0))
					.setParents("WARDENS")
					.setRound()
					.registerResearchItem();
		researchExcubitura.setPages(
				new ResearchPage("0"));

		researchCrystal = new ThaumRevResearchItem(
				"CRYSTAL",
				"trevelations",
				new AspectList()
						.add(EXCUBITOR, 8)
						.add(Aspect.CRYSTAL, 8),
				-6, 2, 2,
				new ItemStack(itemResource, 0, 1))
					.setParents("EXCUBITURA")
					.setSecondary()
					.registerResearchItem();
		researchCrystal.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeCrystal));

		researchQuartz = new ThaumRevResearchItem(
				"QUARTZ",
				"trevelations",
				new AspectList()
						.add(EXCUBITOR, 6)
						.add(Aspect.CRYSTAL, 3)
						.add(Aspect.ENERGY, 3),
				-6, 4, 1,
				new ItemStack(itemResource, 0, 2))
					.setParents("LORE4")
					.registerResearchItem();
		researchQuartz.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeQuartz));

		researchWardencloth = new ThaumRevResearchItem(
				"WARDENCLOTH",
				"trevelations",
				new AspectList()
					.add(EXCUBITOR, 3)
					.add(Aspect.MAGIC, 2)
					.add(Aspect.CLOTH, 2)
					.add(Aspect.ARMOR, 1),
				-10, 0, 0,
				new ItemStack(itemResource, 0, 3))
					.setParents("WARDENS")
					.setParentsHidden("ENCHFABRIC")
					.registerResearchItem();
		researchWardencloth.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeWardencloth),
				new ResearchPage(recipeWardenclothChest),
				new ResearchPage(recipeWardenclothLegs),
				new ResearchPage(recipeWardenclothBoots));

		researchWardenclothWand = new ThaumRevResearchItem(
				"ROD_wardencloth",
				"trevelations",
				new AspectList()
					.add(EXCUBITOR, 6)
					.add(Aspect.MAGIC, 4)
					.add(Aspect.AIR, 1)
					.add(Aspect.EARTH, 1)
					.add(Aspect.ENTROPY, 1)
					.add(Aspect.FIRE, 1)
					.add(Aspect.ORDER, 1)
					.add(Aspect.WATER, 1),
				-10, 2, 2,
				new ItemStack(itemWandCore, 1, 0))
					.setParents("WARDENCLOTH")
					.setParentsHidden("ROD_silverwood")
					.registerResearchItem();
		researchWardenclothWand.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeWardenclothWand));

		researchWardenclothStaff = new ThaumRevResearchItem(
				"ROD_wardencloth_staff",
				"trevelations",
				new AspectList()
					.add(EXCUBITOR, 4)
					.add(Aspect.MAGIC, 4)
					.add(Aspect.AIR, 2)
					.add(Aspect.EARTH, 2)
					.add(Aspect.ENTROPY, 2)
					.add(Aspect.FIRE, 2)
					.add(Aspect.ORDER, 2)
					.add(Aspect.WATER, 2),
				-10, 4, 3,
				new ItemStack(itemWandCore, 1, 10))
					.setConcealed()
					.setSecondary()
					.setParents("ROD_wardencloth")
					.setParentsHidden("ROD_silverwood_staff")
					.registerResearchItem();
		researchWardenclothStaff.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeWardenclothStaff));

		researchWardenArmor = new ThaumRevResearchItem(
				"WARDENARMOR",
				"trevelations",
				new AspectList()
					.add(EXCUBITOR, 6)
					.add(Aspect.ARMOR, 6)
					.add(Aspect.AIR, 2)
					.add(Aspect.EARTH, 2)
					.add(Aspect.ENTROPY, 2)
					.add(Aspect.FIRE, 2)
					.add(Aspect.ORDER, 2)
					.add(Aspect.WATER, 2),
				-4, 1, 3,
				new ItemStack(itemWardenChest))
					.setConcealed()
					.setParents("WASLIEHAMMER")
					.setRound()
					.setSpecial()
					.registerResearchItem();
		researchWardenArmor.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeWardenHelm),
				new ResearchPage(recipeWardenChest),
				new ResearchPage(recipeWardenLegs),
				new ResearchPage(recipeWardenBoots));

		researchWardenWeapon = new ThaumRevResearchItem(
				"WARDENWEAPON",
				"trevelations",
				new AspectList()
					.add(EXCUBITOR, 6)
					.add(Aspect.WEAPON, 6)
					.add(Aspect.AIR, 2)
					.add(Aspect.EARTH, 2)
					.add(Aspect.ENTROPY, 2)
					.add(Aspect.FIRE, 2)
					.add(Aspect.ORDER, 2)
					.add(Aspect.WATER, 2),
				-4, 5, 3,
				new ItemStack(itemWardenWeapon))
					.setConcealed()
					.setParents("WASLIEHAMMER")
					.setRound()
					.setSpecial()
					.registerResearchItem();
		researchWardenWeapon.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeWardenWeapon));

		researchWaslieHammer = new ThaumRevResearchItem(
				"WASLIEHAMMER",
				"trevelations",
				new AspectList(),
				-4, 3, 3,
				new ItemStack(itemWaslieHammer))
					.setParents("CRYSTAL", "QUARTZ")
					.setParentsHidden("ROD_wardencloth")
					.setRound()
					.setSpecial()
					.registerResearchItem();
		researchWaslieHammer.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeWaslieHammer));

		researchIllumination = new ThaumRevResearchItem(
				"ILLUMINATION",
				"trevelations",
				new AspectList()
					.add(Aspect.AIR, 8)
					.add(Aspect.FIRE, 8),
				-12, 2, 2,
				new ItemStack(itemFocusIllumination))
					.setRound()
					.setConcealed()
					.setParents("ROD_wardencloth")
					.setParentsHidden("FOCUSFIRE")
					.registerResearchItem();
		researchIllumination.setPages(
				new ResearchPage("0"),
				new ResearchPage(recipeFocusIllumination));

		researchLore1 = new ThaumRevResearchItem(
				"LORE1",
				"trevelations",
				new AspectList()
					.add(EXCUBITOR, 6)
					.add(Aspect.MIND, 6),
				-8, -1, 1,
				new ItemStack(itemWardenAmulet))
					.setParents("WARDENS")
					.setSecondary()
					.setSpecial()
					.registerResearchItem();
		researchLore1.setPages(
				new ResearchPage("0"),
				new ResearchPage("1"),
				new ResearchPage("2"));

		researchLore2 = new ThaumRevResearchItem("LORE2", "trevelations",
				new AspectList()
					.add(EXCUBITOR, 6)
					.add(Aspect.MIND, 6),
				-8, 1, 1,
				new ItemStack(itemWardenAmulet))
					.setParents("LORE1")
					.setSecondary()
					.setSpecial()
					.registerResearchItem();
		researchLore2.setPages(
				new ResearchPage("0"),
				new ResearchPage("1"),
				new ResearchPage("2"),
				new ResearchPage("3"));

		researchLore3 = new ThaumRevResearchItem(
				"LORE3",
				"trevelations",
				new AspectList()
					.add(EXCUBITOR, 6)
					.add(Aspect.MIND, 6),
				-8, 3, 1,
				new ItemStack(itemWardenAmulet))
					.setParents("LORE2")
					.setSecondary()
					.setSpecial()
					.registerResearchItem();
		researchLore3.setPages(
				new ResearchPage("0"),
				new ResearchPage("1"),
				new ResearchPage("2"),
				new ResearchPage("3"),
				new ResearchPage("4"),
				new ResearchPage("5"));

		researchLore4 = new ThaumRevResearchItem(
				"LORE4", "trevelations",
				new AspectList()
						.add(EXCUBITOR, 6)
						.add(Aspect.MIND, 6),
				-8, 5, 1,
				new ItemStack(itemWardenAmulet))
					.setParents("LORE3")
					.setSecondary()
					.setSpecial()
					.registerResearchItem();
		researchLore4.setPages(
				new ResearchPage("0"),
				new ResearchPage("1"),
				new ResearchPage("2"),
				new ResearchPage("3"));

		researchCrimsonCult = new ThaumRevResearchItem(
				"CRIMSONCULT",
				"trevelations",
				new AspectList()
					.add(CRIMSON, 8)
					.add(Aspect.DARKNESS, 6)
					.add(Aspect.MAGIC, 6),
				0, -3, 3,
				ItemApi.getItem("itemEldritchObject", 1))
					.setSpecial()
					.setParents("TREVELATIONS")
					.registerResearchItem();
		researchCrimsonCult.setPages(
				new ResearchPage("0"));
		addWarpToResearch("CRIMSONCULT", 4);

		researchCrimsoncloth = new ThaumRevResearchItem(
				"CRIMSONCLOTH",
				"trevelations",
				new AspectList()
						.add(CRIMSON, 4)
						.add(Aspect.MAGIC, 2)
						.add(Aspect.CLOTH, 2),
				0, -1, 0,
				new ItemStack(itemResource, 0, 4))
					.setParents("CRIMSONCULT")
					.setParentsHidden("ENCHFABRIC")
					.registerResearchItem();
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
				new AspectList()
					.add(Aspect.ELDRITCH, 8)
					.add(Aspect.VOID, 6)
					.add(Aspect.DARKNESS, 6),
				8, -3, 3,
				ItemApi.getItem("itemEldritchObject", 0))
					.setSpecial()
					.setParents("TREVELATIONS")
					.registerResearchItem();
		researchEldritch.setPages(
				new ResearchPage("0"));
		addWarpToResearch("ELDRITCH", 4);

		researchVoidcloth = new ThaumRevResearchItem(
				"VOIDCLOTH",
				"trevelations",
				new AspectList().add(Aspect.ELDRITCH, 4).add(Aspect.MAGIC, 2).add(Aspect.CLOTH, 2),
				8, -1, 0,
				new ItemStack(itemResource, 0, 5))
					.setParents("ELDRITCH")
					.setParentsHidden("ENCHFABRIC")
					.registerResearchItem();
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
				ItemApi.getItem("itemHelmetCultistRobe", 0),
				new AspectList().add(CRIMSON, 1));
	}
}
