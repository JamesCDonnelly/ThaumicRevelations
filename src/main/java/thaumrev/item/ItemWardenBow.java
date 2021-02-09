package thaumrev.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import thaumcraft.api.aspects.Aspect;
import thaumrev.ThaumRevLibrary;
import thaumrev.util.wardenic.WardenicChargeHelper;

import java.util.List;

public class ItemWardenBow extends ItemBow {

    public static final String[] wardenBowPullArray = new String[] {"pulling_0", "pulling_1", "pulling_2"};

    public ItemWardenBow() {
        super();
        setUnlocalizedName("itemWardenBow");
        setCreativeTab(ThaumRevLibrary.tabThaumRev);
        setMaxStackSize(1);
        setFull3D();
    }


    /** Overrides - void **/
    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int useTime) {
        int air = 0;

        for (int i = 0; i < 4; i++) {
            if ((player.getCurrentArmor(i) != null) &&
                    WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
                            .equals(Aspect.AIR.getName())) {
                air++;
            }
        }

        int chargeTime = this.getMaxItemUseDuration(stack) - useTime;

        ArrowLooseEvent looseEvent = new ArrowLooseEvent(player, stack, chargeTime);
        MinecraftForge.EVENT_BUS.post(looseEvent);

        if (looseEvent.isCanceled()) {
            return;
        }

        chargeTime = looseEvent.charge;

        float f = (float) chargeTime / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;

        if (f < 0.1F) {
            return;
        }

        if (f > 1.0F) {
            f = 1.0F;
        }

        EntityArrow entityArrow = new EntityArrow(world, player, f * 2.0F);

        if (f == 1.0F) {
            entityArrow.setIsCritical(true);
        }

        world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

        entityArrow.canBePickedUp = 2;

        NBTTagCompound tag = entityArrow.getEntityData();
        tag.setBoolean("WardenArrow", true);

        if (entityArrow.getIsCritical()) {
            if (WardenicChargeHelper.getUpgrade(player.getEquipmentInSlot(0)).getUpgradeAspect()
                    .equals(Aspect.AIR.getName())) {
                entityArrow.setDamage(3 * (air + 2));
            } else if (WardenicChargeHelper.getUpgrade(player.getEquipmentInSlot(0)).getUpgradeAspect()
                    .equals(Aspect.ENTROPY.getName())) {
                entityArrow.setDamage(0);
            }
        }

        if (!world.isRemote) {
            world.spawnEntityInWorld(entityArrow);
        }

        stack.setItemDamage(0);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("tooltip.wardenic.upgrade") + ": " + WardenicChargeHelper.getUpgrade(stack).getQuote());

        super.addInformation(stack, player, list, par4);
    }


    /** Overrides - boolean **/
    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean getShareTag() {
        return true;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }


    /** Overrides - int **/
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 72000;
    }


    /** Overrides - ItemStack **/
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        ArrowNockEvent event = new ArrowNockEvent(player, stack);
        MinecraftForge.EVENT_BUS.post(event);

        if (event.isCanceled()) {
            return event.result;
        }

        player.setItemInUse(stack, this.getMaxItemUseDuration(stack));

        return stack;
    }


    /** Overrides - EnumRarity **/
    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }


    /** Overrides - EnumAction **/
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }


    /** Client-side **/
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (usingItem == null) { return itemIcon; }
        int ticksInUse = stack.getMaxItemUseDuration() - useRemaining;

        if (ticksInUse > 14) {
            return iconArray[2];
        } else if (ticksInUse > 7) {
            return iconArray[1];
        } else if (ticksInUse > 0) {
            return iconArray[0];
        } else {
            return itemIcon;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        itemIcon = register.registerIcon("thaumrev:bow/wardenbow");

        iconArray = new IIcon[wardenBowPullArray.length];

        for (int i = 0; i < iconArray.length; i++) {
            iconArray[i] = register.registerIcon("thaumrev:bow/wardenbow" +  "_" + wardenBowPullArray[i]);
        }
    }
}
