package thaumrev.lib;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import thaumrev.config.ConfigLibrary;
import thaumrev.lib.utils.ItemSorter;

import java.util.Collections;
import java.util.List;

public final class CreativeTabThaumRev extends CreativeTabs {
	private final ItemSorter itemSorter = new ItemSorter();

	public CreativeTabThaumRev(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return ConfigLibrary.itemWardenAmulet;
	}

	@Override
	public void displayAllReleventItems(List items) {
		super.displayAllReleventItems(items);

		Collections.sort(items, itemSorter);
	}
}
