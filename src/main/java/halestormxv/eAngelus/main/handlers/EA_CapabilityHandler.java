package halestormxv.eAngelus.main.handlers;

import halestormxv.eAngelus.capabilities.MoralityCapability.moralityProvider;
import halestormxv.eAngelus.main.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Blaze on 7/10/2017.
 */
public class EA_CapabilityHandler
{
    public static final ResourceLocation MORALITY_CAP = new ResourceLocation(Reference.MODID, "morality");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof EntityPlayer)) return;
        event.addCapability(MORALITY_CAP, new moralityProvider());
    }
}
