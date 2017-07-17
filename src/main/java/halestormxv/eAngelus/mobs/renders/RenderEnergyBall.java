package halestormxv.eAngelus.mobs.renders;

import halestormxv.eAngelus.mobs.entitys.EntityCelestialBolt;
import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;


public class RenderEnergyBall extends Render<EntityCelestialBolt>
{
    private static final ResourceLocation celestialBoltTexture = new ResourceLocation(Reference.MODID, "textures/items/cel_bolt.png");
    private final RenderItem renderItem;
    protected final Item item;

    public RenderEnergyBall(RenderManager renderManager)
    {
        super(renderManager);
        this.item = eAngelusItems.celBolt;
        this.renderItem = Minecraft.getMinecraft().getRenderItem();
    }

    public void doRender(EntityCelestialBolt entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        this.renderItem.renderItem(getItemStack(entity), ItemCameraTransforms.TransformType.GROUND);
        GlStateManager.enableColorMaterial();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public ItemStack getItemStack(EntityCelestialBolt entity)
    {
        return new ItemStack(eAngelusItems.celBolt, 1, 0);
    }

    protected ResourceLocation getEntityTexture(EntityCelestialBolt entity)
    {
        return celestialBoltTexture;
    }
}