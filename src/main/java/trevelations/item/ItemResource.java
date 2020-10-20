package trevelations.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import trevelations.common.ThaumRevLibrary;

import java.util.List;

public class ItemResource extends Item {

	public static final String[] RESOURCE_ICON = {
			"wardenpetal",
			"wardencrystal",
			"wardenquartz",
			"wardenfabric",
			"crimsonfabric",
			"voidfabric"};
	public IIcon[] icons;

	public ItemResource() {
		super();
		setUnlocalizedName("itemResource");
		setCreativeTab(ThaumRevLibrary.tabThaumRev);
		setHasSubtypes(true);

		icons = new IIcon["itemResource".length()];
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + stack.getItemDamage();
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < RESOURCE_ICON.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		for (int i = 0; i < RESOURCE_ICON.length; i++) {
			icons[i] = register.registerIcon("trevelations:resources/" + RESOURCE_ICON[i]);
		}
	}
}
