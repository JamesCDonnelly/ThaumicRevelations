package thaumrev;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandRod;

import static thaumrev.ThaumRevLibrary.*;

public class ThaumRevRecipes {
    public static void registerRecipes() {
        loadShapedRecipes();
        loadCrucibleRecipes();
        loadArcaneRecipes();
        loadWandRecipes();
        loadFociRecipes();
    }

    private static void loadArcaneRecipes() {
        loadWardenRecipes();
        loadCrimsonRecipes();
        loadVoidRecipes();
    }

    private static void loadShapedRecipes() {
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
    }

    private static void loadCrucibleRecipes() {
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

        recipeCrimsoncloth = ThaumcraftApi.addCrucibleRecipe(
                "CRIMSONCLOTH",
                new ItemStack(itemResource, 1, 4),
                ItemApi.getItem("itemResource", 7),
                new AspectList()
                        .add(Aspect.WEAPON, 8)
                        .add(Aspect.CLOTH, 8));

        recipeVoidcloth = ThaumcraftApi.addCrucibleRecipe(
                "VOIDCLOTH",
                new ItemStack(itemResource, 1, 5),
                ItemApi.getItem("itemResource", 7),
                new AspectList()
                        .add(Aspect.ELDRITCH, 8)
                        .add(Aspect.CLOTH, 8));
    }

    private static void loadWardenRecipes() {
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

        recipeWardenBow = ThaumcraftApi.addArcaneCraftingRecipe(
                "WARDENWEAPON",
                new ItemStack(itemWardenBow, 1),
                new AspectList()
                        .add(Aspect.ORDER, 125)
                        .add(Aspect.ENTROPY, 125)
                        .add(Aspect.AIR, 125)
                        .add(Aspect.EARTH, 125)
                        .add(Aspect.FIRE, 125)
                        .add(Aspect.WATER, 125),
                " XO", "Y O", " XO",
                'X', new ItemStack(itemResource, 1, 2),
                'O', new ItemStack(Items.string, 1),
                'Y', new ItemStack(itemResource, 1, 1));

        recipeWaslieHammer = ThaumcraftApi.addArcaneCraftingRecipe(
                "WASLIEHAMMER",
                new ItemStack(itemWaslieHammer, 1),
                new AspectList()
                        .add(Aspect.ORDER, 50)
                        .add(Aspect.ENTROPY, 50)
                        .add(Aspect.AIR, 50)
                        .add(Aspect.EARTH, 50)
                        .add(Aspect.FIRE, 50)
                        .add(Aspect.WATER, 50),
                "XXX", "XOX", " I ",
                'X', new ItemStack(itemResource, 1, 1),
                'O', new ItemStack(itemResource, 1, 2),
                'I', new ItemStack(itemWandCore, 1, 0));

        recipeWardenAmulet = ThaumcraftApi.addArcaneCraftingRecipe(
                "WARDENAMULET",
                new ItemStack(itemWardenAmulet, 1, 50),
                new AspectList()
                        .add(Aspect.ORDER, 125)
                        .add(Aspect.ENTROPY, 125)
                        .add(Aspect.AIR, 125)
                        .add(Aspect.EARTH, 125)
                        .add(Aspect.FIRE, 125)
                        .add(Aspect.WATER, 125),
                "X X", " O ", " I ",
                'X', new ItemStack(itemResource, 1, 3),
                ')', new ItemStack(itemResource, 1, 1));
    }

    private static void loadCrimsonRecipes() {
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

        recipeCrimsonHat = ThaumcraftApi.addArcaneCraftingRecipe(
                "CRIMSONHAT",
                new ItemStack(itemCrimsonHat),
                new AspectList()
                        .add(Aspect.ORDER, 40)
                        .add(Aspect.ENTROPY, 40)
                        .add(Aspect.AIR, 40)
                        .add(Aspect.EARTH, 40)
                        .add(Aspect.FIRE, 40)
                        .add(Aspect.WATER, 40),
                " X ", "XOX", " X ",
                'X', new ItemStack(itemResource, 1, 4),
                'O', ItemApi.getItem("itemGolemDecoration", 0));

    }

    private static void loadVoidRecipes() {

    }

    private static void loadFociRecipes() {
        recipeFocusIllumination = ThaumcraftApi.addArcaneCraftingRecipe(
                "FOCUSILLUMINATION",
                new ItemStack(itemFocusIllumination, 1),
                new AspectList()
                        .add(Aspect.AIR, 20)
                        .add(Aspect.FIRE, 20),
                " X ", "XOX", " X ",
                'X', ItemApi.getItem("itemResource", 1),
                'O', ItemApi.getItem("itemFocusFire", 0));

        recipeFocusPurity = ThaumcraftApi.addArcaneCraftingRecipe(
                "FOCUSPURITY",
                new ItemStack(itemFocusPurity, 1),
                new AspectList()
                        .add(Aspect.ORDER, 25)
                        .add(Aspect.ENTROPY, 25)
                        .add(Aspect.AIR, 25)
                        .add(Aspect.EARTH, 25)
                        .add(Aspect.FIRE, 25)
                        .add(Aspect.WATER, 25),
                "YXY", "XOX", "YXY",
                'X', ItemApi.getItem("itemResource", 11),
                'Y', ItemApi.getItem("itemResource", 12),
                'O', ItemApi.getItem("itemFocusWarding", 0));
    }

    private static void loadWandRecipes() {
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
                        new ItemStack(itemResource, 1, 3)});

        recipeWardenclothStaff = ThaumcraftApi.addArcaneCraftingRecipe(
                "ROD_wardencloth_staff",
                new ItemStack(itemWandCore, 1, 10),
                new AspectList()
                        .add(Aspect.AIR, WandRod.rods.get("wardencloth_staff").getCraftCost() / 6)
                        .add(Aspect.EARTH, WandRod.rods.get("wardencloth_staff").getCraftCost() / 6)
                        .add(Aspect.ENTROPY, WandRod.rods.get("wardencloth_staff").getCraftCost() / 6)
                        .add(Aspect.FIRE, WandRod.rods.get("wardencloth_staff").getCraftCost() / 6)
                        .add(Aspect.ORDER, WandRod.rods.get("wardencloth_staff").getCraftCost() / 6)
                        .add(Aspect.WATER, WandRod.rods.get("wardencloth_staff").getCraftCost() / 6),
                "  O", " X ", "X  ",
                'X', new ItemStack(itemWandCore, 1, 0),
                'O', ItemApi.getItem("itemResource", 15));
    }
}