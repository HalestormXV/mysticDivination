package halestormxv.eAngelus.network.packets;

import halestormxv.eAngelus.capabilities.Interfaces.IMorality;
import halestormxv.eAngelus.capabilities.MoralityCapability.moralityProvider;
import halestormxv.eAngelus.main.Utils;
import halestormxv.eAngelus.network.eAngelusPacketHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Blaze on 7/12/2017.
 */
public class SyncMorality implements IMessage
{
    private boolean messageValid;
    private int moralityValue;
    private String className;
    private String moralityValueFieldName;

    public SyncMorality(){ this.messageValid = false; }

    public SyncMorality(int moralityValue, String className, String moralityValueFieldName)
    {
        this.moralityValue = moralityValue;
        this.className = className;
        this.moralityValueFieldName = moralityValueFieldName;
        this.messageValid = true;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        try{
            this.moralityValue = buf.readInt();
            this.className = ByteBufUtils.readUTF8String(buf);
            this.moralityValueFieldName = ByteBufUtils.readUTF8String(buf);
        } catch (IndexOutOfBoundsException ioe) { Utils.getLogger().catching(ioe); return; }
        this.messageValid = true;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        if (!this.messageValid)
            return;
        buf.writeInt(moralityValue);
        ByteBufUtils.writeUTF8String(buf, this.className);
        ByteBufUtils.writeUTF8String(buf, this.moralityValueFieldName);
        //ByteBufUtils.writeTag(); Might be needed to sync data?
    }

    public static class Handler implements IMessageHandler<SyncMorality, IMessage>
    {
        @Override
        public IMessage onMessage(SyncMorality message, MessageContext ctx)
        {
            if(!message.messageValid && ctx.side != Side.SERVER)
                return null;
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> processMessage(message, ctx));
            return null;
        }
        void processMessage(SyncMorality message, MessageContext ctx)
        {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            if(player == null)
                return;
            if(!player.hasCapability(moralityProvider.MORALITY_CAP, null))
                return;
            IMorality morality = player.getCapability(moralityProvider.MORALITY_CAP, null);
            eAngelusPacketHandler.INSTANCE.sendTo(new SyncMoralityReturn(morality.getMorality(), message.className, message.moralityValueFieldName),
                    ctx.getServerHandler().playerEntity); //ctx.getServerHandler().playerEntity) = Player Who Sent Packet
        }
    }
}
