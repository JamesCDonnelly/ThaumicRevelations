package trevelations.common;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.research.ResearchItem;

public class ThaumRevLibrary {
	public static final String MOD_ID = "trevelations";

	public static CreativeTabs tabThaumRev;

	public static final Aspect EXCUBITOR = new Aspect(
			"excubitor",
			0x3CD4FC,
			new Aspect[] {
					Aspect.ELDRITCH,
					Aspect.DEATH},
			new ResourceLocation(
                    "trevelations",
					"textures/aspects/excubitor.png"),
			771);

	public static final Aspect RED = new Aspect(
			"rubus",
			0x3CD4FC,
			new Aspect[] {
					Aspect.ELDRITCH,
					Aspect.DEATH},
			new ResourceLocation(
                    "trevelations",
					"textures/aspects/rubus.png"),
			771);

	public static ItemArmor.ArmorMaterial armorMaterialWarden;
	public static ItemSword.ToolMaterial toolMaterialWarden;

	public static ItemArmor.ArmorMaterial armorMaterialWardencloth;

	public static ItemArmor.ArmorMaterial armorMaterialCrimsoncloth;

	public static Item itemResource;
	public static Item itemCrimsonHat;
	public static Item itemWardenAmulet;
	public static Item itemWardenWeapon;
	public static Item itemWardenBow;
	public static Item itemFocusPurity; //TODO: make it working

	public static Item itemWardenclothHelm;
	public static Item itemWardenclothChest;
	public static Item itemWardenclothLegs;
	public static Item itemWardenclothBoots;

	public static Item itemWardenHelm;
	public static Item itemWardenChest;
	public static Item itemWardenLegs;
	public static Item itemWardenBoots;

	public static Item itemLoveRing;
	public static Item itemWaslieHammer;
	public static Item itemFocusIllumination;

	public static Block blockExcubitura;
	public static ItemSeeds itemExcubituraSeeds;

	public static Block blockInfusedQuartzNormal;
	public static Block blockInfusedQuartzChiseled;
	public static Block blockInfusedQuartzPillar;
	public static Block blockInfusedQuartzSlab;
	public static Block blockInfusedQuartzStair;
	public static Block blockWitor;

	public static CrucibleRecipe recipeQuartz;
	public static CrucibleRecipe recipeCrystal;
	public static CrucibleRecipe recipeWardencloth;
	public static CrucibleRecipe recipeCrimsoncloth;
	public static CrucibleRecipe recipeVoidcloth;

	public static ShapedArcaneRecipe recipeHelmetCultistRobe;
	public static ShapedArcaneRecipe recipeChestCultistRobe;
	public static ShapedArcaneRecipe recipeLegsCultistRobe;
	public static ShapedArcaneRecipe recipeBootsCultist;
	public static ShapedArcaneRecipe recipeCrimsonHat;

	public static ShapedArcaneRecipe recipeWardenHelm;
	public static ShapedArcaneRecipe recipeWardenChest;
	public static ShapedArcaneRecipe recipeWardenLegs;
	public static ShapedArcaneRecipe recipeWardenBoots;
	public static ShapedArcaneRecipe recipeWardenWeapon;
	public static ShapedArcaneRecipe recipeWaslieHammer;
	public static ShapedArcaneRecipe recipeFocusIllumination;

	public static ResearchItem researchThaumRev;

	public static ResearchItem researchWardens;

	public static ResearchItem researchIllumination;

	public static ResearchItem researchWardencloth;
	public static ResearchItem researchWardenclothWand;
	public static ResearchItem researchWardenclothStaff;

	public static ResearchItem researchExcubitura;
	public static ResearchItem researchCrystal;

	public static ResearchItem researchLore1;
	public static ResearchItem researchLore2;
	public static ResearchItem researchLore3;
	public static ResearchItem researchLore4;
	public static ResearchItem researchQuartz;

	public static ResearchItem researchWaslieHammer;
	public static ResearchItem researchWardenArmor;
	public static ResearchItem researchWardenWeapon;

	public static ResearchItem researchCrimsonCult;
	public static ResearchItem researchCrimsoncloth;
	public static ResearchItem researchCrimsonHat;

	public static ResearchItem researchEldritch;
	public static ResearchItem researchVoidcloth;

}
