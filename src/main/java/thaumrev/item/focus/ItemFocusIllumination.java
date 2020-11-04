package thaumrev.item.focus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumrev.ThaumRevLibrary;

public class ItemFocusIllumination extends ItemFocusBasic {

	private IIcon depth;

	public ItemFocusIllumination() {
		super();
		setUnlocalizedName("itemFocusIllumination");
		setCreativeTab(ThaumRevLibrary.tabThaumRev);
	}


	/** Overrides - int **/
	@Override
	public int getActivationCooldown(ItemStack stack) {
		return 500;
	}


	/** Overrides - String **/
	@Override
	public String getSortingHelper(ItemStack itemStack) {
		return "ILLUMINATION";
	}


	/** Overrides - ItemStack **/
	@Override
	public ItemStack onFocusRightClick(ItemStack stack, World world, EntityPlayer player, MovingObjectPosition mop) {
		ItemWandCasting wand = (ItemWandCasting) stack.getItem();
		if (mop != null) {
			if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
				if (!world.isRemote) {
					if (wand.consumeAllVis(stack, player, getVisCost(stack), true, false)) {
						int x = mop.blockX;
						int y = mop.blockY;
						int z = mop.blockZ;

						if (mop.sideHit == 0) {
							if (!(world.getBlock(x, y, z).equals(Blocks.water))) y--;
						}
						if (mop.sideHit == 1) {
							if (!(world.getBlock(x, y, z).equals(Blocks.water))) y++;
						}
						if (mop.sideHit == 2) {
							z--;
						}
						if (mop.sideHit == 3) {
							z++;
						}
						if (mop.sideHit == 4) {
							x--;
						}
						if (mop.sideHit == 5) {
							x++;
						}
						if (world.getBlock(x, y, z).equals(Blocks.air) ||
								world.getBlock(x, y, z).equals(Blocks.water)) {
							world.setBlock(x, y, z, ThaumRevLibrary.blockWitor, 0, 2);
						}
					}
				}
			}
		}
		player.swingItem();
		return stack;
	}


	/** Overrides - AspectList **/
	@Override
	public AspectList getVisCost(ItemStack itemstack) {
		return new AspectList().add(Aspect.AIR, 50).add(Aspect.FIRE, 50);
	}


	/** Overrides - FocusUpgradeType **/
	@Override
	public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemStack, int rank) {
		switch(rank) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				return new FocusUpgradeType[] {
						FocusUpgradeType.frugal
				};
			default:
				return null;
		}
	}


	/** Client-side **/
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		icon = register.registerIcon("thaumrev:focus/purityfocus");
		depth = register.registerIcon("thaumrev:focus/puritydepth");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getFocusColor(ItemStack itemstack) {
		return 0x6698FF;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getFocusDepthLayerIcon(ItemStack itemstack) {
		return depth;
	}
}
