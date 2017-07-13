package halestormxv.eAngelus.capabilities.MoralityCapability;

import halestormxv.eAngelus.capabilities.Interfaces.IMorality;

/**
 * Created by Blaze on 7/10/2017.
 */
public class moralityScale implements IMorality
{
    private int morality = 0;
    //private int maxVirtue = 400;
    //private int maxSin = -400;

    @Override
    public void addSin(int points) //Subtract Morality
    {
        this.morality -= points;
    }

    @Override
    public void addVirtue(int points) //Add Morality
    {
        this.morality += points;
    }

    @Override
    public void set(int points)
    {
        this.morality = points;
    }

    @Override
    public int getMorality()
    {
        return this.morality;
    }
}
