package halestormxv.eAngelus.capabilities;

import halestormxv.eAngelus.capabilities.Interfaces.IMorality;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Morality provider
 * This class is responsible for providing a capability. Other modders may
 * attach their own provider with implementation that returns another
 * implementation of IMorality to the target's (Entity, TE, ItemStack, etc.) disposal.
 */

//You can replace the ICapabilitySerializable with ICapabilityProvider if you don't need persistence and then remove serializeNBT and deserializeNBT methods.
public class moralityProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IMorality.class)
    public static final Capability<IMorality> MORALITY_CAP = null;
    private IMorality instance = MORALITY_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == MORALITY_CAP;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == MORALITY_CAP ? MORALITY_CAP.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return MORALITY_CAP.getStorage().writeNBT(MORALITY_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        MORALITY_CAP.getStorage().readNBT(MORALITY_CAP, this.instance, null, nbt);
    }
}
