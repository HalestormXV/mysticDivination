package halestormxv.eAngelus.capabilities.MoralityCapability;

import halestormxv.eAngelus.capabilities.Interfaces.IMorality;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

/**
 * Created by Blaze on 7/10/2017.
 */
public class moralityStorage implements Capability.IStorage<IMorality>
{

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IMorality> capability, IMorality instance, EnumFacing side)
    {
        return new NBTTagInt(instance.getMorality());
    }

    @Override
    public void readNBT(Capability<IMorality> capability, IMorality instance, EnumFacing side, NBTBase nbt)
    {
        instance.set(((NBTPrimitive) nbt).getInt());
    }
}
