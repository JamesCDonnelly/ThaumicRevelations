package thaumrev.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumrev.item.baubles.ItemWardenAmulet;
import thaumrev.util.wardenic.WardenicChargeHelper;

import static thaumrev.ThaumicRevelations.log;

public class ContainerHammer extends Container {
	InventoryPlayer playerInv;
	InventoryCrafting hammerInv;
	IInventory resultInv;

	public ContainerHammer(@NotNull EntityPlayer player) {
		playerInv = player.inventory;
		hammerInv = new InventoryCrafting(this, 2, 1);
		resultInv = new InventoryCraftResult();

		for (int hotbar = 0; hotbar < 9; hotbar++) {
			addSlotToContainer(new Slot(playerInv, hotbar, 8 + 18 * hotbar, 142));
		}

		for (int row = 0; row < 3; row++) {
			for (int collumn = 0; collumn < 9; collumn++) {
				addSlotToContainer(new Slot(playerInv, 9 + row * 9 + collumn, 8 + 18 * collumn, 84 + row * 18));
			}
		}

		addSlotToContainer(new SlotEssentia(hammerInv, 0, 80, 54));
		addSlotToContainer(new Slot(hammerInv, 1, 80, 33));
		addSlotToContainer(new SlotCrafting(player, hammerInv, resultInv, 0, 80, 12));

		onCraftMatrixChanged(hammerInv);
	}

	@Override
	public void onCraftMatrixChanged(@NotNull IInventory craftingMatrix) {
		ItemStack essentia = craftingMatrix.getStackInSlot(0);
		ItemStack item = craftingMatrix.getStackInSlot(1);

		if (item != null) {
			if (item.getItem() instanceof ItemWardenAmulet) {
				if (essentia != null) {
					String aspectKey = ((IEssentiaContainerItem) essentia.getItem())
						.getAspects(essentia)
						.getAspects()[0]
						.getName();
					ItemStack infused = item.copy();

					if (WardenicChargeHelper.upgrades.containsKey(aspectKey)) {
						WardenicChargeHelper.setUpgradeOnStack(infused, aspectKey);
					}

					resultInv.setInventorySlotContents(0, infused);
				} else {
					resultInv.setInventorySlotContents(0, null);
				}
			} else {
				resultInv.setInventorySlotContents(0, null);
			}
		} else {
			resultInv.setInventorySlotContents(0, null);
		}
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);

		ItemStack essentia = this.hammerInv.getStackInSlotOnClosing(0);
		if (essentia != null) {
			player.dropPlayerItemWithRandomChoice(essentia, false);
		}

		ItemStack item = this.hammerInv.getStackInSlotOnClosing(1);
		if (item != null) {
			player.dropPlayerItemWithRandomChoice(item, false);
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		log.debug(slot);
		return null;
	}

	@Override
	public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) {
		if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem()) {
			return null;
		}
		return super.slotClick(slot, button, flag, player);
	}
}
