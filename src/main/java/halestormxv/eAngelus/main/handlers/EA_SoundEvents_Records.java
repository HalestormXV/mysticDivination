package halestormxv.eAngelus.main.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;


public final class EA_SoundEvents_Records
{
    //Records
    public static final SoundEvent mavismusic = getRegisteredSoundEvent("music.mavismusic");
    public static final SoundEvent motomiyaflute = getRegisteredSoundEvent("music.motomiyaflute");



    private static SoundEvent getRegisteredSoundEvent(String name)
    {
        return SoundEvent.REGISTRY.getObject(new ResourceLocation("eangel", name));

    }

    private EA_SoundEvents_Records() {}
}
