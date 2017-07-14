package halestormxv.eAngelus.capabilities.MoralityCapability;

import halestormxv.eAngelus.capabilities.Interfaces.IMorality;
import halestormxv.eAngelus.network.eAngelusPacketHandler;
import halestormxv.eAngelus.network.packets.SyncMorality;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


/**
 * Created by Blaze on 7/10/2017.
 */
public class moralityScale implements IMorality
{
    EntityPlayer player;
    private int morality = 0;
    private int maxVirtue = 400;
    private int maxSin = -400;

    @Override
    public void addSin(int points) //Subtract Morality
    {
        if (this.morality <= maxSin)
        {
            this.morality = maxSin;
        }else {
            this.morality -= points;
        }

        //this.syncToClient();
    }

    @Override
    public void addVirtue(int points) //Add Morality
    {
        if (this.morality >= maxVirtue)
        {
            this.morality = maxVirtue;
        }else {
            this.morality += points;
        }

       // this.syncToClient();
    }

    @Override
    public void set(int points)
    {
        this.morality = points;
        //this.syncToClient();
    }

    @Override
    public int getMorality() { return this.morality; }


    @Override
    public void syncToClient() {}
}
