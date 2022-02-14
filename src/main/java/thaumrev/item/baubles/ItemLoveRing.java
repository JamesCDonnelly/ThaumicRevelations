package thaumrev.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import thaumrev.ThaumRevLibrary;

public class ItemLoveRing extends Item implements IBauble {

  public ItemLoveRing() {
    super();
    setUnlocalizedName("itemLoveRing");
    setCreativeTab(ThaumRevLibrary.tabThaumRev);
    setMaxStackSize(1);
  }


  /** Overrides - void **/
  @Override
  public void onWornTick(ItemStack stack, EntityLivingBase entityLivingBase) {}

  @Override
  public void onEquipped(ItemStack stack, EntityLivingBase entityLivingBase) {
    entityLivingBase.worldObj.playSoundAtEntity(entityLivingBase, "thaumrev:abderp", 1, 1);
    double maxHealth = entityLivingBase.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue();
    entityLivingBase.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth * 1.5D);
  }

  @Override
  public void onUnequipped(ItemStack stack, EntityLivingBase entityLivingBase) {}


  /** Overrides - boolean **/
  @Override
  public boolean canEquip(ItemStack stack, EntityLivingBase entityLivingBase) {
    InventoryBaubles baubles = PlayerHandler.getPlayerBaubles((EntityPlayer) entityLivingBase);

    for (int i = 0; i < baubles.getSizeInventory(); i++) {
      if (baubles.getStackInSlot(i) != null &&
        baubles.getStackInSlot(i).getItem() instanceof ItemLoveRing) {
        return false;
      }
    }

    return true;
  }

  @Override
  public boolean canUnequip(ItemStack stack, EntityLivingBase entityLivingBase) {
    return false;
  }


  /** Overrides - BaubleType **/
  @Override
  public BaubleType getBaubleType(ItemStack stack) {
    return BaubleType.RING;
  }


  /**
   * Overrides - ItemStack
   **/
  @Override
  public ItemStack onItemRightClick(ItemStack stack, @NotNull World world, EntityPlayer player) {
    world.playSoundAtEntity(player, "thaumrev:abderp", 1, 1);
    return super.onItemRightClick(stack, world, player);
  }


  /** Overrides - EnumRarity **/
  @Override
  public EnumRarity getRarity(ItemStack par1ItemStack) {return EnumRarity.epic;}


  /** Client-side **/
  @Override
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister register) {
    itemIcon = register.registerIcon("thaumrev:lovering");
  }
}
