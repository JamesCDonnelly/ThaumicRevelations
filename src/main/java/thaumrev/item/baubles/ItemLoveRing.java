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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import thaumrev.config.ConfigLibrary;
import thaumrev.lib.utils.AttributeHelper;

import static thaumrev.config.ConfigLibrary.ATTRIBUTE_MODIFIER_UUID;

import java.util.UUID;

public class ItemLoveRing extends Item implements IBauble {

  public ItemLoveRing() {
    super();
    setUnlocalizedName("itemLoveRing");
    setCreativeTab(ConfigLibrary.tabThaumRev);
    setMaxStackSize(1);
  }


  /** Overrides - void **/
  @Override
  public void onWornTick(ItemStack stack, @NotNull EntityLivingBase entityLivingBase) {
    entityLivingBase.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 0, 0));
  }

  @Override
  public void onEquipped(ItemStack stack, @NotNull EntityLivingBase entityLivingBase) {
    entityLivingBase.worldObj.playSoundAtEntity(entityLivingBase, "thaumrev:abderp", 1, 1);

    AttributeHelper.addAttributeModToLiving(
      entityLivingBase,
      SharedMonsterAttributes.maxHealth,
      new UUID(ATTRIBUTE_MODIFIER_UUID, 1),
      "TR_KNOCKBACK_RESISTANCE",
      5F,
      0
    );
  }

  @Override
  public void onUnequipped(ItemStack stack, @NotNull EntityLivingBase entityLivingBase) {
    entityLivingBase.worldObj.playSoundAtEntity(entityLivingBase, "thaumrev:abderp", 1, 1);
    
    AttributeHelper.removeAttributeModFromLiving(
      entityLivingBase,
      SharedMonsterAttributes.maxHealth,
      new UUID(ATTRIBUTE_MODIFIER_UUID, 1),
      "TR_KNOCKBACK_RESISTANCE",
      5F,
      0
    );
  }


  /** Overrides - boolean **/
  @Override
  public boolean canEquip(ItemStack stack, EntityLivingBase entityLivingBase) {
    InventoryBaubles baubles = PlayerHandler.getPlayerBaubles((EntityPlayer) entityLivingBase);

    for (int i = 0; i < baubles.getSizeInventory(); ++i) {
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
  public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
    world.playSoundAtEntity(player, "thaumrev:abderp", 1, 1);
    return super.onItemRightClick(stack, world, player);
  }


  /** Overrides - EnumRarity **/
  @Override
  public EnumRarity getRarity(ItemStack stack) {
    return EnumRarity.epic;
  }


  /** Client-side **/
  @Override
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister register) {
    itemIcon = register.registerIcon("thaumrev:baubles/lovering");
  }
}
