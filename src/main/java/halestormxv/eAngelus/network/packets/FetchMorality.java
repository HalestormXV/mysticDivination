package halestormxv.eAngelus.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Created by Blaze on 7/12/2017.
 */
public class FetchMorality
{
    /*public static class PacketFetchMorality implements IMessage
    {
    EntityPlayer player
    IMorality morality = player.getCapability(moralityProvider.MORALITY_CAP, null);

        private int moralityValue;

        public PacketFetchMorality() {
            moralityValue;
        }

        private PacketFetchMorality(ITextComponent... lines) {
            // this is guaranteed to be >1 length by accessing methods
            this.chatLines = lines;
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeInt(chatLines.length);
            for (ITextComponent c : chatLines) {
                ByteBufUtils.writeUTF8String(buf, ITextComponent.Serializer.componentToJson(c));
            }
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            chatLines = new ITextComponent[buf.readInt()];
            for (int i = 0; i < chatLines.length; i++) {
                chatLines[i] = ITextComponent.Serializer.jsonToComponent(ByteBufUtils.readUTF8String(buf));
            }
        }
    }*/
}
