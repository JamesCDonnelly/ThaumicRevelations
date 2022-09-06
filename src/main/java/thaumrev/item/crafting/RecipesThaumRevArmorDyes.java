package thaumrev.item.crafting;

import net.minecraft.block.BlockColored;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import thaumrev.item.armor.ItemWardenclothRobes;

import java.util.ArrayList;

public class RecipesThaumRevArmorDyes implements IRecipe {

  @Override
  public boolean matches(@NotNull InventoryCrafting inventory, World world) {
    ItemStack dyableArmor = null;
    ArrayList<ItemStack> dyeList = new ArrayList<>();

    for (int i = 0; i < inventory.getSizeInventory(); ++i) {
      ItemStack stack = inventory.getStackInSlot(i);

      if (stack == null) {
        continue;
      }

      if (stack.getItem() instanceof ItemArmor) {
        ItemArmor armor = (ItemArmor) stack.getItem();

        if (!(armor instanceof ItemWardenclothRobes) || dyableArmor != null) {
          return false;
        }

        dyableArmor = stack;
      } else {
        if (stack.getItem() != Items.dye) {
          return false;
        }

        dyeList.add(stack);
      }
    }

    return dyableArmor != null && !dyeList.isEmpty();
  }

  @Override
  public ItemStack getCraftingResult(@NotNull InventoryCrafting inventory) {
    ItemStack result = null;
    ItemArmor armor = null;

    int[] colorArray = new int[3];
    int newColor = 0;
    int dyeCount = 0;

    int currentColor = 0;

    for (int i = 0; i < inventory.getSizeInventory(); ++i) {
      ItemStack stack = inventory.getStackInSlot(i);

      if (stack != null) {
        if (stack.getItem() instanceof ItemArmor) {
          armor = (ItemArmor) stack.getItem();

          if (!(armor instanceof ItemWardenclothRobes) || result != null) {
            return null;
          }

          result = stack.copy();
          result.stackSize = 1;

          if (armor.hasColor(stack)) {
            currentColor = armor.getColor(stack);

            float red = (float)(currentColor >> 16 & 255) / 255.0F;
            float green = (float)(currentColor >> 8 & 255) / 255.0F;
            float blue = (float)(currentColor & 255) / 255.0F;

            newColor = (int)((float)newColor + Math.max(red, Math.max(green, blue)) * 255.0F);

            colorArray[0] = (int) ((float)colorArray[0] + red * 255.0F);
            colorArray[1] = (int) ((float)colorArray[1] + green * 255.0F);
            colorArray[2] = (int) ((float)colorArray[2] + blue * 255.0F);

            ++dyeCount;
          }
        } else {
          if (stack.getItem() != Items.dye) {
            return null;
          }

          float[] colorTable = EntitySheep.fleeceColorTable[
            BlockColored.func_150032_b(stack.getMetadata())
            ];

          int red = (int) (colorTable[0] * 255.0F);
          int green = (int) (colorTable[1] * 255.0F);
          int blue = (int) (colorTable[2] * 255.0F);

          newColor += Math.max(red, Math.max(green, blue));
          colorArray[0] += red;
          colorArray[1] += green;
          colorArray[2] += blue;
          ++dyeCount;
        }
      }
    }

    if (armor == null) {
      return null;
    }

    int red = colorArray[0] / dyeCount;
    int green = colorArray[1] / dyeCount;
    int blue = colorArray[2] / dyeCount;

    float avgColor = (float) newColor / (float) dyeCount;
    float maxColor = (float) Math.max(red, Math.max(green, blue));

    red = (int)((float) red * avgColor / maxColor);
    green = (int)((float) green * avgColor / maxColor);
    blue = (int)((float) blue * avgColor / maxColor);

    int finalColor = (red << 8) + green;
    finalColor = (finalColor << 8) + blue;

    armor.func_82813_b(result, finalColor);

    return result;
  }

  @Override
  public int getRecipeSize() {
    return 10;
  }

  @Override
  public ItemStack getRecipeOutput() {
    return null;
  }
}
