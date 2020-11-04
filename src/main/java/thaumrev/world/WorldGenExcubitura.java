package thaumrev.world;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import thaumrev.ThaumRevLibrary;

import java.util.Random;

public class WorldGenExcubitura implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int x = chunkX * 16 + random.nextInt(16);
		int z = chunkZ * 16 + random.nextInt(16);
		int y = world.getHeightValue(x, z);

		if (world.isAirBlock(x, y, z) && ThaumRevLibrary.blockExcubitura.canBlockStay(world, x, y, z) && random.nextInt(10) == 0) {
			//DEBUG System.out.println(x + " " + y + " " + z);
			world.setBlock(x, y, z, ThaumRevLibrary.blockExcubitura, 0, 2);
			world.setBlockMetadataWithNotify(x, y, z, 15, 0);
		}
	}

}
