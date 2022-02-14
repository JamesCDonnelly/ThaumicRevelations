package thaumrev.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.INpc;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileVisRelay;
import thaumrev.ThaumRevLibrary;
import thaumrev.util.DamageSourceWarden;
import thaumrev.util.PurityHelper;
import thaumrev.util.wardenic.VisHelper;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ItemWardenAmulet extends Item implements IBauble, IRunicArmor {
  DecimalFormat formatter = new DecimalFormat("#######.##");

  public ItemWardenAmulet() {
    super();
    setUnlocalizedName("itemWardenAmulet");
    setCreativeTab(ThaumRevLibrary.tabThaumRev);
    setMaxStackSize(1);
  }

  public static void amuletParticles(EntityPlayer player) {
    ItemStack amulet = getAmulet(player);

    if (amulet != null) {
      List<Object> entities = getEntities(player);

      for (Object entity : entities) {
        if (entity instanceof EntityLivingBase || entity instanceof INpc) {

          summonParticles(
            player.worldObj,
            ((Entity) entity).posX,
            ((Entity) entity).posY,
            ((Entity) entity).posZ
          );
        }
      }
    }
  }

  public static void amuletEffect(EntityPlayer player) {
    ItemStack amulet = getAmulet(player);
    DamageSource damageSource = new DamageSourceWarden("warden", player);

    if (amulet == null) {
      return;
    }

    List<Object> entities = getEntities(player);

    amulet.setMetadata(120);

    for (Object entity : entities) {
      if (entity instanceof Entity &&
        (PurityHelper.isEldritchOrTainted((Entity) entity) || entity instanceof EntityPlayer)) {
        PurityHelper.purifyEntity((Entity) entity);

        if (PurityHelper.isEldritchOrTainted((Entity) entity)) {
          ((Entity) entity).attackEntityFrom(damageSource, ((EntityLivingBase) entity).getMaxHealth() / 2);
        }
      }

      if (entity instanceof EntityPlayer) {
        ItemStack am = getAmulet((EntityPlayer) entity);

        if (am == null) {
          return;
        }

        am.setMetadata(am.getMetadata() - 5);
        amulet.setMetadata(amulet.getMetadata() + 5);
      }
    }

    Thaumcraft.addWarpToPlayer(player, -10, true);
  }

  public static @Nullable ItemStack getAmulet(EntityPlayer player) {
    InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);

    for (int i = 0; i < baubles.getSizeInventory(); i++) {
      if (baubles.getStackInSlot(i) != null &&
        baubles.getStackInSlot(i).getItem() instanceof ItemWardenAmulet) {
        return baubles.getStackInSlot(i);
      }
    }

    return null;
  }

  @Contract("_ -> new")
  private static @NotNull List<Object> getEntities(@NotNull EntityPlayer player) {
    return new ArrayList<Object>(player.worldObj.getEntitiesWithinAABBExcludingEntity(
      player,
      AxisAlignedBB.getBoundingBox(
        player.posX - 8,
        player.posY - 8,
        player.posZ - 8,
        player.posX + 8,
        player.posY + 8,
        player.posZ + 8)
    ));
  }

  private static void summonParticles(World worldObj, double posX, double posY, double posZ) {
    for (int i = 0; i < 3; i++) {
      float fx = (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.3F;
      float fy = (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.3F;
      float fz = (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.3F;
      Thaumcraft.proxy.wispFX3(worldObj,
        posX + fx, posY + fy, posZ + fz,
        posX + fx * 8.0F, posY + fy * 8.0F, posZ + fz * 8.0F,
        0.3F, i % 2 == 0 ? 0 : 2, true, 0.02F);
    }
  }


  /*
   * Overrides - void
   */
  @Override
  public void onWornTick(@NotNull ItemStack stack, @NotNull EntityLivingBase player) {
    if (!player.worldObj.isRemote && player.ticksExisted % 5 == 0) {
      int i;
      if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemWandCasting) {
        ItemWandCasting wand = (ItemWandCasting) player.getHeldItem().getItem();
        AspectList al = wand.getAspectsWithRoom(player.getHeldItem());
        Aspect[] arr$ = al.getAspects();
        i = arr$.length;

        for (int j = 0; j < i; ++j) {
          Aspect aspect = arr$[j];
          if (aspect != null && VisHelper.getVis(stack, aspect) > 0) {
            int amt = Math.min(5, wand.getMaxVis(player.getHeldItem()) - wand.getVis(player.getHeldItem(), aspect));
            amt = Math.min(amt, VisHelper.getVis(stack, aspect));
            VisHelper.storeVis(stack, aspect, VisHelper.getVis(stack, aspect) - amt);
            wand.storeVis(player.getHeldItem(), aspect, VisHelper.getVis(player.getHeldItem(), aspect) + amt);
          }
        }
      }

      if (TileVisRelay.nearbyPlayers.containsKey(player.getEntityId())) {
        if (
          ((WeakReference) TileVisRelay.nearbyPlayers.get(player.getEntityId())).get() != null &&
            ((TileVisRelay) ((WeakReference) TileVisRelay.nearbyPlayers
              .get(player.getEntityId())).get())
              .getDistanceSq(player.posX, player.posY, player.posZ) < 26.0D
        ) {
          AspectList al = VisHelper.getAspectsWithRoom(stack);
          Aspect[] aspects = al.getAspects();

          for (Aspect aspect : aspects) {
            if (aspect != null) {
              int amt = ((TileVisRelay) ((WeakReference) TileVisRelay.nearbyPlayers.get(player.getEntityId())).get()).consumeVis(aspect, Math.min(5, VisHelper.getMaxVis(stack) - VisHelper.getVis(stack, aspect)));
              if (amt > 0) {
                VisHelper.addRealVis(stack, aspect, amt);
                TileVisRelay tileVisRelay = (TileVisRelay) ((WeakReference) TileVisRelay.nearbyPlayers.get(player.getEntityId())).get();

                if (tileVisRelay == null) {
                  return;
                }

                tileVisRelay.triggerConsumeEffect(aspect);
              }
            }
          }
        } else {
          TileVisRelay.nearbyPlayers.remove(player.getEntityId());
        }
      }
    }
  }

  @Override
  public void onEquipped(@NotNull ItemStack stack, @NotNull EntityLivingBase entityLivingBase) {
    // entityLivingBase.worldObj.playSoundAtEntity(entityLivingBase, "thaumrev:compramos", 1, 1);
  }

  @Override
  public void onUnequipped(@NotNull ItemStack stack, @NotNull EntityLivingBase entityLivingBase) {
  }

  @Override
  public void addInformation(@NotNull ItemStack stack, @NotNull EntityPlayer player, @NotNull List list, @NotNull boolean b) {
    String chargeInformation;

    if (stack.getMetadata() > 120) {
      stack.setMetadata(120);
    } else if (stack.getMetadata() < 0) stack.setMetadata(0);

    if (stack.getMetadata() > 101) {
      chargeInformation = EnumChatFormatting.DARK_RED.toString();
    } else if (stack.getMetadata() > 81) {
      chargeInformation = EnumChatFormatting.RED.toString();
    } else if (stack.getMetadata() > 61) {
      chargeInformation = EnumChatFormatting.GOLD.toString();
    } else if (stack.getMetadata() > 41) {
      chargeInformation = EnumChatFormatting.YELLOW.toString();
    } else if (stack.getMetadata() > 21) {
      chargeInformation = EnumChatFormatting.GREEN.toString();
    } else {
      chargeInformation = EnumChatFormatting.DARK_GREEN.toString();
    }

    chargeInformation += (120 - stack.getMetadata()) + "/120";
    list.add(chargeInformation);

    list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("item.capacity.text") + " " + VisHelper.getMaxVis(stack) / 100);
    if (stack.hasTagCompound()) {

      for (Aspect aspect : Aspect.getPrimalAspects()) {
        if (stack.stackTagCompound.hasKey(aspect.getTag())) {
          String amount = this.formatter.format(((float) stack.stackTagCompound.getInteger(aspect.getTag()) / 100.0F));
          list.add(" §" + aspect.getChatcolor() + aspect.getName() + "§r x " + amount);
        }
      }
    }

    super.addInformation(stack, player, list, b);
  }


  /*
   * Overrides - boolean
   */
  @Override
  public boolean canEquip(@NotNull ItemStack stack, @NotNull EntityLivingBase entityLivingBase) {
    return true;
  }

  @Override
  public boolean canUnequip(@NotNull ItemStack stack, @NotNull EntityLivingBase entityLivingBase) {
    return true;
  }

  @Override
  public boolean isDamageable() {
    return false;
  }


  /*
   * Overrides - ItemStack
   */
  @Override
  public ItemStack onItemRightClick(@NotNull ItemStack stack, @NotNull World world, @NotNull EntityPlayer player) {
    world.playSoundAtEntity(player, "thaumrev:compramos", 1, 1);
    return super.onItemRightClick(stack, world, player);
  }


  /*
   * Overrides - int
   */
  @Override
  public int getMaxDurability() {
    return 120;
  }

  @Override
  public int getRunicCharge(ItemStack itemStack) {
    return 0;
  }


  /*
   * Overrides - BaubleType
   */
  @Override
  public BaubleType getBaubleType(@NotNull ItemStack stack) {
    return BaubleType.AMULET;
  }


  /*
   * Overrides - EnumRarity
   */
  @Override
  public EnumRarity getRarity(@NotNull ItemStack stack) {
    return EnumRarity.rare;
  }



  /*
   * Client-side
   */
  @Override
  @SideOnly(Side.CLIENT)
  public void registerIcons(@NotNull IIconRegister register) {
    itemIcon = register.registerIcon("thaumrev:wardenamulet");
  }
}
