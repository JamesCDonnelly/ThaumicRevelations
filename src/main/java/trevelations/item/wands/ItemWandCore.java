package trevelations.item.wands;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.StaffRod;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.ItemWandRod;
import trevelations.common.ThaumRevLibrary;

import java.util.List;

public class ItemWandCore extends ItemWandRod {

    public IIcon[] iconWand = new IIcon[3];
    public IIcon[] iconStaff = new IIcon[3];

    public ItemWandCore() {
        setMaxStackSize(64);
        setHasSubtypes(true);
        setUnlocalizedName("itemWandCore");
        setMaxDamage(0);
        setCreativeTab(ThaumRevLibrary.tabThaumRev);
    }

    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + stack.getItemDamage();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        iconWand[0] = register.registerIcon("trevelations:wands/wand_rod_wardencloth");
        iconWand[1] = register.registerIcon("trevelations:wands/wand_rod_voidwood");
        iconWand[2] = register.registerIcon("trevelations:wands/wand_rod_crimsoncrystal");
        iconStaff[0] = register.registerIcon("trevelations:wands/staff_rod_wardencloth");
        iconStaff[1] = register.registerIcon("trevelations:wands/staff_rod_voidwood");
        iconStaff[2] = register.registerIcon("trevelations:wands/staff_rod_crimsoncult");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return meta < 10 ? this.iconWand[meta] : this.iconStaff[meta - 10];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 6; i++) {
            list.add(new ItemStack(this, 1, (i < 3 ? i : i + 7)));
        }

        ItemStack wand;

        wand = new ItemStack(ConfigItems.itemWandCasting, 1, 400);
        ((ItemWandCasting)wand.getItem()).setCap(wand, WandCap.caps.get("thaumium"));
        ((ItemWandCasting)wand.getItem()).setRod(wand, WandRod.rods.get("wardencloth"));
        ((ItemWandCasting)wand.getItem()).storeAllVis(
            wand, new AspectList()
                .add(Aspect.FIRE, 15000)
                .add(Aspect.WATER, 15000)
                .add(Aspect.EARTH, 15000)
                .add(Aspect.AIR, 15000)
                .add(Aspect.ORDER, 15000)
                .add(Aspect.ENTROPY, 15000));
        list.add(wand);

        /*wand = new ItemStack(ConfigItems.itemWandCasting, 1, 401);
        ((ItemWandCasting) wand.getItem()).setCap(wand, WandCap.caps.get("void"));
        ((ItemWandCasting) wand.getItem()).setRod(wand, WandRod.rods.get("voidwood"));
        list.add(wand);

        wand = new ItemStack(ConfigItems.itemWandCasting, 1, 402);
        ((ItemWandCasting) wand.getItem()).setCap(wand, WandCap.caps.get("void"));
        ((ItemWandCasting) wand.getItem()).setRod(wand, WandRod.rods.get("crimsoncrystal"));
        list.add(wand);
         */

        wand = new ItemStack(ConfigItems.itemWandCasting, 1, 500);
        ((ItemWandCasting) wand.getItem()).setCap(wand, WandCap.caps.get("thaumium"));
        ((ItemWandCasting) wand.getItem()).setRod(wand, StaffRod.rods.get("wardencloth"));
        list.add(wand);

        /*wand = new ItemStack(ConfigItems.itemWandCasting, 1, 501);
        ((ItemWandCasting) wand.getItem()).setCap(wand, WandCap.caps.get("void"));
        ((ItemWandCasting) wand.getItem()).setRod(wand, StaffRod.rods.get("voidwood_staff"));
        list.add(wand);

        wand = new ItemStack(ConfigItems.itemWandCasting, 1, 502);
        ((ItemWandCasting) wand.getItem()).setCap(wand, WandCap.caps.get("void"));
        ((ItemWandCasting) wand.getItem()).setRod(wand, StaffRod.rods.get("crimsoncult_staff"));
        list.add(wand);*/
    }
}