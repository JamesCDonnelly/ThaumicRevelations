package thaumrev.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;
import org.jetbrains.annotations.NotNull;
import thaumrev.config.ConfigLibrary;

public class ItemExcubituraSeeds extends ItemSeeds {

  public ItemExcubituraSeeds() {
      super(ConfigLibrary.blockExcubitura, Blocks.farmland);
      setUnlocalizedName("itemExcubituraSeeds");
      setCreativeTab(ConfigLibrary.tabThaumRev);
  }


  /* Overrides - boolean */
  @Override
  public boolean onItemUse(ItemStack stack, EntityPlayer player,
    World world, int x, int y, int z, int par7, float hitX,
    float hitY, float hitZ) {

    if (par7 != 1) {
      return false;
    }

    else if (player.canPlayerEdit(x, y + 1, z, par7, stack)) {
      if (world.getBlock(x, y, z).canSustainPlant(world,
          x, y, z, ForgeDirection.UP, this) && world
            .isAirBlock(x, y + 1, z)) {
          world.setBlock(x, y + 1, z, ConfigLibrary.blockExcubitura);
          --stack.stackSize;
          return true;
        }
      else {
        return false;
      }
    } else {
      return false;
    }
  }


  /* Overrides - int */
  @Override
  public int getPlantMetadata(IBlockAccess world, int x, int y, int z) { return 0; }


  /* Overrides - Block */
  @Override
  public Block getPlant(IBlockAccess world, int x, int y, int z) {
    return ConfigLibrary.blockExcubitura;
  }


  /* Overrides - EnumPlantType */
  @Override
  public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
      return EnumPlantType.Crop;
  }


  /* Client-side */
  @Override
  @SideOnly(Side.CLIENT)
  public void registerIcons(@NotNull IIconRegister register) {
    itemIcon = register.registerIcon("thaumrev:roseseeds");
  }
}
