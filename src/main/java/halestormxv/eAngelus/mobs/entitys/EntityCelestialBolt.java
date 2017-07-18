package halestormxv.eAngelus.mobs.entitys;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityCelestialBolt extends EntityThrowable {
    private float explosionRadius = 1.3F;
    private double orbPower;

    public EntityCelestialBolt(World world)
    {
        super(world);
    }

    public EntityCelestialBolt(World world, EntityLivingBase entity) {
        super(world, entity);
        if (entity instanceof EntityPlayer)
        {
            orbPower = (float)(((EntityPlayer) entity).experienceLevel) / 3;
        }
    }

    private void explode() {
        int bx = (int) posX;
        int by = (int) posY;
        int bz = (int) posZ;
        world.createExplosion(this, posX, posY, posZ, 0.75F, true);
        setDead();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (ticksExisted > 20) {
            explode();
        }

        for (int i = 0; i < 10; i++) {
            double x = (double) (rand.nextInt(10) - 5) / 8.0D;
            double y = (double) (rand.nextInt(10) - 5) / 8.0D;
            double z = (double) (rand.nextInt(10) - 5) / 8.0D;
            world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX, posY, posZ, x, y, z);
        }
    }

    @Override
    protected float getGravityVelocity() {
        return 0.01F;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        {
            if(result.entityHit instanceof EntityLiving && !this.world.isRemote)
            {
                float f2;
                f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                int k = MathHelper.ceil((double)f2 * this.orbPower);
                result.entityHit.attackEntityFrom(DamageSource.GENERIC, (float)k);
                System.out.println(k / 2 + " hearts of damage from Gunblade.");
            }
            else
            {
                if(!this.world.isRemote)
                {
                    this.world.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius, true);
                }
            }
            this.setDead();
        }
    }

    public void setHeadingFromThrower(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy) {
        float f = -MathHelper.sin(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
        float f1 = -MathHelper.sin((rotationPitchIn + pitchOffset) * 0.017453292F);
        float f2 = MathHelper.cos(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
        this.setThrowableHeading((double) f, (double) f1, (double) f2, velocity, inaccuracy);
        this.motionX += entityThrower.motionX;
        this.motionZ += entityThrower.motionZ;

        if (!entityThrower.onGround) {
            this.motionY += entityThrower.motionY;
        }
    }

    /**
     * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
     */
    public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy) {
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double) f;
        y = y / (double) f;
        z = z / (double) f;
        x = x + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        y = y + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        z = z + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        x = x * (double) velocity;
        y = y * (double) velocity;
        z = z * (double) velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f1 = MathHelper.sqrt(x * x + z * z);
        this.rotationYaw = (float) (MathHelper.atan2(x, z) * (180D / Math.PI));
        this.rotationPitch = (float) (MathHelper.atan2(y, (double) f1) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }
}
