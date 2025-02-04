package thaumrev.config;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import java.util.Enumeration;

import static thaumrev.ThaumicRevelations.log;
import static thaumrev.config.ConfigLibrary.*;

public final class ConfigAspects {
  public static void registerAspects() {
    CRIMSON = new Aspect(
      "rubus",
      0x990000,
      new Aspect[] {
        Aspect.DARKNESS,
        Aspect.MAGIC
      },
      new ResourceLocation(
        "thaumrev",
        "textures/aspects/rubus.png"
      ),
      771);

    EXCUBITOR = new Aspect(
      "excubitor",
      0x3CD4FC,
      new Aspect[] {
        Aspect.ELDRITCH,
        Aspect.DEATH
      },
      new ResourceLocation(
        "thaumrev",
        "textures/aspects/excubitor.png"
      ),
      771);
  }

  public static void registerItemAspects() {
    AspectList tcAspectList;

    ThaumcraftApi.registerObjectTag(
      new ItemStack(itemResource, 0, 0),
      new AspectList().add(EXCUBITOR, 4)
    );

    ThaumcraftApi.registerObjectTag(
      new ItemStack(itemResource, 0, 1),
      new AspectList()
        .add(Aspect.CRYSTAL, 4)
        .add(Aspect.CRYSTAL, 2)
        .add(EXCUBITOR, 2)
    );

    ThaumcraftApi.registerObjectTag(
      new ItemStack(itemResource, 0, 2),
      new AspectList()
        .add(Aspect.CRYSTAL, 4)
        .add(EXCUBITOR, 4)
    );

    ThaumcraftApi.registerObjectTag(
      new ItemStack(itemWardenHelm),
      new AspectList()
        .add(Aspect.ARMOR, 5)
        .add(EXCUBITOR, 10)
    );

    ThaumcraftApi.registerObjectTag(
      new ItemStack(itemWardenChest),
      new AspectList()
        .add(Aspect.ARMOR, 4)
        .add(EXCUBITOR, 30)
    );

    ThaumcraftApi.registerObjectTag(
      new ItemStack(itemWardenLegs),
      new AspectList()
        .add(Aspect.ARMOR, 4)
        .add(EXCUBITOR, 20)
    );

    ThaumcraftApi.registerObjectTag(
      new ItemStack(itemWardenBoots),
      new AspectList()
        .add(Aspect.ARMOR, 4)
        .add(EXCUBITOR, 10)
    );

    ThaumcraftApi.registerObjectTag(
      new ItemStack(itemWardenWeapon),
      new AspectList()
        .add(Aspect.WEAPON, 10)
        .add(EXCUBITOR, 20)
    );

    ThaumcraftApi.registerObjectTag(
      new ItemStack(itemWardenBow),
      new AspectList()
        .add(Aspect.WEAPON, 10)
        .add(EXCUBITOR, 20)
    );


    tcAspectList = ThaumcraftApiHelper.getObjectAspects(
      ItemApi.getItem("itemHelmetCultistRobe", 0)
    );

    ThaumcraftApi.registerObjectTag(
      ItemApi.getItem("itemHelmetCultistRobe", 0),
      tcAspectList
        .add(CRIMSON, tcAspectList.getAmount(Aspect.ELDRITCH))
        .remove(Aspect.ELDRITCH)
    );

    tcAspectList = ThaumcraftApiHelper.getObjectAspects(
      ItemApi.getItem("itemHelmetCultistPlate", 0)
    );

    ThaumcraftApi.registerObjectTag(
      ItemApi.getItem("itemHelmetCultistPlate", 0),
      tcAspectList
        .add(CRIMSON, tcAspectList.getAmount(Aspect.ELDRITCH))
        .remove(Aspect.ELDRITCH)
    );

    tcAspectList = ThaumcraftApiHelper.getObjectAspects(
      ItemApi.getItem("itemHelmetCultistLeaderPlate", 0)
    );

    ThaumcraftApi.registerObjectTag(
      ItemApi.getItem("itemHelmetCultistLeaderPlate", 0),
      tcAspectList
        .add(CRIMSON, tcAspectList.getAmount(Aspect.ELDRITCH))
        .remove(Aspect.ELDRITCH)
    );


    tcAspectList = ThaumcraftApiHelper.getObjectAspects(
      ItemApi.getItem("itemChestCultistRobe", 0)
    );

    ThaumcraftApi.registerObjectTag(
      ItemApi.getItem("itemChestCultistRobe", 0),
      tcAspectList
        .add(CRIMSON, tcAspectList.getAmount(Aspect.ELDRITCH))
        .remove(Aspect.ELDRITCH)
    );

    tcAspectList = ThaumcraftApiHelper.getObjectAspects(
      ItemApi.getItem("itemChestCultistPlate", 0)
    );

    ThaumcraftApi.registerObjectTag(
      ItemApi.getItem("itemChestCultistPlate", 0),
      tcAspectList
        .add(CRIMSON, tcAspectList.getAmount(Aspect.ELDRITCH))
        .remove(Aspect.ELDRITCH)
    );

    tcAspectList = ThaumcraftApiHelper.getObjectAspects(
      ItemApi.getItem("itemChestCultistLeaderPlate", 0)
    );

    ThaumcraftApi.registerObjectTag(
      ItemApi.getItem("itemChestCultistLeaderPlate", 0),
      tcAspectList
        .add(CRIMSON, tcAspectList.getAmount(Aspect.ELDRITCH))
        .remove(Aspect.ELDRITCH)
    );


    tcAspectList = ThaumcraftApiHelper.getObjectAspects(
      ItemApi.getItem("itemLegsCultistRobe", 0)
    );

    ThaumcraftApi.registerObjectTag(
      ItemApi.getItem("itemLegsCultistRobe", 0),
      tcAspectList
        .add(CRIMSON, tcAspectList.getAmount(Aspect.ELDRITCH))
        .remove(Aspect.ELDRITCH)
    );

    tcAspectList = ThaumcraftApiHelper.getObjectAspects(
      ItemApi.getItem("itemLegsCultistPlate", 0)
    );

    ThaumcraftApi.registerObjectTag(
      ItemApi.getItem("itemLegsCultistPlate", 0),
      tcAspectList
        .add(CRIMSON, tcAspectList.getAmount(Aspect.ELDRITCH))
        .remove(Aspect.ELDRITCH)
    );

    tcAspectList = ThaumcraftApiHelper.getObjectAspects(
      ItemApi.getItem("itemLegsCultistLeaderPlate", 0)
    );

    ThaumcraftApi.registerObjectTag(
      ItemApi.getItem("itemLegsCultistLeaderPlate", 0),
      tcAspectList
        .add(CRIMSON, tcAspectList.getAmount(Aspect.ELDRITCH))
        .remove(Aspect.ELDRITCH)
    );


    tcAspectList = ThaumcraftApiHelper.getObjectAspects(
      ItemApi.getItem("itemBootsCultist", 0)
    );

    ThaumcraftApi.registerObjectTag(
      ItemApi.getItem("itemBootsCultist", 0),
      tcAspectList
        .add(CRIMSON, tcAspectList.getAmount(Aspect.ELDRITCH))
        .remove(Aspect.ELDRITCH)
    );
  }
}
