package thaumrev.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.BiomeManager;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.entities.IEldritchMob;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.monster.*;
import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

import java.util.Arrays;

public class PurityHelper {
	public static boolean isTainted(Entity entity) {
		return entity instanceof ITaintedMob;
	}

	public static boolean isEldritchOrTainted(Entity entity) {
		return entity instanceof IEldritchMob ||
			entity instanceof EntityEldritchCrab ||
			entity instanceof EntityInhabitedZombie ||
			entity instanceof ITaintedMob;
	}

	public static void summonParticles(World world, double x, double y, double z) {
		for (int i = 0; i < 2; i++) {
			float fx = (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F;
			float fy = (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F;
			float fz = (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F;

			Thaumcraft.proxy.wispFX3(world,
				x + fx,
				y + fy,
				z + fz,
				x + fx * 8.0F,
				y + fy * 8.0F,
				z + fz * 8.0F,
				0.3F,
				i % 2 == 0 ? 0 : 2,
				true,
				0.02F
			);
		}
	}

	public static void purifyBiome(World world, int x, int z, int radius) {
		for (int i = -radius; i < radius; i++) {
			for (int ii = -radius; ii < radius; ii++) {
				if (world.getBiomeGenForCoords(x + i, z + ii).biomeID == Config.biomeTaintID ||
					world.getBiomeGenForCoords(x + i, z + ii).biomeID == Config.biomeEerieID ||
					world.getBiomeGenForCoords(x + i, z + ii).biomeID == Config.biomeMagicalForestID
				) {
					BiomeGenBase[] biomesForGeneration = null;
					biomesForGeneration = world.getWorldChunkManager().loadBlockGeneratorData(
						biomesForGeneration, x + i, z + ii, 1, 1
					);

					if (biomesForGeneration != null && biomesForGeneration[0] != null) {
						BiomeGenBase biome = biomesForGeneration[0];

						if (biome.biomeID == ThaumcraftWorldGenerator.biomeTaint.biomeID) {
							biome = BiomeGenBase.plains;
						}

						Utils.setBiomeAt(world, x + i, z + ii, biome);
						summonParticles(world, x, world.getHeightValue(x, z), z);
					}
				}
			}
		}
	}

	public static void purifyEntity(@NotNull Entity toPurify) {
		World world = toPurify.worldObj;

		if (isTainted(toPurify)) {
			if (!world.isRemote) {
				Entity purified = getPureState(toPurify);
				purified.setPositionAndRotation(toPurify.posX, toPurify.posY, toPurify.posZ, toPurify.rotationYaw, toPurify.rotationPitch);

				toPurify.setDead();
				world.spawnEntityInWorld(purified);
			}
		}
	}

	public static void damageImpureEntity(Entity impureEntity, EntityPlayer player, float damage) {
		if (isEldritchOrTainted(impureEntity)) {
			EntityLivingBase impureLivingBase = (EntityLivingBase) impureEntity;
			DamageSource damageSource = new DamageSourceWarden("warden", player);

			impureLivingBase.attackEntityFrom(damageSource, damage);
			summonParticles(impureEntity.worldObj, impureEntity.posX, impureEntity.posY, impureEntity.posZ);
		}
	}

	public static void checkAndPurify(@NotNull MovingObjectPosition mop) {
		if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
			purifyEntity(mop.entityHit);
		}
	}

	public static Entity getPureState(Entity entity) {
		EntityLivingBase purifiedEntity = (EntityLivingBase) entity;
		float healthPercentage = purifiedEntity.getHealth() / purifiedEntity.getMaxHealth();

		if (entity instanceof EntityTaintChicken) {
			purifiedEntity = new EntityChicken(entity.worldObj);
		}

		if (entity instanceof EntityTaintCow) {
			purifiedEntity = new EntityCow(entity.worldObj);
		}

		if (entity instanceof EntityTaintCreeper) {
			purifiedEntity = new EntityCreeper(entity.worldObj);
		}

		if (entity instanceof EntityTaintPig) {
			purifiedEntity = new EntityPig(entity.worldObj);
		}

		if (entity instanceof EntityTaintSheep) {
			purifiedEntity = new EntitySheep(entity.worldObj);
		}

		if (entity instanceof EntityTaintSpider) {
			purifiedEntity = new EntitySpider(entity.worldObj);
		}

		if (entity instanceof EntityTaintVillager) {
			purifiedEntity = new EntityVillager(entity.worldObj);
		}

		purifiedEntity.setHealth(purifiedEntity.getMaxHealth() * healthPercentage);
		return purifiedEntity;
	}
}
