package halestormxv.eAngelus.network.packets;

import halestormxv.eAngelus.capabilities.Interfaces.IMorality;
import halestormxv.eAngelus.capabilities.MoralityCapability.moralityProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Blaze on 7/13/2017.
 */
public class SyncMorality implements IMessage
{
    private int moralityValue;

    public SyncMorality(){ }

    public SyncMorality(int moralityValue)
    {
        this.moralityValue = moralityValue;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.moralityValue = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(moralityValue);
    }

    public static class Handler implements IMessageHandler<SyncMorality, IMessage>
    {
        @Override
        public IMessage onMessage(SyncMorality message, MessageContext ctx)
        {
            Minecraft.getMinecraft().addScheduledTask(new Runnable()
            {
                @Override
                public void run()
                {
                    Minecraft mc = Minecraft.getMinecraft();
                    EntityPlayer player = mc.getMinecraft().player;
                    IMorality morality = player.getCapability(moralityProvider.MORALITY_CAP, null);
                    morality.set(message.moralityValue);
                    //System.out.println("The morality has been synced to: " + message.moralityValue);
                }
            });

            return null;
        }
    }
}

//=======================================================================================\\
/**THIS IS HOW YOU USE RUNNABLE
 * Using Runnable
 if (ctx.side == Side.SERVER) {
 return null;
 }else {

 IThreadListener mainThread = Minecraft.getMinecraft();
 mainThread.addScheduledTask(new Runnable() {
@Override
public void run() {
IMorality morality = Minecraft.getMinecraft().player.getCapability(moralityProvider.MORALITY_CAP, null);
morality.set(message.moralityValue);
System.out.println("The morality has been synced to: " + message.moralityValue);
}
});
 }
 return null;**/
//=======================================================================================\\
/**THIS IS HOW YOU USE LAMBADA
 *     public static class Handler implements IMessageHandler<SyncMorality, IMessage>
 {
 @Override
 public IMessage onMessage(SyncMorality message, MessageContext ctx) {
 EntityPlayerMP player = ctx.getServerHandler().playerEntity;
 player.mcServer.addScheduledTask(() ->
 {
 IMorality morality = player.getCapability(moralityProvider.MORALITY_CAP, null);
 morality.set(message.moralityValue);
 System.out.println("The morality has been synced to: "+message.moralityValue);
 });
 return null;
 }
 }
 */
//=======================================================================================\\