package halestormxv.eAngelus.mobs.renders;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.mobs.entitys.EntityPhantom;
import halestormxv.eAngelus.mobs.models.ModelPhantom;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/**
 * Created by Blaze on 7/15/2017.
 */
public class RenderPhantom extends RenderLiving<EntityPhantom>
{
    private static final ResourceLocation PHANTOM_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/phantom/mobphantom.png");
    public static final Factory FACTORY = new Factory();

    public RenderPhantom(RenderManager rm)
    {
        super(rm, new ModelPhantom(), 0.4F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPhantom entity)
    {
        return PHANTOM_TEXTURE;
    }

    @Override
    public void doRender(EntityPhantom entity, double x, double y, double z, float p_76986_8_, float p_76986_9_)
    {
        super.doRender(entity, x, y, z, p_76986_8_, p_76986_9_);
    }

    public static class Factory implements IRenderFactory<EntityPhantom> {

        @Override
        public Render<? super EntityPhantom> createRenderFor(RenderManager manager) {
            return new RenderPhantom(manager);
        }
    }
}
