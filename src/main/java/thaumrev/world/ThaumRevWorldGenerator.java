package thaumrev.world;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import org.jetbrains.annotations.NotNull;
import thaumrev.ThaumRevLibrary;

import java.util.Random;

public class ThaumRevWorldGenerator implements IWorldGenerator {
  public ThaumRevWorldGenerator() {}

  @Override
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider iChunkProvider, IChunkProvider iChunkProvider1) {
    worldGeneration(random, chunkX, chunkZ, world, true);
  }

  public void worldGeneration(Random random, int chunkX, int chunkZ, World world, boolean newGen) {
    switch (world.provider.dimensionId) {
      case 0:
        this.generateOverworld(random, chunkX, chunkZ, world);
        break;
      default:
        break;
    }

    if (!newGen) {
      world.getChunkFromChunkCoords(chunkX, chunkZ).setChunkModified();
    }
  }

  public void generateOverworld(Random random, int chunkX, int chunkZ, World world) {
    generateExcubituraFlowers(random, chunkX, chunkZ, world);
  }

  public void generateExcubituraFlowers(@NotNull Random random, int chunkX, int chunkZ, @NotNull World world) {
    int x = chunkX * 16 + random.nextInt(16);
    int z = chunkZ * 16 + random.nextInt(16);
    int y = world.getHeightValue(x, z);

    if (y < world.getActualHeight()
      && (world.isAirBlock(x, y, z) || world.getBlock(x, y, z) == Blocks.grass)
      && ThaumRevLibrary.blockExcubitura.canBlockStay(world, x, y, z)
      && random.nextInt(10) == 0
    ) {
      // DEBUG log.debug(x + " " + y + " " + z);
      world.setBlock(x, y, z, ThaumRevLibrary.blockExcubitura, 0, 2);
      world.setBlockMetadataWithNotify(x, y, z, 15, 0);
    }
  }
}
