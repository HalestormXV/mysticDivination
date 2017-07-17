package halestormxv.eAngelus.mobs.entitys;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;

import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;


public class EntityCelestialBolt extends EntityThrowable {
    public static final float explosionPower = 0.75F;
    public static final int empRadius = 4;

    public EntityCelestialBolt(World world) {
        super(world);
    }

    public EntityCelestialBolt(World world, EntityLivingBase entity) {
        super(world, entity);
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
        return 0.005F;
    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
        {
            if (result.entityHit != null)
            {
                //this.inflictDamage(result);
            }
            this.setDead();
        }
    }
}
