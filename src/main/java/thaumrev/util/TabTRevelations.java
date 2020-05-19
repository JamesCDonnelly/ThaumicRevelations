package thaumrev.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import thaumrev.common.ThaumRevLibrary;

public class TabTRevelations extends CreativeTabs {

	public TabTRevelations(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return ThaumRevLibrary.itemWardenAmulet;
	}

}
