package thaumrev.item.armor;

import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import thaumcraft.api.IGoggles;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.common.Thaumcraft;
import thaumrev.ThaumRevLibrary;
import thaumrev.util.wardenic.WardenicChargeHelper;

import java.util.List;

import static thaumrev.ThaumRevLibrary.ARMOR_KNOCKBACK_MODIFIERS;
import static thaumrev.ThaumRevLibrary.ARMOR_SPEED_MODIFIERS;

public class ItemWardenArmor extends ItemArmor implements IRepairable, ISpecialArmor, IVisDiscountGear, IGoggles, IRevealer {
  public IIcon iconHelm;
  public IIcon iconChest;
  public IIcon iconLegs;
  public IIcon iconBoots;

  public ItemWardenArmor(int type, String name) {
    super(ThaumRevLibrary.armorMaterialWarden, 2, type);
    setUnlocalizedName(name);
    setCreativeTab(ThaumRevLibrary.tabThaumRev);
    setMaxStackSize(1);
  }

  /** Overrides - void **/
  @Override
  public void addInformation(ItemStack armor, EntityPlayer player, List list, boolean par4) {
    super.addInformation(armor, player, list, par4);

    list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("tooltip.wardenic.upgrade") +
      ": " + WardenicChargeHelper.getUpgrade(armor).getQuote());

    if (this.getVisDiscount(armor, player, Aspect.AIR) == 10) {
      list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") +
        " (Aer): " + this.getVisDiscount(armor, player, Aspect.AIR) + "%");
    } else if (this.getVisDiscount(armor, player, Aspect.WATER) == 10) {
      list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") +
        " (Aqua): " + this.getVisDiscount(armor, player, Aspect.WATER) + "%");
    } else if (this.getVisDiscount(armor, player, Aspect.FIRE) == 10) {
      list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") +
        " (Ignis): " + this.getVisDiscount(armor, player, Aspect.FIRE) + "%");
    } else if (this.getVisDiscount(armor, player, Aspect.ORDER) == 10) {
      list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") +
        " (Ordo): " + this.getVisDiscount(armor, player, Aspect.ORDER) + "%");
    } else if (this.getVisDiscount(armor, player, Aspect.ENTROPY) == 10) {
      list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") +
        " (Perditio): " + this.getVisDiscount(armor, player, Aspect.ENTROPY) + "%");
    } else if (this.getVisDiscount(armor, player, Aspect.EARTH) == 10) {
      list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") +
        " (Terra): " + this.getVisDiscount(armor, player, Aspect.EARTH) + "%");
    } else {
      list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") +
        ": " + this.getVisDiscount(armor, player, null) + "%");
    }
  }

  @Override
  public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
    super.onArmorTick(world, player, stack);
    WardenicChargeHelper.getUpgrade(stack).onTick(world, player, stack);
    String upgrade = WardenicChargeHelper.getUpgrade(stack).getUpgradeAspect();
    ItemArmor armor = (ItemArmor) stack.getItem();

    if (player.moveForward > 0.0F) {
      if (armor.armorType == 3) {
        if (
          (upgrade.equals(Aspect.AIR.getName()) && !player.isInWater()) ||
          (upgrade.equals(Aspect.WATER.getName()) && player.isInWater())
        ) {
          if (!player.isSneaking()) {
            player.stepHeight = 1.0F;
          } else {
            player.stepHeight = 0.5F;
          }

          if (!Thaumcraft.instance.entityEventHandler.prevStep.containsKey(player.getEntityId())) {
            Thaumcraft.instance.entityEventHandler.prevStep.put(player.getEntityId(), player.stepHeight);
          }
        }
      }

      if (player.isInWater()) {
        if (upgrade.equals(Aspect.WATER.getName())) {
          player.moveFlying(0.0F, 1.0F, player.isSprinting() ? 0.02F : 0.01F);
        } else if (upgrade.equals(Aspect.AIR.getName()) || upgrade.equals(Aspect.EARTH.getName())) {
          player.moveFlying(0.0F, 1.0F, -0.001F);
        }
      }
      if (upgrade.equals(Aspect.AIR.getName())) {
        player.jumpMovementFactor += 0.0025F;
      } else if (upgrade.equals(Aspect.EARTH.getName())) { 
        player.jumpMovementFactor -= 0.0025F;
      }

      // if (player.onGround) {
			// 	if (player.isInWater()) {
			// 		player.moveFlying(0.0F, 1.0F, water * 0.025F);
			// 	} else {
			// 		player.moveFlying(0.0F, 1.0F, air * 0.010F - earth * 0.005F);
			// 	}
			// } else if (Hover.getHover(player.getEntityId())) {
			// 	player.jumpMovementFactor = 0.02F + 0.0025F * air;
			// } else {
			// 	player.jumpMovementFactor = 0.02F + 0.005F * air;
			// }
    }
  }


  @Override
  public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
  }

  /** Overrides - MultiMap **/
  @Override
  public Multimap getAttributeModifiers(ItemStack stack) {
    Multimap modifiers = super.getAttributeModifiers(stack);
    String upgrade = WardenicChargeHelper.getUpgrade(stack).getUpgradeAspect();
    ItemArmor armor = (ItemArmor) stack.getItem();
    AttributeModifier speedModifier;
    AttributeModifier knockbackModifier = null;
    float value = 0;

    if (upgrade.equals(Aspect.AIR.getName())) {
      value = 0.01F;
    } else if (upgrade.equals(Aspect.EARTH.getName())) {
      value = -0.01F;
    } else {
      value = 0.0F;
    }

    speedModifier = new AttributeModifier(
      ARMOR_SPEED_MODIFIERS[armor.armorType], "SPEED_MODIFIER", value, 0
    );

    if (upgrade.equals(Aspect.EARTH.getName())) {
      knockbackModifier = new AttributeModifier(
        ARMOR_KNOCKBACK_MODIFIERS[armor.armorType], "KNOCKBACK_MODIFIER", 0.25F, 0
      );
    }

    modifiers.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), speedModifier);

    if (knockbackModifier != null) {
      modifiers.put(SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), knockbackModifier);
    }

    return modifiers;
  }


  /* Overrides - boolean */
  @Override
  public boolean getShareTag() {
    return true;
  }

  @Override
  public boolean isBookEnchantable(ItemStack armor, ItemStack book) {
    return false;
  }

  @Override
  public boolean isDamageable() {
    return false;
  }

  @Override
  public boolean showIngamePopups(ItemStack stack, EntityLivingBase entityLivingBase) {
    return ((ItemArmor)stack.getItem()).armorType == 0;
  }

  @Override
  public boolean showNodes(ItemStack stack, EntityLivingBase entityLivingBase) {
    return ((ItemArmor)stack.getItem()).armorType == 0;
  }


  /* Overrides - int */
  @Override
  public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
    return getArmorMaterial().getDamageReductionAmount(slot);
  }

  @Override
  public int getVisDiscount(ItemStack armor, EntityPlayer player, Aspect aspect) {
    if (WardenicChargeHelper.getUpgrade(armor).getUpgradeAspect().equals(Aspect.AIR.getName())) {
      return aspect == Aspect.AIR ? 10 : 0;
    } else if (WardenicChargeHelper.getUpgrade(armor).getUpgradeAspect().equals(Aspect.EARTH.getName())) {
      return aspect == Aspect.EARTH ? 10 : 0;
    } else if (WardenicChargeHelper.getUpgrade(armor).getUpgradeAspect().equals(Aspect.ENTROPY.getName()))  {
      return aspect == Aspect.ENTROPY ? 10 : 0;
    } else if (WardenicChargeHelper.getUpgrade(armor).getUpgradeAspect().equals(Aspect.FIRE.getName()))  {
      return aspect == Aspect.FIRE ? 10 : 0;
    } else if (WardenicChargeHelper.getUpgrade(armor).getUpgradeAspect().equals(Aspect.ORDER.getName()))  {
      return aspect == Aspect.ORDER ? 10 : 0;
    } else if (WardenicChargeHelper.getUpgrade(armor).getUpgradeAspect().equals(Aspect.WATER.getName()))  {
      return aspect == Aspect.WATER ? 10 : 0;
    } else {
      return 5;
    }
  }


  /* Overrides - EnumRarity */
  @Override
  public EnumRarity getRarity(ItemStack stack) {
    return EnumRarity.epic;
  }


  /* Overrides - ArmorProperties */
  @Override
  public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
    return new ArmorProperties(0, getArmorMaterial().getDamageReductionAmount(slot) / 25D, 20);
  }


  /* Client-side */
  @Override
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister register) {
    this.iconHelm = register.registerIcon("thaumrev:armor/wardenhelm");
    this.iconChest = register.registerIcon("thaumrev:armor/wardenchest");
    this.iconLegs = register.registerIcon("thaumrev:armor/wardenlegs");
    this.iconBoots = register.registerIcon("thaumrev:armor/wardenboots");
  }

  @Override
  @SideOnly(Side.CLIENT)
  public IIcon getIconFromDamage(int par1) {
    return this.armorType == 0 ? this.iconHelm : (this.armorType == 1 ? this.iconChest : (this.armorType == 2 ? this.iconLegs : this.iconBoots));
  }

  @Override
  @SideOnly(Side.CLIENT)
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "thaumrev:textures/models/warden_" + (this.armorType == 2 ? "2" : "1") + ".png";
  }
}
