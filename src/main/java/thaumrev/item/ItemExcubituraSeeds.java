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
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import thaumrev.common.ThaumRevLibrary;

public class ItemExcubituraSeeds extends ItemSeeds implements IPlantable {

    public ItemExcubituraSeeds() {
        super(ThaumRevLibrary.blockExcubitura, Blocks.farmland);
        setUnlocalizedName("itemExcubituraSeeds");
        setCreativeTab(ThaumRevLibrary.tabThaumRev);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        itemIcon = register.registerIcon("thaumrev:roseseeds");
    }


    @Override
    public boolean onItemUse(ItemStack parItemStack, EntityPlayer parPlayer,
                             World parWorld, int parX, int parY, int parZ, int par7, float par8,
                             float par9, float par10) {
        // not sure what this parameter does, copied it from potato
        if (par7 != 1) {
            return false;
        }
        else if (parPlayer.canPlayerEdit(parX, parY+1, parZ, par7, parItemStack)) {
            if (parWorld.getBlock(parX, parY, parZ).canSustainPlant(parWorld,
                    parX, parY, parZ, ForgeDirection.UP, this) && parWorld
                    .isAirBlock(parX, parY+1, parZ)) {
                parWorld.setBlock(parX, parY+1, parZ, ThaumRevLibrary.blockExcubitura);
                --parItemStack.stackSize;
                return true;
            }
            else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        return EnumPlantType.Crop;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z)
    {
        return ThaumRevLibrary.blockExcubitura;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
    {
        return 0;
    }
}
