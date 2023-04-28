package thaumrev.item.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.*;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.IRevealer;
import thaumrev.config.ConfigLibrary;
import thaumrev.client.models.ModelCrimsonHat;

import java.util.List;

public class ItemCrimsonHat extends ItemArmor implements IRepairable, IRevealer, IGoggles, IRunicArmor, IVisDiscountGear, IWarpingGear {

  @SideOnly(Side.CLIENT)
  ModelBiped model;

  public ItemCrimsonHat() {
    super(ConfigLibrary.armorMaterialCrimsoncloth, 1, 0);
    setUnlocalizedName("itemCrimsonHat");
    setCreativeTab(ConfigLibrary.tabThaumRev);
  }

  /** Overrides - void **/
  @Override
  public void addInformation(ItemStack stack, EntityPlayer player, @NotNull List list, boolean doit) {
    list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + ": " +
      this.getVisDiscount(stack, player, null) + "%");

    super.addInformation(stack, player, list, doit);
  }

  /** Overrides - boolean **/
  @Override
  public boolean getIsRepairable(ItemStack stack, @NotNull ItemStack material) {
    return material.isItemEqual(new ItemStack(ConfigLibrary.itemResource, 1, 4));
  }

  @Override
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) { return true; }

  @Override
  public boolean showNodes(ItemStack stack, EntityLivingBase entity) { return true; }

  @Override
  public boolean showIngamePopups(ItemStack stack, EntityLivingBase entity) { return true; }


  /** Overrides - int **/
  @Override
  public int getRunicCharge(ItemStack itemStack) { return 4; }

  @Override
  public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer, Aspect aspect) { return 2; }

  @Override
  public int getWarp(ItemStack itemStack, EntityPlayer entityPlayer) { return 4; }

  /** Overrides - EnumRarity **/
  @Override
  public EnumRarity getRarity(ItemStack itemstack) { return EnumRarity.rare; }

  /** Client-side **/
  @Override
  @SideOnly(Side.CLIENT)
  public void registerIcons(@NotNull IIconRegister register) {
    itemIcon = register.registerIcon("thaumrev:armor/crimsonhat");
  }

  @Override
  @SideOnly(Side.CLIENT)
  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemstack, int armorSlot) {
	  model = null;
    if(this.model == null) {
      this.model = new ModelCrimsonHat();
    }

    return this.model;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "thaumrev:textures/models/crimsonhat.png";
  }
}
