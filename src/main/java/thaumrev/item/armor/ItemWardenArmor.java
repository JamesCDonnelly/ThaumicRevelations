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
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumrev.config.ConfigLibrary;

public class ItemWardenArmor extends ItemArmor implements IRepairable, ISpecialArmor, IRunicArmor {
  public IIcon[] icons = new IIcon[4];

  public ItemWardenArmor(int type, String name) {
    super(ConfigLibrary.armorMaterialWarden, 2, type);
    setUnlocalizedName(name);
    setCreativeTab(ConfigLibrary.tabThaumRev);
  }

  /* Overrides - void */
  @Override
  public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
    super.onArmorTick(world, player, stack);
    /*
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
     */
  }


  @Override
  public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
  }

  /* Overrides - boolean */
  // @Override
  // public boolean getShareTag() {
  //   return true;
  // }

  @Override
  public boolean isBookEnchantable(ItemStack armor, ItemStack book) { return false; }

  @Override
  public boolean isDamageable() {
    return false;
  }

  /* Overrides - int */
  @Override
  public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
    return getArmorMaterial().getDamageReductionAmount(slot);
  }

  @Override
  public int getRunicCharge(ItemStack armor) {
    return 2;
  }

  /* Overrides - EnumRarity */
  @Override
  public EnumRarity getRarity(ItemStack stack) { return EnumRarity.epic; }


  /* Overrides - ArmorProperties */
  @Override
  public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
    int priority = 1;
    double ratio = this.damageReduceAmount / 25.0D;

    if (source.isMagicDamage()) {
      ratio = this.damageReduceAmount / 20.0D;
    }

    if (player instanceof EntityPlayer) {
      double set = 1.0D;

      for (ItemStack piece : ((EntityPlayer) player).inventory.armorInventory) {
        if (piece != null && piece.getItem() instanceof ItemWardenArmor) {
          set += 0.25D;
        }
      }

      ratio *= set;
    }

    return new ArmorProperties(priority, ratio, armor.getMaxDurability());
  }


  /* Client-side */
  @Override
  @SideOnly(Side.CLIENT)
  public void registerIcons(@NotNull IIconRegister register) {
    this.icons[0] = register.registerIcon("thaumrev:armor/wardenhelm");
    this.icons[1] = register.registerIcon("thaumrev:armor/wardenchest");
    this.icons[2] = register.registerIcon("thaumrev:armor/wardenlegs");
    this.icons[3] = register.registerIcon("thaumrev:armor/wardenboots");
  }

  @Override
  @SideOnly(Side.CLIENT)
  public IIcon getIconFromDamage(int par1) {
    return this.icons[this.armorType];
  }

  @Override
  @SideOnly(Side.CLIENT)
  public String getArmorTexture(ItemStack armor, Entity entity, int slot, String type) {
    return "thaumrev:textures/models/warden_" + (this.armorType == 2 ? "2" : "1") + ".png";
  }
}
