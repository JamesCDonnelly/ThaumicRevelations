package thaumrev.item.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumrev.ThaumRevLibrary;

import java.util.List;

public class ItemWardenclothArmor extends ItemArmor implements IRepairable, IVisDiscountGear, IRunicArmor {

    public IIcon iconHelm;
    public IIcon iconChest;
    public IIcon iconLegs;
    public IIcon iconBoots;

    public ItemWardenclothArmor(int type, String name) {
        super(ThaumRevLibrary.armorMaterialWardencloth, 2, type);
        setUnlocalizedName(name);
        setCreativeTab(ThaumRevLibrary.tabThaumRev);
        setMaxStackSize(1);
    }

    /** Overrides - void **/
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + ": "
                + this.getVisDiscount(stack, player, (Aspect)null) + "%");
    }


    /** Overrides - boolean **/
    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }


    /** Overrides - int **/
    @Override
    public int getRunicCharge(ItemStack itemStack) {
        return 0;
    }

    @Override
    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer, Aspect aspect) {
        return 5;
    }


    /** Overrides - EnumRarity **/
    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.uncommon;
    }


    /** Client-side **/
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        iconHelm = register.registerIcon("thaumrev:armor/wardenclothhelm");
        iconChest = register.registerIcon("thaumrev:armor/wardenclothchest");
        iconLegs = register.registerIcon("thaumrev:armor/wardenclothlegs");
        iconBoots = register.registerIcon("thaumrev:armor/wardenclothboots");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        return this.armorType == 0 ? this.iconHelm : (this.armorType == 1 ? this.iconChest : (this.armorType == 2 ? this.iconLegs : this.iconBoots));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "thaumrev:textures/models/wardencloth_" + (this.armorType == 2 ? "2" : "1") + ".png";
    }
}
