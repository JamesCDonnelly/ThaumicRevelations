package thaumrev.entities;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumrev.lib.utils.PurityHelper;

public class EntityPurity extends EntityThrowable {
  int radius = 0;
  int power = 1;

  public EntityPurity(World world, EntityLivingBase entity, int radius, int power) {
    super(world, entity);
    this.radius = radius;
    this.power = power;
  }

  public EntityPurity(World world) {
    super(world);
  }

  @Override
  protected float getGravityVelocity() {
    return 0.01F;
  }

  @Override
  public void onUpdate() {
    if (this.isInsideOfMaterial(Material.portal)) {
      this.onImpact(new MovingObjectPosition(this));
    }

    double x2 = (posX + prevPosX) / 2.0D + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F;
    double y2 = (posY + prevPosY) / 2.0D + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F;
    double z2 = (posZ + prevPosZ) / 2.0D + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F;

    Thaumcraft.proxy.wispFX2(worldObj, x2, y2, z2, 1F, 0, true, true, 0.02F);

    super.onUpdate();
  }

  @Override
  protected void onImpact(MovingObjectPosition mop) {
    for (int i = 0; i < 9; ++i) {
      float fx = (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.5F;
      float fy = (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.5F;
      float fz = (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.5F;

      Thaumcraft.proxy.wispFX2(
        worldObj,
        posX + fx,
        posY + fy,
        posZ + fz,
        0.5F,
        rand.nextInt(6),
        true,
        true,
        0.02F
      );
    }

    if (!worldObj.isRemote) {
      EntityLivingBase thrower = getThrower();
      PurityHelper.checkAndPurify(mop);
      if (power > 0 && mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && thrower instanceof EntityPlayer)
        PurityHelper.damageImpureEntity(mop.entityHit, (EntityPlayer) thrower, 4 << power);
      if (radius > 0)
        PurityHelper.purifyBiome(worldObj, mop.blockX, mop.blockZ, 4 << radius);
      setDead();
    }
  }
}
