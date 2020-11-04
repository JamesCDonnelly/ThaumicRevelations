package thaumrev.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
    public int compare(ItemStack stack1, ItemStack stack2) {
        Item item1 = stack1.getItem();
        Item item2 = stack2.getItem();

        int pos1;
        int pos2;

        if (item1.getClass().getName().toLowerCase().contains("block")) {
            pos1 = Arrays.asList(priority).indexOf("block");
        } else if (item1.getClass().getName().toLowerCase().contains("seeds")) {
            pos1 = Arrays.asList(priority).indexOf("seeds");
        } else if (item1.getClass().getName().toLowerCase().contains("resource")) {
            pos1 = Arrays.asList(priority).indexOf("resource");
        } else if (item1.getClass().getName().toLowerCase().contains("ring") ||
                item1.getClass().getName().toLowerCase().contains("amulet")) {
            pos1 = Arrays.asList(priority).indexOf("bauble");
        } else if (item1.getClass().getName().toLowerCase().contains("armor")) {
            pos1 = Arrays.asList(priority).indexOf("armor");
        } else if (item1.getClass().getName().toLowerCase().contains("weapon") ||
                item1.getClass().getName().toLowerCase().contains("bow")) {
            pos1 = Arrays.asList(priority).indexOf("weapon");
        } else if (item1.getClass().getName().toLowerCase().contains("wand")) {
            pos1 = Arrays.asList(priority).indexOf("wand");
        } else if (item1.getClass().getName().toLowerCase().contains("focus")) {
            pos1 = Arrays.asList(priority).indexOf("focus");
        } else {
            pos1 = Arrays.asList(priority).indexOf("item");
        }

        if (item2.getClass().getName().toLowerCase().contains("block")) {
            pos2 = Arrays.asList(priority).indexOf("block");
        } else if (item2.getClass().getName().toLowerCase().contains("seeds")) {
            pos2 = Arrays.asList(priority).indexOf("seeds");
        } else if (item2.getClass().getName().toLowerCase().contains("resource")) {
            pos2 = Arrays.asList(priority).indexOf("resource");
        } else if (item2.getClass().getName().toLowerCase().contains("ring") ||
                item2.getClass().getName().toLowerCase().contains("amulet")) {
            pos2 = Arrays.asList(priority).indexOf("bauble");
        } else if (item2.getClass().getName().toLowerCase().contains("armor")) {
            pos2 = Arrays.asList(priority).indexOf("armor");
        } else if (item2.getClass().getName().toLowerCase().contains("weapon") ||
                item2.getClass().getName().toLowerCase().contains("bow")) {
            pos2 = Arrays.asList(priority).indexOf("weapon");
        } else if (item2.getClass().getName().toLowerCase().contains("wand")) {
            pos2 = Arrays.asList(priority).indexOf("wand");
        } else if (item2.getClass().getName().toLowerCase().contains("focus")) {
            pos2 = Arrays.asList(priority).indexOf("focus");
        } else {
            pos2 = Arrays.asList(priority).indexOf("item");
        }

        if (pos1 != pos2) {
            return pos1 > pos2 ? 1 : -1;
        }  else {
            String displayName1 = stack1.getDisplayName();
            String displayName2 = stack2.getDisplayName();

            int result = displayName1.compareToIgnoreCase(displayName2);

            return result;
        }
    }
}