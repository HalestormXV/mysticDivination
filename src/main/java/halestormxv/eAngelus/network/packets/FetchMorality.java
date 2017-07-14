package halestormxv.eAngelus.network.packets;

import halestormxv.eAngelus.capabilities.Interfaces.IMorality;
import halestormxv.eAngelus.capabilities.MoralityCapability.moralityProvider;
import halestormxv.eAngelus.main.handlers.EA_EventHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Blaze on 7/12/2017.
 */
public class FetchMorality extends PacketBase<FetchMorality>
{
    //Extract Information
    @Override
    public void fromBytes(ByteBuf buf) {

    }

    //Add Information
    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public void handleClientSide(FetchMorality message, EntityPlayer player) {

    }

    @Override
    public void handleServerSide(FetchMorality message, EntityPlayer player)
    {
        IMorality morality = player.getCapability(moralityProvider.MORALITY_CAP, null);
        int moralityValue = morality.getMorality();
        int moralityNoNegative = EA_EventHandler.displayMorality(player);
        if (morality.getMorality() == 0) { ChatUtil.sendNoSpam(player, "\u00A7eYour current morality is: "+ moralityValue); }
        if (morality.getMorality() > 0) { ChatUtil.sendNoSpam(player, "\u00A73Your virtuous morality is: "+ moralityValue); }
        if (morality.getMorality() < 0) { ChatUtil.sendNoSpam(player, "\u00A74Your sinful morality is: "+ moralityNoNegative); }
    }
}
