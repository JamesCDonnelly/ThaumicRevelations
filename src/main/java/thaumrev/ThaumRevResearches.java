package thaumrev;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;
import thaumrev.util.ThaumRevResearchItem;

import static thaumcraft.api.ThaumcraftApi.addWarpToResearch;
import static thaumrev.ThaumRevLibrary.*;

public class ThaumRevResearches {

  public static void registerResearches() {
    ResearchCategories.registerCategory(
      "thaumrev",
      new ResourceLocation("thaumrev", "textures/items/wardenamulet.png"),
      new ResourceLocation("thaumrev", "textures/gui/gui_researchbackthaumrev.png")
    );

    loadResourceResearches();
    loadWardenResearches();
    loadWandCoresResearches();
    loadWandFociResearches();
    loadLoreResearches();
    loadCrimsonResearches();
  }

  private static void loadResourceResearches() {
    researchCrystal = new ThaumRevResearchItem(
      "CRYSTAL",
      "thaumrev",
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
      "thaumrev",
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
      "thaumrev",
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

    if (!ThaumRevConfig.isThaumicDyesLoaded) {
      researchCrimsoncloth = new ThaumRevResearchItem(
        "CRIMSONCLOTH",
        "thaumrev",
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
    }

    researchVoidcloth = new ThaumRevResearchItem(
      "VOIDCLOTH",
      "thaumrev",
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

  private static void loadWandCoresResearches() {
    researchWardenclothWand = new ThaumRevResearchItem(
      "ROD_wardencloth",
      "thaumrev",
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
      "thaumrev",
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
  }

  private static void loadWandFociResearches() {
    researchFocusIllumination = new ThaumRevResearchItem(
      "FOCUSILLUMINATION",
      "thaumrev",
      new AspectList()
        .add(Aspect.LIGHT, 8)
        .add(Aspect.AIR, 8)
        .add(Aspect.FIRE, 8),
      -12, 1, 2,
      new ItemStack(itemFocusIllumination))
      .setRound()
      .setConcealed()
      .setParents("ROD_wardencloth")
      .setParentsHidden("FOCUSFIRE")
      .registerResearchItem();
    researchFocusIllumination.setPages(
      new ResearchPage("0"),
      new ResearchPage(recipeFocusIllumination));

    researchFocusPurity = new ThaumRevResearchItem(
      "FOCUSPURITY",
      "thaumrev",
      new AspectList()
        .add(EXCUBITOR, 6)
        .add(Aspect.AIR, 3)
        .add(Aspect.EARTH, 3)
        .add(Aspect.ENTROPY, 3)
        .add(Aspect.FIRE, 3)
        .add(Aspect.ORDER, 3)
        .add(Aspect.WATER, 3),
      -12, 3, 3,
      new ItemStack(itemFocusPurity))
      .setRound()
      .setConcealed()
      .setParents("ROD_wardencloth")
      .setParentsHidden("FOCUSWARDING")
      .registerResearchItem();
    researchFocusPurity.setPages(
      new ResearchPage("0"),
      new ResearchPage(recipeFocusPurity));
  }

  private static void loadLoreResearches() {
    researchThaumRev = new ThaumRevResearchItem(
      "THAUMREV",
      "thaumrev",
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
      "thaumrev",
      new AspectList()
        .add(EXCUBITOR, 8)
        .add(Aspect.WEAPON, 6)
        .add(Aspect.ELDRITCH, 6),
      -8, -3, 3,
      new ItemStack(itemWardenAmulet))
      .setSpecial()
      .setParents("THAUMREV")
      .registerResearchItem();
    researchWardens.setPages(
      new ResearchPage("0"));

    researchLore1 = new ThaumRevResearchItem(
      "LORE1",
      "thaumrev",
      new AspectList()
        .add(EXCUBITOR, 6)
        .add(Aspect.MIND, 6),
      -8, -1, 1,
      new ItemStack(itemWardenAmulet))
      .setParents("WARDENS")
      .setSecondary()
      .registerResearchItem();
    researchLore1.setPages(
      new ResearchPage("0"),
      new ResearchPage("1"),
      new ResearchPage("2")
    );

    researchLore2 = new ThaumRevResearchItem(
      "LORE2",
      "thaumrev",
      new AspectList()
        .add(EXCUBITOR, 6)
        .add(Aspect.MIND, 6),
      -8, 1, 1,
      new ItemStack(itemWardenAmulet))
      .setParents("LORE1")
      .setSecondary()
      .registerResearchItem();
    researchLore2.setPages(
      new ResearchPage("0"),
      new ResearchPage("1"),
      new ResearchPage("2"),
      new ResearchPage("3")
    );

    researchLore3 = new ThaumRevResearchItem(
      "LORE3",
      "thaumrev",
      new AspectList()
        .add(EXCUBITOR, 6)
        .add(Aspect.MIND, 6),
      -8, 3, 1,
      new ItemStack(itemWardenAmulet))
      .setParents("LORE2")
      .setSecondary()
      .registerResearchItem();
    researchLore3.setPages(
      new ResearchPage("0"),
      new ResearchPage("1"),
      new ResearchPage("2"),
      new ResearchPage("3"),
      new ResearchPage("4"),
      new ResearchPage("5")
    );

    researchLore4 = new ThaumRevResearchItem(
      "LORE4", "thaumrev",
      new AspectList()
        .add(EXCUBITOR, 6)
        .add(Aspect.MIND, 6),
      -8, 5, 1,
      new ItemStack(itemWardenAmulet))
      .setParents("LORE3")
      .setSecondary()
      .registerResearchItem();
    researchLore4.setPages(
      new ResearchPage("0"),
      new ResearchPage("1"),
      new ResearchPage("2"),
      new ResearchPage("3")
    );

    researchCrimsonCult = new ThaumRevResearchItem(
      "CRIMSONCULT",
      "thaumrev",
      new AspectList()
        .add(CRIMSON, 8)
        .add(Aspect.DARKNESS, 6)
        .add(Aspect.MAGIC, 6),
      0, -3, 3,
      ItemApi.getItem("itemEldritchObject", 1))
      .setSpecial()
      .setParents("THAUMREV")
      .setItemTriggers(ItemApi.getItem("itemEldritchObject", 1))
      .registerResearchItem();
    researchCrimsonCult.setPages(
      new ResearchPage("0")
    );
    addWarpToResearch("CRIMSONCULT", 4);

    researchEldritch = new ThaumRevResearchItem(
      "ELDRITCH",
      "thaumrev",
      new AspectList()
        .add(Aspect.ELDRITCH, 8)
        .add(Aspect.VOID, 6)
        .add(Aspect.DARKNESS, 6),
      8, -3, 3,
      ItemApi.getItem("itemEldritchObject", 0))
      .setSpecial()
      .setParents("THAUMREV")
      .setItemTriggers(ItemApi.getItem("itemEldritchObject", 0))
      .registerResearchItem();
    researchEldritch.setPages(
      new ResearchPage("0")
    );
    addWarpToResearch("ELDRITCH", 4);
  }

  private static void loadWardenResearches() {
    researchExcubitura = new ThaumRevResearchItem(
      "EXCUBITURA",
      "thaumrev",
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
      new ResearchPage("0")
    );

    researchWardenArmor = new ThaumRevResearchItem(
      "WARDENARMOR",
      "thaumrev",
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
      new ResearchPage(recipeWardenBoots)
    );

    researchWardenWeapon = new ThaumRevResearchItem(
      "WARDENWEAPON",
      "thaumrev",
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

    researchWardenBow = new ThaumRevResearchItem(
      "WARDENBOW",
      "thaumrev",
      new AspectList()
        .add(EXCUBITOR, 6)
        .add(Aspect.WEAPON, 6)
        .add(Aspect.AIR, 2)
        .add(Aspect.EARTH, 2)
        .add(Aspect.ENTROPY, 2)
        .add(Aspect.FIRE, 2)
        .add(Aspect.ORDER, 2)
        .add(Aspect.WATER, 2),
      -2, 3, 3,
      new ItemStack(itemWardenBow))
      .setConcealed()
      .setParents("WASLIEHAMMER")
      .setRound()
      .setSpecial()
      .registerResearchItem();
    researchWardenBow.setPages(
      new ResearchPage("0"),
      new ResearchPage(recipeWardenBow));

    researchWaslieHammer = new ThaumRevResearchItem(
      "WASLIEHAMMER",
      "thaumrev",
      new AspectList()
        .add(EXCUBITOR, 12)
        .add(Aspect.TOOL, 6)
        .add(Aspect.WEAPON, 3)
        .add(Aspect.ARMOR, 3),
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

    researchWardenAmulet = new ThaumRevResearchItem(
      "WARDENAMULET",
      "thaumrev",
      new AspectList()
        .add(EXCUBITOR, 12)
        .add(Aspect.ARMOR, 6)
        .add(Aspect.MAGIC, 6),
      -8, 7, 2,
      new ItemStack(itemWardenAmulet))
      .setParents("LORE4")
      .setRound()
      .setSpecial()
      .registerResearchItem();
    researchWardenAmulet.setPages(
      new ResearchPage("0"),
      new ResearchPage(recipeWardenAmulet));
  }

  private static void loadCrimsonResearches() {
    researchCrimsonHat = new ThaumRevResearchItem(
      "CRIMSONHAT",
      "thaumrev",
      new AspectList()
        .add(CRIMSON, 6)
        .add(Aspect.ARMOR, 6)
        .add(Aspect.MAGIC, 6),
      2, -3, 3,
      new ItemStack(itemCrimsonHat)
    )
      .setParents("CRIMSONCULT")
      .setParentsHidden("TINYHAT")
      .registerResearchItem();

    researchCrimsonHat.setPages(
      new ResearchPage("0"),
      new ResearchPage(recipeCrimsonHat)
    );
    addWarpToResearch("CRIMSONCULT", 1);
  }
}
