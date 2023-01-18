package thaumrev.item.baubles;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import net.minecraft.util.IIcon;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.INpc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.IGoggles;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileVisRelay;
import thaumrev.config.ConfigLibrary;
import thaumrev.lib.utils.PurityHelper;
import thaumrev.api.wardenic.VisHelper;
import thaumrev.api.wardenic.WardenicChargeHelper;

import static thaumrev.config.ConfigLibrary.EXCUBITOR;
import static thaumrev.lib.utils.PurityHelper.summonParticles;

public class ItemWardenAmulet extends Item implements IBauble, IRunicArmor, IVisDiscountGear, IGoggles, IRevealer {

  DecimalFormat formatter = new DecimalFormat("#######.##");
  IIcon[] icons = new IIcon[7];

  public ItemWardenAmulet() {
    super();
    setUnlocalizedName("itemWardenAmulet");
    setMaxStackSize(1);
    setCreativeTab(ConfigLibrary.tabThaumRev);
  }

  public static int getMaxVis() {
    return 100000;
  }

  public static void amuletParticles(EntityPlayer player) {
    ItemStack amulet = getAmulet(player);

    if (amulet != null) {
      List<Entity> entities = getEntities(player);

      for (Entity entity : entities) {
        if (entity instanceof EntityLivingBase || entity instanceof INpc) {
          summonParticles(
            player.worldObj,
            entity.posX,
            entity.posY,
            entity.posZ
          );
        }
      }
    }
  }

  public static void amuletEffect(EntityPlayer player) {
    short count = WardenicChargeHelper.getWardenicArmorCount(player);
    ItemStack amulet = getAmulet(player);

    if (amulet == null) {
      return;
    }

    List<Entity> entities = getEntities(player);

    amulet.setMetadata(120);

    for (Entity e : entities) {
      if (PurityHelper.isEldritchOrTainted(e)) {
        EntityLivingBase entity = (EntityLivingBase) e;

        PurityHelper.purifyEntity(entity);
        PurityHelper.damageImpureEntity(entity, player, entity.getMaxHealth() / (10 - 2 * count));
      }

      if (e instanceof EntityPlayer) {
        ItemStack am = getAmulet((EntityPlayer) e);

        if (am == null) {
          return;
        }

        am.setMetadata(am.getMetadata() - (count + 1));
        amulet.setMetadata(amulet.getMetadata() + (count + 1));
      }
    }

    Thaumcraft.addWarpToPlayer(player, -2 * (count + 1), true);
  }

  public static @Nullable ItemStack getAmulet(EntityPlayer player) {
    InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);

    if (baubles.getStackInSlot(0) != null &&
      baubles.getStackInSlot(0).getItem() instanceof ItemWardenAmulet
    ) {
      return baubles.getStackInSlot(0);
    }

    return null;
  }

  public static boolean shouldActivate(ItemStack amulet, int value) {
    if (amulet != null) {
      Aspect aspect = WardenicChargeHelper.getUpgrade(amulet).aspect;

      if (aspect.getName().equals(EXCUBITOR.getName())) {
        AspectList aspectList = VisHelper.getAllVis(amulet);
        Aspect[] aspects = aspectList.getAspects();
        boolean doit = true;

        for (Aspect a : aspects) {
          if (aspectList.getAmount(a) < value / 10) {
            doit = false;
            break;
          }
        }

        if (doit) {
          for (Aspect a : aspects) {
            VisHelper.addRealVis(amulet, a, -value / 10);
          }
        } else {
          return false;
        }
      } else {
        int vis = VisHelper.getVis(amulet, aspect);

        if (vis > value) {
          VisHelper.addRealVis(amulet, aspect, -value);
        } else {
          return false;
        }
      }

      return true;
    } else {
      return false;
    }
  }

  @Contract("_ -> new")
  private static @NotNull List<Entity> getEntities(@NotNull EntityPlayer player) {
    return new ArrayList<Entity>(
      player.worldObj.getEntitiesWithinAABBExcludingEntity(
        player,
        AxisAlignedBB.getBoundingBox(
          player.posX - 8,
          player.posY - 8,
          player.posZ - 8,
          player.posX + 8,
          player.posY + 8,
          player.posZ + 8
        )));
  }

  private void chargeWands(ItemStack amulet, @NotNull EntityLivingBase player) {
    ItemWandCasting wand = (ItemWandCasting) player.getHeldItem().getItem();
    AspectList al = wand.getAspectsWithRoom(player.getHeldItem());
    Aspect[] aspects = al.getAspects();

    for (Aspect aspect : aspects) {
      if (aspect != null && VisHelper.getVis(amulet, aspect) > 0) {
        int amt = Math.min(5, wand.getMaxVis(player.getHeldItem()) - wand.getVis(player.getHeldItem(), aspect));
        amt = Math.min(amt, VisHelper.getVis(amulet, aspect));
        VisHelper.storeVis(amulet, aspect, VisHelper.getVis(amulet, aspect) - amt);
        wand.storeVis(player.getHeldItem(), aspect, VisHelper.getVis(player.getHeldItem(), aspect) + amt);
      }
    }
  }

  private void chargeVis(ItemStack amulet, @NotNull EntityLivingBase player) {
    if (
      (TileVisRelay.nearbyPlayers.get(player.getEntityId())).get() != null &&
        (Objects.requireNonNull((TileVisRelay.nearbyPlayers
          .get(player.getEntityId())).get()))
          .getDistanceSq(player.posX, player.posY, player.posZ) < 26.0D
    ) {
      AspectList al = VisHelper.getAspectsWithRoom(amulet);
      Aspect[] aspects = al.getAspects();

      for (Aspect aspect : aspects) {
        if (aspect != null) {
          int amt = (
            Objects
              .requireNonNull((TileVisRelay.nearbyPlayers.get(player.getEntityId())).get()))
            .consumeVis(aspect, Math.min(5, ItemWardenAmulet.getMaxVis() - VisHelper.getVis(amulet, aspect)));
          if (amt > 0) {
            VisHelper.addRealVis(amulet, aspect, amt);
            TileVisRelay tileVisRelay = TileVisRelay.nearbyPlayers.get(player.getEntityId()).get();

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

  /* Overrides - void */
  @Override
  public void onWornTick(ItemStack amulet, @NotNull EntityLivingBase player) {
    if (!player.worldObj.isRemote && player.ticksExisted % 5 == 0) {
      if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemWandCasting) {
        chargeWands(amulet, player);
      }

      if (TileVisRelay.nearbyPlayers.containsKey(player.getEntityId())) {
        chargeVis(amulet, player);
      }
    }

    WardenicChargeHelper.getUpgrade(amulet).onWornTick(amulet, player);
  }

  @Override
  public void onEquipped(ItemStack amulet, EntityLivingBase entity) {
    WardenicChargeHelper.getUpgrade(amulet).onEquipped(amulet, entity);
  }

  @Override
  public void onUnequipped(ItemStack amulet, EntityLivingBase entity) {
    WardenicChargeHelper.getUpgrade(amulet).onUnequipped(amulet, entity);
  }

  @Override
  public void addInformation(@NotNull ItemStack amulet, EntityPlayer player, List list, boolean doit) {
    String chargeInformation;

    if (amulet.hasTagCompound()) {
      amulet.stackTagCompound.setInteger("max_vis", ItemWardenAmulet.getMaxVis());

      int charge = amulet.stackTagCompound.getInteger("current_charge");

      if (charge > 120) {
        amulet.stackTagCompound.setInteger("current_charge", 120);
      } else if (charge < 0) {
        amulet.stackTagCompound.setInteger("current_charge", 0);
      }

      if (charge > 101) {
        chargeInformation = EnumChatFormatting.DARK_GREEN.toString();
      } else if (charge > 81) {
        chargeInformation = EnumChatFormatting.GREEN.toString();
      } else if (charge > 61) {
        chargeInformation = EnumChatFormatting.YELLOW.toString();
      } else if (charge > 41) {
        chargeInformation = EnumChatFormatting.GOLD.toString();
      } else if (charge > 21) {
        chargeInformation = EnumChatFormatting.RED.toString();
      } else {
        chargeInformation = EnumChatFormatting.DARK_RED.toString();
      }

      list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tooltip.wardenic.upgrade") +
        ": " + WardenicChargeHelper.getUpgrade(amulet).getQuote());

      chargeInformation += StatCollector.translateToLocal("item.thaumrevcharge.text") + " " + charge + "/120";

      list.add(chargeInformation);

      list.add("");

      list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("item.capacity.text") + " " + ItemWardenAmulet.getMaxVis() / 100);

      for (Aspect aspect : Aspect.getPrimalAspects()) {
        if (amulet.stackTagCompound.hasKey(aspect.getTag())) {
          String amount = this.formatter.format(((float) amulet.stackTagCompound.getInteger(aspect.getTag()) / 100.0F));
          list.add(" §" + aspect.getChatcolor() + aspect.getName() + "§r x " + amount);
        }
      }

      list.add("");

      if (this.getVisDiscount(amulet, player, Aspect.AIR) == 10) {
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") +
          " (Aer): " + this.getVisDiscount(amulet, player, Aspect.AIR) + "%");
      } else if (this.getVisDiscount(amulet, player, Aspect.WATER) == 10) {
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") +
          " (Aqua): " + this.getVisDiscount(amulet, player, Aspect.WATER) + "%");
      } else if (this.getVisDiscount(amulet, player, Aspect.FIRE) == 10) {
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") +
          " (Ignis): " + this.getVisDiscount(amulet, player, Aspect.FIRE) + "%");
      } else if (this.getVisDiscount(amulet, player, Aspect.ORDER) == 10) {
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") +
          " (Ordo): " + this.getVisDiscount(amulet, player, Aspect.ORDER) + "%");
      } else if (this.getVisDiscount(amulet, player, Aspect.ENTROPY) == 10) {
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") +
          " (Perditio): " + this.getVisDiscount(amulet, player, Aspect.ENTROPY) + "%");
      } else if (this.getVisDiscount(amulet, player, Aspect.EARTH) == 10) {
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") +
          " (Terra): " + this.getVisDiscount(amulet, player, Aspect.EARTH) + "%");
      } else {
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") +
          ": " + this.getVisDiscount(amulet, player, null) + "%");
      }
    }

    super.addInformation(amulet, player, list, doit);
  }

  /* Overrides - boolean */
  @Override
  public boolean canEquip(ItemStack amulet, EntityLivingBase entityLivingBase) { return true; }

  @Override
  public boolean canUnequip(ItemStack amulet, EntityLivingBase entityLivingBase) { return true; }

  /* Overrides - ItemStack */
  @Override
  public ItemStack onItemRightClick(ItemStack amulet, World world, EntityPlayer player) {
    return super.onItemRightClick(amulet, world, player);
  }

  @Override
  public int getRunicCharge(ItemStack amulet) {
    return 2;
  }


  /* Overrides - BaubleType */
  @Override
  public BaubleType getBaubleType(ItemStack amulet) {
    return BaubleType.AMULET;
  }


  /* Overrides - EnumRarity */
  @Override
  public EnumRarity getRarity(ItemStack amulet) {
    return EnumRarity.epic;
  }

  @Override
  public int getVisDiscount(ItemStack amulet, EntityPlayer player, Aspect aspect) {
    String upgrade = WardenicChargeHelper.getUpgrade(amulet).getUpgradeAspect();

    if (upgrade.equals(Aspect.AIR.getName())) {
      return aspect == Aspect.AIR ? 10 : 0;
    } else if (upgrade.equals(Aspect.EARTH.getName())) {
      return aspect == Aspect.EARTH ? 10 : 0;
    } else if (upgrade.equals(Aspect.ENTROPY.getName()))  {
      return aspect == Aspect.ENTROPY ? 10 : 0;
    } else if (upgrade.equals(Aspect.FIRE.getName()))  {
      return aspect == Aspect.FIRE ? 10 : 0;
    } else if (upgrade.equals(Aspect.ORDER.getName()))  {
      return aspect == Aspect.ORDER ? 10 : 0;
    } else if (upgrade.equals(Aspect.WATER.getName()))  {
      return aspect == Aspect.WATER ? 10 : 0;
    } else {
      return 5;
    }
  }

  @Override
  public void getSubItems(Item item, CreativeTabs tab, List list) {
    ItemWardenAmulet amulet = (ItemWardenAmulet) item;
    ItemStack stack = new ItemStack(amulet);
    AspectList aspects = new AspectList();

    for (Aspect aspect : Aspect.getPrimalAspects()) {
      aspects.add(aspect, 100000);
    }

    VisHelper.storeAllVis(stack, aspects);

    list.add(stack);
  }

  /* Client-side */
  @Override
  @SideOnly(Side.CLIENT)
  public void registerIcons(@NotNull IIconRegister register) {
    this.icons[0] = register.registerIcon("thaumrev:baubles/wardenamulet-warden");
    this.icons[1] = register.registerIcon("thaumrev:baubles/wardenamulet-air");
    this.icons[2] = register.registerIcon("thaumrev:baubles/wardenamulet-water");
    this.icons[3] = register.registerIcon("thaumrev:baubles/wardenamulet-fire");
    this.icons[4] = register.registerIcon("thaumrev:baubles/wardenamulet-order");
    this.icons[5] = register.registerIcon("thaumrev:baubles/wardenamulet-entropy");
    this.icons[6] = register.registerIcon("thaumrev:baubles/wardenamulet-earth");
  }

  @Override
  @SideOnly(Side.CLIENT)
  public IIcon getIconFromDamage(int damage) {
    return this.icons[damage];
  }

  @Override
  @SideOnly(Side.CLIENT)
  public boolean showNodes(ItemStack amulet, EntityLivingBase entity) {
    if (!(entity instanceof EntityPlayer)) return false;

    short count = WardenicChargeHelper.getWardenicArmorCount((EntityPlayer) entity);
    return count == 4;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public boolean showIngamePopups(ItemStack amulet, EntityLivingBase entity) {
    if (!(entity instanceof EntityPlayer)) return false;

    short count = WardenicChargeHelper.getWardenicArmorCount((EntityPlayer) entity);
    return count == 4;
  }
}
