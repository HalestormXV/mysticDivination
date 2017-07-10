package halestormxv.eAngelus.network;

import halestormxv.eAngelus.network.packets.ChatUtil;
import halestormxv.eAngelus.main.Reference;
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

    public static void init()
    {
        INSTANCE.registerMessage(ChatUtil.PacketNoSpamChat.Handler.class, ChatUtil.PacketNoSpamChat.class, nextID(), Side.CLIENT);
    }

    public static void sendToAllAround(IMessage message, TileEntity te, int range) {
        BlockPos p = te.getPos();
        INSTANCE.sendToAllAround(message, new NetworkRegistry.TargetPoint(te.getWorld().provider.getDimension(), p.getX(), p.getY(), p.getZ(), range));
    }

    public static void sendToAllAround(IMessage message, TileEntity te) {
        sendToAllAround(message, te, 64);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player) {
        INSTANCE.sendTo(message, player);
    }

    public static void sendToServer(IMessage message) {
        INSTANCE.sendToServer(message);
    }
}
