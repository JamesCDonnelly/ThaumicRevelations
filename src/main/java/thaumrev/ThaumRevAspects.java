package thaumrev;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import static thaumrev.ThaumRevLibrary.*;

public class ThaumRevAspects {
	public static void registerAspects() {
		CRIMSON = new Aspect(
				"rubus",
				0x990000,
				new Aspect[] {
						Aspect.DARKNESS,
						Aspect.MAGIC},
				new ResourceLocation(
						"thaumrev",
						"textures/aspects/rubus.png"),
				771);

		EXCUBITOR = new Aspect(
				"excubitor",
				0x3CD4FC,
				new Aspect[] {
						Aspect.ELDRITCH,
						Aspect.DEATH},
				new ResourceLocation(
						"thaumrev",
						"textures/aspects/excubitor.png"),
				771);
	}

	public static void registerItemAspects() {
		ThaumcraftApi.registerObjectTag(
				new ItemStack(itemResource, 0, 0),
				new AspectList().add(EXCUBITOR, 1));

		ThaumcraftApi.registerObjectTag(
				new ItemStack(itemResource, 0, 1),
				new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.CRYSTAL, 2).add(EXCUBITOR, 2));

		ThaumcraftApi.registerObjectTag(
				new ItemStack(itemResource, 0, 2),
				new AspectList().add(Aspect.CRYSTAL, 4).add(EXCUBITOR, 4));

		ThaumcraftApi.registerObjectTag(
				new ItemStack(itemWardenHelm),
				new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ARMOR, 5).add(EXCUBITOR, 10));

		ThaumcraftApi.registerObjectTag(
				new ItemStack(itemWardenChest),
				new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 15).add(EXCUBITOR, 30));

		ThaumcraftApi.registerObjectTag(
				new ItemStack(itemWardenLegs),
				new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 10).add(EXCUBITOR, 20));

		ThaumcraftApi.registerObjectTag(
				new ItemStack(itemWardenBoots),
				new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 5).add(EXCUBITOR, 10));

		ThaumcraftApi.registerObjectTag(
				new ItemStack(itemWardenWeapon),
				new AspectList().add(Aspect.WEAPON, 4).add(Aspect.WEAPON, 10).add(EXCUBITOR, 20));

		ThaumcraftApi.registerObjectTag(
				ItemApi.getItem("itemHelmetCultistRobe", 0),
				new AspectList().add(CRIMSON, 1));
	}
}
