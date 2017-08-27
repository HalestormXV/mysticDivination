package halestormxv.eAngelus.gui;

import halestormxv.eAngelus.main.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Blaze on 7/11/2017.
 */
public class GuiOverlay extends Gui
{
    private final ResourceLocation moralitybar = new ResourceLocation(Reference.MODID, "textures/gui/moralitybar.png");
    private final int tex_width = 102, tex_height = 8, bar_width = 100, bar_height = 6;

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event)
    {
       if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT)
       {
           Minecraft mc = Minecraft.getMinecraft();
           mc.renderEngine.bindTexture(moralitybar);
           float oneUnit = (float)bar_width / mc.player.getMaxHealth();
           int currentWidth = (int) (oneUnit * mc.player.getHealth());
           drawTexturedModalRect(0, 0, 0, 0, tex_width, tex_height);
           drawTexturedModalRect(1, 0, 1, tex_height, currentWidth, tex_height);
       }
    }
}
