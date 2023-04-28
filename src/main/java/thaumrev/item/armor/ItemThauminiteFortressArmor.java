package thaumrev.item.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.oredict.OreDictionary;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.IGoggles;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.IRevealer;
import thaumrev.config.ConfigLibrary;
import thaumrev.client.models.ModelThauminiteFortressArmor;

import java.util.ArrayList;
import java.util.List;

import static thaumrev.config.ConfigLibrary.armorMaterialFortressThauminite;

public class ItemThauminiteFortressArmor extends ItemArmor implements IRepairable, IRunicArmor, ISpecialArmor, IGoggles, IRevealer, IVisDiscountGear {

  @SideOnly(Side.CLIENT)
  public IIcon[] icons;
  ModelBiped[] models;

  public ItemThauminiteFortressArmor(int type, String name) {
    super(armorMaterialFortressThauminite, 4, type);
    setUnlocalizedName(name);
    setCreativeTab(ConfigLibrary.tabThaumRev);
  }

  @Override
  public int getVisDiscount(ItemStack armor, EntityPlayer entityPlayer, Aspect aspect) {
    return 4;
  }

  @Override
  public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, @NotNull DamageSource source, double damage, int slot) {
    int priority = 1;
    double ratio = this.damageReduceAmount / 20.0D;

    if (source.isMagicDamage()) {
      ratio = this.damageReduceAmount / 35.0D;
    } else if (!source.isFireDamage() && !source.isExplosion()) {
      if (source.isUnblockable()) {
        ratio = 0.0D;
        priority = 0;
      }
    }

    if (player instanceof EntityPlayer) {
      double set = 0.875D;

      for (ItemStack piece : ((EntityPlayer) player).inventory.armorInventory) {
        if (piece != null && piece.getItem() instanceof ItemThauminiteFortressArmor) {
          set += 0.125D;

          if (piece.hasTagCompound() && piece.stackTagCompound.hasKey("mask")) {
            set += 0.05D;
          }
        }
      }

      ratio *= set;
    }

    return new ArmorProperties(priority, ratio, armor.getMaxDurability() + 1 - armor.getCurrentDurability());
  }

  @Override
  public EnumRarity getRarity(ItemStack armor) {
    return EnumRarity.rare;
  }

  @Override
  public void addInformation(@NotNull ItemStack armor, EntityPlayer player, List list, boolean par4) {
    if (armor.hasTagCompound() && armor.stackTagCompound.hasKey("goggles")) {
      list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("item.ItemGoggles.name"));
    }

    if (armor.hasTagCompound() && armor.stackTagCompound.hasKey("mask")) {
      list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("item.HelmetFortress.mask." + armor.stackTagCompound.getInteger("mask")));
    }

    list.add("");


    list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + ": "
      + this.getVisDiscount(armor, player, null) + "%");

    super.addInformation(armor, player, list, par4);
  }

  @Override
  public boolean getIsRepairable(ItemStack armor, ItemStack material) {
    ArrayList<ItemStack> thauminiteList = OreDictionary.getOres("ingotThauminite");
    boolean isRepairable = false;

    if (super.getIsRepairable(armor, material)) return true;

    for (ItemStack thauminite : thauminiteList) {
      isRepairable = material.isItemEqual(thauminite);
    }

    return isRepairable;
  }

  @Override
  public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
    return this.damageReduceAmount;
  }

  @Override
  public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
    if (source != DamageSource.fall) {
      armor.damageItem(damage, entity);
    }
  }

  @Override
  public int getRunicCharge(ItemStack armor) {
    return 0;
  }

  @Override
  public boolean showNodes(@NotNull ItemStack armor, EntityLivingBase player) {
    return armor.hasTagCompound() && armor.stackTagCompound.hasKey("goggles");
  }

  @Override
  public boolean showIngamePopups(@NotNull ItemStack armor, EntityLivingBase player) {
    return armor.hasTagCompound() && armor.stackTagCompound.hasKey("goggles");
  }

  @Override
  @SideOnly(Side.CLIENT)

  public void registerIcons(@NotNull IIconRegister register) {
	icons = new IIcon[3];
    icons[0] = register.registerIcon("thaumrev:armor/thauminitefortresshelm");
    icons[1] = register.registerIcon("thaumrev:armor/thauminitefortresschest");
    icons[2] = register.registerIcon("thaumrev:armor/thauminitefortresslegs");
  }

  @Override
  @SideOnly(Side.CLIENT)
  public IIcon getIconFromDamage(int par1) {
    return this.icons[this.armorType];
  }

  @Override
  @SideOnly(Side.CLIENT)
  public ModelBiped getArmorModel(EntityLivingBase entity, @NotNull ItemStack armor, int armorSlot) {
    int type = ((ItemArmor) armor.getItem()).armorType;
    models = new ModelBiped[3];
    if (this.models[1] == null) {
      this.models[1] = new ModelThauminiteFortressArmor(1.0F);
    }

    if (this.models[2] == null) {
      this.models[2] = new ModelThauminiteFortressArmor(0.5F);
    }

    if (type != 1 && type != 3) {
      this.models[0] = this.models[2];
    } else {
      this.models[0] = this.models[1];
    }

    if (this.models[0] != null) {
      this.models[0].bipedHead.showModel = armorSlot == 0;
      this.models[0].bipedHeadwear.showModel = armorSlot == 0;
      this.models[0].bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
      this.models[0].bipedRightArm.showModel = armorSlot == 1;
      this.models[0].bipedLeftArm.showModel = armorSlot == 1;
      this.models[0].bipedRightLeg.showModel = armorSlot == 2;
      this.models[0].bipedLeftLeg.showModel = armorSlot == 2;
      this.models[0].isSneak = entity.isSneaking();
      this.models[0].isRiding = entity.isRiding();
      this.models[0].isChild = entity.isChild();
      this.models[0].aimedBow = false;
      this.models[0].heldItemRight = entity.getHeldItem() != null ? 1 : 0;
      if (entity instanceof EntityPlayer && ((EntityPlayer)entity).getItemInUseDuration() > 0) {
        EnumAction enumaction = ((EntityPlayer)entity).getItemInUse().getItemUseAction();
        if (enumaction == EnumAction.block) {
          this.models[0].heldItemRight = 3;
        } else if (enumaction == EnumAction.bow) {
          this.models[0].aimedBow = true;
        }
      }
    }

    return this.models[0];
  }

  @Override
  @SideOnly(Side.CLIENT)
  public String getArmorTexture(ItemStack armor, Entity entity, int slot, String type) {
    return "thaumrev:textures/models/thauminite_fortress_armor.png";
  }

}
