package thaumrev.lib.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;

public class ItemSorter implements Comparator<ItemStack> {

  static String[] priority = {
    "block",
    "seeds",
    "resource",
    "item",
    "bauble",
    "armor",
    "weapon",
    "wand",
    "focus"
  };

  @Override
  public int compare(@NotNull ItemStack stack1, @NotNull ItemStack stack2) {
    Item item1 = stack1.getItem();
    Item item2 = stack2.getItem();

    final int pos1 = getPos(item1);
    final int pos2 = getPos(item2);

    if (pos1 != pos2) {
      return pos1 > pos2 ? 1 : -1;
    } else {
      String displayName1 = stack1.getDisplayName();
      String displayName2 = stack2.getDisplayName();

      return displayName1.compareToIgnoreCase(displayName2);
    }
  }

  private int getPos(@NotNull Item item) {
    int pos;

    if (item.getClass().getName().toLowerCase().contains("block")) {
      pos = Arrays.asList(priority).indexOf("block");
    } else if (item.getClass().getName().toLowerCase().contains("seeds")) {
      pos = Arrays.asList(priority).indexOf("seeds");
    } else if (item.getClass().getName().toLowerCase().contains("resource")) {
      pos = Arrays.asList(priority).indexOf("resource");
    } else if (item.getClass().getName().toLowerCase().contains("ring") ||
      item.getClass().getName().toLowerCase().contains("amulet")) {
      pos = Arrays.asList(priority).indexOf("bauble");
    } else if (item.getClass().getName().toLowerCase().contains("armor")) {
      pos = Arrays.asList(priority).indexOf("armor");
    } else if (item.getClass().getName().toLowerCase().contains("weapon") ||
      item.getClass().getName().toLowerCase().contains("bow")) {
      pos = Arrays.asList(priority).indexOf("weapon");
    } else if (item.getClass().getName().toLowerCase().contains("wand")) {
      pos = Arrays.asList(priority).indexOf("wand");
    } else if (item.getClass().getName().toLowerCase().contains("focus")) {
      pos = Arrays.asList(priority).indexOf("focus");
    } else {
      pos = Arrays.asList(priority).indexOf("item");
    }

    return pos;
  }
}