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
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
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
import thaumrev.ThaumRevLibrary;
import thaumrev.util.DamageSourceWarden;
import thaumrev.util.PurityHelper;
import thaumrev.util.wardenic.VisHelper;
import thaumrev.util.wardenic.WardenicChargeHelper;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Multimap;

import static thaumrev.ThaumRevLibrary.KNOCKBACK_MODIFIER;
import static thaumrev.ThaumRevLibrary.SPEED_MODIFIER;

import static thaumrev.ThaumRevLibrary.EXCUBITOR;

public class ItemWardenAmulet extends Item implements IBauble, IRunicArmor, IVisDiscountGear, IGoggles, IRevealer {
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

  public static ItemStack getAmulet(EntityPlayer player) {
    InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);

    for (int i = 0; i < baubles.getSizeInventory(); i++) {
      if (baubles.getStackInSlot(i) != null &&
        baubles.getStackInSlot(i).getItem() instanceof ItemWardenAmulet) {
        return baubles.getStackInSlot(i);
      }
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

  private static List<Object> getEntities(EntityPlayer player) {
    return new ArrayList<Object>(
      player.worldObj.getEntitiesWithinAABBExcludingEntity(
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

  private static void summonParticles(World world, double x, double y, double z) {
    for (int i = 0; i < 2; i++) {
      float fx = (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F;
      float fy = (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F;
      float fz = (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F;
      Thaumcraft.proxy.wispFX3(world,
        x + fx, y + fy, z + fz,
        x + fx * 8.0F, y + fy * 8.0F, z + fz * 8.0F,
        0.3F, i % 2 == 0 ? 0 : 2, true, 0.02F);
    }
  }

  private void chargeWands(ItemStack amulet, EntityLivingBase player) {
    ItemWandCasting wand = (ItemWandCasting) player.getHeldItem().getItem();
    AspectList al = wand.getAspectsWithRoom(player.getHeldItem());
    Aspect[] aspects = al.getAspects();

    for (int j = 0; j < aspects.length; ++j) {
      Aspect aspect = aspects[j];
      if (aspect != null && VisHelper.getVis(amulet, aspect) > 0) {
        int amt = Math.min(5, wand.getMaxVis(player.getHeldItem()) - wand.getVis(player.getHeldItem(), aspect));
        amt = Math.min(amt, VisHelper.getVis(amulet, aspect));
        VisHelper.storeVis(amulet, aspect, VisHelper.getVis(amulet, aspect) - amt);
        wand.storeVis(player.getHeldItem(), aspect, VisHelper.getVis(player.getHeldItem(), aspect) + amt);
      }
    }
  }

  private void chargeVis(ItemStack amulet, EntityLivingBase player) {
    if (
      ((WeakReference) TileVisRelay.nearbyPlayers.get(player.getEntityId())).get() != null &&
        ((TileVisRelay) ((WeakReference) TileVisRelay.nearbyPlayers
          .get(player.getEntityId())).get())
          .getDistanceSq(player.posX, player.posY, player.posZ) < 26.0D
    ) {
      AspectList al = VisHelper.getAspectsWithRoom(amulet);
      Aspect[] aspects = al.getAspects();

      for (Aspect aspect : aspects) {
        if (aspect != null) {
          int amt = ((TileVisRelay) ((WeakReference) TileVisRelay.nearbyPlayers.get(player.getEntityId())).get()).consumeVis(aspect, Math.min(5, VisHelper.getMaxVis(amulet) - VisHelper.getVis(amulet, aspect)));
          if (amt > 0) {
            VisHelper.addRealVis(amulet, aspect, amt);
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

  /* Overrides - void */
  @Override
  public void onWornTick(ItemStack amulet, EntityLivingBase player) {
    if (!player.worldObj.isRemote && player.ticksExisted % 5 == 0) {
      if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemWandCasting) {
        chargeWands(amulet, player);
      }

      if (TileVisRelay.nearbyPlayers.containsKey(player.getEntityId())) {
        chargeVis(amulet, player);
      }
    }

    // String upgrade = WardenicChargeHelper.getUpgrade(amulet).getUpgradeAspect();
  }

  @Override
  public void onEquipped(ItemStack amulet, EntityLivingBase entity) {
    // entityLivingBase.world.playSoundAtEntity(entityLivingBase, "thaumrev:compramos", 1, 1);
  }

  @Override
  public void onUnequipped(ItemStack amulet, EntityLivingBase entity) { }

  @Override
  public void addInformation(ItemStack amulet, EntityPlayer player, List list, boolean doit) {
    String chargeInformation;

    if (amulet.getMetadata() > 120) {
      amulet.setMetadata(120);
    } else if (amulet.getMetadata() < 0) amulet.setMetadata(0);

    if (amulet.getMetadata() > 101) {
      chargeInformation = EnumChatFormatting.DARK_RED.toString();
    } else if (amulet.getMetadata() > 81) {
      chargeInformation = EnumChatFormatting.RED.toString();
    } else if (amulet.getMetadata() > 61) {
      chargeInformation = EnumChatFormatting.GOLD.toString();
    } else if (amulet.getMetadata() > 41) {
      chargeInformation = EnumChatFormatting.YELLOW.toString();
    } else if (amulet.getMetadata() > 21) {
      chargeInformation = EnumChatFormatting.GREEN.toString();
    } else {
      chargeInformation = EnumChatFormatting.DARK_GREEN.toString();
    }

    chargeInformation += (120 - amulet.getMetadata()) + "/120";

    list.add(chargeInformation);
    list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("item.capacity.text") + " " + VisHelper.getMaxVis(amulet) / 100);

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

    if (amulet.hasTagCompound()) {
      for (Aspect aspect : Aspect.getPrimalAspects()) {
        if (amulet.stackTagCompound.hasKey(aspect.getTag())) {
          String amount = this.formatter.format(((float) amulet.stackTagCompound.getInteger(aspect.getTag()) / 100.0F));
          list.add(" §" + aspect.getChatcolor() + aspect.getName() + "§r x " + amount);
        }
      }
    }

    list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("tooltip.wardenic.upgrade") +
      ": " + WardenicChargeHelper.getUpgrade(amulet).getQuote());

    super.addInformation(amulet, player, list, doit);
  }


  /* Overrides - boolean */
  @Override
  public boolean canEquip(ItemStack amulet, EntityLivingBase entityLivingBase) { return true; }

  @Override
  public boolean canUnequip(ItemStack amulet, EntityLivingBase entityLivingBase) { return true; }

  // @Override
  // public boolean getShareTag() { return true; }

  /* Overrides - ItemStack */
  @Override
  public ItemStack onItemRightClick(ItemStack amulet, World world, EntityPlayer player) {
    // world.playSoundAtEntity(player, "thaumrev:compramos", 1, 1);
    return super.onItemRightClick(amulet, world, player);
  }


  /* Overrides - int */
  @Override
  public int getMaxDurability() { return 120; }

  @Override
  public int getRunicCharge(ItemStack itemStack) { return 0; }


  /* Overrides - BaubleType */
  @Override
  public BaubleType getBaubleType(ItemStack amulet) { return BaubleType.AMULET; }


  /* Overrides - EnumRarity */
  @Override
  public EnumRarity getRarity(ItemStack amulet) { return EnumRarity.rare; }


  /* Overrides - MultiMap */
  @Override   // FIXME: Attribute modifiers don't actually work with Baubles...
  public Multimap getAttributeModifiers(ItemStack amulet) {
    Multimap modifiers = super.getAttributeModifiers(amulet);
    String upgrade = WardenicChargeHelper.getUpgrade(amulet).getUpgradeAspect();
    AttributeModifier speedModifier;
    AttributeModifier knockbackModifier = null;
    float value = 0;

    if (upgrade.equals(Aspect.AIR.getName())) {
      value = 0.01F;
    } else if (upgrade.equals(Aspect.EARTH.getName())) {
      value = -0.04F;
    } else {
      value = 0.0F;
    }

    speedModifier = new AttributeModifier(
      SPEED_MODIFIER, "SPEED_MODIFIER", value, 0
    );

    if (upgrade.equals(Aspect.EARTH.getName())) {
      knockbackModifier = new AttributeModifier(
        KNOCKBACK_MODIFIER, "KNOCKBACK_MODIFIER", 0.75F, 0
      );
    }

    modifiers.put(
      SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(),
      speedModifier
    );

    if (knockbackModifier != null) {
      modifiers.put(
        SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(),
        knockbackModifier
      );
    }

    return modifiers;
  }


  /* Client-side */
  @Override
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister register) {
    itemIcon = register.registerIcon("thaumrev:wardenamulet");
  }

  @Override
  public boolean showNodes(ItemStack amulet, EntityLivingBase entity) {
    if (!(entity instanceof EntityPlayer)) return false;

    short count = WardenicChargeHelper.getWardenicArmorCount((EntityPlayer) entity);
    return count == 4;
  }

  @Override
  public boolean showIngamePopups(ItemStack amulet, EntityLivingBase entity) {
    if (!(entity instanceof EntityPlayer)) return false;

    short count = WardenicChargeHelper.getWardenicArmorCount((EntityPlayer) entity);
    return count == 4;
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
}
