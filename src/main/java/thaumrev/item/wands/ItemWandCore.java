package thaumrev.item.wands;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.ItemWandRod;
import thaumrev.ThaumRevLibrary;

import java.util.List;

public class ItemWandCore extends ItemWandRod {

	public IIcon[] iconWand = new IIcon[3];
	public IIcon[] iconStaff = new IIcon[3];

	public ItemWandCore() {
		setUnlocalizedName("itemWandCore");
		setMaxStackSize(64);
		setHasSubtypes(true);
		setCreativeTab(ThaumRevLibrary.tabThaumRev);
	}


	/** Overrides - String **/
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + stack.getMetadata();
	}


	/** Client-side **/
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		iconWand[0] = register.registerIcon("thaumrev:wands/wand_rod_wardencloth");
		iconWand[1] = register.registerIcon("thaumrev:wands/wand_rod_voidwood");
		iconWand[2] = register.registerIcon("thaumrev:wands/wand_rod_crimsoncrystal");
		iconStaff[0] = register.registerIcon("thaumrev:wands/staff_rod_wardencloth");
		iconStaff[1] = register.registerIcon("thaumrev:wands/staff_rod_voidwood");
		iconStaff[2] = register.registerIcon("thaumrev:wands/staff_rod_crimsoncult");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 6; i++) {
			list.add(new ItemStack(this, 1, (i < 3 ? i : i + 7)));
		}

		ItemStack wand;
		ItemWandCasting wandCasting;

		/** Wands **/
		wand = new ItemStack(ConfigItems.itemWandCasting, 1, 400);
		wandCasting = (ItemWandCasting) wand.getItem();

		wandCasting.setCap(wand, WandCap.caps.get("thaumium"));
		wandCasting.setRod(wand, WandRod.rods.get("wardencloth"));
		wandCasting.storeAllVis(
      wand, new AspectList()
        .add(Aspect.FIRE, 15000)
        .add(Aspect.WATER, 15000)
        .add(Aspect.EARTH, 15000)
        .add(Aspect.AIR, 15000)
        .add(Aspect.ORDER, 15000)
        .add(Aspect.ENTROPY, 15000));
		list.add(wand);

		wand = new ItemStack(ConfigItems.itemWandCasting, 1, 401);
		wandCasting = (ItemWandCasting) wand.getItem();

		wandCasting.setCap(wand, WandCap.caps.get("void"));
		wandCasting.setRod(wand, WandRod.rods.get("voidwood"));
		wandCasting.storeAllVis(
				wand, new AspectList()
						.add(Aspect.FIRE, 20000)
						.add(Aspect.WATER, 20000)
						.add(Aspect.EARTH, 20000)
						.add(Aspect.AIR, 20000)
						.add(Aspect.ORDER, 20000)
						.add(Aspect.ENTROPY, 20000));
		list.add(wand);

		wand = new ItemStack(ConfigItems.itemWandCasting, 1, 402);
		wandCasting = (ItemWandCasting) wand.getItem();

		wandCasting.setCap(wand, WandCap.caps.get("void"));
		wandCasting.setRod(wand, WandRod.rods.get("crimsoncult"));
		wandCasting.storeAllVis(
      wand, new AspectList()
        .add(Aspect.FIRE, 10000)
        .add(Aspect.WATER, 10000)
        .add(Aspect.EARTH, 10000)
        .add(Aspect.AIR, 10000)
        .add(Aspect.ORDER, 10000)
        .add(Aspect.ENTROPY, 10000));
		list.add(wand);


		/** Staffs **/
		wand = new ItemStack(ConfigItems.itemWandCasting, 1, 500);
		wandCasting = (ItemWandCasting) wand.getItem();

		wandCasting.setCap(wand, WandCap.caps.get("thaumium"));
		wandCasting.setRod(wand, WandRod.rods.get("wardencloth_staff"));
		wandCasting.storeAllVis(
				wand, new AspectList()
						.add(Aspect.FIRE, 37500)
						.add(Aspect.WATER, 37500)
						.add(Aspect.EARTH, 37500)
						.add(Aspect.AIR, 37500)
						.add(Aspect.ORDER, 37500)
						.add(Aspect.ENTROPY, 37500));
		list.add(wand);

		wand = new ItemStack(ConfigItems.itemWandCasting, 1, 501);
		wandCasting = (ItemWandCasting) wand.getItem();

		wandCasting.setCap(wand, WandCap.caps.get("void"));
		wandCasting.setRod(wand, WandRod.rods.get("voidwood_staff"));
		wandCasting.storeAllVis(
      wand, new AspectList()
        .add(Aspect.FIRE, 50000)
        .add(Aspect.WATER, 50000)
        .add(Aspect.EARTH, 50000)
        .add(Aspect.AIR, 50000)
        .add(Aspect.ORDER, 50000)
        .add(Aspect.ENTROPY, 50000));
		list.add(wand);

		wand = new ItemStack(ConfigItems.itemWandCasting, 1, 502);
		wandCasting = (ItemWandCasting) wand.getItem();

		wandCasting.setCap(wand, WandCap.caps.get("void"));
		wandCasting.setRod(wand, WandRod.rods.get("crimsoncult_staff"));
		wandCasting.storeAllVis(
      wand, new AspectList()
        .add(Aspect.FIRE, 100000)
        .add(Aspect.WATER, 100000)
        .add(Aspect.EARTH, 100000)
        .add(Aspect.AIR, 100000)
        .add(Aspect.ORDER, 100000)
        .add(Aspect.ENTROPY, 100000));
		list.add(wand);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return meta < 10 ? this.iconWand[meta] : this.iconStaff[meta - 10];
	}

}