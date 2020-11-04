package thaumrev.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import thaumrev.ThaumRevLibrary;

import java.util.Collections;
import java.util.List;

public class TabThaumRev extends CreativeTabs {

	private final ItemSorter itemSorter = new ItemSorter();

	public TabThaumRev(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return ThaumRevLibrary.itemWardenAmulet;
	}

	@Override
	public void displayAllReleventItems(List items) {
		super.displayAllReleventItems(items);

		Collections.sort(items, itemSorter);
	}
}
