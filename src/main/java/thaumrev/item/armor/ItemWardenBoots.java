package thaumrev.item.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumrev.util.wardenic.upgrade.WardenicUpgradeAir;

public class ItemWardenBoots extends ItemWardenArmor {

	public ItemWardenBoots() {
		super(3);
		setUnlocalizedName("itemWardenBoots");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon("thaumrev:armor/wardenboots");
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return "thaumrev:textures/models/warden_1.png";
	}

}
