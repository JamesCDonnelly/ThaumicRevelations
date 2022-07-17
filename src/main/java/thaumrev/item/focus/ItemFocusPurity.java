package thaumrev.item.focus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumrev.ThaumRevLibrary;
import thaumrev.entity.EntityPurity;

import static thaumrev.ThaumRevConfig.*;

public class ItemFocusPurity extends ItemFocusBasic {

	private IIcon depth, orn;

	public ItemFocusPurity() {
		super();
		setUnlocalizedName("itemFocusPurity");
		setCreativeTab(ThaumRevLibrary.tabThaumRev);
	}

	/** Overrides - int **/
	@Override
	public int getActivationCooldown(ItemStack focus) {
		int enlargeMod = getUpgradeLevel(focus, FocusUpgradeType.enlarge) * 4;
		int potencyMod = getUpgradeLevel(focus, FocusUpgradeType.potency) / 2;
		int cooldown = basePurityCooldown * (potencyMod + enlargeMod - 1);
		return cooldown > 0 ? cooldown : basePurityCooldown;
	}

	/** Overrides - String **/
	@Override
	public String getSortingHelper(ItemStack focusStack) {
		return "PURITY";
	}


	/** Overrides - ItemStack **/
	@Override
	public ItemStack onFocusRightClick(@NotNull ItemStack focus, World world, EntityPlayer player, MovingObjectPosition mop) {
		ItemWandCasting wand = (ItemWandCasting) focus.getItem();
		EntityPurity purityOrb = new EntityPurity(world, player, getUpgradeLevel(focus, FocusUpgradeType.enlarge), getUpgradeLevel(focus, FocusUpgradeType.potency));

		if (!world.isRemote) {
			if (wand.consumeAllVis(focus, player, getVisCost(focus), true, false)) {
				world.spawnEntityInWorld(purityOrb);
				world.playSoundAtEntity(purityOrb, "thaumcraft:ice", 0.3F, 0.8F + world.rand.nextFloat() * 0.1F);
			}
		}

		player.swingItem();
		return focus;
	}

	/** Overrides - AspectList **/
	@Override
	public AspectList getVisCost(ItemStack focus) {
		int enlargeMod = getUpgradeLevel(focus, FocusUpgradeType.enlarge) * 8;
		int potencyMod = getUpgradeLevel(focus, FocusUpgradeType.potency) / 2;
		int amount = basePurityVisCost * (potencyMod + enlargeMod - 1);
		int finalAmount = amount > 0 ? amount : basePurityVisCost;
		return new AspectList()
			.add(Aspect.AIR, basePurityVisCost)
			.add(Aspect.EARTH, basePurityVisCost * (enlargeMod))
			.add(Aspect.WATER, finalAmount)
			.add(Aspect.ORDER, basePurityVisCost * (enlargeMod + 1))
			.add(Aspect.ENTROPY, basePurityVisCost / 2 * potencyMod);
	}


	/** Overrides - FocusUpgradeType **/
	@Override
	public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack focus, int rank) {
		switch (rank) {
			case 1:
			case 3:
			case 5:
				return new FocusUpgradeType[]{FocusUpgradeType.frugal};
			case 2:
			case 4:
				return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge};
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
		orn = register.registerIcon("thaumrev:focus/purityorn");
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

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getOrnament(ItemStack itemstack) {
		return orn;
	}
}
