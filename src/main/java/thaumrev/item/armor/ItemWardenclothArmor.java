package thaumrev.item.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import thaumcraft.api.IGoggles;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.IRevealer;
import thaumrev.common.ThaumRevLibrary;

import java.util.List;

import static thaumrev.common.ThaumRevLibrary.itemWardenclothHelm;

public class ItemWardenclothArmor extends ItemArmor implements IVisDiscountGear, IRevealer, IGoggles {

    public ItemWardenclothArmor(int type, String name) {
        super(ThaumRevLibrary.armorMaterialWardencloth, 2, type);
        setUnlocalizedName(name);
        setCreativeTab(ThaumRevLibrary.tabThaumRev);
        setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        String name = "";

        switch(this.armorType) {
            case 0:
                name = "wardenclothhelm";
            case 1:
                name = "wardenclothchest";
            case 2:
                name = "wardenclothlegs";
            case 3:
                name = "wardenclothboots";
        }

        itemIcon = register.registerIcon("thaumrev:armor/" + name + ".png");
    }

    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.uncommon;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "thaumrev:textures/models/" + (this.armorType == 2 ? "2" : "1") + ".png";
    }

    @Override
    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer, Aspect aspect) {
        return 6;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }

    @Override
    public boolean showIngamePopups(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        if (itemStack.getItem().equals(itemWardenclothHelm)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean showNodes(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        if (itemStack.getItem().equals(itemWardenclothHelm)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + ": " + this.getVisDiscount(stack, player, (Aspect)null) + "%");
    }
}
