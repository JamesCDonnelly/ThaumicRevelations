package thaumrev.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.IEssentiaContainerItem;

public class TileKnowledgeReprocessor extends TileThaumcraft implements IInventory {
  public ItemStack[] content;

  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.getBoundingBox((double)(this.xCoord - 1), (double)this.yCoord, (double)(this.zCoord - 1), (double)(this.xCoord + 2), (double)(this.yCoord + 1), (double)(this.zCoord + 2));
  }

  @Override
  public void updateEntity() {
    super.updateEntity();
  }

  @Override
  public int getSizeInventory() {
    return 2;
  }

  @Override
  public ItemStack getStackInSlot(int i) {
    return null;
  }

  @Override
  public ItemStack decrStackSize(int i, int i1) {
    return null;
  }

  @Override
  public ItemStack getStackInSlotOnClosing(int i) {
    return null;
  }

  @Override
  public void setInventorySlotContents(int i, ItemStack stack) {
    this.content[0] = stack;

    // if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
    //   stack.stackSize = this.getInventoryStackLimit();
    // }

    this.markDirty();
  }

  @Override
  public String getInventoryName() {
    return null;
  }

  @Override
  public boolean isCustomInventoryName() {
    return false;
  }

  @Override
  public int getInventoryStackLimit() {
    return 64;
  }

  @Override
  public boolean isUseableByPlayer(EntityPlayer player) {
    if (this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this) {
      return false;
    } else {
      return player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }
  }

  @Override
  public void openChest() { }

  @Override
  public void closeChest() { }

  @Override
  public boolean isItemValidForSlot(int slot, ItemStack stack) {
    return stack.getItem() instanceof IEssentiaContainerItem && slot == 0;
  }
}
