package thaumrev.api.wardenic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public abstract class VisHelper {

  private static int getMaxVis(@NotNull ItemStack stack) {
    return stack.stackTagCompound.getInteger("maxVis");
  }

  public static int getVis(@NotNull ItemStack stack, @NotNull Aspect aspect) {
    int out = 0;

    if (stack.hasTagCompound() && stack.stackTagCompound.hasKey(aspect.getTag())) {
      out = stack.stackTagCompound.getInteger(aspect.getTag());
    }

    return out;
  }

  public static void storeVis(@NotNull ItemStack stack, @NotNull Aspect aspect, int amount) {
    stack.setTagInfo(aspect.getTag(), new NBTTagInt(amount));
  }

  public static void storeAllVis(@NotNull ItemStack stack, @NotNull AspectList aspects) {
    for (Aspect aspect : aspects.getAspects()) {
      storeVis(stack, aspect, aspects.getAmount(aspect));
    }
  }

  public static AspectList getAspectsWithRoom(ItemStack wand) {
    AspectList out = new AspectList();
    AspectList cur = getAllVis(wand);
    Aspect[] arr$ = cur.getAspects();

    for (Aspect aspect : arr$) {
      if (cur.getAmount(aspect) < getMaxVis(wand)) {
        out.add(aspect, 1);
      }
    }

    return out;
  }

  public static AspectList getAllVis(ItemStack stack) {
    AspectList out = new AspectList();

    for (Aspect aspect : Aspect.getPrimalAspects()) {
      if (stack.hasTagCompound() && stack.stackTagCompound.hasKey(aspect.getTag())) {
        out.merge(aspect, stack.stackTagCompound.getInteger(aspect.getTag()));
      } else {
        out.merge(aspect, 0);
      }
    }

    return out;
  }

  public static boolean consumeAllVis(ItemStack stack, EntityPlayer player, AspectList aspects) {
    if (aspects != null && aspects.size() != 0) {
      Aspect[] arr$ = aspects.getAspects();
      int len$ = arr$.length;

      int i$;
      Aspect aspect;
      for(i$ = 0; i$ < len$; ++i$) {
        aspect = arr$[i$];
        if (getVis(stack, aspect) < aspects.getAmount(aspect)) {
          return false;
        }
      }

      arr$ = aspects.getAspects();
      len$ = arr$.length;

      for(i$ = 0; i$ < len$; ++i$) {
        aspect = arr$[i$];
        storeVis(stack, aspect, getVis(stack, aspect) - aspects.getAmount(aspect));
      }

      return true;
    } else {
      return false;
    }
  }

  public static int addVis(@NotNull ItemStack stack, @NotNull Aspect aspect, int amount) {
    if (!aspect.isPrimal()) {
      return 0;
    } else {
      int storeAmount = getVis(stack, aspect) + amount * 100;
      int leftover = Math.max(storeAmount - getMaxVis(stack), 0);
      storeVis(stack, aspect, Math.min(storeAmount, getMaxVis(stack)));

      return leftover / 100;
    }
  }

  public static int addRealVis(@NotNull ItemStack stack, @NotNull Aspect aspect, int amount) {
    if (!aspect.isPrimal()) {
      return 0;
    } else {
      int storeAmount = getVis(stack, aspect) + amount;
      int leftover = Math.max(storeAmount - getMaxVis(stack), 0);
      storeVis(stack, aspect, Math.min(storeAmount, getMaxVis(stack)));


      return leftover;
    }
  }
}
