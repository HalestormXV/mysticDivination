package halestormxv.eAngelus.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * Created by Blaze on 7/16/2017.
 */
@SideOnly(Side.CLIENT)
public class ModelCreeperPrime extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer back_rightleg;
    public ModelRenderer front_rightleg;
    public ModelRenderer front_leftleg;
    public ModelRenderer head2;
    public ModelRenderer head;
    public ModelRenderer back_leftleg;

    public ModelCreeperPrime() {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.body = new ModelRenderer(this, 16, 16);
        this.body.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
        this.back_rightleg = new ModelRenderer(this, 0, 16);
        this.back_rightleg.setRotationPoint(2.0F, 18.0F, 4.0F);
        this.back_rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
        this.front_rightleg = new ModelRenderer(this, 0, 16);
        this.front_rightleg.setRotationPoint(2.0F, 18.0F, -4.0F);
        this.front_rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
        this.front_leftleg = new ModelRenderer(this, 0, 16);
        this.front_leftleg.setRotationPoint(-2.0F, 18.0F, -4.0F);
        this.front_leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
        this.head2 = new ModelRenderer(this, 0, 0);
        this.head2.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.head2.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        this.head = new ModelRenderer(this, 32, 0);
        this.head.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        this.back_leftleg = new ModelRenderer(this, 0, 16);
        this.back_leftleg.setRotationPoint(-2.0F, 18.0F, 4.0F);
        this.back_leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.body.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.back_rightleg.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.front_rightleg.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.front_leftleg.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.head2.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.head.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.back_leftleg.render(scale);
        GlStateManager.disableBlend();
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.front_rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.front_leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.back_rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.back_leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }
}
