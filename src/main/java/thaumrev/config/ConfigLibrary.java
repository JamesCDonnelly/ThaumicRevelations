package thaumrev.config;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemSword;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.wands.StaffRod;
import thaumcraft.api.wands.WandRod;

public class ConfigLibrary {
  public static final String MOD_ID = "thaumrev";
  public static final String VERSION = "1.2.2";

  public static final long ATTRIBUTE_MODIFIER_UUID = 213742069L;

  public static CreativeTabs tabThaumRev;

  public static Aspect CRIMSON;
  public static Aspect EXCUBITOR;

  public static ItemArmor.ArmorMaterial armorMaterialWarden;
  public static ItemArmor.ArmorMaterial armorMaterialWardencloth;
  public static ItemArmor.ArmorMaterial armorMaterialCrimsoncloth;
  public static ItemArmor.ArmorMaterial armorMaterialFortressThauminite;

  public static ItemSword.ToolMaterial toolMaterialWarden;

  public static WandRod WAND_ROD_WARDENCLOTH;
  public static WandRod WAND_ROD_VOIDWOOD;
  public static WandRod WAND_ROD_CRIMSONCULT;

  public static StaffRod STAFF_ROD_WARDENCLOTH;
  public static StaffRod STAFF_ROD_VOIDWOOD;
  public static StaffRod STAFF_ROD_CRIMSONCULT;

  public static Item itemWandCore;

  public static Item itemResource;
  public static Item itemWardenWeapon;
  public static Item itemWardenBow;
  public static Item itemFocusPurity;

  public static Item itemCrimsonHat;

  public static Item itemWardenclothChest;
  public static Item itemWardenclothLegs;
  public static Item itemWardenclothBoots;

  public static Item itemWardenHelm;
  public static Item itemWardenChest;
  public static Item itemWardenLegs;
  public static Item itemWardenBoots;

  public static Item itemWardenAmulet;
  public static Item itemLoveRing;
  public static Item itemWaslieHammer;
  public static Item itemFocusIllumination;

  public static Item itemThauminiteFortressHelm;
  public static Item itemThauminiteFortressChest;
  public static Item itemThauminiteFortressLegs;

  public static Block blockExcubitura;
  public static ItemSeeds itemExcubituraSeeds;

  public static Block blockInfusedQuartzNormal;
  public static Block blockInfusedQuartzChiseled;
  public static Block blockInfusedQuartzPillar;
  public static Block blockSlabInfusedQuartz;
  public static Block blockSlabDoubleInfusedQuartz;
  public static Block blockInfusedQuartzStair;
  public static Block blockWitor;
  public static Block blockKnowledgeReprocessor;

  public static CrucibleRecipe recipeQuartz;
  public static CrucibleRecipe recipeCrystal;
  public static CrucibleRecipe recipeWardencloth;
  public static CrucibleRecipe recipeCrimsoncloth;
  public static CrucibleRecipe recipeVoidcloth;

  public static InfusionRecipe recipeWardenclothWand;
  public static ShapedArcaneRecipe recipeVoidwoodWand;
  public static ShapedArcaneRecipe recipeRedCrystalWand;

  public static ShapedArcaneRecipe recipeWardenclothStaff;
  public static ShapedArcaneRecipe recipeVoidwoodStaff;
  public static ShapedArcaneRecipe recipeCrimsonCultStaff;

  public static ShapedArcaneRecipe recipeHelmetCultistRobe;
  public static ShapedArcaneRecipe recipeChestCultistRobe;
  public static ShapedArcaneRecipe recipeLegsCultistRobe;
  public static ShapedArcaneRecipe recipeBootsCultist;
  public static ShapedArcaneRecipe recipeCrimsonHat;

  public static ShapedArcaneRecipe recipeWardenclothChest;
  public static ShapedArcaneRecipe recipeWardenclothLegs;
  public static ShapedArcaneRecipe recipeWardenclothBoots;

  public static ShapedArcaneRecipe recipeWardenHelm;
  public static ShapedArcaneRecipe recipeWardenChest;
  public static ShapedArcaneRecipe recipeWardenLegs;
  public static ShapedArcaneRecipe recipeWardenBoots;

  public static ShapedArcaneRecipe recipeWardenWeapon;
  public static ShapedArcaneRecipe recipeWardenBow;
  public static ShapedArcaneRecipe recipeWaslieHammer;

  public static ShapedArcaneRecipe recipeFocusIllumination;
  public static ShapedArcaneRecipe recipeFocusPurity;

  public static ShapedArcaneRecipe recipeWardenAmulet;

  public static ResearchItem researchThaumRev;
  public static ResearchItem researchWardens;
  public static ResearchItem researchFocusIllumination;
  public static ResearchItem researchFocusPurity;

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
  public static ResearchItem researchWardenAmulet;

  public static ResearchItem researchWaslieHammer;
  public static ResearchItem researchWardenArmor;
  public static ResearchItem researchWardenWeapon;
  public static ResearchItem researchWardenBow;

  public static ResearchItem researchCrimsonCult;
  public static ResearchItem researchCrimsoncloth;
  public static ResearchItem researchCrimsonHat;

  public static ResearchItem researchEldritch;
  public static ResearchItem researchVoidcloth;
  public static ResearchItem researchVoidwoodWand;
  public static ResearchItem researchVoidwoodStaff;
}
