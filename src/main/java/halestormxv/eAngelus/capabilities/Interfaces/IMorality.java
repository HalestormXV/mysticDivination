package halestormxv.eAngelus.capabilities.Interfaces;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Blaze on 7/10/2017.
 */
public interface IMorality
{
    public void addSin(int points); //Subtract from Morality

    public void addVirtue(int points); //Add to Morality

    public void set(int points); //Set Moraility

    public int getMorality();

    public void syncToClient();

}
