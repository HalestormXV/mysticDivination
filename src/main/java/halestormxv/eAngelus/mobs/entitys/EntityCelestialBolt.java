package halestormxv.eAngelus.mobs.entitys;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Arrays;

public class EntityCelestialBolt extends EntityThrowable
{
    public static final float GRAVITY = 0.3F;
    public static final Block[] BOLT_BREAKS_THROUGH =
            {Blocks.TALLGRASS, Blocks.VINE, Blocks.RED_FLOWER, Blocks.YELLOW_FLOWER, Blocks.BROWN_MUSHROOM_BLOCK, Blocks.BROWN_MUSHROOM,
                    Blocks.RED_MUSHROOM_BLOCK, Blocks.RED_MUSHROOM, Blocks.REEDS, Blocks.DOUBLE_PLANT, Blocks.DEADBUSH, Blocks.WHEAT,
                    Blocks.WATERLILY, Blocks.CARROTS, Blocks.POTATOES, Blocks.SNOW_LAYER};

    public EntityCelestialBolt(World worldIn)
    {
        super(worldIn);
    }

    public EntityCelestialBolt(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public EntityCelestialBolt(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public void inflictDamage(RayTraceResult result)
    {
        result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 4);
    }

    @Override
    protected float getGravityVelocity()
    {
        return this.GRAVITY;
    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
        {
            if (result.typeOfHit == RayTraceResult.Type.BLOCK)
            {
                Block block = this.world.getBlockState(result.getBlockPos()).getBlock();

                if (Arrays.asList(BOLT_BREAKS_THROUGH).contains(block))
                {
                    BlockPos blockpos = result.getBlockPos();
                    IBlockState blockstate = this.world.getBlockState(blockpos);
                    TileEntity te = this.world.getTileEntity(blockpos);

                    if (this.getThrower() instanceof EntityPlayer) // if thrower is a player
                    {
                        EntityPlayer player = (EntityPlayer)this.getThrower();
                        this.world.destroyBlock(blockpos, false);
                    }
                }
                else
                {
                    this.setDead();
                }
            }
            else
            {
                if (result.entityHit != null)
                {
                    this.inflictDamage(result);
                }
                this.setDead();
            }
        }
    }
}
