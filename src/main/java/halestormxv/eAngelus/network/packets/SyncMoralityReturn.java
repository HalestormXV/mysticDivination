package halestormxv.eAngelus.network.packets;

import halestormxv.eAngelus.main.Utils;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

import java.lang.reflect.Field;

/**
 * Created by Blaze on 7/12/2017.
 */
public class SyncMoralityReturn implements IMessage
{
    private boolean messageValid;
    private int moralityValue;
    private String className;
    private String moralityValueFieldName;

    public SyncMoralityReturn()
    {
        this.messageValid = false;
    }

    public SyncMoralityReturn(int moralityValue, String className, String moralityValueFieldName)
    {
        this.messageValid = true;
        this.moralityValue = moralityValue;
        this.className = className;
        this.moralityValueFieldName = moralityValueFieldName;
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
        buf.writeInt(this.moralityValue);
        ByteBufUtils.writeUTF8String(buf, this.className);
        ByteBufUtils.writeUTF8String(buf, this.moralityValueFieldName);
    }

    public static class Handler implements IMessageHandler<SyncMoralityReturn, IMessage>
    {
        @Override
        public IMessage onMessage(SyncMoralityReturn message, MessageContext ctx)
        {
            if(!message.messageValid && ctx.side != Side.CLIENT)
            return null;
            Minecraft.getMinecraft().addScheduledTask(() -> processMessage(message));
            return null;
        }

        void processMessage(SyncMoralityReturn message)
        {
            try{
                Class clazz = Class.forName(message.className);
                Field moralityValueFieldName = clazz.getDeclaredField(message.moralityValueFieldName);
                moralityValueFieldName.setInt(clazz, message.moralityValue);
            } catch (Exception e) {
                Utils.getLogger().catching(e);
            }

        }
    }

}
