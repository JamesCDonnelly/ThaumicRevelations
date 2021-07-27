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
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.ISpecialArmor;
import thaumcraft.api.*;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.IRevealer;
import thaumrev.ThaumRevLibrary;
import thaumrev.client.renderers.ModelCrimsonHat;

import java.util.List;

public class ItemCrimsonHat extends ItemArmor implements ISpecialArmor, IRepairable, IRevealer, IGoggles, IRunicArmor, IVisDiscountGear, IWarpingGear {

    ModelBiped model = null;

    public ItemCrimsonHat() {
        super(ThaumRevLibrary.armorMaterialCrimsoncloth, 1, 0);
        setUnlocalizedName("itemCrimsonHat");
        setCreativeTab(ThaumRevLibrary.tabThaumRev);
        setMaxStackSize(1);
    }

    /** Overrides - void **/
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + ": " +
                this.getVisDiscount(stack, player, null) + "%");
    }

    @Override
    public void damageArmor(EntityLivingBase entityLivingBase, ItemStack itemStack, DamageSource damageSource, int i, int i1) {
    }


    /** Overrides - boolean **/
    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.isItemEqual(new ItemStack(ThaumRevLibrary.itemResource, 1, 4));
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }

    @Override
    public boolean showNodes(ItemStack stack, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    public boolean showIngamePopups(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }


    /** Overrides - int **/
    @Override
    public int getRunicCharge(ItemStack itemStack) {
        return 4;
    }

    @Override
    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer, Aspect aspect) {
        return 2;
    }

    @Override
    public int getWarp(ItemStack itemStack, EntityPlayer entityPlayer) {
        return 4;
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return getArmorMaterial().getDamageReductionAmount(slot);
    }


    /** Overrides - EnumRarity **/
    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.rare;
    }


    /** Overrides - ArmorProperties **/
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        return new ArmorProperties(0, getArmorMaterial().getDamageReductionAmount(slot) / 25D, 20);
    }


    /** Client-side **/
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        itemIcon = register.registerIcon("thaumrev:armor/crimsonhat");
    }


    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemstack, int armorSlot) {

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
