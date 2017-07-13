package halestormxv.eAngelus.main.handlers;

import halestormxv.eAngelus.capabilities.Interfaces.IMorality;
import halestormxv.eAngelus.capabilities.MoralityCapability.moralityProvider;
import halestormxv.eAngelus.main.proxy.ClientProxy;
import halestormxv.eAngelus.network.eAngelusPacketHandler;
import halestormxv.eAngelus.network.packets.FetchMorality;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Blaze on 7/12/2017.
 */
public class EA_KeyInputHandler
{
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority= EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(InputEvent.KeyInputEvent event)
    {
        KeyBinding[] keyBindings = ClientProxy.keyBindings;
        EntityPlayer player = Minecraft.getMinecraft().player;
        IMorality morality = player.getCapability(moralityProvider.MORALITY_CAP, null);

        // check each enumerated key binding type for pressed and take appropriate action
        if (keyBindings[0].isPressed())
        {
            eAngelusPacketHandler.sendToServer(new FetchMorality());
        }
    }
}

