package halestormxv.eAngelus.mobs.renders;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.mobs.entitys.EntityCreeperPrime;
import halestormxv.eAngelus.mobs.entitys.EntityPhantom;
import halestormxv.eAngelus.mobs.models.ModelCreeperPrime;
import halestormxv.eAngelus.mobs.models.ModelPhantom;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/**
 * Created by Blaze on 7/16/2017.
 */
public class RenderCreeperPrime extends RenderLiving<EntityCreeperPrime>
{
    private static final ResourceLocation CREEPER_PRIME_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/creeperprime.png");
    public static final Factory FACTORY = new Factory();

    public RenderCreeperPrime(RenderManager rm)
    {
        super(rm, new ModelCreeperPrime(), 0.4F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCreeperPrime entity)
    {
        return CREEPER_PRIME_TEXTURE;
    }

    @Override
    public void doRender(EntityCreeperPrime entity, double x, double y, double z, float p_76986_8_, float p_76986_9_)
    {
        super.doRender(entity, x, y, z, p_76986_8_, p_76986_9_);
    }

    public static class Factory implements IRenderFactory<EntityCreeperPrime> {

        @Override
        public Render<? super EntityCreeperPrime> createRenderFor(RenderManager manager) {
            return new RenderCreeperPrime(manager);
        }
    }
}
