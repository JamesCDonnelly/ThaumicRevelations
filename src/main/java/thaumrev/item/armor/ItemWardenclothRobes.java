package thaumrev.item.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.config.ConfigItems;
import thaumrev.config.ConfigLibrary;

import java.util.List;

public class ItemWardenclothRobes extends ItemArmor implements IRepairable, IVisDiscountGear, IRunicArmor {
  public IIcon[] icons = new IIcon[4];
  public IIcon[] overlays = new IIcon[4];

  public ItemWardenclothRobes(int type, String name) {
    super(ConfigLibrary.armorMaterialWardencloth, 2, type);
    setUnlocalizedName(name);
    setCreativeTab(ConfigLibrary.tabThaumRev);
  }

  /* Overrides - void */
  @Override
  public void addInformation(@NotNull ItemStack armor, EntityPlayer player, List list, boolean par4) {
    if (armor.hasTagCompound() && armor.stackTagCompound.hasKey("goggles")) {
      list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("item.ItemGoggles.name"));
      list.add("");
    }

    list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + ": "
      + this.getVisDiscount(armor, player, null) + "%");
  }

  @Override
  public void removeColor(@NotNull ItemStack armor) {
    if (!armor.hasTagCompound()) return;

    NBTTagCompound displayCompound = armor.stackTagCompound.getCompoundTag("display");

    if (displayCompound.hasKey("color")) {
      displayCompound.removeTag("color");
    }
  }

  @Override
  public void func_82813_b(@NotNull ItemStack armor, int color) {
    if (!armor.hasTagCompound()) {
      armor.setTagCompound(new NBTTagCompound());
    }

    NBTTagCompound displayCompound = armor.stackTagCompound.getCompoundTag("display");

    if (!armor.stackTagCompound.hasKey("display")) {
      armor.stackTagCompound.setTag("display", displayCompound);
    }

    displayCompound.setInteger("color", color);
  }

  /* Overrides - boolean */
  @SideOnly(Side.CLIENT)
  public boolean requiresMultipleRenderPasses() {
    return true;
  }

  @Override
  public boolean isBookEnchantable(ItemStack armor, ItemStack book) {
      return true;
  }

  @Override
  public boolean onItemUseFirst(ItemStack armor, EntityPlayer player, @NotNull World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (!world.isRemote && world.getBlock(x, y, z) == Blocks.cauldron && world.getBlockMetadata(x, y, z) > 0) {
      this.removeColor(armor);

      world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) - 1, 2);
      world.notifyBlockChange(x, y, z, Blocks.cauldron);
      return true;
    } else {
      return super.onItemUseFirst(armor, player, world, x, y, z, side, hitX, hitY, hitZ);
    }
  }

  @Override
  public boolean getIsRepairable(@NotNull ItemStack armor, ItemStack item) {
    return armor.isItemEqual(new ItemStack(ConfigItems.itemResource, 1, 3)) || super.getIsRepairable(armor, item);
  }

  @Override
  public boolean hasColor(ItemStack armor) {
    return true;
  }


  /* Overrides - int */
  @Override
  public int getRunicCharge(ItemStack armor) {
      return 0;
  }

  @Override
  public int getVisDiscount(ItemStack armor, EntityPlayer player, Aspect aspect) {
    return this.armorType == 3 ? 3 : 6;
  }

  @Override
  public int getColor(@NotNull ItemStack armor) {
    if (!armor.hasTagCompound()) {
      return 3680614;
    }

    NBTTagCompound displayCompound = armor.stackTagCompound.getCompoundTag("display");

    return displayCompound == null
      ? 3680614
      : (
      displayCompound.hasKey("color")
        ? displayCompound.getInteger("color")
        : 3680614
    );
  }


  /* Overrides - EnumRarity */
  @Override
  public EnumRarity getRarity(ItemStack armor) {
      return EnumRarity.uncommon;
  }


  /* Client-side */
  @Override
  @SideOnly(Side.CLIENT)
  public void registerIcons(@NotNull IIconRegister register) {
    this.icons[1] = register.registerIcon("thaumrev:armor/wardenclothchestcolor");
    this.icons[2] = register.registerIcon("thaumrev:armor/wardenclothlegscolor");
    this.icons[3] = register.registerIcon("thaumrev:armor/wardenclothbootscolor");

    this.overlays[1] = register.registerIcon("thaumrev:armor/wardenclothchestoverlay");
    this.overlays[2] = register.registerIcon("thaumrev:armor/wardenclothlegsoverlay");
    this.overlays[3] = register.registerIcon("thaumrev:armor/wardenclothbootsoverlay");
  }

  @Override
  @SideOnly(Side.CLIENT)
  public IIcon getIconFromDamageForRenderPass(int damage, int type) {
    return type == 0 ? this.icons[this.armorType] : this.overlays[this.armorType];
  }

  @Override
  @SideOnly(Side.CLIENT)
  public String getArmorTexture(ItemStack armor, Entity entity, int slot, String type) {
    // TODO: Wardencloth Helmet
    return "thaumrev:textures/models/wardencloth_" + (this.armorType == 2 ? "2" : "1") + (type == null ? "" : "_overlay") + ".png";
  }
}
