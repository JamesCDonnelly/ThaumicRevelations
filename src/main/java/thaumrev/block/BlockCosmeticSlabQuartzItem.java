 // Source code is unavailable, and was generated by the Fernflower decompiler.
package thaumrev.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import thaumrev.ThaumRevLibrary;

public class BlockCosmeticSlabQuartzItem extends ItemSlab {
  public BlockCosmeticSlabQuartzItem(Block block) {
    super(
      block,
      (BlockSlab)ThaumRevLibrary.blockSlabInfusedQuartz,
      (BlockSlab)ThaumRevLibrary.blockSlabDoubleInfusedQuartz,
      false
    );

    this.setHasSubtypes(true);
  }
}
