package halestormxv.eAngelus.capabilities.MoralityCapability;

import halestormxv.eAngelus.capabilities.Interfaces.IMorality;
import halestormxv.eAngelus.network.eAngelusPacketHandler;
import halestormxv.eAngelus.network.packets.SyncMorality;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


/**
 * Created by Blaze on 7/10/2017.
 */
public class moralityScale implements IMorality
{
    private int morality = 0;
    private int maxVirtue = 400;
    private int maxSin = -400;
    EntityPlayer entityPlayer;

    @Override
    public void addSin(int points) //Subtract Morality
    {
        int clampValue = this.morality - points;
        this.morality = MathHelper.clamp(clampValue, this.maxSin, this.maxVirtue);
    }

    @Override
    public void addVirtue(int points) //Add Morality
    {
        int clampValue = this.morality + points;
        this.morality = MathHelper.clamp(clampValue, this.maxSin, this.maxVirtue);
    }

    @Override
    public void set(int points)
    {
        this.morality = points;
    }

    @Override
    public int getMorality() { return this.morality; }

    @Override
    public void syncToClient()
    {

    }
}
