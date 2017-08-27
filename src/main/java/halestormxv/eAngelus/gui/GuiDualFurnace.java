package halestormxv.eAngelus.gui;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.tileentity.TileEntityDualFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Blaze on 8/27/2017.
 */
public class GuiDualFurnace extends GuiContainer
{
    private static final ResourceLocation DUAL_FURNACE_TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/container/dual_furnace.png");
    private final InventoryPlayer player;
    private final TileEntityDualFurnace tileEntity;

    public GuiDualFurnace(InventoryPlayer player, TileEntityDualFurnace tileEntity)
    {
        super(new ContainerDualFurnace(player, tileEntity));
        this.player = player;
        this.tileEntity = tileEntity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = this.tileEntity.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 4, 4210752);
        this.fontRendererObj.drawString(this.player.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(DUAL_FURNACE_TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        if(TileEntityDualFurnace.isBurning(this.tileEntity))
        {
            int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 42 + 12 - k, 176, 12 - k, 14, k + 1 );
        }

        int l = this.getcookProgressScaled(24);
        int r = this.getcookProgressScaled(24);
        this.drawTexturedModalRect(this.guiLeft + 43, this.guiTop + 18, 176, 14, l + 1, 16);
        this.drawTexturedModalRect(this.guiLeft + 109, this.guiTop + 18, 176, 31, 23 - r, 16);
    }

    private int getBurnLeftScaled(int pixels)
    {
        int i = this.tileEntity.getField(1);
        if(i == 0)
            i = 200;
        return this.tileEntity.getField(0) * pixels / i;
    }

    private int getcookProgressScaled(int pixels)
    {
        int i = this.tileEntity.getField(2);
        int j = this.tileEntity.getField(3);
        return j != 0 && i !=0 ? i * pixels / j : 0;
    }
}
