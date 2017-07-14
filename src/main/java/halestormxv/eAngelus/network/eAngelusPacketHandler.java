package halestormxv.eAngelus.network;

import halestormxv.eAngelus.network.packets.ChatUtil;
import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.network.packets.FetchMorality;
import halestormxv.eAngelus.network.packets.SyncMorality;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Blaze on 7/7/2017.
 */
public class eAngelusPacketHandler
{

    public static final ThreadedNetworkWrapper INSTANCE = new ThreadedNetworkWrapper(Reference.NAME);

    private static int ID = 0;
    private static int nextID()
    {
        return ID++;
    }

    /**
     The first parameter is messageHandler, which is the class that handles your packet. This class must always have a default constructor, and should
     have type bound REQ that matches the next argument. The second parameter is requestMessageType which is the actual packet class. This class must
     also have a default constructor and match the REQ type bound of the previous param. The third parameter is the discriminator for the packet.
     This is a per-channel unique ID for the packet. We recommend you use a static variable to hold the ID, and then call registerMessage using id++.
     This will guarantee 100% unique IDs. The fourth and final parameter is the side that your packet will be received on.
     If you are planning to send the packet to both sides, it must be registered twice with a different discriminator.
     **/
    public static void init()
    {
        INSTANCE.registerMessage(ChatUtil.PacketNoSpamChat.Handler.class, ChatUtil.PacketNoSpamChat.class, nextID(), Side.CLIENT);
        INSTANCE.registerMessage(FetchMorality.class, FetchMorality.class, nextID(), Side.SERVER); //Key Bind Fetch Morality
        INSTANCE.registerMessage(SyncMorality.Handler.class, SyncMorality.class, nextID(), Side.CLIENT);
    }

    public static void sendToAllAround(IMessage message, TileEntity te, int range)
    {
        BlockPos p = te.getPos();
        INSTANCE.sendToAllAround(message, new NetworkRegistry.TargetPoint(te.getWorld().provider.getDimension(), p.getX(), p.getY(), p.getZ(), range));
    }

    public static void sendToAll(IMessage message)
    {
        INSTANCE.sendToAll(message);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player) { INSTANCE.sendTo(message, player); }

    //Only use if you plan on sending the pack to the server. Has nothing to do with the player. There is only one server.
    public static void sendToServer(IMessage message)
    {
        INSTANCE.sendToServer(message);
    }
}
