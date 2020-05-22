package thaumrev.common;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.research.ResearchItem;

public class ThaumRevLibrary {
	public static final String MOD_ID = "trevelations";

	public static CreativeTabs tabThaumRev;

	public static final Aspect EXCUBITOR = new Aspect("excubitor", 0x3CD4FC, new Aspect[] {Aspect.ELDRITCH, Aspect.DEATH}, new ResourceLocation("thaumrev", "textures/aspects/excubitor.png"), 771);

	public static ItemArmor.ArmorMaterial armorMaterialWarden;
	public static ItemSword.ToolMaterial toolMaterialWarden;

	public static Item itemResource;
	public static Item itemWardenAmulet;
	public static Item itemWardenWeapon;
	public static Item itemWardenBow;
	//public static Item itemFocusPurity; //Broken
	public static Item itemWardenHelm;
	public static Item itemWardenChest;
	public static Item itemWardenLegs;
	public static Item itemWardenBoots;
	//public static Item itemLoveRing; //Broken
	public static Item itemWaslieHammer;
	public static Item itemFocusIllumination;

	public static Block blockExcubitura;
	public static Block blockInfusedQuartzNormal;
	public static Block blockInfusedQuartzChiseled;
	public static Block blockInfusedQuartzPillar;
	public static Block blockInfusedQuartzSlab;
	public static Block blockInfusedQuartzStair;
	public static Block blockWitor;

	public static CrucibleRecipe recipeQuartz;
	public static CrucibleRecipe recipeCrystal;
	public static ShapedArcaneRecipe recipeWardenHelm;
	public static ShapedArcaneRecipe recipeWardenChest;
	public static ShapedArcaneRecipe recipeWardenLegs;
	public static ShapedArcaneRecipe recipeWardenBoots;
	public static ShapedArcaneRecipe recipeWardenWeapon;
	public static ShapedArcaneRecipe recipeWaslieHammer;
	public static ShapedArcaneRecipe recipeFocusIllumination;

	public static ResearchItem researchTWarden;
	public static ResearchItem researchExcubitura;
	public static ResearchItem researchQuartz;
	public static ResearchItem researchCrystal;
	public static ResearchItem researchWardenArmor;
	public static ResearchItem researchWardenWeapon;
	public static ResearchItem researchWaslieHammer;
	public static ResearchItem researchLore1;
	public static ResearchItem researchLore2;
	public static ResearchItem researchLore3;
	public static ResearchItem researchLore4;
	public static ResearchItem researchIllumination;
}
